/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package evoting_distributed_24180_23885.Cliente.Login;

import evoting_24180_23885.SecurityUtils.*;
import java.io.FileOutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.rmi.RemoteException;
import java.rmi.server.RemoteServer;
import java.rmi.server.ServerNotActiveException;
import java.rmi.server.UnicastRemoteObject;
import java.security.Key;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A classe RemoteLoginObject representa um objeto remoto responsável pela
 * autenticação de clientes em um sistema distribuído de votação eletrônica.
 * Estende a classe UnicastRemoteObject para fornecer funcionalidades remotas.
 * Implementa a interface RemoteInterface que define os métodos remotos
 * disponíveis.
 *
 */
public class RemoteLoginObject extends UnicastRemoteObject implements RemoteLoginInterface {

    String host; // nome do servidor

    /**
     * Construtor da classe RemoteLoginObject.
     *
     * @param port O número da porta para exportar o objeto remoto.
     * @throws RemoteException Exceção lançada em caso de erro remoto.
     */
    public RemoteLoginObject(int port) throws RemoteException {
        super(port);
        try {
            //atualizar o nome do servidor
            host = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            host = "unknow";
        }
    }

    /**
     * Método remoto para autenticar um cliente no sistema.
     *
     * @param numCC O número do Cartão de Cidadão do cliente.
     * @param password A senha do cliente.
     * @return O número do Cartão de Cidadão se a autenticação for bem-sucedida;
     * caso contrário, retorna null.
     * @throws RemoteException Exceção lançada em caso de erro remoto.
     * @throws Exception Exceção genérica que pode ser lançada durante o
     * processo de autenticação.
     */
    @Override
    public String loginUser(String numCC, String password) throws RemoteException, Exception {
        String client = "";
        try {
            //nome do cliente
            client = RemoteServer.getClientHost();
            System.out.println("Message to " + client);

            //chave public
            PublicKey publicKey = Assimetric.getPublicKey("keys/USER" + numCC + ".pubkey");

            //chave privada
            byte[] encryptedPrivateKey = Files.readAllBytes(Paths.get("keys/USER" + numCC + ".privkey"));
            PrivateKey privateKey = Assimetric.getPrivateKey(PasswordBasedEncryption.decrypt(encryptedPrivateKey, password));

            //chave simétrica
            byte[] encryptedSimetricKey = Files.readAllBytes(Paths.get("keys/USER" + numCC + ".sim"));
            Key simetricKey = Simetric.getAESKey(Simetric.decrypt(encryptedSimetricKey, privateKey));

            //assinatura
            byte[] assinatura = Files.readAllBytes(Paths.get("assinaturas/USER" + numCC + ".sig"));

            //dados do registo
            byte[] data = (numCC + password).getBytes();

            //verificar assinatura
            boolean validSignature = AssinaturaDigital.verify(data, assinatura, publicKey);

            if (validSignature) {
                System.out.println("SUCESSO");
                return numCC;
            } else {
                System.out.println("FALHA NA AUTENTICAÇÂO");
                return null;
            }
        } catch (ServerNotActiveException ex) {
            Logger.getLogger(RemoteLoginObject.class.getName()).log(Level.SEVERE, null, ex);
        }
        //retornar uma mensagem
        return "";
    }

    
    /**
     * Método remoto para registar um cliente no sistema.
     *
     * @param numCC O número do Cartão de Cidadão do cliente.
     * @param password A senha do cliente.
     * @throws RemoteException Exceção lançada em caso de erro remoto.
     * @throws Exception Exceção genérica que pode ser lançada durante o
     * processo de autenticação.
     */
    @Override
    public void registerUser(String numCC, String password) throws RemoteException, Exception {
        String regex = "\\d{8}";
        if (!numCC.matches(regex)) {
            return;
        }
        //gerar chaves
        KeyPair keyPair = Assimetric.generateKeyPair(2048);

        //guardar a chave pública
        Assimetric.saveKey(keyPair.getPublic(), "keys/USER" + numCC + ".pubkey");

        //salvar chave privada com password 
        byte[] encryptedPrivate = PasswordBasedEncryption.encrypt(keyPair.getPrivate().getEncoded(), password);
        try (FileOutputStream fos = new FileOutputStream("keys/USER" + numCC + ".privkey")) {
            fos.write(encryptedPrivate);
        }

        //assinar registo
        byte[] registo = (numCC + password).getBytes();
        byte[] assinatura = AssinaturaDigital.signature(registo, keyPair.getPrivate());

        //guardar assinatura
        try (FileOutputStream fos = new FileOutputStream("assinaturas/USER" + numCC + ".sig")) {
            fos.write(assinatura);
        }

        //gerar e guardar chave simétrica
        Key simetric = Simetric.generateAESKey(128);
        byte[] encryptedSimetric = Simetric.encrypt(simetric.getEncoded(), keyPair.getPublic());
        try (FileOutputStream fos = new FileOutputStream("keys/USER" + numCC + ".sim")) {
            fos.write(encryptedSimetric);
        }
    }
}
