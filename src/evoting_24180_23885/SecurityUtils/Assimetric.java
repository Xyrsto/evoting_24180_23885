/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package evoting_24180_23885.SecurityUtils;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.InvalidAlgorithmParameterException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.sql.Timestamp;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.Date;
import java.util.IllegalFormatException;
import java.util.Random;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
/**
 *
 * @author rodri
 */
public class Assimetric {
    String data;
    
    public Assimetric(String data){
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
    
    public static KeyPair generateKeyPair(int size) throws Exception{
        System.out.println("Generating RSA key pair...");
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(size);
        return keyGen.generateKeyPair();
    }
    
    public static void saveKey(Key key, String filePath) throws Exception{
        Files.write(Paths.get(filePath), key.getEncoded());
    }
    
    public static PublicKey getPublicKey(String filePath) throws Exception{
        //ler ficheiro 
        byte[] data = Files.readAllBytes(Paths.get(filePath));
        
        X509EncodedKeySpec pubSpec = new X509EncodedKeySpec(data);
        
        //objeto para gerar chave RSA
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        
        //gerar chave public
        return keyFactory.generatePublic(pubSpec);
    }
    
    public static PrivateKey getPrivateKey(String filePath) throws Exception{
        //ler ficheiro
        byte[] data = Files.readAllBytes(Paths.get(filePath));
        PKCS8EncodedKeySpec privSpec = new PKCS8EncodedKeySpec(data);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(privSpec);
    }
    
    public static PrivateKey getPrivateKey(byte[] privData) throws Exception {
        //especificações da chave privada PKCS8
        PKCS8EncodedKeySpec privSpec = new PKCS8EncodedKeySpec(privData);
        KeyFactory keyFactory;
        //objecto para gerar a chave RSA        
        try {//test RSA
            keyFactory = KeyFactory.getInstance("RSA");
            //Gerar a chave privada
            return keyFactory.generatePrivate(privSpec);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException | NullPointerException ex) {
            try {//test EC
                keyFactory = KeyFactory.getInstance("EC");
                //Gerar a chave privada
                return keyFactory.generatePrivate(privSpec);
            } catch (Exception ex2) {
                throw new InvalidAlgorithmParameterException();
            }
        }
    }
    
}
