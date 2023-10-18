/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package evoting_24180_23885;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import evoting_24180_23885.Miner;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author rodri
 */
public class BlockChain {
    ArrayList<Block> chain = new ArrayList<>();
    
    /**
     * gets the last hash of the blockchain
     * @returns last hash of the blockchain
     */
    public String getLastHash(){
        //if the blockchain is empty, return the first block's hash
        if(chain.isEmpty()){
            return String.format("%08d", 0);
        }
        
        //if not, returns the hash of the last block in the blockchain
        return chain.get(chain.size() - 1).currentHash;
    }
    
    /**
     * adds data to the blockchain
     * @param data
     * @param difficulty 
     */
    public void add(String data, int difficulty){
        //previous block's hash
        String previousHash = getLastHash();
        Miner mineiro = new Miner(0,0, previousHash + data, difficulty);
        
        //mines the nonce value for the block
        int nonce = 0;      
        try {
            //nonce = Miner.getNonce(previousHash + data, difficulty);
            nonce = Miner.getNonce(previousHash + data, difficulty, mineiro);
        } catch (Exception ex) {
            Logger.getLogger(BlockChain.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //builds the new block
        Block newBlock = new Block(previousHash, data, nonce);
        
        //adds the new block to the blockchain
        chain.add(newBlock);
    }
    
    /**
     * @param i
     * @returns the block at the i position
     */
    public Block get(int i){
        return chain.get(i);
    }
    
    @Override
    public String toString() 
    {
        StringBuilder txt = new StringBuilder();
        txt.append("Blockchain size = ").append(chain.size()).append("\n");
        for (Block block : chain) {
            txt.append(block.toString()).append("\n");
        }        
        return txt.toString();
    }
    
    public void save(String fileName) throws Exception {
        try ( ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(chain);
        }
    }

    public void load(String fileName) throws Exception {
        try ( ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
            this.chain = (ArrayList<Block>) in.readObject();
        }
    }
    
    public boolean isValid() 
    {
        //Validate blocks
        for (Block block : chain) {
            if (!block.isValid()) {
                return false;
            }
        }
        //validate Links
        //starts in the second block
        for (int i = 1; i < chain.size(); i++) {
            //previous hash !=  hash of previous
            if (chain.get(i).previousHash != chain.get(i - 1).currentHash) {
                return false;
            }
        }
        return true;
    }
    
    
}
