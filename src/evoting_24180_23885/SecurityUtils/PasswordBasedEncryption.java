/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package evoting_24180_23885.SecurityUtils;
import java.math.BigInteger;
import java.security.spec.KeySpec;
import java.util.Random;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * A classe PasswordBasedEncryption fornece métodos para realizar criptografia e descriptografia
 * de dados usando um esquema de criptografia baseado em senha.
 * Ela utiliza a cifra AES em modo CBC (Cipher Block Chaining) com preenchimento PKCS5Padding
 * e a função de derivação de chave PBKDF2 para fortalecer a segurança da senha.
 */
public class PasswordBasedEncryption {
    String data;

    /**
     * Construtor da classe PasswordBasedEncryption.
     * 
     * @param data Os dados a serem processados pela criptografia ou descriptografia.
     */
    public PasswordBasedEncryption(String data) {
        this.data = data;
    }

    /**
     * Obtém os dados atualmente armazenados na instância.
     * 
     * @return Os dados armazenados.
     */
    public String getData() {
        return data;
    }

    
    /**
     * Define os dados a serem processados pela criptografia ou descriptografia.
     * 
     * @param data Os dados a serem definidos.
     */
    public void setData(String data) {
        this.data = data;
    }
    
    /**
     * Cria e retorna uma instância da classe Cipher configurada para o modo especificado
     * (criptografia ou descriptografia) utilizando um esquema de criptografia baseado em senha.
     * 
     * @param mode O modo da cifra (Cipher.ENCRYPT_MODE para criptografia, Cipher.DECRYPT_MODE para descriptografia).
     * @param password A senha utilizada para derivar a chave.
     * @return Uma instância da classe Cipher configurada para o modo especificado.
     * @throws Exception Se ocorrer um erro durante a criação da instância Cipher.
     */
    public static Cipher createPBE(int mode, String password) throws Exception
    {
        //generate salt - random 
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256"); 
        byte[] salt = password.getBytes();
        
        Random rand = new Random(new BigInteger(salt).longValue());
        rand.nextBytes(salt);
        
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 256);
        SecretKey key = factory.generateSecret(spec);
        
        SecretKeySpec secretKey = new SecretKeySpec(key.getEncoded(), "AES");
        
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        
        byte[] iv = new byte[16];
        rand.nextBytes(iv);
        
        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        
        cipher.init(mode, secretKey, ivSpec);
        
        return cipher;
    }
    
    /**
     * Criptografa os dados utilizando uma senha e retorna o resultado.
     * 
     * @param data Os dados a serem criptografados.
     * @param password A senha utilizada para derivar a chave.
     * @return Os dados criptografados.
     * @throws Exception Se ocorrer um erro durante a operação de criptografia.
     */
    public static byte[] encrypt(byte[] data, String password) throws Exception{
        Cipher cipher = createPBE(Cipher.ENCRYPT_MODE, password);
        return cipher.doFinal(data);
    }
    
    /**
     * Descriptografa os dados criptografados utilizando uma senha e retorna o resultado.
     * 
     * @param data Os dados criptografados a serem descriptografados.
     * @param password A senha utilizada para derivar a chave.
     * @return Os dados descriptografados.
     * @throws Exception Se ocorrer um erro durante a operação de descriptografia.
     */
    public static byte[] decrypt(byte[] data, String password) throws Exception{
        Cipher cipher = createPBE(Cipher.DECRYPT_MODE, password);
        return cipher.doFinal(data);
    }    
}
