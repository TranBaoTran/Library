/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.BorrowDAO;
import DTO.BorrowDTO;
import DTO.BorrowDetailDTO;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author User
 */
public class BorrowBUS {

    private BorrowDAO borrowDao;

    public BorrowBUS() {
        borrowDao = new BorrowDAO();
    }

    public int add(String readerID, String staffID, java.sql.Date dueDate) {
        try {
            if (readerID == null || staffID == null || dueDate == null) {
                throw new IllegalArgumentException("Thông tin không đầy đủ.");
            }
            return borrowDao.addBorrow(readerID, staffID, dueDate);
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;  // Trả về -1 nếu có lỗi xảy ra
        }
    }
    
    public boolean AddBorrow(String readerID, String staffID, Date dueDate, List<BorrowDetailDTO> tempBorrowDetails ){
        try {
            return borrowDao.AddBorrow(readerID, staffID, dueDate, tempBorrowDetails);
        } catch (SQLException ex) {
            Logger.getLogger(BorrowBUS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public List<BorrowDTO> sellectAll() {
        return borrowDao.selectAll();
    }

    public BorrowDTO selectABorrow(int id) {
        return borrowDao.selectABorrow(id);
    }

    // Gọi DAO để lấy tên độc giả dựa trên readerID
    public String getPersonNameById(String readerID) {
        try {
            return borrowDao.getPersonName(readerID);
        } catch (SQLException ex) {
            Logger.getLogger(BorrowBUS.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    // Gọi DAO để lấy id borrowing dựa trên readerID
    public int getBorrowIDByReaderID(String readerID) {
        try {
            return borrowDao.getBorrowID(readerID);
        } catch (SQLException ex) {
            Logger.getLogger(BorrowBUS.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }

    // Gọi DAO để lấy tên độc giả dựa trên readerID
    public String getBookNameByISBN(String ISBN) {
        try {
            return borrowDao.getBookName(ISBN);
        } catch (SQLException ex) {
            Logger.getLogger(BorrowBUS.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public boolean checkISBNExistence(String ISBN) {
        try {
            return borrowDao.checkISBNExistence(ISBN);
        } catch (SQLException ex) {
            Logger.getLogger(BorrowBUS.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public double selectPrice(String ISBN){
        try {
            return borrowDao.selectPrice(ISBN);
        } catch (SQLException ex) {
            Logger.getLogger(BorrowBUS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0.0;
    }
    
    public boolean setDelay(int id, Date dueDate) {
        return borrowDao.setDelay(id, dueDate);
    }
    
    public boolean returnBook(int id, String staffID,double fine){
        return borrowDao.returnBook(id, staffID, fine);
    }
    
    //kiểm tra xem độc giả có đang mượn sách không
    public boolean checkReaderIsBorrowing(String readerID) {
        try {
            return borrowDao.checkReaderIsBorrowing(readerID);
        } catch (SQLException ex) {
            Logger.getLogger(BorrowBUS.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    //kiểm tra xem độc giả đã gia hạn chưa
    public boolean checkReaderHasDelayed(int id) {
        try {
            return borrowDao.checkReaderHasDelayed(id);
        } catch (SQLException ex) {
            Logger.getLogger(BorrowBUS.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    //cộng (trừ) số lượng vào kho
    public boolean updateAvailable(String ISBN, int quantity) {
        try {
            return borrowDao.updateAvailable(ISBN, quantity);
        } catch (SQLException ex) {
            Logger.getLogger(BorrowBUS.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    //kiểm tra sách còn trong kho không
    public boolean checkBooksInStock(String ISBN, int quantity) {
        try {
            return borrowDao.checkBooksInStock(ISBN, quantity);
        } catch (SQLException ex) {
            Logger.getLogger(BorrowBUS.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    //kiểm tra xem độc giả có bị khóa không
    public boolean checkReaderIDIsLocked(String readerID) {
        try {
            return borrowDao.checkReaderIDIsLocked(readerID);
        } catch (SQLException ex) {
            Logger.getLogger(BorrowBUS.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
