/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import connection.ConnectDB;
import java.sql.SQLException;
import java.util.List;
import DTO.PersonDTO;
import DTO.RoleDTO;
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
//        String query = "SELECT * FROM Person WHERE isActive = 1";
        String query = "SELECT person.*, account.positionID FROM person\n" +
                            "INNER JOIN account ON person.id = account.id\n" +
                            "WHERE account.positionID IN ('SV', 'GV')";
        Statement stmt = connectDB.getConnection().createStatement();
        ResultSet rs = stmt.executeQuery(query);

        List<PersonDTO> staffList = new ArrayList<>();
        while (rs.next()) {
            RoleDTO roleDTO = new RoleDTO(rs.getString("positionID"), null);
            staffList.add(new PersonDTO(
                rs.getString("id"),
                rs.getString("name"),
                rs.getString("tel"),
                rs.getString("address"),
                rs.getString("schoolYear"),
                roleDTO
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
    
    public PersonDTO getPersonById(String id) throws SQLException {
        try {
            connectDB.connect();
            PreparedStatement stmt = connectDB.getConnection().prepareStatement("SELECT * FROM person WHERE id = ?");
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new PersonDTO(
                rs.getString("id"),
                rs.getString("name"),
                rs.getString("tel"),
                rs.getString("address"),
                rs.getString("schoolYear")
            );
            } else
                return null;
        } catch (SQLException e) {
          throw e;
        }
    }
    
    public List<PersonDTO> searchReadersWithRole(String keyword, boolean isSinhVienChecked, boolean isGiangVienChecked) {
        List<PersonDTO> listReader = new ArrayList<>();
        String sql = "SELECT * FROM Person WHERE (id LIKE ? OR name LIKE ? OR tel LIKE ?)"; // Truy vấn cơ bản

        // Điều kiện để lọc theo vai trò
        if (isSinhVienChecked && !isGiangVienChecked) {
            sql += " AND roleId = 'SV'"; // Chỉ tìm Sinh viên
        } else if (isGiangVienChecked && !isSinhVienChecked) {
            sql += " AND roleId = 'GV'"; // Chỉ tìm Giảng viên
        }

        try (PreparedStatement stmt = connectDB.getConnection().prepareStatement(sql)) {
            String searchKeyword = "%" + keyword + "%"; // Tìm kiếm với LIKE
            stmt.setString(1, searchKeyword);
            stmt.setString(2, searchKeyword);
            stmt.setString(3, searchKeyword);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("name");
                String tel = rs.getString("tel");
                String address = rs.getString("address");
                String schoolYear = rs.getString("schoolYear");
                RoleDTO role = new RoleDTO(rs.getString("roleId"), rs.getString("roleName"));
                PersonDTO person = new PersonDTO(id, name, tel, address, schoolYear, role);
                listReader.add(person);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listReader;
    }


}

