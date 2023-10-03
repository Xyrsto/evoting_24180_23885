/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package evoting_24180_23885.SecurityUtils;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;

/**
 *
 * @author rodri
 */
public class AssinaturaDigital {
    public static byte[] signature(byte[] data, PrivateKey privateKey) throws Exception{
        Signature sig = Signature.getInstance("SHA256withRSA");
        sig.initSign(privateKey);
        sig.update(data);
        return sig.sign();       
    }
    
    public static boolean verify(byte[] data, byte[] signature, PublicKey key){
        try{
            Signature sig = Signature.getInstance("SHA256withRSA");
            sig.initVerify(key);
            sig.update(data);
            return sig.verify(signature);
        }
        catch(Exception ex){
            return false;
        }
    }
}
