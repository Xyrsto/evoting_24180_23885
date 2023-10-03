/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package evoting_24180_23885;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

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
    
    
    //public 

    public static boolean getHasVoted() {
        return hasVoted;
    }
}
