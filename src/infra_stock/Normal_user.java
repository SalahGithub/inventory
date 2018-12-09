/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infra_stock;

import com.jidesoft.swing.ComboBoxSearchable;
import static com.sun.xml.internal.fastinfoset.alphabet.BuiltInRestrictedAlphabets.table;
import java.awt.Component;
import java.awt.Point;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowSorter;
import javax.swing.SwingConstants;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Salah
 */
public class Normal_user extends javax.swing.JFrame {

    /**
     * Creates new form Normal_user
     */
    Map<String, Integer> nomProd_quanti = new HashMap<>();
    Map<String, Integer> depart_id = new HashMap<>();
    Map<String, String> prod_id = new HashMap<>();
    Map<String, Integer> employe_id = new HashMap<>();
    HashMap<String, Object> mod_prod = new HashMap<String, Object>();
    boolean start = true;

    public void setColumnWidths(JTable table, int... widths) {
        TableColumnModel columnModel = table.getColumnModel();
        for (int i = 0; i < widths.length; i++) {
            if (i < columnModel.getColumnCount()) {
                columnModel.getColumn(i).setPreferredWidth(widths[i]);

            } else {
                break;
            }
        }
    }

    public Normal_user() {
        initComponents();
        start = false;
        setColumnWidths(jtable_produit, 100, 600, 100, 100, 100, 100, 150, 150);
        //fill_combobox("select * from sous", jComboBoxbfa_p91_ajout, "id_sous");
    }

    private void jTextField_stock_c12KeyReleased(java.awt.event.KeyEvent evt) {
        // TODO add your handling code here:
    }

    public void Rechercher_produit() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("id");

        model.addColumn("Designation");
        model.addColumn("Nature");
        model.addColumn("Sous-Ch");
        model.addColumn("Famille");
        model.addColumn("Prix un");
        model.addColumn("Stock");
        model.addColumn("Total TTC");

        try {
            Connection conn1 = Connect.ConnectDB("infra2");
            Statement stm = conn1.createStatement();
            ResultSet rs;
            String selectQuery = "select * from Produit where 1=1 ";

            System.out.println(selectQuery);
            PreparedStatement pst;
            pst = conn1.prepareStatement(selectQuery);

            rs = pst.executeQuery(selectQuery);

            int id1 = 0;
            while (rs.next()) {
                id1 += 1;
                Float prix2 = rs.getFloat("prix");
                int quanti = rs.getInt("quantity");
                System.out.println(rs.getString("designation") + rs.getString("type_C_N")
                        + rs.getString("sous")
                        + rs.getString("type_sous")
                        + rs.getInt("prix")
                        + rs.getInt("quantity"));
                model.addRow(new Object[]{
                    id1,
                    rs.getString("designation"),
                    rs.getString("type_C_N"),
                    rs.getString("sous"),
                    rs.getString("type_sous"),
                    rs.getInt("prix"),
                    rs.getInt("quantity"),
                    prix2 * quanti
                });
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            e.getSQLState();
            e.printStackTrace();
        }

        jtable_produit.setModel(model);
    }

    public void update_table_produits() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("id");

        model.addColumn("Designation");
        model.addColumn("Nature");
        model.addColumn("Sous-Ch");
        model.addColumn("Famille");
        model.addColumn("Prix un");
        model.addColumn("Stock");
        model.addColumn("Total TTC");

        try {
            Connection conn1 = Connect.ConnectDB("infra2");
            Statement stm = conn1.createStatement();
            ResultSet rs = stm.executeQuery("select * from Produit");
            int id = 0;
            while (rs.next()) {
                id += 1;
                Float prix = rs.getFloat("prix");
                int quanti = rs.getInt("quantity");
                model.addRow(new Object[]{
                    id,
                    rs.getString("designation"),
                    rs.getString("type_C_N"),
                    rs.getString("sous"),
                    rs.getString("type_sous"),
                    rs.getInt("prix"),
                    rs.getInt("quantity"),
                    prix * quanti
                });
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            e.getSQLState();
            e.printStackTrace();
        }

        TableColumnModel columnModel = jtable_produit.getColumnModel();
        columnModel.getColumn(3).setPreferredWidth(60);
        jtable_produit.setModel(model);

    }

    public void update_table_alerts() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("id");

        model.addColumn("Designation");
        model.addColumn("Nature");
        model.addColumn("Sous-Ch");
        model.addColumn("Famille");
        model.addColumn("Prix un");
        model.addColumn("Stock");
        model.addColumn("Total TTC");

        try {
            Connection conn1 = Connect.ConnectDB("infra2");
            Statement stm = conn1.createStatement();
            ResultSet rs = stm.executeQuery("select * from produit where quantity<=quant_min");
            int id = 0;
            while (rs.next()) {
                id += 1;
                Float prix = rs.getFloat("prix");
                int quanti = rs.getInt("quantity");
                model.addRow(new Object[]{
                    id,
                    rs.getString("designation"),
                    rs.getString("type_C_N"),
                    rs.getString("sous"),
                    rs.getString("type_sous"),
                    rs.getInt("prix"),
                    rs.getInt("quantity"),
                    prix * quanti
                });
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            e.getSQLState();
            e.printStackTrace();
        }

        TableColumnModel columnModel = jtable_produit.getColumnModel();
        columnModel.getColumn(3).setPreferredWidth(60);
        jTable_alertes.setModel(model);

    }

    public void setPanelEnabled(JPanel panel, Boolean isEnabled) {
        panel.setEnabled(isEnabled);

        Component[] components = panel.getComponents();

        for (Component component : components) {
            if (component instanceof JPanel) {
                setPanelEnabled((JPanel) component, isEnabled);
            }
            component.setEnabled(isEnabled);
        }
    }

