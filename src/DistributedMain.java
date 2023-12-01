
import evoting_distributed_24180_23885.Cliente.Login.LoginScreen;
import evoting_distributed_24180_23885.Cliente.Login.LoginServer;
import evoting_distributed_24180_23885.Cliente.MainScreen.MainScreen;

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
            LoginServer loginServer = new LoginServer();
            loginScreen.setVisible(true);
            loginServer.setVisible(true);
        }
    });
}
}
