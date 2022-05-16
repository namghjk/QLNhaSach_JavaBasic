package QLNhaSach.DAO;

import QLNhaSach.DAO.MyConnectUnitDAO;
import QLNhaSach.DTO.BillDTO;
import QLNhaSach.DTO.DetailBillDTO;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BillDAO {
    MyConnectUnitDAO connect;

    public ArrayList<BillDTO> docDB(String condition, String orderBy) throws Exception {
        // kết nối CSDL
        connect = new MyConnectUnitDAO();
        
        ResultSet result = this.connect.Select("hoadon", condition, orderBy);
        ArrayList<BillDTO> hoadons = new ArrayList<>();
        while ( result.next() ) {
            BillDTO hoadon = new BillDTO();
            hoadon.setCodeBill(result.getString("MAHD"));
            hoadon.setCodeCustomer(result.getString("MAKH"));
            hoadon.setCodeStaff(result.getString("MANV"));
            hoadon.setDaySale(result.getString("NgayLapHD"));   
            hoadon.setThanhTien(Float.parseFloat(result.getString("ThanhTien"))); 
            hoadons.add(hoadon);
        }
        connect.Close();
        return hoadons;
    }
    
    public ArrayList<BillDTO> docDB(String condition) throws Exception {
        return docDB(condition, null);
    }
    
    public ArrayList<BillDTO> docDB() throws Exception {
        return docDB(null);
    }
    public List<BillDTO> listBills() throws Exception
    {
        List<BillDTO> bills = new ArrayList<BillDTO>();
        connect = new MyConnectUnitDAO();
        
        ResultSet result = this.connect.Select("hoadon");
        while ( result.next() ) {
            BillDTO bill = new BillDTO();
            bill.setCodeBill(result.getString("MAHD"));
            bill.setCodeStaff(result.getString("MANV"));
            bill.setCodeCustomer(result.getString("MAKH"));
            bill.setDaySale(result.getString("NgayLapHD"));
            bill.setThanhTien(Float.parseFloat(result.getString("ThanhTien")));
            bills.add(bill);
        }
        connect.Close();
        return bills;
    }

    public Boolean them(BillDTO hd) throws Exception {
        connect = new MyConnectUnitDAO();
        HashMap<String, Object> insertValues = new HashMap<>();
        insertValues.put("MAHD", hd.getCodeBill());
        insertValues.put("MAKH", hd.getCodeCustomer());
        insertValues.put("MANV", hd.getCodeStaff());
        insertValues.put("NgayLapHD", hd.getDaySale());
        insertValues.put("ThanhTien", hd.getThanhTien());
        
        Boolean check = connect.Insert("hoadon", insertValues);
        
        connect.Close();
        return check;
    }
    
    public Boolean xoa(BillDTO hd) throws Exception {
        connect = new MyConnectUnitDAO();
        String condition = " MAHD = '"+hd.getCodeBill()+"'";
        
        Boolean check = connect.Delete("hoadon", condition);
        
        connect.Close();
        return check;
    }

    public Boolean sua(BillDTO hd) throws Exception {
        connect = new MyConnectUnitDAO();
        
        // tạo đối tượng truyền vào
        HashMap<String, Object> insertValues = new HashMap<>();
        insertValues.put("MAHD", hd.getCodeBill());
        insertValues.put("MAKH", hd.getCodeCustomer());
        insertValues.put("MANV", hd.getCodeStaff());
        insertValues.put("NgayLapHD", hd.getDaySale());
        insertValues.put("ThanhTien", hd.getThanhTien());
        
        String condition = " MaHD = '"+hd.getCodeBill()+"'";
        
        Boolean check = connect.Update("hoadon", insertValues, condition);
        
        connect.Close();
        return check;
    }
}
