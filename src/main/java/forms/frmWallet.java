/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package forms;

import blockchain.Block;
import blockchain.Cifrado;
import blockchain.NodeData;
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
public class frmWallet extends javax.swing.JFrame implements Runnable {

    /**
     * Creates new form frmWallet P2P... PEER TO PEER --- POINT TO POINT
     */
    private NodeData nodeData;
    private ArrayList<NodeData> aServers;
    private double dCurrentBalance;
    // private ArrayList<NodeData> aAlternativeServers;
    private ServerSocket clientSocket;
    private Thread tListener;

    private Cifrado oCifrado;

    public frmWallet() {
        initComponents();
        this.setSize(500, 600); // Por ejemplo, 800x600 píxeles
        this.setResizable(false); // Opcional, para evitar que el usuario redimensione la ventana
        this.setResizable(false);
        oCifrado = new Cifrado();
    }

    public void configure(NodeData nodeClient, double pBalance, String simboloMoneda) {
        this.nodeData = nodeClient;
        this.dCurrentBalance = pBalance;

        // Configurar la IP y el Socket
        this.jLabel7.setText("IP " + this.nodeData.getIPAddress() + ". Socket " + this.nodeData.getSocketNum());

        // Configurar el nombre del cliente
        this.lUser.setText(this.nodeData.getNodeName());

        // Mostrar el balance sin símbolo de moneda
        this.lBalance.setText(Double.toString(this.dCurrentBalance));

        // Establecer el símbolo de la moneda en el JLabel correspondiente
        this.lblMoneda.setText(simboloMoneda);

        // Iniciar el cliente
        this.startClient();
    }

    public void registerServers(ArrayList<NodeData> aNodeServers) {
        this.aServers = aNodeServers;
        this.jComboBox1.removeAllItems();
        for (int i = 0; i < this.aServers.size(); i++) {
            this.jComboBox1.addItem(this.aServers.get(i).getNodeName());
        }
    }

