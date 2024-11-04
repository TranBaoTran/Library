/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import connection.ConnectDB;

/**
 *
 * @author User
 */
public class BookDTO {
    ConnectDB connectDB;
    private Connection connection;

    private String ISBN;
    private String name;
    private String img;
    private String publisher;
    private Vector<String> authors;
    private Vector<String> categories;
    private String edition;
    private String location;
    private long price;
    private int quantity;
    private int available;
    
    public BookDTO(String ISBN, String name, String img, String publisher, Vector<String> authors, Vector<String> categories, String edition, String location, long price, int quantity, int available) {
        this.ISBN = ISBN;
        this.name = name;
        this.img = img;
        this.publisher = publisher;
        this.authors = authors;
        this.categories = categories;
        this.edition = edition;
        this.location = location;
        this.price = price;
        this.quantity = quantity;
        this.available = available;
    }

    public BookDTO() {
        try {
            connectDB = new ConnectDB();
            connectDB.connect(); // Thêm dòng này để kết nối tới cơ sở dữ liệu
            connection = connectDB.getConnection(); // Lấy Connection từ ConnectDB
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Vector<String> getAuthors() {
        return authors;
    }

    public void setAuthors(Vector<String> authors) {
        this.authors = authors;
    }

    public Vector<String> getCategories() {
        return categories;
    }

    public void setCategories(Vector<String> categories) {
        this.categories = categories;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public List<BookDTO> getAllBooks(String searchSql)
    {
        List<BookDTO> books = new ArrayList<>();
        String sql = generateSQL() + searchSql;
        System.out.println(sql);

        try (PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery()) {

            // Sử dụng một map để nhóm các sách theo ISBN
            Map<String, BookDTO> bookMap = new HashMap<>();

            while (rs.next()) {
                String isbn = rs.getString("ISBN");
                BookDTO book = bookMap.get(isbn);

                // Nếu sách chưa có trong map, tạo mới
                if (book == null) {
                    book = new BookDTO(
                        isbn,
                        rs.getString("BookName"),
                        rs.getString("img"),
                        rs.getString("PublisherName"),
                        new Vector<>(), // Placeholder cho authors
                        new Vector<>(), // Placeholder cho categories
                        rs.getString("edition"),
                        rs.getString("location"),
                        rs.getLong("price"),
                        rs.getInt("quantity"),
                        rs.getInt("available")
                    );
                    bookMap.put(isbn, book);
                }

                // Thêm tác giả nếu có
                String authorName = rs.getString("AuthorName");
                if (authorName != null && !book.getAuthors().contains(authorName)) {
                    book.getAuthors().add(authorName);
                }

                // Thêm thể loại nếu có
                String categoryName = rs.getString("CategoryName");
                if (categoryName != null && !book.getCategories().contains(categoryName)) {
                    book.getCategories().add(categoryName);
                }
            }

            // Chuyển đổi map thành danh sách
            books.addAll(bookMap.values());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        printBooks(books);

        return books;
    }

    private String generateSQL() {
        return "SELECT "
            + "b.id AS BookID, "
            + "b.name AS BookName, "
            + "b.isActive AS BookIsActive, "
            + "v.ISBN, "
            + "v.img, "
            + "v.publisherID, "
            + "p.name AS PublisherName, "
            + "v.edition, "
            + "v.location, "
            + "v.price, "
            + "v.quantity, "
            + "v.available, "
            + "ba.authorID AS AuthorID, "
            + "a.name AS AuthorName, "
            + "bc.categoryID AS CategoryID, "
            + "c.name AS CategoryName "
            + "FROM book b "
            + "LEFT JOIN versionofbook v ON b.id = v.bookID "
            + "LEFT JOIN publisher p ON v.publisherID = p.id "
            + "LEFT JOIN bookauthor ba ON b.id = ba.bookID "
            + "LEFT JOIN author a ON ba.authorID = a.id "
            + "LEFT JOIN bookcategory bc ON b.id = bc.bookID "
            + "LEFT JOIN category c ON bc.categoryID = c.id "
            + "WHERE "
            + "b.isActive = 1 AND "
            + "p.isActive = 1 AND "
            + "c.isActive = 1 AND "
            + "a.isActive = 1";
    }

    public boolean deleteBookByISBN(String isbn) {
        String checkSql = "SELECT v.available, v.quantity, COALESCE(i.quantity, 0) AS importQuantity " +
                          "FROM versionofbook v " +
                          "LEFT JOIN importdetail i ON v.ISBN = i.ISBN " +
                          "WHERE v.ISBN = ?";
        
        String deleteSql = "DELETE FROM versionofbook WHERE ISBN = ?";
        
        try {
            PreparedStatement checkStmt = connection.prepareStatement(checkSql);
            checkStmt.setString(1, isbn);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                int available = rs.getInt("available");
                int quantity = rs.getInt("quantity");
                int importQuantity = rs.getInt("importQuantity");

                // Kiểm tra điều kiện xóa
                if (available == 0 && quantity == 0 && importQuantity == 0) {
                    // Thực hiện xóa
                    PreparedStatement deleteStmt = connection.prepareStatement(deleteSql);
                    deleteStmt.setString(1, isbn);
                    deleteStmt.executeUpdate();
                    return true; // Xóa thành công
                } else {
                    System.out.println("Không thể xóa sách: Điều kiện không thỏa mãn. available != 0 or quantity != 0 or importQuantity == 0");
                    return false; // Không xóa do điều kiện không thỏa mãn
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Trả về false nếu không tìm thấy ISBN hoặc có lỗi
    }

    public void printBooks(List<BookDTO> books) {
        for (BookDTO book : books) {
            System.out.println("ISBN: " + book.getISBN());
            System.out.println("Tên sách: " + book.getName());
            System.out.println("Nhà xuất bản: " + book.getPublisher());
            System.out.println("Hình ảnh: " + book.getImg());
            System.out.println("Phiên bản: " + book.getEdition());
            System.out.println("Kệ sách: " + book.getLocation());
            System.out.println("Giá: " + book.getPrice());
            System.out.println("Số lượng: " + book.getQuantity());
            System.out.println("Số lượng có sẵn: " + book.getAvailable());
            System.out.println("Tác giả: " + book.getAuthors());
            System.out.println("Thể loại: " + book.getCategories());
            System.out.println("-------------------------");
        }
    }
}
