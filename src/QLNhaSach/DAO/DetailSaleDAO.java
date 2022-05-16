package QLNhaSach.DAO;

import QLNhaSach.DTO.AccountDTO;
import QLNhaSach.DTO.DetailSaleDTO;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DetailSaleDAO {
    MyConnectUnitDAO connect;
    public ArrayList<DetailSaleDTO> docDB(String condition, String orderBy) throws Exception {
        connect = new MyConnectUnitDAO();   
        ResultSet result = this.connect.Select("khuyenmai", condition, orderBy);
        ArrayList<DetailSaleDTO> khuyenmais = new ArrayList<>();
        while ( result.next() ) {
            DetailSaleDTO khuyenmai = new DetailSaleDTO();
            khuyenmai.setCodeSale(result.getInt("MAKM"));
            khuyenmai.setCodeBook(result.getInt("MALOAI"));
            khuyenmai.setPercentSale(result.getInt("PhanTramGiam"));  
            khuyenmais.add(khuyenmai);
        }
        connect.Close();
        return khuyenmais;
    }
    
    public ArrayList<DetailSaleDTO> docDB(String condition) throws Exception {
        return docDB(condition, null);
    }
    
    public ArrayList<DetailSaleDTO> docDB() throws Exception {
        return docDB(null);
    }
    public List<DetailSaleDTO> listSales() throws Exception
    {
        List<DetailSaleDTO> sales = new ArrayList<DetailSaleDTO>();
        connect = new MyConnectUnitDAO();
        
        ResultSet result = this.connect.Select("khuyenmai");
        while ( result.next() ) {
            DetailSaleDTO sale = new DetailSaleDTO();
            sale.setCodeSale( Integer.parseInt(result.getString("MAKM")) );
            sale.setCodeBook(Integer.parseInt(result.getString("MALOAI")));
            sale.setPercentSale(Integer.parseInt(result.getString("PhanTramGiam")));
            sales.add(sale);
        }
        connect.Close();
        return sales;
    }

    public Boolean them(DetailSaleDTO hd) throws Exception {
        connect = new MyConnectUnitDAO();
        HashMap<String, Object> insertValues = new HashMap<>();
        insertValues.put("MAKM", hd.getCodeSale());
        insertValues.put("MALOAI", hd.getCodeBook());
        insertValues.put("PhanTramGiam", hd.getPercentSale());
        Boolean check = connect.Insert("khuyenmai", insertValues);
        connect.Close();
        return check;
    }

    public Boolean xoa(DetailSaleDTO hd) throws Exception {
        connect = new MyConnectUnitDAO();
        String condition = " MAKM = '"+hd.getCodeSale()+"'";
        
        Boolean check = connect.Delete("khuyenmai", condition);
        
        connect.Close();
        return check;
    }

    public Boolean sua(DetailSaleDTO hd) throws Exception {
        connect = new MyConnectUnitDAO();
        HashMap<String, Object> insertValues = new HashMap<>();
        insertValues.put("MAKM", hd.getCodeSale());
        insertValues.put("MALOAI", hd.getCodeBook());
        insertValues.put("PhanTramGiam", hd.getPercentSale());     
        String condition = " MAKM = '"+hd.getCodeSale() +"'";    
        Boolean check = connect.Update("khuyenmai", insertValues, condition);
        connect.Close();
        return check;
    }
}
