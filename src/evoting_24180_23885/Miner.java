/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package evoting_24180_23885;
import java.security.MessageDigest;
import java.util.Base64;
import javax.swing.JLabel;
import javax.swing.SwingWorker;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
/**
 * Classe que representa um minerador 
 * @author Rodrigo Serra, Rúben Cardoso
 */
public class Miner extends SwingWorker<Integer, String>{
    public static int MAX_NONCE = (int) 1E9;
    private int startNonce;
    private int threadId;
    private String data;
    private int difficulty;
    private String zeros;
    private volatile int result = -1;
    private int THREAD_COUNT = Runtime.getRuntime().availableProcessors();
    public MiningWindow ver = new MiningWindow();
    public int nonceP;
    public BlockChain blockchain;
    public MainScreen mainWindow;
    
    
    /**
     * Construtor da classe Miner.
     * @param startNonce O Nonce inicial a ser minerado por esta thread.
     * @param threadId O identificador da thread.
     * @param data Os dados a serem usados na mineração.
     * @param difficulty A dificuldade da mineração (número de zeros no hash).
     * @param bc A cadeia de blocos a ser atualizada após a mineração.
     * @param mw A tela principal que será atualizada após a mineração.
     */
    public Miner(int startNonce, int threadId, String data, int difficulty, BlockChain bc, MainScreen mw) {
        this.startNonce = startNonce;
        this.threadId = threadId;
        this.data = data;
        this.difficulty = difficulty;
        this.zeros = String.format("%0" + difficulty + "d", 0);
        blockchain = bc;
        mainWindow = mw;
    }
    
    /**
     * Método doInBackground().
     * Método de mineração de Nonces.
     * @return O resultado da mineração.
     */
    @Override 
    protected Integer doInBackground() {        
        ver.setVisible(true);           
        AtomicInteger result = new AtomicInteger(-1);
        ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);
        try {
            for (int i = 0; i < THREAD_COUNT; i++) {
                int threadNonce = i; // Distribute work evenly among threads
                executor.submit(() -> mineNonce(threadNonce, data, result));
            }
            executor.awaitTermination(1, TimeUnit.HOURS);
            return result.get();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }

        return result.get();
    }
        
    /**
     * Método que realiza a mineração do Nonce durante o método doInBackground.
     * @param startNonce O Nonce inicial a ser minerado por esta thread.
     * @param data Os dados a serem usados na mineração.
     * @param result O resultado a ser devolvido após a mineração.
     */
    private void mineNonce(int startNonce, String data, AtomicInteger result) {
        int nonce = startNonce;
        try {
            MessageDigest hasher = MessageDigest.getInstance("SHA3-256");
            while (result.get() == -1) {
                hasher.reset();
                hasher.update((nonce + data).getBytes());
                String hash =  Base64.getEncoder().encodeToString(hasher.digest());
                publish(hash);
                if (hash.startsWith(zeros)) {
                    nonceP = nonce;
                    result.set(nonce);
                    ver.progressText.append("Hash : " + hash + "nonce: " + nonce + "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                    System.out.println("Thread " + Thread.currentThread().getName() + "encontrou o nonce " + nonce);
                    blockchain.addBloco(nonceP);
                    mainWindow.updateText();
                    return;
                }
                nonce += THREAD_COUNT; // Increment by the number of threads to distribute work evenly
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }
    
    
    /**
     * Processa os valores intermediários da mineração e atualiza a janela de mineração.
     * @param chunks Uma lista de valores de Nonce intermediários.
     */
    protected void process(List<String> chunks){
        ver.progressText.append(chunks.getLast() + "\n");
    }
    
}