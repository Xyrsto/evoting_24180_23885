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
 *
 * @author rodri
 */
public class Voto implements Serializable {

    private byte[] eleitor;
    private String partido;

    public Voto(byte[] eleitor, String partido) {
        this.eleitor = eleitor;
        this.partido = partido;
    }

    public byte[] getEleitor() {
        return eleitor;
    }

    public void setEleitor(byte[] eleitor) {
        this.eleitor = eleitor;
    }

    public String getPartido() {
        return partido;
    }

    public void setPartido(String partido) {
        this.partido = partido;
    }

    public String toString() {
        return eleitor + "->" + partido;
    }

    public byte[] getBytes() throws IOException {
        return Utils.serialize(this);
    }

    public static Voto fromBytes(byte[] bytes) throws IOException, ClassNotFoundException {
        return (Voto) Utils.deserialize(bytes);
    }

    public byte[] getBytes(Key key) throws Exception {
        byte[] data = Utils.serialize(this);
        return Simetric.encrypt(data, key);
    }

    public static Voto fromBytes(byte[] bytes, Key key) throws Exception {
        return (Voto) Utils.deserialize(Simetric.decrypt(bytes, key));
    }

}
