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

import java.security.Key;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

/**
 * Created on 28/09/2023, 05:47:12
 *
 * @author IPT - computer
 * @version 1.0
 */
public class TestSecurityUtils {

    public static void main(String[] args) throws Exception {
        String txt = "Hello secret world";
        byte[] data = txt.getBytes();
        System.out.println("ORIGINAL \t= " + txt);
        //::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
        //HASH
        //::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
        byte[] hash = SecurityUtils.calculateHash(data);
        hash = SecurityUtils.calculateHash(data, "SHA3-256");
        hash = SecurityUtils.calculateHash(data, "SHA3-256", "SUN");

        System.out.println("Hash \t= " + Base64.getEncoder().encodeToString(hash));

        System.out.println("Hash OK?\t= " + SecurityUtils.verifyHash(data, hash));
        System.out.println("Hash OK?\t= " + SecurityUtils.verifyHash(data, hash, "SHA3-256"));
        System.out.println("Hash OK?\t= " + SecurityUtils.verifyHash(data, hash, "SHA3-256", "SUN"));

        //::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
        // KEYS
        //::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
        Key k1 = SecurityUtils.generateKey("AES", 256, "SunJCE");
        Key k2 = SecurityUtils.generateKey(256);
        Key k3 = SecurityUtils.generateKey();
        KeyPair kec1 = SecurityUtils.generateECKeyPair(256, "SunEC");
        KeyPair kec2 = SecurityUtils.generateECKeyPair(256);
        KeyPair kec3 = SecurityUtils.generateECKeyPair();
        KeyPair krsa1 = SecurityUtils.generateRSAKeyPair(2048, "SunRsaSign");
        KeyPair krsa2 = SecurityUtils.generateRSAKeyPair(2048);
        KeyPair krsa3 = SecurityUtils.generateRSAKeyPair();

        //save / load
        SecurityUtils.saveKey(k1, "AES.sim");
        k1 = SecurityUtils.loadKey("AES.sim");

        SecurityUtils.saveKey(krsa1.getPrivate(), "RSA.priv");
        PrivateKey kpriv = SecurityUtils.loadPrivateKey("RSA.priv");

        SecurityUtils.saveKey(krsa1.getPublic(), "RSA.pub");
        PublicKey kpub = SecurityUtils.loadPublicKey("RSA.pub");

        // from / to byte Arrays        
        byte[] kdata = k1.getEncoded();
        Key k = SecurityUtils.getKey(kdata, "AES");
        k = SecurityUtils.getKey(kdata);

        //:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: 
        //encrypt / decrypt
        //::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
        byte[] secret = SecurityUtils.encrypt(data, k1);
        System.out.println("Secret AES \t\t= " + Base64.getEncoder().encodeToString(secret));
        byte[] plain = SecurityUtils.decrypt(secret, k1);
        System.out.println("Message \t\t= " + new String(plain));

        secret = SecurityUtils.encrypt(data, "password");
        System.out.println("Secret passsword \t= " + Base64.getEncoder().encodeToString(secret));
        plain = SecurityUtils.decrypt(secret, "password");
        System.out.println("Message \t\t= " + new String(plain));

        secret = SecurityUtils.encrypt(data, krsa1.getPublic());
        System.out.println("Secret RSA pub-priv\t= " + Base64.getEncoder().encodeToString(secret));
        plain = SecurityUtils.decrypt(secret, krsa1.getPrivate());
        System.out.println("Message \t\t= " + new String(plain));

        secret = SecurityUtils.encrypt(data, krsa1.getPrivate());
        System.out.println("Secret RSA priv-pub\t= " + Base64.getEncoder().encodeToString(secret));
        plain = SecurityUtils.decrypt(secret, krsa1.getPublic());
        System.out.println("Message \t\t= " + new String(plain));

        //:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: 
        //signature
        //::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
        byte[] sign = SecurityUtils.sign(data, krsa1.getPrivate());
        System.out.println("Signature RSA \t= " + Base64.getEncoder().encodeToString(sign));
        System.out.println("Signature OK?\t= " + SecurityUtils.verifySign(data, sign, krsa1.getPublic()));

        sign = SecurityUtils.sign(data, kec1.getPrivate());
        System.out.println("Signature EC \t= " + Base64.getEncoder().encodeToString(sign));
        System.out.println("Signature OK?\t= " + SecurityUtils.verifySign(data, sign, kec1.getPublic()));

    }

    //::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    private static final long serialVersionUID = 202309280547L;
    //:::::::::::::::::::::::::::  Copyright(c) M@nso  2023  :::::::::::::::::::
    ///////////////////////////////////////////////////////////////////////////
}
