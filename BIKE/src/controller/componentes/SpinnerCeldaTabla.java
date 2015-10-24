
package controller.componentes;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

public class SpinnerCeldaTabla extends JSpinner {
        // <editor-fold defaultstate="collapsed" desc="Codigo de la Clase SpinnerCeldaTabla">

        private SpinnerModel myspinnerNumModel;

        /**
         * Constructor Spinner
         */
        public SpinnerCeldaTabla() {
            super();
            myspinnerNumModel = new SpinnerNumberModel(1, 0, 10, 1);
            constructorGeneral();
        }

        /**
         * Constructor Spinner con Modelo
         *
         * @param spinnerModel
         */
        public SpinnerCeldaTabla(SpinnerModel spinnerModel) {
            super();
            if (spinnerModel == null) {
                //throw new NullPointerException("model cannot be null");
                this.myspinnerNumModel = new SpinnerNumberModel(1, 0, 10, 1);// Dato visualizado al inicio repuesto_stock el spinner, minimo, maximo, paso
            } else {
                this.myspinnerNumModel = spinnerModel;
            }
            constructorGeneral();
        }

        public SpinnerCeldaTabla(int valor_fijo) {
            super();
            this.myspinnerNumModel = new SpinnerNumberModel(valor_fijo, 0, valor_fijo, 1);
            constructorGeneral();
            this.setEnabled(false);
        }

        /**
         * Esta clase redefine un spinner haciendo que no se le puedan ingresar
         * letras por ejemplo, o cuaquier configuracion que el programador
         * quiera dar.
         */
        private void constructorGeneral() {
            this.setModel(myspinnerNumModel);
            JSpinner.DefaultEditor editor;
            editor = ((JSpinner.DefaultEditor) this.getEditor());
            editor.getTextField().addKeyListener(new NoLetras());//aqui aplico la clase que implementa los listener del teclado para impedir la insercion de letras
        }

        private static class NoLetras implements KeyListener {

            // <editor-fold defaultstate="collapsed" desc="CONTROLADOR DE TECLAS OPRIMIDAS">
            @Override
            public void keyTyped(KeyEvent e) {
                char typ = e.getKeyChar();
                if ((typ < '0' || typ > '9')) {
                    e.consume();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
            // </editor-fold>
        }
        // </editor-fold>
    }
