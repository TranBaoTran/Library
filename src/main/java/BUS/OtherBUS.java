/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.OtherDAO;
import DTO.AuthorDTO;
import DTO.CategoryDTO;
import DTO.PublisherDTO;
import DTO.SupplierDTO;
import java.util.List;

/**
 *
 * @author antra
 */
public class OtherBUS {
    private OtherDAO otherDAO;
    public OtherBUS(){
        otherDAO = new OtherDAO();
    }
    public List<AuthorDTO> loadAuthorData(){
        return otherDAO.loadAuthorData();
    }
    public List<CategoryDTO> loadCategoryData(){
        return otherDAO.loadCategoryData();
    }
    public List<PublisherDTO> loadPublisherData(){
        return otherDAO.loadPublisherData();
    }
    public List<SupplierDTO> loadSupplierData(){
        return otherDAO.loadSupplierData();
    }
}
