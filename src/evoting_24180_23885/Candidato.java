/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package evoting_24180_23885;

import java.io.Serializable;

/**
 *
 * @author nuttell
 */
public class Candidato implements Serializable{
    public String nome;
    public int numeroVotos;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getNumeroVotos() {
        return numeroVotos;
    }

    public void setNumeroVotos(int numeroVotos) {
        this.numeroVotos = numeroVotos;
    }
    
    public void addVoto(){
        this.numeroVotos++;
    }
    
    public Candidato(String nome){
        this.nome = nome;
        this.numeroVotos = 0;
    }
    
    
}
