package nl.infosys.hartigehap.kitchenSystem.presentation;

import nl.infosys.hartigehap.kitchenSystem.businesslogic.OrderManager;
import nl.infosys.hartigehap.kitchenSystem.domain.Employee;
import nl.infosys.hartigehap.kitchenSystem.domain.Order;
import nl.infosys.hartigehap.kitchenSystem.domain.OrderDetail;
import java.util.ArrayList;
import javax.swing.AbstractButton;
import javax.swing.JDialog;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Infosys
 *
 */
public class GUI extends javax.swing.JFrame {

    private OrderManager manager;
    private int orderId;
    private ArrayList<Employee> employees = new ArrayList<>();
    private ArrayList<OrderDetail> orders = new ArrayList<>();
    JMenuBar menuBar;
    JMenu menu, submenu;
    private int selectedRow;
    private DefaultTableModel model;

    /**
     * Creates new form MainJFrame
     *
     * @param manager ordermanager
     */
    public GUI(OrderManager manager) {
        this.manager = manager;
        initComponents();
        setVisible(true);

        UIManager.put("OptionPane.cancelButtonText", "Annuleer");
        UIManager.put("OptionPane.noButtonText", "Nee");
        UIManager.put("OptionPane.okButtonText", "Ok");
        UIManager.put("OptionPane.yesButtonText", "Ja");

        selectedRow = -1;
        orderDetailsField.setEditable(false);
        coupleTextArea.setEditable(false);

        for (Employee employee : manager.getEmployeeList()) {
            medewerkerComboBox.addItem(employee.getEmployeeId() + ". " + employee.getFirstName());
            employees.add(employee);
        }
    }

    public void refreshOrders() {

        DefaultTableModel model = new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Bestelling nummer", "Tafelnummer", "Tijd", "Status", "Geaccepteerd"
                }
        );
        //import the arraylist orders using the manager
        ArrayList<Order> orders = manager.getOrders();
        //loop through the arraylist, let each Order object perform 
        //the getBestellingID() and getTafelNummer and add it to the
        //model of the jTable in the gui

//           
        for (int i = 0; i < orders.size(); i++) {
            model.addRow(new Object[]{
                orders.get(i).getOrderId(),
                orders.get(i).getTableNumber(),
                orders.get(i).getTimeStamp(),
                orders.get(i).getStatus(),
                orders.get(i).getAccepted()
            });
        }

        this.model = model;
        orderTable.setModel(model);
    }

//        DefaultTableModel tableModel = new DefaultTableModel();
//        tableModel.addColumn("test");
//        orderTable.setModel(tableModel);
        //TODO menu maken met opties.
