/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package evoting_24180_23885;

import evoting_24180_23885.SecurityUtils.Simetric;
import java.io.IOException;
import java.io.Serializable;
import java.security.Key;

/**
 * A classe Voto representa um voto efetuado por um eleitor para um partido
 * específico. Contém métodos para converter o voto em bytes e vice-versa,
 * permitindo a serialização e desserialização. Além disso, suporta criptografia
 * simétrica para proteger os dados do voto.
 */
public class Voto implements Serializable {

    private String eleitor;
    private String partido;

    /**
     * Construtor da classe Voto.
     *
     * @param eleitor O identificador do eleitor que emitiu o voto.
     * @param partido O partido para o qual o eleitor votou.
     */
    public Voto(String eleitor, String partido) {
        this.eleitor = eleitor;
        this.partido = partido;
    }

    /**
     * Obtém o identificador do eleitor associado ao voto.
     *
     * @return Retorna o identificador do eleitor.
     */
    public String getEleitor() {
        return eleitor;
    }

    /**
     * Define o identificador do eleitor associado ao voto.
     *
     * @param eleitor O novo identificador do eleitor.
     */
    public void setEleitor(String eleitor) {
        this.eleitor = eleitor;
    }

    /**
     * Obtém o partido para o qual o voto foi emitido.
     *
     * @return Retorna o partido associado ao voto.
     */
    public String getPartido() {
        return partido;
    }

    /**
     * Define o partido para o qual o voto será emitido.
     *
     * @param partido O novo partido associado ao voto.
     */
    public void setPartido(String partido) {
        this.partido = partido;
    }

    /**
     * Retorna uma representação em string do voto, indicando o eleitor e o
     * partido escolhido.
     *
     * @return Retorna uma string representando o voto.
     */
    public String toString() {
        return eleitor + "->" + partido;
    }

    /**
     * Obtém os bytes representando o voto para fins de serialização.
     *
     * @return Retorna um array de bytes representando o voto.
     * @throws IOException Lança uma exceção se ocorrer um erro durante a
     * serialização.
     */
    public byte[] getBytes() throws IOException {
        return Utils.serialize(this);
    }

    /**
     * Converte os bytes de volta para um objeto Voto durante a desserialização.
     *
     * @param bytes Os bytes a serem convertidos de volta para um objeto Voto.
     * @return Retorna o objeto Voto desserializado.
     * @throws IOException Lança uma exceção se ocorrer um erro durante a
     * desserialização.
     * @throws ClassNotFoundException Lança uma exceção se a classe do objeto
     * não for encontrada.
     */
    public static Voto fromBytes(byte[] bytes) throws IOException, ClassNotFoundException {
        return (Voto) Utils.deserialize(bytes);
    }

    /**
     * Obtém os bytes representando o voto, usando criptografia simétrica com a
     * chave fornecida.
     *
     * @param key A chave simétrica usada para criptografar os dados do voto.
     * @return Retorna um array de bytes representando o voto criptografado.
     * @throws Exception Lança uma exceção se ocorrer um erro durante a
     * criptografia.
     */
    public byte[] getBytes(Key key) throws Exception {
        byte[] data = Utils.serialize(this);
        return Simetric.encrypt(data, key);
    }

    /**
     * Converte os bytes criptografados de volta para um objeto Voto usando a
     * chave fornecida.
     *
     * @param bytes Os bytes criptografados a serem convertidos de volta para um
     * objeto Voto.
     * @param key A chave usada para descriptografar os dados do voto.
     * @return Retorna o objeto Voto descriptografado.
     * @throws Exception Lança uma exceção se ocorrer um erro durante a
     * descriptografia.
     */
    public static Voto fromBytes(byte[] bytes, Key key) throws Exception {
        return (Voto) Utils.deserialize(Simetric.decrypt(bytes, key));
    }
}
