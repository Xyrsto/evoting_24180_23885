/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package evoting_24180_23885;

/**
 *
 * @author rodri
 */
public class Hash {
    
    /**
     * 
     * @param n
     * @returns the Integer value of the integer parameter n to Hex, uppercasing it 
     */
    public static String toHexString(int n){
        return Integer.toHexString(n).toUpperCase();
    }
    
    /**
     * 
     * @param data
     * @returns the hex value of the hashed parameter data 
     */
    public static String getHash(String data){
        return toHexString(data.hashCode());
    }
}
