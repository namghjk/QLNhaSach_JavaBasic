package QLNhaSach.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.TimeZone;

public class MySQLConnectionDAO {
    String Host = "";
    String Username = "";
    String Password = "";
    String Database = "";
    
    Connection connect = null;
    Statement statement = null;
    ResultSet resultSet = null;

    public MySQLConnectionDAO(String Host, String Username, String Password, String Database) {
        this.Host = Host;
        this.Username = Username;
        this.Password = Password;
        this.Database = Database;
    }
    
    protected void driveTest () throws Exception {
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//        }
//        catch (ClassNotFoundException e) {
//            throw new Exception("My SQl not found driveTest");
//        }
    }
    
    protected Connection getConnect() throws Exception {
        if (this.connect == null) {
            driveTest();            
            String url = "jdbc:mysql://" + this.Host + ":3306/" + this.Database
                    + "?serverTimezone=" + TimeZone.getDefault().getID();
            try{
                this.connect = DriverManager.getConnection(url, this.Username, this.Password);
            }        
            catch (SQLException e) {
                throw new Exception("không thể kết nối tới Database" + url +e.getMessage());
            }
        }
        
        return this.connect;
    }

    protected Statement getStatement() throws Exception {
        if (this.statement == null) {
            this.statement = getConnect().createStatement();
        }
        else {
            this.statement.isClosed();
        }
        return this.statement;
    }

    public ResultSet excuteQuery(String Query) throws Exception{
        try {
            this.resultSet = getStatement().executeQuery(Query);
            
        } catch (Exception e) {
            throw new Exception("Error excuteQuery " + e.getMessage());
        }
        
        return this.resultSet;
    }
    
    public int executeUpdate(String Query) throws Exception {
        int res = Integer.MIN_VALUE;
        
        try {
            res = getStatement().executeUpdate(Query);
        } catch (Exception e) {
            throw new Exception("Error " + e.getMessage());
        }
        
        return res;
    }

    public void Close() throws Exception {
        if (this.resultSet != null && this.resultSet.isClosed()) {
            this.resultSet.close();
            this.resultSet = null;
        }
        if (this.statement != null && this.statement.isClosed()) {
            this.statement.close();
            this.statement = null;
        }
        if (this.connect != null && this.connect.isClosed()) {
            this.connect.close();
            this.connect = null;
        }
    }
}
