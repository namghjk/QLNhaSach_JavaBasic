package QLNhaSach.DAO;

import QLNhaSach.DTO.DetailSaleDTO;
import QLNhaSach.DTO.SaleDTO;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class    SaleDAO {
    MyConnectUnitDAO connect;
    
    public ArrayList<SaleDTO> docDB(String condition, String orderBy) throws Exception {
        connect = new MyConnectUnitDAO();        
        ResultSet result = this.connect.Select("chitietkhuyenmai", condition, orderBy);
        ArrayList<SaleDTO> khuyenmais = new ArrayList<>();
        while ( result.next() ) {
            SaleDTO khuyenmai = new SaleDTO();
            khuyenmai.setCodeSale(result.getString("MAKM"));
            khuyenmai.setNameSale(result.getString("TenKhuyenMai"));
            khuyenmai.setStartDay(result.getString("NgayBD"));
            khuyenmai.setEndDay(result.getString("NgayKT"));
            khuyenmais.add(khuyenmai);
        }
        connect.Close();
        return khuyenmais;
    }
    
    public ArrayList<SaleDTO> docDB(String condition) throws Exception {
        return docDB(condition, null);
    }
    
    public ArrayList<SaleDTO> docDB() throws Exception {
        return docDB(null);
    }
    
    public List<SaleDTO> listDetailSales() throws Exception
    {
        List<SaleDTO> dtsales = new ArrayList<SaleDTO>();
        connect = new MyConnectUnitDAO();
        
        ResultSet result = this.connect.Select("chitietkhuyenmai");
        while ( result.next() ) {
            SaleDTO dtsale = new SaleDTO();
            dtsale.setCodeSale(result.getString("MAKM"));
            dtsale.setNameSale(result.getString("TenKhuyenMai"));
            dtsale.setStartDay(result.getString("NgayBD"));
            dtsale.setEndDay(result.getString("NgayKT"));
            dtsales.add(dtsale);
        }
        connect.Close();
        return dtsales;
    }
    
    public Boolean them(SaleDTO km) throws Exception {
        connect = new MyConnectUnitDAO();
        HashMap<String, Object> insertValues = new HashMap<>();
        insertValues.put("MAKM", km.getCodeSale());
        insertValues.put("TenKhuyenMai", km.getNameSale());
        insertValues.put("NgayBD", km.getStartDay());
        insertValues.put("NgayKT", km.getEndDay());
        Boolean check = connect.Insert("chitietkhuyenmai", insertValues);
        
        connect.Close();
        return check;
    }

    public Boolean xoa(SaleDTO km) throws Exception {
        connect = new MyConnectUnitDAO();
        String condition = " MAKM = '"+km.getCodeSale()+"'";        
        Boolean check = connect.Delete("chitietkhuyenmai", condition);       
        connect.Close();
        return check;
    }

    public Boolean sua(SaleDTO km) throws Exception {
        connect = new MyConnectUnitDAO();
        HashMap<String, Object> insertValues = new HashMap<>();
        insertValues.put("MAKM", km.getCodeSale());
        insertValues.put("TenKhuyenMai", km.getNameSale());
        insertValues.put("NgayBD", km.getStartDay());
        insertValues.put("NgayKT", km.getEndDay());       
        String condition = " MAKM = '"+km.getCodeSale()+"'";       
        Boolean check = connect.Update("chitietkhuyenmai", insertValues, condition);      
        connect.Close();
        return check;
    }
}
