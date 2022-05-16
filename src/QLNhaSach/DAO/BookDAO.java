package QLNhaSach.DAO;

import QLNhaSach.DAO.MyConnectUnitDAO;
import QLNhaSach.DTO.AccountDTO;
import QLNhaSach.DTO.BookDTO;
import QLNhaSach.DTO.BookDTO;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BookDAO {
    MyConnectUnitDAO connect;

    public ArrayList<BookDTO> docDB(String condition, String orderBy) throws Exception {
        connect = new MyConnectUnitDAO();
        
        ResultSet result = this.connect.Select("sach", condition, orderBy);
        ArrayList<BookDTO> books = new ArrayList<>();
        while ( result.next() ) {
            BookDTO book = new BookDTO();
            book.setCodeBook(result.getString("MASACH"));
            book.setPublishingCompany(result.getString("NhaXuatBan"));
            book.setCodeKindOfBook(result.getString("MaLoai"));
            book.setBookName(result.getString("TenSach"));
            book.setAuthor(result.getString("TacGia"));
            book.setUnitPrice(result.getFloat("DonGia"));
            book.setNumber(result.getInt("SoLuong"));
            
            
            books.add(book);
        }
        connect.Close();
        return books;
    }
    
    public ArrayList<BookDTO> docDB(String condition) throws Exception {
        return docDB(condition, null);
    }
    
    public ArrayList<BookDTO> docDB() throws Exception {
        return docDB(null);
    }

    public List<BookDTO> listBooks() throws Exception
    {
        List<BookDTO> books = new ArrayList<BookDTO>();
        connect = new MyConnectUnitDAO();
        
        ResultSet result = this.connect.Select("sach");
        while ( result.next() ) {
            BookDTO book = new BookDTO();
            book.setCodeBook(result.getString("MASACH"));
            book.setPublishingCompany(result.getString("NhaXuatBan"));
            book.setCodeKindOfBook(result.getString("MaLoai"));
            book.setBookName(result.getString("TenSach"));
            book.setAuthor(result.getString("TacGia"));
            book.setUnitPrice( Integer.parseInt(result.getString("DonGia")) );
            book.setNumber( Integer.parseInt(result.getString("SoLuong")) );
            books.add(book);
        }
        connect.Close();
        return books;
    }
    public Boolean them(BookDTO book) throws Exception {
        connect = new MyConnectUnitDAO();
        HashMap<String, Object> insertValues = new HashMap<>();
        insertValues.put("MASACH", book.getCodeBook());
        insertValues.put("NhaXuatBan", book.getPublishingCompany());
        insertValues.put("MaLoai", book.getCodeKindOfBook());
        insertValues.put("TenSach", book.getBookName());
        insertValues.put("TacGia", book.getAuthor());
        insertValues.put("DonGia", book.getUnitPrice());
        insertValues.put("SoLuong", book.getNumber());
        
        Boolean check = connect.Insert("sach", insertValues);
        
        connect.Close();
        return check;
    }
    
    public Boolean xoa(BookDTO book) throws Exception {
        connect = new MyConnectUnitDAO();
        String condition = " MASACH = '"+book.getCodeBook()+"'";
        
        Boolean check = connect.Delete("sach", condition);
        
        connect.Close();
        return check;
    }
    
    public Boolean sua(BookDTO book) throws Exception {
        connect = new MyConnectUnitDAO();
        HashMap<String, Object> insertValues = new HashMap<>();
        insertValues.put("MASACH", book.getCodeBook());
        insertValues.put("NhaXuatBan", book.getPublishingCompany());
        insertValues.put("MaLoai", book.getCodeKindOfBook());
        insertValues.put("TenSach", book.getBookName());
        insertValues.put("TacGia", book.getAuthor());
        insertValues.put("DonGia", book.getUnitPrice());
        insertValues.put("SoLuong", book.getNumber());
        
        String condition = " MASACH = '"+book.getCodeBook()+"'";
        
        Boolean check = connect.Update("sach", insertValues, condition);
        
        connect.Close();
        return check;
    }
}
