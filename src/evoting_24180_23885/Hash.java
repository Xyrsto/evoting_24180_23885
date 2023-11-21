/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package evoting_24180_23885;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A classe {@code Hash} fornece métodos para calcular o hash de dados usando o
 * algoritmo SHA3-256. Esta classe utiliza uma instância de MessageDigest para
 * realizar a operação de hash.
 */
public class Hash {

    private static MessageDigest hasher = null;

    static {
        try {
            hasher = MessageDigest.getInstance("SHA3-256");
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Miner.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Converte um número inteiro para sua representação hexadecimal em letras
     * maiúsculas.
     *
     * @param n O número inteiro a ser convertido.
     * @return A representação hexadecimal em letras maiúsculas do número
     * inteiro.
     */
    public static String toHexString(int n) {
        return Integer.toHexString(n).toUpperCase();
    }

    /**
     * Calcula o hash de uma string utilizando o algoritmo SHA3-256.
     *
     * @param data A string a ser hash.
     * @return O valor hash em Base64 da string fornecida.
     */
    public static String getHash(String data) {
        hasher.reset();
        hasher.update(data.getBytes());
        return Base64.getEncoder().encodeToString(hasher.digest());
    }
}
