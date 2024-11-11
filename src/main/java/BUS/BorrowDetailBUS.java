/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.BorrowDetailDAO;
import DTO.BorrowDetailDTO;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author anhthu
 */
public class BorrowDetailBUS {

    private BorrowDetailDAO detailDAO;
    private BorrowBUS borrowBus;
    private Map<Integer, BorrowDetailDTO> borrowDetailMap = new HashMap<>(); // Lưu BorrowDetailDTO theo chỉ số hàng

    public BorrowDetailBUS() {
        detailDAO = new BorrowDetailDAO();
        borrowBus = new BorrowBUS();
    }

    // Gọi DAO để thêm 1 chi tiết chi tiết phiếu mượn
    public boolean add(BorrowDetailDTO detail) {
        return detailDAO.add(detail);
    }

    //Gọi DAO để cập nhật số lượng của 1 chi tiết phiếu mượn
    public boolean updateQuantity(BorrowDetailDTO detailDTO) {
        return detailDAO.updateQuantity(detailDTO);
    }

    //Gọi DAO để cập nhật mô tả của 1 chi tiết phiếu mượn
    public boolean updateDescription(int borrowID, String ISBN, String description) {
        return detailDAO.updateDesciption(borrowID, ISBN, description);
    }

    //Gọi DAO để cập nhật số lượng mất và hỏng
    public boolean updateLostAndBroke(int borrowID, String ISBN, int lost, int broke) {
        return detailDAO.updateLostAndBroke(borrowID, ISBN, lost, broke);
    }

    // Lớp BUS
    public List<BorrowDetailDTO> loadBorrowDetailData(int borrowID) {
        List<BorrowDetailDTO> borrowDetailList = detailDAO.selectAll(borrowID);
        return borrowDetailList;
    }

    public boolean checkBorrowDetailExistence(String ISBN, int borrowID){
        try {
            return detailDAO.checkBorrowDetailExistence(ISBN, borrowID);
        } catch (SQLException ex) {
            Logger.getLogger(BorrowDetailBUS.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public Vector<BorrowDetailDTO> getBorrowDetails(int borrowID) {
        return detailDAO.getBorrowDetails(borrowID);
    }
}
