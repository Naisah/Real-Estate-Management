package MyApp;

import MyLib.CornerLot;
import MyLib.Lot;
import MyLib.LotDatabase;
import MyLib.LotDatabaseAdapter;
import MyLib.LotService;
import MyLib.LotStatus;
import MyLib.User;
import java.awt.Color;
import java.text.DecimalFormat;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

public class MainForm extends javax.swing.JFrame {
    private LotService lotService; 
    private String loggedInUser;
    /**
     * Creates new form Form
     */
    public MainForm() {
        this.loggedInUser = loggedInUser;
        Color col=new Color(241, 237, 230 );
        getContentPane().setBackground(col);
        
        initComponents();
        LotDatabase lotDatabase = new LotDatabase("lots.txt"); 
        lotService = new LotDatabaseAdapter(lotDatabase);
   
    }
    
    public void setLoggedInUser(String username) {
        this.loggedInUser = username;
    }
    
    private void filterLots() {
        try {
            int preferredBlockNum = Integer.parseInt(jComboBox1.getSelectedItem().toString());
            int minLotSize = Integer.parseInt(textField3.getText());
            int maxLotSize = Integer.parseInt(textField6.getText());
            double minBudget = Double.parseDouble(textField4.getText());
            double maxBudget = Double.parseDouble(textField5.getText());

            List<Lot> filteredLots = lotService.getFilteredLots(preferredBlockNum, minLotSize, maxLotSize, minBudget, maxBudget);
            DefaultTableModel model = (DefaultTableModel) jTable3.getModel();
            model.setRowCount(0);

            for (Lot lot : filteredLots) {
                double finalPrice = (lot instanceof CornerLot) ? ((CornerLot) lot).calculateFinalPrice() : lot.getLotPrice();
                int finalSize = (lot instanceof CornerLot) ? ((CornerLot) lot).calculateFinalSize(): lot.getLotSize();
                model.addRow(new Object[]{lot.getBlockNumber(), lot.getLotNumber(), finalSize, finalPrice, lot.getStatus(), lot.getLotType()});
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid input! Please enter numeric values.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void selectLot() {
        int selectedRow = jTable3.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a lot from the table.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
 
        int blockNumber = (int) jTable3.getValueAt(selectedRow, 0);
        int lotNumber = (int) jTable3.getValueAt(selectedRow, 1);
        Lot selectedLot = lotService.findLot(blockNumber, lotNumber);
 
        if (selectedLot != null) {
            DecimalFormat df = new DecimalFormat("#,###");
            double finalPrice = (selectedLot instanceof CornerLot) ? ((CornerLot) selectedLot).calculateFinalPrice() : selectedLot.getLotPrice();
            int finalSize = (selectedLot instanceof CornerLot) ? ((CornerLot) selectedLot).calculateFinalSize(): selectedLot.getLotSize();
            String formattedPrice = "₱" + df.format(finalPrice);
            JOptionPane.showMessageDialog(this,
                    "You have selected:\n" +
                            "Block: " + blockNumber + "\n" +
                            "Lot Number: " + lotNumber + "\n" +
                            "Size: " + finalSize + " sqm\n" +
                            "Price: " + formattedPrice + "\n" +
                            "Status: " + selectedLot.getStatus() + "\n" +
                            "Lot Type: " + selectedLot.getLotType(),
                    "Lot Selection", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Selected lot not found!", "Error", JOptionPane.ERROR_MESSAGE);
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

        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        textField1 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        textField2 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        textField3 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        textField4 = new javax.swing.JTextField();
        button1 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        textField5 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        textField6 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        buyButton = new javax.swing.JButton();
        LogOutButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(235, 227, 219));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel4.setText("Enter Name:");

        textField1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        textField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textField1ActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel5.setText("Enter age:");

        textField2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        textField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textField2ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel1.setText("Enter lot  size:");

        textField3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        textField3.setText("in sqm");
        textField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textField3ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel2.setText("Choose Block #:");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel3.setText("Budget range:");

        textField4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        textField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textField4ActionPerformed(evt);
            }
        });

        button1.setBackground(new java.awt.Color(58, 45, 40));
        button1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        button1.setForeground(new java.awt.Color(255, 255, 255));
        button1.setText("See Available lots");
        button1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                button1ItemStateChanged(evt);
            }
        });
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });

        jComboBox1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        textField5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        textField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textField5ActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel7.setText("           Min");

        jLabel11.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel11.setText("Max");

        jLabel14.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel14.setText("Min");

        jLabel15.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel15.setText("Max");

        textField6.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        textField6.setText("e.g.100");
        textField6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textField6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(textField3, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(button1))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(18, 18, 18)
                                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(textField1, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(textField2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addGap(32, 32, 32)
                                                        .addComponent(jLabel14)))
                                                .addGap(43, 43, 43)
                                                .addComponent(jLabel15)))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(textField4, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(textField5, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(38, 38, 38)
                                        .addComponent(jLabel11)))
                                .addGap(61, 61, 61)))
                        .addGap(67, 67, 67))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(textField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(textField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(textField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(textField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(85, 85, 85)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button1))
                .addContainerGap(45, Short.MAX_VALUE))
        );

        jLabel8.setFont(new java.awt.Font("Arial", 0, 36)); // NOI18N
        jLabel8.setText("Real Estate Management");

        jPanel4.setBackground(new java.awt.Color(235, 227, 219));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel4.setPreferredSize(new java.awt.Dimension(668, 71));

        jLabel13.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel13.setText("Filtered Lots:");

        jScrollPane4.setBackground(new java.awt.Color(235, 227, 219));

        jTable3.setBackground(new java.awt.Color(235, 227, 219));
        jTable3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Block number", "Lot number", "Lot size", "Lot price", "Status", "Lot Type"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Double.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable3.setGridColor(new java.awt.Color(235, 227, 219));
        jTable3.setSelectionForeground(new java.awt.Color(58, 45, 40));
        jTable3.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jTable3ComponentShown(evt);
            }
        });
        jScrollPane4.setViewportView(jTable3);

        jButton1.setBackground(new java.awt.Color(58, 45, 40));
        jButton1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Select Lot");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        buyButton.setBackground(new java.awt.Color(58, 45, 40));
        buyButton.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        buyButton.setForeground(new java.awt.Color(255, 255, 255));
        buyButton.setText("Buy");
        buyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buyButtonActionPerformed(evt);
            }
        });

        LogOutButton.setBackground(new java.awt.Color(58, 45, 40));
        LogOutButton.setForeground(new java.awt.Color(255, 255, 255));
        LogOutButton.setText("Log Out");
        LogOutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LogOutButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 790, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buyButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 622, Short.MAX_VALUE)
                .addComponent(LogOutButton)
                .addGap(82, 82, 82))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buyButton)
                    .addComponent(LogOutButton))
                .addGap(41, 41, 41))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 896, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(224, 224, 224))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 518, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(994, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTable3ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jTable3ComponentShown
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable3ComponentShown

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        selectLot();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void buyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buyButtonActionPerformed
        int selectedRow = jTable3.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a lot first.");
            return;
        }

        int blockNumber = Integer.parseInt(jTable3.getValueAt(selectedRow, 0).toString());
        int lotNumber = Integer.parseInt(jTable3.getValueAt(selectedRow, 1).toString());
        Lot selectedLot = lotService.findLot(blockNumber, lotNumber);

        if (selectedLot == null) {
            JOptionPane.showMessageDialog(this, "Error: Selected lot does not exist in the database.");
            return;
        }

        if (selectedLot.getStatus() != LotStatus.AVAILABLE) {
            JOptionPane.showMessageDialog(this, "Lot is not available for purchase.");
            return;
        }

        if (loggedInUser == null) {  
            JOptionPane.showMessageDialog(this, "Error: No logged-in user.");
            return;
        }

        BuyLotForm buyForm = new BuyLotForm(selectedLot, lotService, loggedInUser);
        buyForm.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_buyButtonActionPerformed

    private void textField6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textField6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textField6ActionPerformed

    private void textField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textField5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textField5ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        try {
            String name = textField1.getText();
            int age = Integer.parseInt(textField2.getText());
            double minBudget = Double.parseDouble(textField4.getText()); 
            double maxBudget = Double.parseDouble(textField5.getText()); 
            int minLotSize = Integer.parseInt(textField3.getText()); 
            int maxLotSize = Integer.parseInt(textField6.getText()); 
            int preferredBlockNum = Integer.parseInt(jComboBox1.getSelectedItem().toString());

            User userObj = new User(name, age, preferredBlockNum, minBudget, maxBudget, minLotSize, maxLotSize);

            if (!userObj.checkAge()) {
                jLabel6.setText("You must be at least 18 years old to be eligible to buy a property.");
                textField1.setText("");
                textField2.setText("");
                textField3.setText("");
                textField4.setText("");
                textField5.setText(""); 
                textField6.setText("               "); 
            } else {
                jLabel6.setText("");
                filterLots(); 
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid input! Please enter numeric values.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_button1ActionPerformed

    private void button1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_button1ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_button1ItemStateChanged

    private void textField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textField4ActionPerformed

    private void textField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textField3ActionPerformed

    private void textField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textField2ActionPerformed

    private void textField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textField1ActionPerformed

    private void LogOutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LogOutButtonActionPerformed
        loggedInUser = null; 
        this.dispose(); 

        LoginAndRegisterForm loginForm = new LoginAndRegisterForm();
        loginForm.setVisible(true);
    }//GEN-LAST:event_LogOutButtonActionPerformed

    private void jTable3MouseClicked(java.awt.event.MouseEvent evt) {                                      
                int selectedRow = jTable3.getSelectedRow();
         if (selectedRow == -1) {
             return;
         }

         int blockNumber = Integer.parseInt(jTable3.getValueAt(selectedRow, 0).toString()); 
         int lotNumber = Integer.parseInt(jTable3.getValueAt(selectedRow, 1).toString());
         double lotPrice = Double.parseDouble(jTable3.getValueAt(selectedRow, 3).toString());
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
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

      
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton LogOutButton;
    private javax.swing.JButton button1;
    private javax.swing.JButton buyButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable3;
    private javax.swing.JTextField textField1;
    private javax.swing.JTextField textField2;
    private javax.swing.JTextField textField3;
    private javax.swing.JTextField textField4;
    private javax.swing.JTextField textField5;
    private javax.swing.JTextField textField6;
    // End of variables declaration//GEN-END:variables

}


//place the classes here (if necessary)
