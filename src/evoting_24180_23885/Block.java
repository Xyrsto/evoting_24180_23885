/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package evoting_24180_23885;

import java.io.Serializable;
import evoting_24180_23885.Hash;

/**
 * A classe {@code Block} representa um bloco em uma cadeia de blocos utilizada
 * para armazenar informações no sistema de voto eletrónico. Cada bloco contém
 * um hash do bloco anterior, dados específicos do bloco, um número de prova de
 * trabalho (nonce) e um hash atual do bloco.
 */
public class Block implements Serializable {

    String previousHash; //link to previous hash
    String data; //data of the block
    int nonce; //discovered number (proof of work)
    String currentHash; //hash of the current block

    /**
     * Construtor da classe {@code Block}.
     *
     * @param previousHash O hash do bloco anterior.
     * @param data Os dados específicos do bloco.
     * @param nonce O número de prova de trabalho (nonce).
     */
    public Block(String previousHash, String data, int nonce) {
        this.previousHash = previousHash;
        this.data = data;
        this.nonce = nonce;
        this.currentHash = calculateHash();
    }

    /**
     * Calcula o hash do bloco atual com base no nonce, previousHash e data.
     *
     * @return O valor hash do bloco atual.
     */
    public String calculateHash() {
        return Hash.getHash(nonce + previousHash + data);
    }

    /**
     * Representação de string do bloco.
     *
     * @return Uma string que representa o bloco.
     */
    public String toString() {
        return // (isValid() ? "OK\t" : "ERROR\t")+
                String.format("[ %8s", previousHash) + " <- "
                + String.format("%-10s", data) + String.format(" %7d ] = ", nonce)
                + String.format("%8s", currentHash);
    }

    /**
     * Verifica se o bloco é válido comparando o hash atual com o hash
     * calculado.
     *
     * @return true se o bloco é válido, false caso contrário.
     */
    public boolean isValid() {
        return currentHash.equals(calculateHash());
    }
}
