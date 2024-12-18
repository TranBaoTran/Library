/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import BUS.AccountBUS;
import BUS.RoleBUS;
import DTO.AccountDTO;
import DTO.RoleDTO;

/**
 *
 * @author User
 */
public class AccountGUI extends javax.swing.JPanel {
    private AccountBUS accountBus;
    private RoleBUS roleBus;

    /**
     * Creates new form AccountGUI
     */
    public AccountGUI() {
        try {
            accountBus = new AccountBUS();
            roleBus = new RoleBUS();
        } catch (ClassNotFoundException | SQLException | IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error initializing database connection: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        initComponents();
        jPanel1.setVisible(false);
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
        jLabel1 = new javax.swing.JLabel();
        accountIDTextField = new MyDesign.MyTextField_Basic();
        jLabel2 = new javax.swing.JLabel();
        startDateChooser = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        endDateChooser = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        roleComboBox = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        searchButton = new MyDesign.MyButton();
        isLockedCheckBox = new javax.swing.JCheckBox();
        isOpenedCheckBox = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        accountTable = new MyDesign.MyTable();
        lockButton = new MyDesign.MyButton();
        openButton = new MyDesign.MyButton();
        changePassButton = new MyDesign.MyButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        newPassTextField = new MyDesign.MyTextField_Basic();
        acceptChangeButton = new MyDesign.MyButton();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Mã tài khoản");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Từ ngày");

        startDateChooser.setBackground(new java.awt.Color(246, 250, 255));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Đến");

        endDateChooser.setBackground(new java.awt.Color(246, 250, 255));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Vai trò");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("DANH SÁCH TÀI KHOẢN");

        searchButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/img/icon/search.png"))); // NOI18N
        // Thiết lập ActionListener cho button search
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                onSearchButtonClick(); // Gọi phương thức khi button được nhấn
            }
        });

        isLockedCheckBox.setText("Khoá");

        isOpenedCheckBox.setText("Mở");

        // Khi người dùng chọn checkbox Khóa
        isLockedCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                onSearchButtonClick(); // Gọi hàm tìm kiếm
            }
        });

        // Khi người dùng chọn checkbox Mở
        isOpenedCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                onSearchButtonClick(); // Gọi hàm tìm kiếm
            }

        });

        jScrollPane1.setViewportView(accountTable);
        if (accountTable.getColumnModel().getColumnCount() > 0) {
            accountTable.getColumnModel().getColumn(0).setResizable(false);
            accountTable.getColumnModel().getColumn(1).setResizable(false);
            accountTable.getColumnModel().getColumn(2).setResizable(false);
            accountTable.getColumnModel().getColumn(3).setResizable(false);
            accountTable.getColumnModel().getColumn(4).setResizable(false);
        }

        lockButton.setForeground(new java.awt.Color(248, 67, 67));
        lockButton.setText("Khoá tài khoản");
        lockButton.setColor(new java.awt.Color(255, 241, 241));
        lockButton.setColorOver(new java.awt.Color(255, 241, 241));
        lockButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lockButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                updateAccountStatus(false);
            }
        });

        openButton.setBackground(new java.awt.Color(22, 113, 221));
        openButton.setForeground(new java.awt.Color(255, 255, 255));
        openButton.setText("Mở tài khoản");
        openButton.setColor(new java.awt.Color(22, 113, 221));
        openButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        openButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                updateAccountStatus(true);
            }
        });

        changePassButton.setBackground(new java.awt.Color(22, 113, 221));
        changePassButton.setForeground(new java.awt.Color(255, 255, 255));
        changePassButton.setText("Đổi mật khẩu");
        changePassButton.setColor(new java.awt.Color(22, 113, 221));
        changePassButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        changePassButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changePassButtonActionPerformed(evt);
            }
        });

        // Thêm MouseListener vào accountTable
        accountTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                // Kiểm tra nếu nhấp chuột vào hàng
                int row = accountTable.rowAtPoint(evt.getPoint());
                if (row >= 0) {
                    // Lấy dữ liệu từ hàng đã chọn
                    String accountId = accountTable.getValueAt(row, 0).toString(); // Giả sử cột 0 là ID tài khoản
                    // Có thể hiển thị thông tin tài khoản (nếu cần)
                }
            }
        });

        jPanel1.setBackground(new java.awt.Color(246, 250, 255));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Nhập mật khẩu mới");

        newPassTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newPassTextFieldActionPerformed(evt);
            }
        });

        acceptChangeButton.setText("Đổi");
        acceptChangeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acceptChangeButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(newPassTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(acceptChangeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(newPassTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acceptChangeButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(62, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 839, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(accountIDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(startDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(endDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(roleComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(39, 39, 39)
                                .addComponent(isLockedCheckBox)
                                .addGap(18, 18, 18)
                                .addComponent(isOpenedCheckBox))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder1Layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(openButton, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lockButton, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(changePassButton, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(roleComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelBorder1Layout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1)
                                    .addComponent(accountIDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2)))
                            .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(startDateChooser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(endDateChooser, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)))))
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(isLockedCheckBox)
                    .addComponent(isOpenedCheckBox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lockButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(openButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(changePassButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(22, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        loadAccount();
        loadRoles();
    }// </editor-fold>//GEN-END:initComponents

    private void loadAccount() {
        try {
            loadAccountDefault();

            List<AccountDTO> accounts = accountBus.getFullAccount();
        
            DefaultTableModel model = (DefaultTableModel) accountTable.getModel();
            model.setRowCount(0); // Xóa tất cả các hàng trong bảng trước khi thêm dữ liệu mới
    
            for (AccountDTO account : accounts) {
                System.out.println(account.getName());
                model.addRow(new Object[]{
                    account.getId(),
                    account.getName(),
                    account.getRoleDTO().getName(),
                    account.getDayCreated(),
                    account.isIsActive() ? "Hoạt động" : "Đã khóa" // Chuyển đổi tình trạng
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading accounts: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateAccountStatus(boolean isActive) {
        int selectedRow = accountTable.getSelectedRow(); // Lấy chỉ số hàng được chọn
    
        if (selectedRow != -1) { // Kiểm tra nếu có hàng nào được chọn
            String accountId = accountTable.getValueAt(selectedRow, 0).toString(); // Lấy Mã tài khoản từ cột đầu tiên
            
            try {
                // Gọi phương thức trong accountBus để cập nhật trạng thái
                boolean success = accountBus.updateAccountStatus(accountId, isActive);
                
                String message = isActive ? "Tài khoản đã được mở khóa thành công." : "Tài khoản đã được khóa thành công.";
                if (success) {
                    JOptionPane.showMessageDialog(this, message, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    loadAccount(); // Tải lại danh sách tài khoản
                } else {
                    JOptionPane.showMessageDialog(this, "Không thể cập nhật trạng thái tài khoản.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error updating account status: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một tài khoản để cập nhật trạng thái.", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void loadRoles() {
        try {
            List<RoleDTO> roles = roleBus.getAllRoles(); // Lấy danh sách vai trò
            roleComboBox.addItem("Tất cả"); // Giá trị mặc định để người dùng có thể chọn "Tất cả"

            for (RoleDTO role : roles) {
                roleComboBox.addItem(role.getName()); // Thêm tên vai trò vào comboBox
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading roles: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadAccountDefault() {
        accountTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã tài khoản", "Tên tài khoản", "Vai trò", "Ngày tạo", "Tình trạng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
    }

    private void newPassTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newPassTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_newPassTextFieldActionPerformed

    private void changePassButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changePassButtonActionPerformed
        // Kiểm tra nếu có hàng nào được chọn trong bảng
        int row = accountTable.getSelectedRow();
        if (row >= 0) {
            // Nếu có hàng được chọn, hiện panel nhập mật khẩu mới
            jPanel1.setVisible(true);
        } else {
            // Nếu không có hàng nào được chọn, thông báo cho người dùng
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một tài khoản!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_changePassButtonActionPerformed

    // Khi nhấn nút "Đổi" trong jPanel1
    private void acceptChangeButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // Lấy mật khẩu mới từ text field
        String newPassword = newPassTextField.getText();
        
        // Kiểm tra nếu mật khẩu không trống và có ít nhất 6 ký tự
        if (newPassword != null && !newPassword.isEmpty() && newPassword.length() >= 6) {
            // Lấy ID tài khoản từ hàng đã chọn trong bảng
            int row = accountTable.getSelectedRow();
            if (row >= 0) {
                String accountId = accountTable.getValueAt(row, 0).toString(); // Giả sử cột 0 là ID tài khoản
                
                // Gọi phương thức changePassword từ lớp BUS
                try {
                    boolean success = accountBus.changePassword(accountId, newPassword); // Giả sử accountBus là đối tượng của lớp BUS
                    if (success) {
                        JOptionPane.showMessageDialog(this, "Đổi mật khẩu thành công!");
                    } else {
                        JOptionPane.showMessageDialog(this, "Không thể đổi mật khẩu. Vui lòng kiểm tra lại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception e) {
                    // Xử lý ngoại lệ nếu có lỗi
                    JOptionPane.showMessageDialog(this, "Lỗi khi đổi mật khẩu: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn một tài khoản!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Mật khẩu mới phải có ít nhất 6 ký tự!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
        }
        
        // Ẩn panel sau khi thực hiện xong
        jPanel1.setVisible(false);
    }

    // Phương thức xử lý sự kiện khi button search được nhấn
    public void onSearchButtonClick() {
        try {
            String accountId = accountIDTextField.getText();
            
            // Chuyển đổi từ java.util.Date sang LocalDate
            LocalDate startDate = startDateChooser.getDate() != null ? startDateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate() : null;
            LocalDate endDate = endDateChooser.getDate() != null ? endDateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate() : null;
            
            // Kiểm tra điều kiện ngày tháng
            LocalDate currentDate = LocalDate.now();
            if (startDate != null && endDate != null) {
                if (endDate.isBefore(startDate)) {
                    JOptionPane.showMessageDialog(this, "Ngày kết thúc phải lớn hơn ngày bắt đầu.", "Lỗi ngày tháng", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            
            if (startDate != null && startDate.isAfter(currentDate)) {
                JOptionPane.showMessageDialog(this, "Ngày bắt đầu không được lớn hơn ngày hiện tại.", "Lỗi ngày tháng", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if (endDate != null && endDate.isAfter(currentDate)) {
                JOptionPane.showMessageDialog(this, "Ngày kết thúc không được lớn hơn ngày hiện tại.", "Lỗi ngày tháng", JOptionPane.ERROR_MESSAGE);
                return;
            }
    
            Boolean isActive = null;
    
            // Xác định trạng thái isActive dựa trên checkbox
            if (isLockedCheckBox.isSelected() && isOpenedCheckBox.isSelected()) {
                isActive = null; // Cả hai đều được chọn
            } else if (isLockedCheckBox.isSelected()) {
                isActive = false; // Chỉ khóa
            } else if (isOpenedCheckBox.isSelected()) {
                isActive = true; // Chỉ hoạt động
            }
    
            System.out.println("isActive");
            System.out.println(isActive);
    
            // Lấy giá trị từ roleComboBox
            String selectedRole = (String) roleComboBox.getSelectedItem();
            if (selectedRole.equals("Tất cả")) {
                selectedRole = null;
            }
    
            // Kiểm tra điều kiện tìm kiếm
            if (accountId.isEmpty() && startDate == null && endDate == null && isActive == null && selectedRole == null) {
                loadAccount();
                return;
            }
    
            // Gọi phương thức để tìm kiếm tài khoản với các điều kiện đã xác định
            List<AccountDTO> accounts = accountBus.getAccountBySearchCondition(accountId, startDate, endDate, isActive, selectedRole);
    
            DefaultTableModel model = (DefaultTableModel) accountTable.getModel();
            model.setRowCount(0); // Xóa tất cả các hàng trong bảng trước khi thêm dữ liệu mới
    
            for (AccountDTO account : accounts) {
                System.out.println(account.getName());
                model.addRow(new Object[]{
                    account.getId(),
                    account.getName(),
                    account.getRoleDTO().getName(),
                    account.getDayCreated(),
                    account.isIsActive() ? "Hoạt động" : "Đã khóa" // Chuyển đổi tình trạng
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi tải tài khoản: " + e.getMessage(), "Lỗi cơ sở dữ liệu", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private MyDesign.MyButton acceptChangeButton;
    private MyDesign.MyTextField_Basic accountIDTextField;
    private MyDesign.MyTable accountTable;
    private MyDesign.MyButton changePassButton;
    private com.toedter.calendar.JDateChooser endDateChooser;
    private javax.swing.JCheckBox isLockedCheckBox;
    private javax.swing.JCheckBox isOpenedCheckBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private MyDesign.MyButton lockButton;
    private MyDesign.MyTextField_Basic newPassTextField;
    private MyDesign.MyButton openButton;
    private MyDesign.PanelBorder panelBorder1;
    private javax.swing.JComboBox<String> roleComboBox;
    private MyDesign.MyButton searchButton;
    private com.toedter.calendar.JDateChooser startDateChooser;
    // End of variables declaration//GEN-END:variables
}
