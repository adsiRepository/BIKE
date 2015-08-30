
                                    /*  SENA BRETAÑA CALI   */


package Modelo;

import ConexionBD.ConnBD;//Me conecto con el paquete de conexion
import Controlador.CntrlAccs;//Establezco conexion con el controlador acceso usuario para recibir los datos.
import Vista.MenuPrincipal;
import RecursosTemporales.InterfazPOS;
//  import Vista.ValidUser;
import java.sql.Connection;//Permite establecer una conexion SQL
import java.sql.ResultSet;//Muestra resultados en la base de datos.
import java.sql.SQLException;//Maneja excepciones sql
import java.sql.Statement;//Permite recibir instrucciones Mysql en Java
//import java.util.ArrayList;//Maneja el ArrayList
import javax.swing.JOptionPane;

/*
 * creation    : 26/11/2014, 08:53:33 AM
 * author      : Aprendiz
 */

public class MdlAccsUser {
    
    Connection miCndb;
    CntrlAccs user;
    
    public MdlAccsUser(CntrlAccs usr) {
        this.miCndb = ConnBD.connect_BD();
        this.user = usr;
    }
    
    public void validar(){
        
        try{
            Statement qst = miCndb.createStatement();
            String query;
            query = "select * from usuarios where nikuser='"+user.getUser()+"' and contrasena='"+user.getPass()+"' and "
                    + "rol='"+user.getRole()+"'";
            ResultSet resultado;
            resultado = qst.executeQuery(query);
            
            if( resultado.next() ){
                ClasePrincipal.iduser = resultado.getString("iduser");
                user.captInstant();
                ClasePrincipal.usuario_session = resultado.getString("nikuser");
                ClasePrincipal.rol = resultado.getString("rol");
                Statement sent = miCndb.createStatement();
                sent.execute("insert into accesos values ('','"+ClasePrincipal.iduser+"','"+user.getHour()+"','"+user.getDate()+"')");
                //JOptionPane.showMessageDialog(null,"Acceso Correcto");
                
                switch (ClasePrincipal.rol) {
                case "Administrador":
                    {
                        JOptionPane.showMessageDialog(null,"Ha Ingresado como Administrador");
                        MenuPrincipal menu = new MenuPrincipal();
                        menu.setVisible(true);
                        ClasePrincipal.start.dispose();
                        break;
                    }
                case "Cajero":
                        JOptionPane.showMessageDialog(null,"Ha Ingresado como Cajero");
                        InterfazPOS pos = new InterfazPOS();
                        pos.setVisible(true);
                        ClasePrincipal.start.dispose();
                        break;
                case "Soporte Tecnico":
                    {
                        //JOptionPane.showMessageDialog(null,"Soporte");
                        MenuPrincipal menu = new MenuPrincipal();
                        menu.setVisible(true);
                        ClasePrincipal.start.dispose();
                        break;
                    }
                default:
                    JOptionPane.showMessageDialog(null,"No se ha accedido con el Usuario "+user.getUser());
                    break;
            }
                
            }
            else{
                JOptionPane.showMessageDialog(null,"No se ha encontrado el Usuario","Acceso",0);
            }
            
        }catch(SQLException ex){
            //Genera un error del sistema si no se puede acceder o conectar.
            JOptionPane.showMessageDialog(null,"Error de Acceso al Sistema, \n("+ex+")");
        }
    }

    
}
