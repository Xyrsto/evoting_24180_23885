/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package evoting_distributed_24180_23885.Cliente.Login;

import java.rmi.RemoteException;
import java.rmi.Remote;

/**
 * A interface RemoteInterface define os métodos remotos disponíveis para autenticação de clientes
 * em um sistema distribuído de votação eletrônica.
 * Estende a interface Remote para indicar que os métodos podem ser chamados remotamente.
 * 
 */
public interface RemoteLoginInterface extends Remote {
    /**
     * Realiza a autenticação de um cliente no sistema.
     * 
     * @param numCC O número do Cartão de Cidadão do cliente.
     * @param password A senha do cliente.
     * @return O número do Cartão de Cidadão se a autenticação for bem-sucedida; caso contrário, retorna null.
     * @throws RemoteException Exceção lançada em caso de erro remoto.
     * @throws Exception Exceção genérica que pode ser lançada durante o processo de autenticação.
     */
    public String loginUser(String numCC, String password) throws RemoteException, Exception;
}
