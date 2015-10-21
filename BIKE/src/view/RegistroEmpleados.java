
package view;

// <editor-fold defaultstate="collapsed" desc="imports">

import controller.componentes.Paneles;
import java.awt.Dimension;
import static view.MenuPrincipal.escritorio;

// </editor-fold>

/**FORMULARIO PARA LA GESTION DE EMPLEADOS.
 *Consigna tu nombre si haces parte de la Autoría de este Documento.
 * @author Miguel
 */
public class RegistroEmpleados extends Paneles.VentanaInterna {

    /**NOMBRE ARCHIVO IMAGEN DE FONDO PARA ESTA VENTANA. Solo nombre sin extension (obligatorio archivos png)*/
    private static final String NOMBRE_MI_IMAGEN_FONDO = "fondo_empleados";
    /***/
    
    /**
     * Creates new form GestionEmpleados
     */
    public RegistroEmpleados() {
        //AQUI AL LLAMAR A SUPER LLAMO AL CONSTRUCTOR DE VENTANA INTERNA QUE RECIBE UN STRING CON EL NOMBRE
        //DE LA IMAGEN DE FONDO UBICADA EN LA CARPETA "mis_imagenes" EN EL DIRECTORIO PRINCIPAL DEL PROYECTO (fuera de todas las carpetas) 
        super(NOMBRE_MI_IMAGEN_FONDO);
        initComponents();
        this.title = "Registro y Control de Empleados";
        this.closable = true;
        this.iconable = true;
        Dimension tamaño_escritorio = escritorio.getSize(), mySpc = this.getSize();
        this.setLocation((tamaño_escritorio.width / 4), ((tamaño_escritorio.height - mySpc.height) / 6));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 394, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 274, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
