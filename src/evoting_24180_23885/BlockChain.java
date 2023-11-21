/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package evoting_24180_23885;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.SwingWorker;

/**
 * A classe {@code BlockChain} representa uma cadeia de blocos utilizada para
 * armazenar informações no sistema de voto eletrónico. A cadeia de blocos é
 * composta por blocos, cada um contendo dados específicos, um número de prova
 * de trabalho (nonce) e um hash atual do bloco. A classe inclui métodos para
 * adicionar blocos à cadeia, obter o último hash da cadeia, validar a
 * integridade da cadeia e salvar e carregar a cadeia de um arquivo.
 */
public class BlockChain {

    ArrayList<Block> chain = new ArrayList<>();
    public JLabel jlabel = new JLabel();
    public String dataP;
    public MainScreen mainWindow;

    /**
     * Construtor da classe {@code BlockChain}.
     */
    public BlockChain(MainScreen mw) {
        mainWindow = mw;
    }

    /**
     * Construtor da classe {@code BlockChain}.
     */
    public BlockChain() {

    }

    /**
     * Obtém o último hash da cadeia de blocos.
     *
     * @return O último hash da cadeia de blocos.
     */
    public String getLastHash() {
        //if the blockchain is empty, return the first block's hash
        if (chain.isEmpty()) {
            return String.format("%08d", 0);
        }

        //if not, returns the hash of the last block in the blockchain
        return chain.get(chain.size() - 1).currentHash;
    }

    /**
     * Adiciona dados à cadeia de blocos.
     *
     * @param data Os dados a serem adicionados.
     * @param difficulty A dificuldade da prova de trabalho.
     */
    public void add(String data, int difficulty) {
        //previous block's hash
        String previousHash = getLastHash();
        SwingWorker<Integer, String> mineiro = new Miner(0, 0, previousHash + data, difficulty, this, mainWindow);
        dataP = data;

        //mines the nonce value for the block
        int nonce = 0;
        try {
            mineiro.execute();
            nonce = -1;
        } catch (Exception ex) {
            Logger.getLogger(BlockChain.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Adiciona um bloco à cadeia com um nonce específico.
     *
     * @param nonce O nonce do bloco.
     */
    public void addBloco(int nonce) {
        //builds the new block
        Block newBlock = new Block(getLastHash(), dataP, nonce);

        //adds the new block to the blockchain
        chain.add(newBlock);
    }

    /**
     * Obtém o bloco na posição especificada da cadeia.
     *
     * @param i A posição do bloco na cadeia.
     * @return O bloco na posição especificada.
     */
    public Block get(int i) {
        return chain.get(i);
    }

    /**
     * Representação de string da cadeia de blocos.
     *
     * @return Uma string que representa a cadeia de blocos.
     */
    @Override
    public String toString() {
        StringBuilder txt = new StringBuilder();
        txt.append("Blockchain size = ").append(chain.size()).append("\n");
        for (Block block : chain) {
            txt.append(block.toString()).append("\n");
        }
        return txt.toString();
    }

    /**
     * Salva a cadeia de blocos em um arquivo.
     *
     * @param fileName O nome do arquivo para salvar a cadeia de blocos.
     * @throws Exception Se ocorrer um erro durante a operação de salvamento.
     */
    public void save(String fileName) throws Exception {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(chain);
        }
    }

    /**
     * Carrega a cadeia de blocos de um arquivo.
     *
     * @param fileName O nome do arquivo para carregar a cadeia de blocos.
     * @throws Exception Se ocorrer um erro durante a operação de carregamento.
     */
    public void load(String fileName) throws Exception {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
            this.chain = (ArrayList<Block>) in.readObject();
        }
    }

    /**
     * Verifica se a cadeia de blocos é válida.
     *
     * @return true se a cadeia de blocos é válida, false caso contrário.
     */
    public boolean isValid() {
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
