/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package evoting_24180_23885;
import evoting_24180_23885.Hash;
/**
 *
 * @author rodri
 */
public class Miner {
    //maximum number of Nonce
    public static int MAX_NONCE = (int)1E9;
    
    /**
     * @param data
     * @param difficulty
     * @returns the nonce value for the block 
     */
    public static int getNonce(String data, int difficulty){
        //String of zeros
        String zeros = String.format("%0" + difficulty + "d", 0);
        
        //starting nonce
        int nonce = 0;
        
        while(nonce < MAX_NONCE){
            String hash = Hash.getHash(nonce + data);
            
            if(hash.endsWith(zeros)){
                return nonce;
            }
            nonce++;
        }
        return nonce;
    }
}
