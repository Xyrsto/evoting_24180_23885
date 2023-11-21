/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package evoting_24180_23885;

import java.io.Serializable;

/**
 * A classe {@code Candidato} representa um candidato em um sistema de votação
 * eletrônica. Cada candidato possui um nome e um contador de votos. A classe
 * inclui métodos para obter e definir o nome do candidato, obter e definir o
 * número de votos recebidos e adicionar um voto.
 */
public class Candidato implements Serializable {

    public String nome;
    public int numeroVotos;

    /**
     * Construtor da classe {@code Candidato}.
     *
     * @param nome O nome do candidato.
     */
    public Candidato(String nome) {
        this.nome = nome;
        this.numeroVotos = 0;
    }

    /**
     * Obtém o nome do candidato.
     *
     * @return O nome do candidato.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome do candidato.
     *
     * @param nome O novo nome do candidato.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Obtém o número de votos do candidato.
     *
     * @return O número de votos do candidato.
     */
    public int getNumeroVotos() {
        return numeroVotos;
    }

    /**
     * Define o número de votos do candidato.
     *
     * @param numeroVotos O novo número de votos do candidato.
     */
    public void setNumeroVotos(int numeroVotos) {
        this.numeroVotos = numeroVotos;
    }

    /**
     * Adiciona um voto ao candidato, incrementando o contador de votos.
     */
    public void addVoto() {
        this.numeroVotos++;
    }
}
