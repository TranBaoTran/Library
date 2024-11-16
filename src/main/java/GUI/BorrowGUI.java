/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI;

import BUS.BorrowBUS;
import BUS.BorrowDetailBUS;
import DTO.BorrowDTO;
import DTO.BorrowDetailDTO;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author User
 */
public class BorrowGUI extends javax.swing.JPanel implements BarcodeListener {

    Scanner_Dialog scannerDialog = new Scanner_Dialog();
    String idScan = "";
    BorrowBUS borrowBus = new BorrowBUS();
    BorrowDetailBUS borrowDetailBus = new BorrowDetailBUS();

    /**
     * Creates new form BorrowGUI
     */
    public BorrowGUI() {
        initComponents();
        jScrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);

        loadBorrowDetail(bookBorrowTable);
        loadBorrowData(borrowReceiptTable);

        staffIDLb.setText("TT00000001");
        searchEvent();
        readerEvent();
        ISBNEvent();
        quantityEvent();
        updateDescriptionEvent();
        scanButton2.addActionListener(evt -> addBorrowing());
        borrowDetailClick();
        
//        hfhf
    }

    /*==============================  XỬ LÝ EVENT  ==================================*/
    //xử lý sự kiện khi tìm kiếm phiếu mượn
    private void searchEvent() {
        txtFindBorrowReceipt.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                performSearch();
            }
        });

        returnedCheckBox.addActionListener(evt -> {
            performSearch();
        });

        notReturnedCheckBox.addActionListener(evt -> {
            performSearch();
        });

        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                performSearch(); // Gọi hàm search khi nhấn vào jLabel9
            }
        });
    }

    //xử lý sự kiện khi nhập mã đọc giả
    private void readerEvent() {
        // Lắng nghe sự kiện nhấn Enter trên readerIDTextField
        readerIDTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                isFocusHandled = true;  // Đặt cờ sau khi nhấn Enter
                handleReaderID();
            }
        });

        // Lắng nghe sự kiện khi con trỏ chuột rời khỏi readerIDTextField (mất focus)
        readerIDTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (!isFocusHandled) {
                    handleReaderID();
                }
                isFocusHandled = false;
            }
        });
    }

    private void ISBNEvent() {
        // Lắng nghe sự kiện nhấn Enter trên ISBNTextField
        ISBNTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                isFocusHandled = true;
                handleISBN();  // Đặt cờ sau khi nhấn Enter
            }
        });

        // Lắng nghe sự kiện khi con trỏ chuột rời khỏi readerIDTextField (mất focus)
        ISBNTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (!isFocusHandled) {
                    handleISBN();
                }
                isFocusHandled = false;  // Đặt cờ sau khi nhấn Enter
            }
        });
    }

    private void quantityEvent() {
        addBookButton.addActionListener(evt -> {
            int new_quantity = (Integer) jSpinner1.getValue() + 1;
            jSpinner1.setValue(new_quantity);
        });
        delBookButton.addActionListener(evt -> {
            int new_quantity = (Integer) jSpinner1.getValue() - 1;
            jSpinner1.setValue(new_quantity);
        });
        jSpinner1.addChangeListener(evt -> handleAddOrUpdateBook(isUpdateQuantity));
    }

    private void updateDescriptionEvent() {
        bookBorrowTable.getModel().addTableModelListener(e -> {
            if (e.getType() == TableModelEvent.UPDATE) {
                int row = e.getFirstRow();
                int column = e.getColumn();
                if (column == 2) { // Kiểm tra nếu ô cập nhật là ô mô tả
                    String newDescription = (String) bookBorrowTable.getValueAt(row, column);
                    BorrowDetailDTO borrowDetail = tempBorrowDetails.get(row);
                    if (borrowDetail != null) {
                        String ISBN = borrowDetail.getISBN();
                        updateDescription(ISBN, newDescription);
                    }
                }
            }
        });
    }

    private void borrowDetailClick() {
        borrowReceiptTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int selectedRow = borrowReceiptTable.getSelectedRow();

                if (selectedRow != -1) { // Kiểm tra nếu có dòng được chọn
                    // Giả sử borrowID là cột đầu tiên (cột 0) trong bảng borrowReceiptTable
                    int borrowID = (int) borrowReceiptTable.getValueAt(selectedRow, 0);

                    // Lấy danh sách chi tiết mượn từ BorrowDetailBus
                    Vector<BorrowDetailDTO> borrowDetails = borrowDetailBus.getBorrowDetails(borrowID);

                    BorrowDTO borrowDTO = borrowBus.selectABorrow(borrowID);
                    borrowDTO.setBorrowDetailDTO(borrowDetails);

                    // Thiết lập và hiển thị biên lai mượn
                    borrowReceipt1.setBorrowDTO(borrowDTO);
                    borrowReceipt1.showBorrowReceipt();
                }

                // Đăng ký listener để nhận thông báo
                borrowReceipt1.addBorrowListener(new BorrowListener() {
                    @Override
                    public void onBookReturned() {
                        loadBorrowData(borrowReceiptTable);
                    }
                });
            }
        });
    }

    /*==============================  XỬ LÝ BORROW  ==================================*/
    public void loadBorrowData(javax.swing.JTable borrowReceiptTable) {
        List<BorrowDTO> borrowList = borrowBus.sellectAll();
        // Tạo mô hình bảng
        DefaultTableModel model = (DefaultTableModel) borrowReceiptTable.getModel();
        model.setRowCount(0);

        for (BorrowDTO borrow : borrowList) {
            Object[] row = new Object[]{
                borrow.getId(),
                borrow.getReaderName(),
                borrow.getBorrowDate(),
                borrow.getReturnDate(),
                borrow.isIsActive() ? "Đang mượn" : "Đã trả" // Thay đổi theo tình trạng
            };
            model.addRow(row);
        }
    }

    public void searchBorrowData(javax.swing.JTable borrowReceiptTable, String keyword, boolean isReturned, boolean isNotReturned) {
        List<BorrowDTO> borrowList = borrowBus.sellectAll();
        DefaultTableModel model = (DefaultTableModel) borrowReceiptTable.getModel();
        model.setRowCount(0); // Xóa dữ liệu bảng hiện tại

        for (BorrowDTO borrow : borrowList) {
            // Kiểm tra từ khóa có tồn tại trong tên người đọc hoặc ID phiếu mượn
            boolean matchesKeyword = keyword.isEmpty()
                    || borrow.getReaderName().toLowerCase().contains(keyword.toLowerCase())
                    || String.valueOf(borrow.getId()).contains(keyword);

            // Kiểm tra trạng thái (Đã trả hoặc Chưa trả)
            boolean matchesStatus = !isReturned && !isNotReturned
                    || (isReturned && !borrow.isIsActive()) || (isNotReturned && borrow.isIsActive());

            // Chỉ thêm vào bảng nếu thỏa mãn cả từ khóa và trạng thái
            if (matchesKeyword && matchesStatus) {
                Object[] row = new Object[]{
                    borrow.getId(),
                    borrow.getReaderName(),
                    borrow.getBorrowDate(),
                    borrow.getReturnDate(),
                    borrow.isIsActive() ? "Đang mượn" : "Đã trả"
                };
                model.addRow(row);
            }
        }
    }

    private void performSearch() {
        String keyword = txtFindBorrowReceipt.getText().trim(); // Lấy từ khóa từ ô tìm kiếm
        boolean isReturned = returnedCheckBox.isSelected();            // Kiểm tra trạng thái "Đã trả"
        boolean isNotReturned = notReturnedCheckBox.isSelected();       // Kiểm tra trạng thái "Chưa trả"

        searchBorrowData(borrowReceiptTable, keyword, isReturned, isNotReturned);
    }

    private void addBorrow() {

    }

    /*===========================XỬ LÝ BORROW DETAIL ==================================*/
    // Nạp dữ liệu vào bảng từ list lưu trữ tạm thời "tempBorrowDetails"    
    private void loadBorrowDetail(javax.swing.JTable bookBorrowTable) {
        DefaultTableModel model = (DefaultTableModel) bookBorrowTable.getModel();
        model.setRowCount(0);

        for (BorrowDetailDTO borrowDetail : tempBorrowDetails) {
            Object[] row = {
                borrowDetail.getBookName(), // Tên sách
                borrowDetail.getQuantity(), // Số lượng
                borrowDetail.getDescription() // Mô tả
            };
            model.addRow(row);
        }
    }

    private boolean handleAddOrUpdateBook(boolean flag) {
        String ISBN = ISBNTextField.getText().trim();
        String readerID = readerIDTextField.getText().trim();
        int quantity = (Integer) jSpinner1.getValue();

        if (!flag) {
            return false;
        }

        if (isValidInput(ISBN, readerID, quantity)) {
            processTemporaryBorrowDetail(ISBN, quantity);
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đủ thông tin", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
        return true;
    }

    private boolean isValidInput(String ISBN, String readerID, int quantity) {
        return !readerID.isEmpty() && !ISBN.isEmpty() && quantity >= 0;
    }

    //xử lý thêm borrowDetail vào bộ nhớ tạm thời
    public void processTemporaryBorrowDetail(String ISBN, int quantity) {
        if (borrowBus.checkBooksInStock(ISBN, quantity)) {
            // nếu chưa có sách trong list thì thêm vào
            if (findBorrowDetailByISBN(ISBN) == null && quantity > 0) {
                BorrowDetailDTO borrowDetail = new BorrowDetailDTO();
                String bookName = borrowBus.getBookNameByISBN(ISBN);
                borrowDetail.setISBN(ISBN);
                borrowDetail.setBookName(bookName);
                borrowDetail.setQuantity(quantity);
                tempBorrowDetails.add(borrowDetail);
                JOptionPane.showMessageDialog(null, "Đã thêm vào bộ nhớ tạm thời");
            } else {
                //ngược lại cập nhật nếu số lượng lớn 0, xóa nếu nhỏ hơn 0
                updateOrDelTemporaryBorrowDetail(ISBN, quantity);
            }

            loadBorrowDetail(bookBorrowTable);
            jSpinner1.setValue(quantity);
        } else {
            JOptionPane.showMessageDialog(null, "Kho hiện không đủ sách", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    // Cập nhật quantity của borrowDetail vào list tạm thời
    public void updateOrDelTemporaryBorrowDetail(String ISBN, int quantity) {
        BorrowDetailDTO existingDetail = findBorrowDetailByISBN(ISBN);

        if (existingDetail != null) {
            if (quantity > 0) {
                existingDetail.setQuantity(quantity);
                JOptionPane.showMessageDialog(null, "Đã cập nhật số lượng trong bộ nhớ tạm thời");
            } else {
                tempBorrowDetails.remove(existingDetail);
                JOptionPane.showMessageDialog(null, "Đã xóa khỏi bộ nhớ tạm thời");
            }
        }
    }

    // Cập nhật description của borrowDetail vào list tạm thời
    public void updateDescription(String ISBN, String description) {
        BorrowDetailDTO existingDetail = findBorrowDetailByISBN(ISBN);

        if (existingDetail != null) {
            existingDetail.setDescription(description);
            JOptionPane.showMessageDialog(null, "Đã cập nhật trong bộ nhớ tạm thời");
        } else {
            JOptionPane.showMessageDialog(null, "Đã có lỗi xảy ra");
        }
    }

    //tìm xem ISBN đã tồn tại trong list tạm thời chưa
    private BorrowDetailDTO findBorrowDetailByISBN(String ISBN) {
        for (BorrowDetailDTO detail : tempBorrowDetails) {
            if (detail.getISBN().equals(ISBN)) {
                return detail;
            }
        }
        return null;
    }

    // lấy tất cả ds detail trong bộ nhớ tạm thời
    public List<BorrowDetailDTO> getTempBorrowDetails() {
        return tempBorrowDetails;
    }

    /*=========================== XỬ LÝ TẠO PHIẾU ==================================*/
    private void addBorrowing() {
        String reader = readerIDTextField.getText();
        String staff = staffIDLb.getText();
        Date dueDate = dueDateChooser.getDate();

        if (reader != null && staff != null) {
            java.sql.Date sqlDueDate = new java.sql.Date(dueDate.getTime());
            int borrowID = borrowBus.add(reader, staff, sqlDueDate);
            for (BorrowDetailDTO tempBorrowDetail : tempBorrowDetails) {
                addBorrowDetail(tempBorrowDetail, borrowID);
                borrowBus.updateAvailable(tempBorrowDetail.getISBN(), -tempBorrowDetail.getQuantity());
            }

            JOptionPane.showMessageDialog(null, "Thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            loadBorrowData(borrowReceiptTable);
        } else {
            JOptionPane.showMessageDialog(null, "Thông tin độc giả hoặc nhân viên không hợp lệ", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void addBorrowDetail(BorrowDetailDTO borrowDetail, int borrowID) {
        if (borrowDetail.getISBN() != null && borrowDetail.getQuantity() > 0) {
            borrowDetail.setBorrowID(borrowID);
            borrowDetailBus.add(borrowDetail);
        }
    }

    /*=========================== CÁC HÀM HỔ TRỢ ==================================*/
    // Phương thức chung để xử lý logic tìm kiếm tên độc giả
    private void handleReaderID() {
        String readerID = readerIDTextField.getText().trim();

        if (!readerID.isEmpty()) {
            // Gọi BUS để lấy tên độc giả
            String readerName = borrowBus.getPersonNameById(readerID);

            if (readerName != null) {
                // gọi BUS để xem độc giả có đang bị khóa không
                if (borrowBus.checkReaderIDIsLocked(readerID)) {
                    JOptionPane.showMessageDialog(null, "Độc giả đã bị khóa", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    readerIDTextField.setText("");
                } else if (borrowBus.checkReaderIsBorrowing(readerID)) {
                    JOptionPane.showMessageDialog(null, "Độc giả vẫn chưa trả sách", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    readerIDTextField.setText("");
                } else {
                    readerNameLb.setText(readerName);
//                    ISBNTextField.requestFocusInWindow();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Độc giả không tồn tại", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                readerIDTextField.setText("");
            }
        }
    }

    private void handleISBN() {
        String ISBN = ISBNTextField.getText().trim();

        if (!ISBN.isEmpty()) {
            if (borrowBus.checkISBNExistence(ISBN)) {
                isUpdateQuantity = false;
                if (findBorrowDetailByISBN(ISBN) != null) {
                    jSpinner1.setValue(findBorrowDetailByISBN(ISBN).getQuantity());
                    focusOnRowWithISBN(ISBN);
                } else {
                    jSpinner1.setValue(0);
                }
                isUpdateQuantity = true;
            } else {
                JOptionPane.showMessageDialog(null, "Mã sách không tồn tại", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                ISBNTextField.setText("");
            }
        }
    }

    //focus khi trong bảng đã có sách cùng iSBN
    private void focusOnRowWithISBN(String ISBN) {
        for (int row = 0; row < bookBorrowTable.getRowCount(); row++) {
            String tableISBN = (String) bookBorrowTable.getValueAt(row, 0);
            if (ISBN.equals(tableISBN)) {
                bookBorrowTable.setRowSelectionInterval(row, row);
                bookBorrowTable.scrollRectToVisible(bookBorrowTable.getCellRect(row, 0, true));
                break;
            }
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

        borrowReceipt1 = new GUI.BorrowReceipt();
        borrowReceipt1.setUpdateTableCallback(() ->  loadBorrowData(borrowReceiptTable)); //để call reload lại table borrowing
        panelBorder1 = new MyDesign.PanelBorder();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        ISBNTextField = new MyDesign.MyTextField_Basic();
        scanReaderButton = new MyDesign.MyButton();
        jLabel3 = new javax.swing.JLabel();
        jSpinner1 = new javax.swing.JSpinner();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        staffIDLb = new javax.swing.JLabel();
        readerIDTextField = new MyDesign.MyTextField_Basic();
        readerNameLb = new javax.swing.JLabel();
        scanButton1 = new MyDesign.MyButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        bookBorrowTable = new MyDesign.MyTable();
        addBookButton = new MyDesign.MyButton();
        delBookButton = new MyDesign.MyButton();
        jLabel7 = new javax.swing.JLabel();
        dueDateChooser = new com.toedter.calendar.JDateChooser();
        jLabel8 = new javax.swing.JLabel();
        scanButton2 = new MyDesign.MyButton();
        panelBorder_Basic1 = new MyDesign.PanelBorder_Basic();
        jLabel9 = new javax.swing.JLabel();
        txtFindBorrowReceipt = new MyDesign.SearchText();
        returnedCheckBox = new javax.swing.JCheckBox();
        notReturnedCheckBox = new javax.swing.JCheckBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        borrowReceiptTable = new MyDesign.MyTable();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(949, 553));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("DANH SÁCH PHIẾU MƯỢN");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Mã nhân viên");

        scanReaderButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/img/icon/scan.png"))); // NOI18N
        scanReaderButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                scanReaderButtonActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Số lượng");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("ISBN\n\n");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Mã độc giả");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Tên độc giả");

        staffIDLb.setText("3121410000");

        readerNameLb.setText("Trần Văn A");

        scanButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/img/icon/scan.png"))); // NOI18N
        scanButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                scanButton1ActionPerformed(evt);
            }
        });

        bookBorrowTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Tên sách", "Số lượng", "Mô tả"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(bookBorrowTable);
        if (bookBorrowTable.getColumnModel().getColumnCount() > 0) {
            bookBorrowTable.getColumnModel().getColumn(0).setResizable(false);
            bookBorrowTable.getColumnModel().getColumn(1).setResizable(false);
            bookBorrowTable.getColumnModel().getColumn(2).setResizable(false);
        }

        addBookButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/img/icon/add.png"))); // NOI18N

        delBookButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/img/icon/tru.png"))); // NOI18N

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("Ngày trả dự kiến");

        dueDateChooser.setBackground(new java.awt.Color(246, 250, 255));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("TẠO PHIẾU MƯỢN");

        scanButton2.setText("Tạo phiếu");
        scanButton2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/img/icon/search.png"))); // NOI18N

        txtFindBorrowReceipt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFindBorrowReceiptActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelBorder_Basic1Layout = new javax.swing.GroupLayout(panelBorder_Basic1);
        panelBorder_Basic1.setLayout(panelBorder_Basic1Layout);
        panelBorder_Basic1Layout.setHorizontalGroup(
            panelBorder_Basic1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder_Basic1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtFindBorrowReceipt, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelBorder_Basic1Layout.setVerticalGroup(
            panelBorder_Basic1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder_Basic1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBorder_Basic1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder_Basic1Layout.createSequentialGroup()
                        .addComponent(txtFindBorrowReceipt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        returnedCheckBox.setText("Đã trả");
        returnedCheckBox.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        notReturnedCheckBox.setText("Chưa trả");
        notReturnedCheckBox.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        borrowReceiptTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã ", "Độc giả", "Ngày mượn", "Ngày trả", "Tình trạng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(borrowReceiptTable);
        if (borrowReceiptTable.getColumnModel().getColumnCount() > 0) {
            borrowReceiptTable.getColumnModel().getColumn(0).setResizable(false);
            borrowReceiptTable.getColumnModel().getColumn(1).setResizable(false);
            borrowReceiptTable.getColumnModel().getColumn(2).setResizable(false);
            borrowReceiptTable.getColumnModel().getColumn(3).setResizable(false);
            borrowReceiptTable.getColumnModel().getColumn(4).setResizable(false);
        }

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelBorder1Layout.createSequentialGroup()
                            .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(panelBorder1Layout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(addBookButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(delBookButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(panelBorder1Layout.createSequentialGroup()
                                    .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel2)
                                        .addComponent(jLabel5)
                                        .addComponent(jLabel6)
                                        .addComponent(jLabel4))
                                    .addGap(18, 18, 18)
                                    .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(readerNameLb)
                                        .addComponent(staffIDLb)
                                        .addGroup(panelBorder1Layout.createSequentialGroup()
                                            .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(ISBNTextField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                                                .addComponent(readerIDTextField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(scanReaderButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(scanButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addComponent(jLabel1))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(panelBorder1Layout.createSequentialGroup()
                                    .addComponent(jLabel7)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(dueDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(scanButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(panelBorder1Layout.createSequentialGroup()
                                    .addComponent(panelBorder_Basic1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(returnedCheckBox)
                                    .addGap(18, 18, 18)
                                    .addComponent(notReturnedCheckBox))))))
                .addContainerGap(322, Short.MAX_VALUE))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(staffIDLb))
                        .addGap(12, 12, 12)
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel5)
                                .addComponent(readerIDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(scanReaderButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(readerNameLb))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(ISBNTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4))
                            .addComponent(scanButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(addBookButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(delBookButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3)))
                        .addGap(58, 58, 58)
                        .addComponent(jLabel1))
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel7))
                            .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(dueDateChooser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(scanButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addComponent(panelBorder_Basic1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(returnedCheckBox)
                                    .addComponent(notReturnedCheckBox))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(borrowReceipt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(borrowReceipt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtFindBorrowReceiptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFindBorrowReceiptActionPerformed
        String text = txtFindBorrowReceipt.getText().trim();
        try {

        } catch (Exception e1) {
            // TODO Auto-generated catch block
            JOptionPane.showMessageDialog(null, e1.getMessage());
        }
    }//GEN-LAST:event_txtFindBorrowReceiptActionPerformed

    private void scanReaderButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_scanReaderButtonActionPerformed
        // TODO add your handling code here:
        isScanningReaderID = true;
        isScanningISBN = false;
        scannerDialog.initAndShowGUI(this);
    }//GEN-LAST:event_scanReaderButtonActionPerformed

    private void scanButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_scanButton1ActionPerformed
        // TODO add your handling code here:
        isScanningReaderID = false;
        isScanningISBN = true;
        scannerDialog.initAndShowGUI(this);
    }//GEN-LAST:event_scanButton1ActionPerformed

    @Override
    public void onBarcodeScanned(String barcode) {
        idScan = barcode;
        System.out.println("Scanned barcode in MainClass: " + barcode);

        if (isScanningReaderID) {
            readerIDTextField.setText(barcode);
            ISBNTextField.requestFocusInWindow();
            handleReaderID();
        } else if (isScanningISBN) {
            ISBNTextField.setText(barcode);
            handleISBN();
        }
    }

    // Biến cờ để kiểm tra trạng thái quét
    private boolean isScanningReaderID = false;
    private boolean isScanningISBN = false;
    private boolean isUpdateQuantity = true;
    private boolean isFocusHandled = false;
//    private List<BorrowDetailDTO> borrowDetailListCache = new ArrayList<>(); // Lưu tạm thời thông tin BorrowDetailDTO
    private List<BorrowDetailDTO> tempBorrowDetails = new ArrayList<>();

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private MyDesign.MyTextField_Basic ISBNTextField;
    private MyDesign.MyButton addBookButton;
    private MyDesign.MyTable bookBorrowTable;
    private GUI.BorrowReceipt borrowReceipt1;
    private MyDesign.MyTable borrowReceiptTable;
    private MyDesign.MyButton delBookButton;
    private com.toedter.calendar.JDateChooser dueDateChooser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JCheckBox notReturnedCheckBox;
    private MyDesign.PanelBorder panelBorder1;
    private MyDesign.PanelBorder_Basic panelBorder_Basic1;
    private MyDesign.MyTextField_Basic readerIDTextField;
    private javax.swing.JLabel readerNameLb;
    private javax.swing.JCheckBox returnedCheckBox;
    private MyDesign.MyButton scanButton1;
    private MyDesign.MyButton scanButton2;
    private MyDesign.MyButton scanReaderButton;
    private javax.swing.JLabel staffIDLb;
    private MyDesign.SearchText txtFindBorrowReceipt;
    // End of variables declaration//GEN-END:variables
}
