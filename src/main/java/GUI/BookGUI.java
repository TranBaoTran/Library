/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import BUS.BookBUS;
import DTO.AuthorDTO;
import DTO.BookDTO;
import DTO.CategoryDTO;
import DTO.PublisherDTO;

/**
 *
 * @author User
 */
public class BookGUI extends javax.swing.JPanel {
    private BookBUS bookBus;
    private List<BookDTO> books;
    private  BookDTO targetBook;
    private Map<String, String> searchCondition;

    /**
     * Creates new form BookGUI
     */
    public BookGUI() {
        try {
            bookBus = new BookBUS();
            this.books = bookBus.getAllBook();
        } catch (ClassNotFoundException | SQLException | IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error initializing database connection: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        initComponents();
        jScrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        updateTable();
        searchCondition = new HashMap<>();

        loadAuthorData();
        loadCategoryData();
        loadNXBData();
    }
    
    private void updateTable(){
        myTable1.setRowCount(0);      
        for (BookDTO i : books){
            Object row[] = {i.getISBN(), i.getName(), i.getEdition(), i.getLocation(), i.getQuantity()};
            myTable1.addRow(row);
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

        panelBorder1 = new MyDesign.PanelBorder();
        panelBorder_Basic1 = new MyDesign.PanelBorder_Basic();
        jLabel8 = new javax.swing.JLabel();
        txtFindByISBN = new MyDesign.SearchText();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        panelBorder_Basic2 = new MyDesign.PanelBorder_Basic();
        jLabel9 = new javax.swing.JLabel();
        txtFindByName = new MyDesign.SearchText();
        jLabel3 = new javax.swing.JLabel();
        panelBorder_Basic4 = new MyDesign.PanelBorder_Basic();
        jLabel11 = new javax.swing.JLabel();
        txtFindByISBN1 = new MyDesign.SearchText();
        jLabel4 = new javax.swing.JLabel();
        cbAuthor = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        cbCategory = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        cbPublisher = new javax.swing.JComboBox<>();
        btFind = new MyDesign.MyButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        myTable1 = new MyDesign.MyTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        bookDetail = new GUI.BookDetail();
        editBookButton1 = new MyDesign.MyButton();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/img/icon/search.png"))); // NOI18N

        txtFindByISBN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFindByISBNActionPerformed(evt);
            }
        });

