package QLNhaSach.DAO;

import  QLNhaSach.DTO.KindOfBookDTO;
import java.sql.ResultSet;
import java.util.ArrayList;


public class KindOfBookDAO {
    MyConnectUnitDAO connect;
    public ArrayList<KindOfBookDTO> docDB(String condition, String orderBy) throws Exception {
        connect = new MyConnectUnitDAO();      
        ResultSet result = this.connect.Select("tblloai", condition, orderBy);
        ArrayList<KindOfBookDTO> loais = new ArrayList<>();
        while ( result.next() ) {
            KindOfBookDTO loai = new KindOfBookDTO();
            loai.setCodeKindOfBook(result.getString("MaLoai"));
            loai.setNameKind(result.getString("TenLoai"));
            loais.add(loai);
        }
        connect.Close();
        return loais;
    }
    
    public ArrayList<KindOfBookDTO> docDB(String condition) throws Exception {
        return docDB(condition, null);
    }
    
    public ArrayList<KindOfBookDTO> docDB() throws Exception {
        return docDB(null);
    }
    
}

