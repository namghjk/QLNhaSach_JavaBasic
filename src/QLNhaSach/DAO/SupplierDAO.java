package QLNhaSach.DAO;

import QLNhaSach.DTO.BookDTO;
import QLNhaSach.DTO.SupplierDTO;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class SupplierDAO {
    MyConnectUnitDAO connect;

    public ArrayList<SupplierDTO> docDB(String condition, String orderBy) throws Exception {
        connect = new MyConnectUnitDAO();        
        ResultSet result = this.connect.Select("nhacungcap", condition, orderBy);
        ArrayList<SupplierDTO> nhaccs = new ArrayList<>();
        while ( result.next() ) {
            SupplierDTO nhacc = new SupplierDTO();
            nhacc.setCodeSupplier(result.getString("Mancc"));
            nhacc.setNameSupplier(result.getString("Tenncc"));
            nhacc.setAddressSupplier(result.getString("DiaChi"));
            nhacc.setPhonenumberSupplier(result.getString("Sdt"));
            nhaccs.add(nhacc);
        }
        connect.Close();
        return nhaccs;
    }
    
    public ArrayList<SupplierDTO> docDB(String condition) throws Exception {
        return docDB(condition, null);
    }
    
    public ArrayList<SupplierDTO> docDB() throws Exception {
        return docDB(null);
    }
    public List<SupplierDTO> listSuppliers() throws Exception
    {
        List<SupplierDTO> suppliers = new ArrayList<SupplierDTO>();
        connect = new MyConnectUnitDAO();
        
        ResultSet result = this.connect.Select("nhacungcap");
        while ( result.next() ) {
            SupplierDTO supp = new SupplierDTO();
            supp.setCodeSupplier(result.getString("MANCC"));
            supp.setNameSupplier(result.getString("TenNCC"));
            supp.setAddressSupplier(result.getString("DiaChi"));
            supp.setPhonenumberSupplier(result.getString("Sdt"));
            suppliers.add(supp);
        }
        connect.Close();
        return suppliers;
    }

    public Boolean them(SupplierDTO nv) throws Exception {
        connect = new MyConnectUnitDAO();
        HashMap<String, Object> insertValues = new HashMap<>();
        insertValues.put("Mancc", nv.getCodeSupplier());
        insertValues.put("Tenncc", nv.getNameSupplier());
        insertValues.put("DiaChi", nv.getAddressSupplier());
        insertValues.put("Sdt", nv.getPhonenumberSupplier());        
        Boolean check = connect.Insert("nhacungcap", insertValues);       
        connect.Close();
        return check;
    }

    public Boolean xoa(SupplierDTO nv) throws Exception {
        connect = new MyConnectUnitDAO();
        String condition = " Mancc = '"+nv.getCodeSupplier()+"'";
        
        Boolean check = connect.Delete("nhacungcap", condition);
        
        connect.Close();
        return check;
    }
    
    public Boolean sua(SupplierDTO nv) throws Exception {
        connect = new MyConnectUnitDAO();
        HashMap<String, Object> insertValues = new HashMap<>();
        insertValues.put("Tenncc", nv.getNameSupplier());
        insertValues.put("DiaChi", nv.getAddressSupplier());
        insertValues.put("Sdt", nv.getPhonenumberSupplier());        
        String condition = " Mancc = '"+nv.getCodeSupplier()+"'";        
        Boolean check = connect.Update("nhacungcap", insertValues, condition);        
        connect.Close();
        return check;
    }
}
 