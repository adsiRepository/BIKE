package controller.componentes;

import controller.ConsultaSQL;
import java.awt.Component;
import java.util.ArrayList;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 *
 * @author Miguel
 */
public class MyCheckCombo extends JCheckBox {

    private final String my_texto;

    public MyCheckCombo(String texto) {
        my_texto = texto;
        this.setText(my_texto);
        this.setSelected(false);
    }

    @Override
    public String getText() {
        return my_texto;
        //return super.getText();
    }
    

    /**
     * COMBOBOX QUE IMPLEMENTA CHECKBOX COMO SUS ITEMS.
     */
    public static class ComboCheckBox extends JComboBox<MyCheckCombo> {

        public static final String MODELO_COMBO_TALLAS = "mis_tallas_articulos";
        
        //private ModeloComboTallas mi_modelo;
        //private ArrayList<MyCheckCombo> my_checks;
        //private int posicion;

        //private MyCheckCombo[] my_checks;
        public ComboCheckBox() {
            super();
            //mi_modelo = new ModeloComboTallas();
            //this.setModel(mi_modelo);
            this.setRenderer(new RenderCheckCombo());
            this.addItem(new MyCheckCombo("1"));
        }

        @Override
        public void setSelectedItem(Object anObject) {
            super.setSelectedItem(anObject);
            MyCheckCombo ch = ((MyCheckCombo) anObject);
            ch.setSelected(!ch.isSelected());
        }

        /*@Override
        public void setSelectedIndex(int anIndex) {
            super.setSelectedIndex(anIndex);
            MyCheckCombo ch = my_checks.get(anIndex);
            ch.setSelected(true);
        }*/

        
        /**
         * LLENA EL COMBO EFECTUANDO LA CONSULTA SQL DE DONDE ADQUIRIRÁ LOS
         * DATOS
         *
         * @param modelo
         * @throws java.lang.Exception
         */
        public void llenarme(String modelo) throws Exception {
            try {
                this.removeAllItems();
                if (modelo.equals(MODELO_COMBO_TALLAS)) {
                    Object[] tallas = ConsultaSQL.tallasUsadas();
                    //my_checks = new ArrayList<>();
                    for(Object ob : tallas){
                        //my_checks.add(new MyCheckCombo(ob.toString()));
                        this.addItem(new MyCheckCombo(ob.toString()));
                    }
                }
            } catch (Exception e) {
                throw e;
            }
        }
        
        /**
         * @return */
        public Object[] obtenerTallasSeleccionadas() {
            ArrayList<Object> tallas_seleccionadas = new ArrayList<>();
            MyCheckCombo ch;
            for (int i = 0; i < this.getItemCount(); i++) {
                ch = this.getItemAt(i);
                if (ch.isSelected()) {
                    tallas_seleccionadas.add(ch.getText());
                }
            }
            return tallas_seleccionadas.toArray();
        }

        /**
         * @param lista
         * @throws java.lang.Exception
         */
        /*public void seleccionarItems(Object lista)throws Exception{
            try {
                Iterator it = ((ArrayList) lista).iterator();

                if (it.hasNext()) {
                    for (MyCheckCombo my_check : my_checks) {
                        while (it.hasNext()) {
                            if (it.next().toString().equals(my_check.getText())) {
                                //my_check.setSelected(true);
                                this.setSelectedItem(my_check);
                            }
                        }
                    } /*for (MyCheckCombo check : my_checks) {
                    String texto = check.getText();
                    if (texto.equals(it.next().toString()))/*getItemAt(i)/ {
                    check.setSelected(true);
                    break;
                    }
                    } getItemCount()/
                }
                //mis_elementos = new ArrayList<>();

            } catch (Exception e) {
                throw new Exception("error seleccionarItems "+e.toString());
            }

        }*/


        /*private class ModeloComboTallas extends AbstractListModel<MyCheckCombo> implements ComboBoxModel<MyCheckCombo> {
            // <editor-fold defaultstate="collapsed" desc="MODELO DEL COMBOBOX DE TALLAS">

            //private Object[] my_checks;
            
            public ModeloComboTallas() {
                //mis_elementos = new Object[]{""};
                my_checks = new ArrayList<>();
                my_checks.add(new MyCheckCombo(""));
            }

            

            @Override
            public int getSize() {
                //return my_checks.length;
                return my_checks.size();
            }

            @Override
            public MyCheckCombo getElementAt(int index) {
                posicion = index;
                return my_checks.get(index);
                //return my_checks[index];
            }

            @Override
            public void setSelectedItem(Object anItem) {
                my_checks.set(posicion, (MyCheckCombo)anItem);
                //mis_elementos[posicion] = anItem;
            }

            @Override
            public Object getSelectedItem() {
                return my_checks.get(posicion);
                //return my_checks[posicion];
            }
// </editor-fold>
        }*/
        
        
        /***/
        private static class RenderCheckCombo implements ListCellRenderer<MyCheckCombo> {
            // <editor-fold defaultstate="collapsed" desc="RENDERIZADOR (O DIBUJANTE) DE LOS ITEMS Y SUS CARACTERISTICAS VISUALES DENTRO DEL COMBOBOX">
            @Override
            public Component getListCellRendererComponent(JList<? extends MyCheckCombo> list, MyCheckCombo value, int index, boolean isSelected, boolean cellHasFocus) {
                return (Component) value;
            }
            // </editor-fold>
        }
    }
}