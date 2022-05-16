package QLNhaSach.DAO;


import QLNhaSach.DTO.AccountDTO;
import QLNhaSach.DTO.ImportCouponDTO;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class ImportCouponDAO {
    MyConnectUnitDAO connect;
    public ArrayList<ImportCouponDTO> docDB(String condition, String orderBy) throws Exception {
        connect = new MyConnectUnitDAO();
        ResultSet result = this.connect.Select("phieunhaphang", condition, orderBy);
        ArrayList<ImportCouponDTO> nhaccs = new ArrayList<>();
        while ( result.next() ) {
            ImportCouponDTO nhacc = new ImportCouponDTO();
            nhacc.setCodeCoupon(result.getString("MAPHIEU"));
            nhacc.setCodeStaff(result.getString("MANV"));
            nhacc.setCodeSupplier(result.getString("MANCC"));
            nhacc.setDate(result.getString("Ngay")); 
            nhaccs.add(nhacc);
        }
        connect.Close();
        return nhaccs;
    }
    
    public ArrayList<ImportCouponDTO> docDB(String condition) throws Exception {
        return docDB(condition, null);
    }
    
    public ArrayList<ImportCouponDTO> docDB() throws Exception {
        return docDB(null);
    }
    public List<ImportCouponDTO> listImports() throws Exception
    {
        List<ImportCouponDTO> imports = new ArrayList<ImportCouponDTO>();
        connect = new MyConnectUnitDAO();      
        ResultSet result = this.connect.Select("phieunhaphang");
        while ( result.next() ) {
            ImportCouponDTO importe = new ImportCouponDTO();
            importe.setCodeCoupon(result.getString("MAPHIEU"));
            importe.setCodeStaff(result.getString("MANV"));
            importe.setCodeSupplier(result.getString("MANCC"));
            importe.setDate(result.getString("Ngay"));
            imports.add(importe);
        }
        connect.Close();
        return imports;
    }
    
    public Boolean them(ImportCouponDTO nv) throws Exception {
        connect = new MyConnectUnitDAO();
        HashMap<String, Object> insertValues = new HashMap<>();
        insertValues.put("MANCC", nv.getCodeSupplier());
        insertValues.put("MANV", nv.getCodeStaff());
        insertValues.put("MAPHIEU", nv.getCodeCoupon());
        insertValues.put("Ngay", nv.getDate());
        Boolean check = connect.Insert("phieunhaphang", insertValues);       
        connect.Close();
        return check;
    }

    public Boolean xoa(ImportCouponDTO nv) throws Exception {
        connect = new MyConnectUnitDAO();
        String condition = " mapn = '"+nv.getCodeCoupon()+"'";  
        Boolean check = connect.Delete("phieunhaphang", condition);   
        connect.Close();
        return check;
    }
    
    public Boolean sua(ImportCouponDTO nv) throws Exception {
        connect = new MyConnectUnitDAO();
        HashMap<String, Object> insertValues = new HashMap<>();
        insertValues.put("MANCC", nv.getCodeSupplier());
        insertValues.put("MANV", nv.getCodeStaff());
        insertValues.put("Ngay", nv.getDate());
        String condition = " mapn = '"+nv.getCodeCoupon()+"'";
        Boolean check = connect.Update("phieunhaphang", insertValues, condition);
        connect.Close();
        return check;
    }
}