//        menuBar = new JMenuBar();
//        menu = new JMenu("Application");
//        menuBar.add(menu);
    private void fillCoupledEmployeeList() {
        String coupledText = "";
        for (Employee employee : manager.getCoupledEmployeeList(orderId)) {
            coupledText += (employee.getEmployeeId() + ". " + employee.getFirstName() + "\n"
                    + "Gerecht " + employee.getDishName() + "\n\n ");
        }
        coupleTextArea.setText(coupledText);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        orderDetailsField = new javax.swing.JTextArea();
        bestellingButton = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        coupleTextArea = new javax.swing.JTextArea();
        koppelButton = new javax.swing.JButton();
        medewerkerComboBox = new javax.swing.JComboBox();
        gerechtComboBox = new javax.swing.JComboBox();
        acceptButton = new javax.swing.JButton();
        deAcceptButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        orderTable = new javax.swing.JTable();
        orderReadyButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(234, 234, 234));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setPreferredSize(new java.awt.Dimension(95, 650));

        jButton1.setText("< Terug");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        orderDetailsField.setColumns(20);
        orderDetailsField.setRows(5);
        jScrollPane1.setViewportView(orderDetailsField);

        bestellingButton.setText("Haal bestellingen op");
        bestellingButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bestellingButtonActionPerformed(evt);
            }
        });

        coupleTextArea.setColumns(20);
        coupleTextArea.setRows(5);
        jScrollPane4.setViewportView(coupleTextArea);

        koppelButton.setText("Koppel");
        koppelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                koppelButtonActionPerformed(evt);
            }
        });

        medewerkerComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                medewerkerComboBoxActionPerformed(evt);
            }
        });

        gerechtComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gerechtComboBoxActionPerformed(evt);
            }
        });

        acceptButton.setText("Accepteren");
        acceptButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acceptButtonActionPerformed(evt);
            }
        });

        deAcceptButton.setText("De-accepteren");
        deAcceptButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deAcceptButtonActionPerformed(evt);
            }
        });

        //create DefaultTableModel and put it in a local variable
        //TODO tabel model zelf maken voor combobox etc.
        //MyTableModel modelTest = new MyTableModel();

        refreshOrders();
        //apply model to table
        orderTable.setModel(model);
        //orderTable.setModel(modelTest);
        orderTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                orderTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(orderTable);

        orderReadyButton.setText("Bestelling gereed");
        orderReadyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                orderReadyButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(bestellingButton)
                        .addGap(18, 18, 18)
                        .addComponent(acceptButton)
                        .addGap(18, 18, 18)
                        .addComponent(deAcceptButton)
                        .addGap(18, 18, 18)
                        .addComponent(orderReadyButton)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(medewerkerComboBox, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(koppelButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(gerechtComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(bestellingButton)
                    .addComponent(acceptButton)
                    .addComponent(deAcceptButton)
                    .addComponent(orderReadyButton)
                    .addComponent(gerechtComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(medewerkerComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(koppelButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane4))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(60, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Eetcafe de Hartige Hap");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Powered by InfoSys");
        jLabel2.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel2.setAlignmentX(0.5F);
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jButton2.setText("Uitloggen");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 798, Short.MAX_VALUE)
                                .addComponent(jButton2))
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1058, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 509, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(147, 147, 147)
                .addComponent(jLabel2))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 517, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void orderTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_orderTableMouseClicked
        // TODO add your handling code here:
        coupleTextArea.setText("");
        gerechtComboBox.removeAllItems();

        this.selectedRow = orderTable.getSelectedRow();
        this.orderId = (int) orderTable.getValueAt(selectedRow, 0);

        fillCoupledEmployeeList();

        String allText = "";

        for (OrderDetail order : manager.getOrderDetails(orderId)) {
            allText += "Naam: " + order.getName() + "\nBeschrijving:\n" + order.getDescription() + "\nBereidingstijd: " + order.getAverageTime() + " Minuten\n";
            allText += "Hoeveelheid: " + order.getAmmount() + "\n\n";
            gerechtComboBox.addItem(order.getName());
            orders.add(order);
        }
        orderDetailsField.setText(allText);

        orderDetailsField.setEditable(false);

        //OrderDetailsDAO daod = new OrderDetailsDAO();
        //daod.getOrderDetails(selectedValue);
//        orderDetails.setText("Naam gerecht: " + daod.getName() + "\n" 
//                + "Beschrijving: " + daod.getDescription() + "\n" 
//                + "Hoeveelheid: " + daod.getAmmount() + "\n" 
//                + "Gemiddelde bereidingstijd: " + daod.getAverageTime()+ "\n");
//        
        //manager.getOrderDetails(selectedValue);
        //manager.setOrderId(selected);
    }//GEN-LAST:event_orderTableMouseClicked

    private void gerechtComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gerechtComboBoxActionPerformed

    }//GEN-LAST:event_gerechtComboBoxActionPerformed

    private void medewerkerComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_medewerkerComboBoxActionPerformed


    }//GEN-LAST:event_medewerkerComboBoxActionPerformed

    private void koppelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_koppelButtonActionPerformed

        //System.out.println(medewerkerComboBox.getSelectedIndex());
        /* Uitgeschreven uitwerking van de regel aangegeven bij #
         int number = medewerkerComboBox.getSelectedIndex();
         Employee test123 = employees.get(number);
         test123.getEmployeeId();
         * ## System.out.println("employee id " + employees.get(medewerkerComboBox.getSelectedIndex()).getEmployeeId());
         */
        if (selectedRow != -1) {
            int employeeId = employees.get(medewerkerComboBox.getSelectedIndex()).getEmployeeId();
            int productId = orders.get(gerechtComboBox.getSelectedIndex()).getProductId();

            manager.updateCoupledOrderIdOp(employeeId, orderId, productId);
            fillCoupledEmployeeList();
            disable(koppelButton, 500);
        } else {

            JOptionPane optionPane = new JOptionPane("Er is geen bestelling geselecteerd.", JOptionPane.ERROR_MESSAGE);
            JDialog dialog = optionPane.createDialog("Fout bij koppelen");
            dialog.setAlwaysOnTop(this.isAlwaysOnTopSupported());
            dialog.setVisible(true);

        }

    }//GEN-LAST:event_koppelButtonActionPerformed

    private void bestellingButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bestellingButtonActionPerformed
        // TODO add your handling code here:
        refreshOrders();
    }//GEN-LAST:event_bestellingButtonActionPerformed

    private void acceptButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acceptButtonActionPerformed
        // TODO add your handling code here:
        if (selectedRow != -1) {
            manager.updateAcceptionStatus(orderId, true);
            disable(acceptButton, 500);
        } else {
            JOptionPane optionPane = new JOptionPane("Er is geen bestelling geselecteerd.", JOptionPane.ERROR_MESSAGE);
            JDialog dialog = optionPane.createDialog("Fout bij accepteren");
            dialog.setAlwaysOnTop(this.isAlwaysOnTopSupported());
            dialog.setVisible(true);
        }
        refreshOrders();
    }//GEN-LAST:event_acceptButtonActionPerformed

    private void deAcceptButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deAcceptButtonActionPerformed
        // TODO add your handling code here:
        if (selectedRow != -1) {
            manager.updateAcceptionStatus(orderId, false);
            disable(deAcceptButton, 500);
        } else {
            JOptionPane optionPane = new JOptionPane("Er is geen bestelling geselecteerd.", JOptionPane.ERROR_MESSAGE);
            JDialog dialog = optionPane.createDialog("Fout bij de-accepteren");
            dialog.setAlwaysOnTop(this.isAlwaysOnTopSupported());
            dialog.setVisible(true);
        }
        refreshOrders();
    }//GEN-LAST:event_deAcceptButtonActionPerformed

    private void orderReadyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_orderReadyButtonActionPerformed
        // TODO add your handling code here:
        if (selectedRow != -1) {
            disable(orderReadyButton, 500);

            if (JOptionPane.showConfirmDialog(null, "Weet je zeker dat de bestelling klaar is?", "Waarschuwing",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                manager.updateDeliveryStatus(orderId);
                refreshOrders();
            } else {
                JOptionPane optionPane = new JOptionPane("De bestelling is niet doorgevoerd", JOptionPane.INFORMATION_MESSAGE);
                JDialog dialog = optionPane.createDialog("Bestelling niet gereed");
                dialog.setAlwaysOnTop(this.isAlwaysOnTopSupported());
                dialog.setVisible(true);
            }

        } else {
            JOptionPane optionPane = new JOptionPane("Er is geen bestelling geselecteerd.", JOptionPane.ERROR_MESSAGE);
            JDialog dialog = optionPane.createDialog("Fout bij gereed melden");
            dialog.setAlwaysOnTop(this.isAlwaysOnTopSupported());
            dialog.setVisible(true);
        }
        orderTable.setModel(model);
    }//GEN-LAST:event_orderReadyButtonActionPerformed

    static void disable(final AbstractButton b, final long ms) {
        b.setEnabled(false);
        new SwingWorker() {
            @Override
            protected Object doInBackground() throws Exception {
                Thread.sleep(ms);
                return null;
            }

            @Override
            protected void done() {
                b.setEnabled(true);
            }
        }.execute();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton acceptButton;
    private javax.swing.JButton bestellingButton;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JTextArea coupleTextArea;
    private javax.swing.JButton deAcceptButton;
    private javax.swing.JComboBox gerechtComboBox;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JButton koppelButton;
    private javax.swing.JComboBox medewerkerComboBox;
    private javax.swing.JTextArea orderDetailsField;
    private javax.swing.JButton orderReadyButton;
    private javax.swing.JTable orderTable;
    // End of variables declaration//GEN-END:variables
}
