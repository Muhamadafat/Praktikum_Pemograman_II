import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class ItemManagementSystem extends JFrame {
    private JMenuBar menuBar;
    private JPanel mainPanel;
    private CardLayout cardLayout;

    public ItemManagementSystem() {
        setTitle("Sistem Manajemen Item");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Menu Bar
        menuBar = new JMenuBar();
        JMenu menuItems = new JMenu("Item");
        JMenuItem menuNewItem = new JMenuItem("Item Baru");
        JMenuItem menuCategories = new JMenuItem("Kategori");
        JMenuItem menuExit = new JMenuItem("Keluar");

        menuItems.add(menuNewItem);
        menuItems.add(menuCategories);
        menuItems.add(menuExit);
        menuBar.add(menuItems);
        setJMenuBar(menuBar);

        // CardLayout untuk berpindah antar panel
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        mainPanel.add(new NewItemPanel(), "Item Baru");
        mainPanel.add(new CategoriesPanel(), "Kategori");

        add(mainPanel);

        // Action Listener untuk menu items
        menuNewItem.addActionListener(e -> cardLayout.show(mainPanel, "Item Baru"));
        menuCategories.addActionListener(e -> cardLayout.show(mainPanel, "Kategori"));
        menuExit.addActionListener(e -> System.exit(0));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ItemManagementSystem().setVisible(true);
        });
    }
}

// Panel untuk Menambah Item
class NewItemPanel extends JPanel {
    private JTextField tfItemName;
    private JTextArea taDescription;
    private JRadioButton rbPersonal, rbWork;
    private JCheckBox cbFavorite;
    private JComboBox<String> cbCategory;
    private JSlider sliderQuality;
    private JSpinner spQuantity;
    private JTable tableItems;
    private DefaultTableModel tableModel;

    public NewItemPanel() {
        setLayout(new BorderLayout());

        // Form Panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Komponen untuk form
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Nama Item:"), gbc);
        gbc.gridx = 1;
        tfItemName = new JTextField(20);
        formPanel.add(tfItemName, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Deskripsi:"), gbc);
        gbc.gridx = 1;
        taDescription = new JTextArea(5, 20);
        formPanel.add(new JScrollPane(taDescription), gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(new JLabel("Kategori:"), gbc);
        gbc.gridx = 1;
        cbCategory = new JComboBox<>(new String[] { "Umum", "Makanan", "Minuman", "Kesehatan" });
        formPanel.add(cbCategory, gbc);

        // Pindahkan JCheckBox Favorit ke sebelah JComboBox Kategori
        gbc.gridx = 2;
        cbFavorite = new JCheckBox("Favorit");
        formPanel.add(cbFavorite, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(new JLabel("Kualitas (1-10):"), gbc);
        gbc.gridx = 1;
        sliderQuality = new JSlider(1, 10, 5);
        formPanel.add(sliderQuality, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        formPanel.add(new JLabel("Jumlah:"), gbc);
        gbc.gridx = 1;
        spQuantity = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
        formPanel.add(spQuantity, gbc);

        // Button Panel
        JPanel buttonPanel = new JPanel();
        JButton btnSave = new JButton("Simpan Item");
        buttonPanel.add(btnSave);

        // Table untuk Menampilkan Item
        String[] columns = { "Nama Item", "Deskripsi", "Kategori", "Favorit", "Kualitas", "Jumlah" };
        tableModel = new DefaultTableModel(columns, 0);
        tableItems = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tableItems);

        // Layout
        add(formPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);

        // Action Listener untuk tombol simpan
        btnSave.addActionListener(e -> saveItem());
    }

    private void saveItem() {
        Vector<Object> row = new Vector<>();
        row.add(tfItemName.getText());
        row.add(taDescription.getText());
        row.add(cbCategory.getSelectedItem());
        row.add(cbFavorite.isSelected() ? "Ya" : "Tidak");
        row.add(sliderQuality.getValue());
        row.add(spQuantity.getValue());
        tableModel.addRow(row);

        clearForm();
    }

    private void clearForm() {
        tfItemName.setText("");
        taDescription.setText("");
        cbCategory.setSelectedIndex(0);
        cbFavorite.setSelected(false);
        sliderQuality.setValue(5);
        spQuantity.setValue(1);
    }
}

// Panel untuk Kategori
class CategoriesPanel extends JPanel {
    private JTextField tfCategoryName;
    private JRadioButton rbPersonal, rbWork;
    private JTable tableCategories;
    private DefaultTableModel tableModel;
    private JList<String> categoryList;
    private DefaultListModel<String> listModel;

    public CategoriesPanel() {
        setLayout(new BorderLayout());

        // Form Panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Komponen untuk form
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Nama Kategori:"), gbc);
        gbc.gridx = 1;
        tfCategoryName = new JTextField(20);
        formPanel.add(tfCategoryName, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Tipe:"), gbc);
        gbc.gridx = 1;
        JPanel radioPanel = new JPanel();
        ButtonGroup bg = new ButtonGroup();
        rbPersonal = new JRadioButton("Pribadi");
        rbWork = new JRadioButton("Kerja");
        bg.add(rbPersonal);
        bg.add(rbWork);
        radioPanel.add(rbPersonal);
        radioPanel.add(rbWork);
        formPanel.add(radioPanel, gbc);

        // Button Panel
        JPanel buttonPanel = new JPanel();
        JButton btnSave = new JButton("Simpan Kategori");
        buttonPanel.add(btnSave);

        // Table untuk Kategori
        String[] columns = { "Nama Kategori", "Tipe" };
        tableModel = new DefaultTableModel(columns, 0);
        tableCategories = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tableCategories);

        // JList untuk menampilkan kategori
        listModel = new DefaultListModel<>();
        categoryList = new JList<>(listModel);
        JScrollPane listScrollPane = new JScrollPane(categoryList);

        // Layout
        JPanel centerPanel = new JPanel(new GridLayout(1, 2));
        centerPanel.add(scrollPane);
        centerPanel.add(listScrollPane);

        add(formPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(centerPanel, BorderLayout.SOUTH);

        // Action Listener untuk tombol simpan
        btnSave.addActionListener(e -> saveCategory());
    }

    private void saveCategory() {
        String categoryName = tfCategoryName.getText();
        String type = rbPersonal.isSelected() ? "Pribadi" : "Kerja";

        if (!categoryName.isEmpty()) {
            listModel.addElement(categoryName + " (" + type + ")");
            tableModel.addRow(new Object[] { categoryName, type });
        }
        clearForm();
    }

    private void clearForm() {
        tfCategoryName.setText("");
        rbPersonal.setSelected(false);
        rbWork.setSelected(false);
    }
}
