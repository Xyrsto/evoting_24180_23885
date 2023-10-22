/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package evoting_24180_23885;
import evoting_24180_23885.Hash;
import javax.swing.JLabel;
import javax.swing.SwingWorker;
import java.util.List;
/**
 *
 * @author rodri
 */
public class Miner extends SwingWorker<Integer, Integer>{
    //maximum number of Nonce
    public static int MAX_NONCE = (int) 1E9;
    private int startNonce;
    private int threadId;
    private String data;
    private int difficulty;
    private String zeros;
    private volatile int result = -1;
    
    private JLabel label;
    
    public Miner(int startNonce, int threadId, String data, int difficulty, JLabel label) {
        this.startNonce = startNonce;
        this.threadId = threadId;
        this.data = data;
        this.difficulty = difficulty;
        this.zeros = String.format("%0" + difficulty + "d", 0);
        this.label = label;
    }
    
    /**
     *
     * @throws Exception
     */
    @Override
    protected Integer doInBackground() {
        int nonce = startNonce;
        while (nonce < MAX_NONCE && result == -1) { 
            String hash = Hash.getHash(nonce + data);

            publish(nonce);
            if (hash.endsWith(zeros)) {
                result = nonce;
                return result;
            }
            nonce += 1; // Increment by the number of threads to distribute work evenly
        }

        return result;
    }

    protected void process(List<Integer> chunks){
        label.setText(Hash.getHash(chunks.getLast() + data).toString());
    }
    
    public void done(){
        System.out.println("done");
    }
}