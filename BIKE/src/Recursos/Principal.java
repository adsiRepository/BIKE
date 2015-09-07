
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JTextField;

import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.ImageIcon;

public class Principal extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel jContentPane = null;
    private JLabel jLabel = null;
    private JPanel panel_tablas = null;
    private JScrollPane jScrollPane_1 = null;
    private JTable Table_forma_1 = null;
    /**
     * ************************************
     */
    private final String[] columnas = {"Codigo", "Socio", "Adeuda"};
    private final Object[][] filas = {
        {024, "Carlos", true}, {001, "Josefina", false},
        {045, "Tiburcio", true}, {002, "Andrea", false}
    };
    private final DefaultTableModel modelo = new DefaultTableModel(filas, columnas) {
        @Override
        public Class<?> getColumnClass(int column) {
            return getValueAt(0, column).getClass();
        }
    };
    private final DefaultTableModel modelo_2 = new DefaultTableModel(filas, columnas) {
        @Override
        public Class<?> getColumnClass(int column) {
            return getValueAt(0, column).getClass();
        }
    };
    private JScrollPane jScrollPane_2 = null;
    private JTable Table_forma_2 = null;

    /**
     * This method initializes panel_tablas
     *
     * @return javax.swing.JPanel
     */
    private JPanel getPanel_tablas() {
        if (panel_tablas == null) {
            panel_tablas = new JPanel();
            panel_tablas.setLayout(new BorderLayout());
            panel_tablas.add(getJScrollPane_1(), BorderLayout.NORTH);
            panel_tablas.add(getJScrollPane_2(), BorderLayout.SOUTH);
            panel_tablas.add(jLabel, BorderLayout.CENTER);
        }
        return panel_tablas;
    }

    /**
     * This method initializes jScrollPane_1
     *
     * @return javax.swing.JScrollPane
     */
    private JScrollPane getJScrollPane_1() {
        if (jScrollPane_1 == null) {
            jScrollPane_1 = new JScrollPane();
            jScrollPane_1.setViewportView(getTable_forma_1());
            jScrollPane_1.setPreferredSize(new Dimension(650, 120));
        }
        return jScrollPane_1;
    }

    /**
     * This method initializes Table_forma_1
     *
     * @return javax.swing.JTable
     */
    private JTable getTable_forma_1() {
        if (Table_forma_1 == null) {
            Table_forma_1 = new JTable(modelo);
            JComboBox combo = CreameCombo();//CREO EL COMBO
            TableColumn col = Table_forma_1.getColumnModel().getColumn(0);
            col.setMinWidth(60);
            col.setMaxWidth(60);
            col.setResizable(false);
            col = Table_forma_1.getColumnModel().getColumn(1);
            col.setCellEditor(new DefaultCellEditor(combo));//AGREGO EL COMBO AL CELLEDITOR
            Table_forma_1.setAutoCreateRowSorter(true);
        }
        return Table_forma_1;
    }

    /**
     * This method initializes jScrollPane_2
     *
     * @return javax.swing.JScrollPane
     */
    private JScrollPane getJScrollPane_2() {
        if (jScrollPane_2 == null) {
            jScrollPane_2 = new JScrollPane();
            jScrollPane_2.setViewportView(getTable_forma_2());
            jScrollPane_2.setPreferredSize(new Dimension(650, 120));
        }
        return jScrollPane_2;
    }

    /**
     * This method initializes Table_forma_2
     *
     * @return javax.swing.JTable
     */
    private JTable getTable_forma_2() {
        if (Table_forma_2 == null) {
            Table_forma_2 = new JTable(modelo_2);
            JComboBox combo_2 = CreameCombo();
            TableColumn col = Table_forma_2.getColumnModel().getColumn(0);
            col.setMinWidth(60);
            col.setMaxWidth(60);
            col.setResizable(false);
            col = Table_forma_2.getColumnModel().getColumn(1);
            col.setCellRenderer(new ComboCellRenderer());
            col.setCellEditor(new DefaultCellEditor(combo_2));
            Table_forma_2.setAutoCreateRowSorter(true);
        }
        return Table_forma_2;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        SwingUtilities.invokeLater(() -> {
            Principal thisClass = new Principal();
            thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            thisClass.setVisible(true);
        });
    }

    /**
     * This is the default constructor
     */
    public Principal() {
        super();
        initialize();
    }

    /**
     * This method initializes this
     *
     * @return void
     */
    private void initialize() {
        this.setSize(670, 371);
        this.setContentPane(getJContentPane());
        this.setTitle("JTABLE CON COMBOBOX DOS FORMAS DE HACERLO by----javaface-elblogdelprogramador.com");
        this.setLocationRelativeTo(null);
    }

    /**
     * This method initializes jContentPane
     *
     * @return javax.swing.JPanel
     */
    private JPanel getJContentPane() {
        if (jContentPane == null) {
            jLabel = new JLabel();
            jLabel.setBackground(Color.white);
            jContentPane = new JPanel();
            jContentPane.setLayout(new BorderLayout());
            jContentPane.add(getPanel_tablas(), BorderLayout.CENTER);
        }
        return jContentPane;
    }

    /**
     * ***********************METODOS Y FUNCIONES*********************************
     */
    @SuppressWarnings("unchecked")
    private JComboBox CreameCombo() {
        JComboBox combo = new JComboBox(new String[]{"Hector", "Maria Julia", "Daniel", "Salomon", "Pancrasia", "Prudencia", "Martin", "Daniela", "Mario"}) {
            public void updateUI() {
                super.updateUI();
                setBorder(BorderFactory.createEmptyBorder());
                setUI(new BasicComboBoxUI() {
                    @Override
                    protected JButton createArrowButton() {
                        JButton button = super.createArrowButton();
                        button.setContentAreaFilled(false);
                        button.setBorder(BorderFactory.createEmptyBorder());
                        return button;
                    }
                });
//                
//              
            }
        };
        return combo;
    }

    /**
     * *******************************CREANDO CLASE SEGUNDA FORMA****************************************
     */
    class ComboCellRenderer extends JComboBox implements TableCellRenderer {

        private Color evenColor = new Color(240, 240, 250);
        private JTextField editor;
        private JButton button;

        public ComboCellRenderer() {
            super();
            setEditable(true);
        }

        @Override
        public void updateUI() {
            super.updateUI();
            setBorder(BorderFactory.createEmptyBorder());
            setUI(new BasicComboBoxUI() {
                @Override
                protected JButton createArrowButton() {
                    button = super.createArrowButton();
                    button.setContentAreaFilled(false);
                    button.setBorder(BorderFactory.createEmptyBorder());
                    return button;
                }
            });
            editor = (JTextField) getEditor().getEditorComponent();
            editor.setBorder(BorderFactory.createEmptyBorder());
            editor.setOpaque(true);
            editor.setEditable(false);
        }

        @SuppressWarnings("unchecked")
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            removeAllItems();
            if (isSelected) {
                editor.setForeground(table.getSelectionForeground());
                editor.setBackground(table.getSelectionBackground());
                button.setBackground(table.getSelectionBackground());
            } else {
                editor.setForeground(table.getForeground());
                Color bg = (row % 2 == 0) ? evenColor : table.getBackground();
                editor.setBackground(bg);
                button.setBackground(bg);
            }
            addItem((value == null) ? "" : value.toString());
            return this;
        }

        @Override
        public boolean isOpaque() {
            Color back = getBackground();
            Component p = getParent();
            if (p != null) {
                p = p.getParent();
            }
            boolean colorMatch = back != null && p != null && back.equals(p.getBackground()) && p.isOpaque();
            return !colorMatch && super.isOpaque();
        }

        @Override
        protected void firePropertyChange(String propertyName, Object oldValue, Object newValue) {

        }

        @Override
        public void repaint(long tm, int x, int y, int width, int height) {
        }

        @Override
        public void repaint(Rectangle r) {
        }

        @Override
        public void repaint() {
        }

        @Override
        public void revalidate() {
        }
        //<---- Overridden for performance reasons.
    }

}  //  @jve:decl-index=0:visual-constraint="158,39"
