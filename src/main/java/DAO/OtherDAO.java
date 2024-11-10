package DAO;

import DTO.AuthorDTO;
import DTO.CategoryDTO;
import DTO.PublisherDTO;
import DTO.SupplierDTO;
import connection.ConnectDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author antra
 */
public class OtherDAO {
    private ConnectDB db;
    public OtherDAO(){
        db = new ConnectDB();
    }
    public List<AuthorDTO> loadAuthorData(){
        List<AuthorDTO> authors = new ArrayList<>();
            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;
        try{
            db.connect();
            conn = db.getConnection();
            String sqlQuery = "SELECT * FROM author";
            stmt = conn.prepareStatement(sqlQuery);
            rs = stmt.executeQuery();
            while(rs.next()){
                AuthorDTO author = new AuthorDTO();
                author.setId(rs.getInt("id"));
                author.setName(rs.getString("name"));
                author.setYear(rs.getInt("year"));
                authors.add(author);
            }
            
        }catch(SQLException e){
            System.out.println("Error: " + e.getMessage());
        }finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                db.disconnect(); // Đóng kết nối
            } catch (SQLException e) {
                System.out.println("Error closing resources: " + e.getMessage());
            }
        }
        return authors;
    }
    
    public List<CategoryDTO> loadCategoryData(){
        List<CategoryDTO> categories = new ArrayList<>();
            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;
        try{
            db.connect();
            conn = db.getConnection();
            String sqlQuery = "SELECT * FROM category";
            stmt = conn.prepareStatement(sqlQuery);
            rs = stmt.executeQuery();
            while(rs.next()){
                CategoryDTO category = new CategoryDTO();
                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));
                categories.add(category);
            }
            
        }catch(SQLException e){
            System.out.println("Error: " + e.getMessage());
        }finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                db.disconnect(); // Đóng kết nối
            } catch (SQLException e) {
                System.out.println("Error closing resources: " + e.getMessage());
            }
        }
        return categories;
    }
    
    public List<PublisherDTO> loadPublisherData(){
        List<PublisherDTO> publishers = new ArrayList<>();
            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;
        try{
            db.connect();
            conn = db.getConnection();
            String sqlQuery = "SELECT * FROM publisher";
            stmt = conn.prepareStatement(sqlQuery);
            rs = stmt.executeQuery();
            while(rs.next()){
                PublisherDTO publisher = new PublisherDTO();
                publisher.setId(rs.getInt("id"));
                publisher.setName(rs.getString("name"));
                publishers.add(publisher);
            }
            
        }catch(SQLException e){
            System.out.println("Error: " + e.getMessage());
        }finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                db.disconnect(); // Đóng kết nối
            } catch (SQLException e) {
                System.out.println("Error closing resources: " + e.getMessage());
            }
        }
        return publishers;
    }
    
    public List<SupplierDTO> loadSupplierData(){
        List<SupplierDTO> suppliers = new ArrayList<>();
            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;
        try{
            db.connect();
            conn = db.getConnection();
            String sqlQuery = "SELECT * FROM supplier";
            stmt = conn.prepareStatement(sqlQuery);
            rs = stmt.executeQuery();
            while(rs.next()){
                SupplierDTO supplier = new SupplierDTO();
                supplier.setId(rs.getInt("id"));
                supplier.setName(rs.getString("name"));
                suppliers.add(supplier);
            }
            
        }catch(SQLException e){
            System.out.println("Error: " + e.getMessage());
        }finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                db.disconnect(); // Đóng kết nối
            } catch (SQLException e) {
                System.out.println("Error closing resources: " + e.getMessage());
            }
        }
        return suppliers;
    }
}
