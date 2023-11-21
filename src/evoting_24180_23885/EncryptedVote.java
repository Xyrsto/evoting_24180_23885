/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package evoting_24180_23885;

import java.io.Serializable;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * A classe {@code EncryptedVote} representa um voto criptografado em um sistema de votação eletrônica.
 * Cada instância desta classe contém dados sobre o voto criptografado, incluindo o voto em si, a chave simétrica
 * criptografada usada para criptografar o voto e a chave pública associada ao eleitor que emitiu o voto.
 */
public class EncryptedVote implements Serializable{
    private byte[] encryptedVote;
    private byte[] simetricKeyEncrypted;
    private PublicKey pubKey;

    /**
     * Construtor da classe {@code EncryptedVote}.
     *
     * @param encryptedVote          O voto criptografado.
     * @param symmetricKeyEncrypted  A chave simétrica criptografada usada para criptografar o voto.
     * @param publicKey              A chave pública associada ao eleitor que emitiu o voto.
     */
    public EncryptedVote(byte[] encryptedVote, byte[] simetricKeyEncrypted, PublicKey pubKey) {
        this.encryptedVote = encryptedVote;
        this.simetricKeyEncrypted = simetricKeyEncrypted;
        this.pubKey = pubKey;
    }   
}
