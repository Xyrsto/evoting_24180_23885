package evoting_distributed_24180_23885.Cliente.Login;

import evoting_distributed_24180_23885.Cliente.Login.BlockchainUtils.ServerMiner;
import evoting_distributed_24180_23885.Cliente.Login.LoginScreen;
import evoting_distributed_24180_23885.Cliente.Login.LoginServer;
import evoting_distributed_24180_23885.Cliente.Login.MainScreen;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 * ALTERAR:
 *
 * @neste ficheiro: host para o nosso ip;
 * @LoginScreen: host para o nosso ip ;
 * @MainScreen: address para o nosso ip;
 * @ServerMiner: na GUI network meter o nosso ip;
 */
public class DistributedMain {

    private static final String host = "//192.168.1.236:10011";

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                java.awt.EventQueue.invokeLater(() -> {
                    new LoginServer().setVisible(true);
                });
                java.awt.EventQueue.invokeLater(() -> {
                    try {
                        new ServerMiner(10_010, 400, 500).setVisible(true);
                    } catch (IOException ex) {
                        Logger.getLogger(DistributedMain.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
                java.awt.EventQueue.invokeLater(() -> {
                    try {
                        new ServerMiner(10_012, 800, 100).setVisible(true);
                    } catch (IOException ex) {
                        Logger.getLogger(DistributedMain.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
                java.awt.EventQueue.invokeLater(() -> {
                    try {
                        new ServerMiner(10_013, 1200, 150).setVisible(true);
                    } catch (IOException ex) {
                        Logger.getLogger(DistributedMain.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
                java.awt.EventQueue.invokeLater(() -> {
                    new LoginScreen(new MainScreen(host + "/RemoteMiner")).setVisible(true);
                });
                java.awt.EventQueue.invokeLater(() -> {
                    new LoginScreen(new MainScreen(host + "/RemoteMiner")).setVisible(true);
                });
            }
        });
    }
}
