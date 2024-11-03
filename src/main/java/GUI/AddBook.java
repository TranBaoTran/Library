/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI;

import BUS.BookBUS;
import DTO.AuthorDTO;
import DTO.BookNameDTO;
import DTO.CategoryDTO;
import DTO.FullBookDTO;
import DTO.PublisherDTO;
import MyDesign.MyTextField_Basic;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author User
 */
public class AddBook extends javax.swing.JPanel implements BarcodeListener{
    Scanner_Dialog scannerDialog = new Scanner_Dialog();
    String idScan = "";
    String imgURL = "";
    AuthorDTO au = new AuthorDTO();
    CategoryDTO cate = new CategoryDTO();
    FullBookDTO fullbook = new FullBookDTO();
    BookBUS bookBUS;
    /**
     * Creates new form AddBook
     */
    public AddBook() {
        initComponents();
        jScrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        try {
            bookBUS = new BookBUS();
        } catch (ClassNotFoundException | SQLException | IOException ex) {
            Logger.getLogger(ChooseAuthorDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void updateAuthorTable(){
        authorTable.setRowCount(0);
        for(int i=0;i<fullbook.getAuthors().size();i++){
            Object row[] = {fullbook.getAuthors().get(i).getName()};
            authorTable.addRow(row);
        }
    }
    
    private void updateCategoryTable(){
        cateTable.setRowCount(0);
        for(int i=0;i<fullbook.getCategories().size();i++){
            Object row[] = {fullbook.getCategories().get(i).getName()};
            cateTable.addRow(row);
        }
    }

    public void disableSetUpBook(){
        switch(fullbook.getStatus()){
            case "bookNameExisted":
                findAuthorButton.setVisible(false);
                delAuthorButton.setVisible(false);
                findCateButton.setVisible(false);
                delCateButton.setVisible(false);
                findBookNameButton.setVisible(true);
                findPublisherButton.setVisible(true);
                editionTextField.setEditable(true);
                priceTextField.setEditable(true);
                break;
            case "ISBNExisted":
                findAuthorButton.setVisible(false);
                delAuthorButton.setVisible(false);
                findCateButton.setVisible(false);
                delCateButton.setVisible(false);
                findBookNameButton.setVisible(false);
                findPublisherButton.setVisible(false);
                editionTextField.setEditable(false);
                priceTextField.setEditable(false);
                break;
            default:
                findAuthorButton.setVisible(true);
                delAuthorButton.setVisible(true);
                findCateButton.setVisible(true);
                delCateButton.setVisible(true);
                findBookNameButton.setVisible(true);
                findPublisherButton.setVisible(true);
                editionTextField.setEditable(true);
                priceTextField.setEditable(true);
                break;
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
        pnImageBook = new MyDesign.PanelBorder_Basic();
        lbImageBook = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        ISBNTextField = new MyDesign.MyTextField_Basic();
        scanButton = new MyDesign.MyButton();
        jLabel5 = new javax.swing.JLabel();
        findBookNameButton = new MyDesign.MyButton();
        jLabel6 = new javax.swing.JLabel();
        findPublisherButton = new MyDesign.MyButton();
        jLabel7 = new javax.swing.JLabel();
        editionTextField = new MyDesign.MyTextField_Basic();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        authorTable = new MyDesign.MyTable();
        findAuthorButton = new MyDesign.MyButton();
        publisherLabel = new javax.swing.JLabel();
        bookNameLabel = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        cateTable = new MyDesign.MyTable();
        findCateButton = new MyDesign.MyButton();
        jLabel10 = new javax.swing.JLabel();
        quantitySpinner = new javax.swing.JSpinner();
        delAuthorButton = new MyDesign.MyButton();
        delCateButton = new MyDesign.MyButton();
        jLabel11 = new javax.swing.JLabel();
        priceTextField = new MyDesign.MyTextField_Basic();

        setBackground(new java.awt.Color(255, 255, 255));

        pnImageBook.setPreferredSize(new java.awt.Dimension(137, 192));

        lbImageBook.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbImageBook.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/img/AddImage.png"))); // NOI18N
        lbImageBook.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbImageBookMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnImageBookLayout = new javax.swing.GroupLayout(pnImageBook);
        pnImageBook.setLayout(pnImageBookLayout);
        pnImageBookLayout.setHorizontalGroup(
            pnImageBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 151, Short.MAX_VALUE)
            .addGroup(pnImageBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnImageBookLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lbImageBook, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        pnImageBookLayout.setVerticalGroup(
            pnImageBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 210, Short.MAX_VALUE)
            .addGroup(pnImageBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnImageBookLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lbImageBook, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("ISBN\n\n");

        ISBNTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ISBNTextFieldActionPerformed(evt);
            }
        });

        scanButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/img/icon/scan.png"))); // NOI18N
        scanButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                scanButtonActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Tên sách  ");

        findBookNameButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/img/icon/search.png"))); // NOI18N
        findBookNameButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                findBookNameButtonActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Phiên bản");

        findPublisherButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/img/icon/search.png"))); // NOI18N
        findPublisherButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                findPublisherButtonActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("Nhà xuất bản");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("Tác giả");

        authorTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Tên tác giả"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(authorTable);
        if (authorTable.getColumnModel().getColumnCount() > 0) {
            authorTable.getColumnModel().getColumn(0).setResizable(false);
        }

        findAuthorButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/img/icon/search.png"))); // NOI18N
        findAuthorButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                findAuthorButtonActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setText("Thể loại");

        cateTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Tên thể loại"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(cateTable);
        if (cateTable.getColumnModel().getColumnCount() > 0) {
            cateTable.getColumnModel().getColumn(0).setResizable(false);
        }

        findCateButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/img/icon/search.png"))); // NOI18N
        findCateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                findCateButtonActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setText("Số lượng");

        quantitySpinner.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));

        delAuthorButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/img/icon/tru.png"))); // NOI18N
        delAuthorButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delAuthorButtonActionPerformed(evt);
            }
        });

        delCateButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/img/icon/tru.png"))); // NOI18N
        delCateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delCateButtonActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setText("Giá tiền");

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnImageBook, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(quantitySpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(priceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6))
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addGap(47, 47, 47)
                                .addComponent(publisherLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(editionTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(67, 67, 67)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(ISBNTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addGap(67, 67, 67)
                                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(bookNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(18, 18, 18)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scanButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(findBookNameButton, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(findAuthorButton, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(findPublisherButton, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(findCateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(delAuthorButton, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(delCateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ISBNTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(16, 16, 16)
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bookNameLabel))
                        .addGap(19, 19, 19)
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addGap(47, 47, 47)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(editionTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(scanButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13)
                        .addComponent(findBookNameButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)
                        .addComponent(findAuthorButton, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(delAuthorButton, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(pnImageBook, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(quantitySpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(publisherLabel)
                    .addComponent(findPublisherButton, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(findCateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(delCateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19))
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(priceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void scanButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_scanButtonActionPerformed
        // TODO add your handling code here:
        scannerDialog.initAndShowGUI(this);
    }//GEN-LAST:event_scanButtonActionPerformed

    private void findCateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_findCateButtonActionPerformed
        // TODO add your handling code here:
        ChooseCateDialog whid = new ChooseCateDialog(new javax.swing.JFrame(), true, this.cate, this::updateCategory);
        whid.setVisible(true);
    }//GEN-LAST:event_findCateButtonActionPerformed

    private void updateCategory(){
        if (cate.getName() == null || cate.getName().isBlank()){
            return;
        }
        for(int i=0 ; i < fullbook.getCategories().size() ; i++){
            if (cate.getId() == fullbook.getCategories().get(i).getId()){
                JOptionPane.showMessageDialog(null, "Đã chọn thể loại này");
                cate.setName("");
                return;
            }
        }
        fullbook.getCategories().add(new CategoryDTO(cate.getId(), cate.getName()));
        cate.setName("");
        updateCategoryTable();
    }
    
    private void findBookNameButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_findBookNameButtonActionPerformed
        // TODO add your handling code here:
        ChooseBookDialog whid = new ChooseBookDialog(new javax.swing.JFrame(), true, fullbook.getBookName(), this::updateBookName);
        whid.setVisible(true);
    }//GEN-LAST:event_findBookNameButtonActionPerformed

    private void updateBookNameText(){
        bookNameLabel.setText(fullbook.getBookName().getName());
    }
    
    private void updateBookName(){
        updateBookNameText();
        try {
            if (bookBUS.checkBookName(fullbook.getBookName().getId())){
                fullbook.setAuthors(bookBUS.getBookAuthor(fullbook.getBookName().getId()));
                fullbook.setCategories(bookBUS.getBookCategory(fullbook.getBookName().getId()));
                updateAuthorTable();
                updateCategoryTable();
                fullbook.setStatus("bookNameExisted");
            }else{
                fullbook.getAuthors().clear();
                fullbook.getCategories().clear();
                updateAuthorTable();
                updateCategoryTable();
                fullbook.setStatus("bookNameNew");
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddBook.class.getName()).log(Level.SEVERE, null, ex);
        }
        disableSetUpBook();
    }
    
    private void findAuthorButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_findAuthorButtonActionPerformed
        // TODO add your handling code here:
        ChooseAuthorDialog whid = new ChooseAuthorDialog(new javax.swing.JFrame(), true, this.au, this::updateAuthor);
        whid.setVisible(true);
    }//GEN-LAST:event_findAuthorButtonActionPerformed

    private void updateAuthor(){
        if (au.getName() == null || au.getName().isBlank()){
            return;
        }
        for(int i=0 ; i < fullbook.getAuthors().size() ; i++){
            if (au.getId() == fullbook.getAuthors().get(i).getId()){
                JOptionPane.showMessageDialog(null, "Đã chọn tác giả này");
                au.setName("");
                return;
            }
        }
        fullbook.getAuthors().add(new AuthorDTO(au.getId(), au.getName()));
        au.setName("");
        updateAuthorTable();
    }
    
    private void findPublisherButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_findPublisherButtonActionPerformed
        // TODO add your handling code here:
        ChoosePublisherDialog whid = new ChoosePublisherDialog(new javax.swing.JFrame(), true, fullbook.getPublisher(), this::updatePublisher);
        whid.setVisible(true);
    }//GEN-LAST:event_findPublisherButtonActionPerformed

    private void updatePublisher(){
        publisherLabel.setText(fullbook.getPublisher().getName());
    }
    
    private void lbImageBookMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbImageBookMouseClicked
        // TODO add your handling code here:
        if(fullbook.getStatus() != null && fullbook.getStatus().equals("ISBNExisted")){
            JOptionPane.showMessageDialog(null, "Không thể chỉnh sửa ảnh của sách có sẵn");
            return;
        }
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Chọn tệp ảnh .png, .jpg");            
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Tệp ảnh .png, .jpg", "png", "jpg");
        fileChooser.setFileFilter(filter);
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            String selectedImagePath = fileChooser.getSelectedFile().getAbsolutePath();
            if(selectedImagePath==null)
                {
                    JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Không tìm thấy ảnh.","Thông Báo",JOptionPane.INFORMATION_MESSAGE);
                    lbImageBook.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/img/AddImage.png")));
                    imgURL = "";
                }else {
                    JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Đã chọn ảnh: " + selectedImagePath);
                    lbImageBook.setIcon(new ImageIcon(new javax.swing.ImageIcon(selectedImagePath).getImage().getScaledInstance(135, 192,Image.SCALE_SMOOTH)));
                    imgURL = selectedImagePath;
                }
        }
    }//GEN-LAST:event_lbImageBookMouseClicked

    private void delAuthorButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delAuthorButtonActionPerformed
        // TODO add your handling code here:
        int row = authorTable.getSelectedRow();
        if (row >= 0) {
            fullbook.getAuthors().remove(row);
            updateAuthorTable();
        }else{
            JOptionPane.showMessageDialog(null, "Vui lòng chọn tác giả muốn bỏ.");
        }   
    }//GEN-LAST:event_delAuthorButtonActionPerformed

    private void delCateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delCateButtonActionPerformed
        // TODO add your handling code here:
        int row = cateTable.getSelectedRow();
        if (row >= 0) {
            fullbook.getCategories().remove(row);
            updateCategoryTable();
        }else{
            JOptionPane.showMessageDialog(null, "Vui lòng chọn thể loại muốn bỏ.");
        }  
    }//GEN-LAST:event_delCateButtonActionPerformed

    private void ISBNTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ISBNTextFieldActionPerformed
        // TODO add your handling code here:
        String ISBN = ISBNTextField.getText().trim();
        if (!ISBN.matches("^97[89]\\d{10}$")){
            JOptionPane.showMessageDialog(null, "ISBN phải là thuộc cấu trúc ISBN-13");
        }
        try {
            fullbook = bookBUS.getFullBook(ISBN);
        } catch (SQLException ex) {
            Logger.getLogger(AddBook.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (fullbook.getISBN() == null || fullbook.getISBN().isBlank()){
            fullbook.setStatus("ISBNNew");
        }else{
            updateBookName();
            setUpBookFromTable(false);
            fullbook.setStatus("ISBNExisted");
            lbImageBook.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource(fullbook.getImg())).getImage().getScaledInstance(135, 192,Image.SCALE_SMOOTH)));
        }
        disableSetUpBook();
    }//GEN-LAST:event_ISBNTextFieldActionPerformed

    public void setUpBookFromTable(boolean isFromTable){
        updateAuthorTable();
        updateCategoryTable();
        editionTextField.setText(fullbook.getEdition());
        updatePublisher();
        priceTextField.setText(String.valueOf(fullbook.getPrice())); 
        if (isFromTable){
            updateBookNameText();
            quantitySpinner.setValue(fullbook.getQuantity());
            ISBNTextField.setText(fullbook.getISBN());
            if ("ISBNExisted".equals(fullbook.getStatus()) || "reset".equals(fullbook.getStatus())){
                lbImageBook.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource(fullbook.getImg())).getImage().getScaledInstance(135, 192,Image.SCALE_SMOOTH)));
            }else{
                lbImageBook.setIcon(new ImageIcon(new javax.swing.ImageIcon(fullbook.getImg()).getImage().getScaledInstance(135, 192,Image.SCALE_SMOOTH)));
            }
            disableSetUpBook();
        }      
    }
    
    public void resetBook(){
        fullbook = new FullBookDTO();
        fullbook.setQuantity(1);
        fullbook.setImg("/asset/img/AddImage.png");
        fullbook.setStatus("reset");
        imgURL = "";
        setUpBookFromTable(true);
    }
    
    @Override
    public void onBarcodeScanned(String barcode) {
        idScan = barcode;
        ISBNTextField.setText(idScan);
    }
    
    public void getData(){
        
    }
    
    public String getISBNTextField(){
        return ISBNTextField.getText().trim();
    }
    
    public String getEditionTextField(){
        return editionTextField.getText().trim();
    }
    
    public long getPriceTextField() throws NumberFormatException {
        return Long.parseLong(priceTextField.getText().trim());
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private MyDesign.MyTextField_Basic ISBNTextField;
    private MyDesign.MyTable authorTable;
    private javax.swing.JLabel bookNameLabel;
    private MyDesign.MyTable cateTable;
    private MyDesign.MyButton delAuthorButton;
    private MyDesign.MyButton delCateButton;
    private MyDesign.MyTextField_Basic editionTextField;
    private MyDesign.MyButton findAuthorButton;
    private MyDesign.MyButton findBookNameButton;
    private MyDesign.MyButton findCateButton;
    private MyDesign.MyButton findPublisherButton;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbImageBook;
    private MyDesign.PanelBorder panelBorder1;
    private MyDesign.PanelBorder_Basic pnImageBook;
    private MyDesign.MyTextField_Basic priceTextField;
    private javax.swing.JLabel publisherLabel;
    private javax.swing.JSpinner quantitySpinner;
    private MyDesign.MyButton scanButton;
    // End of variables declaration//GEN-END:variables

    public JSpinner getQuantitySpinner() {
        return quantitySpinner;
    }
}