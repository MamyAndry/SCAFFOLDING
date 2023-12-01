/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package generator;

import generator.service.DbService;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mamisoa
 */
public class CLIReader {
    String path;
    String packageName;
    String lang;
    String table;

    //GETTERS AND SETTERS
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }
    
    //METHODS
    public void getData(String[] str) throws Exception{
        if(!str[0].equals("generate"))
            throw new Exception("Wrong syntax");
        else if(str[0].equals("exit"))
            throw new Exception("Farewell");
        for(int i = 0; i < str.length; i++){
            if(str[i].equals("-p")){
                this.setPackageName(str[i+1]);
            }
            if(str[i].equals("-t")){
                this.setTable(str[i+1]);
            }
            if(str[i].equals("-path")){
                this.setPath(str[i+1]);
            }
            if(str[i].equals("-l")){
                this.setLang(str[i+1].toLowerCase());
            }
        }
    }
    
    public static void showAllTables(Connection con) throws Exception{
        List<String> lst = DbService.getAllTables(con);
        for(String item : lst)
            System.out.println(item);
    }
    
    public String[] getTables(Connection con) throws Exception{
        if(this.getTable().equals("all"))
            return DbService.getAllTablesArrays(con);
        return this.getTable().split(",");
    }
    
    public void read(Connection con, String str) throws Exception{
        if(str.equals("list all tables") || str.equals("show all tables")){
            showAllTables(con);
            return;
        }
        List<String> lst = new ArrayList<>();
        String[] array = str.split(" ");
        getData(array); 
        String[] list = getTables(con);
        CodeGenerator.createPackage(getPackageName(), getPath());
        if(getLang().equals("java")){
            for(String item : list)
                CodeGenerator.generateSource(con, getPath(),item, getPackageName(), "java");
        }else if(getLang().equals("dotnet")){
            for(String item : list)
                CodeGenerator.generateSource(con, getPath(),item, getPackageName(), "cs");
        }
    }
    
        //generate -p test -t information -path E:\ITU\TEST -l java
        //generate -p solaire.entity -t all -path E:\ITU\L3\Mr_Tahina\SOLAIRE\src\java -l java
}

