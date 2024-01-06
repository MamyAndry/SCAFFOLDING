/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package generator.dao;

import generator.parser.FileParser;
import generator.parser.JsonParser;
import java.util.List;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import properties.DatabaseType;
/**
 *
 * @author Mamisoa
 */
public class DbConnection {
    String database;
    String datasource;
    String username;
    String password;
    DatabaseType databaseType;

    //SETTERS and GETTERS

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getDatasource() {
        return datasource;
    }

    public void setDatasource(String datasource) {
        this.datasource = datasource;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public DatabaseType getDatabaseType() {
        return databaseType;
    }

    public void setDatabaseType(DatabaseType databaseType) {
        this.databaseType = databaseType;
    }

    //CONSTRUCTOR
    public DbConnection(){}
    
    //FUNCTION 
//    public HashMap<String, Method> getMapping(){
//        HashMap<String, Method> mapping = new HashMap<>();
//        return mapping;
//    } 
    
    public void readFile()throws Exception{
        String separator = File.separator;
        String confFile = System.getProperty("user.dir") + separator +"database.conf";
        List<String[]> lst = FileParser.readFile(confFile);
        for(String[] elt : lst ){
            if(elt[0].equals("database")) setDatabase(elt[1]);
            else if (elt[0].equals("datasource")) setDatasource(elt[1]);
            else if (elt[0].equals("username")) setUsername(elt[1]);
            else if (elt[0].equals("password")) setPassword(elt[1]);
        }
        
        if(getDatabase().equals("POSTGRESQL")) setDatabaseType(DatabaseType.POSTGRESQL);
        else if(getDatabase().equals("ORACLE")) setDatabaseType(DatabaseType.ORACLE);
        else if(getDatabase().equals("MYSLQ")) setDatabaseType(DatabaseType.MYSQL);
        else if(getDatabase().equals( "SQLSERVER")) setDatabaseType(DatabaseType.SQLSERVER);
    }
    
    public void readJson()throws Exception{
        String separator = File.separator;
        String confFile = System.getProperty("user.dir") + separator +"database.json";
        System.out.println(confFile);
        DbConnection temp = JsonParser.parseJson(confFile, DbConnection.class);
        setDatabaseType(temp.getDatabaseType());
        setDatasource(temp.getDatasource());
        setUsername(temp.getUsername());
        setPassword(temp.getPassword());
    }
    
    public Connection connect()throws Exception{
//        readFile(); 
        System.out.println("HIHIHIHIHI");
        readJson();
        Class.forName(getDatabaseType().getDriver());
        Connection con = DriverManager.getConnection(getDatasource(),getUsername(),getPassword());
        return con;
    }
    
    public void readFile(String path)throws Exception{
        List<String[]> lst = FileParser.readFile(path);
        for(String[] elt : lst ){
            if(elt[0].equals("database")) setDatabase(elt[1]);
            else if (elt[0].equals("datasource")) setDatasource(elt[1]);
            else if (elt[0].equals("username")) setUsername(elt[1]);
            else if (elt[0].equals("password")) setPassword(elt[1]);
        }
        
        if(getDatabase().equals("POSTGRESQL")) setDatabaseType(DatabaseType.POSTGRESQL);
        else if(getDatabase().equals("ORACLE")) setDatabaseType(DatabaseType.ORACLE);
        else if(getDatabase().equals("MYSLQ")) setDatabaseType(DatabaseType.MYSQL);
        else if(getDatabase().equals( "SQLSERVER")) setDatabaseType(DatabaseType.SQLSERVER);
    }
    
    public Connection connect(String path)throws Exception{
        readFile(path);
//        readJson(path); 
        Class.forName(getDatabaseType().getDriver());
        Connection con = DriverManager.getConnection(getDatasource(),getUsername(),getPassword());
        return con;
    }
}
