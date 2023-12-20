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

/**
 * Created on 26/09/2023, 18:23:55
 *
 * @author manso - computer
 */
import java.io.FileOutputStream;
import java.security.KeyStore;
import java.security.cert.X509Certificate;
import java.security.cert.Certificate;
import java.security.KeyPairGenerator;
import java.security.KeyPair;
import java.security.Security;
import static utils.SSLUtils.generateCertificate;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class CreateRMICertificate {
    public final static String STORE_FILE = "rmiStore.jks";
    public final static String STORE_PASSWORD = "storePassword";
    public final static String ALIAS = "RMICertificate";

    public static void main(String[] args) throws Exception {
        //bouncy castle provider
        Security.addProvider(new BouncyCastleProvider());
        //keystore password
        char[] passwordJKS = STORE_PASSWORD.toCharArray();

        // generate RSA key pair
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        KeyPair kPair = keyGen.generateKeyPair();
        // create a certificate
        X509Certificate certificado = generateCertificate(kPair);
        //Create a keystore
        KeyStore keyStore = KeyStore.getInstance("JKS");
        //init keystores
        keyStore.load(null, null);

        // Add certificate
        keyStore.setCertificateEntry(ALIAS, certificado);
        //add private key
        keyStore.setKeyEntry(ALIAS+"_private", kPair.getPrivate(), passwordJKS, new Certificate[]{certificado});
        // Save KeyStore
        try (FileOutputStream fos = new FileOutputStream(STORE_FILE)) {
            keyStore.store(fos, passwordJKS);
        }
    }
}
