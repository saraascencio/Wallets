/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package forms;

import blockchain.Block;
import blockchain.BlockChain;
import blockchain.Cifrado;
import blockchain.NodeData;
import blockchain.Transaction;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author carlos
 */
public class frmServer extends javax.swing.JFrame implements Runnable {

    /**
     * Creates new form frmServer
     */
    private Thread tListener;
    private NodeData currentNode;
    private ArrayList<NodeData> aOtherServers;
    private ArrayList<NodeData> aClients;
    private ArrayList<frmWallet> frmWallets;
    private ServerSocket SvrSocket;
    private BlockChain bc;
    private Cifrado oCifrado;

    public frmServer() {
        initComponents();
    }

    public frmServer(NodeData pnodeData) {
        initComponents();
        this.oCifrado = new Cifrado("ñVbFg-98+*DsHgñ");
        this.currentNode = pnodeData;
        this.startServer();

        this.jLabel6.setText(this.currentNode.getNodeName());
        this.jLabel7.setText("Network IP Adrress: " + this.currentNode.getIPAddress());
        this.jLabel5.setText("Port number: " + Integer.toString(this.currentNode.getSocketNum()));
        this.aOtherServers = new ArrayList<>();
        this.aClients = new ArrayList<>();
        this.frmWallets = new ArrayList<>();
    }

