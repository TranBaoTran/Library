/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI;

/**
 *
 * @author User
 */
public class StatisticGUI extends javax.swing.JPanel {

    /**
     * Creates new form StatisticGUI
     */
    public StatisticGUI() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        startDateChooser = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        endDateChooser = new com.toedter.calendar.JDateChooser();
        panelBorder_Statistic_Blue1 = new MyDesign.PanelBorder_Statistic_Blue();
        jLabel3 = new javax.swing.JLabel();
        borrowRecieptNumber = new javax.swing.JLabel();
        panelBorder_Statistic_Red1 = new MyDesign.PanelBorder_Statistic_Red();
        jLabel5 = new javax.swing.JLabel();
        lostNumber = new javax.swing.JLabel();
        panelBorder_Statistic_Red2 = new MyDesign.PanelBorder_Statistic_Red();
        jLabel7 = new javax.swing.JLabel();
        brokeNumber = new javax.swing.JLabel();
        panelBorder_Statistic_Blue2 = new MyDesign.PanelBorder_Statistic_Blue();
        jLabel9 = new javax.swing.JLabel();
        returnRate = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        mostReadBookNumber = new MyDesign.MyTable();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("THỐNG KÊ TỪ");

        startDateChooser.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("ĐẾN");

        endDateChooser.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("SỐ LƯỢT MƯỢN");

        borrowRecieptNumber.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        borrowRecieptNumber.setForeground(new java.awt.Color(255, 255, 255));
        borrowRecieptNumber.setText("1");

        javax.swing.GroupLayout panelBorder_Statistic_Blue1Layout = new javax.swing.GroupLayout(panelBorder_Statistic_Blue1);
        panelBorder_Statistic_Blue1.setLayout(panelBorder_Statistic_Blue1Layout);
        panelBorder_Statistic_Blue1Layout.setHorizontalGroup(
            panelBorder_Statistic_Blue1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder_Statistic_Blue1Layout.createSequentialGroup()
                .addGroup(panelBorder_Statistic_Blue1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder_Statistic_Blue1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3))
                    .addGroup(panelBorder_Statistic_Blue1Layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(borrowRecieptNumber)))
                .addContainerGap(68, Short.MAX_VALUE))
        );
        panelBorder_Statistic_Blue1Layout.setVerticalGroup(
            panelBorder_Statistic_Blue1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder_Statistic_Blue1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel3)
                .addGap(35, 35, 35)
                .addComponent(borrowRecieptNumber)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(248, 67, 67));
        jLabel5.setText("SỐ SÁCH MẤT");

        lostNumber.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lostNumber.setForeground(new java.awt.Color(248, 67, 67));
        lostNumber.setText("1");

        javax.swing.GroupLayout panelBorder_Statistic_Red1Layout = new javax.swing.GroupLayout(panelBorder_Statistic_Red1);
        panelBorder_Statistic_Red1.setLayout(panelBorder_Statistic_Red1Layout);
        panelBorder_Statistic_Red1Layout.setHorizontalGroup(
            panelBorder_Statistic_Red1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder_Statistic_Red1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBorder_Statistic_Red1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder_Statistic_Red1Layout.createSequentialGroup()
                        .addComponent(lostNumber)
                        .addGap(15, 15, 15)))
                .addContainerGap(88, Short.MAX_VALUE))
        );
        panelBorder_Statistic_Red1Layout.setVerticalGroup(
            panelBorder_Statistic_Red1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder_Statistic_Red1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel5)
                .addGap(32, 32, 32)
                .addComponent(lostNumber)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(248, 67, 67));
        jLabel7.setText("SỐ SÁCH HỎNG");

        brokeNumber.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        brokeNumber.setForeground(new java.awt.Color(248, 67, 67));
        brokeNumber.setText("1");

        javax.swing.GroupLayout panelBorder_Statistic_Red2Layout = new javax.swing.GroupLayout(panelBorder_Statistic_Red2);
        panelBorder_Statistic_Red2.setLayout(panelBorder_Statistic_Red2Layout);
        panelBorder_Statistic_Red2Layout.setHorizontalGroup(
            panelBorder_Statistic_Red2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder_Statistic_Red2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addContainerGap(61, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder_Statistic_Red2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(brokeNumber)
                .addGap(90, 90, 90))
        );
        panelBorder_Statistic_Red2Layout.setVerticalGroup(
            panelBorder_Statistic_Red2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder_Statistic_Red2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel7)
                .addGap(30, 30, 30)
                .addComponent(brokeNumber)
                .addContainerGap(89, Short.MAX_VALUE))
        );

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("TỈ LỆ HOÀN TRẢ");

        returnRate.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        returnRate.setForeground(new java.awt.Color(255, 255, 255));
        returnRate.setText("1%");

        javax.swing.GroupLayout panelBorder_Statistic_Blue2Layout = new javax.swing.GroupLayout(panelBorder_Statistic_Blue2);
        panelBorder_Statistic_Blue2.setLayout(panelBorder_Statistic_Blue2Layout);
        panelBorder_Statistic_Blue2Layout.setHorizontalGroup(
            panelBorder_Statistic_Blue2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder_Statistic_Blue2Layout.createSequentialGroup()
                .addGroup(panelBorder_Statistic_Blue2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder_Statistic_Blue2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel9))
                    .addGroup(panelBorder_Statistic_Blue2Layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(returnRate)))
                .addContainerGap(56, Short.MAX_VALUE))
        );
        panelBorder_Statistic_Blue2Layout.setVerticalGroup(
            panelBorder_Statistic_Blue2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder_Statistic_Blue2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel9)
                .addGap(35, 35, 35)
                .addComponent(returnRate)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("SÁCH ĐƯỢC MƯỢN NHIỀU NHẤT");

        mostReadBookNumber.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ISBN", "Tên sách", "Phiên bản", "Số lượt mượn"
            }
        ));
        jScrollPane1.setViewportView(mostReadBookNumber);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(panelBorder_Statistic_Blue1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(panelBorder_Statistic_Red1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(panelBorder_Statistic_Red2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(panelBorder_Statistic_Blue2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(27, 27, 27)
                                .addComponent(startDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(jLabel2)
                                .addGap(33, 33, 33)
                                .addComponent(endDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(119, 119, 119)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 715, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(endDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(startDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelBorder_Statistic_Blue2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelBorder_Statistic_Red1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelBorder_Statistic_Blue1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelBorder_Statistic_Red2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel borrowRecieptNumber;
    private javax.swing.JLabel brokeNumber;
    private com.toedter.calendar.JDateChooser endDateChooser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lostNumber;
    private MyDesign.MyTable mostReadBookNumber;
    private MyDesign.PanelBorder_Statistic_Blue panelBorder_Statistic_Blue1;
    private MyDesign.PanelBorder_Statistic_Blue panelBorder_Statistic_Blue2;
    private MyDesign.PanelBorder_Statistic_Red panelBorder_Statistic_Red1;
    private MyDesign.PanelBorder_Statistic_Red panelBorder_Statistic_Red2;
    private javax.swing.JLabel returnRate;
    private com.toedter.calendar.JDateChooser startDateChooser;
    // End of variables declaration//GEN-END:variables
}