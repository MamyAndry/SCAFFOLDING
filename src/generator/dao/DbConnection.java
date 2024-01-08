/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package generator.dao;

import generator.parser.JsonUtility;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;

/**
 *
 * @author Mamisoa
 */
public class DbConnection {
    private HashMap<String, DbProperties> listConnection;

    //SETTERS & GETTERS
    public HashMap<String, DbProperties> getListConnection() {
        return listConnection;
    }

    public void setListConnection(HashMap<String, DbProperties> listConnection) {
        this.listConnection = listConnection;
    }
    
    //METHODS
    public void readJson()throws Exception{
        String separator = File.separator;
        String confFile = System.getProperty("user.dir") + separator +"database.json";
//        System.out.println(confFile);
        DbConnection temp = JsonUtility.parseJson(confFile, this.getClass());
        this.setListConnection(temp.getListConnection());
    }
    
    public Connection connect()throws Exception{
        readJson();
        DbProperties prop = this.getListConnection().get("DefaultConnection");
        Class.forName(prop.getDatabaseType().getDriver());
        Connection con = DriverManager.getConnection(prop.getDatasource(),prop.getUsername(),prop.getPassword());
        return con;
    }
    
    public Connection connect(String connection)throws Exception{
        readJson();
//        readJson(path);
        DbProperties prop = this.getListConnection().get(connection); 
        Class.forName(prop.getDatabaseType().getDriver());
        Connection con = DriverManager.getConnection(prop.getDatasource(),prop.getUsername(),prop.getPassword());
        return con;
    }
}
