/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package evoting_24180_23885;

import evoting_24180_23885.SecurityUtils.Assimetric;
import evoting_24180_23885.SecurityUtils.Simetric;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.security.PublicKey;
import java.util.HashMap;

/**
 * A classe {@code Eleitor} representa um eleitor elegível em um sistema de voto eletrónico.
 * Armazena informações como o número de identificação nacional do eleitor (numCC),
 * a palavra-passe e o estado de voto.
 */
public class Eleitor implements Serializable{
    private String numCC;
    private String password;
    private static boolean hasVoted;
    
    /**
     * Constrói uma nova instância de {@code Eleitor} com o número de identificação nacional especificado,
     * palavra-passe e estado de voto inicial.
     *
     * @param numCC      o número de identificação nacional do eleitor
     * @param password   a palavra-passe associada ao eleitor
     * @param hasVoted   o estado inicial de voto do eleitor
     */
    public Eleitor(String numCC, String password, boolean hasVoted){
        this.numCC = numCC;
        this.password = password;
        this.hasVoted = hasVoted;
    }
 
    /**
     * Obtém o número de identificação nacional do eleitor.
     *
     * @return o número de identificação nacional
     */
    public String getNumCC() {
        return numCC;
    }

    /**
     * Define o número de identificação nacional do eleitor.
     *
     * @param numCC o número de identificação nacional a definir
     */
    public void setNumCC(String numCC) {
        this.numCC = numCC;
    }

    /**
     * Obtém a palavra-passe associada ao eleitor.
     *
     * @return a palavra-passe
     */
    public String getPassword() {
        return password;
    }

    /**
     * Define a palavra-passe associada ao eleitor.
     *
     * @param password a palavra-passe a definir
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    /**
     * Guarda o estado de voto do eleitor escrevendo-o para um ficheiro serializado.
     *
     * @param numCC o número de identificação nacional do eleitor
     */
    public static void saveVote(String numCC){
        //hasVoted = true;
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("votes/" + numCC + "_state.vote"))){
            oos.writeBoolean(hasVoted);
        }
        catch(Exception err){
            System.out.println(err.toString());
        }
    }
    
    /**
     * Carrega o estado de voto do eleitor lendo-o de um ficheiro serializado.
     *
     * @param numCC o número de identificação nacional do eleitor
     */
    public static void loadVote(String numCC){
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("votes/" + numCC + "_state.vote"))){
            hasVoted = ois.readBoolean();
        }
        catch(Exception err){
            System.out.println(err.toString());
        }
    }

    /**
     * Define o estado de voto do eleitor.
     *
     * @param hasVoted o estado de voto a definir
     */
    public void setHasVoted(boolean hasVoted) {
        this.hasVoted = hasVoted;
    }
    
    /**
     * Verifica se o eleitor já votou com base no HashMap fornecido e no número de identificação nacional.
     *
     * @param hashMap o HashMap contendo informações de voto
     * @param numCC   o número de identificação nacional do eleitor
     * @return {@code true} se o eleitor já votou, {@code false} caso contrário
     * @throws IOException  se ocorrer um erro de I/O
     * @throws Exception    se ocorrer um erro durante o cálculo do hash
     */
    public boolean getHasVoted(HashMap<String, String> hashMap, String numCC) throws IOException, Exception {    
        String hashedUser = Hash.getHash(numCC);
               
        if(hashMap.containsKey(hashedUser)){
            return true;
        }
        
        return false;
    }
}
