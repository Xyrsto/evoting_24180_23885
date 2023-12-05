package evoting_distributed_24180_23885.Cliente.Login;

import java.awt.Color;
import java.net.InetAddress;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

import utils.GuiUtils;
import utils.RMI;

/**
 * @author IPT
 */
public class LoginServer extends javax.swing.JFrame {

    RemoteLoginInterface myRemote;
    public static final String remoteName = "RemoteLogin";
    public static int remotePort;

    public LoginServer() {
        initComponents();
        setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tpMain = new javax.swing.JTabbedPane();
        pnServerMining = new javax.swing.JPanel();
        pnServer = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btStartServer = new javax.swing.JButton();
        spMyServerPort = new javax.swing.JSpinner();
        runningIcon = new javax.swing.JLabel();
        pnMining = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        txtField2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtData = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Miner (c)2023");

        pnServerMining.setLayout(new java.awt.BorderLayout());

        pnServer.setLayout(new java.awt.BorderLayout());

        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel2.setLayout(new java.awt.GridLayout(1, 0));

        btStartServer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Connect_to_Server.png"))); // NOI18N
        btStartServer.setText("Start Server");
        btStartServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btStartServerActionPerformed(evt);
            }
        });
        jPanel2.add(btStartServer);

        spMyServerPort.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        spMyServerPort.setModel(new javax.swing.SpinnerNumberModel(10010, 10010, null, 1));
        spMyServerPort.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.add(spMyServerPort);

        jPanel1.add(jPanel2, java.awt.BorderLayout.PAGE_START);

        pnServer.add(jPanel1, java.awt.BorderLayout.WEST);

        runningIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        runningIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/working.gif"))); // NOI18N
        pnServer.add(runningIcon, java.awt.BorderLayout.CENTER);

        pnServerMining.add(pnServer, java.awt.BorderLayout.NORTH);

        pnMining.setLayout(new java.awt.BorderLayout());

        jPanel8.setLayout(new java.awt.BorderLayout());

        jPanel3.setLayout(new java.awt.BorderLayout());
        jPanel8.add(jPanel3, java.awt.BorderLayout.EAST);

        pnMining.add(jPanel8, java.awt.BorderLayout.NORTH);

        jPanel6.setLayout(new java.awt.BorderLayout());

        txtField2.setLayout(new java.awt.BorderLayout(0, 5));

        jLabel4.setText("Message");
        txtField2.add(jLabel4, java.awt.BorderLayout.PAGE_START);

        txtData.setEditable(false);
        txtData.setColumns(20);
        txtData.setFont(new java.awt.Font("Courier New", 1, 12)); // NOI18N
        txtData.setRows(5);
        jScrollPane1.setViewportView(txtData);

        txtField2.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel6.add(txtField2, java.awt.BorderLayout.CENTER);

        pnMining.add(jPanel6, java.awt.BorderLayout.CENTER);

        pnServerMining.add(pnMining, java.awt.BorderLayout.CENTER);

        tpMain.addTab("Login Server", pnServerMining);

        getContentPane().add(tpMain, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btStartServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btStartServerActionPerformed
        //create object  to listen in the remote port

        try {
            remotePort = (int) spMyServerPort.getValue();
            RemoteLoginObject helloWorld = new RemoteLoginObject(remotePort);
            //local adress of server
            String host = InetAddress.getLocalHost().getHostAddress();
            //create registry to object
            LocateRegistry.createRegistry(remotePort);
            //create adress of remote object
            String address = String.format("//%s:%d/%s", host, remotePort, remoteName);
            //link adress to object
            Naming.rebind(address, helloWorld);
            System.out.printf("Ready on %s%n", address);
        } catch (Exception ex) {
            Logger.getLogger(LoginServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btStartServerActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LoginServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginServer().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btStartServer;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pnMining;
    private javax.swing.JPanel pnServer;
    private javax.swing.JPanel pnServerMining;
    private javax.swing.JLabel runningIcon;
    private javax.swing.JSpinner spMyServerPort;
    private javax.swing.JTabbedPane tpMain;
    private javax.swing.JTextArea txtData;
    private javax.swing.JPanel txtField2;
    // End of variables declaration//GEN-END:variables

}