        // Click button tìm kiếm
        btFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFindByISBNActionPerformed(evt);
                txtFindByNameActionPerformed(evt);
                txtFindByISBN1ActionPerformed(evt);
                cbAuthorCategoryPublisherActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelBorder_Basic1Layout = new javax.swing.GroupLayout(panelBorder_Basic1);
        panelBorder_Basic1.setLayout(panelBorder_Basic1Layout);
        panelBorder_Basic1Layout.setHorizontalGroup(
            panelBorder_Basic1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder_Basic1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtFindByISBN, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelBorder_Basic1Layout.setVerticalGroup(
            panelBorder_Basic1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder_Basic1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBorder_Basic1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder_Basic1Layout.createSequentialGroup()
                        .addComponent(txtFindByISBN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("ISBN");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Tên");

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/img/icon/search.png"))); // NOI18N

        txtFindByName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFindByNameActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelBorder_Basic2Layout = new javax.swing.GroupLayout(panelBorder_Basic2);
        panelBorder_Basic2.setLayout(panelBorder_Basic2Layout);
        panelBorder_Basic2Layout.setHorizontalGroup(
            panelBorder_Basic2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder_Basic2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtFindByName, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelBorder_Basic2Layout.setVerticalGroup(
            panelBorder_Basic2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder_Basic2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBorder_Basic2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder_Basic2Layout.createSequentialGroup()
                        .addComponent(txtFindByName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Phiên bản");

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/img/icon/search.png"))); // NOI18N

        txtFindByISBN1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFindByISBN1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelBorder_Basic4Layout = new javax.swing.GroupLayout(panelBorder_Basic4);
        panelBorder_Basic4.setLayout(panelBorder_Basic4Layout);
        panelBorder_Basic4Layout.setHorizontalGroup(
            panelBorder_Basic4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder_Basic4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtFindByISBN1, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelBorder_Basic4Layout.setVerticalGroup(
            panelBorder_Basic4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder_Basic4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBorder_Basic4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder_Basic4Layout.createSequentialGroup()
                        .addComponent(txtFindByISBN1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Thể loại");

        cbAuthor.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        cbAuthor.setBorder(null);
        cbAuthor.setOpaque(true);
        cbAuthor.setPreferredSize(new java.awt.Dimension(77, 28));
        cbAuthor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tác giả" }));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Tác giả");

        cbCategory.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        cbCategory.setBorder(null);
        cbCategory.setOpaque(true);
        cbCategory.setPreferredSize(new java.awt.Dimension(77, 28));
        cbCategory.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Thể loại" }));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("NXB");

        cbPublisher.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        cbPublisher.setBorder(null);
        cbPublisher.setOpaque(true);
        cbPublisher.setPreferredSize(new java.awt.Dimension(77, 28));
        cbPublisher.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nhà xuất bản" }));

        btFind.setText("Tìm kiếm");

        myTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ISBN", "Tên sách", "Phiên bản", "Kệ sách", "Số lượng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });

        // Khi click vào 1 sách bất kỳ
        clickBook();

        jScrollPane2.setViewportView(myTable1);
        if (myTable1.getColumnModel().getColumnCount() > 0) {
            myTable1.getColumnModel().getColumn(0).setResizable(false);
            myTable1.getColumnModel().getColumn(1).setResizable(false);
            myTable1.getColumnModel().getColumn(2).setResizable(false);
            myTable1.getColumnModel().getColumn(3).setResizable(false);
            myTable1.getColumnModel().getColumn(4).setResizable(false);
        }

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(panelBorder_Basic4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(panelBorder_Basic2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(panelBorder_Basic1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(70, 70, 70)
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbAuthor, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel6))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbPublisher, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGap(242, 242, 242)
                        .addComponent(btFind, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 548, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(9, Short.MAX_VALUE))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1)
                        .addGap(28, 28, 28)
                        .addComponent(jLabel2)
                        .addGap(29, 29, 29)
                        .addComponent(jLabel3))
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelBorder1Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(panelBorder_Basic1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panelBorder1Layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(cbAuthor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel5))))
                                .addGap(7, 7, 7)
                                .addComponent(panelBorder_Basic2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addGap(55, 55, 55)
                                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(cbCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(panelBorder_Basic4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6)
                                    .addComponent(cbPublisher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btFind, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jScrollPane1.setBorder(null);

        bookDetail.setAutoscrolls(true);
        jScrollPane1.setViewportView(bookDetail);

        editBookButton1.setForeground(new java.awt.Color(248, 67, 67));
        editBookButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/img/icon/action-delete-white.png"))); // NOI18N
        editBookButton1.setText("Xoá sách");
        editBookButton1.setColor(new java.awt.Color(255, 241, 241));
        editBookButton1.setColorOver(new java.awt.Color(255, 241, 241));
        editBookButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        // Khi nhấn vào button xóa sách
        editBookButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmDelete();
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(editBookButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(122, 122, 122))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 495, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                        .addComponent(editBookButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtFindByISBNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFindByISBNActionPerformed
        String text = txtFindByISBN.getText().trim();
        searchCondition.put("v.ISBN", text);
    }//GEN-LAST:event_txtFindByISBNActionPerformed

    private void txtFindByNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFindByNameActionPerformed
        String text = txtFindByName.getText().trim();
        searchCondition.put("b.name", text);
    }//GEN-LAST:event_txtFindByNameActionPerformed

    private void txtFindByISBN1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFindByISBN1ActionPerformed
        String text = txtFindByISBN1.getText().trim();
        searchCondition.put("v.edition", text);
    }//GEN-LAST:event_txtFindByISBN1ActionPerformed

    private void cbAuthorCategoryPublisherActionPerformed(java.awt.event.ActionEvent evt) {
        String authorSelectedText = cbAuthor.getSelectedItem().toString();
        String categorySelectedText = cbCategory.getSelectedItem().toString();
        String publisherSelectedText = cbPublisher.getSelectedItem().toString();
    
        // Thêm hoặc xóa các điều kiện tìm kiếm
        updateSearchCondition("a.name", authorSelectedText, "Tác giả");
        updateSearchCondition("c.name", categorySelectedText, "Thể loại");
        updateSearchCondition("p.name", publisherSelectedText, "Nhà xuất bản");
    
        try {
            loadBookData(searchCondition);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    private void updateSearchCondition(String key, String selectedText, String defaultText) {
        if (!selectedText.equals(defaultText)) {
            searchCondition.put(key, selectedText);
        } else {
            searchCondition.remove(key);
        }
    }

    // Load danh sách tác giả
    private void loadAuthorData() {
        try {
            Vector<AuthorDTO> authors = bookBus.getAllAuthor();
            cbAuthor.removeAllItems();
            cbAuthor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Tác giả"}));
            for (AuthorDTO author : authors) {
                cbAuthor.addItem(author.getName());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading authors: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Load danh sach category
    private void loadCategoryData() {
        try {
            Vector<CategoryDTO> categories = bookBus.getAllCategory();
            cbCategory.removeAllItems();
            cbCategory.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Thể loại" }));
            for (CategoryDTO category : categories) {
                cbCategory.addItem(category.getName());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading authors: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Load danh sach nhà xuất bản
    private void loadNXBData() {
        try { 
            Vector<PublisherDTO> publishers = bookBus.getAllPublisher();
            cbPublisher.removeAllItems();
            cbPublisher.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nhà xuất bản" }));

            for (PublisherDTO publisher : publishers) {
                cbPublisher.addItem(publisher.getName());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading authors: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Load danh sach book
    private void loadBookData(Map<String, String> conditions) {
        try {
            books = bookBus.getAllBookByCondition(conditions);
            DefaultTableModel model = (DefaultTableModel) myTable1.getModel();
            model.setRowCount(0);
        
            for (BookDTO book : books) {
                model.addRow(new Object[]{
                    book.getISBN(),
                    book.getName(),
                    book.getEdition(),
                    book.getLocation(),
                    book.getQuantity()
                });
            }

            // Lấy book đầu tiên để hiển thị chi tiết
            if (!books.isEmpty()) {
                targetBook = books.get(0); // Lấy đối tượng BookDTO đầu tiên          
                bookDetail.setBookDTO(targetBook);
                bookDetail.setBook();
            } else {
                bookDetail.setBookDTO(null);
                bookDetail.showBook();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading authors: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clickBook() {
        myTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = myTable1.rowAtPoint(evt.getPoint());
                if (row >= 0 && row < books.size()) {
                    targetBook = books.get(row); // Lấy sách từ hàng được click
                    bookDetail.setBookDTO(targetBook);
                    bookDetail.setBook(); // Hiển thị chi tiết sách
                }
            }
        });    
    }

    private void confirmDelete() {
        // Hiển thị hộp thoại xác nhận
        int response = JOptionPane.showConfirmDialog(null, 
                "Bạn có muốn xóa sách này không?", 
                "Xác nhận xóa", 
                JOptionPane.YES_NO_OPTION, 
                JOptionPane.QUESTION_MESSAGE);

        // Kiểm tra phản hồi từ người dùng
        if (response == JOptionPane.YES_OPTION) {
            deleteBook(); // Gọi phương thức xóa sách
        }
        // Nếu chọn NO, không làm gì cả
    }

    private void deleteBook() {
        String bookISBN = targetBook.getISBN();
        try {
            boolean isDeleted = bookBus.deleteBookByISBN(bookISBN);

            // Thực hiện xóa sách ở đây
            if (isDeleted) {
                JOptionPane.showMessageDialog(null, "This Book has been deleted.", "Notification", JOptionPane.INFORMATION_MESSAGE);
                loadBookData(searchCondition);
            } else {
                JOptionPane.showMessageDialog(null, "Please check the quantity of books on the shelves, the quantity in the warehouse and the quantity imported.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Delete this book error: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private GUI.BookDetail bookDetail;
    private MyDesign.MyButton btFind;
    private javax.swing.JComboBox<String> cbAuthor;
    private javax.swing.JComboBox<String> cbCategory;
    private javax.swing.JComboBox<String> cbPublisher;
    private MyDesign.MyButton editBookButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private MyDesign.MyTable myTable1;
    private MyDesign.PanelBorder panelBorder1;
    private MyDesign.PanelBorder_Basic panelBorder_Basic1;
    private MyDesign.PanelBorder_Basic panelBorder_Basic2;
    private MyDesign.PanelBorder_Basic panelBorder_Basic4;
    private MyDesign.SearchText txtFindByISBN;
    private MyDesign.SearchText txtFindByISBN1;
    private MyDesign.SearchText txtFindByName;
    // End of variables declaration//GEN-END:variables
}
