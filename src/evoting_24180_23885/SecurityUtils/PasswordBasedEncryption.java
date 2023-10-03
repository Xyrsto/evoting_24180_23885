/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package evoting_24180_23885.SecurityUtils;
import java.math.BigInteger;
import java.security.spec.KeySpec;
import java.util.Random;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author rodri
 */
public class PasswordBasedEncryption {
    String data;

    public PasswordBasedEncryption(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
    
    public static Cipher createPBE(int mode, String password) throws Exception
    {
        //generate salt - random 
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256"); 
        byte[] salt = password.getBytes();
        
        Random rand = new Random(new BigInteger(salt).longValue());
        rand.nextBytes(salt);
        
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 256);
        SecretKey key = factory.generateSecret(spec);
        
        SecretKeySpec secretKey = new SecretKeySpec(key.getEncoded(), "AES");
        
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        
        byte[] iv = new byte[16];
        rand.nextBytes(iv);
        
        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        
        cipher.init(mode, secretKey, ivSpec);
        
        return cipher;
    }
    
    public static byte[] encrypt(byte[] data, String password) throws Exception{
        Cipher cipher = createPBE(Cipher.ENCRYPT_MODE, password);
        return cipher.doFinal(data);
    }
    
    public static byte[] decrypt(byte[] data, String password) throws Exception{
        Cipher cipher = createPBE(Cipher.DECRYPT_MODE, password);
        return cipher.doFinal(data);
    }    
}
