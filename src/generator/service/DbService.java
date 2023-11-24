/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package generator.service;

import generator.mapping.ClassMapping;
import java.util.HashMap;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.DatabaseMetaData;

import java.util.ArrayList;
import java.util.List;
import utils.ObjectUtility;
/**
 *
 * @author Mamisoa
 */
public class DbService {
    
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
    
    
    public static HashMap<String, Class> getColumnNameAndType(Connection con, String tableName) throws Exception{
        HashMap<String, Class> map = new HashMap<>();
        HashMap<Integer, Class> mapping = ClassMapping.getClassMapTable();
        
        String query = "SELECT * FROM "+tableName;
        
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        ResultSetMetaData rsmd = rs.getMetaData();
        int count = rsmd.getColumnCount();
        for(int i = 1; i <= count; i++){
            Integer key = rsmd.getColumnType(i);
            String column = rsmd.getColumnName(i);
            map.put(column, mapping.get(key));
        }
        return map;
    }   

}
