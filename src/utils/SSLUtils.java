//::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: 
//::                                                                         ::
//::     Antonio Manuel Rodrigues Manso                                      ::
//::                                                                         ::
//::     I N S T I T U T O    P O L I T E C N I C O   D E   T O M A R        ::
//::     Escola Superior de Tecnologia de Tomar                              ::
//::     e-mail: manso@ipt.pt                                                ::
//::     url   : http://orion.ipt.pt/~manso                                  ::
//::                                                                         ::
//::     This software was build with the purpose of investigate and         ::
//::     learning.                                                           ::
//::                                                                         ::
//::                                                               (c)2023   ::
//:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
//////////////////////////////////////////////////////////////////////////////
package utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.math.BigInteger;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Date;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import javax.security.auth.x500.X500Principal;
import org.bouncycastle.x509.X509V3CertificateGenerator;

/**
 * Created on 27/09/2023, 16:19:15
 *
 * @author manso - computer
 */
public class SSLUtils {

    public static boolean DEBUG = true;
    //command  line to create a keystore with a certificate
// keytool -genkeypair -keyalg RSA -keystore keystore.jks -keypass password -storepass password -validity 365 -keysize 2048 -dname "CN=My Name, OU=My Organization, O=My Company, L=City, ST=State, C=US"

    /**
     * creates a self signed certificate
     *
     * @param keypair key par
     * @return X509Certificate
     * @throws Exception
     */
    public static X509Certificate generateCertificate(KeyPair keypair) throws Exception {
        //configuratino of the self signed certificate
        String emmiterDN
                = "CN=Distributed Computing,"
                + " OU=Polytecnic University of Tomar,"
                + " O=IPT,"
                + " L=Tomar,"
                + " ST=Mean Tagus,"
                + " C=PT";
        // emiter and subject are the same in self signed certificates
        String subjectDN = emmiterDN;
        //serial number
        BigInteger numeroSerial = new BigInteger(64, new SecureRandom());
        //start date = yesterday
        Date startDate = new Date(new Date().getTime() - 24 * 60 * 60 * 1000);
        // validity = 1 year
        Date endDate = new Date(new Date().getTime() + 365 * 24 * 60 * 60 * 1000);

        // signature algorithm
        String signAlgorithm = "SHA256withRSA";
        // Creates a certificate generator
        X509V3CertificateGenerator certGenerator = new X509V3CertificateGenerator();
        certGenerator.setSerialNumber(numeroSerial);
        certGenerator.setSubjectDN(new X500Principal(subjectDN));
        certGenerator.setIssuerDN(new X500Principal(emmiterDN));
        certGenerator.setNotBefore(startDate);
        certGenerator.setNotAfter(endDate);
        certGenerator.setPublicKey(keypair.getPublic());
        certGenerator.setSignatureAlgorithm(signAlgorithm);
        //creates a certificate
        X509Certificate cert = certGenerator.generate(keypair.getPrivate(), "BC");
        if (DEBUG) {
            System.out.println("Certificate ready: " + cert.toString());
        }
        //returns certificate
        return cert;

    }

    /**
     * adds a certificate to keyStore
     *
     * @param alias alias of certificate
     * @param password password do certificado
     * @param fileName ficheiro para guardar
     * @return
     * @throws Exception
     */
    public static X509Certificate addNewCertificate(String alias, String fileName, String password) throws Exception {
        //size of RSA key
        final int RSA_KEY_SIZE = 2048;

        KeyStore keyStore = KeyStore.getInstance("JKS");
        try {
            //load if exists
            keyStore.load(new FileInputStream(fileName), password.toCharArray());
        } catch (Exception e) {
            //create one
            keyStore.load(null, null);
        }

        // generates new KeyPair
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(RSA_KEY_SIZE);
        KeyPair keys = keyGen.generateKeyPair();

        // generates a certificate with the keys
        X509Certificate cert = generateCertificate(keys);
        // add a certificate
        keyStore.setCertificateEntry(alias, cert);
        // add private key
        keyStore.setKeyEntry(alias + "_Key", keys.getPrivate(),
                password.toCharArray(), new Certificate[]{cert});

        // save certificate in KeyStore 
        try (FileOutputStream fos = new FileOutputStream(fileName)) {
            keyStore.store(fos, password.toCharArray());
        }
        if (DEBUG) {
            System.out.println("certificate ready: " + alias + " in " + fileName);
        }
        return cert;
    }

    public static KeyStore loadKeyStore(String fileName, String password) throws Exception {
        KeyStore keyStore = KeyStore.getInstance("JKS");
        try {
            //load if exists
            keyStore.load(new FileInputStream(fileName), password.toCharArray());
        } catch (Exception e) {
            //create one
            keyStore.load(null, null);
        }
        if (DEBUG) {
            System.out.println("KeyStore " + fileName + " loaded");
        }
        return keyStore;
    }

    public static void saveKeyStore(KeyStore keyStore, String fileName, String password) throws Exception {
        // save certificate in KeyStore 
        try (FileOutputStream fos = new FileOutputStream(fileName)) {
            keyStore.store(fos, password.toCharArray());
        }
        if (DEBUG) {
            System.out.println("KeyStore " + fileName + " saved");
        }

    }

    public static class TrustManager implements X509TrustManager {

       
        @Override
        public void checkClientTrusted(X509Certificate[] xcs, String authType) throws CertificateException {
            // Implement client certificate validation logic here
            // Throw a CertificateException if the client certificate is not valid       
        }

        @Override
        public void checkServerTrusted(X509Certificate[] xcs, String authType) throws CertificateException {            
            // Implement server certificate validation logic here
            // Throw a CertificateException if the server certificate is not valid            
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }

    }

    public static SSLServerSocket createServerSocketSSL(String storeFileName, String storePassword, int serverPort) throws Exception {

        // loads keystore
        char[] password = storePassword.toCharArray();
        KeyStore keyStore = KeyStore.getInstance("JKS");
        keyStore.load(new FileInputStream(storeFileName), password);

        //loads a certificate
        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance("SunX509");
        keyManagerFactory.init(keyStore, password);

        // creates SSL context
        SSLContext sslContext = SSLContext.getInstance("TLS");

        sslContext.init(keyManagerFactory.getKeyManagers(),
                new TrustManager[]{new TrustManager()},
                new SecureRandom());

        //creates a SSLServerSocket
        SSLServerSocketFactory socketFactory = sslContext.getServerSocketFactory();
        if (DEBUG) {
            System.out.println("SSLServerSocket ready port: " + 10_010);
        }
        return (SSLServerSocket) socketFactory.createServerSocket(10_010);
    }

    public static SSLSocket createSocketSSL(String serverHost, int serverPort) throws Exception {
        // creates a SSL context
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, new TrustManager[]{new TrustManager()}, new SecureRandom());
        // creates a SSL factory
        SSLSocketFactory socketFactory = sslContext.getSocketFactory();
        // creates a socket linket to ssl server
        SSLSocket clientSocket = (SSLSocket) socketFactory.createSocket(serverHost, serverPort);
        // connect to server
        clientSocket.startHandshake();
        if (DEBUG) {
            System.out.println("SSLSocket conected to " + serverHost + ":" + serverPort);
        }
        // returns connected socket
        return clientSocket;
    }

}
