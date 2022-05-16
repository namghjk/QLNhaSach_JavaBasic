package QLNhaSach.DAO;

import QLNhaSach.DTO.DetailImportCouponDTO;
import QLNhaSach.DTO.ImportCouponDTO;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DetailImportCouponDAO {
    MyConnectUnitDAO connect;
    public ArrayList<DetailImportCouponDTO> docDB(String condition, String orderBy) throws Exception {
        connect = new MyConnectUnitDAO();     
        ResultSet result = this.connect.Select("chitietphieunhap", condition, orderBy);
        ArrayList<DetailImportCouponDTO> hoadons = new ArrayList<>();
        while ( result.next() ) {
            DetailImportCouponDTO hoadon = new DetailImportCouponDTO();
            hoadon.setCodeBook(result.getString("MASACH"));
            hoadon.setCodeCoupon(result.getString("MAPHIEU"));
            hoadon.setNumber(result.getInt("SoLuong"));
            hoadons.add(hoadon);
        }
        connect.Close();
        return hoadons;
    }
    
    public ArrayList<DetailImportCouponDTO> docDB(String condition) throws Exception {
        return docDB(condition, null);
    }
    
    public ArrayList<DetailImportCouponDTO> docDB() throws Exception {
        return docDB(null);
    }
    
    public List<DetailImportCouponDTO> listDetailImports() throws Exception
    {
        List<DetailImportCouponDTO> dtimports = new ArrayList<DetailImportCouponDTO>();
        connect = new MyConnectUnitDAO();
        
        ResultSet result = this.connect.Select("chitietphieunhap");
        while ( result.next() ) 
        {
            DetailImportCouponDTO dtimport = new DetailImportCouponDTO();
            dtimport.setCodeCoupon(result.getString("MAPHIEU"));
            dtimport.setCodeBook(result.getString("MASACH"));
            dtimport.setNumber(result.getInt("SoLuong"));
            dtimports.add(dtimport);
        }
        connect.Close();
        return dtimports;
    }

    public Boolean them(DetailImportCouponDTO pn) throws Exception {
        connect = new MyConnectUnitDAO();
        HashMap<String, Object> insertValues = new HashMap<>();
        insertValues.put("MAPHIEU", pn.getCodeCoupon());
        insertValues.put("MASACH", pn.getCodeBook());
        insertValues.put("SoLuong", pn.getNumber());
        
        Boolean check = connect.Insert("chitietphieunhap", insertValues);
        
        connect.Close();
        return check;
    }

    public Boolean xoa(DetailImportCouponDTO pn) throws Exception {
        connect = new MyConnectUnitDAO();
        String condition = " mapn = '"+pn.getCodeCoupon()+"' && masach = '"+pn.getCodeBook()+"'";
        
        Boolean check = connect.Delete("chitietphieunhap", condition);
        
        connect.Close();
        return check;
    }

    public Boolean sua(DetailImportCouponDTO pn) throws Exception {
        connect = new MyConnectUnitDAO();
        HashMap<String, Object> insertValues = new HashMap<>();
        insertValues.put("MAPHIEU", pn.getCodeCoupon());
        insertValues.put("MASACH", pn.getCodeBook());
        insertValues.put("SoLuong", pn.getNumber());
        String condition = " mapn = '"+pn.getCodeCoupon()+"' && masach = '"+pn.getCodeBook()+"'";
        Boolean check = connect.Update("chitietphieunhap", insertValues, condition);     
        connect.Close();
        return check;
    }
}
