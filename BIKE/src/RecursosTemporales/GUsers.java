/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RecursosTemporales;

import Modelo.MainClass;

import Controlador.ConsultaSQL;
import Modelo.MdlData;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.*;

/*
 * @author Miguel
 */
public class GUsers extends javax.swing.JInternalFrame {
    
    final String mitabla = "usuarios";
    
    DefaultTableModel dtm = new DefaultTableModel();
    
    Object obs;

  /**
     * Creates new form Clientes
     */
    public GUsers() {
        initComponents();
        String[] hr = {"Identificacion","Nombre","Direccion","Telefono","Email","Usuario","Contraseña","Departamento","Rol"};
        dtm.setColumnIdentifiers(hr);
        tblusers.setModel(dtm);
        txtnknme.setVisible(false);
        txtpass.setVisible(false);
        lblnknme.setVisible(false);
        lblpass.setVisible(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField3 = new javax.swing.JTextField();
        txtdoc = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtname = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtresid = new javax.swing.JTextField();
        txttel = new javax.swing.JTextField();
        txtmail = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        Agregar_ = new javax.swing.JButton();
        Modificar_ = new javax.swing.JButton();
        Eliminar_ = new javax.swing.JButton();
        LimpiarPantalla_ = new javax.swing.JButton();
        Buscar_ = new javax.swing.JButton();
        txtsearch = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblusers = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblaux = new javax.swing.JLabel();
        cmbrol = new javax.swing.JComboBox();
        cmbdept = new javax.swing.JComboBox();
        txtpass = new javax.swing.JTextField();
        txtnknme = new javax.swing.JTextField();
        lblnknme = new javax.swing.JLabel();
        lblpass = new javax.swing.JLabel();

        setClosable(true);
        setTitle("Gestion Usuarios");

        jLabel1.setText("Documento:");

        jLabel3.setText("Nombre:");

        jLabel4.setText("Dirección:");

        jLabel5.setText("Teléfono:");

        jLabel6.setText("e-mail:");

        Agregar_.setText("Ingresar");
        Agregar_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Agregar_ActionPerformed(evt);
            }
        });

        Modificar_.setText("Modificar");
        Modificar_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Modificar_ActionPerformed(evt);
            }
        });

        Eliminar_.setText("Eliminar");
        Eliminar_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Eliminar_ActionPerformed(evt);
            }
        });

        LimpiarPantalla_.setText("Limpiar");
        LimpiarPantalla_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LimpiarPantalla_ActionPerformed(evt);
            }
        });

        Buscar_.setText("Buscar");
        Buscar_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Buscar_ActionPerformed(evt);
            }
        });

        jScrollPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPane1MouseClicked(evt);
            }
        });

        tblusers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tblusers.getTableHeader().setReorderingAllowed(false);
        tblusers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblusersMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblusers);

        jLabel7.setText("Rol:");

        jLabel8.setText("Departamento:");

        cmbrol.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Administrador", "Cajero", "Servicio Técnico" }));

        cmbdept.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ventas", "Bodega", "Sistemas", "Administracion" }));

        txtnknme.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnknmeActionPerformed(evt);
            }
        });

        lblnknme.setText("Usuario:");

        lblpass.setText("Contraseña:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(228, 228, 228)
                                .addComponent(lblaux, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(26, 26, 26)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(lblnknme)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(txtnknme, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jLabel1)
                                                    .addComponent(jLabel3)
                                                    .addComponent(jLabel4)
                                                    .addComponent(jLabel6))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(txtresid, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                                    .addComponent(txtname)
                                                    .addComponent(txtdoc)
                                                    .addComponent(txtmail)))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(lblpass)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(txtpass, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel8)
                                            .addComponent(jLabel7)
                                            .addComponent(jLabel5))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(cmbdept, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(cmbrol, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(txttel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(27, 27, 27)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(Agregar_, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(Eliminar_, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(LimpiarPantalla_, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(Modificar_, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                        .addGap(0, 197, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(Buscar_, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap(22, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(cmbrol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel8)
                                    .addComponent(cmbdept, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txttel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5))
                                .addGap(28, 28, 28)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(Agregar_)
                                    .addComponent(Modificar_))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(LimpiarPantalla_)
                                    .addComponent(Eliminar_))
                                .addGap(26, 26, 26))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtdoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtresid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtnknme, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblnknme))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtpass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblpass))))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Buscar_)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(txtsearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(67, 67, 67)
                .addComponent(lblaux, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    public void LimpiarPantalla(){
        txtdoc.setText(null);
        txtname.setText(null);
        txtresid.setText(null);
        txttel.setText(null);
        txtmail.setText(null);
        txtnknme.setText(null);
        txtpass.setText(null);
        cmbdept.setSelectedIndex(0);
        cmbrol.setSelectedIndex(0);
        try{
            for( int i=0; i<tblusers.getRowCount(); i++ ){
                dtm.removeRow(i);
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(rootPane, "Error: "+e.getLocalizedMessage(), title, 0);
        }
    }
    
    
    private void Agregar_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Agregar_ActionPerformed
        // TODO add your handling code here:
        
        ConsultaSQL data = new ConsultaSQL(mitabla);
        MdlData exe = new MdlData();//
        
        String nknme, pass;
        
        JOptionPane.showMessageDialog(rootPane,"Defina el nombre de Usuario y Contraseña de Ingreso");
        nknme = JOptionPane.showInputDialog("Usuario:");
        pass = JOptionPane.showInputDialog("Contraseña:");
        
        ArrayList<Object> subida = new ArrayList<>();
        
        subida.add(txtdoc.getText());
        subida.add(txtname.getText());
        subida.add(txtresid.getText());
        subida.add(txttel.getText());
        subida.add(txtmail.getText());
        subida.add(nknme);
        subida.add(pass);
        subida.add(cmbdept.getSelectedItem());
        subida.add(cmbrol.getSelectedItem());
        
        exe.SaveToBD(data.arrayToBD(subida));
        
        LimpiarPantalla();
        
    }//GEN-LAST:event_Agregar_ActionPerformed

    private void Modificar_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Modificar_ActionPerformed
        // TODO add your handling code here:
        
        ConsultaSQL data = new ConsultaSQL(mitabla);
        MdlData exe = new MdlData();
        
        String camps[] = {"iduser","nombre","dir","telf","mail","nikuser","contrasena","dept","rol"};
        
        ArrayList<Object> newdata = new ArrayList<>();
        
        try{
            newdata.add(txtdoc.getText());
            newdata.add(txtname.getText());
            newdata.add(txtresid.getText());
            newdata.add(txttel.getText());
            newdata.add(txtmail.getText());
            newdata.add(txtnknme.getText());
            newdata.add(txtpass.getText());
            newdata.add(cmbdept.getSelectedItem());
            newdata.add(cmbrol.getSelectedItem());

            exe.Update(data.changeReg("iduser", ""+obs, newdata, camps ));

            txtnknme.setVisible(false);
            txtpass.setVisible(false); 
            lblnknme.setVisible(false);
            lblpass.setVisible(false);
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(rootPane, "Error: \n"+ex.getLocalizedMessage(), "GUsers Modificar", 0);
        }
        
    }//GEN-LAST:event_Modificar_ActionPerformed

    private void Buscar_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Buscar_ActionPerformed
        // TODO add your handling code here:
        
        if( !"".equals(txtsearch.getText()) ){
            this.obs = txtsearch.getText();
        }
        
        ConsultaSQL data = new ConsultaSQL(mitabla);
        MdlData exe = new MdlData();
        
        try{
            String camp = "iduser";
            
            exe.Search(data.extWhere(camp, ""+obs) );
            
            if(!exe.getResults().isEmpty()){
                dtm.addRow(exe.getResults().toArray());
            }
            
            txtdoc.setText(""+exe.getResults().toArray()[0]);
            txtname.setText(""+exe.getResults().toArray()[1]);
            txtresid.setText(""+exe.getResults().toArray()[2]);
            txttel.setText(""+exe.getResults().toArray()[3]);
            txtmail.setText(""+exe.getResults().toArray()[4]);
            txtnknme.setText(""+exe.getResults().toArray()[5]);
            txtpass.setText(""+exe.getResults().toArray()[6]);
            cmbdept.setSelectedItem(""+exe.getResults().toArray()[7]);
            cmbrol.setSelectedItem(""+exe.getResults().toArray()[8]);
        
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(rootPane, "Error: \n"+ex.getLocalizedMessage(), "GClientes",0);
        }
        
        txtnknme.setVisible(true);
        txtpass.setVisible(true); 
        lblnknme.setVisible(true);
        lblpass.setVisible(true);
        
        txtsearch.setText(""+obs);
        
    }//GEN-LAST:event_Buscar_ActionPerformed

    private void txtnknmeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnknmeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnknmeActionPerformed

    private void Eliminar_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Eliminar_ActionPerformed
        // TODO add your handling code here:
        
        MdlData exe = new MdlData();
        ConsultaSQL data = new ConsultaSQL(mitabla);
        
        exe.Delete(data.eraseIt("iduser", ""+obs));
        
        LimpiarPantalla();
        
    }//GEN-LAST:event_Eliminar_ActionPerformed

    private void jScrollPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jScrollPane1MouseClicked

    private void tblusersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblusersMouseClicked
        // TODO add your handling code here:
        
        int fila = tblusers.rowAtPoint(evt.getPoint());
        int col = tblusers.columnAtPoint(evt.getPoint());
        if( fila > -1 && col > -1 ){
            this.obs = dtm.getValueAt(fila, 1);
        }
        
        //dtm.getColumnName(col);
        
    }//GEN-LAST:event_tblusersMouseClicked

    private void LimpiarPantalla_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LimpiarPantalla_ActionPerformed
        // TODO add your handling code here:
        
        LimpiarPantalla();
        
    }//GEN-LAST:event_LimpiarPantalla_ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Agregar_;
    private javax.swing.JButton Buscar_;
    private javax.swing.JButton Eliminar_;
    private javax.swing.JButton LimpiarPantalla_;
    private javax.swing.JButton Modificar_;
    private javax.swing.JComboBox cmbdept;
    private javax.swing.JComboBox cmbrol;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JLabel lblaux;
    private javax.swing.JLabel lblnknme;
    private javax.swing.JLabel lblpass;
    private javax.swing.JTable tblusers;
    private javax.swing.JTextField txtdoc;
    private javax.swing.JTextField txtmail;
    private javax.swing.JTextField txtname;
    private javax.swing.JTextField txtnknme;
    private javax.swing.JTextField txtpass;
    private javax.swing.JTextField txtresid;
    private javax.swing.JTextField txtsearch;
    private javax.swing.JTextField txttel;
    // End of variables declaration//GEN-END:variables
}
