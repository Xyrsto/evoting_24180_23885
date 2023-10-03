/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package evoting_24180_23885.SecurityUtils;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.util.Arrays;

/**
 *
 * @author rodri
 */
public class EncryptionHash {
    String data;
    
    public EncryptionHash(String data){
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
    
    public static byte[] getHash(byte[] data, String algorithm) throws Exception{
        MessageDigest md = MessageDigest.getInstance(algorithm);
        md.update(data);
        return md.digest();
    }
    
    public static boolean verifyHash(byte[] data, byte[] hash, String algorithm) throws Exception{
        MessageDigest md = MessageDigest.getInstance(algorithm);
        md.update(data);
        byte[] trueHash = md.digest();
        return Arrays.equals(trueHash, hash);
    }
    
    public static void saveHash(byte[] data, String filePath) throws Exception{
        Files.write(Paths.get("hashes/" + filePath + ".hash"), data);
    }
    
    public static byte[] loadHash(String filePath) throws Exception{
        return Files.readAllBytes(Paths.get(filePath));
    }
}
