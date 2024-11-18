/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.BorrowDTO;
import DTO.BorrowDetailDTO;
import connection.ConnectDB;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author User
 */
public class BorrowDAO {

    connection.ConnectDB connectDB;
    
    public BorrowDAO(){
        connectDB = new ConnectDB();
    }

    //tạo một phiếu mượn
    public int addBorrow(String readerID, String staffID, Date dueDate) throws SQLException {
        String sql = "INSERT INTO borrowing (readerID, borrowStaffID, dueDate) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connectDB.getConnection().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, readerID);
            statement.setString(2, staffID);
            statement.setDate(3, dueDate);
            statement.executeUpdate();

            // Lấy borrowID tự động tạo sau khi thêm bản ghi
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);  // Trả về borrowID
                } else {
                    throw new SQLException("Thêm giao dịch mượn thất bại, không lấy được ID.");
                }
            }
        }
    }

    //lấy danh sách các phiếu mượn
    public List<BorrowDTO> selectAll() {
        List<BorrowDTO> listBorrow = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connectDB.connect();
            String sql = "select* from borrowing";
            ps = connectDB.getConnection().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                BorrowDTO borrow = new BorrowDTO();
                borrow.setId(Integer.parseInt(rs.getString("id")));

                String readerId = rs.getString("readerID");
                borrow.setReaderID(readerId);

                String readerName = getPersonName(readerId);
                borrow.setReaderName(readerName);

                borrow.setStaffID(rs.getString("borrowStaffID"));

                String borrowDateStr = rs.getString("borrowDate");
                if (borrowDateStr != null && !borrowDateStr.isEmpty()) {
                    LocalDate borrowDate = LocalDate.parse(borrowDateStr, DateTimeFormatter.ISO_LOCAL_DATE);
                    borrow.setBorrowDate(borrowDate);
                }

                String dueDateStr = rs.getString("dueDate");
                if (dueDateStr != null && !dueDateStr.isEmpty()) {
                    LocalDate dueDate = LocalDate.parse(dueDateStr);
                    borrow.setDueDate(dueDate);
                }

                String returnDateStr = rs.getString("returnDate");
                if (returnDateStr != null && !returnDateStr.isEmpty()) {
                    LocalDate returnDate = LocalDate.parse(returnDateStr);
                    borrow.setReturnDate(returnDate);
                }

                String delay = rs.getString("delay").equals("1") ? "true" : "false";
                borrow.setDelay(Boolean.parseBoolean(delay));

                borrow.setFine(Long.parseLong(rs.getString("fine")));

                String isActive = rs.getString("isActive").equals("1") ? "true" : "false";
                borrow.setIsActive(Boolean.parseBoolean(isActive));

                listBorrow.add(borrow);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listBorrow;
    }

    //lấy một phiếu mượn
    public BorrowDTO selectABorrow(int id) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        BorrowDTO borrow = new BorrowDTO();
        try {
            connectDB.connect();
            String sql = "select* from borrowing where id = ?";
            ps = connectDB.getConnection().prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                borrow.setId(Integer.parseInt(rs.getString("id")));

                String readerId = rs.getString("readerID");
                borrow.setReaderID(readerId);

                String readerName = getPersonName(readerId);
                borrow.setReaderName(readerName);

                String staffID = rs.getString("borrowStaffID");
                borrow.setStaffID(staffID);
                borrow.setStaffName(getPersonName(staffID));

                String borrowDateStr = rs.getString("borrowDate");
                if (borrowDateStr != null && !borrowDateStr.isEmpty()) {
                    LocalDate borrowDate = LocalDate.parse(borrowDateStr, DateTimeFormatter.ISO_LOCAL_DATE);
                    borrow.setBorrowDate(borrowDate);
                }

                String dueDateStr = rs.getString("dueDate");
                if (dueDateStr != null && !dueDateStr.isEmpty()) {
                    LocalDate dueDate = LocalDate.parse(dueDateStr);
                    borrow.setDueDate(dueDate);
                }

                String returnDateStr = rs.getString("returnDate");
                if (returnDateStr != null && !returnDateStr.isEmpty()) {
                    LocalDate returnDate = LocalDate.parse(returnDateStr);
                    borrow.setReturnDate(returnDate);
                }

                String delay = rs.getString("delay").equals("1") ? "true" : "false";
                borrow.setDelay(Boolean.parseBoolean(delay));

                borrow.setFine(Long.parseLong(rs.getString("fine")));

                String isActive = rs.getString("isActive").equals("1") ? "true" : "false";
                borrow.setIsActive(Boolean.parseBoolean(isActive));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return borrow;
    }

    //lấy borrowID từ readerID
    public int getBorrowID(String readerID) throws SQLException {
        String query = "SELECT id FROM borrowing WHERE readerID = ?";
        connectDB.connect();
        try (PreparedStatement stmt = connectDB.getConnection().prepareStatement(query)) {
            stmt.setString(1, readerID);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                } else {
                    return 0;
                }
            }
        }
    }

    //gia hạn phiếu mượn
    public boolean setDelay(int id, Date dueDate) {
        String query = "UPDATE `borrowing` SET `delay`= ?, `dueDate` = ? WHERE `id` = ?";
        try (PreparedStatement stmt = connectDB.getConnection().prepareStatement(query)) {
            stmt.setInt(1, 1);
            stmt.setDate(2, dueDate);
            stmt.setInt(3, id);

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //trả sách
    public boolean returnBook(int id, String staffID, double fine) {
        String query = "UPDATE `borrowing` SET `returnStaffID`= ?, `fine`= ?, `returnDate`= CURRENT_DATE, `isActive`= 0 WHERE `id` = ?";
        try (PreparedStatement stmt = connectDB.getConnection().prepareStatement(query)) {
            stmt.setString(1, staffID);
            stmt.setDouble(2, fine);
            stmt.setInt(3, id);

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //kiểm tra xem độc giả có đang mượn sách không
    public boolean checkReaderIsBorrowing(String readerID) throws SQLException {
        String query = "SELECT COUNT(*) FROM `borrowing` WHERE isActive = 1 and readerID = ?";

        try (PreparedStatement stmt = connectDB.getConnection().prepareStatement(query)) {
            stmt.setString(1, readerID);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0; //nếu độc giả đang mượn sách return true
            }
        }
    }

    //kiểm tra xem độc giả đã gia hạn chưa
    public boolean checkReaderHasDelayed(int id) throws SQLException {
        String query = "SELECT COUNT(*) FROM `borrowing` WHERE delay = 1 and id = ?";

        try (PreparedStatement stmt = connectDB.getConnection().prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0; //nếu độc giả đã gia hạn return true
            }
        }
    }

    //lấy bookname từ ISBN
    public String getBookName(String ISBN) throws SQLException {
        String query = "SELECT book.name FROM book JOIN versionofbook ON book.id = versionofbook.bookID\n"
                + "WHERE versionofbook.ISBN = ?";
        connectDB.connect();
        try (PreparedStatement stmt = connectDB.getConnection().prepareStatement(query)) {
            stmt.setString(1, ISBN);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("name");
                } else {
                    return null;
                }
            }
        }
    }

    //cộng (trừ) số lượng vào kho
    public boolean updateAvailable(String ISBN, int quantity) throws SQLException {
        String query = "UPDATE `versionofbook` SET `available` = `available` + ?  WHERE `ISBN` = ?";
        connectDB.connect();
        try (PreparedStatement stmt = connectDB.getConnection().prepareStatement(query)) {
            stmt.setInt(1, quantity);
            stmt.setString(2, ISBN);

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //kiểm tra xem có tồn tại mã ISBN không
    public boolean checkISBNExistence(String ISBN) throws SQLException {
        String query = "SELECT COUNT(*) FROM `versionofbook` WHERE ISBN = ?";

        try (PreparedStatement stmt = connectDB.getConnection().prepareStatement(query)) {
            stmt.setString(1, ISBN);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        }
    }

    //kiểm tra sách còn trong kho không
    public boolean checkBooksInStock(String ISBN, int quantity) throws SQLException {
        String query = "SELECT COUNT(*) FROM `versionofbook` WHERE ISBN = ? and `available` >= ?";

        try (PreparedStatement stmt = connectDB.getConnection().prepareStatement(query)) {
            stmt.setString(1, ISBN);
            stmt.setInt(2, quantity);

            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0; //nếu còn trong kho true
            }
        }
    }

    public double selectPrice(String ISBN) throws SQLException {
        String query = "SELECT price FROM `versionofbook` WHERE ISBN = ?";

        try (PreparedStatement stmt = connectDB.getConnection().prepareStatement(query)) {
            stmt.setString(1, ISBN);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble(1);
                } else {
                    return 0.0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    //lấy tên từ personID
    public String getPersonName(String id) throws SQLException {
        String query = "SELECT name FROM Person WHERE id = ?";
        connectDB.connect();
        try (PreparedStatement stmt = connectDB.getConnection().prepareStatement(query)) {
            stmt.setString(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("name");
                } else {
                    return null;
                }
            }
        }
    }

    //kiểm tra xem độc giả có bị khóa không
    public boolean checkReaderIDIsLocked(String readerID) throws SQLException {
        String query = "SELECT COUNT(*) FROM `person` WHERE isActive = 0 and id = ?";

        try (PreparedStatement stmt = connectDB.getConnection().prepareStatement(query)) {
            stmt.setString(1, readerID);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0; //nếu bị khóa return true
            }
        }
    }
    
     public boolean AddBorrow(String readerID, String staffID, Date dueDate, List<BorrowDetailDTO> tempBorrowDetails ) throws SQLException {
        boolean flag = false;
        connectDB.connect();

        if (ConnectDB.conn != null) {
            PreparedStatement preparedStatement = null;
            try {
                ConnectDB.conn.setAutoCommit(false); 
                
                String sql = "INSERT INTO borrowing (readerID, borrowStaffID, dueDate) VALUES (?, ?, ?)";
                preparedStatement = ConnectDB.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, readerID);
                preparedStatement.setString(2, staffID);
                preparedStatement.setDate(3, dueDate);

                if (preparedStatement.executeUpdate() > 0) {
                    int generatedID = -1; 
                    ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        generatedID = generatedKeys.getInt(1);
                    }

                    for (BorrowDetailDTO tempBorrowDetail : tempBorrowDetails) {
                        String query = "INSERT INTO `borrowdetail`(`borrowID`, `ISBN`, `quantity`, `description`) VALUES (?, ?, ?, ?)";
                        try (PreparedStatement stmt = connectDB.getConnection().prepareStatement(query)) {
                            stmt.setInt(1, generatedID);
                            stmt.setString(2, tempBorrowDetail.getISBN());
                            stmt.setInt(3, tempBorrowDetail.getQuantity());
                            stmt.setString(4, tempBorrowDetail.getDescription());
                            stmt.executeUpdate();
                        }
                        
                        String updateQuery = "UPDATE `borrowdetail` SET `quantity` = ? WHERE ISBN = ? AND borrowID = ?";
                        try (PreparedStatement updateStatement = connectDB.getConnection().prepareStatement(updateQuery)) {
                            updateStatement.setInt(1, tempBorrowDetail.getQuantity());
                            updateStatement.setString(2, tempBorrowDetail.getISBN());
                            updateStatement.setInt(3, tempBorrowDetail.getBorrowID());
                            updateStatement.executeUpdate();
                        }
                    }

                    ConnectDB.conn.commit();
                    flag = true;
                }

            } catch (SQLException e) {
                try {
                    ConnectDB.conn.rollback(); 
                } catch (SQLException rollbackException) {
                    rollbackException.printStackTrace();
                }
                e.printStackTrace();

            } finally {
                connectDB.disconnect(); 
            }
        }

        return flag;
    }


    public List<BorrowDTO> getBorrowFromDayToDay(java.sql.Date date1,java.sql.Date date2){
         List<BorrowDTO> borrowList = new ArrayList<>();
        String query = "SELECT * FROM borrowing WHERE borrowDate BETWEEN ? AND ?";

        try {
            // Kết nối cơ sở dữ liệu
            connectDB.connect();

            // Chuẩn bị câu lệnh SQL
            PreparedStatement stmt = connectDB.getConnection().prepareStatement(query);
            stmt.setDate(1, date1);
            stmt.setDate(2, date2);

            // Thực thi truy vấn
            ResultSet rs = stmt.executeQuery();

            // Duyệt kết quả và thêm vào danh sách
            while (rs.next()) {
                int id = rs.getInt("id");
                String readerID = rs.getString("readerID");
                String staffID = rs.getString("returnStaffID");
                LocalDate borrowDate = rs.getDate("borrowDate").toLocalDate();
                LocalDate dueDate = rs.getDate("dueDate").toLocalDate();
                LocalDate returnDate = rs.getDate("returnDate") != null ? rs.getDate("returnDate").toLocalDate() : null;
                boolean delay = rs.getBoolean("delay");
                long fine = rs.getLong("fine");
                boolean isActive = rs.getBoolean("isActive");

                // Lấy thông tin chi tiết mượn
                // Bạn có thể thực hiện truy vấn phụ ở đây để lấy chi tiết mượn nếu cần
                BorrowDetailDAO borrowDetailDAO = new BorrowDetailDAO();
                Vector<BorrowDetailDTO> borrowDetailDTO = borrowDetailDAO.getBorrowDetails(id);
            
                // Tạo đối tượng BorrowDTO và thêm vào danh sách
                BorrowDTO borrow = new BorrowDTO(id, readerID, staffID, borrowDate, dueDate, returnDate, delay, fine, isActive, borrowDetailDTO);
                borrowList.add(borrow);
            }

            // Đóng kết nối
            rs.close();
            stmt.close();
            connectDB.disconnect();

        } catch (SQLException e) {
            e.printStackTrace(); // Xử lý lỗi nếu có
        }

        return borrowList;
    }
}
