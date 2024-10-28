/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.AuthorDTO;
import DTO.BookNameDTO;
import DTO.CategoryDTO;
import DTO.PublisherDTO;
import DTO.SupplierDTO;
import connection.ConnectDB;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

/**
 *
 * @author User
 */
public class BookDAO {
    ConnectDB connectDB;
    
    public BookDAO() throws ClassNotFoundException, SQLException, IOException {
        connectDB = new ConnectDB();
    }
    
    public Vector<AuthorDTO> getAllAuthor() throws SQLException{
        Vector<AuthorDTO> result = new Vector<>();
        connectDB.connect();
        if (ConnectDB.conn != null){
            try{
                String sql = "SELECT author.* FROM author where isActive = 1";
                PreparedStatement stmt = ConnectDB.conn.prepareStatement(sql);  
                ResultSet rs = stmt.executeQuery();
          
                while(rs.next()) {
                   AuthorDTO u = new AuthorDTO();
                   u.setId(rs.getInt(1));
                   u.setName(rs.getString(2));
                   u.setYear(rs.getInt(3));
                   result.add(u);
                }
                 
            }catch(SQLException e){
            }finally {
                connectDB.disconnect();
            }
        }
        return result;
    }
    
    public boolean isAuthorExist(AuthorDTO author) throws SQLException{
        boolean flag = false;
        connectDB.connect();
        if (ConnectDB.conn != null){
            try{
                String sql = "SELECT * FROM author WHERE UPPER(author.name) = UPPER(?) AND author.year = ? AND isActive = 1";
                PreparedStatement stmt = ConnectDB.conn.prepareStatement(sql);  
                stmt.setString(1, author.getName());
                stmt.setInt(2, author.getYear());
                ResultSet rs = stmt.executeQuery();
          
                while(rs.next()) {
                   flag = true;
                }
                 
            }catch(SQLException e){
            }finally {
                connectDB.disconnect();
            }
        }
        return flag;
    }
    
    public boolean addAuthor(AuthorDTO author) throws SQLException{
        boolean flag = false;
        connectDB.connect();
        if (ConnectDB.conn != null){
            try{
                String sql = "INSERT INTO author(name, year) values (?, ?)";
                PreparedStatement stmt = ConnectDB.conn.prepareStatement(sql);  
                stmt.setString(1, author.getName());
                stmt.setInt(2, author.getYear());
          
                if(stmt.executeUpdate()>0){
                    flag=true;
                }
                 
            }catch(SQLException e){
            }finally {
                connectDB.disconnect();
            }
        }
        return flag;
    }
    
    public Vector<PublisherDTO> getAllPublisher() throws SQLException{
        Vector<PublisherDTO> result = new Vector<>();
        connectDB.connect();
        if (ConnectDB.conn != null){
            try{
                String sql = "SELECT publisher.* FROM publisher where isActive = 1";
                PreparedStatement stmt = ConnectDB.conn.prepareStatement(sql);  
                ResultSet rs = stmt.executeQuery();
          
                while(rs.next()) {
                   PublisherDTO u = new PublisherDTO();
                   u.setId(rs.getInt(1));
                   u.setName(rs.getString(2));
                   result.add(u);
                }
                 
            }catch(SQLException e){
            }finally {
                connectDB.disconnect();
            }
        }
        return result;
    }
    
    public boolean isPublisherExist(PublisherDTO publisher) throws SQLException{
        boolean flag = false;
        connectDB.connect();
        if (ConnectDB.conn != null){
            try{
                String sql = "SELECT * FROM publisher WHERE UPPER(publisher.name) = UPPER(?) AND isActive = 1";
                PreparedStatement stmt = ConnectDB.conn.prepareStatement(sql);  
                stmt.setString(1, publisher.getName());
                ResultSet rs = stmt.executeQuery();
          
                while(rs.next()) {
                   flag = true;
                }
                 
            }catch(SQLException e){
            }finally {
                connectDB.disconnect();
            }
        }
        return flag;
    }
    
    public boolean addPublisher(PublisherDTO publisher) throws SQLException{
        boolean flag = false;
        connectDB.connect();
        if (ConnectDB.conn != null){
            try{
                String sql = "INSERT INTO publisher(name) values (?)";
                PreparedStatement stmt = ConnectDB.conn.prepareStatement(sql);  
                stmt.setString(1, publisher.getName());
          
                if(stmt.executeUpdate()>0){
                    flag=true;
                }
                 
            }catch(SQLException e){
            }finally {
                connectDB.disconnect();
            }
        }
        return flag;
    }
    
    public Vector<CategoryDTO> getAllCategory() throws SQLException{
        Vector<CategoryDTO> result = new Vector<>();
        connectDB.connect();
        if (ConnectDB.conn != null){
            try{
                String sql = "SELECT category.* FROM category where isActive = 1";
                PreparedStatement stmt = ConnectDB.conn.prepareStatement(sql);  
                ResultSet rs = stmt.executeQuery();
          
                while(rs.next()) {
                   CategoryDTO u = new CategoryDTO();
                   u.setId(rs.getInt(1));
                   u.setName(rs.getString(2));
                   result.add(u);
                }
                 
            }catch(SQLException e){
            }finally {
                connectDB.disconnect();
            }
        }
        return result;
    }
    
