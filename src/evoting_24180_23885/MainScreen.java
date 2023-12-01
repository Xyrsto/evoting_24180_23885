/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package evoting_24180_23885;
import evoting_24180_23885.Voto;
import evoting_24180_23885.AddCandidato;
import evoting_24180_23885.SecurityUtils.Assimetric;
import evoting_24180_23885.SecurityUtils.Simetric;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.PublicKey;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 * Ecrã principal da aplicação
 */
public class MainScreen extends javax.swing.JFrame {
    public boolean isAuthenticated;
    public Eleitor loggedUser;
    public boolean userHasVoted;
    BlockChain BlockChain = new BlockChain(this);
    HashMap<String, String> allVotes = new HashMap();
    ArrayList<Object> votos = new ArrayList<>();
    ArrayList<Object> dataTree = new ArrayList<>();
    public ArrayList<Object> eleitoresList = new ArrayList<>();
    public ArrayList<Candidato> candidatos = new ArrayList<>();
    private final int DIFFICULTY = 2;

    //Modelo combo box
    DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();

    /**
     * Creates new form MainScreen
     */
    public MainScreen() {
        initComponents();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        jTextArea1.setEditable(false);
        jTextArea2.setEditable(false);
        BlockChain.jlabel = noncePreview;
        loadBlockchain();
        loadCandidatos();
        loadVotos();
    }

