
                                    /*  SENA BRETAÑA CALI   */


package Modelo;

import Controlador.ConexionBD;//Me conecto con el paquete de conexion
import java.sql.Connection;//Permite establecer una conexion SQL
import java.sql.ResultSet;//Muestra resultados en la base de datos.
import java.sql.ResultSetMetaData;
import java.sql.SQLException;//Maneja excepciones sql
import java.sql.Statement;//Permite recibir instrucciones Mysql en Java
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.util.*;

/*
 * creation    : 26/11/2014, 08:53:33 AM
 * author      : Aprendiz
 */

public class MdlData {
    
    Connection miCndb;
    ArrayList<Object> qryres = new ArrayList<>();
    ArrayList<Object> alltable = new ArrayList<>();
    int no_regs;
    
    //Constructor
    public MdlData() {
        this.miCndb = ConexionBD.obtenerConexion();
    }
    
    
    public void SaveToBD(String sentence){
        
        try{
            Statement qst = miCndb.createStatement();
            qst.execute(sentence);
            JOptionPane.showMessageDialog(null,"Ingreso de Datos Exitoso");
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Error en el Registro de Usuario \n("+ex.getMessage()+")","MdlData SaveToBD",0);
        }
    
    }
    
    public void Search(String busqueda){
        try{
            PreparedStatement look;
            ResultSet resultados;
            ResultSetMetaData md;
            look = miCndb.prepareStatement(busqueda);
            resultados = look.executeQuery();
            if( resultados.next() ){
                
                md = resultados.getMetaData();
                for( int i=1; i<=md.getColumnCount(); i++ ){
                    qryres.add( resultados.getString(i) );
                }
                //JOptionPane.showMessageDialog(null, "Registro Encontrado", "MdlData Search", 2);
                
                int i = 1;
                //Iterator<Object> cover = qryres.iterator();
                while ( resultados.next() ){
                     i = i + 1;
                }
                
                no_regs = i;
                
                String[][] listprods = {};
                
                int c = 0;
                while ( resultados.next() ){
                    for( int j=1; j<=md.getColumnCount(); j++ ){
                    alltable.add( resultados.getString(j) );
                } 
                }
                        
            }
            else{
                qryres = null;
                JOptionPane.showMessageDialog(null, "No se ha encontrado ningun registro para esta Busqueda", "MdlData",0);
            }
        }
        catch(SQLException ex){
           JOptionPane.showMessageDialog(null, "Error: \n"+ex.getLocalizedMessage(), "MdlData",0);             
        }
    }
    
    
    public void Update(String query){
        try{
            Statement look = miCndb.createStatement();
            int ch = look.executeUpdate(query);
            if(ch>0){
                JOptionPane.showMessageDialog(null, "Modificacion Exitosa", "MdlData Update",2);
            }
             
        }
        catch(SQLException ex){
           JOptionPane.showMessageDialog(null, "Error: \n"+ex.getMessage(), "MdlData Update",0);             
        }
    }
    
    
    public void Delete(String target){
        try{
            Statement look = miCndb.createStatement();
            int ch = look.executeUpdate(target);
            if(ch>0){
                JOptionPane.showMessageDialog(null, "Registro Eliminado", "MdlData Delete", 1);
            }
            else{
                JOptionPane.showMessageDialog(null, "No se ha encontrado el Objeto", "MdlData Delete", 1); 
            }
        }
        catch(SQLException ex){
           JOptionPane.showMessageDialog(null, "Error: \n"+ex.getMessage(), "MdlData Update",0);             
        }
    }
    
    
    public void ViewForKey(String sentence, String camp){
        try{
            Statement look = miCndb.createStatement();
            ResultSet resultados;
            ResultSetMetaData md;
            resultados = look.executeQuery(sentence);
            while( resultados.next() ){
                qryres.add(resultados.getString(camp));
            }
        }
        catch(SQLException ex){
           JOptionPane.showMessageDialog(null, "Error: \n"+ex.getMessage(), "MdlData Update",0);             
        }
    }
    
    
    
    public ArrayList<Object> getResults(){
        return qryres;
    }
    
    public ArrayList<Object> getAllTable(){
        return alltable;
    }
    
    public int getNumRegs(){
        return no_regs;
    }
    
}
