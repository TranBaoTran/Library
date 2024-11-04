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
import java.util.List;

import connection.ConnectDB;

/**
 *
 * @author User
 */
public class AuthorDTO {
    ConnectDB connectDB;
    private Connection connection;

    private int id;
    private String name;
    private int year;

    public AuthorDTO(int id, String name, int year) {
        this.id = id;
        this.name = name;
        this.year = year;
    }

    public AuthorDTO() {
        try {
            connectDB = new ConnectDB();
            connectDB.connect(); // Thêm dòng này để kết nối tới cơ sở dữ liệu
            connection = connectDB.getConnection(); // Lấy Connection từ ConnectDB
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<String> getAllAuthors() {
        List<String> authors = new ArrayList<>();
        String sql = "SELECT name FROM author WHERE isActive = 1";

        try (PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                authors.add(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return authors;
    }
}
