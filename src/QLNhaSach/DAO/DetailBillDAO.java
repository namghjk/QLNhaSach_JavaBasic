package QLNhaSach.DAO;

import QLNhaSach.DTO.DetailBillDTO;
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

public class DetailBillDAO {
    MyConnectUnitDAO connect;
    Controller ctrler = new Controller();
    public ArrayList<DetailBillDTO> docDB(String condition, String orderBy) throws Exception {
        connect = new MyConnectUnitDAO();      
        ResultSet result = this.connect.Select("chitiethoadon", condition, orderBy);
        ArrayList<DetailBillDTO> detailbills = new ArrayList<DetailBillDTO>();
        while ( result.next() ) {
            DetailBillDTO detailbill = new DetailBillDTO();
            detailbill.setCodeBill(result.getString("MAHD"));
            detailbill.setCodeBook(result.getString("MASACH"));
            detailbill.setPrice( Integer.parseInt(result.getString("DonGia")) );
            detailbill.setNumber(Integer.parseInt(result.getString("SoLuong")));
            detailbill.setSale(Integer.parseInt(result.getString("GiamGia")));
            detailbill.setTotal(Float.parseFloat(result.getString("ThanhTien")));
            detailbills.add(detailbill);
        }
        connect.Close();
        return detailbills;
    }
    
    public ArrayList<DetailBillDTO> docDB(String condition) throws Exception {
        return docDB(condition, null);
    }
    
    public ArrayList<DetailBillDTO> docDB() throws Exception {
        return docDB(null);
    }

    public List<DetailBillDTO> listDetailBills() throws Exception
    {
        List<DetailBillDTO> detailbills = new ArrayList<DetailBillDTO>();
        connect = new MyConnectUnitDAO();     
        ResultSet result = this.connect.Select("chitiethoadon");
        while ( result.next() ) {
            DetailBillDTO detailbill = new DetailBillDTO();
            detailbill.setCodeBill(result.getString("MAHD"));
            detailbill.setCodeBook(result.getString("MASACH"));
            detailbill.setPrice( Integer.parseInt(result.getString("DonGia")) );
            detailbill.setNumber(Integer.parseInt(result.getString("SoLuong")));
            detailbill.setSale(Integer.parseInt(result.getString("GiamGia")));
            detailbill.setTotal(Float.parseFloat(result.getString("ThanhTien")));
            detailbills.add(detailbill);
        }
        connect.Close();
        return detailbills;
    }

    public Boolean Add(DetailBillDTO ac) throws Exception {
        connect = new MyConnectUnitDAO();
        HashMap<String, Object> insertValues = new HashMap<>();
        insertValues.put("MAHD", ac.getCodeBill());
        insertValues.put("MASACH", ac.getCodeBook());
        insertValues.put("DonGia", ac.getPrice());
        insertValues.put("SoLuong", ac.getNumber());
        insertValues.put("GiamGia", ac.getSale());
        insertValues.put("ThanhTien", ac.getTotal());  
        Boolean check = connect.Insert("chitiethoadon", insertValues);
        connect.Close();
        return check;
    }

    public Boolean Delete(DetailBillDTO ac) throws Exception {
        connect = new MyConnectUnitDAO();
        String condition = " MAHD = '"+ac.getCodeBill()+"'";   
        Boolean check = connect.Delete("chitiethoadon", condition);  
        connect.Close();
        return check;
    }

    public Boolean Fix(DetailBillDTO ac) throws Exception {
        connect = new MyConnectUnitDAO();
        HashMap<String, Object> insertValues = new HashMap<>();
        insertValues.put("MAHD", ac.getCodeBill());
        insertValues.put("MASACH", ac.getCodeBook());
        insertValues.put("DonGia", ac.getPrice());
        insertValues.put("SoLuong", ac.getNumber());
        insertValues.put("GiamGia", ac.getSale());
        insertValues.put("ThanhTien", ac.getTotal());        
        String condition = " MAHD = '"+ac.getCodeBill()+"'";       
        Boolean check = connect.Update("chitiethoadon", insertValues, condition);      
        connect.Close();
        return check;
    }
}