    public static void printMap(Map mp) {
        Iterator it = mp.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            System.out.println(pair.getKey() + " = " + pair.getValue());
        }
    }

    public void update_table_produits_rech() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("id");

        model.addColumn("Designation");
        model.addColumn("Nature");
        model.addColumn("Sous-Ch");
        model.addColumn("Famille");
        model.addColumn("Prix un");
        model.addColumn("Stock");
        model.addColumn("Total TTC");

        try {
            Connection conn1 = Connect.ConnectDB("infra2");
            Statement stm = conn1.createStatement();
            ResultSet rs = stm.executeQuery("select * from Produit");
            int id = 0;
            while (rs.next()) {
                id += 1;
                Float prix = rs.getFloat("prix");
                int quanti = rs.getInt("quantity");
                model.addRow(new Object[]{
                    id,
                    rs.getString("designation"),
                    rs.getString("type_C_N"),
                    rs.getString("sous"),
                    rs.getString("type_sous"),
                    rs.getInt("prix"),
                    rs.getInt("quantity"),
                    prix * quanti
                });
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            e.getSQLState();
            e.printStackTrace();
        }

        jTable_rp.setModel(model);
    }

    public void update_table_bfa() {
        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("id");
        model.addColumn("Designation");
        model.addColumn("Nature");
        model.addColumn("Prix un");
        model.addColumn("Stock");
        setColumnWidths(jTable2, 100, 600, 100, 100, 100, 100, 150, 150);
        try {
            Connection conn1 = Connect.ConnectDB("infra2");
            Statement stm = conn1.createStatement();
            ResultSet rs = stm.executeQuery("select * from Produit");
            int id = 0;
            while (rs.next()) {
                id += 1;
                String type = "";
                if (rs.getString("type_C_N").equalsIgnoreCase("C")) {
                    type = "Consommabe";
                } else {
                    type = "Non consommabe";
                }
                model.addRow(new Object[]{
                    id,
                    rs.getString("designation"),
                    type,
                    rs.getInt("prix"),
                    rs.getInt("quantity"),});
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            e.getSQLState();
            e.printStackTrace();
        }

        jTable2.setModel(model);
    }

    public String getDate() {
        Calendar cal = new GregorianCalendar();
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);

        return day + "-" + (month) + "-" + year;
    }

    public void update_table_bfa_c_nc(String c_nc) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("id");
        model.addColumn("Designation");
        model.addColumn("Famille");
        model.addColumn("Prix un");
        model.addColumn("Stock");

        try {
            Connection conn1 = Connect.ConnectDB("infra2");
            Statement stm = conn1.createStatement();
            ResultSet rs = stm.executeQuery("select * from Produit where sous='BFA' and type_C_N='" + c_nc + "'");
            int id = 0;
            while (rs.next()) {
                id += 1;
                String type = rs.getString("type_C_N");

                model.addRow(new Object[]{
                    id,
                    rs.getString("designation"),
                    type,
                    rs.getInt("prix"),
                    rs.getInt("quantity"),});
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            e.getSQLState();
            e.printStackTrace();
        }

        jTable2.setModel(model);
    }

    public void update_table_p90() {
        DefaultTableModel model = new DefaultTableModel();
        setColumnWidths(jTable1, 100, 600, 100, 100, 100, 100, 150, 150);
        model.addColumn("id");
        model.addColumn("Designation");
        model.addColumn("Nature");
        model.addColumn("Famille");
        model.addColumn("Prix un");
        model.addColumn("Stock");

        try {
            Connection conn1 = Connect.ConnectDB("infra2");
            Statement stm = conn1.createStatement();
            ResultSet rs = stm.executeQuery("select * from Produit where sous='P90'");
            int id = 0;
            while (rs.next()) {
                id += 1;
                String type = "";
                if (rs.getString("type_C_N").equalsIgnoreCase("C")) {
                    type = "Consommabe";
                } else {
                    type = "Non consommabe";
                }
                model.addRow(new Object[]{
                    id,
                    rs.getString("designation"),
                    type,
                    rs.getString("type_sous"),
                    rs.getInt("prix"),
                    rs.getInt("quantity"),});
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            e.getSQLState();
            e.printStackTrace();
        }

        jTable1.setModel(model);
    }

    public void update_table_c12() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("id");
        model.addColumn("Numero");
        model.addColumn("Date");
        model.addColumn("Livraison");
        model.addColumn("Sous_ch");
        model.addColumn("Consommable");
        model.addColumn("Departement");
        model.addColumn("Employe");
        model.addColumn("Remarque");

        try {
            Connection conn1 = Connect.ConnectDB("infra2");
            Statement stm = conn1.createStatement();
            ResultSet rs = stm.executeQuery("SELECT  c.`id_c12`, c.`N_12`,c.`date` ,c.`type_c12_L_R`,c.`remarque`,c.`type_B_P`,c.`C_NC`, e.`nom`,e.prenom, d.`nom_dep`\n"
                    + "FROM C12 c  LEFT OUTER JOIN employe e on e.id_employe=c.id_emp\n"
                    + "join departement d on d.id_dep=c.id_dep");
            while (rs.next()) {
                String nom_emp = rs.getString("nom") + "  " + rs.getString("prenom");
                if (nom_emp.equalsIgnoreCase("null  null")) {
                    nom_emp = "";
                }
                model.addRow(new Object[]{
                    rs.getString("id_c12"),
                    rs.getString("N_12"),
                    rs.getString("Date"),
                    rs.getString("type_c12_L_R"),
                    rs.getString("type_B_P"),
                    rs.getString("C_NC"),
                    rs.getString("nom_dep"),
                    nom_emp,
                    rs.getString("remarque")});
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            e.getSQLState();
            e.printStackTrace();
        }

        jTable_c12.setModel(model);
    }

    public void update_table_p90_c_nc(String c_nc) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("id");
        model.addColumn("Designation");
        model.addColumn("Nature");
        model.addColumn("Famille");
        model.addColumn("Prix un");
        model.addColumn("Stock");

        try {
            Connection conn1 = Connect.ConnectDB("infra2");
            Statement stm = conn1.createStatement();
            ResultSet rs = stm.executeQuery("select * from Produit where sous='P90' and type_C_N='" + c_nc + "'");
            int id = 0;
            while (rs.next()) {
                id += 1;
                String type = "";
                if (rs.getString("type_C_N").equalsIgnoreCase("C")) {
                    type = "Consommabe";
                } else {
                    type = "Non consommabe";
                }
                model.addRow(new Object[]{
                    id,
                    rs.getString("designation"),
                    type,
                    rs.getString("type_sous"),
                    rs.getInt("prix"),
                    rs.getInt("quantity"),});
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            e.getSQLState();
            e.printStackTrace();
        }

        jTable1.setModel(model);
    }

    public void update_table_depart() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("id");
        model.addColumn("Nom");
        model.addColumn("Numero");
        model.addColumn("Tel");
        model.addColumn("Responsable");
        model.addColumn("Info");

        try {
            Connection conn1 = Connect.ConnectDB("infra2");
            Statement stm = conn1.createStatement();
            ResultSet rs = stm.executeQuery("select * from Departement");
            int id = 0;
            while (rs.next()) {
                id += 1;

                model.addRow(new Object[]{
                    id,
                    rs.getString("nom_dep"),
                    rs.getInt("num_dep"),
                    rs.getString("num_tel"),
                    rs.getString("nom_respon"),
                    rs.getString("info_dep"),});
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            e.getSQLState();
            e.printStackTrace();
        }

        jTable_departement.setModel(model);
    }

    public void fill_combobox(String query, JComboBox c, String col_name) {
        Connection conn1 = Connect.ConnectDB("infra2");

        ResultSet rs;
        try {
            Statement stm = conn1.createStatement();
            rs = stm.executeQuery(query);
            c.removeAllItems();
            while (rs.next()) {
                c.addItem(rs.getString(col_name));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Normal_user.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel10 = new javax.swing.JPanel();
        jDialog_ajouter_c12 = new javax.swing.JDialog();
        jPanel12 = new javax.swing.JPanel();
        jTextField_numero_c12 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jComboBox_depar_c12 = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
        jDateChooser_ajouter_c12 = new com.toedter.calendar.JDateChooser();
        jLabel17 = new javax.swing.JLabel();
        jComboBox_bfa_c12 = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jComboBox_livr_re = new javax.swing.JComboBox<>();
        jComboBox_c_nc_ajouter_c12 = new javax.swing.JComboBox<>();
        jComboBox_emp_ajout_c12 = new javax.swing.JComboBox<>();
        jScrollPane7 = new javax.swing.JScrollPane();
        jtable_ajouter_produits = new  javax.swing.JTable() ;
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jLabel42 = new javax.swing.JLabel();
        jPanel_prods_c12 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jComboBox_produit_c12 = new javax.swing.JComboBox<>();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jTextField_stock_c12 = new javax.swing.JTextField();
        jButton9 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jTextField_deman_c12 = new javax.swing.JTextField();
        jTextField__delivre_c12 = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jcheck_garder_fenetre = new javax.swing.JCheckBox();
        jLabel_tot_c12 = new javax.swing.JLabel();
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        longConverter1 = new com.jidesoft.converter.LongConverter();
        basicJideLabelUI1 = new com.jidesoft.plaf.basic.BasicJideLabelUI();
        spinnerPointModel1 = new com.jidesoft.spinner.SpinnerPointModel();
        jDialog_afficher_c12 = new javax.swing.JDialog();
        jPanel22 = new javax.swing.JPanel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        jTextArea_rem_c12 = new javax.swing.JTextArea();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel_num_c12 = new javax.swing.JLabel();
        jLabel_dep_c12 = new javax.swing.JLabel();
        jLabel_date_c12 = new javax.swing.JLabel();
        jLabel_sous_c12 = new javax.swing.JLabel();
        jLabel_c_nc_c12 = new javax.swing.JLabel();
        jLabel_employe_c12 = new javax.swing.JLabel();
        jLabel_livr_c12 = new javax.swing.JLabel();
        jScrollPane11 = new javax.swing.JScrollPane();
        jtable_aficher_c12 = new  javax.swing.JTable() ;
        jButton15 = new javax.swing.JButton();
        jLabel61 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jLabel_total_c12 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtable_produit = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jTextField_desig_ajout = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jComboBox_c_nc_ajout = new javax.swing.JComboBox<>();
        jComboBoxbfa_p91_ajout = new javax.swing.JComboBox<>();
        jComboBox_famille_ajout = new javax.swing.JComboBox<>();
        jSpinner_prix_ajout = new javax.swing.JSpinner();
        jSpinner_stock_ajout = new javax.swing.JSpinner();
        jLabel20 = new javax.swing.JLabel();
        jCheckBox_retournable_ajouter = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        jSpinner_quanti_min = new javax.swing.JSpinner();
        jLabel_quant_min = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton_ajouter = new javax.swing.JButton();
        jLabel40 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jRadioButton_less_stock = new javax.swing.JRadioButton();
        jLabel38 = new javax.swing.JLabel();
        jComboBoxbfa_p9_rp = new javax.swing.JComboBox<>();
        jComboBox_c_nc_r_p = new javax.swing.JComboBox<>();
        jRadioButton_eq = new javax.swing.JRadioButton();
        jComboBox_famille_rp = new javax.swing.JComboBox<>();
        jLabel37 = new javax.swing.JLabel();
        jRadioButton_greater_stock = new javax.swing.JRadioButton();
        jRadioButton_great = new javax.swing.JRadioButton();
        jRadioButton_eq_stock = new javax.swing.JRadioButton();
        jLabel35 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jRadioButton_less = new javax.swing.JRadioButton();
        jLabel36 = new javax.swing.JLabel();
        jButton13 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jTextField_prix_rp = new javax.swing.JTextField();
        jTextField_stock_rp1 = new javax.swing.JTextField();
        jPanel24 = new javax.swing.JPanel();
        jPanel25 = new javax.swing.JPanel();
        jLabel64 = new javax.swing.JLabel();
        txt_designation_editer = new javax.swing.JTextField();
        jLabel65 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        jComboBox_c_nc_edi = new javax.swing.JComboBox<>();
        jComboBoxbfa_ped = new javax.swing.JComboBox<>();
        jComboBox_famille_ed = new javax.swing.JComboBox<>();
        jSpinner_stock_ed = new javax.swing.JSpinner();
        jSpinner_prix_ed = new javax.swing.JSpinner();
        jButton17 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        jLabel71 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTable_rp = new javax.swing.JTable();
        jPanel18 = new javax.swing.JPanel();
        jButton14 = new javax.swing.JButton();
        jLabel33 = new javax.swing.JLabel();
        txt_designation_recher = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        jComboBoxbfa_p9_recher = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jcombop90 = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel11 = new javax.swing.JPanel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable_c12 = new javax.swing.JTable(){
            public boolean isCellEditable(int d, int c){
                return false;
            }
        };
        jButton7 = new javax.swing.JButton();
        jPanel19 = new javax.swing.JPanel();
        jLabel51 = new javax.swing.JLabel();
        jPanel21 = new javax.swing.JPanel();
        jLabel43 = new javax.swing.JLabel();
        jTextField_num_rc12 = new javax.swing.JTextField();
        jComboBox_bfarc121 = new javax.swing.JComboBox<>();
        jButton19 = new javax.swing.JButton();
        jLabel52 = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        jLabel48 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jComboBox_emplo_rc12 = new javax.swing.JComboBox<>();
        jComboBox_prod_rc12 = new javax.swing.JComboBox<>();
        jLabel50 = new javax.swing.JLabel();
        jComboBox_livr_rc12 = new javax.swing.JComboBox<>();
        jYearChooser1 = new com.toedter.calendar.JYearChooser();
        jLabel46 = new javax.swing.JLabel();
        jMonthChooser1 = new com.toedter.calendar.JMonthChooser();
        jLabel49 = new javax.swing.JLabel();
        jComboBox_bfa_rc12 = new javax.swing.JComboBox<>();
        jButton18 = new javax.swing.JButton();
        jComboBox_depar_rc12 = new javax.swing.JComboBox<>();
        jComboBox_c_nc_rc12 = new javax.swing.JComboBox<>();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable_departement = new javax.swing.JTable();
        jPanel13 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jTextField_nom_dep = new javax.swing.JTextField();
        jTextField_num_dep = new javax.swing.JTextField();
        jTextField_tel_dep = new javax.swing.JTextField();
        jTextField_resp_dep = new javax.swing.JTextField();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTextArea_info_dep = new javax.swing.JTextArea();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jPanel23 = new javax.swing.JPanel();
        jScrollPane12 = new javax.swing.JScrollPane();
        jTable_alertes = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jDialog_ajouter_c12.setMinimumSize(new java.awt.Dimension(920, 600));
        jDialog_ajouter_c12.setResizable(false);

        jPanel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel15.setText("Numéro");

        jLabel16.setText("Departement");

        jLabel17.setText("Date");

        jLabel18.setText("Sous CH");

        jLabel19.setText("Consommable");

        jLabel30.setText("Employe");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane8.setViewportView(jTextArea1);

        jLabel31.setText("Remarque:");

        jLabel32.setText("Livraison");

        jComboBox_livr_re.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Livraison", "Retourne" }));

        jComboBox_c_nc_ajouter_c12.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "C", "NC" }));
        jComboBox_c_nc_ajouter_c12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_c_nc_ajouter_c12ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel31)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane8)
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel15)
                                    .addComponent(jLabel16)
                                    .addComponent(jLabel17)
                                    .addComponent(jLabel18)
                                    .addComponent(jLabel19)
                                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel32))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jComboBox_bfa_c12, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jDateChooser_ajouter_c12, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
                                    .addComponent(jComboBox_depar_c12, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jTextField_numero_c12)
                                    .addComponent(jComboBox_livr_re, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBox_c_nc_ajouter_c12, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBox_emp_ajout_c12, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(7, 7, 7)))
                        .addContainerGap(19, Short.MAX_VALUE))))
        );

        jPanel12Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jComboBox_bfa_c12, jComboBox_depar_c12, jDateChooser_ajouter_c12, jTextField_numero_c12});

        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(jTextField_numero_c12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addComponent(jComboBox_depar_c12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDateChooser_ajouter_c12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18)
                    .addComponent(jComboBox_bfa_c12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(jComboBox_c_nc_ajouter_c12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel30)
                    .addComponent(jComboBox_emp_ajout_c12, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(jComboBox_livr_re, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel31)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(47, Short.MAX_VALUE))
        );

        jtable_ajouter_produits.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Produit", "Demandé", "Délivré"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jtable_ajouter_produits.setMinimumSize(new java.awt.Dimension(400, 500));
        jScrollPane7.setViewportView(jtable_ajouter_produits);

        jButton10.setText("Ajouter C12");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton11.setText("Annuler");

        jLabel42.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel42.setText("Info C12");

        jPanel_prods_c12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel26.setText("Produit:");

        jLabel27.setText("Demandé:");

        ComboBoxSearchable searchable = new ComboBoxSearchable(jComboBox_produit_c12);
        searchable.setShowPopupDuringSearching(true);
        searchable.setSearchLabel("Rechercher: ");
        jComboBox_produit_c12.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox_produit_c12ItemStateChanged(evt);
            }
        });

        jLabel28.setText("Délivré:");

        jLabel29.setText("Stock:");

        jTextField_stock_c12.setEditable(false);
        jTextField_stock_c12.setText("20");

        jButton9.setText("Supprimer");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton8.setText("Ajouter");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jTextField_deman_c12.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField_deman_c12KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_deman_c12KeyTyped(evt);
            }
        });

        jTextField__delivre_c12.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField__delivre_c12KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField__delivre_c12KeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel_prods_c12Layout = new javax.swing.GroupLayout(jPanel_prods_c12);
        jPanel_prods_c12.setLayout(jPanel_prods_c12Layout);
        jPanel_prods_c12Layout.setHorizontalGroup(
            jPanel_prods_c12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_prods_c12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_prods_c12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_prods_c12Layout.createSequentialGroup()
                        .addComponent(jLabel26)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox_produit_c12, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                        .addComponent(jLabel29)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField_stock_c12, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel_prods_c12Layout.createSequentialGroup()
                        .addComponent(jLabel27)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField_deman_c12, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel28)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField__delivre_c12, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton9)))
                .addGap(25, 25, 25))
        );
        jPanel_prods_c12Layout.setVerticalGroup(
            jPanel_prods_c12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_prods_c12Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel_prods_c12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_prods_c12Layout.createSequentialGroup()
                        .addGroup(jPanel_prods_c12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton8)
                            .addComponent(jTextField_stock_c12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel29))
                        .addGap(18, 18, 18)
                        .addComponent(jButton9))
                    .addGroup(jPanel_prods_c12Layout.createSequentialGroup()
                        .addGroup(jPanel_prods_c12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel26)
                            .addComponent(jComboBox_produit_c12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel_prods_c12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel27)
                            .addComponent(jLabel28)
                            .addComponent(jTextField__delivre_c12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_deman_c12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(23, 23, 23))
        );

        jPanel_prods_c12Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jComboBox_produit_c12, jTextField_stock_c12});

        jLabel44.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel44.setText("Produits C12");

        jLabel62.setText("Total= ");

        jcheck_garder_fenetre.setText("Garder la fenetre ouverte");

        javax.swing.GroupLayout jDialog_ajouter_c12Layout = new javax.swing.GroupLayout(jDialog_ajouter_c12.getContentPane());
        jDialog_ajouter_c12.getContentPane().setLayout(jDialog_ajouter_c12Layout);
        jDialog_ajouter_c12Layout.setHorizontalGroup(
            jDialog_ajouter_c12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog_ajouter_c12Layout.createSequentialGroup()
                .addGroup(jDialog_ajouter_c12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDialog_ajouter_c12Layout.createSequentialGroup()
                        .addGap(91, 91, 91)
                        .addComponent(jLabel42)
                        .addGap(125, 125, 125))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialog_ajouter_c12Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jDialog_ajouter_c12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialog_ajouter_c12Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel44)
                        .addGap(257, 257, 257))
                    .addGroup(jDialog_ajouter_c12Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jDialog_ajouter_c12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jDialog_ajouter_c12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jDialog_ajouter_c12Layout.createSequentialGroup()
                                    .addComponent(jcheck_garder_fenetre)
                                    .addGap(61, 61, 61)
                                    .addComponent(jButton10)
                                    .addGap(31, 31, 31)
                                    .addComponent(jButton11))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialog_ajouter_c12Layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel62)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel_tot_c12, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(227, 227, 227)))
                            .addGroup(jDialog_ajouter_c12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jScrollPane7)
                                .addComponent(jPanel_prods_c12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(0, 88, Short.MAX_VALUE))))
        );
        jDialog_ajouter_c12Layout.setVerticalGroup(
            jDialog_ajouter_c12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog_ajouter_c12Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jDialog_ajouter_c12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel42)
                    .addComponent(jLabel44))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jDialog_ajouter_c12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jDialog_ajouter_c12Layout.createSequentialGroup()
                        .addComponent(jPanel_prods_c12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jDialog_ajouter_c12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel62)
                    .addComponent(jLabel_tot_c12, javax.swing.GroupLayout.DEFAULT_SIZE, 19, Short.MAX_VALUE))
                .addGap(2, 2, 2)
                .addGroup(jDialog_ajouter_c12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton10)
                    .addComponent(jButton11)
                    .addComponent(jcheck_garder_fenetre))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jDialog_afficher_c12.setMinimumSize(new java.awt.Dimension(850, 550));

        jPanel22.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel53.setText("Numéro");

        jLabel54.setText("Departement");

        jLabel55.setText("Date");

        jLabel56.setText("Sous CH");

        jLabel57.setText("Consommable");

        jLabel58.setText("Employe");

        jTextArea_rem_c12.setEditable(false);
        jTextArea_rem_c12.setColumns(20);
        jTextArea_rem_c12.setRows(5);
        jScrollPane10.setViewportView(jTextArea_rem_c12);

        jLabel59.setText("Remarque:");

        jLabel60.setText("Livraison");

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel54)
                            .addComponent(jLabel53)
                            .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel57)
                            .addComponent(jLabel60))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel_date_c12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel_sous_c12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel_c_nc_c12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel_employe_c12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel_livr_c12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel_num_c12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel_dep_c12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)))
                    .addComponent(jLabel56)
                    .addComponent(jLabel55)
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel59))
                .addGap(35, 51, Short.MAX_VALUE))
        );

        jPanel22Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel_c_nc_c12, jLabel_date_c12, jLabel_dep_c12, jLabel_employe_c12, jLabel_livr_c12, jLabel_num_c12, jLabel_sous_c12});

        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel22Layout.createSequentialGroup()
                                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel22Layout.createSequentialGroup()
                                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel53)
                                            .addComponent(jLabel_num_c12, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(17, 17, 17)
                                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel54)
                                            .addComponent(jLabel_dep_c12, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(12, 12, 12)
                                        .addComponent(jLabel55))
                                    .addComponent(jLabel_date_c12, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(17, 17, 17)
                                .addComponent(jLabel56))
                            .addComponent(jLabel_sous_c12, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel57)
                            .addComponent(jLabel_c_nc_c12, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addComponent(jLabel58))
                    .addComponent(jLabel_employe_c12, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel60)
                    .addComponent(jLabel_livr_c12, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel59)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(47, Short.MAX_VALUE))
        );

        jtable_aficher_c12.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jtable_aficher_c12.setMinimumSize(new java.awt.Dimension(400, 500));
        jScrollPane11.setViewportView(jtable_aficher_c12);

        jButton15.setText("Fermer");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        jLabel61.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel61.setText("Info C12");

        jLabel66.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel66.setText("Produits C12");

        jLabel63.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel63.setText("Total= ");

        jLabel_total_c12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        javax.swing.GroupLayout jDialog_afficher_c12Layout = new javax.swing.GroupLayout(jDialog_afficher_c12.getContentPane());
        jDialog_afficher_c12.getContentPane().setLayout(jDialog_afficher_c12Layout);
        jDialog_afficher_c12Layout.setHorizontalGroup(
            jDialog_afficher_c12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog_afficher_c12Layout.createSequentialGroup()
                .addGroup(jDialog_afficher_c12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDialog_afficher_c12Layout.createSequentialGroup()
                        .addGap(91, 91, 91)
                        .addComponent(jLabel61)
                        .addGap(125, 125, 125))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialog_afficher_c12Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jDialog_afficher_c12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialog_afficher_c12Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel66)
                        .addGap(257, 257, 257))
                    .addGroup(jDialog_afficher_c12Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 548, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(22, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialog_afficher_c12Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel63)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel_total_c12, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jButton15)
                .addGap(86, 86, 86))
        );
        jDialog_afficher_c12Layout.setVerticalGroup(
            jDialog_afficher_c12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog_afficher_c12Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jDialog_afficher_c12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel61)
                    .addComponent(jLabel66))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jDialog_afficher_c12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jDialog_afficher_c12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton15)
                    .addGroup(jDialog_afficher_c12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel63)
                        .addComponent(jLabel_total_c12, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTabbedPane1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        jTabbedPane1.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jTabbedPane1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane1StateChanged(evt);
            }
        });
        jTabbedPane1.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                jTabbedPane1CaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });

        jtable_produit.setAutoCreateRowSorter(true);
        jtable_produit.setGridColor(new java.awt.Color(255, 0, 0));
        jtable_produit.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jtable_produit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtable_produitMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jtable_produit);

        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel9.setText("Designation");

        jLabel10.setText("Sous_CH");

        jLabel11.setText("Nature C_NC");

        jLabel12.setText("Prix");

        jLabel13.setText("Famille");

        jLabel14.setText("Stock");

        jComboBoxbfa_p91_ajout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxbfa_p91_ajoutActionPerformed(evt);
            }
        });

        jSpinner_prix_ajout.setModel(new javax.swing.SpinnerNumberModel(0.0f, 0.0f, null, 1.0f));

        jSpinner_stock_ajout.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));

        jLabel20.setText("Retournable");

        jCheckBox3.setText("Suivre quantité       ");
        jCheckBox3.setHorizontalTextPosition(SwingConstants.LEFT);
        jCheckBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox3ActionPerformed(evt);
            }
        });

        jSpinner_quanti_min.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        jSpinner_quanti_min.setEnabled(false);

        jLabel_quant_min.setText("quantité min");
        jLabel_quant_min.setEnabled(false);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jComboBox_c_nc_ajout, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel14)
                            .addComponent(jLabel13)
                            .addComponent(jLabel12)
                            .addComponent(jLabel20))
                        .addGap(40, 40, 40)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSpinner_stock_ajout, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jComboBoxbfa_p91_ajout, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBox_famille_ajout, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jSpinner_prix_ajout, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jCheckBox_retournable_ajouter)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jCheckBox3)
                                .addGap(20, 20, 20)
                                .addComponent(jLabel_quant_min)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSpinner_quanti_min, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(43, 43, 43)
                                .addComponent(jTextField_desig_ajout, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel9Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jComboBox_c_nc_ajout, jTextField_desig_ajout});

        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jTextField_desig_ajout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jComboBox_c_nc_ajout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jComboBoxbfa_p91_ajout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jSpinner_prix_ajout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox_famille_ajout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addGap(10, 10, 10)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jSpinner_stock_ajout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(jCheckBox_retournable_ajouter))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox3)
                    .addComponent(jSpinner_quanti_min, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_quant_min))
                .addContainerGap(10, Short.MAX_VALUE))
        );

        jButton3.setText("Annuler");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton_ajouter.setText("Sauvegarder");
        jButton_ajouter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ajouterActionPerformed(evt);
            }
        });

        jLabel40.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel40.setText("Ajouter produit");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(114, 114, 114)
                .addComponent(jButton_ajouter)
                .addGap(18, 18, 18)
                .addComponent(jButton3)
                .addContainerGap(18, Short.MAX_VALUE))
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addComponent(jLabel40)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel40)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton_ajouter))
                .addContainerGap())
        );

        jButton4.setText("Supprimer");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 878, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(jButton4)
                        .addContainerGap())))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 571, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(273, 273, 273)))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Ajouter produit", jPanel4);

        jPanel17.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        buttonGroup2.add(jRadioButton_less_stock);
        jRadioButton_less_stock.setText("<");

        jLabel38.setText("Stock");

        jComboBoxbfa_p9_rp.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "BFA", "P90" }));

        jComboBox_c_nc_r_p.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "C", "NC" }));

        buttonGroup1.add(jRadioButton_eq);
        jRadioButton_eq.setText("=");
        jRadioButton_eq.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton_eqActionPerformed(evt);
            }
        });

        jLabel37.setText("Famille");

        buttonGroup2.add(jRadioButton_greater_stock);
        jRadioButton_greater_stock.setText(">");

        buttonGroup1.add(jRadioButton_great);
        jRadioButton_great.setText(">");

        buttonGroup2.add(jRadioButton_eq_stock);
        jRadioButton_eq_stock.setSelected(true);
        jRadioButton_eq_stock.setText("=");
        jRadioButton_eq_stock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton_eq_stockActionPerformed(evt);
            }
        });

        jLabel35.setText("Nature C_NC");

        jLabel34.setText("Sous_CH");

        buttonGroup1.add(jRadioButton_less);
        jRadioButton_less.setText("<");

        jLabel36.setText("Prix");

        jButton13.setText("Rechercher");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jButton12.setText("Annuler");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel34)
                    .addComponent(jLabel36)
                    .addComponent(jLabel37)
                    .addComponent(jLabel35)
                    .addComponent(jLabel38))
                .addGap(27, 27, 27)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(jButton13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton12)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(jRadioButton_eq_stock)
                        .addGap(29, 29, 29)
                        .addComponent(jRadioButton_less_stock)
                        .addGap(30, 30, 30)
                        .addComponent(jRadioButton_greater_stock)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel17Layout.createSequentialGroup()
                                .addComponent(jRadioButton_eq)
                                .addGap(30, 30, 30)
                                .addComponent(jRadioButton_less)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jRadioButton_great))
                            .addGroup(jPanel17Layout.createSequentialGroup()
                                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField_stock_rp1, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jComboBox_c_nc_r_p, 0, 158, Short.MAX_VALUE)
                                        .addComponent(jComboBoxbfa_p9_rp, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jComboBox_famille_rp, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jTextField_prix_rp)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(85, 85, 85))))
        );

        jPanel17Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jComboBox_c_nc_r_p, jComboBox_famille_rp, jComboBoxbfa_p9_rp});

        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxbfa_p9_rp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel34))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox_c_nc_r_p, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel35))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(jTextField_prix_rp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton_eq)
                    .addComponent(jRadioButton_less)
                    .addComponent(jRadioButton_great))
                .addGap(15, 15, 15)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox_famille_rp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel37))
                .addGap(18, 18, 18)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38)
                    .addComponent(jTextField_stock_rp1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton_eq_stock)
                    .addComponent(jRadioButton_less_stock)
                    .addComponent(jRadioButton_greater_stock))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton13)
                    .addComponent(jButton12))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel25.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel64.setText("Designation");

        jLabel65.setText("Sous_CH");

        jLabel67.setText("Nature C_NC");

        jLabel68.setText("Prix");

        jLabel69.setText("Famille");

        jLabel70.setText("Stock");

        jComboBoxbfa_ped.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxbfa_pedActionPerformed(evt);
            }
        });

        jSpinner_stock_ed.setModel(new javax.swing.SpinnerNumberModel(0.0f, 0.0f, null, 1.0f));
        jSpinner_stock_ed.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinner_stock_edStateChanged(evt);
            }
        });

        jSpinner_prix_ed.setModel(new javax.swing.SpinnerNumberModel(0.0f, 0.0f, null, 1.0f));

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel65)
                            .addComponent(jLabel68)
                            .addComponent(jLabel69)
                            .addComponent(jLabel70))
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel25Layout.createSequentialGroup()
                                .addGap(56, 56, 56)
                                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jComboBoxbfa_ped, 0, 121, Short.MAX_VALUE)
                                    .addComponent(jComboBox_famille_ed, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jSpinner_stock_ed))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel25Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jSpinner_prix_ed, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel64)
                            .addComponent(jLabel67))
                        .addGap(36, 36, 36)
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_designation_editer, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox_c_nc_edi, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        jPanel25Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jComboBox_c_nc_edi, jComboBox_famille_ed, jComboBoxbfa_ped, jSpinner_prix_ed, jSpinner_stock_ed, txt_designation_editer});

        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel64)
                    .addComponent(txt_designation_editer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel67)
                    .addComponent(jComboBox_c_nc_edi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel65)
                    .addComponent(jComboBoxbfa_ped, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel68)
                    .addComponent(jSpinner_prix_ed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel69)
                    .addComponent(jComboBox_famille_ed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel70)
                    .addComponent(jSpinner_stock_ed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton17.setText("Annuler");

        jButton20.setText("Sauvegarder");
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });

        jLabel71.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel71.setText("Modifier produit");

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel24Layout.createSequentialGroup()
                                .addComponent(jButton20)
                                .addGap(18, 18, 18)
                                .addComponent(jButton17))
                            .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addGap(95, 95, 95)
                        .addComponent(jLabel71)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addComponent(jLabel71)
                .addGap(5, 5, 5)
                .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton17)
                    .addComponent(jButton20))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 25, Short.MAX_VALUE))
        );

        jTable_rp.setAutoCreateRowSorter(true);
        jTable_rp.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTable_rp.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable_rp.setIntercellSpacing(new java.awt.Dimension(2, 2));
        jTable_rp.setRowHeight(20);
        jTable_rp.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable_rp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_rpMouseClicked(evt);
            }
        });
        jTable_rp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTable_rpKeyTyped(evt);
            }
        });
        jScrollPane9.setViewportView(jTable_rp);

        jPanel18.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jButton14.setText("Rechercher");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jLabel33.setText("Designation");

        txt_designation_recher.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        jLabel39.setText("Sous_CH");

        jComboBoxbfa_p9_recher.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "BFA", "P90" }));

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton14)
                    .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel18Layout.createSequentialGroup()
                            .addComponent(jLabel39)
                            .addGap(56, 56, 56)
                            .addComponent(jComboBoxbfa_p9_recher, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel18Layout.createSequentialGroup()
                            .addComponent(jLabel33)
                            .addGap(43, 43, 43)
                            .addComponent(txt_designation_recher, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel33)
                    .addComponent(txt_designation_recher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39)
                    .addComponent(jComboBoxbfa_p9_recher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton14)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 866, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane9))
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Recherche produit", jPanel14);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jcombop90.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "Consommable", "Non Consommable" }));
        jcombop90.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcombop90ActionPerformed(evt);
            }
        });

        jLabel1.setText("Afficher");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcombop90, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 580, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jcombop90, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("P90", jPanel1);

        jPanel2.setPreferredSize(new java.awt.Dimension(1514, 670));

        jTable2.setAutoCreateRowSorter(true);
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "Consommable", "Non Consommable" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jLabel2.setText("Afficher");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 962, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(53, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 558, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jTabbedPane1.addTab("BFA", jPanel2);

        jTable_c12.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable_c12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_c12MouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(jTable_c12);

        jButton7.setText("Ajouter C12");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jLabel51.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel51.setText("Rechercher C12");

        jPanel21.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel43.setText("Numero");

        jButton19.setText("Rechercher");
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });

        jLabel52.setText("Sous CH");

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel43)
                            .addComponent(jLabel52))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField_num_rc12, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox_bfarc121, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                        .addGap(140, 140, 140)
                        .addComponent(jButton19)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField_num_rc12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel43, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox_bfarc121, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel52))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton19)
                .addContainerGap())
        );

        jPanel20.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel20.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel48.setText("Employe");
        jPanel20.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 206, 42, -1));

        jLabel45.setText("Departement");
        jPanel20.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 15, -1, -1));

        jLabel47.setText("C / NC");
        jPanel20.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 162, -1, -1));

        jPanel20.add(jComboBox_emplo_rc12, new org.netbeans.lib.awtextra.AbsoluteConstraints(103, 202, 156, -1));

        jComboBox_prod_rc12.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel20.add(jComboBox_prod_rc12, new org.netbeans.lib.awtextra.AbsoluteConstraints(103, 282, 161, -1));

        jLabel50.setText("Produit");
        jPanel20.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, -1, -1));

        jComboBox_livr_rc12.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "Livraison", "Retourne" }));
        jComboBox_livr_rc12.setSelectedIndex(1);
        jPanel20.add(jComboBox_livr_rc12, new org.netbeans.lib.awtextra.AbsoluteConstraints(103, 240, 156, -1));

        jYearChooser1.setEnabled(false);
        jPanel20.add(jYearChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(103, 85, 73, -1));

        jLabel46.setText("Sous CH");
        jPanel20.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 121, -1, -1));

        jMonthChooser1.setLocale(Locale.FRANCE);
        jMonthChooser1.setEnabled(false);
        jPanel20.add(jMonthChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(103, 54, 83, -1));

        jLabel49.setText("Livraison");
        jPanel20.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 238, -1, -1));

        jPanel20.add(jComboBox_bfa_rc12, new org.netbeans.lib.awtextra.AbsoluteConstraints(103, 123, 156, -1));

        jButton18.setText("Rechercher");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });
        jPanel20.add(jButton18, new org.netbeans.lib.awtextra.AbsoluteConstraints(177, 308, -1, -1));

        jPanel20.add(jComboBox_depar_rc12, new org.netbeans.lib.awtextra.AbsoluteConstraints(103, 12, 122, -1));

        jComboBox_c_nc_rc12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_c_nc_rc12ActionPerformed(evt);
            }
        });
        jPanel20.add(jComboBox_c_nc_rc12, new org.netbeans.lib.awtextra.AbsoluteConstraints(103, 164, 156, -1));

        jCheckBox1.setText("Mois");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });
        jPanel20.add(jCheckBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 54, -1, -1));

        jCheckBox2.setText("Anné");
        jCheckBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox2ActionPerformed(evt);
            }
        });
        jPanel20.add(jCheckBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 80, -1, -1));

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addComponent(jLabel51)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel20, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)
            .addComponent(jPanel21, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addComponent(jLabel51)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(79, Short.MAX_VALUE))
        );

        jButton1.setText("supprimer");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("modifier");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jButton7)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2)
                        .addGap(24, 24, 24)
                        .addComponent(jButton1))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 874, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton7)
                            .addComponent(jButton1)
                            .addComponent(jButton2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane5))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jTabbedPane1.addTab("C12", jPanel3);

        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jTable_departement.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane4.setViewportView(jTable_departement);

        jPanel13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel21.setText("Nom");

        jLabel22.setText("Numero");

        jLabel23.setText("Tel");

        jLabel24.setText("Responsable");

        jLabel25.setText("Info");

        jTextArea_info_dep.setColumns(20);
        jTextArea_info_dep.setRows(5);
        jScrollPane6.setViewportView(jTextArea_info_dep);

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addComponent(jLabel21)
                                .addGap(57, 57, 57)
                                .addComponent(jTextField_nom_dep))
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel22)
                                    .addComponent(jLabel23))
                                .addGap(41, 41, 41)
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField_num_dep)
                                    .addComponent(jTextField_tel_dep))))
                        .addGap(20, 20, 20))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addGap(59, 59, 59)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel24)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField_resp_dep)
                        .addGap(20, 20, 20))))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_nom_dep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21))
                .addGap(5, 5, 5)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel22)
                    .addComponent(jTextField_num_dep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel23)
                    .addComponent(jTextField_tel_dep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(jTextField_resp_dep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jButton5.setText("Ajouter");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("Annuler");

        jButton16.setText("Supprimer");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(0, 115, Short.MAX_VALUE)
                        .addComponent(jButton5)
                        .addGap(18, 18, 18)
                        .addComponent(jButton6))
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 910, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton16))
                .addGap(13, 13, 13))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton5)
                            .addComponent(jButton6)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jButton16)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(182, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Departements", jPanel5);

        jTable_alertes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane12.setViewportView(jTable_alertes);

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                .addContainerGap(236, Short.MAX_VALUE)
                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 914, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(74, 74, 74))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(129, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Alertes", jPanel23);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1224, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 631, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Employes", jPanel6);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1231, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 665, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTabbedPane1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1StateChanged
        int selected = jTabbedPane1.getSelectedIndex();
        switch (selected) {
            case 0:
                update_table_produits();
                setColumnWidths(jtable_produit, 100, 600, 100, 100, 100, 100, 150, 150);
                fill_combobox("select * from type_c_n", jComboBox_c_nc_ajout, "type");
                fill_combobox("select * from sous", jComboBoxbfa_p91_ajout, "id_sous");
                fill_combobox("select * from type_sous", jComboBox_famille_ajout, "type_s");

                break;//produits
            case 1:
                update_table_produits_rech();//rechercher produit
                setColumnWidths(jTable_rp, 100, 600, 100, 100, 100, 100, 150, 150);
                fill_combobox("select * from type_c_n", jComboBox_c_nc_r_p, "type");
                fill_combobox("select * from type_c_n", jComboBox_c_nc_edi, "type");
                fill_combobox("select * from sous", jComboBoxbfa_ped, "id_sous");
                fill_combobox("select * from type_sous", jComboBox_famille_ed, "type_s");
                fill_combobox("select * from sous", jComboBoxbfa_p9_rp, "id_sous");
                fill_combobox("select * from type_sous", jComboBox_famille_rp, "type_s");

                jComboBox_famille_rp.addItem(" ");
                jComboBox_famille_rp.setSelectedItem(" ");

                break;
            case 2:
                update_table_p90();
                break;//p90
            case 3:
                update_table_bfa();
                break;//bfa
            case 4:
                update_table_c12();

                fill_combobox("select * from type_c_n", jComboBox_c_nc_rc12, "type");
                jComboBox_c_nc_rc12.addItem(" ");
                jComboBox_c_nc_rc12.setSelectedItem(" ");
                fill_combobox("select * from sous", jComboBox_bfa_rc12, "id_sous");
                jComboBox_bfa_rc12.addItem(" ");
                jComboBox_bfa_rc12.setSelectedItem(" ");
                fill_combobox("select * from sous", jComboBox_bfarc121, "id_sous");
                jComboBox_bfarc121.addItem(" ");
                jComboBox_bfarc121.setSelectedItem(" ");
                // fill_combobox("select * from produit", jComboBox_prod_rc12, "designation");
                //jComboBox_prod_rc12.addItem(" ");jComboBox_prod_rc12.setSelectedItem(" ");

                Connection conn1 = Connect.ConnectDB("infra2");
                String query = "select id_dep,nom_dep from departement";
                ResultSet rs;
                try {
                    Statement stm = conn1.createStatement();
                    rs = stm.executeQuery(query);
                    jComboBox_depar_rc12.removeAllItems();
                    while (rs.next()) {
                        jComboBox_depar_rc12.addItem(rs.getString("nom_dep"));
                        depart_id.put(rs.getString("nom_dep"), rs.getInt("id_dep"));
                    }
                    jComboBox_depar_rc12.addItem(" ");
                    jComboBox_depar_rc12.setSelectedItem(" ");
                } catch (SQLException ex) {
                    Logger.getLogger(Normal_user.class.getName()).log(Level.SEVERE, null, ex);
                }

                Connection conn2 = Connect.ConnectDB("infra2");
                String query2 = "select id_employe,nom , prenom from employe";
                ResultSet rs2;
                try {
                    Statement stm = conn2.createStatement();
                    rs2 = stm.executeQuery(query2);
                    jComboBox_emplo_rc12.removeAllItems();
                    while (rs2.next()) {
                        jComboBox_emplo_rc12.addItem(rs2.getString("nom") + "  " + rs2.getString("prenom"));
                        employe_id.put(rs2.getString("nom"), rs2.getInt("id_employe"));
                    }
                    jComboBox_emplo_rc12.addItem(" ");
                    jComboBox_emplo_rc12.setSelectedItem(" ");
                } catch (SQLException ex) {
                    Logger.getLogger(Normal_user.class.getName()).log(Level.SEVERE, null, ex);
                }

                Connection conn3 = Connect.ConnectDB("infra2");
                String query3 = "select designation,id from produit";
                ResultSet rs3;
                try {
                    Statement stm = conn3.createStatement();
                    rs3 = stm.executeQuery(query3);
                    jComboBox_prod_rc12.removeAllItems();
                    while (rs3.next()) {
                        jComboBox_prod_rc12.addItem(rs3.getString("designation"));
                        prod_id.put(rs3.getString("designation"), rs3.getString("id"));
                    }
                    jComboBox_prod_rc12.addItem(" ");
                    jComboBox_prod_rc12.setSelectedItem(" ");
                } catch (SQLException ex) {
                    Logger.getLogger(Normal_user.class.getName()).log(Level.SEVERE, null, ex);
                }
                //fill_combobox("select * from sous", jComboBox_bfa_rc12, "id_sous");
                break;//p90
            case 5:
                update_table_depart();
                break;//bfa
            case 6:
                update_table_alerts();
                break;//bfa
        }
    }//GEN-LAST:event_jTabbedPane1StateChanged

    private void jcombop90ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcombop90ActionPerformed
        int selected = jcombop90.getSelectedIndex();
        if (selected == 1) {
            update_table_p90_c_nc("C");
        } else if (selected == 2) {
            update_table_p90_c_nc("NC");
        }
        setColumnWidths(jTable1, 100, 600, 100, 100, 100, 100, 150, 150);
    }//GEN-LAST:event_jcombop90ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        int selected = jComboBox2.getSelectedIndex();
        if (selected == 1) {
            update_table_bfa_c_nc("C");
        } else if (selected == 2) {
            update_table_bfa_c_nc("NC");
        }

        setColumnWidths(jTable2, 100, 600, 100, 100, 100, 100, 150, 150);
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jtable_produitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtable_produitMouseClicked
        /*   int selected_row = jtable_produit.getSelectedRow();
        txt_designation_editer.setText(jtable_produit.getValueAt(selected_row, 1).toString());
        jComboBox_c_nc.setSelectedItem(jtable_produit.getValueAt(selected_row, 2).toString());
        jComboBoxbfa_p90.setSelectedItem(jtable_produit.getValueAt(selected_row, 3).toString());

        if (jtable_produit.getValueAt(selected_row, 3).toString().equalsIgnoreCase("BFA")) {
            jComboBox_famille.setEnabled(false);
        } else {
            jComboBox_famille.setEnabled(true);
            jComboBox_famille.setSelectedItem(jtable_produit.getValueAt(selected_row, 4).toString());
        }

        jSpinner_prix.setValue(jtable_produit.getValueAt(selected_row, 5));
        jSpinner_stock.setValue(jtable_produit.getValueAt(selected_row, 6));
         */

    }//GEN-LAST:event_jtable_produitMouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        jTextField_desig_ajout.setText("");
        jComboBox_c_nc_ajout.setSelectedIndex(0);
        jComboBoxbfa_p91_ajout.setSelectedIndex(0);
        jSpinner_prix_ajout.setValue(0);
        jComboBox_famille_ajout.setSelectedIndex(0);
        jSpinner_stock_ajout.setValue(0);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton_ajouterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ajouterActionPerformed
        String desig = jTextField_desig_ajout.getText();
        String nature_c_nc = jComboBox_c_nc_ajout.getSelectedItem().toString();
        String bfa_90 = jComboBoxbfa_p91_ajout.getSelectedItem().toString();
        float prix = (float) jSpinner_prix_ajout.getValue();
        int famille = (Integer.parseInt(jComboBox_famille_ajout.getSelectedItem().toString()));
        int stock = (int) jSpinner_stock_ajout.getValue();
        boolean retournable_bol = jCheckBox_retournable_ajouter.isSelected();
        int retournable = 0;
        if (retournable_bol) {
            retournable = 1;
        }
        if (jCheckBox3.isSelected()) {
            Produits.addProduit(desig, prix, stock, nature_c_nc, bfa_90, famille, retournable, (int) jSpinner_quanti_min.getValue());
        } else {
            Produits.addProduit(desig, prix, stock, nature_c_nc, bfa_90, famille, retournable, 0);
        }

        System.out.println("produit ajouté");


    }//GEN-LAST:event_jButton_ajouterActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        int selected_row = jtable_ajouter_produits.getSelectedRow();
        String prod = jtable_ajouter_produits.getValueAt(selected_row, 1).toString();
        String souch = jtable_ajouter_produits.getValueAt(selected_row, 3).toString();
        String hash = (prod + souch).toLowerCase().hashCode() + "";

        if (selected_row > -1) {

            try {
                PreparedStatement pst;
                ResultSet rs;//resulta de requet
                Connection conn = Connect.ConnectDB("infra2");
                String sql = "delete from produit where id=?";
                pst = conn.prepareStatement(sql);

                pst.setString(1, hash);
                pst.execute();
                System.out.println("departemetn " + prod + " supprimé");
                DefaultTableModel model2 = (DefaultTableModel) jtable_ajouter_produits.getModel();
                model2.removeRow(selected_row);
                jtable_ajouter_produits.setModel(model2);
            } catch (SQLException ex) {
                Logger.getLogger(Normal_user.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        String nom_dep = jTextField_nom_dep.getText();
        int num_dep = (Integer.parseInt(jTextField_num_dep.getText()));
        String num_tel = jTextField_tel_dep.getText();
        String nom_respon = jTextField_resp_dep.getText();
        String info = jTextArea_info_dep.getText();
        String sql = "INSERT INTO Departement(nom_dep,num_dep,num_tel,nom_respon,info_dep) VALUES (?,?,?,?,?)";

        try {
            PreparedStatement pst;
            ResultSet rs;//resulta de requet
            Connection conn = Connect.ConnectDB("infra2");
            pst = conn.prepareStatement(sql);

            pst.setString(1, nom_dep);
            pst.setInt(2, num_dep);
            pst.setString(3, num_tel);
            pst.setString(4, nom_respon);
            pst.setString(5, info);
            pst.execute();

            JOptionPane.showMessageDialog(null, "Dep est ajouté avec succes");
            DefaultTableModel model = (DefaultTableModel) jTable_departement.getModel();
            model.addRow(new Object[]{nom_dep, num_dep, num_tel, nom_respon, info});

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e);
        }


    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        fill_combobox("select * from departement ", jComboBox_depar_c12, "nom_dep");
        jComboBox_bfa_c12.setSelectedItem(" ");
        Connection conn2 = Connect.ConnectDB("infra2");
        fill_combobox("select * from sous", jComboBox_bfa_c12, "id_sous");
        jComboBox_bfa_c12.addItem(" ");

        String query2 = "select id_employe,nom , prenom from employe";
        ResultSet rs2;
        try {
            Statement stm = conn2.createStatement();
            rs2 = stm.executeQuery(query2);
            jComboBox_emplo_rc12.removeAllItems();
            while (rs2.next()) {
                jComboBox_emp_ajout_c12.addItem(rs2.getString("nom") + "  " + rs2.getString("prenom"));
                employe_id.put(rs2.getString("nom") + "  " + rs2.getString("prenom"), rs2.getInt("id_employe"));
            }
            jComboBox_emp_ajout_c12.addItem(" ");
            jComboBox_emp_ajout_c12.setSelectedItem(" ");
        } catch (SQLException ex) {
            Logger.getLogger(Normal_user.class.getName()).log(Level.SEVERE, null, ex);
        }
        /*   
        Connection conn1 = Connect.ConnectDB("infra2");
        String query = ("select designation, quantity from produit where sous='" + jComboBox_bfa_c12.getSelectedItem().toString() + "' and  type_C_N='C'");
        ResultSet rs;
        try {
            Statement stm = conn1.createStatement();
            rs = stm.executeQuery(query);
            jComboBox_produit_c12.removeAllItems();

            nomProd_quanti.clear();
            while (rs.next()) {
                String desig = rs.getString("designation");
                jComboBox_produit_c12.addItem(desig);
                nomProd_quanti.put(desig, rs.getInt("quantity"));
            }
            jComboBox_produit_c12.validate();
            System.out.println("items:" + jComboBox_produit_c12.getItemCount());
            DefaultTableModel dtm = (DefaultTableModel) jtable_ajouter_produits.getModel();
            dtm.setRowCount(0);
            setPanelEnabled(jPanel_prods_c12,true);
            jPanel_prods_c12.setEnabled(true);
        } catch (SQLException ex) {
            Logger.getLogger(Normal_user.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        //setPanelEnabled(jPanel_prods_c12,false);
        jDialog_ajouter_c12.show();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        DefaultTableModel model1 = (DefaultTableModel) jtable_ajouter_produits.getModel();
        String produit = jComboBox_produit_c12.getSelectedItem().toString();

        String demande = jTextField_deman_c12.getText();
        String delivre = jTextField__delivre_c12.getText();

        String stock = jTextField_stock_c12.getText();

        model1.addRow(new Object[]{
            produit,
            demande,
            delivre,
            stock,});

        jtable_ajouter_produits.setModel(model1);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        if (jtable_ajouter_produits.getSelectedRow() >= 0) {

            DefaultTableModel model2 = (DefaultTableModel) jtable_ajouter_produits.getModel();
            model2.removeRow(jtable_ajouter_produits.getSelectedRow());
            jtable_ajouter_produits.setModel(model2);
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed

        String num_c12 = jTextField_numero_c12.getText();
        String nom_dep = jComboBox_depar_c12.getSelectedItem().toString();
        int year_c12 = jDateChooser_ajouter_c12.getDate().getYear() + 1900;
        int month_c12 = jDateChooser_ajouter_c12.getDate().getMonth() + 1;
        int day_c12 = jDateChooser_ajouter_c12.getDate().getDay() + 5;
        String liv_re = jComboBox_livr_re.getSelectedItem().toString();
        String sous_ch = jComboBox_bfa_c12.getSelectedItem().toString();
        String c_nc = jComboBox_c_nc_ajouter_c12.getSelectedItem().toString();
        //Boolean c_nc = jCheckBox_c_nc_c12.isSelected();
        int id_employe;

        String remarque = jTextArea1.getText();
        String id_c12 = num_c12 + "-" + year_c12 + "-" + sous_ch;
        //ArrayList<ArrayList> prod_id_q=new ArrayList<ArrayList>();  
        Map<String, String> map = new HashMap<>();

        int row_count = jtable_ajouter_produits.getRowCount();
        String query_relation = "insert into relation_c12_pro (`id_c12`, `id_pro`, `qua_livre`, `qua_demende`) VALUES ";
        String where = "WHERE id IN(";
        String when = "";

        for (int i = 0; i < row_count; i++) {

            String prod = jtable_ajouter_produits.getValueAt(i, 0).toString();
            String demand = jtable_ajouter_produits.getValueAt(i, 1).toString();
            String delivr = jtable_ajouter_produits.getValueAt(i, 2).toString();
            String hash = (prod + sous_ch).toLowerCase().hashCode() + "";
            System.out.println(" hash : " + hash);
            map.put(hash, delivr);
            where = where + "'" + hash + "',";
            when = when + " WHEN '" + hash + "' THEN quantity-" + delivr;
            query_relation = query_relation + "('" + num_c12 + "-" + year_c12 + "-" + sous_ch + "', '" + hash + "'," + demand + "," + delivr + "),";
            // insert in one transaction
        }
        query_relation = query_relation.substring(0, query_relation.length() - 1) + ";";
        where = where.substring(0, where.length() - 1) + ");";
        String update_query = "UPDATE produit SET quantity = CASE id " + when + " ELSE quantity END " + where;

        // inserer dans table c12
        String query_c12 = "INSERT INTO c12 (id_c12, N_12, date, type_c12_L_R, remarque, type_B_P, C_NC, id_emp, id_dep) VALUES (?,?,?,?,?,?,?,?,(select id_dep from departement where nom_dep=?) )";

        String global_query = query_c12 + "; " + query_relation + " " + update_query;
        System.out.println(global_query);

        // query c12
        try {
            PreparedStatement pst;
            ResultSet rs;//resulta de requet
            Connection conn = Connect.ConnectDB("infra2");
            pst = conn.prepareStatement(query_c12 + ";");

            pst.setString(1, id_c12);
            pst.setString(2, num_c12);
            pst.setString(3, year_c12 + "-" + month_c12 + "-" + day_c12);
            pst.setString(4, liv_re);
            pst.setString(5, remarque);
            pst.setString(6, sous_ch);
            pst.setString(7, c_nc);

            if (!jComboBox_emp_ajout_c12.getSelectedItem().toString().equalsIgnoreCase(" ")) {
                pst.setInt(8, employe_id.get(jComboBox_emp_ajout_c12.getSelectedItem().toString()));
            } else {
                pst.setNull(8, java.sql.Types.INTEGER);
            }

            pst.setString(9, nom_dep);
            pst.execute();

            // JOptionPane.showMessageDialog(null, "c12 est ajouté avec succes");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e);
        }

        //query relation
        try {
            PreparedStatement pst;
            ResultSet rs;//resulta de requet
            Connection conn = Connect.ConnectDB("infra2");
            pst = conn.prepareStatement(query_relation);

            pst.execute();

            //  JOptionPane.showMessageDialog(null, "relation est ajouté avec succes");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e);
        }

        //query update
        try {
            PreparedStatement pst;
            ResultSet rs;//resulta de requet
            Connection conn = Connect.ConnectDB("infra2");
            pst = conn.prepareStatement(update_query);

            pst.execute();

            //  JOptionPane.showMessageDialog(null, "update avec succes");
            DefaultTableModel model = (DefaultTableModel) jTable_c12.getModel();
            model.addRow(new Object[]{id_c12, num_c12, year_c12 + "-" + month_c12 + "-" + day_c12, liv_re, sous_ch, c_nc, nom_dep, jComboBox_emp_ajout_c12.getSelectedItem().toString(), remarque});
            if (!jcheck_garder_fenetre.isSelected()) {
                jDialog_ajouter_c12.setVisible(false);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e);
        }

        // inserer dans table relation

    }//GEN-LAST:event_jButton10ActionPerformed

    private void jRadioButton_eqActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton_eqActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton_eqActionPerformed

    private void jRadioButton_eq_stockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton_eq_stockActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton_eq_stockActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed

        String sous_ch = jComboBoxbfa_p9_rp.getSelectedItem().toString();
        String c_nc = jComboBox_c_nc_r_p.getSelectedItem().toString();

        String prix_st = jTextField_prix_rp.getText();
        float prix = 0;
        if (!prix_st.isEmpty()) {
            prix = (Float.parseFloat(prix_st));
        }

        String famille = jComboBox_famille_rp.getSelectedItem().toString();
        int stock = 0;

        //DefaultTableModel model = new DefaultTableModel();
        DefaultTableModel model = (DefaultTableModel) jTable_rp.getModel();
        model.setRowCount(0);

        /* model.addColumn("id");

        model.addColumn("Designation");
        model.addColumn("Nature");
        model.addColumn("Sous-Ch");
        model.addColumn("Famille");
        model.addColumn("Prix un");
        model.addColumn("Stock");
        model.addColumn("Total TTC");*/
        try {
            Connection conn1 = Connect.ConnectDB("infra2");
            Statement stm = conn1.createStatement();
            ResultSet rs;
            String selectQuery = "select * from Produit where 1=1 ";
            String operator = "";

            if (!sous_ch.equalsIgnoreCase("")) {
                selectQuery = selectQuery + " and sous ='" + sous_ch + "'";
            }

            if (!c_nc.equalsIgnoreCase("")) {
                selectQuery = selectQuery + " and type_C_N ='" + c_nc + "'";
            }

            if (jRadioButton_eq.isSelected()) {
                operator = "=";
            }
            if (jRadioButton_less.isSelected()) {
                operator = "<";
            }
            if (jRadioButton_great.isSelected()) {
                operator = ">";
            }

            if (!prix_st.equalsIgnoreCase("")) {
                selectQuery = selectQuery + " and prix" + operator + "" + prix + "  ";
            }

            if (sous_ch.equalsIgnoreCase("P90")) {
                if (!famille.equalsIgnoreCase(" ")) {
                    selectQuery = selectQuery + " and type_sous=" + famille;
                }
            }

            if (jRadioButton_eq_stock.isSelected()) {
                operator = "=";
            }
            if (jRadioButton_less_stock.isSelected()) {
                operator = "<";
            }
            if (jRadioButton_greater_stock.isSelected()) {
                operator = ">";
            }

            if (!jTextField_stock_rp1.getText().equalsIgnoreCase("")) {
                stock = Integer.parseInt(jTextField_stock_rp1.getText());
                selectQuery = selectQuery + " and quantity" + operator + "" + stock + "  ";
            }

            PreparedStatement pst;
            pst = conn1.prepareStatement(selectQuery);

            rs = pst.executeQuery(selectQuery);

            int id1 = 0;
            while (rs.next()) {
                id1 += 1;
                Float prix2 = rs.getFloat("prix");
                int quanti = rs.getInt("quantity");
                System.out.println(rs.getString("designation") + rs.getString("type_C_N")
                        + rs.getString("sous")
                        + rs.getString("type_sous")
                        + rs.getInt("prix")
                        + rs.getInt("quantity"));
                model.addRow(new Object[]{
                    id1,
                    rs.getString("designation"),
                    rs.getString("type_C_N"),
                    rs.getString("sous"),
                    rs.getString("type_sous"),
                    rs.getInt("prix"),
                    rs.getInt("quantity"),
                    prix2 * quanti
                });
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            e.getSQLState();
            e.printStackTrace();
        }

        jTable_rp.setModel(model);


    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        String desig = txt_designation_recher.getText();
        String bfa_p90 = jComboBoxbfa_p9_recher.getSelectedItem().toString();

        DefaultTableModel model = (DefaultTableModel) jTable_rp.getModel();
        model.setRowCount(0);

        try {
            Connection conn1 = Connect.ConnectDB("infra2");

            String selectQuery = "select * from Produit where designation like ? and sous=?";
            PreparedStatement pst;
            ResultSet rs;
            pst = conn1.prepareStatement(selectQuery);

            pst.setString(1, desig + "%");
            pst.setString(2, bfa_p90);

            rs = pst.executeQuery();

            int id1 = 0;
            while (rs.next()) {

                id1 += 1;
                Float prix2 = rs.getFloat("prix");
                int quanti = rs.getInt("quantity");

                model.addRow(new Object[]{
                    id1,
                    rs.getString("designation"),
                    rs.getString("type_C_N"),
                    rs.getString("sous"),
                    rs.getString("type_sous"),
                    rs.getInt("prix"),
                    rs.getInt("quantity"),
                    prix2 * quanti
                });
            }
            jTable_rp.setModel(model);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            e.getSQLState();
            e.printStackTrace();
        }

    }//GEN-LAST:event_jButton14ActionPerformed

    private void jComboBoxbfa_p91_ajoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxbfa_p91_ajoutActionPerformed
        /*
        if (jComboBoxbfa_p91_ajout.getSelectedItem().toString().equalsIgnoreCase("BFA")) {
            jComboBox_famille_ajout.setEnabled(false);
        } else {
            jComboBox_famille_ajout.setEnabled(true);

        }*/
    }//GEN-LAST:event_jComboBoxbfa_p91_ajoutActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        int selected_row = jTable_departement.getSelectedRow();
        if (selected_row > -1) {
            String id = jTable_departement.getValueAt(selected_row, 0).toString();
            System.out.println(id);

            try {
                PreparedStatement pst;
                ResultSet rs;//resulta de requet
                Connection conn = Connect.ConnectDB("infra2");
                String sql = "delete from departement where id_dep=?";
                pst = conn.prepareStatement(sql);

                pst.setString(1, id);
                pst.execute();
                System.out.println("departemetn " + id + " supprimé");
                DefaultTableModel model2 = (DefaultTableModel) jTable_departement.getModel();
                model2.removeRow(selected_row);
                jTable_departement.setModel(model2);
            } catch (SQLException ex) {
                Logger.getLogger(Normal_user.class.getName()).log(Level.SEVERE, null, ex);
            }

        }


    }//GEN-LAST:event_jButton16ActionPerformed

    private void jComboBox_c_nc_ajouter_c12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_c_nc_ajouter_c12ActionPerformed
        String c_nc = jComboBox_c_nc_ajouter_c12.getSelectedItem().toString();

        /* if(jCheckBox_c_nc_c12.isSelected()){
               fill_combobox("select designation from produit where sous='"+jComboBox_bfa_c12.getSelectedItem().toString()+"' and type_C_N='C'", jComboBox_produit_c12, "designation");
           }else{
                fill_combobox("select designation from produit where sous='"+jComboBox_bfa_c12.getSelectedItem().toString()+"' and type_C_N='NC'", jComboBox_produit_c12, "designation");
           }*/
        Connection conn1 = Connect.ConnectDB("infra2");
        String query = ("select designation, quantity from produit where sous='" + jComboBox_bfa_c12.getSelectedItem().toString() + "' and  type_C_N='" + c_nc + "'");
        ResultSet rs;
        try {
            Statement stm = conn1.createStatement();
            rs = stm.executeQuery(query);
            jComboBox_produit_c12.removeAllItems();

            nomProd_quanti.clear();
            while (rs.next()) {
                String desig = rs.getString("designation");
                jComboBox_produit_c12.addItem(desig);
                nomProd_quanti.put(desig, rs.getInt("quantity"));
            }
            //jComboBox_produit_c12.validate();
            //System.out.println("items:" + jComboBox_produit_c12.getItemCount());
            DefaultTableModel dtm = (DefaultTableModel) jtable_ajouter_produits.getModel();
            dtm.setRowCount(0);
            setPanelEnabled(jPanel_prods_c12, true);
            jPanel_prods_c12.setEnabled(true);
        } catch (SQLException ex) {
            Logger.getLogger(Normal_user.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jComboBox_c_nc_ajouter_c12ActionPerformed

    private void jComboBox_produit_c12ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox_produit_c12ItemStateChanged

        //
        if (evt.getStateChange() == ItemEvent.DESELECTED) {
            if (jComboBox_produit_c12.getItemCount() > 1) {
                System.out.println("map= " + jComboBox_produit_c12.getItemCount());
                jTextField_stock_c12.setText("" + nomProd_quanti.get(jComboBox_produit_c12.getSelectedItem().toString()));
            }
        }
    }//GEN-LAST:event_jComboBox_produit_c12ItemStateChanged

    private void jComboBox_c_nc_rc12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_c_nc_rc12ActionPerformed

    }//GEN-LAST:event_jComboBox_c_nc_rc12ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        String num_c12 = jTextField_num_rc12.getText();
        String depart = jComboBox_depar_rc12.getSelectedItem().toString();
        String sous_ch = jComboBox_bfa_rc12.getSelectedItem().toString();
        String c_nc = jComboBox_c_nc_rc12.getSelectedItem().toString();
        String employe = jComboBox_emplo_rc12.getSelectedItem().toString();
        String liv_re = jComboBox_livr_rc12.getSelectedItem().toString();
        String produit = jComboBox_prod_rc12.getSelectedItem().toString();

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("id");
        model.addColumn("Numero");
        model.addColumn("Date");
        model.addColumn("Livraison");
        model.addColumn("Sous_ch");
        model.addColumn("Consommable");
        model.addColumn("Departement");
        model.addColumn("Employe");
        model.addColumn("Remarque");

        String selectQuery = "select * from C12\n"
                + "left outer join employe emp on C12.id_emp=emp.id_employe\n"
                + "left outer join departement dep on C12.id_dep=dep.id_dep\n"
                + // "join relation_c12_pro re on C12.id_c12=re.id_c12\n" +
                "where 1=1";

        if (!produit.equalsIgnoreCase(" ")) { //produit selectionné
            selectQuery = "select * from C12\n"
                    + "left outer join employe emp on C12.id_emp=emp.id_employe\n"
                    + "left outer join departement dep on C12.id_dep=dep.id_dep\n"
                    + "left outer join relation_c12_pro re on C12.id_c12=re.id_c12\n"
                    + "where 1=1";
            selectQuery = selectQuery + " and re.id_pro='" + prod_id.get(produit) + "'";////////
        }

        if (!depart.equalsIgnoreCase(" ")) {
            selectQuery = selectQuery + " and dep.nom_dep ='" + depart + "'";
        }

        if (!c_nc.equalsIgnoreCase(" ")) {
            selectQuery = selectQuery + " and C12.C_NC ='" + c_nc + "'";
        }

        if (!sous_ch.equalsIgnoreCase(" ")) {
            selectQuery = selectQuery + " and C12.type_B_P='" + sous_ch + "'";
        }

        if (!employe.equalsIgnoreCase(" ")) {
            selectQuery = selectQuery + " and emp.nom='" + employe + "'";
        }

        if (!liv_re.equalsIgnoreCase("")) {
            selectQuery = selectQuery + " and C12.type_c12_L_R='" + liv_re + "'";
        }

        //  if(produit.equalsIgnoreCase(" ")){
        //recherche sans produit
        try {
            Connection conn1 = Connect.ConnectDB("infra2");
            Statement stm = conn1.createStatement();
            ResultSet rs;

            if (jMonthChooser1.isEnabled()) {
                selectQuery = selectQuery + " and month(C12.date)='" + (jMonthChooser1.getMonth() + 1) + "'";  //month=jMonthChooser1.getMonth();
            }
            if (jYearChooser1.isEnabled()) {
                selectQuery = selectQuery + " and year(C12.date)='" + jYearChooser1.getYear() + "'"; // year=jYearChooser1.getYear();
            }

            System.out.println(selectQuery);

            PreparedStatement pst;
            pst = conn1.prepareStatement(selectQuery);
            rs = pst.executeQuery(selectQuery);

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("id_c12"),
                    rs.getInt("N_12"),
                    rs.getString("date"),
                    rs.getString("type_c12_L_R"),
                    rs.getString("C_NC"),
                    rs.getString("type_B_P"),
                    rs.getString("nom"),
                    rs.getString("nom_dep"),
                    rs.getString("remarque"),});
            }
            jTable_c12.setModel(model);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            e.getSQLState();
            e.printStackTrace();
        }
        /*}else{
            // produit selectionné
            
            
            
            
        }*/
 /*
        try {
            Connection conn1 = Connect.ConnectDB("infra2");
            Statement stm = conn1.createStatement();
            ResultSet rs;
            String selectQuery = "select * from Produit where 1=1 ";
            String operator = "";

            if (!sous_ch.equalsIgnoreCase("")) {
                selectQuery = selectQuery + " and sous ='" + sous_ch + "'";
            }

            if (!c_nc.equalsIgnoreCase("")) {
                selectQuery = selectQuery + " and type_C_N ='" + c_nc + "'";
            }

            if (jRadioButton_eq.isSelected()) {
                operator = "=";
            }
            if (jRadioButton_less.isSelected()) {
                operator = "<";
            }
            if (jRadioButton_great.isSelected()) {
                operator = ">";
            }

            if (!prix_st.equalsIgnoreCase("")) {
                selectQuery = selectQuery + " and prix" + operator + "" + prix + "  ";
            }

            if (sous_ch.equalsIgnoreCase("P90")) {
                if (!famille.equalsIgnoreCase(" ")) {
                    selectQuery = selectQuery + " and type_sous=" + famille;
                }
            }

            if (jRadioButton_eq_stock.isSelected()) {
                operator = "=";
            }
            if (jRadioButton_less_stock.isSelected()) {
                operator = "<";
            }
            if (jRadioButton_greater_stock.isSelected()) {
                operator = ">";
            }

            if (!jTextField_stock_rp1.getText().equalsIgnoreCase("")) {
                stock = Integer.parseInt(jTextField_stock_rp1.getText());
                selectQuery = selectQuery + " and quantity" + operator + "" + stock + "  ";
            }

            System.out.println(selectQuery);
            PreparedStatement pst;
            pst = conn1.prepareStatement(selectQuery);

            rs = pst.executeQuery(selectQuery);

            int id1 = 0;
            while (rs.next()) {
                id1 += 1;
                Float prix2 = rs.getFloat("prix");
                int quanti = rs.getInt("quantity");
                System.out.println(rs.getString("designation") + rs.getString("type_C_N")
                        + rs.getString("sous")
                        + rs.getString("type_sous")
                        + rs.getInt("prix")
                        + rs.getInt("quantity"));
                model.addRow(new Object[]{
                    id1,
                    rs.getString("designation"),
                    rs.getString("type_C_N"),
                    rs.getString("sous"),
                    rs.getString("type_sous"),
                    rs.getInt("prix"),
                    rs.getInt("quantity"),
                    prix2 * quanti
                });
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            e.getSQLState();
            e.printStackTrace();
        }*/


    }//GEN-LAST:event_jButton18ActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        String num_c12 = jTextField_num_rc12.getText();
        String bfa_p90 = jComboBox_bfarc121.getSelectedItem().toString();

        DefaultTableModel model = (DefaultTableModel) jTable_c12.getModel();
        model.setRowCount(0);
        /*
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("id");
        model.addColumn("Numero");
        model.addColumn("Date");
        model.addColumn("Livraison");
        model.addColumn("Sous_ch");
        model.addColumn("Consommable");
        model.addColumn("Departement");
        model.addColumn("Employe");
         */

        String selectQuery = "select c.`id_c12`, c.`N_12`, c.`date`, c.`type_c12_L_R`, c.`type_B_P`, c.`C_NC` , c.`remarque`, e.`nom`, e.`prenom`, d.`nom_dep` from C12 c "
                + "LEFT OUTER JOIN employe e on e.id_employe=c.id_emp "
                + "lEFT OUTER JOIN departement d on d.id_dep=c.id_dep "
                + "where 1=1";
        if (!bfa_p90.equalsIgnoreCase(" ")) // sou_ch selectioné
        {
            selectQuery = selectQuery + " and c.type_B_P='" + bfa_p90 + "'";
        }
        if (!num_c12.equalsIgnoreCase("")) // num selectioné
        {
            selectQuery = selectQuery + " and c.N_12='" + num_c12 + "'";
        }

        try {
            Connection conn1 = Connect.ConnectDB("infra2");

            PreparedStatement pst;
            ResultSet rs;
            pst = conn1.prepareStatement(selectQuery);

            rs = pst.executeQuery();

            int id1 = 0;
            while (rs.next()) {
                String nom_emp = rs.getString("nom") + "  " + rs.getString("prenom");
                if (nom_emp.equalsIgnoreCase("null  null")) {
                    nom_emp = "";
                }
                model.addRow(new Object[]{
                    rs.getString("id_c12"),
                    rs.getString("N_12"),
                    rs.getString("Date"),
                    rs.getString("type_c12_L_R"),
                    rs.getString("type_B_P"),
                    rs.getString("C_NC"),
                    rs.getString("nom_dep"),
                    nom_emp,
                    rs.getString("remarque")});
            }
            jTable_c12.setModel(model);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            e.getSQLState();
            e.printStackTrace();
        }

    }//GEN-LAST:event_jButton19ActionPerformed

    private void jCheckBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox2ActionPerformed
        if (!jYearChooser1.isEnabled()) {
            jYearChooser1.setEnabled(true);
        } else {
            jYearChooser1.setEnabled(false);
        }
    }//GEN-LAST:event_jCheckBox2ActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        if (!jMonthChooser1.isEnabled()) {
            jMonthChooser1.setEnabled(true);
        } else {
            jMonthChooser1.setEnabled(false);
        }
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jCheckBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox3ActionPerformed
        if (!jLabel_quant_min.isEnabled()) {
            jLabel_quant_min.setEnabled(true);
            jSpinner_quanti_min.setEnabled(true);
        } else {
            jLabel_quant_min.setEnabled(false);
            jSpinner_quanti_min.setEnabled(false);
        }
    }//GEN-LAST:event_jCheckBox3ActionPerformed

    private void jTable_c12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_c12MouseClicked

        if (evt.getClickCount() == 2 && jTable_c12.getSelectedRow() != -1) {
            JTable table = (JTable) evt.getSource();
            Point p = evt.getPoint();
            int row = table.rowAtPoint(p);
            String id_c12 = jTable_c12.getValueAt(row, 0).toString();

            String query = "SELECT *\n"
                    + "FROM C12\n"
                    + "LEFT JOIN employe emp ON C12.id_emp = emp.id_employe\n"
                    + "JOIN departement dep ON C12.id_dep = dep.id_dep\n"
                    + "JOIN relation_c12_pro re ON C12.id_c12 = re.id_c12\n"
                    + "JOIN produit pr ON re.id_pro = pr.id "
                    + "where C12.id_c12='" + id_c12 + "'";
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("id");
            model.addColumn("Designation");
            model.addColumn("Nature");
            model.addColumn("Sous-Ch");
            model.addColumn("Famille");
            model.addColumn("demandé");
            model.addColumn("livré");
            model.addColumn("Total TTC");

            try {
                Connection conn1 = Connect.ConnectDB("infra2");
                Statement stm = conn1.createStatement();
                ResultSet rs = stm.executeQuery(query);
                int id = 0;
                float prix_tot = 0;

                while (rs.next()) {

                    id += 1;
                    Float prix = rs.getFloat("prix");
                    int quanti_livr = rs.getInt("qua_livre");
                    float somme = prix * quanti_livr;
                    prix_tot += somme;
                    model.addRow(new Object[]{
                        id,
                        rs.getString("designation"),
                        rs.getString("type_C_N"),
                        rs.getString("sous"),
                        rs.getString("type_sous"),
                        rs.getInt("qua_demende"),
                        rs.getInt("qua_livre"),
                        somme
                    });
                }
                jLabel_total_c12.setText(prix_tot + "");

                jLabel_c_nc_c12.setText(jTable_c12.getValueAt(row, 5).toString());
                jLabel_date_c12.setText(jTable_c12.getValueAt(row, 2).toString());
                jLabel_livr_c12.setText(jTable_c12.getValueAt(row, 3).toString());
                jLabel_dep_c12.setText(jTable_c12.getValueAt(row, 6).toString());
                jLabel_employe_c12.setText(jTable_c12.getValueAt(row, 7).toString());
                jLabel_num_c12.setText(jTable_c12.getValueAt(row, 1).toString());
                jLabel_sous_c12.setText(jTable_c12.getValueAt(row, 4).toString());
                jTextArea_rem_c12.setText(jTable_c12.getValueAt(row, 8).toString());

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
                e.getSQLState();
                e.printStackTrace();
            }

            TableColumnModel columnModel = jtable_aficher_c12.getColumnModel();
            jtable_aficher_c12.setModel(model);
            jDialog_afficher_c12.setVisible(true);
        }

    }//GEN-LAST:event_jTable_c12MouseClicked

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jTextField_deman_c12KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_deman_c12KeyTyped

    }//GEN-LAST:event_jTextField_deman_c12KeyTyped

    private void jTextField__delivre_c12KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField__delivre_c12KeyTyped

    }//GEN-LAST:event_jTextField__delivre_c12KeyTyped

    private void jTextField_deman_c12KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_deman_c12KeyReleased
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
            evt.consume();
        } else {
            float qtt_dem = Float.parseFloat(jTextField_deman_c12.getText());
            jTextField__delivre_c12.setText(jTextField_deman_c12.getText());
        }
    }//GEN-LAST:event_jTextField_deman_c12KeyReleased

    private void jTextField__delivre_c12KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField__delivre_c12KeyReleased
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
            evt.consume();
        } else {
            float qtt_dem = Float.parseFloat(jTextField_deman_c12.getText());
            float qtt_del = Float.parseFloat(jTextField__delivre_c12.getText());
            if (qtt_del > qtt_dem) {
                jTextField__delivre_c12.setText(jTextField_deman_c12.getText());
                // jTextField_stock_c12.setText((nomProd_quanti.get(jComboBox_produit_c12.getSelectedItem().toString()) -Float.parseFloat(jTextField__delivre_c12.getText()))+"" );
            }

        }
    }//GEN-LAST:event_jTextField__delivre_c12KeyReleased

    private void jComboBoxbfa_pedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxbfa_pedActionPerformed
        if (jComboBoxbfa_ped.getSelectedItem().toString().equalsIgnoreCase("BFA")) {
            jComboBox_famille_ed.setEnabled(false);
        } else {
            jComboBox_famille_ed.setEnabled(true);
        }
    }//GEN-LAST:event_jComboBoxbfa_pedActionPerformed

    private void jSpinner_stock_edStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinner_stock_edStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jSpinner_stock_edStateChanged

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        /*
        int selected_row = jTable_rp.getSelectedRow();
        String old_desi = jTable_rp.getValueAt(selected_row, 1).toString();
        String old_nature = jTable_rp.getValueAt(selected_row, 2).toString();
        String old_sous = jTable_rp.getValueAt(selected_row, 3).toString();
        int old_famill = (Integer.parseInt(jTable_rp.getValueAt(selected_row, 4).toString()));
        float old_pu = Float.parseFloat(jTable_rp.getValueAt(selected_row, 5).toString());
        float old_quan_stock = Float.parseFloat(jTable_rp.getValueAt(selected_row, 6).toString());
         */

        int selected_row = jTable_rp.getSelectedRow();

        String old_desi = mod_prod.get("old_desi").toString();
        String old_nature = mod_prod.get("old_nature").toString();
        String old_sous = mod_prod.get("old_sous").toString();
        int old_famill = (Integer.parseInt(mod_prod.get("old_famill").toString()));
        float old_pu = Float.parseFloat(mod_prod.get("old_pu").toString());
        float old_quan_stock = Float.parseFloat(mod_prod.get("old_quan_stock").toString());

        //new 
        String new_desi = txt_designation_editer.getText();
        String new_nature_c_nc = jComboBox_c_nc_edi.getSelectedItem().toString();
        String new_sous = jComboBoxbfa_ped.getSelectedItem().toString();
        float new_pu = Float.parseFloat(jSpinner_prix_ed.getValue().toString());
        int new_famille = (Integer.parseInt(jComboBox_famille_ajout.getSelectedItem().toString()));
        float new_quan_stock = Float.parseFloat(jSpinner_stock_ed.getValue().toString());

        ResultSet rs = null;
        PreparedStatement pst;

        java.sql.Connection conn2 = Connect.ConnectDB("infra2");
        String insert_pro = "UPDATE `produit` SET `id`=?,`designation`=?, `prix`=?, `quantity`=?, `type_C_N`=?, `sous`=?, `type_sous`=? WHERE `id`=?";//, `retounable`=?, `quant_min`=?

        if ((new_quan_stock > old_quan_stock) && (new_pu != old_pu)) {
            new_pu = (old_pu + new_pu) / 2;
        }

        try {

            pst = conn2.prepareStatement(insert_pro);
            //pst.setString
            pst.setInt(1, (new_desi + new_sous).toLowerCase().hashCode());
            pst.setString(2, new_desi);
            pst.setFloat(3, new_pu);
            pst.setFloat(4, new_quan_stock);
            pst.setString(5, new_nature_c_nc);
            pst.setString(6, new_sous);
            pst.setInt(7, new_famille);
            pst.setString(8, (old_desi + old_sous).toLowerCase().hashCode() + "");
            System.out.println("******************");
            printMap(mod_prod);
            System.out.println("******************");

            pst.executeUpdate();
            conn2.close();
            System.out.println("produit modifié");
            jSpinner_prix_ed.setValue(0);
            jSpinner_stock_ed.setValue(0);
            txt_designation_editer.setText("");
            // emptry the fields....
            jTable_rp.clearSelection();
        } catch (SQLException ex) {
            Logger.getLogger(Produits.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton20ActionPerformed

    private void jTable_rpKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable_rpKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable_rpKeyTyped

    private void jTable_rpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_rpMouseClicked
        int selected_row = jTable_rp.getSelectedRow();
        txt_designation_editer.setText(jTable_rp.getValueAt(selected_row, 1).toString());
        jComboBox_c_nc_edi.setSelectedItem(jTable_rp.getValueAt(selected_row, 2).toString());
        jComboBoxbfa_ped.setSelectedItem(jTable_rp.getValueAt(selected_row, 3).toString());

        if (jTable_rp.getValueAt(selected_row, 3).toString().equalsIgnoreCase("BFA")) {
            jComboBox_famille_ed.setEnabled(false);
        } else {
            jComboBox_famille_ed.setEnabled(true);
            jComboBox_famille_ed.setSelectedItem(jTable_rp.getValueAt(selected_row, 4).toString());
        }

        jSpinner_prix_ed.setValue(jTable_rp.getValueAt(selected_row, 5));
        jSpinner_stock_ed.setValue(jTable_rp.getValueAt(selected_row, 6));
        txt_designation_editer.setCaretPosition(0);

        String old_desi = jTable_rp.getValueAt(selected_row, 1).toString();
        String old_nature = jTable_rp.getValueAt(selected_row, 2).toString();
        String old_sous = jTable_rp.getValueAt(selected_row, 3).toString();
        
          //String old_fami_string=jTable_rp.getValueAt(selected_row, 4).toString();
           //mod_prod.put("old_famill", old_fami_string);
           Object value = jTable_rp.getValueAt(selected_row,4);
            if (value!=null)
            {
                String sta = value.toString();
              int  old_famill = (Integer.parseInt(sta));
              mod_prod.put("old_famill", old_famill);
            }

         System.out.println(jTable_rp.getValueAt(selected_row, 4));        
        int old_famill;
        /*if(!old_fami_string.equalsIgnoreCase("")){
             old_famill = (Integer.parseInt(jTable_rp.getValueAt(selected_row, 4).toString()));
             mod_prod.put("old_famill", old_famill);
        }*/
       
        float old_pu = Float.parseFloat(jTable_rp.getValueAt(selected_row, 5).toString());
        float old_quan_stock = Float.parseFloat(jTable_rp.getValueAt(selected_row, 6).toString());

        mod_prod.put("old_desi", old_desi);
        mod_prod.put("old_nature", old_nature);
        mod_prod.put("old_sous", old_sous);
        
        mod_prod.put("old_pu", old_pu);
        mod_prod.put("old_quan_stock", old_quan_stock);
        System.out.println("-----");
        printMap(mod_prod);
        System.out.println("-----");

    }//GEN-LAST:event_jTable_rpMouseClicked

    private void jTabbedPane1CaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_jTabbedPane1CaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jTabbedPane1CaretPositionChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int selected_row = jTable_c12.getSelectedRow();
        if (selected_row > -1) {
            String id = jTable_c12.getValueAt(selected_row, 0).toString();
            System.out.println(id);

            try {
                PreparedStatement pst;
                ResultSet rs;//resulta de requet
                Connection conn = Connect.ConnectDB("infra2");
                String sql = "delete from c12 where id_c12=?";
                pst = conn.prepareStatement(sql);

                pst.setString(1, id);
                pst.execute();
                System.out.println("departemetn " + id + " supprimé");
                DefaultTableModel model2 = (DefaultTableModel) jTable_departement.getModel();
                model2.removeRow(selected_row);
                jTable_departement.setModel(model2);
            } catch (SQLException ex) {
                Logger.getLogger(Normal_user.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
       
            int row = jTable_c12.getSelectedRow();
            String id_c12 = jTable_c12.getValueAt(row, 0).toString();

            String query = "SELECT *\n"
                    + "FROM C12\n"
                    + "LEFT JOIN employe emp ON C12.id_emp = emp.id_employe\n"
                    + "JOIN departement dep ON C12.id_dep = dep.id_dep\n"
                    + "JOIN relation_c12_pro re ON C12.id_c12 = re.id_c12\n"
                    + "JOIN produit pr ON re.id_pro = pr.id "
                    + "where C12.id_c12='" + id_c12 + "'";
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("id");
            model.addColumn("Designation");
            model.addColumn("Nature");
            model.addColumn("Sous-Ch");
            model.addColumn("Famille");
            model.addColumn("demandé");
            model.addColumn("livré");
            model.addColumn("Total TTC");

            try {
                Connection conn1 = Connect.ConnectDB("infra2");
                Statement stm = conn1.createStatement();
                ResultSet rs = stm.executeQuery(query);
                int id = 0;
                float prix_tot = 0;

                while (rs.next()) {

                    id += 1;
                    Float prix = rs.getFloat("prix");
                    int quanti_livr = rs.getInt("qua_livre");
                    float somme = prix * quanti_livr;
                    prix_tot += somme;
                    model.addRow(new Object[]{
                        id,
                        rs.getString("designation"),
                        rs.getString("type_C_N"),
                        rs.getString("sous"),
                        rs.getString("type_sous"),
                        rs.getInt("qua_demende"),
                        rs.getInt("qua_livre"),
                        somme
                    });
                }
                jLabel_total_c12.setText(prix_tot + "");

                jLabel_c_nc_c12.setText(jTable_c12.getValueAt(row, 5).toString());
                jLabel_date_c12.setText(jTable_c12.getValueAt(row, 2).toString());
                jLabel_livr_c12.setText(jTable_c12.getValueAt(row, 3).toString());
                jLabel_dep_c12.setText(jTable_c12.getValueAt(row, 6).toString());
                jLabel_employe_c12.setText(jTable_c12.getValueAt(row, 7).toString());
                jLabel_num_c12.setText(jTable_c12.getValueAt(row, 1).toString());
                jLabel_sous_c12.setText(jTable_c12.getValueAt(row, 4).toString());
                jTextArea_rem_c12.setText(jTable_c12.getValueAt(row, 8).toString());

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
                e.getSQLState();
                e.printStackTrace();
            }

            TableColumnModel columnModel = jtable_aficher_c12.getColumnModel();
            jtable_aficher_c12.setModel(model);
            jDialog_afficher_c12.setVisible(true);
        

    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Normal_user.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Normal_user.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Normal_user.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Normal_user.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Normal_user().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.jidesoft.plaf.basic.BasicJideLabelUI basicJideLabelUI1;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JButton jButton_ajouter;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox_retournable_ajouter;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox_bfa_c12;
    private javax.swing.JComboBox<String> jComboBox_bfa_rc12;
    private javax.swing.JComboBox<String> jComboBox_bfarc121;
    private javax.swing.JComboBox<String> jComboBox_c_nc_ajout;
    private javax.swing.JComboBox<String> jComboBox_c_nc_ajouter_c12;
    private javax.swing.JComboBox<String> jComboBox_c_nc_edi;
    private javax.swing.JComboBox<String> jComboBox_c_nc_r_p;
    private javax.swing.JComboBox<String> jComboBox_c_nc_rc12;
    private javax.swing.JComboBox<String> jComboBox_depar_c12;
    private javax.swing.JComboBox<String> jComboBox_depar_rc12;
    private javax.swing.JComboBox<String> jComboBox_emp_ajout_c12;
    private javax.swing.JComboBox<String> jComboBox_emplo_rc12;
    private javax.swing.JComboBox<String> jComboBox_famille_ajout;
    private javax.swing.JComboBox<String> jComboBox_famille_ed;
    private javax.swing.JComboBox<String> jComboBox_famille_rp;
    private javax.swing.JComboBox<String> jComboBox_livr_rc12;
    private javax.swing.JComboBox<String> jComboBox_livr_re;
    private javax.swing.JComboBox<String> jComboBox_prod_rc12;
    private javax.swing.JComboBox<String> jComboBox_produit_c12;
    private javax.swing.JComboBox<String> jComboBoxbfa_p91_ajout;
    private javax.swing.JComboBox<String> jComboBoxbfa_p9_recher;
    private javax.swing.JComboBox<String> jComboBoxbfa_p9_rp;
    private javax.swing.JComboBox<String> jComboBoxbfa_ped;
    private com.toedter.calendar.JDateChooser jDateChooser_ajouter_c12;
    private javax.swing.JDialog jDialog_afficher_c12;
    private javax.swing.JDialog jDialog_ajouter_c12;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel_c_nc_c12;
    private javax.swing.JLabel jLabel_date_c12;
    private javax.swing.JLabel jLabel_dep_c12;
    private javax.swing.JLabel jLabel_employe_c12;
    private javax.swing.JLabel jLabel_livr_c12;
    private javax.swing.JLabel jLabel_num_c12;
    private javax.swing.JLabel jLabel_quant_min;
    private javax.swing.JLabel jLabel_sous_c12;
    private javax.swing.JLabel jLabel_tot_c12;
    private javax.swing.JLabel jLabel_total_c12;
    private com.toedter.calendar.JMonthChooser jMonthChooser1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPanel_prods_c12;
    private javax.swing.JRadioButton jRadioButton_eq;
    private javax.swing.JRadioButton jRadioButton_eq_stock;
    private javax.swing.JRadioButton jRadioButton_great;
    private javax.swing.JRadioButton jRadioButton_greater_stock;
    private javax.swing.JRadioButton jRadioButton_less;
    private javax.swing.JRadioButton jRadioButton_less_stock;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSpinner jSpinner_prix_ajout;
    private javax.swing.JSpinner jSpinner_prix_ed;
    private javax.swing.JSpinner jSpinner_quanti_min;
    private javax.swing.JSpinner jSpinner_stock_ajout;
    private javax.swing.JSpinner jSpinner_stock_ed;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable_alertes;
    private javax.swing.JTable jTable_c12;
    private javax.swing.JTable jTable_departement;
    private javax.swing.JTable jTable_rp;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea_info_dep;
    private javax.swing.JTextArea jTextArea_rem_c12;
    private javax.swing.JTextField jTextField__delivre_c12;
    private javax.swing.JTextField jTextField_deman_c12;
    private javax.swing.JTextField jTextField_desig_ajout;
    private javax.swing.JTextField jTextField_nom_dep;
    private javax.swing.JTextField jTextField_num_dep;
    private javax.swing.JTextField jTextField_num_rc12;
    private javax.swing.JTextField jTextField_numero_c12;
    private javax.swing.JTextField jTextField_prix_rp;
    private javax.swing.JTextField jTextField_resp_dep;
    private javax.swing.JTextField jTextField_stock_c12;
    private javax.swing.JTextField jTextField_stock_rp1;
    private javax.swing.JTextField jTextField_tel_dep;
    private com.toedter.calendar.JYearChooser jYearChooser1;
    private javax.swing.JCheckBox jcheck_garder_fenetre;
    private javax.swing.JComboBox<String> jcombop90;
    private javax.swing.JTable jtable_aficher_c12;
    private javax.swing.JTable jtable_ajouter_produits;
    private javax.swing.JTable jtable_produit;
    private com.jidesoft.converter.LongConverter longConverter1;
    private com.jidesoft.spinner.SpinnerPointModel spinnerPointModel1;
    private javax.swing.JTextField txt_designation_editer;
    private javax.swing.JTextField txt_designation_recher;
    // End of variables declaration//GEN-END:variables

}
