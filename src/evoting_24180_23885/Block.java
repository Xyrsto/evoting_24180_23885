/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package evoting_24180_23885;

import java.io.Serializable;
import evoting_24180_23885.Hash;
/**
 *
 * @author rodri
 */
public class Block implements Serializable{
    String previousHash; //link to previous hash
    String data; //data of the block
    int nonce; //discovered number (proof of work)
    String currentHash; //hash of the current block
    
   /**
    * 
    * @param previousHash
    * @param data
    * @param nonce
    * @param currentHash 
    * Class constructor
    */   
    public Block(String previousHash, String data, int nonce){
        this.previousHash = previousHash;
        this.data = data;
        this.nonce = nonce;
        this.currentHash = calculateHash();
    }
    
    /**
     * @returns the hashed value of the current block hash(nonce + previousHash + data) 
     */
    public String calculateHash(){
        return Hash.getHash(nonce + previousHash + data);
    }
    
    public String toString() {
        return // (isValid() ? "OK\t" : "ERROR\t")+
                 String.format("[ %8s", previousHash) + " <- " + 
                   String.format("%-10s", data) +  String.format(" %7d ] = ", nonce) + 
                String.format("%8s",currentHash);
    }
    
    public boolean isValid(){
        return currentHash.equals(calculateHash());
    }
}
