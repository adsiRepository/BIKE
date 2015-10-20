package controller.componentes;

import model.componentes.ItemDeLista;
import controller.ConsultaSQL;
import view.OrdenProduccion;
import java.awt.Component;
import java.awt.Cursor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.plaf.basic.BasicComboBoxUI;

public class ComboBoxItem extends JComboBox<ItemDeLista> {

    private ArrayList<ItemDeLista> mis_items;
    
    /**
     * Constructor
     * @param combobox
     * constante aplicada en el constructor, dependiente del combobox a construir
     * @throws java.lang.Exception*/
    public ComboBoxItem(String combobox) throws Exception{
        super();
        seleccionaComboBox(combobox);
    }
    
    private void seleccionaComboBox(String combobox) throws Exception{
        if (combobox.equals(OrdenProduccion.COD_CMBOX_ENSAMBLADORES)) {
            //ConsultaSQL.ConsultorBD.obtenerListaEnsambladores().entrySet().stream().map((registro) -> new ItemDeLista(registro.getKey(), registro.getValue())).forEach((item) -> {
              //  this.addItem(item);
            //});
            constructor(ConsultaSQL.ConsultorBD.obtenerListaEnsambladores());
        }
        if (combobox.equals(OrdenProduccion.COD_CMBOX_ARTICULOS)) {
            /*ConsultaSQL.ConsultorBD.obtenerCatalogoArticulos().entrySet().stream().map((registro) -> new ItemDeLista(registro.getKey(), registro.getValue())).forEach((item) -> {
                this.addItem(item);
            });*/
            constructor(ConsultaSQL.ConsultorBD.obtenerCatalogoArticulos());
        }
    }
    
    private void constructor(ArrayList<ItemDeLista> objItems){
        mis_items = /*(ArrayList) */objItems;
        //if (((ArrayList) objItems).size() > 0) {
        if (mis_items.size() > 0) {
            Iterator it = /*((ArrayList) objItems)*/mis_items.iterator();
            while (it.hasNext()) {
                this.addItem((ItemDeLista) it.next());
            }
        }
        else{
            this.addItem(new ItemDeLista());
            this.setUI(new BasicComboBoxUI() {
                @Override
                protected JButton createArrowButton() {
                    return null;
                }
            });
            //this.setKeySelectionManager(keySelectionManager);
        }
        this.setRenderer(new RenderItemComboBox());
        this.setSelectedItem(this.getItemAt(0));
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
    } 

    /**
     * Constructor
     * @param objItems
     * Cuando sus my_items vienen en un ArrayList*/
    /*public ComboBoxItem() {
        super();
        
        constructor();
    }*/
    
    
    // <editor-fold defaultstate="collapsed" desc="MODELO COMBOBOX, NO IMPLEMENTADO">
    /*private class ModeloComboBox extends AbstractListModel<ItemDeLista> implements ComboBoxModel<ItemDeLista> {
        
        private ItemDeLista item_actual;
        //private static int pos_actual;
        
        public ModeloComboBox() {
            my_items = new ArrayList<>();
        }

        public ModeloComboBox(ArrayList<ItemDeLista> itms) {
            my_items = itms;
        }

        @Override
        public int getSize() {
            return my_items.size();
        }

        @Override
        public ItemDeLista getElementAt(int index) {
            return my_items.get(index);
        }

        @Override
        public void setSelectedItem(Object anItem) {
            item_actual = (ItemDeLista)anItem;
        }

        @Override
        public Object getSelectedItem() {
            return item_actual;
        }
        
    }*/// </editor-fold>

    private class RenderItemComboBox extends JTextField implements ListCellRenderer<ItemDeLista>{
        // <editor-fold defaultstate="collapsed" desc="RENDERIZADOR (O DIBUJANTE) DE LOS ITEMS Y SUS CARACTERISTICAS VISUALES DENTRO DEL COMBOBOX">
        public RenderItemComboBox() {
            this.setBorder(null);
        }

        @Override
        public Component getListCellRendererComponent(JList<? extends ItemDeLista> list, ItemDeLista value, int index, boolean isSelected, boolean cellHasFocus) {
            if (value != null) {
                HashMap<String, Object> attrs = ((ItemDeLista) value).getAtributos();
                setText(String.valueOf(attrs.get(ItemDeLista.TEXTO_MOSTRADO)));
                if(value.obtenerCodigoId() == null){
                    setHorizontalAlignment(JTextField.CENTER);
                }
            }
            return this;
        }
        // </editor-fold>
    }
    
    // <editor-fold defaultstate="collapsed" desc=" IMPLEMENTACION DE COMPONENTE (COMO TEXTFIELD) PARA EDITAR LOS VALORES DE UN ITEM DE COMBOBOX ">
  /*  
    private static class EditorComboBox implements ComboBoxEditor {
        
        private ItemDeLista itcombo;
        private JTextField field;
        
        public EditorComboBox() {
        }
        
        @Override
        public Component getEditorComponent() {
            return null;
        }
        
        @Override
        public void setItem(Object anObject) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
        @Override
        public Object getItem() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
        @Override
        public void selectAll() {
        }
        
        @Override
        public void addActionListener(ActionListener l) {
        }
        
        @Override
        public void removeActionListener(ActionListener l) {
        }
        
    }*/

// </editor-fold>

}
