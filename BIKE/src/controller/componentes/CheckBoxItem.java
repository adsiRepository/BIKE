//code
package controller.componentes;

import controller.ConsultaSQL;
import model.componentes.ItemDeLista;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 *
 * @author
 */
public class CheckBoxItem extends JCheckBox /*implements ListCellRenderer<ItemOfCollection>*/ {

    private final transient ItemDeLista my_item;

    /**
     * @param item
     */
    public CheckBoxItem(ItemDeLista item) {
        this.my_item = item;
    }

    public ItemDeLista getMyItem() {
        return my_item;
    }

    /*@Override
     public Component getListCellRendererComponent(JList<? extends ItemDeLista> list, ItemDeLista value, int index, boolean isSelected, boolean cellHasFocus) {
     this.setText(value.getAtributos().get(ItemDeLista.TEXTO_MOSTRADO));
     this.setSelected(isSelected);
     this.setBackground(list.getBackground());
     return this;
     }*/
    public static class ListadoCheckBox extends JList<CheckBoxItem> {

        private CheckBoxItem[] items;
        //private ModeloListaArticulos my_data_model;

        /**
         *
         */
        public ListadoCheckBox() {
            //FUENTES
            //http://swing-facil.blogspot.com.co/2012/01/jbutton-jcheckbox-jcombobox-en-jtable.html
            //my_data_model = new ModeloListaArticulos();
            //this.setModel(my_data_model);
            this.setModel(new ModeloListaArticulos());
            
            this.setCellRenderer(new ListCellRenderer<CheckBoxItem>() {
                @Override
                public Component getListCellRendererComponent(JList<? extends CheckBoxItem> list, CheckBoxItem value, int index, boolean isSelected, boolean cellHasFocus) {
                    value.setText(String.valueOf(value.getMyItem().getAtributos().get(ItemDeLista.TEXTO_MOSTRADO)));
                    value.setBackground(isSelected ? getSelectionBackground() : getBackground());
                    value.setForeground(isSelected ? getSelectionForeground() : getForeground());
                    return value;
                }
            });

            this.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    int index = locationToIndex(e.getPoint());
                    if (index != -1) {
                        CheckBoxItem checkbox = getModel().getElementAt(index);
                        checkbox.setSelected(!checkbox.isSelected());
                        if (checkbox.isSelected()) {
                            checkbox.setBackground(getSelectionBackground());
                        } else {
                            checkbox.setBackground(getBackground());
                        }
                        repaint();
                    }
                }
            }
            );
            this.setSelectionMode(DefaultListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        }

        /**
         * @param cod_familia
         * @throws java.lang.Exception
         */
        public void llenarLista(String cod_familia) throws Exception {

            this.removeAll();
            Object[][] componentes = ConsultaSQL.obtenerComponentesFamilia(cod_familia);
            if(componentes != null){
                items = new CheckBoxItem[componentes.length];
                ItemDeLista contenido_item;
                String cod_item;
                HashMap<String, Object> datos_contenido;
                for (int i = 0; i < componentes.length; i++) {
                    cod_item = componentes[i][0].toString();
                    datos_contenido = new HashMap<>();
                    datos_contenido.put(ItemDeLista.TEXTO_MOSTRADO, componentes[i][1].toString());
                    contenido_item = new ItemDeLista(cod_item, datos_contenido);
                    items[i] = new CheckBoxItem(contenido_item);
                }
                this.setModel(new ModeloListaArticulos());
                repaint();
            }
            else{
                items = new CheckBoxItem[]{};
                this.setModel(new ModeloListaArticulos());
            }
        }
        
        /**
         * Metodo para obtener los componentes seleccionados para vincular al articulo en cuestion. 
         * 
         * @return ArrayList con los codigos de los componentes seleccionados
         * @throws java.lang.Exception*/
        public ArrayList<Object> obtenerSeleccionados() throws Exception{
            ArrayList<Object> seleccionados = new ArrayList<>();
            for (CheckBoxItem item : items) {
                if(item.isSelected()){
                    seleccionados.add(item.getMyItem().obtenerCodigoId());
                }
            }
            return seleccionados;
        }
      
        /**
         * @throws java.lang.Exception*/
        public void llenarmeTallas() throws Exception {
            this.removeAll();
            Object[] tallas = ConsultaSQL.tallasUsadas();
            items = new CheckBoxItem[tallas.length];
            HashMap<String, Object> dets;
            for (int i = 0; i < tallas.length; i++) {
                dets = new HashMap<>();
                dets.put(ItemDeLista.TEXTO_MOSTRADO, tallas[i].toString());
                items[i] = new CheckBoxItem(new ItemDeLista(tallas[i].toString(), dets));
            }
            this.setModel(new ModeloListaArticulos());
            repaint();
        }

        /**
         * @param tallas_ ArrayList
         * @throws java.lang.Exception
         */
        public void seleccionarTallas(Object tallas_) throws Exception{
            ArrayList<Object> tallas = (ArrayList<Object>) tallas_;
            for (CheckBoxItem item : items) {
                item.setSelected(false);
                Iterator it = tallas.iterator();
                while (it.hasNext()) {
                    if (item.getMyItem().obtenerCodigoId().equals(it.next())) {
                        item.setSelected(true);
                        break;
                    }
                }
            }
            repaint();
        }

        public void deseleccionarTallas(){
            for (CheckBoxItem item : items) {
                item.setSelected(false);
            }
            repaint();
        }
        
        /**
         * 
         */
        private class ModeloListaArticulos extends AbstractListModel<CheckBoxItem> {

            public ModeloListaArticulos() {
                super();
                //items = new CheckBoxItem[]{};
            }

            @Override
            public int getSize() {
                if(items == null){
                    return 0;
                }
                return items.length;
            }

            @Override
            public CheckBoxItem getElementAt(int index) {
                return items[index];
            }
        }

    }

}
