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
    private Eleitor eleitor;
    private Candidato partido;
    
    public Voto(Eleitor eleitor, Candidato partido)
    {
        this.eleitor = eleitor;
        this.partido = partido;
    }

    public String getEleitor() {
        return eleitor.getNumCC();
    }

    public void setEleitor(Eleitor eleitor) {
        this.eleitor = eleitor;
    }

    public String getPartido() {
        return partido.getNome();
    }

    public void setPartido(Candidato partido) {
        this.partido = partido;
    }
    
    public String toString(){
        return eleitor.getNumCC() + "->" + partido.getNome();
    }
}
