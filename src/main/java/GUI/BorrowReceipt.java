/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI;

import BUS.BorrowBUS;
import DTO.BorrowDTO;
import DTO.BorrowDetailDTO;
import MyDesign.MyLabel;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

/**
 *
 * @author User
 */
public class BorrowReceipt extends javax.swing.JPanel {

    private GridBagConstraints detailGBC = new GridBagConstraints();
    private GridBagConstraints bookGBC = new GridBagConstraints();
    private MyLabel readerLb = new MyLabel("");
    private MyLabel staffLb = new MyLabel("");
    private MyLabel borrowDateLb = new MyLabel("");
    private MyLabel dueDateLb = new MyLabel("");
    private MyLabel returnDateLb = new MyLabel("");
    private MyLabel fineLb = new MyLabel("");
    private boolean isReturn = true;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private BorrowDTO borrowDTO;
    private int count = 0;
    private List<JSpinner> spinners = new ArrayList<>();
    private boolean isUpdatingFineLabel = false; // Biến cờ để kiểm tra việc cập nhật

    private int previousBrokenValue = 0;
    // Khai báo map để lưu trữ ISBN cho mỗi JSpinner
    private Map<JSpinner, String> spinnerISBNMap = new HashMap<>();
    private List<BorrowListener> listeners = new ArrayList<>();
    BorrowBUS borrowBUS = new BorrowBUS();

    /**
     * Creates new form BorrowReceipt
     */
    public BorrowReceipt() {
        initComponents();
        jScrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);

        setUpDetail();
        showBorrowReceipt();

        delayReturnButton.addActionListener(evt -> delayHandle());

