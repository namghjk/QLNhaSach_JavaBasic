package QLNhaSach.DAO;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;

public class MyConnectUnitDAO {
    public MySQLConnectionDAO connect;

    public MyConnectUnitDAO() {
        connect = new MySQLConnectionDAO("localhost", "root", "", "qlnhasach");
    }

    public MyConnectUnitDAO(String Host, String Username, String Password, String Database) {
        connect = new MySQLConnectionDAO(Host, Username, Password, Database);
    }

    public ResultSet Select(String TableName, String Condition, String OrderBy) throws Exception {
        StringBuilder query = new StringBuilder("SELECT * FROM " + TableName);
        this.AddCondition(query, Condition);
        this.AddOrderBy(query, OrderBy);
        query.append(";");
        return this.connect.excuteQuery(query.toString());
    }

    public ResultSet Select(String TableName, String Condition) throws Exception {
        return this.Select(TableName, Condition, null);
    }

    public ResultSet Select(String TableName) throws Exception {
        return this.Select(TableName, null, null);
    }

    private void AddCondition(StringBuilder query, String Condition) {
        if (Condition != null) {
            query.append(" WHERE "+ Condition);
        }
    }

    private void AddOrderBy(StringBuilder query, String OrderBy) {
        if ( OrderBy != null) {
            query.append(" ORDER BY " + OrderBy);
        }
    }

    public boolean Insert(String TableName, HashMap<String, Object> ColumnValues) throws Exception {
        StringBuilder query = new StringBuilder("Insert Into " + TableName);
        StringBuilder valueInsert = new StringBuilder();        
        query.append("(");
        for (String key : ColumnValues.keySet()) {
            query.append(key + ",");
            valueInsert.append("'" + ColumnValues.get(key).toString() + "',");
        }
        query = query.delete(query.length() - 1, query.length());
        valueInsert = valueInsert.delete(valueInsert.length() - 1, valueInsert.length());
        query.append(") Values(" + valueInsert.toString() + ")");
        query.append(";");
        System.out.println(query);
        return this.connect.executeUpdate(query.toString()) > 0;
    }

    public boolean Update(String TableName, HashMap<String, Object> ColumnValues, String Condition) throws Exception {
        StringBuilder query = new StringBuilder("Update " + TableName + " Set ");
        for (String key : ColumnValues.keySet()) {
            query.append(key + " = '" + ColumnValues.get(key).toString() + "',");
        }
        query = query.delete(query.length() - 1, query.length());
        this.AddCondition(query, Condition);
        query.append(";");
        System.out.println(query);
        return this.connect.executeUpdate(query.toString()) >0;
    }

    public boolean Delete(String TableName, String Condition) throws Exception {
        StringBuilder query = new StringBuilder("Delete From " + TableName);
        this.AddCondition(query, Condition);
        query.append(";");
        System.out.println(query);
        return this.connect.executeUpdate(query.toString()) > 0;
    }
    
    public static int getColumnCount(ResultSet result) throws SQLException {
         return result.getMetaData().getColumnCount();
    }

    public static String[] getColumnName(ResultSet result) throws SQLException {
        ResultSetMetaData rsMetaData = (ResultSetMetaData) result.getMetaData();
        int ColumnCount = rsMetaData.getColumnCount();
        String[] list = new String[ColumnCount];
        for (int i=0 ; i < ColumnCount ; i++) {
            list[i] = rsMetaData.getColumnName(i);
        }
        return list;
    }

    public void Close() throws Exception {
        this.connect.Close();
    }

    public void close() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
