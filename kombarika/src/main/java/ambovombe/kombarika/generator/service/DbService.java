/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ambovombe.kombarika.generator.service;

import ambovombe.kombarika.database.DbConnection;
import ambovombe.kombarika.generator.utils.ObjectUtility;
import java.util.HashMap;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 *
 * @author Mamisoa
 */
public class DbService {
    
    public static String getColumnType(String className){
        String[] splited = className.split("\\.");
        return splited[splited.length - 1];
    }
    
    public static String formatString(String column){
        String[] splited = column.split("_");
        if(splited.length <2)
            return column;
        String res = splited[0];
        for(int i = 1; i < splited.length; i++){
            res += ObjectUtility.capitalize(splited[i]);
        }
        return res;
    }

    public static List<String> getAllTables(Connection con) throws Exception{
        List<String> lst = new ArrayList<>();
        DatabaseMetaData meta = (DatabaseMetaData) con.getMetaData();
        ResultSet rs = meta.getTables(null, null,  null, new String[] {
         "TABLE"
      });
        while (rs.next()) {
          lst.add(rs.getString(3));
        }
        return lst;
    }

    public static String[] getAllTablesArrays(Connection con) throws Exception{
        List<String> lst = getAllTables(con);
        String[] array= new String[lst.size()];
        for(int i = 0; i < lst.size(); i++){
            array[i] = lst.get(i);
        }
        return array;
    }
    
    public static List<String> getAllTables(DbConnection con) throws Exception{
        List<String> lst = new ArrayList<>();
        DatabaseMetaData meta = (DatabaseMetaData) con.getConnection().getMetaData();
        ResultSet rs = meta.getTables(null, null,  null, new String[] {
         "TABLE"
      });
        while (rs.next()) {
          lst.add(rs.getString(3));
        }
        return lst;
    }

    public static String[] getAllTablesArrays(DbConnection con) throws Exception{
        List<String> lst = getAllTables(con);
        String[] array= new String[lst.size()];
        for(int i = 0; i < lst.size(); i++){
            array[i] = lst.get(i);
        }
        return array;
    }
    
    public static HashMap<String, String> getColumnNameAndType(Connection con, String tableName) throws Exception{
        HashMap<String, String> map = new HashMap<>();
        
        String query = "SELECT * FROM "+tableName;
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        ResultSetMetaData rsmd = rs.getMetaData();
        int count = rsmd.getColumnCount();
        for(int i = 1; i <= count; i++){
            String key = rsmd.getColumnName(i);
            String value = getColumnType(rsmd.getColumnClassName(i));
            map.put(key, value);
        }
        return map;
    }
    
    public static HashMap<String, String> getDetailsColumn(Connection con, String tableName) throws Exception{
        HashMap<String, String> map = new HashMap<>();
        
        String query = "SELECT * FROM "+tableName;
        
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        ResultSetMetaData rsmd = rs.getMetaData();
        int count = rsmd.getColumnCount();
        for(int i = 1; i <= count; i++){
            String key = rsmd.getColumnClassName(i);
            String column = getColumnType(rsmd.getColumnName(i));
            map.put(column, key);
        }
        return map;
    }
    
    //AZA FAFANA 
//    public static  void getPrimaryKey(Connection con, String tableName) throws Exception{
////        HashMap<String, String> map = new HashMap<>();
//        
//        String query = "SELECT * FROM "+tableName;
//        
//        DatabaseMetaData meta = con.getMetaData();
//        ResultSet tablesRs = meta.getTables(null, null, tableName, new String[]{"TABLE"});
//        ResultSet primaryKeys = meta.getPrimaryKeys(null, null, tableName);
//        
//        ResultSetMetaData rsmd = primaryKeys.getMetaData();
//        int count  = rsmd.getColumnCount();
//        
//        System.out.println("--------- PRIMARY KEY ----------------");
//        while(primaryKeys.next()){
////            for(int i = 1; i <= count; i++){
//                System.out.println(" column NAME = " + rsmd.getColumnName(4) + " column VALUES = "+primaryKeys.getObject("column_name") + " column TYPE = " + rsmd.getColumnClassName(4));
////            }
//        }
//    }


    public static List<String> getPrimaryKey(DbConnection dbConnection, String tableName) throws Exception{
        String query = dbConnection.getListConnection().get(dbConnection.getInUseConnection()).getDatabaseType().getPrimaryKeyQuery();
        ArrayList<String> listPrimaryKeys = new ArrayList<>();
        query = query.replace("?", tableName);
        PreparedStatement stmt = dbConnection.getConnection().prepareCall(query);
        ResultSet rs = stmt.executeQuery();
        while (rs.next())
            listPrimaryKeys.add(rs.getString(1));
        return listPrimaryKeys;
    }

    public static HashMap<String, String> getForeignKeys(DbConnection dbConnection, String tableName) throws Exception{
        String query = dbConnection.getListConnection().get(dbConnection.getInUseConnection()).getDatabaseType().getForeignKeyQuery();
        HashMap<String, String> listForeignKeysKeys = new HashMap<>();
        query = query.replace("?", tableName);
        PreparedStatement stmt = dbConnection.getConnection().prepareCall(query);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()){
            listForeignKeysKeys.put(rs.getString(1), rs.getString(2));
        }
        return listForeignKeysKeys;
    }

    public static String getType(String str){
        return str.split("\\.")[str.split("\\.").length -1];
    }

    public static  List<String> getPrimaryKeyType(DbConnection dbConnection, String tableName) throws Exception{
        ArrayList<String> listPrimaryKeysType = new ArrayList<>();
        HashMap<String ,String> map = getDetailsColumn(dbConnection.getConnection(), tableName);
        List<String> primaryKeys = getPrimaryKey(dbConnection, tableName);
        for (Map.Entry<String, String> set : map.entrySet()) {
            if (primaryKeys.contains(set.getKey())) {
                listPrimaryKeysType.add(getType(set.getValue()));
            }
        }
        return listPrimaryKeysType;
    }

    
//     public static void getForeignKeys(Connection con, String tableName) throws Exception{
//         String query = "SELECT * FROM "+tableName;
//         DatabaseMetaData meta = con.getMetaData();
//         ResultSet tablesRs = meta.getTables(null, null, tableName, new String[]{"TABLE"});
//         ResultSet primaryKeys = meta.getPrimaryKeys(null, null, tableName);
//         ResultSet foreignKeys = meta.getExportedKeys(null, null, tableName);
//         ResultSetMetaData rsmd2 = foreignKeys.getMetaData();
        
//         int count2  = rsmd2.getColumnCount();
//         System.out.println("");
//         System.out.println("--------- FOREIGN KEY ----------------");     
// //        System.out.println(foreignKeys.next());
//         while(foreignKeys.next()){
//             for(int i = 1; i <= count2; i++){
//                 System.out.println(" column NAME = " + rsmd2.getColumnName(i) + " column VALUES = " + foreignKeys.getString(i) + " column TYPE = "+rsmd2.getColumnClassName(i));
//             }
//             System.out.println("-------------------------------------");
//         }
// //        return map;   
//     }

}
