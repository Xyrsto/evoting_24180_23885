/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package evoting_24180_23885;
import evoting_24180_23885.Hash;
import java.security.MessageDigest;
import java.util.Base64;
import javax.swing.JLabel;
import javax.swing.SwingWorker;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
/**
 * Classe que representa um minerador 
 * @author Rodrigo Serra, Rúben Cardoso
 */
public class Miner extends SwingWorker<Integer, String>{
    //maximum number of Nonce
    public static int MAX_NONCE = (int) 1E9;
    private int startNonce;
    private int threadId;
    private String data;
    private int difficulty;
    private String zeros;
    private volatile int result = -1;
    private int THREAD_COUNT = Runtime.getRuntime().availableProcessors();
    
            
    private JLabel label;
    
    
    /**
     * Construtor da classe Miner.
     * @param startNonce O Nonce inicial a ser minerado por esta thread.
     * @param threadId O identificador da thread.
     * @param data Os dados a serem usados na mineração.
     * @param difficulty A dificuldade da mineração (número de zeros no hash).
     * @param label O rótulo onde o hash mais recente será exibido.
     */
    public Miner(int startNonce, int threadId, String data, int difficulty, JLabel label) {
        this.startNonce = startNonce;
        this.threadId = threadId;
        this.data = data;
        this.difficulty = difficulty;
        this.zeros = String.format("%0" + difficulty + "d", 0);
        this.label = label;
    }
    
//    /**
//     *
//     * @throws Exception
//     */
//    @Override
//    protected Integer doInBackground() {
//        int cores = Runtime.getRuntime().availableProcessors();
//        int nonce = startNonce;
//        while (nonce < MAX_NONCE && result == -1) { 
//            String hash = Hash.getHash(nonce + data);
//
//            publish(nonce);
//            if (hash.endsWith(zeros)) {
//                result = nonce;
//                return result;
//            }
//            nonce += 1; // Increment by the number of threads to distribute work evenly
//        }
//
//        return result;
//    }
    
    
    /**
     * Método doInBackground()
     * Método de mineração de Nonces 
     * @return result 
     */
    @Override 
    protected Integer doInBackground() {
        AtomicInteger result = new AtomicInteger(-1);
        ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);
        //CountDownLatch latch = new CountDownLatch(THREAD_COUNT);
        try {
            for (int i = 0; i < THREAD_COUNT; i++) {
                int threadNonce = i; // Distribute work evenly among threads
                executor.submit(() -> mineNonce(threadNonce, data, result));
            }

            // Wait for all threads to complete
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
     * Método que vai minar o nonce dentro do método doInBackground
     * @param startNonce O Nonce inicial a ser minerado por esta thread.
     * @param data Os dados a serem usados na mineração.
     * @param result é o resultado a ser devolvido depois de ser minado
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
                    result.set(nonce);
                    System.out.println("Thread " + Thread.currentThread().getName() + "encontrou o nonce " + nonce);
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
     * Processa os valores intermediários da mineração e atualiza o rótulo.
     * @param chunks Uma lista de valores de Nonce intermediários.
     */
    protected void process(List<String> chunks){
        label.setText((chunks.getLast() + data));
    }
    
    /**
     * Método chamado quando a tarefa do minerador está concluída.
     * @param chain A cadeia de blocos na qual o Nonce foi inserido.
     */
    public void done(BlockChain chain){
        
        System.out.println("done");
    }
}