    public boolean sendTransaction() {
        String sNode = this.nodeData.getNodeName();
        String sReceiver = this.txtSend.getText().trim().toUpperCase();
        Double dAmount = Double.parseDouble(this.txtAmount.getText());
        String sMotivo = this.comboMotivo.getSelectedItem().toString();
        int iServer = this.jComboBox1.getSelectedIndex();

        String sMonedaRemitente = this.lblMoneda.getText();

   
        String sPaisReceptor = (String) this.jComboBox1.getSelectedItem();
        String sMonedaReceptor = "";
        switch (sPaisReceptor) {
            case "ESTADOS UNIDOS":
            case "CANADÁ":
            case "EL SALVADOR":
            case "PANAMÁ":
            case "ECUADOR":
            case "BELICE":
                sMonedaReceptor = "$"; // USD
                break;
            case "ALEMANIA":
            case "FRANCIA":
            case "ITALIA":
            case "ESPAÑA":
            case "BÉLGICA":
            case "PORTUGAL":
            case "IRLANDA":
            case "FINLANDIA":
                sMonedaReceptor = "€"; // EUR
                break;
            case "JAPÓN":
                sMonedaReceptor = "¥"; // JPY
                break;

            // País que utiliza MXN
            case "MÉXICO":
                sMonedaReceptor = "MX$"; // MXN
                break;
        }

  
        double dMontoConvertido = dAmount;

   
        if (dMontoConvertido <= this.dCurrentBalance) {
            this.jLabel4.setText("Current Balance:");
            this.dCurrentBalance -= dMontoConvertido;

            try {

                sNode = this.oCifrado.encriptar(sNode);
                sReceiver = this.oCifrado.encriptar(sReceiver);

           
                Block blk = new Block();
                blk.setTransaction(sNode, dMontoConvertido, sReceiver, sMotivo, sMonedaRemitente, sMonedaReceptor);  // Pasar monedas sin convertir

      
                Socket socket = new Socket(
                        this.aServers.get(iServer).getIPAddress(),
                        this.aServers.get(iServer).getSocketNum()
                );
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                oos.writeObject(blk);
                socket.close();

      
                this.jLabel4.setText("Current Balance:");
                this.lBalance.setText(Double.toString(this.dCurrentBalance));
                return true;
            } catch (Exception e) {

                this.dCurrentBalance += dMontoConvertido;
                this.jLabel5.setText(e.toString());
            }
        } else {
            this.jLabel4.setText("-Insufficient Balance:");
        }
        return false;
    }




    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        txtAmount = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        lBalance = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtSend = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jlBackground = new javax.swing.JLabel();
        labelMotivo = new javax.swing.JLabel();
        comboMotivo = new javax.swing.JComboBox<>();
        lblMoneda = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lUser = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(525, 547));
        setMinimumSize(new java.awt.Dimension(525, 547));
        setSize(new java.awt.Dimension(292, 547));
        getContentPane().setLayout(null);

        jLabel2.setFont(new java.awt.Font("URW Gothic", 0, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Servidor:");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(130, 330, 70, 20);
        getContentPane().add(txtAmount);
        txtAmount.setBounds(250, 190, 110, 40);

        jLabel4.setFont(new java.awt.Font("URW Gothic", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Saldo actual:");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(80, 440, 140, 30);

        lBalance.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        lBalance.setForeground(new java.awt.Color(0, 0, 0));
        lBalance.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lBalance.setText("0.0");
        getContentPane().add(lBalance);
        lBalance.setBounds(290, 440, 110, 20);

        jLabel6.setFont(new java.awt.Font("URW Gothic", 0, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Enviar a:");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(140, 140, 70, 20);
        getContentPane().add(txtSend);
        txtSend.setBounds(250, 130, 110, 40);

        jButton2.setBackground(new java.awt.Color(0, 51, 204));
        jButton2.setFont(new java.awt.Font("URW Gothic", 1, 16)); // NOI18N
        jButton2.setText("Enviar");
        jButton2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 255), 0, true));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(250, 370, 200, 40);

        jComboBox1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jComboBox1.setForeground(new java.awt.Color(0, 0, 0));
        getContentPane().add(jComboBox1);
        jComboBox1.setBounds(250, 320, 200, 26);

        jLabel3.setFont(new java.awt.Font("URW Gothic", 0, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Monto:");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(140, 200, 70, 20);
        getContentPane().add(jLabel5);
        jLabel5.setBounds(20, 460, 250, 0);
        getContentPane().add(jlBackground);
        jlBackground.setBounds(0, 0, 0, 510);

        labelMotivo.setFont(new java.awt.Font("URW Gothic", 0, 16)); // NOI18N
        labelMotivo.setForeground(new java.awt.Color(0, 0, 0));
        labelMotivo.setText("Motivo:");
        labelMotivo.setToolTipText("");
        getContentPane().add(labelMotivo);
        labelMotivo.setBounds(140, 270, 80, 20);

        comboMotivo.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        comboMotivo.setForeground(new java.awt.Color(0, 0, 0));
        comboMotivo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Transferencia de dinero", "Donación", "Inversión", "Pago de servicios", "Remesa", "Incentivo" }));
        getContentPane().add(comboMotivo);
        comboMotivo.setBounds(250, 260, 200, 26);

        lblMoneda.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        lblMoneda.setForeground(new java.awt.Color(0, 0, 0));
        lblMoneda.setText("$");
        getContentPane().add(lblMoneda);
        lblMoneda.setBounds(250, 440, 60, 30);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new javax.swing.OverlayLayout(jPanel1));
        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 120, 570, 450);

        jLabel1.setFont(new java.awt.Font("URW Gothic", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Simple Wallet");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(200, 20, 157, 30);

        lUser.setFont(new java.awt.Font("URW Gothic", 1, 24)); // NOI18N
        lUser.setForeground(new java.awt.Color(0, 51, 204));
        lUser.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lUser.setText("user");
        getContentPane().add(lUser);
        lUser.setBounds(188, 80, 200, 30);

        jLabel7.setFont(new java.awt.Font("URW Gothic", 0, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("IP/port");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(181, 50, 210, 20);

        jPanel2.setBackground(new java.awt.Color(255, 102, 0));
        getContentPane().add(jPanel2);
        jPanel2.setBounds(0, 0, 570, 120);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.sendTransaction();
        this.txtAmount.setText("");
        this.txtSend.setText("");
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(frmWallet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmWallet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmWallet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmWallet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmWallet().setVisible(true);
            }
        });
    }

    private void startClient() {
        //start listener
        try {
            InetAddress iAddress = InetAddress.getByName(this.nodeData.getIPAddress());
            InetSocketAddress sNetSckt = new InetSocketAddress(iAddress, this.nodeData.getSocketNum());
            this.clientSocket = new ServerSocket();
            this.clientSocket.bind(sNetSckt);
            this.tListener = new Thread(this);
            this.tListener.start();
        } catch (Exception ee) {
            this.jLabel5.setText(ee.toString());
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                Socket socket = this.clientSocket.accept();

                //Deserialization
                InputStream is = socket.getInputStream();
                ObjectInputStream ois = new ObjectInputStream(is);
                this.dCurrentBalance += (Double) ois.readObject();
                this.jLabel4.setText("Current Balance:");
                this.lBalance.setText(Double.toString(dCurrentBalance));
                socket.close();
            } catch (Exception ee) {
                this.jLabel5.setText(ee.toString());
            }
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> comboMotivo;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel jlBackground;
    private javax.swing.JLabel lBalance;
    private javax.swing.JLabel lUser;
    private javax.swing.JLabel labelMotivo;
    private javax.swing.JLabel lblMoneda;
    private javax.swing.JTextField txtAmount;
    private javax.swing.JTextField txtSend;
    // End of variables declaration//GEN-END:variables
}