    public boolean isCategoryExist(CategoryDTO category) throws SQLException{
        boolean flag = false;
        connectDB.connect();
        if (ConnectDB.conn != null){
            try{
                String sql = "SELECT * FROM category WHERE UPPER(category.name) = UPPER(?) AND isActive = 1";
                PreparedStatement stmt = ConnectDB.conn.prepareStatement(sql);  
                stmt.setString(1, category.getName());
                ResultSet rs = stmt.executeQuery();
          
                while(rs.next()) {
                   flag = true;
                }
                 
            }catch(SQLException e){
            }finally {
                connectDB.disconnect();
            }
        }
        return flag;
    }
    
    public boolean addCategory(CategoryDTO category) throws SQLException{
        boolean flag = false;
        connectDB.connect();
        if (ConnectDB.conn != null){
            try{
                String sql = "INSERT INTO category(name) values (?)";
                PreparedStatement stmt = ConnectDB.conn.prepareStatement(sql);  
                stmt.setString(1, category.getName());
          
                if(stmt.executeUpdate()>0){
                    flag=true;
                }
                 
            }catch(SQLException e){
            }finally {
                connectDB.disconnect();
            }
        }
        return flag;
    }
    
    public Vector<SupplierDTO> getAllSupplier() throws SQLException{
        Vector<SupplierDTO> result = new Vector<>();
        connectDB.connect();
        if (ConnectDB.conn != null){
            try{
                String sql = "SELECT supplier.* FROM supplier where isActive = 1";
                PreparedStatement stmt = ConnectDB.conn.prepareStatement(sql);  
                ResultSet rs = stmt.executeQuery();
          
                while(rs.next()) {
                   SupplierDTO u = new SupplierDTO();
                   u.setId(rs.getInt(1));
                   u.setName(rs.getString(2));
                   u.setAddress(rs.getString(3));
                   u.setTel(rs.getString(4));
                   result.add(u);
                }
                 
            }catch(SQLException e){
            }finally {
                connectDB.disconnect();
            }
        }
        return result;
    }
    
    public boolean isSupplierExist(SupplierDTO supplier) throws SQLException{
        boolean flag = false;
        connectDB.connect();
        if (ConnectDB.conn != null){
            try{
                String sql = "SELECT * FROM supplier WHERE UPPER(category.name) = UPPER(?) OR tel = ? AND isActive = 1";
                PreparedStatement stmt = ConnectDB.conn.prepareStatement(sql);  
                stmt.setString(1, supplier.getName());
                stmt.setString(2, supplier.getTel());
                ResultSet rs = stmt.executeQuery();
          
                while(rs.next()) {
                   flag = true;
                }
                 
            }catch(SQLException e){
            }finally {
                connectDB.disconnect();
            }
        }
        return flag;
    }
    
    public boolean addSupplier(SupplierDTO supplier) throws SQLException{
        boolean flag = false;
        connectDB.connect();
        if (ConnectDB.conn != null){
            try{
                String sql = "INSERT INTO supplier(name, address, tel) values (?, ?, ?)";
                PreparedStatement stmt = ConnectDB.conn.prepareStatement(sql);  
                stmt.setString(1, supplier.getName());
                stmt.setString(2, supplier.getAddress());
                stmt.setString(3, supplier.getTel());
          
                if(stmt.executeUpdate()>0){
                    flag=true;
                }
                 
            }catch(SQLException e){
                e.printStackTrace();
            }finally {
                connectDB.disconnect();
            }
        }
        return flag;
    }
    
    public Vector<BookNameDTO> getAllBookName() throws SQLException{
        Vector<BookNameDTO> result = new Vector<>();
        connectDB.connect();
        if (ConnectDB.conn != null){
            try{
                String sql = "SELECT book.* FROM book where isActive = 1";
                PreparedStatement stmt = ConnectDB.conn.prepareStatement(sql);  
                ResultSet rs = stmt.executeQuery();
          
                while(rs.next()) {
                   BookNameDTO u = new BookNameDTO();
                   u.setId(rs.getInt(1));
                   u.setName(rs.getString(2));
                   result.add(u);
                }
                 
            }catch(SQLException e){
            }finally {
                connectDB.disconnect();
            }
        }
        return result;
    }
    
    public boolean isBookNameExist(BookNameDTO bookName) throws SQLException{
        boolean flag = false;
        connectDB.connect();
        if (ConnectDB.conn != null){
            try{
                String sql = "SELECT * FROM book WHERE UPPER(book.name) = UPPER(?) AND isActive = 1";
                PreparedStatement stmt = ConnectDB.conn.prepareStatement(sql);  
                stmt.setString(1, bookName.getName());
                ResultSet rs = stmt.executeQuery();
          
                while(rs.next()) {
                   flag = true;
                }
                 
            }catch(SQLException e){
            }finally {
                connectDB.disconnect();
            }
        }
        return flag;
    }
    
    public boolean addBookName(BookNameDTO bookName) throws SQLException{
        boolean flag = false;
        connectDB.connect();
        if (ConnectDB.conn != null){
            try{
                String sql = "INSERT INTO book(name) values (?)";
                PreparedStatement stmt = ConnectDB.conn.prepareStatement(sql);  
                stmt.setString(1, bookName.getName());
          
                if(stmt.executeUpdate()>0){
                    flag=true;
                }
                 
            }catch(SQLException e){
            }finally {
                connectDB.disconnect();
            }
        }
        return flag;
    }
}
