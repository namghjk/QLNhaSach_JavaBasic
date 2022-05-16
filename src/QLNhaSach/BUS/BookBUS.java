package QLNhaSach.BUS;

import QLNhaSach.DAO.BookDAO;
import QLNhaSach.DTO.BookDTO;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class BookBUS {
    private ArrayList<BookDTO> list_Book;
    private BookDAO bookDAO;
    
    public BookBUS() throws Exception {
        list_Book = new ArrayList<>();
         bookDAO= new BookDAO();
        list_Book = bookDAO.docDB();
    }

    public Boolean Add(BookDTO books) throws Exception{
        if ( bookDAO.them(books) ) {
            list_Book.add(books);
        }
        return false;
    }

    public Boolean Delete(BookDTO books) throws Exception {
        if ( bookDAO.xoa(books) ) {
            for ( BookDTO book : list_Book ) {
                if (book.getCodeBook().equals(book.getCodeBook())){
                    list_Book.remove(books);
                break;
                }
            }
        }
        
        return false;
    }

    public Boolean Fix(BookDTO books) throws Exception {
        if ( bookDAO.sua(books) ) {
            for ( BookDTO book : list_Book ) {
                if (book.getCodeBook().equals(book.getCodeBook())){
                break;
                }
            }
        }
        
        return false;
    }
}
