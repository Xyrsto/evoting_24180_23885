//::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: 
//::                                                                         ::
//::     Antonio Manuel Rodrigues Manso                                      ::
//::                                                                         ::
//::     I N S T I T U T O    P O L I T E C N I C O   D E   T O M A R        ::
//::     Escola Superior de Tecnologia de Tomar                              ::
//::     e-mail: manso@ipt.pt                                                ::
//::     url   : http://orion.ipt.pt/~manso                                  ::
//::                                                                         ::
//::     This software was build with the purpose of investigate and         ::
//::     learning.                                                           ::
//::                                                                         ::
//::                                                               (c)2023   ::
//:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
//////////////////////////////////////////////////////////////////////////////
package miner01_gui;

import java.rmi.RemoteException;
import java.rmi.server.RemoteServer;
import java.rmi.server.ServerNotActiveException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author manso
 */
public class RemoteMinerObject extends UnicastRemoteObject implements RemoteMinerInterface {

    MiningListener listener;

    /**
     * creates a object listening the port
     *
     * @param port port to listen
     * @param listener listener do objeto
     * @throws RemoteException
     */
    public RemoteMinerObject(int port, MiningListener listener) throws RemoteException {
        super(port);
        this.listener = listener;
        listener.onStartServer(utils.RMI.getRemoteName(port, RemoteMinerInterface.OBJECT_NAME));
    }

    @Override
    public int mine(String msg, int dificulty) throws RemoteException {
         //minar o bloco      
        return MinerGUI.getNonce(msg, dificulty, listener);
    }

    @Override
    public String getHash(int nonce, String msg) throws RemoteException {
          //calcular hash      
        return MinerGUI.getHash(nonce + msg);
    }

    public String getClient() {
        //informação do cliente
        try {
            return RemoteServer.getClientHost();
        } catch (ServerNotActiveException ex) {
        }
        return "Anonimous";
    }
}
