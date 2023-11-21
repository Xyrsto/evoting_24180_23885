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
 *
 * @author rodri
 */
public class Eleitor implements Serializable{
    private String numCC;
    private String password;
    private static boolean hasVoted;
    
    public Eleitor(String numCC, String password, boolean hasVoted){
        this.numCC = numCC;
        this.password = password;
        this.hasVoted = hasVoted;
    }

    public String getNumCC() {
        return numCC;
    }

    public void setNumCC(String numCC) {
        this.numCC = numCC;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /*public static boolean hasVoted() {
        return hasVoted;
    }*/
    
    public static void saveVote(String numCC){
        //hasVoted = true;
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("votes/" + numCC + "_state.vote"))){
            oos.writeBoolean(hasVoted);
        }
        catch(Exception err){
            System.out.println(err.toString());
        }
    }
    
    public static void loadVote(String numCC){
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("votes/" + numCC + "_state.vote"))){
            hasVoted = ois.readBoolean();
        }
        catch(Exception err){
            System.out.println(err.toString());
        }
    }

    public void setHasVoted(boolean hasVoted) {
        this.hasVoted = hasVoted;
    }
    
    
    public boolean getHasVoted(HashMap<String, String> hashMap) throws IOException, Exception {        
        String hashedUser = Hash.getHash(this.numCC);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(hashedUser);
        oos.close(); // Close the ObjectOutputStream
        byte[] serializedUser = baos.toByteArray(); // Get the serialized data as a byte array
        PublicKey pubKey = Assimetric.getPublicKey("keys/USER" + this.numCC + ".pubkey");
        byte[] encrypted = Simetric.encrypt(serializedUser, pubKey);
                
        if(hashMap.containsValue(encrypted)){
            return true;
        }
        
        return false;
    }
}
