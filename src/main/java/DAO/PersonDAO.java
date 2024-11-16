/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import connection.ConnectDB;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import DTO.PersonDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
/**
 *
 * @author User
 */
public class PersonDAO {
    private Connection conn;

    public PersonDAO(Connection conn) {
        this.conn = conn;
    }

    public boolean addPerson(PersonDTO person) throws SQLException {
        String query = "INSERT INTO Person (id, name, tel, address, schoolYear) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, person.getId());
        stmt.setString(2, person.getName());
        stmt.setString(3, person.getTel());
        stmt.setString(4, person.getAddress());
        stmt.setString(5, person.getSchoolYear());
        return stmt.executeUpdate() > 0;
    }

    public boolean updatePerson(PersonDTO person) throws SQLException {
        String query = "UPDATE Person SET name = ?, tel = ?, address = ?, schoolYear = ? WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, person.getName());
        stmt.setString(2, person.getTel());
        stmt.setString(3, person.getAddress());
        stmt.setString(4, person.getSchoolYear());
        stmt.setString(5, person.getId());
        return stmt.executeUpdate() > 0;
    }
    
    public boolean deletePerson(String id) throws SQLException {
        String query = "DELETE FROM Person WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, id);
        return stmt.executeUpdate() > 0;
    }
    
    public ArrayList<PersonDTO> searchPerson(String keyword) throws SQLException {
        String query = "SELECT * FROM Person WHERE name LIKE ? OR tel LIKE ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, "%" + keyword + "%");
        stmt.setString(2, "%" + keyword + "%");
        ResultSet rs = stmt.executeQuery();

        ArrayList<PersonDTO> result = new ArrayList<>();
        while (rs.next()) {
            result.add(new PersonDTO(
                rs.getString("id"),
                rs.getString("name"),
                rs.getString("tel"),
                rs.getString("address"),
                rs.getString("schoolYear")
            ));
        }
        return result;
    }
    public List<PersonDTO> getAllStaff() throws SQLException {
        String query = "SELECT * FROM Person WHERE isActive = 1";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        List<PersonDTO> staffList = new ArrayList<>();
        while (rs.next()) {
            staffList.add(new PersonDTO(
                rs.getString("id"),
                rs.getString("name"),
                rs.getString("tel"),
                rs.getString("address"),
                rs.getString("schoolYear")
            ));
        }
        return staffList;
    }
}

