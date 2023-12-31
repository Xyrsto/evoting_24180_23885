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
//::                                                               (c)2021   ::
//:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
//////////////////////////////////////////////////////////////////////////////
package miner01_gui;

import java.awt.Color;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import utils.GuiUtils;

/**
 *
 * @author IPT
 */
public class RemoteMiner01 extends javax.swing.JFrame implements MiningListener {

    RemoteMinerObject myRemote;

    /**
     * Creates new form Test03_GUI_miner
     */
    public RemoteMiner01() {
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
        pnServerNetwork = new javax.swing.JPanel();
        pnServer = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btStartServer = new javax.swing.JButton();
        spMyServerPort = new javax.swing.JSpinner();
        runningIcon = new javax.swing.JLabel();
        pnMining = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        txtField = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtNonce = new javax.swing.JTextField();
        txtField1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtHash = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        spDificulty = new javax.swing.JSpinner();
        jLabel1 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        txtField2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtData = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtLog = new javax.swing.JTextPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Miner (c)2023");

        pnServerNetwork.setLayout(new java.awt.BorderLayout());

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

        pnServerNetwork.add(pnServer, java.awt.BorderLayout.NORTH);

        pnMining.setLayout(new java.awt.BorderLayout());

        jPanel8.setLayout(new java.awt.BorderLayout());

        jPanel5.setLayout(new java.awt.BorderLayout());

        txtField.setLayout(new java.awt.BorderLayout(0, 5));

        jLabel2.setText("Nonce");
        txtField.add(jLabel2, java.awt.BorderLayout.NORTH);

        txtNonce.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        txtNonce.setText("00000000");
        txtNonce.setEnabled(false);
        txtNonce.setPreferredSize(new java.awt.Dimension(150, 32));
        txtField.add(txtNonce, java.awt.BorderLayout.CENTER);

        jPanel5.add(txtField, java.awt.BorderLayout.WEST);

        txtField1.setLayout(new java.awt.BorderLayout(0, 5));

        jLabel3.setText("Hash");
        txtField1.add(jLabel3, java.awt.BorderLayout.NORTH);

        txtHash.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        txtHash.setEnabled(false);
        txtField1.add(txtHash, java.awt.BorderLayout.CENTER);

        jPanel5.add(txtField1, java.awt.BorderLayout.CENTER);

        jPanel8.add(jPanel5, java.awt.BorderLayout.CENTER);

        jPanel3.setLayout(new java.awt.BorderLayout());

        spDificulty.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        spDificulty.setModel(new javax.swing.SpinnerNumberModel(3, null, null, 1));
        spDificulty.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        spDificulty.setEnabled(false);
        jPanel3.add(spDificulty, java.awt.BorderLayout.CENTER);

        jLabel1.setText("Dificulty");
        jPanel3.add(jLabel1, java.awt.BorderLayout.PAGE_START);

        jPanel8.add(jPanel3, java.awt.BorderLayout.EAST);

        pnMining.add(jPanel8, java.awt.BorderLayout.NORTH);

        jPanel6.setLayout(new java.awt.BorderLayout());

        txtField2.setLayout(new java.awt.BorderLayout(0, 5));

        jLabel4.setText("Message");
        txtField2.add(jLabel4, java.awt.BorderLayout.NORTH);

        txtData.setEditable(false);
        txtData.setColumns(20);
        txtData.setFont(new java.awt.Font("Courier New", 1, 12)); // NOI18N
        txtData.setRows(5);
        jScrollPane1.setViewportView(txtData);

        txtField2.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel6.add(txtField2, java.awt.BorderLayout.CENTER);

        pnMining.add(jPanel6, java.awt.BorderLayout.CENTER);

        pnServerNetwork.add(pnMining, java.awt.BorderLayout.CENTER);

        tpMain.addTab("Remote Miner", pnServerNetwork);

        getContentPane().add(tpMain, java.awt.BorderLayout.CENTER);

        jScrollPane2.setPreferredSize(new java.awt.Dimension(64, 300));

        txtLog.setBackground(new java.awt.Color(0, 0, 0));
        txtLog.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtLog.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        jScrollPane2.setViewportView(txtLog);

        getContentPane().add(jScrollPane2, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btStartServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btStartServerActionPerformed
       

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
            java.util.logging.Logger.getLogger(RemoteMiner01.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RemoteMiner01.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RemoteMiner01.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RemoteMiner01.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

 
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RemoteMiner01().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btStartServer;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel pnMining;
    private javax.swing.JPanel pnServer;
    private javax.swing.JPanel pnServerNetwork;
    private javax.swing.JLabel runningIcon;
    private javax.swing.JSpinner spDificulty;
    private javax.swing.JSpinner spMyServerPort;
    private javax.swing.JTabbedPane tpMain;
    private javax.swing.JTextArea txtData;
    private javax.swing.JPanel txtField;
    private javax.swing.JPanel txtField1;
    private javax.swing.JPanel txtField2;
    private javax.swing.JTextField txtHash;
    private javax.swing.JTextPane txtLog;
    private javax.swing.JTextField txtNonce;
    // End of variables declaration//GEN-END:variables

    @Override
    public void onException(String title, Exception ex) {
        GuiUtils.addText(txtLog, title, ex.getMessage(), Color.RED, Color.MAGENTA);
        JOptionPane.showMessageDialog(this, ex.getMessage(),
                title, JOptionPane.ERROR_MESSAGE);
        Logger.getAnonymousLogger().log(Level.SEVERE, null, ex);
    }

    @Override
    public void onMessage(String title, String msg) {
        GuiUtils.addText(txtLog, title, msg, Color.GREEN, Color.WHITE);
    }
    
    @Override
    public void onStartServer(String adress){
        btStartServer.setEnabled(false);
        spMyServerPort.setEnabled(false);
        runningIcon.setEnabled(false);
        onMessage("Server ready", adress);
    }

    @Override
    public void onStartMining(String message, int zeros) {
        runningIcon.setVisible(true);
        runningIcon.setEnabled(true);
        txtData.setText(message);
        spDificulty.setValue(zeros);
        onMessage("Start Mining ", "[" + zeros + "] " + message);
    }

    @Override
    public void onStopMining(int nonce) {        
        runningIcon.setVisible(false);        
        onMessage("Stop Mining ", " " + nonce + "");
    }

    @Override
    public void onMining(int number) {
        txtNonce.setText(number + "");
    }

    @Override
    public void onNounceFound(int nonce) {
        try {  
            onStopMining(nonce);
            txtNonce.setText(nonce + "");
            txtHash.setText(myRemote.getHash(nonce, txtData.getText()));
            onMessage("NOUNCE FOUND", nonce+"");
        } catch (RemoteException ex) {
            Logger.getLogger(RemoteMiner01.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
