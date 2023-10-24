/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package evoting_24180_23885;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rodri
 */
public class Hash {
    private static MessageDigest hasher = null;

    static {
        try {
            hasher = MessageDigest.getInstance("SHA3-256");
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Miner.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * @param n
     * @returns the Integer value of the integer parameter n to Hex, uppercasing it 
     */
    public static String toHexString(int n){
        return Integer.toHexString(n).toUpperCase();
    }
    
    /**
     * @param data
     * @returns the hex value of the hashed parameter data 
     */
    public static String getHash(String data){
        hasher.reset();
        hasher.update(data.getBytes());
        return Base64.getEncoder().encodeToString(hasher.digest());
    }
}
