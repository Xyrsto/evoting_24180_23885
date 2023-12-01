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
import java.io.PrintStream;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;
import java.util.Scanner;
import javax.crypto.SecretKey;

/**
 * Created on 29/09/2023, 17:23:07
 *
 * @author manso - computer
 */
public class User {

    private final String name;
    private final PublicKey pubKey;
    private final PrivateKey privKey;
    private final SecretKey simKey;

    public User(String name) throws Exception {
        this.name = name;
        simKey = (SecretKey) SecurityUtils.generateKey();
        KeyPair kp = SecurityUtils.generateRSAKeyPair();
        privKey = kp.getPrivate();
        pubKey = kp.getPublic();
    }

    public User(String name, PublicKey pubKey, PrivateKey privKey, SecretKey simKey) {
        this.name = name;
        this.pubKey = pubKey;
        this.privKey = privKey;
        this.simKey = simKey;
    }
    
    
    public String getName() {
        return name;
    }

    public PublicKey getPubKey() {
        return pubKey;
    }

    public PrivateKey getPrivKey() {
        return privKey;
    }

    public SecretKey getSimKey() {
        return simKey;
    }

    @Override
    public String toString() {

        String pub = Base64.getEncoder().encodeToString(pubKey.getEncoded());
        String priv = privKey != null ? Base64.getEncoder().encodeToString(privKey.getEncoded()) : "NONE";
        String sim = simKey != null ? Base64.getEncoder().encodeToString(simKey.getEncoded()) : "NONE";
        return "Name\t: " + name
                + "\nPub\t: " + pub
                + "\nPrv\t: " + priv
                + "\nSim\t: " + sim;
    }
    
    

    public static String getUserFileName(String name) throws Exception {
        return Converter.byteArrayToHex(
                SecurityUtils.calculateHash(
                        SecurityUtils.calculateHash(name.getBytes(), "SHA3-512"),
                        "MD5")) + ".user";
    }

    public void save(String password) throws Exception {
        String fileName = getUserFileName(name);
        PrintStream out = new PrintStream(new FileOutputStream(fileName));
        out.println(name);
        out.println(Base64.getEncoder().encodeToString(pubKey.getEncoded()));
        out.println(Base64.getEncoder().encodeToString(SecurityUtils.encrypt(simKey.getEncoded(), pubKey)));
        out.println(Base64.getEncoder().encodeToString(SecurityUtils.encrypt(privKey.getEncoded(), password)));
        out.close();
    }

    public static User loadUser(String... params) throws Exception {
        params[0] = getUserFileName(params[0]);
        return load(params);
    }

    /**
     * loads an user from filename
     *
     * @param params array of parameters
     * <br>params[0] - filename (mandatory)
     * <br>params[1] - password (optional)
     *
     * @return User
     * @throws Exception
     */
    public static User load(String... params) throws Exception {
        Scanner file = new Scanner(new FileInputStream(params[0]));
        String name = file.nextLine();
        String pub = file.nextLine();
        String priv = file.nextLine();
        String sim = file.nextLine();

        PublicKey pubKey = SecurityUtils.getPublicKey(Base64.getDecoder().decode(pub));
        PrivateKey privKey = null;
        SecretKey simKey = null;
        //try to load keys 
        try {
            privKey = SecurityUtils.getPrivateKey(
                    SecurityUtils.decrypt(Base64.getDecoder().decode(priv), params[1]));
            simKey = (SecretKey) SecurityUtils.getKey(
                    SecurityUtils.decrypt(Base64.getDecoder().decode(sim), params[1]));
        } catch (Exception e) {
        }

        return new User(name,
                SecurityUtils.getPublicKey(Base64.getDecoder().decode(pub)),
                null, null);
    }

    public static void main(String[] args) throws Exception {
        User u = new User("manso");
        System.out.println(u);
        u.save("password");
        u = User.loadUser("manso","password");
        System.out.println(u);

    }


}
