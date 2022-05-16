package QLNhaSach.DAO;

import QLNhaSach.DTO.AccountDTO;
import QLNhaSach.DTO.StaffDTO;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class StaffDAO {
    MyConnectUnitDAO connect;

    public ArrayList<StaffDTO> docDB(String condition, String orderBy) throws Exception {
        connect = new MyConnectUnitDAO();       
        ResultSet result = this.connect.Select("nhanvien", condition, orderBy);
        ArrayList<StaffDTO> Staffs = new ArrayList<>();
        while ( result.next() ) {
           StaffDTO Staff = new StaffDTO();
            Staff.setCodeStaff(result.getString("MANV") );
            Staff.setDateOfBirth(result.getString("NgaySinh"));
            Staff.setEmail(result.getString("Email"));
            Staff.setLastName(result.getString("Ho"));
            Staff.setFirstName(result.getString("Ten"));
            Staffs.add(Staff);
        }
        connect.Close();
        return Staffs;
    }
    
    public ArrayList<StaffDTO> docDB(String condition) throws Exception {
        return docDB(condition, null);
    }
    
    public ArrayList<StaffDTO> docDB() throws Exception {
        return docDB(null);
    }
    public List<StaffDTO> listStaffs() throws Exception
    {
        List<StaffDTO> staffs = new ArrayList<StaffDTO>();
        connect = new MyConnectUnitDAO();
        
        ResultSet result = this.connect.Select("nhanvien");
        while ( result.next() ) {
            StaffDTO staff = new StaffDTO();
            staff.setCodeStaff(result.getString("MANV"));
            staff.setLastName(result.getString("Ho"));
            staff.setFirstName(result.getString("Ten"));
            staff.setDateOfBirth(result.getString("NgaySinh"));
            staff.setEmail(result.getString("Email"));
            staff.setSalary( Float.parseFloat(result.getString("Luong")) );
            staffs.add(staff);
        }
        connect.Close();
        return staffs;
    }
    
    public Boolean them(StaffDTO Staff) throws Exception {
        connect = new MyConnectUnitDAO();
        
        // tạo đối tượng truyền vào
        HashMap<String, Object> insertValues = new HashMap<>();
        insertValues.put("MANV", Staff.getCodeStaff());
        insertValues.put("Ho", Staff.getLastName());
        insertValues.put("Ten", Staff.getFirstName());
        insertValues.put("NgaySinh", Staff.getDateOfBirth());
        insertValues.put("Email", Staff.getEmail());
        insertValues.put("Luong", Staff.getSalary());      
        Boolean check = connect.Insert("nhanvien", insertValues);   
        connect.Close();
        return check;
    }
    
    public Boolean xoa(StaffDTO Staff) throws Exception {
        connect = new MyConnectUnitDAO();
        String condition = " MANV = '"+Staff.getCodeStaff()+"'";
        
        Boolean check = connect.Delete("nhanvien", condition);
        
        connect.Close();
        return check;
    }
    
    public Boolean sua(StaffDTO Staff) throws Exception {
        connect = new MyConnectUnitDAO();
        HashMap<String, Object> insertValues = new HashMap<>();
        insertValues.put("Ho", Staff.getLastName());
        insertValues.put("Ten", Staff.getFirstName());
        insertValues.put("MANV", Staff.getCodeStaff());
        insertValues.put("Email", Staff.getEmail());
        insertValues.put("NgaySinh", Staff.getDateOfBirth());
        insertValues.put("Luong", Staff.getSalary());        
        String condition = " MANV = '"+Staff.getCodeStaff()+"'";        
        Boolean check = connect.Update("nhanvien", insertValues, condition);        
        connect.Close();
        return check;
    }
}
