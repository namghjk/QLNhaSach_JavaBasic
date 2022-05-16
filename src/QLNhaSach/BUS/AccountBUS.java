/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QLNhaSach.BUS;

import QLNhaSach.DAO.AccountDAO;
import QLNhaSach.DTO.AccountDTO;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class AccountBUS {
    private ArrayList<AccountDTO> list_AC;
    private AccountDAO acDAO;
    
    public AccountBUS() throws Exception {
        list_AC = new ArrayList<>();
         acDAO= new AccountDAO();
        list_AC = acDAO.docDB();
    }

    public Boolean Add(AccountDTO ac) throws Exception{
        if ( acDAO.Add(ac) ) {
            list_AC.add(ac);
        }
        return false;
    }

    public Boolean Delete(AccountDTO ac) throws Exception {
        if ( acDAO.Delete(ac) ) {
            
            // duyệt từng phẩn tử
            for ( AccountDTO account : list_AC ) {
                if (account.getUserName().equals(ac.getUserName())){
                    list_AC.remove(account);
                break;
                }
            }
        }
        
        return false;
    }

    public Boolean Fix(AccountDTO ac) throws Exception {
        if ( acDAO.Fix(ac) ) {
            for ( AccountDTO account : list_AC ) {
                if (account.getUserName().equals(account.getUserName())){
                break;
                }
            }
        }
        
        return false;
    }

    public Boolean CheckLogin(AccountDTO ac) {
        for ( AccountDTO account : list_AC ) {
            if (account.getUserName().equalsIgnoreCase(ac.getUserName())
                    && account.getPassword().equalsIgnoreCase(account.getPassword()) ) {
                return true;
            }
        }
        return false;
    }
    
    public int getLevel_Name(String Name) {
        for ( AccountDTO account : list_AC ) {
            if ( account.getUserName().equalsIgnoreCase(Name) )
                return account.getLevel();
        } 
        return -1;
    }
}
