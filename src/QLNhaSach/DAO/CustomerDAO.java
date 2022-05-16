package QLNhaSach.DAO;

import QLNhaSach.DAO.MyConnectUnitDAO;
import QLNhaSach.DTO.CustomerDTO;
import QLNhaSach.GUI.Controller;
import static com.sun.tools.attach.VirtualMachine.list;
import static java.nio.file.Files.list;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.HashMap;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class CustomerDAO {
    MyConnectUnitDAO connect;
    Controller ctrler = new Controller();
    public ArrayList<CustomerDTO> docDB(String condition, String orderBy) throws Exception {
        connect = new MyConnectUnitDAO();       
        ResultSet result = this.connect.Select("khachhang", condition, orderBy);
        ArrayList<CustomerDTO> customers = new ArrayList<CustomerDTO>();
        while ( result.next() ) {
            CustomerDTO customer = new CustomerDTO();
            customer.setCustomerID(result.getString("MAKH"));
            customer.setHo(result.getString("Ho"));
            customer.setTen(result.getString("Ten"));
            customer.setAddress(result.getString("DiaChi"));
            customer.setSdt(result.getString("Sdt"));
            customer.setEmail(result.getString("Email"));
            customers.add(customer);
        }
        connect.Close();
        return customers;
    }
    
    public ArrayList<CustomerDTO> docDB(String condition) throws Exception {
        return docDB(condition, null);
    }
    
    public ArrayList<CustomerDTO> docDB() throws Exception {
        return docDB(null);
    }

    public List<CustomerDTO> listCustomers() throws Exception
    {
        List<CustomerDTO> customers = new ArrayList<CustomerDTO>();
        connect = new MyConnectUnitDAO();        
        ResultSet result = this.connect.Select("khachhang");
        while ( result.next() ) {
            CustomerDTO customer = new CustomerDTO();
            customer.setCustomerID(result.getString("MAKH"));
            customer.setHo(result.getString("Ho"));
            customer.setTen(result.getString("Ten"));
            customer.setAddress(result.getString("DiaChi"));
            customer.setSdt(result.getString("Sdt"));
            customer.setEmail(result.getString("Email"));
            customers.add(customer);
        }
        connect.Close();
        return customers;
    }
    
    public Boolean Add(CustomerDTO kh) throws Exception {
        connect = new MyConnectUnitDAO();
        HashMap<String, Object> insertValues = new HashMap<>();
        insertValues.put("MAKH", kh.getCustomerID());
        insertValues.put("Ho", kh.getHo());
        insertValues.put("Ten", kh.getTen());
        insertValues.put("DiaChi", kh.getAddress());
        insertValues.put("Sdt", kh.getSdt());
        insertValues.put("Email", kh.getEmail());       
        Boolean check = connect.Insert("khachhang", insertValues);       
        connect.Close();
        return check;
    }

    public Boolean Delete(CustomerDTO kh) throws Exception {
        connect = new MyConnectUnitDAO();
        String condition = " MAKH = '"+kh.getCustomerID()+"'";       
        Boolean check = connect.Delete("khachhang", condition);        
        connect.Close();
        return check;
    }

    public Boolean Fix(CustomerDTO kh) throws Exception {
        connect = new MyConnectUnitDAO();      
        HashMap<String, Object> insertValues = new HashMap<>();
        insertValues.put("MAKH", kh.getCustomerID());
        insertValues.put("Ho", kh.getHo());
        insertValues.put("Ten", kh.getTen());
        insertValues.put("DiaChi", kh.getAddress());
        insertValues.put("Sdt", kh.getSdt());
        insertValues.put("Email", kh.getEmail());      
        String condition = " MAKH = '"+kh.getCustomerID()+"'";        
        Boolean check = connect.Update("khachhang", insertValues, condition);       
        connect.Close();
        return check;
    }
}
