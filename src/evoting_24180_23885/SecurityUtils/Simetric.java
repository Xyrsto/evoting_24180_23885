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
public class Simetric 
{
    private String input;

    public Simetric(String input) 
    {
        this.input = input;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }
    
    public static Key generateAESKey(int keySize) throws Exception{
        return generateAESKey(keySize, "SunJCE");
    }
    
    public static Key generateBlowfishKey(int keySize) throws Exception{
        return generateBlowfishKey(keySize, "SunJCE");
    }
    
    public static Key generateAESKey(int keySize, String provider)throws Exception{
        //gerador de keys
        KeyGenerator keyGen = KeyGenerator.getInstance("AES",provider);
        
        //tamanho da chave
        keyGen.init(keySize);
        
        //gera e guarda a chave
        Key key = keyGen.generateKey();              
        return key;
    }
    
    public static Key generateBlowfishKey(int keySize, String provider)throws Exception{
        //gerador de keys
        KeyGenerator keyGen = KeyGenerator.getInstance("Blowfish",provider);
        
        //tamanho da chave
        keyGen.init(keySize);
        
        //gera e guarda a chave
        Key key = keyGen.generateKey();
        return key;
    }
    
    public static byte[] encrypt(byte[] data, Key key) throws Exception{
        //cria um objeto do tipo cifragem de chave
        Cipher cipher = Cipher.getInstance(key.getAlgorithm());
        
        //configurar o objeto para cifrar
        cipher.init(Cipher.ENCRYPT_MODE, key);
        
        //cifra os dados
        return cipher.doFinal(data);
    }
    
    public static byte[] decrypt(byte[] data, Key key)throws Exception{
        //cria um objeto do tipo cifragem de chave
        Cipher cipher = Cipher.getInstance(key.getAlgorithm());
        
        //configurar o modo da cifra
        cipher.init(Cipher.DECRYPT_MODE, key);
        
        //decifra os dados
        return cipher.doFinal(data);
    }
    
    public static void saveKey(Key key, String num) throws Exception{
        //Timestamp time = new Timestamp(System.currentTimeMillis());
        Files.write(Paths.get("keys/USER" + num + ".sim"), key.getEncoded());
    }
    
    public static Key loadKey(String keyName, String algoritmo) throws Exception{
        // TODO add your handling code here:
        byte[] encoded = Files.readAllBytes(Paths.get(keyName));
        Key key = new SecretKeySpec(encoded, algoritmo);
        return key;
    }
    
    public static Key getAESKey(byte[] key) {
        return new SecretKeySpec(key, "AES");
    }
    
    
}
