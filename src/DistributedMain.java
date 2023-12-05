
import evoting_distributed_24180_23885.Cliente.Login.LoginScreen;
import evoting_distributed_24180_23885.Cliente.Login.LoginServer;
import evoting_distributed_24180_23885.Cliente.Login.MainScreen;
import miner01_gui.ClientMiner01;
import miner01_gui.RemoteMiner01;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author rodri
 */
public class DistributedMain {

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                MainScreen mainScreen = new MainScreen();
                LoginScreen loginScreen = new LoginScreen(mainScreen);
                loginScreen.setVisible(true);
                LoginServer loginServer = new LoginServer();
                //RemoteMiner01 remoteMiner = new RemoteMiner01();
                //ClientMiner01 clientMiner = new ClientMiner01();
                loginServer.setVisible(true);
                //remoteMiner.setVisible(true);
                //clientMiner.setVisible(true);
            }
        });
    }
}