    public void registerNet(ArrayList<NodeData> pNodes) {
        String sCad = "";
        for (int i = 0; i < pNodes.size(); i++) {
            if (!pNodes.get(i).getNodeName().equals(this.currentNode.getNodeName())) {
                this.aOtherServers.add(pNodes.get(i));
                sCad += pNodes.get(i).getNodeName() + ", ";
            }
        }
        if (sCad.length() < 1) {
            sCad = "none";
        }
        this.jLabel4.setText(sCad);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtMessages = new javax.swing.JTextArea();
        jTextField1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Nodo de Servidor");
        setPreferredSize(new java.awt.Dimension(525, 547));

        jPanel1.setBackground(new java.awt.Color(255, 102, 0));

        jLabel1.setFont(new java.awt.Font("URW Gothic", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 204));
        jLabel1.setText("Server Node Location: ");

        jLabel5.setForeground(new java.awt.Color(254, 254, 254));
        jLabel5.setText("Port...");

        jLabel6.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("...");

        jLabel7.setForeground(new java.awt.Color(254, 254, 254));
        jLabel7.setText("IP...");

        jLabel3.setFont(new java.awt.Font("URW Gothic", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Available nodes:");

        jLabel4.setFont(new java.awt.Font("URW Gothic", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("none");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGap(83, 83, 83)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(89, 89, 89)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)))
                .addContainerGap(181, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel5))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 153, 51));

        jLabel10.setFont(new java.awt.Font("URW Gothic", 0, 15)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(254, 254, 254));
        jLabel10.setText("BlockChain Actions and Messages:");

        jButton1.setBackground(new java.awt.Color(0, 51, 204));
        jButton1.setFont(new java.awt.Font("URW Gothic", 1, 16)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Balances");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(0, 51, 204));
        jButton2.setFont(new java.awt.Font("URW Gothic", 1, 16)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Resumen");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel10))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton1))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jScrollPane1.setBackground(new java.awt.Color(245, 235, 236));
        jScrollPane1.setForeground(new java.awt.Color(71, 0, 0));

        txtMessages.setBackground(new java.awt.Color(243, 233, 233));
        txtMessages.setColumns(20);
        txtMessages.setForeground(new java.awt.Color(86, 13, 13));
        txtMessages.setRows(5);
        jScrollPane1.setViewportView(txtMessages);

        jTextField1.setText("mensajes");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField1)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
                .addGap(12, 12, 12)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String sCad = "";
        for (int i = 1; i < this.bc.size(); i++) {
            sCad += "Block " + this.bc.getBlock(i).getId() + ". "
                    + this.bc.transactionReport(this.bc.getBlock(i).getId())
                    + "-------------------\n";
        }
        this.txtMessages.setText(sCad);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.listBalances();
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(frmServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmServer().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextArea txtMessages;
    // End of variables declaration//GEN-END:variables

    private void startServer() {
        //create blockchain
        this.bc = new BlockChain(3, "0");
        this.bc.createGenesis();

        //start listener
        try {
            InetAddress iAddress = InetAddress.getByName(this.currentNode.getIPAddress());
            InetSocketAddress sNetServer = new InetSocketAddress(iAddress, this.currentNode.getSocketNum());
            SvrSocket = new ServerSocket();
            SvrSocket.bind(sNetServer);
            tListener = new Thread(this);
            tListener.start();
        } catch (Exception ee) {
        }
    }

    public void setBlockChainCopy(BlockChain copyBlockChain) {
        // improve this method with a real copy
        this.bc = copyBlockChain;
    }

    public BlockChain getBlockChainCopy() {
        // improve this method with a real copy
        return this.bc;
    }

    public int getBlockChainSize() {
        return this.bc.size();
    }

    public void startClientAccount(NodeData nodeClient, double initialBalance, String dMoneda) {
        this.bc.createBlock();
        this.bc.getLastBlock().setTransaction("0000WALLET000INITIAL000BALANCE",
                initialBalance, nodeClient.getNodeName(), "", "", dMoneda);
        this.bc.mineBlock();
    }

    public void registerClients(ArrayList<NodeData> aNClients) {
        this.aClients = aNClients;
        this.listBalances();
    }

    public boolean broadcastBlock(Block pBlk) {
        try {
            for (int i = 0; i < this.aOtherServers.size(); i++) {
                Socket socket
                        = new Socket(this.aOtherServers.get(i).getIPAddress(),
                                this.aOtherServers.get(i).getSocketNum());
                ObjectOutputStream oos
                        = new ObjectOutputStream(socket.getOutputStream());
                oos.writeObject(pBlk);
                socket.close();
            }
            return true;
        } catch (Exception e) {
        }
        return false;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Socket socket = this.SvrSocket.accept();

                // Deserialización
                InputStream is = socket.getInputStream();
                ObjectInputStream ois = new ObjectInputStream(is);
                Block blk = (Block) ois.readObject();
                socket.close();

                if (blk.getId() < 0) { 
                    String sSender = this.oCifrado.desencriptar(blk.getTransaction(0).getSender());
                    String sReceiver = this.oCifrado.desencriptar(blk.getTransaction(0).getReceiver());
                    double dAmount = blk.getTransaction(0).getAmount();
                    String sMotivo = blk.getTransaction(0).getMotivo();
                    String sMonedaRemitente = blk.getTransaction(0).getMonedaRemitente();
                    String sMonedaReceptor = blk.getTransaction(0).getMonedaReceptor();

                   /*Esos print se usaron para hacer 
                    pruebas solamente*/
                    double dMontoConvertido = dAmount;
                    System.out.println("Moneda Remitente: '" + sMonedaRemitente + "'");
                    System.out.println("Moneda Receptor: '" + sMonedaReceptor + "'");

                    if (sMonedaRemitente.equals("$") && sMonedaReceptor.equals("€")) {
                        double tasaConversionUSDToEUR = 0.85; 
                        dMontoConvertido = dAmount * tasaConversionUSDToEUR;  
                        System.out.println("Conversion de USD a EUR: " + dMontoConvertido);
                    } else if (sMonedaRemitente.equals("€") && sMonedaReceptor.equals("$")) {
                        double tasaConversionEURToUSD = 1.18; 
                        dMontoConvertido = dAmount * tasaConversionEURToUSD;  
                        System.out.println("Conversion de EUR a USD: " + dMontoConvertido);
                    } else if (sMonedaRemitente.equals("$") && sMonedaReceptor.equals("JPY")) {
                        double tasaConversion = 110.0;
                        dMontoConvertido = dAmount * tasaConversion;
                        System.out.println("Conversión de USD a JPY: " + dMontoConvertido);
                    } else if (sMonedaRemitente.equals("JPY") && sMonedaReceptor.equals("$")) {
                        double tasaConversion = 0.0091;
                        dMontoConvertido = dAmount * tasaConversion;
                        System.out.println("Conversión de JPY a USD: " + dMontoConvertido);
                    } else if (sMonedaRemitente.equals("$") && sMonedaReceptor.equals("MX$")) {
                        double tasaConversion = 20.0;
                        dMontoConvertido = dAmount * tasaConversion;
                        System.out.println("Conversión de USD a MXN: " + dMontoConvertido);
                    } else if (sMonedaRemitente.equals("MX$") && sMonedaReceptor.equals("$")) {
                        double tasaConversion = 0.05;
                        dMontoConvertido = dAmount * tasaConversion;
                        System.out.println("Conversión de MXN a USD: " + dMontoConvertido);
                    } else {
                        System.out.println("No se necesita conversion. Remitente: '" + sMonedaRemitente + "' Receptor: '" + sMonedaReceptor + "'");
                    }

                   
                    if (this.bc.getBalance(sSender) >= dMontoConvertido) {
                       
                        this.bc.createBlock();
                        this.bc.getLastBlock().setTransaction(
                                new Transaction(0, sSender, sReceiver, dMontoConvertido, sMotivo, sMonedaRemitente, sMonedaReceptor)
                        );
                        this.bc.mineBlock();

                        
                        if (this.broadcastBlock(this.bc.getLastBlock())) {
                            this.reportNewBalance(sReceiver, dMontoConvertido);
                            this.listBalances(); 
                        } else {
                            this.txtMessages.setText("Error broadcasting block!");
                        }
                    } else {
                        this.txtMessages.setText("Insufficient funds from: " + sSender);
                    }

                } else {
                    // Cuando se reciben bloques completos
                    this.bc.addProvedBlock(blk);
                }
                this.listBalances();
            } catch (Exception ee) {
                this.jTextField1.setText("Error: " + ee.toString());
            }
        }
    }

    public void listBalances() {
        String sCad = "";
        for (int i = 0; i < this.aClients.size(); i++) {
            sCad += this.aClients.get(i).getNodeName()
                    + "= $ "
                    + Double.toString(this.bc.getBalance(this.aClients.get(i).getNodeName()))
                    + "\n";
        }
        this.txtMessages.setText(sCad);
    }

    public void reportNewBalance(String receiver, double amount) {
        for (int i = 0; i < this.aClients.size(); i++) {
            if (this.aClients.get(i).getNodeName().equals(receiver)) {
                try {
                    Socket socket = new Socket(
                            this.aClients.get(i).getIPAddress(),
                            this.aClients.get(i).getSocketNum());
                    ObjectOutputStream oos
                            = new ObjectOutputStream(socket.getOutputStream());
                    oos.writeObject(amount);
                    socket.close();
                } catch (Exception e) {
                }
            }
        }
    }

}
