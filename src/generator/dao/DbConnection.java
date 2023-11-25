/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package generator.dao;

import generator.parser.FileParser;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
/**
 *
 * @author Mamisoa
 */
public class DbConnection {
    String datasource;
    String driver;
    String username;
    String password;

    //SETTERS and GETTERS

    public String getDatasource() {
        return datasource;
    }

    public void setDatasource(String datasource) {
        this.datasource = datasource;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
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

    //CONSTRUCTOR
    public DbConnection(){}
    
    //FUNCTION 
    public void readFile()throws Exception{
        String separator = "\\";
        if(System.getProperty("os.name").equals("Linux"))
            separator = "/";
        String confFile = System.getProperty("user.dir") + separator + "database.conf";
        System.out.println("Hello from DAO : " + confFile);
        List<String[]> lst = FileParser.readFile(confFile);
        for(String[] elt : lst ){
            switch (elt[0]) {
                case "datasource":
                    setDatasource(elt[1]);
                    break;
                case "driver":
                    setDriver(elt[1]);
                    break;
                case "username":
                    setUsername(elt[1]);
                    break;
                case "password":
                    setPassword(elt[1]);
                    break;
                default:
                    break;
            }
        }
    }
    
    public Connection connect()throws Exception{
        readFile(); 
        Class.forName(getDriver());
        Connection con = DriverManager.getConnection(getDatasource(),getUsername(),getPassword());
        return con;
    }
}
