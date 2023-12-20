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
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.server.RMISocketFactory;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;

public class SSLFactoryRMI extends RMISocketFactory {

    
    private static final String SERVER_KEYSTORE_PATH = CreateRMICertificate.STORE_FILE;
    private static final char[] SERVER_KEYSTORE_PASSWORD = CreateRMICertificate.STORE_PASSWORD.toCharArray();
    
    private static final String CLIENT_KEYSTORE_PATH = CreateRMICertificate.STORE_FILE;;
    private static final char[] CLIENT_KEYSTORE_PASSWORD = CreateRMICertificate.STORE_PASSWORD.toCharArray();

    @Override
    public ServerSocket createServerSocket(int port) throws IOException {
        try {

            // loads keystore
            KeyStore keyStore = KeyStore.getInstance("JKS");
            keyStore.load(new FileInputStream(SERVER_KEYSTORE_PATH), SERVER_KEYSTORE_PASSWORD);

            //loads a certificate
            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance("SunX509");
            keyManagerFactory.init(keyStore, SERVER_KEYSTORE_PASSWORD);

            // creates SSL context
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(keyManagerFactory.getKeyManagers(),
                    new TrustManager[]{new TrustManager()},
                    new SecureRandom());

            // Create an SSLServerSocketFactory from the SSLContext
            SSLServerSocketFactory sslServerSocketFactory = sslContext.getServerSocketFactory();

            // Create an SSLServerSocket using the SSLServerSocketFactory
            return sslServerSocketFactory.createServerSocket(port);
        } catch (Exception e) {
            throw new IOException("Failed to create SSL server socket", e);
        }
    }

    @Override
    public Socket createSocket(String host, int port) throws IOException {
        try {
            // loads keystore
            KeyStore keyStore = KeyStore.getInstance("JKS");
            keyStore.load(new FileInputStream(CLIENT_KEYSTORE_PATH), CLIENT_KEYSTORE_PASSWORD);

            //loads a certificate
            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance("SunX509");
            keyManagerFactory.init(keyStore, CLIENT_KEYSTORE_PASSWORD);

            // creates SSL context
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(keyManagerFactory.getKeyManagers(),
                    new TrustManager[]{new TrustManager()},
                    new SecureRandom());

            // creates a SSL factory
            SSLSocketFactory socketFactory = sslContext.getSocketFactory();
            // creates a socket linket to ssl server
            SSLSocket clientSocket = (SSLSocket) socketFactory.createSocket(host, port);
            // connect to server
            clientSocket.startHandshake();
            // returns connected socket
            return clientSocket;
        } catch (Exception e) {
            throw new IOException("Failed to create SSL socket", e);
        }

    }

    private class TrustManager implements X509TrustManager {

        @Override
        public void checkClientTrusted(X509Certificate[] xcs, String string) throws CertificateException {
            // Implement client certificate validation logic here
            // Throw a CertificateException if the client certificate is not valid
        }

        @Override
        public void checkServerTrusted(X509Certificate[] xcs, String string) throws CertificateException {
            // Implement server certificate validation logic here
            // Throw a CertificateException if the server certificate is not valid
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }

    }

}
