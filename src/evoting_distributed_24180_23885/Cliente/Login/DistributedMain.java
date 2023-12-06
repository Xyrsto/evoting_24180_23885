package evoting_distributed_24180_23885.Cliente.Login;


import evoting_distributed_24180_23885.Cliente.Login.BlockchainUtils.ServerMiner;
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
    private static final String host = "//169.254.76.66:10010";
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                java.awt.EventQueue.invokeLater(() -> {
                    new LoginServer().setVisible(true);
                });
                java.awt.EventQueue.invokeLater(() -> {
                    new ServerMiner(10_010, 400, 500).setVisible(true);
                });
                java.awt.EventQueue.invokeLater(() -> {
                    new ServerMiner(10_012, 800, 100).setVisible(true);
                });
                java.awt.EventQueue.invokeLater(() -> {
                    new ServerMiner(10_013, 1200, 150).setVisible(true);
                });
                java.awt.EventQueue.invokeLater(() -> {
                    new LoginScreen(new MainScreen(host+"/RemoteMiner")).setVisible(true);
                });
            }
        });
    }
}
