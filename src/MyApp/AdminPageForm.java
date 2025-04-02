package MyApp;

import MyLib.CornerLot;
import MyLib.Lot;
import MyLib.LotDatabase;
import MyLib.LotDatabaseAdapter;
import javax.swing.table.DefaultTableModel;
import MyLib.LotService;
import java.awt.Color;
import java.awt.Component;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Naisah
 */
public class AdminPageForm extends javax.swing.JFrame {
    private LotService lotService;
    private String loggedInUser;
    private static final String PURCHASE_FILE = "purchases.txt";
    /**
     * Creates new form AdminPageForm
     */
    public AdminPageForm(LotService lotService) {
        initComponents();
        this.lotService = lotService;
        jTable1.getColumnModel().getColumn(4).setCellRenderer(new StatusCellRenderer());
        loadAllLots();  
        loadAvailableLots();
        loadReservedLots();
        loadSoldLots();
        loadAllPurchases();
        Color col=new Color(241, 237, 230 );
        getContentPane().setBackground(col);
    }
    
    private void loadAllLots() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);

       for (Lot lot : lotService.getAllLots()) {
            model.addRow(new Object[]{
                lot.getBlockNumber(), 
                lot.getLotNumber(), 
                lot.getLotSize(),  
                lot.getLotPrice(),  
                lot.getStatus(), 
                lot.getLotType()
            });
        }
    }
    
    private void loadAvailableLots() {
        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
        model.setRowCount(0);
            for (Lot lot : lotService.getAvailableLots()) {
                double finalPrice = (lot instanceof CornerLot) ? ((CornerLot) lot).calculateFinalPrice() : lot.getLotPrice();
                int finalSize = (lot instanceof CornerLot) ? ((CornerLot) lot).calculateFinalSize(): lot.getLotSize();
                model.addRow(new Object[]{lot.getBlockNumber(), lot.getLotNumber(), finalSize, finalPrice, lot.getStatus(), lot.getLotType()});
            }
    }   
    
    private void loadReservedLots() {
        DefaultTableModel model = (DefaultTableModel) jTable3.getModel();
        model.setRowCount(0);
            for (Lot lot : lotService.getReservedLots()) {
                double finalPrice = (lot instanceof CornerLot) ? ((CornerLot) lot).calculateFinalPrice() : lot.getLotPrice();
                int finalSize = (lot instanceof CornerLot) ? ((CornerLot) lot).calculateFinalSize(): lot.getLotSize();
                model.addRow(new Object[]{lot.getBlockNumber(), lot.getLotNumber(), finalSize, finalPrice, lot.getStatus(), lot.getLotType()});
            }
    }   
    
    private void loadSoldLots() {
        DefaultTableModel model = (DefaultTableModel) jTable5.getModel();
        model.setRowCount(0);
            for (Lot lot : lotService.getSoldLots()) {
                double finalPrice = (lot instanceof CornerLot) ? ((CornerLot) lot).calculateFinalPrice() : lot.getLotPrice();
                int finalSize = (lot instanceof CornerLot) ? ((CornerLot) lot).calculateFinalSize(): lot.getLotSize();
                model.addRow(new Object[]{lot.getBlockNumber(), lot.getLotNumber(), finalSize, finalPrice, lot.getStatus(), lot.getLotType()});
            }
    }   
    
    private void loadAllPurchases() {
        DefaultTableModel model = (DefaultTableModel) jTable4.getModel();
        model.setRowCount(0);

        try (BufferedReader reader = new BufferedReader(new FileReader(PURCHASE_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                if (parts.length == 7) {
                    String loggedUser = parts[0];
                    int blockNumber = Integer.parseInt(parts[1]);
                    int lotNumber = Integer.parseInt(parts[2]);
                    int lotSize = Integer.parseInt(parts[3]);
                    double lotPrice = Double.parseDouble(parts[4]);
                    String status = parts[5].trim(); 
                    String lotType = parts[6].trim(); 

                    model.addRow(new Object[]{loggedUser, blockNumber, lotNumber, lotSize, lotPrice, status, lotType});
                } else {
                    System.err.println("Skipping malformed line: " + line);
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error loading all purchases.");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Error parsing purchase data.");
        }
    }

    
    public class StatusCellRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            if (value != null) {
                String status = value.toString().toLowerCase();
                switch (status) {
                    case "available":
                        cell.setBackground(new Color(144, 238, 144)); 
                        cell.setForeground(Color.BLACK);
                        break;
                    case "reserved":
                        cell.setBackground(new Color(173, 216, 230)); 
                        cell.setForeground(Color.BLACK);
                        break;
                    case "sold":
                        cell.setBackground(new Color(255, 99, 71)); 
                        cell.setForeground(Color.WHITE);
                        break;
                    default:
                        cell.setBackground(Color.WHITE);
                        cell.setForeground(Color.BLACK);
                        break;
                }
            }
            return cell;
        }
    }    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        LogOutButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setBackground(new java.awt.Color(235, 227, 219));
        jTable1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Block Number", "Lot Number", "Lot Size", "Lot Price", "Status", "Lot Type"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Double.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable1.setToolTipText("");
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel1.setText("All lots");

        jTable2.setBackground(new java.awt.Color(235, 227, 219));
        jTable2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Block Number", "Lot Number", "Lot Size", "Lot Price", "Status", "Lot Type"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Double.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jLabel2.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel2.setText("Available lots");

        jTable3.setAutoCreateRowSorter(true);
        jTable3.setBackground(new java.awt.Color(235, 227, 219));
        jTable3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Block Number", "Lot Number", "Lot Size", "Lot Price", "Status", "Lot Type"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Double.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable3.setToolTipText("");
        jScrollPane3.setViewportView(jTable3);

        jTable4.setAutoCreateRowSorter(true);
        jTable4.setBackground(new java.awt.Color(235, 227, 219));
        jTable4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Name", "Block Number", "Lot Number", "Lot Size", "Lot Price", "Status", "Lot Type"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Double.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable4.setToolTipText("");
        jScrollPane4.setViewportView(jTable4);

        jLabel3.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel3.setText("Reserved lots");

        jLabel4.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel4.setText("Sold lots");

        jTable5.setAutoCreateRowSorter(true);
        jTable5.setBackground(new java.awt.Color(235, 227, 219));
        jTable5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTable5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Block Number", "Lot Number", "Lot Size", "Lot Price", "Status", "Lot Type"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Double.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable5.setToolTipText("");
        jScrollPane5.setViewportView(jTable5);

        jLabel5.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel5.setText("With Name of the Owner");

        LogOutButton.setBackground(new java.awt.Color(58, 45, 40));
        LogOutButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        LogOutButton.setForeground(new java.awt.Color(255, 255, 255));
        LogOutButton.setText("Log Out");
        LogOutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LogOutButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(294, 294, 294)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(465, 465, 465))
            .addGroup(layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(205, 205, 205)
                        .addComponent(jLabel1)
                        .addGap(408, 408, 408)
                        .addComponent(jLabel2)
                        .addGap(362, 362, 362)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 495, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 495, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 486, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 495, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane4)))
                    .addComponent(LogOutButton))
                .addContainerGap(80, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addComponent(LogOutButton)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void LogOutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LogOutButtonActionPerformed
        loggedInUser = null; 
        this.dispose(); 

        LoginAndRegisterForm loginForm = new LoginAndRegisterForm();
        loginForm.setVisible(true);
    }//GEN-LAST:event_LogOutButtonActionPerformed

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
            java.util.logging.Logger.getLogger(AdminPageForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminPageForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminPageForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminPageForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                LotDatabase lotDatabase = new LotDatabase("lots.txt"); 
            LotService lotService = new LotDatabaseAdapter(lotDatabase); 
            
            new AdminPageForm(lotService).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton LogOutButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTable5;
    // End of variables declaration//GEN-END:variables
}
