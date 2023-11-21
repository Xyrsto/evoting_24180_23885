/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package evoting_24180_23885;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * A classe Utils fornece métodos utilitários para serialização e
 * desserialização de objetos. Esses métodos ajudam na conversão de objetos em
 * arrays de bytes e vice-versa.
 */
public class Utils {

    /**
     * Serializa um objeto em um array de bytes.
     *
     * @param obj O objeto a ser serializado.
     * @return Retorna um array de bytes representando o objeto serializado.
     * @throws IOException Lança uma exceção se ocorrer um erro durante a
     * serialização.
     */
    public static byte[] serialize(Object obj) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(out);
        os.writeObject(obj);
        return out.toByteArray();
    }

    /**
     * Desserializa um array de bytes para recriar o objeto original.
     *
     * @param data O array de bytes a ser desserializado.
     * @return Retorna o objeto desserializado.
     * @throws IOException Lança uma exceção se ocorrer um erro durante a
     * desserialização.
     * @throws ClassNotFoundException Lança uma exceção se a classe do objeto
     * não for encontrada.
     */
    public static Object deserialize(byte[] data) throws IOException, ClassNotFoundException {
        ByteArrayInputStream in = new ByteArrayInputStream(data);
        ObjectInputStream is = new ObjectInputStream(in);
        return is.readObject();
    }
}
