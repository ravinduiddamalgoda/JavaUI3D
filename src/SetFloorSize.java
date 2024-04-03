import java.util.Objects;

public class SetFloorSize extends javax.swing.JFrame {

    private float tableScale;
    private float chairScale;
    private float cupboardScale;
    private String tableColor;
    private String chairColor;
    private String cupColor;
    public SetFloorSize(float tableScale , float chairScale , float cupboardScale , String tableColor , String chairColor , String cupColor) {
        this.tableScale = tableScale;
        this.chairScale = chairScale;
        this.cupboardScale = cupboardScale;
        this.tableColor = tableColor;
        this.chairColor = chairColor;
        this.cupColor = cupColor;
        initComponents();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        xGap = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        yGap = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Nimbus Sans", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(53, 123, 171));
        jLabel1.setText("Set Floor Size");

        xGap.setText("75");
        xGap.setToolTipText("Enter X axis Gap");

        jLabel2.setIcon(new javax.swing.ImageIcon(Objects.requireNonNull(getClass().getResource("/thumnail1.jpg")))); // NOI18N

        jLabel3.setFont(new java.awt.Font("Nimbus Sans", 0, 18)); // NOI18N
        jLabel3.setText("Enter the X axis gap : ");

        jLabel4.setFont(new java.awt.Font("Nimbus Sans", 0, 18)); // NOI18N
        jLabel4.setText("Enter the Y axis Gap : ");

        yGap.setText("160");
        yGap.setToolTipText("Enter Y Axis Gap");

        jButton1.setBackground(new java.awt.Color(156, 87, 59));
        jButton1.setFont(new java.awt.Font("Nimbus Sans", 0, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Next");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Nimbus Sans", 0, 18)); // NOI18N
        jLabel5.setText("Select Floor Color :");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "BEIGE", "TAN", "LIGHT_GRAY", "IVORY", "SAND" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(56, 56, 56)
                                                .addComponent(jLabel3)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(xGap, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(40, 40, 40)
                                                .addComponent(jLabel4)
                                                .addGap(18, 18, 18)
                                                .addComponent(yGap, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(118, 118, 118)
                                                .addComponent(jLabel2)
                                                .addGap(70, 70, 70)
                                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(274, 274, 274)
                                                .addComponent(jLabel1))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(164, 164, 164)
                                                .addComponent(jLabel5)
                                                .addGap(42, 42, 42)
                                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(46, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addComponent(jLabel1)
                                .addGap(78, 78, 78)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(xGap, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel3)
                                        .addComponent(jLabel4)
                                        .addComponent(yGap, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(25, 25, 25)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel5)
                                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2)
                                .addGap(25, 25, 25))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(360, Short.MAX_VALUE)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(188, 188, 188))
        );

        pack();
    }// </editor-fold>

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        int gapX = Integer.valueOf(xGap.getText());
        int gapY  = Integer.valueOf(yGap.getText());
        String flCol = (String) jComboBox1.getSelectedItem();
        RectFloor sf = new RectFloor(tableScale , chairScale , cupboardScale , tableColor,chairColor , cupColor ,gapX ,gapY , flCol);
        sf.setVisible(true);


    }

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
            java.util.logging.Logger.getLogger(SetFloorSize.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SetFloorSize.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SetFloorSize.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SetFloorSize.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
//                new SetFloorSize().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField xGap;
    private javax.swing.JTextField yGap;
    // End of variables declaration
}