/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package evoting_24180_23885;

import java.io.Serializable;

/**
 *
 * @author rodri
 */
public class Voto implements Serializable
{
    private String eleitor;
    private String partido;
    
    public Voto(String eleitor, String partido)
    {
        this.eleitor = eleitor;
        this.partido = partido;
    }

    public String getEleitor() {
        return eleitor;
    }

    public void setEleitor(String eleitor) {
        this.eleitor = eleitor;
    }

    public String getPartido() {
        return partido;
    }

    public void setPartido(String partido) {
        this.partido = partido;
    }
    
    public String toString(){
        return eleitor + "->" + partido;
    }
}
