package QLNhaSach.DAO;

import QLNhaSach.DTO.AccountDTO;
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

public class AccountDAO {
    MyConnectUnitDAO connect;
    Controller ctrler = new Controller();

    public ArrayList<AccountDTO> docDB(String condition, String orderBy) throws Exception {
        connect = new MyConnectUnitDAO();       
        ResultSet result = this.connect.Select("tbltaikhoan", condition, orderBy);        ArrayList<AccountDTO> accounts = new ArrayList<AccountDTO>();
        while ( result.next() ) {
            AccountDTO account = new AccountDTO();
            account.setUserName(result.getString("tendangnhap"));
            account.setPassword(result.getString("matkhau"));
            account.setLevel( Integer.parseInt(result.getString("capbac")) );
            accounts.add(account);
        }
        connect.Close();
        return accounts;
    }
    
    public ArrayList<AccountDTO> docDB(String condition) throws Exception {
        return docDB(condition, null);
    }
    
    public ArrayList<AccountDTO> docDB() throws Exception {
        return docDB(null);
    }

    public List<AccountDTO> listAccounts() throws Exception
    {
        List<AccountDTO> accounts = new ArrayList<AccountDTO>();
        connect = new MyConnectUnitDAO();
        
        ResultSet result = this.connect.Select("tbltaikhoan");
        while ( result.next() ) {
            AccountDTO account = new AccountDTO();
            account.setID( Integer.parseInt(result.getString("id")) );
            account.setUserName(result.getString("tendangnhap"));
            account.setPassword(result.getString("matkhau"));
            account.setLevel( Integer.parseInt(result.getString("capbac")) );
            accounts.add(account);
        }
        connect.Close();
        return accounts;
    }

    public Boolean Add(AccountDTO ac) throws Exception {
        connect = new MyConnectUnitDAO();
        HashMap<String, Object> insertValues = new HashMap<>();
        insertValues.put("id", ac.getID());
        insertValues.put("tendangnhap", ac.getUserName());
        insertValues.put("matkhau", ac.getPassword());
        insertValues.put("capbac", ac.getLevel());
        
        Boolean check = connect.Insert("tbltaikhoan", insertValues);
        
        connect.Close();
        return check;
    }

    public Boolean Delete(AccountDTO ac) throws Exception {
        connect = new MyConnectUnitDAO();
        String condition = " id = '"+ac.getID()+"'";
        
        Boolean check = connect.Delete("tbltaikhoan", condition);
        
        connect.Close();
        return check;
    }
    
    public Boolean Fix(AccountDTO ac) throws Exception {
        connect = new MyConnectUnitDAO();
        HashMap<String, Object> insertValues = new HashMap<>();
        insertValues.put("tendangnhap", ac.getUserName());
        insertValues.put("matkhau", ac.getPassword());
        insertValues.put("capbac", ac.getLevel());
        
        String condition = " id = '"+ac.getID()+"'";
        
        Boolean check = connect.Update("tbltaikhoan", insertValues, condition);
        
        connect.Close();
        return check;
    }
}
