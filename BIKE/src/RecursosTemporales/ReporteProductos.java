/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RecursosTemporales;

//import ConexionBD.ConexionBD;//Me conecto con el paquete de conexion
import java.sql.Connection;//Permite establecer una conexion SQL
import java.sql.ResultSet;//Muestra resultados en la base de datos.
import java.sql.ResultSetMetaData;
import java.sql.SQLException;//Maneja excepciones sql
import java.sql.Statement;//Permite recibir instrucciones Mysql en Java
//import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
//import java.util.*;

import Modelo.MainClass;

import Controlador.ConexionBD;
import Controlador.ConsultaSQL;
import Modelo.MdlData;
import Controlador.Fechas;
import Vista.ControlRepuestos;
import java.util.*;//Maneja el ArrayList
import javax.swing.JOptionPane;
import javax.swing.table.*;

/**
 *
 * @author Aprendiz
 */
public class ReporteProductos extends javax.swing.JInternalFrame {
    private static final long serialVersionUID = 1L;
    
    DefaultTableModel dtm = new DefaultTableModel();
    Connection myConex;

    /**
     * Creates new form ReporteProductos
     */
    public ReporteProductos() {
        initComponents();
        
        String headers[] = {"Código","Proveedor","Descripcion","Inventario","Fecha Surtido","Hora","Costo Und","Precio Venta"};
        dtm.setColumnIdentifiers(headers);
        tblprods.setModel(dtm);
        tblprods.setAutoResizeMode(tblprods.AUTO_RESIZE_ALL_COLUMNS);
        myConex = ConexionBD.obtenerConexion();
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
        tblprods = new javax.swing.JTable();
        VerLista_ = new javax.swing.JButton();
        GestionarProductos_ = new javax.swing.JButton();

        tblprods.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tblprods);

        VerLista_.setText("Ver Listado");
        VerLista_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VerLista_ActionPerformed(evt);
            }
        });

        GestionarProductos_.setText("Ir a Gestion de Productos");
        GestionarProductos_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GestionarProductos_ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 717, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(220, 220, 220)
                                .addComponent(GestionarProductos_))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(90, 90, 90)
                                .addComponent(VerLista_)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(VerLista_)
                .addGap(21, 21, 21)
                .addComponent(GestionarProductos_)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void VerLista_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VerLista_ActionPerformed
        // TODO add your handling code here:
        
        //MdlData exe = new MdlData();
        //CtrData data = new ConsultaSQL("productos");
        
        
        try{
            /*exe.Search(data.ExtractAll());
            listado = exe.getAllTable();
            //listado.size();
            
            Iterator<Object> listing = listado.iterator();
            
            while ( listing.hasNext() ){
                dtm.addRow( listado.toArray() );
            }*/
            
            for( int i=0; i<tblprods.getRowCount(); i++ ){
                dtm.removeRow(i);
            }
            
            PreparedStatement look;
            ResultSet resultados;
            ResultSetMetaData md;
            look = myConex.prepareStatement("select * from productos");
            resultados = look.executeQuery();
            
            ArrayList<Object[]> listado = new ArrayList<>();
            md = resultados.getMetaData();
            //int f = 1;
            //Object[] fila = new Object[md.getColumnCount()];
            while ( resultados.next() ){
                
                Object[] rows = new Object [md.getColumnCount()];
                for ( int i=0; i<rows.length; i++ ){
                    rows[i] = resultados.getObject(i+1);
                    //fila[i] = resultados.getString(i+1);
                }
                listado.add( rows );
            }
            for ( Object[] listado1 : listado ) {
                dtm.addRow(listado1);
            }
            //myConex.close();
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(this, ""+ex.getMessage());
        }
        
    }//GEN-LAST:event_VerLista_ActionPerformed

    private void GestionarProductos_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GestionarProductos_ActionPerformed
        // TODO add your handling code here:
        
        ControlRepuestos gestion = new ControlRepuestos();
        this.getDesktopPane().add(gestion);
        gestion.setVisible(true);
        //report.setVisible(true);
        gestion.toFront();
        
    }//GEN-LAST:event_GestionarProductos_ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton GestionarProductos_;
    private javax.swing.JButton VerLista_;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblprods;
    // End of variables declaration//GEN-END:variables
}