        returnButton.addActionListener(evt -> returnBookHandle());
    }

    private void delayHandle() {
        if (borrowBUS.checkReaderHasDelayed(borrowDTO.getId())) {
            JOptionPane.showMessageDialog(null, "Phiếu mượn đã gia hạn", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } else {
            LocalDate localDate = borrowDTO.getDueDate().plusDays(5); // Thêm 5 ngày kể từ dueDate
            java.sql.Date newDate = java.sql.Date.valueOf(localDate); // Chuyển LocalDate sang java.sql.Date
            if (new BorrowBUS().setDelay(borrowDTO.getId(), newDate)) {
                JOptionPane.showMessageDialog(null, "Gia hạn thành công");
                notifyBookReturned(); // Gọi thông báo sau khi gia hạn thành công
            } else {
                JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi");
            }
        }
    }

    private void returnBookHandle() {
        double fineAmount;
        fineAmount = Double.parseDouble(fineLb.getText());

        // Duyệt qua danh sách chi tiết mượn và cập nhật số lượng sách sẵn có
        for (BorrowDetailDTO tempBorrowDetail : borrowDTO.getBorrowDetailDTO()) {
            borrowBUS.updateAvailable(tempBorrowDetail.getISBN(), tempBorrowDetail.getQuantity());
        }

        // Thực hiện trả sách
        boolean isReturned = borrowBUS.returnBook(borrowDTO.getId(), borrowDTO.getStaffID(), fineAmount);
        if (isReturned) {
            JOptionPane.showMessageDialog(this, "Trả sách thành công!");
            notifyBookReturned(); // Gọi thông báo sau khi trả sách thành công
        } else {
            JOptionPane.showMessageDialog(this, "Có lỗi xảy ra khi trả sách.");
        }
    }

    public void showBorrowReceipt() {
        if (borrowDTO == null) {
            jPanel1.setVisible(false);
        } else {
            setBorrowReceipt();
        }
    }

    private void setBorrowReceipt() {
        jPanel1.setVisible(true);
        lbMaPM.setText(borrowDTO.getId() + "");
        readerLb.setText(borrowDTO.getReaderName());
        staffLb.setText(borrowDTO.getStaffName());
        borrowDateLb.setText(convertLocalDateToString(borrowDTO.getBorrowDate()));
        dueDateLb.setText(convertLocalDateToString(borrowDTO.getDueDate()));
        if (borrowDTO.getReturnDate() == null) {
            returnDateLb.setText("");
        } else {
            returnDateLb.setText(convertLocalDateToString(borrowDTO.getReturnDate()));
        }
        fineLb.setText(String.valueOf(borrowDTO.getFine()));
        isReturn = borrowDTO.isIsActive();
        System.out.println(borrowDTO.isIsActive());
        if (!isReturn) {
            lbTrangThai.setText("Đã trả");
            lbTrangThai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/img/icon/Node.png")));
            lbTrangThai.setForeground(new java.awt.Color(18, 210, 49));
            lbMaPM.setForeground(new java.awt.Color(18, 210, 49));
            jPanel1.setBackground(new java.awt.Color(242, 255, 244));
            bookContainer.setBackground(new java.awt.Color(242, 255, 244));
            recieptDetail.setBackground(new java.awt.Color(242, 255, 244));
            delayReturnButton.setVisible(false);
            returnButton.setVisible(false);
        } else {
            lbTrangThai.setText("Chưa trả");
            lbTrangThai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/img/icon/RedNode.png")));
            lbTrangThai.setForeground(new java.awt.Color(234, 38, 44));
            lbMaPM.setForeground(new java.awt.Color(234, 38, 44));
            jPanel1.setBackground(new java.awt.Color(255, 241, 241));
            bookContainer.setBackground(new java.awt.Color(255, 241, 241));
            recieptDetail.setBackground(new java.awt.Color(255, 241, 241));
            delayReturnButton.setVisible(true);
            returnButton.setVisible(true);
        }
        setUpBook();
    }

    private String convertLocalDateToString(LocalDate localDate) {
        return localDate.format(formatter);
    }

    public void setBorrowDTO(BorrowDTO borrowDTO) {
        this.borrowDTO = borrowDTO;
    }

    private void setUpBook() {
        resetGridBagConstraints();
        bookGBC.weightx = 1;
        bookGBC.insets = new Insets(5, 5, 5, 5);

        for (BorrowDetailDTO bdDTO : borrowDTO.getBorrowDetailDTO()) {
            bookGBC.gridx = 0;
            bookGBC.gridy = count;
            bookGBC.anchor = GridBagConstraints.WEST;
            bookContainer.add(new MyLabel("Tên sách", true), bookGBC);

            bookGBC.gridx = 1;
            bookGBC.gridy = count;
            bookGBC.anchor = GridBagConstraints.EAST;
            bookContainer.add(new MyLabel(bdDTO.getBookName()), bookGBC);

            count++;

            bookGBC.gridx = 0;
            bookGBC.gridy = count;
            bookGBC.anchor = GridBagConstraints.WEST;
            bookContainer.add(new MyLabel("Mô tả", true), bookGBC);

            bookGBC.gridx = 1;
            bookGBC.gridy = count;
            bookGBC.anchor = GridBagConstraints.EAST;
            bookContainer.add(new MyLabel(bdDTO.getDescription()), bookGBC);

            count++;

            bookGBC.gridx = 0;
            bookGBC.gridy = count;
            bookGBC.anchor = GridBagConstraints.WEST;
            bookContainer.add(new MyLabel("Số lượng", true), bookGBC);

            bookGBC.gridx = 1;
            bookGBC.gridy = count;
            bookGBC.anchor = GridBagConstraints.EAST;
            bookContainer.add(new MyLabel(String.valueOf(bdDTO.getQuantity())), bookGBC);

            count++;

            bookGBC.gridx = 0;
            bookGBC.gridy = count;
            bookGBC.anchor = GridBagConstraints.WEST;
            bookContainer.add(new MyLabel("Mất", true), bookGBC);

            if (isReturn) {
                bookGBC.gridx = 1;
                bookGBC.gridy = count;
                bookGBC.anchor = GridBagConstraints.EAST;
                JSpinner lostSpinner = new JSpinner(new SpinnerNumberModel(0, 0, bdDTO.getQuantity() - bdDTO.getBroke(), 1));
                lostSpinner.setName("lostSpinner");
                spinners.add(lostSpinner);
                bookContainer.add(lostSpinner, bookGBC);
                // Thêm lostSpinner và ISBN của nó vào map
                spinnerISBNMap.put(lostSpinner, bdDTO.getISBN());

                lostSpinner.addChangeListener(evt -> {
                    int lostValue = (int) lostSpinner.getValue();
                    double fineAmount = calculateFineForBrokenItems();
                    updateFineLabel(fineAmount);
                });

            } else {
                bookGBC.gridx = 1;
                bookGBC.gridy = count;
                bookGBC.anchor = GridBagConstraints.EAST;
                bookContainer.add(new MyLabel(String.valueOf(bdDTO.getLost())), bookGBC);
            }

            count++;

            bookGBC.gridx = 0;
            bookGBC.gridy = count;
            bookGBC.anchor = GridBagConstraints.WEST;
            bookContainer.add(new MyLabel("Hỏng", true), bookGBC);

            if (isReturn) {
                bookGBC.gridx = 1;
                bookGBC.gridy = count;
                bookGBC.anchor = GridBagConstraints.EAST;
                JSpinner brokenSpinner = new JSpinner(new SpinnerNumberModel(0, 0, bdDTO.getQuantity() - bdDTO.getLost(), 1));
                // Đặt tên cho JSpinner để có thể nhận diện sau này
                brokenSpinner.setName("brokenSpinner");
                spinners.add(brokenSpinner);
                bookContainer.add(brokenSpinner, bookGBC);

                // Xử lý sự kiện khi thay đổi giá trị của brokenSpinner
                brokenSpinner.addChangeListener(e -> {
                    int brokenValue = (int) brokenSpinner.getValue();
                    double fineAmount = calculateFineForBrokenItems();
                    updateFineLabel(fineAmount);
                });
            } else {
                bookGBC.gridx = 1;
                bookGBC.gridy = count;
                bookGBC.anchor = GridBagConstraints.EAST;
                bookContainer.add(new MyLabel(String.valueOf(bdDTO.getLost())), bookGBC);
            }

            count++;

            bookGBC.gridx = 0;
            bookGBC.gridy = count;
            bookGBC.anchor = GridBagConstraints.WEST;
            bookContainer.add(new MyLabel("", true), bookGBC);

            bookGBC.gridx = 1;
            bookGBC.gridy = count;
            bookGBC.anchor = GridBagConstraints.EAST;
            bookContainer.add(new MyLabel(""), bookGBC);

            count++;
        }

    }

    private void resetGridBagConstraints() {
        bookContainer.removeAll();
        bookContainer.revalidate();
        bookContainer.repaint();
        count = 0;
        spinners.clear();
    }

    private void setUpDetail() {
        detailGBC.weightx = 1;
        detailGBC.insets = new Insets(5, 5, 5, 5);

        detailGBC.gridx = 0;
        detailGBC.gridy = 0;
        detailGBC.anchor = GridBagConstraints.WEST;
        recieptDetail.add(new MyLabel("Độc giả", true), detailGBC);

        detailGBC.gridx = 0;
        detailGBC.gridy = 1;
        recieptDetail.add(new MyLabel("Ngày mượn", true), detailGBC);

        detailGBC.gridx = 0;
        detailGBC.gridy = 2;
        recieptDetail.add(new MyLabel("Ngày trả dự kiến", true), detailGBC);

        detailGBC.gridx = 0;
        detailGBC.gridy = 3;
        recieptDetail.add(new MyLabel("Ngày trả thực tế", true), detailGBC);

        detailGBC.gridx = 0;
        detailGBC.gridy = 4;
        recieptDetail.add(new MyLabel("Tiền phạt", true), detailGBC);

        detailGBC.gridx = 0;
        detailGBC.gridy = 5;
        recieptDetail.add(new MyLabel("Nhân viên", true), detailGBC);

        detailGBC.gridx = 1;
        detailGBC.gridy = 0;
        detailGBC.anchor = GridBagConstraints.EAST;
        recieptDetail.add(readerLb, detailGBC);

        detailGBC.gridx = 1;
        detailGBC.gridy = 1;
        recieptDetail.add(borrowDateLb, detailGBC);

        detailGBC.gridx = 1;
        detailGBC.gridy = 2;
        recieptDetail.add(dueDateLb, detailGBC);

        detailGBC.gridx = 1;
        detailGBC.gridy = 3;
        recieptDetail.add(returnDateLb, detailGBC);

        detailGBC.gridx = 1;
        detailGBC.gridy = 4;
        recieptDetail.add(fineLb, detailGBC);

        detailGBC.gridx = 1;
        detailGBC.gridy = 5;
        recieptDetail.add(staffLb, detailGBC);
    }

    // Hàm cập nhật nhãn tiền phạt
    private void updateFineLabel(double fineAmount) {
        fineLb.setText(fineAmount + "");
        // Kiểm tra lại thay đổi
        fineLb.revalidate();
        fineLb.repaint();
    }

    private double calculateFineForBrokenItems() {
        double totalFine = 0;
        double finePerBrokenItem = 30000; //phạt 30.000đ cho mỗi quyển sách hỏng

        // Duyệt qua tất cả các thành phần trong `bookContainer` để lấy giá trị `brokenSpinner`
        for (Component component : bookContainer.getComponents()) {
            if (component instanceof JSpinner && "brokenSpinner".equals(component.getName())) {
                int brokenValue = (int) ((JSpinner) component).getValue();
                totalFine += brokenValue * finePerBrokenItem;
            } else if (component instanceof JSpinner && "lostSpinner".equals(component.getName())) {
                JSpinner spinner = (JSpinner) component;
                String isbn = spinnerISBNMap.get(spinner);
                double bookPrice = new BorrowBUS().selectPrice(isbn);

                int lostValue = (int) ((JSpinner) component).getValue();
                totalFine += lostValue * bookPrice;
                System.out.println("ISBN của sách mất: " + bookPrice + ", Số lượng mất: " + lostValue);
            }
        }
        return totalFine;
    }

    // Phương thức thêm listener
    public void addBorrowListener(BorrowListener listener) {
        listeners.add(listener);
    }

    // Phương thức để gọi khi trả sách thành công
    private void notifyBookReturned() {
        for (BorrowListener listener : listeners) {
            listener.onBookReturned(); // Thông báo cho tất cả các listener
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

        jPanel1 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        lbMaPM = new javax.swing.JLabel();
        lbTrangThai = new javax.swing.JLabel();
        recieptDetail = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        bookContainer = new javax.swing.JPanel();
        delayReturnButton = new MyDesign.MyButton();
        returnButton = new MyDesign.MyButton();

        jLabel33.setFont(new java.awt.Font("SansSerif", 0, 10)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(127, 127, 127));
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel33.setText("MÃ PHIẾU MƯỢN");

        lbMaPM.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        lbMaPM.setForeground(new java.awt.Color(18, 210, 49));
        lbMaPM.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbMaPM.setText("#LB01");

        lbTrangThai.setForeground(new java.awt.Color(18, 210, 49));
        lbTrangThai.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTrangThai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/img/icon/Node.png"))); // NOI18N
        lbTrangThai.setText("Đã trả");

        recieptDetail.setLayout(new java.awt.GridBagLayout());

        jScrollPane1.setBorder(null);

        bookContainer.setLayout(new java.awt.GridBagLayout());
        jScrollPane1.setViewportView(bookContainer);

        delayReturnButton.setBackground(new java.awt.Color(255, 255, 255));
        delayReturnButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/img/icon/action-refresh.png"))); // NOI18N
        delayReturnButton.setText("Gia hạn");
        delayReturnButton.setColor(new java.awt.Color(255, 255, 255));
        delayReturnButton.setColorOver(new java.awt.Color(255, 241, 241));
        delayReturnButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        returnButton.setBackground(new java.awt.Color(255, 255, 255));
        returnButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/img/icon/3.png"))); // NOI18N
        returnButton.setText("Trả sách");
        returnButton.setColor(new java.awt.Color(255, 255, 255));
        returnButton.setColorOver(new java.awt.Color(255, 241, 241));
        returnButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbTrangThai, javax.swing.GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)
            .addComponent(lbMaPM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(recieptDetail, javax.swing.GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(delayReturnButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(returnButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel33)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbMaPM)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbTrangThai)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(recieptDetail, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(delayReturnButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(returnButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bookContainer;
    private MyDesign.MyButton delayReturnButton;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbMaPM;
    private javax.swing.JLabel lbTrangThai;
    private javax.swing.JPanel recieptDetail;
    private MyDesign.MyButton returnButton;
    // End of variables declaration//GEN-END:variables
}
