/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package evoting_24180_23885;

import evoting_24180_23885.SecurityUtils.Assimetric;
import evoting_24180_23885.SecurityUtils.AssinaturaDigital;
import evoting_24180_23885.SecurityUtils.PasswordBasedEncryption;
import evoting_24180_23885.SecurityUtils.Simetric;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Key;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 *
 * @author rodri
 */
public class Users {
    public static void register(String numCC, String password) throws Exception{
        String regex = "\\d{8}";
        if(!numCC.matches(regex)){
            return;
        }
        //gerar chaves
        KeyPair keyPair = Assimetric.generateKeyPair(1024);
        
        //guardar a chave pública
        Assimetric.saveKey(keyPair.getPublic(), "keys/USER" + numCC + ".pubkey");
        
        //salvar chave privada com password 
        byte[] encryptedPrivate = PasswordBasedEncryption.encrypt(keyPair.getPrivate().getEncoded(), password);
        //isto não funciona -> Files.write("keys/USER" + numCC + ".privkey", encryptedPrivate);       
        try(FileOutputStream fos = new FileOutputStream("keys/USER" + numCC + ".privkey")){
            fos.write(encryptedPrivate);
        }
        
        //assinar registo
        byte[] registo = (numCC + password).getBytes();
        byte[] assinatura = AssinaturaDigital.signature(registo, keyPair.getPrivate());
        
        //guardar assinatura
        try(FileOutputStream fos = new FileOutputStream("assinaturas/USER" + numCC + ".sig")){
            fos.write(assinatura);
        }
        
        //gerar e guardar chave simétrica
        Key simetric = Simetric.generateAESKey(128);
        byte[] encryptedSimetric = Simetric.encrypt(simetric.getEncoded(), keyPair.getPublic());
        try(FileOutputStream fos = new FileOutputStream("keys/USER" + numCC + ".sim")){
            fos.write(encryptedSimetric);
        }      
    }
    
    public static boolean authenticate(String numCC, String password) throws Exception{  
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
        
        if(validSignature){
            System.out.println("SUCESSO");
            return true;
        }
        else{
            System.out.println("FALHA NA AUTENTICAÇÂO");
            return false;
        }
    }
}
