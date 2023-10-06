/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package evoting_24180_23885;

import java.io.Serializable;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 *
 * @author rodri
 */
public class EncryptedVote implements Serializable{
    private byte[] encryptedVote;
    private byte[] simetricKeyEncrypted;
    private PublicKey pubKey;

    public EncryptedVote(byte[] encryptedVote, byte[] simetricKeyEncrypted, PublicKey pubKey) {
        this.encryptedVote = encryptedVote;
        this.simetricKeyEncrypted = simetricKeyEncrypted;
        this.pubKey = pubKey;
    }   
}
