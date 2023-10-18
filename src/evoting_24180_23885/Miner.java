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
public class Miner extends Thread{
    //maximum number of Nonce
    public static int MAX_NONCE = (int) 1E9;
    private int startNonce;
    private int threadId;
    private String data;
    private int difficulty;
    private String zeros;
    private volatile int result = -1;
    
    public Miner(int startNonce, int threadId, String data, int difficulty) {
        this.startNonce = startNonce;
        this.threadId = threadId;
        this.data = data;
        this.difficulty = difficulty;
        this.zeros = String.format("%0" + difficulty + "d", 0);
    }
    
    @Override
    public void run() {
        int nonce = startNonce;
        while (nonce < MAX_NONCE && result == -1) {
            String hash = Hash.getHash(nonce + data);

            if (hash.endsWith(zeros)) {
                result = nonce;
            }
            nonce += 1; // Increment by the number of threads to distribute work evenly
        }
    }
      
    public int getResult() {
        return result;
    }
      
    public static int getNonce(String data, int difficulty, Miner mineiro) throws InterruptedException {            
        //Miner mineiro = new Miner(0, 0, data, difficulty);

        mineiro.start();
        mineiro.join();
        return mineiro.getResult();
    }
}