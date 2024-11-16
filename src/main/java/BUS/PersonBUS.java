/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.PersonDAO;
import DTO.PersonDTO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class PersonBUS {
    private PersonDAO personDAO;

    public PersonBUS(Connection conn) {
        personDAO = new PersonDAO(conn);
    }

    public List<PersonDTO> getAllStaff() throws Exception {
        return personDAO.getAllStaff();
    }
    
    public boolean addPerson(PersonDTO person) {
        try {
            return personDAO.addPerson(person);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updatePerson(PersonDTO person) {
        try {
            return personDAO.updatePerson(person);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deletePerson(String id) {
        try {
            return personDAO.deletePerson(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<PersonDTO> searchPerson(String keyword) {
        try {
            return personDAO.searchPerson(keyword);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
