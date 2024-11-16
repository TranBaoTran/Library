/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.BorrowDetailDTO;
import connection.ConnectDB;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import DTO.PersonDTO;
import DTO.RoleDTO;
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
    private ConnectDB connectDB;

    public PersonDAO() {
        this.connectDB =  new ConnectDB();
    }

    public boolean addPerson(PersonDTO person) throws SQLException {
        connectDB.connect();
        String query = "INSERT INTO Person (id, name, tel, address, schoolYear) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement stmt = connectDB.getConnection().prepareStatement(query);
        stmt.setString(1, person.getId());
        stmt.setString(2, person.getName());
        stmt.setString(3, person.getTel());
        stmt.setString(4, person.getAddress());
        stmt.setString(5, person.getSchoolYear());
        return stmt.executeUpdate() > 0;
    }

    public boolean updatePerson(PersonDTO person) throws SQLException {
        connectDB.connect();
        String query = "UPDATE Person SET name = ?, tel = ?, address = ?, schoolYear = ? WHERE id = ?";
        PreparedStatement stmt = connectDB.getConnection().prepareStatement(query);
        stmt.setString(1, person.getName());
        stmt.setString(2, person.getTel());
        stmt.setString(3, person.getAddress());
        stmt.setString(4, person.getSchoolYear());
        stmt.setString(5, person.getId());
        return stmt.executeUpdate() > 0;
    }
    
    public boolean deletePerson(String id) throws SQLException {
        connectDB.connect();
        String query = "DELETE FROM Person WHERE id = ?";
        PreparedStatement stmt = connectDB.getConnection().prepareStatement(query);
        stmt.setString(1, id);
        return stmt.executeUpdate() > 0;
    }
    
    public ArrayList<PersonDTO> searchPerson(String keyword) throws SQLException {
        connectDB.connect();
        String query = "SELECT * FROM Person WHERE name LIKE ? OR tel LIKE ?";
        PreparedStatement stmt = connectDB.getConnection().prepareStatement(query);
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
        connectDB.connect();
        String query = "SELECT * FROM Person WHERE isActive = 1";
        Statement stmt = connectDB.getConnection().createStatement();
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
    
    public List<PersonDTO> getAllReaders() throws SQLException  {
        List<PersonDTO> readerList = new ArrayList<>();
        try {
            connectDB.connect();
            String query = "SELECT person.*, account.positionID FROM person\n" +
                            "INNER JOIN account ON person.id = account.id\n" +
                            "WHERE account.positionID IN ('SV', 'GV')";
            Statement stmt = connectDB.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                RoleDTO roleDTO = new RoleDTO(rs.getString("positionID"), null);
                readerList.add(new PersonDTO(
                    rs.getString("id"),
                    rs.getString("name"),
                    rs.getString("tel"),
                    rs.getString("address"),
                    rs.getString("schoolYear"),
                    roleDTO
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return readerList;
    }

}