    /**
     * Carrega os votos do ficheiro na memória.
     */
    public void loadVotos() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("votes/allVotesCurState.votes"))) {
            allVotes = (HashMap<String, String>) ois.readObject();
            System.out.println(allVotes);
        } catch (Exception err) {
            System.out.println(err.toString());
        }
    }

    /**
     * Carrega a blockchain principal em memória.
     */
    public void loadBlockchain() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Blockchains/blockchain1696330634509.obj"))) {
            BlockChain = (BlockChain) ois.readObject();
        } catch (Exception err) {
            System.out.println(err.toString());
        }
    }

    /**
     * Este método carrega o estado atual (número de votos) para cada partido.
     * 
     * 
     */
    @SuppressWarnings("unchecked")
    public void loadCandidatos() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Partidos/currentState.partido"))) {
            candidatos = (ArrayList<Candidato>) ois.readObject();
            for (Candidato c : candidatos) {
                model.addElement(c.nome);
                System.out.println(c.getNome() + "->" + c.getNumeroVotos());
            }
            jComboBox1.setModel(model);
        } catch (Exception err) {
            System.out.println(err.toString());
        }
    }

    /**
     * Atualiza a lista de candidatos na interface.
     * @param newCand 
     */
    public void updateCandidatos(Candidato newCand) {
        // Adiciona o candidato à arraylist candidatos
        for (Candidato c : candidatos) {
            if (newCand.getNome().equals(c.getNome())) {
                System.out.println("JÁ EXISTE");
                return;
            }
        }
        this.candidatos.add(newCand);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Partidos/currentState.partido"))) {
            oos.writeObject(candidatos);
        } catch (Exception err) {
            System.out.println(err.toString());
        }

        // Adiciona o nome desse candidato ao modelo da combobox
        model.addElement(newCand.nome);

        // Aplica o modelo à combo box
        jComboBox1.setModel(model);
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jTextField3 = new javax.swing.JTextField();
        label1 = new java.awt.Label();
        jComboBox1 = new javax.swing.JComboBox<>();
        noncePreview = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setText("Partido");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jButton1.setText("Votar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane2.setViewportView(jTextArea2);

        jButton2.setText("Guardar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Carregar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        label1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        label1.setText("File name");

        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        noncePreview.setText("<placeholder>");

        jMenu1.setText("Registar");
        jMenu1.addMenuListener(new javax.swing.event.MenuListener() {
            public void menuCanceled(javax.swing.event.MenuEvent evt) {
            }
            public void menuDeselected(javax.swing.event.MenuEvent evt) {
            }
            public void menuSelected(javax.swing.event.MenuEvent evt) {
                jMenu1MenuSelected(evt);
            }
        });
        jMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu1ActionPerformed(evt);
            }
        });
        jMenuBar1.add(jMenu1);

        jMenu3.setText("Login");
        jMenu3.addMenuListener(new javax.swing.event.MenuListener() {
            public void menuCanceled(javax.swing.event.MenuEvent evt) {
            }
            public void menuDeselected(javax.swing.event.MenuEvent evt) {
            }
            public void menuSelected(javax.swing.event.MenuEvent evt) {
                jMenu3MenuSelected(evt);
            }
        });
        jMenuBar1.add(jMenu3);

        jMenu2.setText("Adicionar Candidato");
        jMenu2.addMenuListener(new javax.swing.event.MenuListener() {
            public void menuCanceled(javax.swing.event.MenuEvent evt) {
            }
            public void menuDeselected(javax.swing.event.MenuEvent evt) {
            }
            public void menuSelected(javax.swing.event.MenuEvent evt) {
                jMenu2MenuSelected(evt);
            }
        });
        jMenu2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu2ActionPerformed(evt);
            }
        });
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField3)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                    .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2)
                    .addComponent(noncePreview, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(noncePreview)
                        .addGap(43, 43, 43)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Verifica se um utilizador está autenticado ou não, dando valor true à variável isAuthenticated caso esteja.
     *
     * @return 
     */
    public boolean setAuth() {
        return isAuthenticated = true;
    }

    /**
     * Recebe o utilizador autenticado. Utilizado na LoginWindow.
     * @param numCC
     * @param password
     * @throws Exception 
     */
    public void getLoggedUser(String numCC, String password) throws Exception {
        loggedUser = new Eleitor(numCC, password, false);
        loggedUser.loadVote(numCC);
        if (loggedUser.getHasVoted(allVotes, numCC)) {
            userHasVoted = true;
        }
        System.out.println(userHasVoted);
    }

    /**
     * Encripta o utilizador que votou.
     * @param user
     * @return
     * @throws Exception 
     */
    public byte[] encryptUser(String user) throws Exception {
        String hashedUser = Hash.getHash(user);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(hashedUser);
        oos.close(); // Close the ObjectOutputStream
        byte[] serializedUser = baos.toByteArray(); // Get the serialized data as a byte array
        PublicKey pubKey = Assimetric.getPublicKey("keys/USER" + user + ".pubkey");
        return Simetric.encrypt(serializedUser, pubKey);
    }

    /**
     * Adiciona um voto encriptado.
     * @param vote
     * @throws Exception 
     */
    public void addEncryptedVote(Voto vote) throws Exception {
        PublicKey pubKey = Assimetric.getPublicKey("keys/USERadmin.pubkey");
        votos.add(vote);
        allVotes.put(vote.getEleitor(), vote.getPartido());

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("votes/allVotesCurState.votes"))) {
            oos.writeObject(allVotes);
        } catch (Exception err) {
            System.out.println(err.toString());
        }
    }

    /**
     * <p>Método do botão votar.</p>
     * <p>Ao clicar no botão, é garantido que cada utilizador só pode votar uma única vez.
     * É possível votar livremente até a quantidade de votos chegar a 10. 
     * Assim que isso acontecer, é criado um bloco na blockchain com a root de uma merkle tree.</p>
     * @param evt 
     */
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        if (!isAuthenticated) {
            System.out.println("ERRO. NAO AUTENTICADO");
            return;
        }

        //controlo para garantir que cada utilizador apenas vota uma vez
        if (userHasVoted == true) {
            System.out.println("Já votou");
            return;
        }
        System.out.println("AUTENTICADO");
        loggedUser.setHasVoted(true);
        loggedUser.saveVote(loggedUser.getNumCC());

        Candidato partido = new Candidato("Null");

        //adds the strings to an array called "elements"
        for (int i = 0; i < candidatos.size(); i++) {
            if (candidatos.get(i).nome == jComboBox1.getSelectedItem().toString()) {
                partido = candidatos.get(i);
                partido.addVoto();
                System.out.println(partido.getNumeroVotos());
                try (FileOutputStream fos = new FileOutputStream("Partidos/currentState.partido"); ObjectOutputStream oos = new ObjectOutputStream(fos);) {
                    oos.writeObject(candidatos);
                } catch (Exception e) {
                    System.out.println("erro");
                    throw new RuntimeException(e);
                }
            }
        }

        if (votos.size() <= 10) {
            try {
                //byte[] encryptedUser = encryptUser(loggedUser.getNumCC());
                Voto vote = new Voto(Hash.getHash(loggedUser.getNumCC()), partido.getNome());
                System.out.println(vote.toString());

                addEncryptedVote(vote);

            } catch (Exception err) {
                System.out.println(err.toString());
            }

            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("votes/votesCurState.votes"))) {
                oos.writeObject(votos);
            } catch (Exception err) {
                System.out.println(err.toString());
            }

            if (votos.size() < 10) {
                return;
            }

            MerkleTreeString merkleTree = new MerkleTreeString(votos);
            dataTree.add(votos);
            dataTree.add(merkleTree);

            Timestamp timestamp = new Timestamp(System.currentTimeMillis());

            try (FileOutputStream fos = new FileOutputStream("DataTrees/dataTree" + merkleTree.getRoot() + ".obj"); ObjectOutputStream oos = new ObjectOutputStream(fos);) {
                oos.writeObject(dataTree);
            } catch (Exception e) {
                System.out.println("erro");
                throw new RuntimeException(e);
            }
            dataTree.clear();
            jTextArea2.setText(merkleTree.toString());
            BlockChain.add(merkleTree.getRoot(), DIFFICULTY);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     *
     */
    public void updateText() {
        jTextArea1.setText(BlockChain.toString());
        votos.clear();
    }

    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu1ActionPerformed

    private void jMenu2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu2ActionPerformed

    //Ao clicar em Ver, mostra uma nova janela que permite ver os votos que já foram feitos
    private void jMenu2MenuSelected(javax.swing.event.MenuEvent evt) {//GEN-FIRST:event_jMenu2MenuSelected
        // TODO add your handling code here:
        AddCandidato ver = new AddCandidato();
        ver.setVisible(true);
        ver.setAlwaysOnTop(true);
        ver.mainWindow = this;
    }//GEN-LAST:event_jMenu2MenuSelected

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        try (FileOutputStream fos = new FileOutputStream("Blockchains/blockchain" + timestamp.getTime() + ".obj"); ObjectOutputStream oos = new ObjectOutputStream(fos);) {
            oos.writeObject(dataTree);

        } catch (Exception e) {
            System.out.println("erro");
            throw new RuntimeException(e);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        JFileChooser fc = new JFileChooser(new File(jTextField3.getText()));
        if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                BlockChain.load(fc.getSelectedFile().getAbsolutePath());
                jTextField3.setText(fc.getSelectedFile().getAbsolutePath());
                jTextArea1.setText(BlockChain.toString());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jMenu1MenuSelected(javax.swing.event.MenuEvent evt) {//GEN-FIRST:event_jMenu1MenuSelected
        // TODO add your handling code here:
        RegistarWindow ver = new RegistarWindow();
        ver.setVisible(true);
        ver.setAlwaysOnTop(true);
        ver.mainWindow = this;
    }//GEN-LAST:event_jMenu1MenuSelected

    private void jMenu3MenuSelected(javax.swing.event.MenuEvent evt) {//GEN-FIRST:event_jMenu3MenuSelected
        // TODO add your handling code here:
        LoginWindow ver = new LoginWindow();
        ver.setVisible(true);
        ver.setAlwaysOnTop(true);
        ver.mainWindow = this;
    }//GEN-LAST:event_jMenu3MenuSelected

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
            java.util.logging.Logger.getLogger(MainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainScreen().setVisible(true);
            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    public javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextField jTextField3;
    private java.awt.Label label1;
    private javax.swing.JLabel noncePreview;
    // End of variables declaration//GEN-END:variables
}
