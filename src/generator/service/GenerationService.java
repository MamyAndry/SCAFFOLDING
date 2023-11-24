/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package generator.service;

import java.sql.Timestamp;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import utils.ObjectUtility;

/**
 *
 * @author Mamisoa
 */
public class GenerationService {
    
    public static List<String> getAllImports(HashMap<String, Class> columns){
        List<String> lst = new ArrayList<>();
        
        lst.add("import dao.annotation.PrimaryKey;\n");
        lst.add("import dao.annotation.Column;\n");
        lst.add("import dao.annotation.Table;\n");
        lst.add("import java.sql.Connection;\n");
        
        for (Map.Entry<String, Class> set : columns.entrySet()) {  
            if(set.getValue().equals(Date.class))
                lst.add("import java.sql.Date;\n");
            else if(set.getValue().equals(Timestamp.class))
                lst.add("import java.sql.Timestamp;\n");
        } 
        return lst;
    }
    
    public static String getImports(HashMap<String, Class> columns){
        List<String> lst = getAllImports(columns);
        String res = "";
        for(String item : lst){
            res += item;
        }
        return res;
    }
    
    public static String splitByPoint(String str){
        return  str.split("\\.")[str.split("\\.").length - 1];
    }

    public static String getClassName(String table){
        return ObjectUtility.capitalize(DbService.formatString(table));
    }
    
    public static String getClass(String table){
        String res = "@Table(name = " + "\"" + table + "\")\n";
        res += "public class " + getClassName(table) + "{\n";
        return res;
    }

    public static List<String> getAllGetters(HashMap<String, Class> columns){
        List<String> lst = new ArrayList<>();
        
        for (Map.Entry<String, Class> set : columns.entrySet()) {
            String field = DbService.formatString(set.getKey());
            String type = splitByPoint(set.getValue().getName());
            String temp = "\tpublic " + type + " get" + ObjectUtility.capitalize(field) + "(){\n";
            temp += "\t\treturn this." + field + ";\n";
            temp += "\t}\n";
            lst.add(temp);
        }
            
        return lst;
    }

    public static List<String> getAllSetters(HashMap<String, Class> columns){
        List<String> lst = new ArrayList<>();
        
        for (Map.Entry<String, Class> set : columns.entrySet()) {
            String field = DbService.formatString(set.getKey());
            String type = splitByPoint(set.getValue().getName());
            String temp = "\tpublic void set" + ObjectUtility.capitalize(field) + "(" + type + " " + field+"){\n";
            temp += "\t\tthis." + field+" = " + field + ";\n";
            temp += "\t}\n";
            lst.add(temp);
        }
            
        return lst;
    }
    
    public static String getSetters(HashMap<String, Class> columns){
        List<String> lst = getAllSetters(columns);
        String res = "";
        for(String item : lst){
            res += item;
        }
        return res;
    }    
    
    public static String getGetters(HashMap<String, Class> columns){
        List<String> lst = getAllGetters(columns);
        String res = "";
        for(String item : lst){
            res += item;
        }
        return res;
    }
   
    public static List<String> getAllFields(HashMap<String, Class> columns){
        List<String> lst = new ArrayList<>();
        
        for (Map.Entry<String, Class> set : columns.entrySet()) {
            String field = DbService.formatString(set.getKey());
            String type = splitByPoint(set.getValue().getName());
            String temp = "\t@Column(name = \""+set.getKey()+"\")\n";
            temp += "\t" + type + " " + field + ";\n";
            lst.add(temp);
        }
        
        return lst;
    }
    public static String getFields(HashMap<String, Class> columns){
        List<String> lst = getAllFields(columns);
        String res = "";
        for(String item : lst){
            res += item;
        }
        return res;
    }
    
    public static List<String> getAllConstructors(String table, HashMap<String, Class> columns){
        List<String> lst = new ArrayList<>();

        lst.add("\tpublic " + getClassName(table) + "(){}\n");
        String temp = "\tpublic " + getClassName(table) + "(";
        String args = "";
        String  setters = "";
        for (Map.Entry<String, Class> set : columns.entrySet()) {
            String field = DbService.formatString(set.getKey());
            String type = splitByPoint(set.getValue().getName());
            args += type + " " + field + ", ";
            setters += "\t\tset" + ObjectUtility.capitalize(field) +"("+field+");\n";
        }
        args = args.substring(0, args.length() - 2);
        temp += args + "){\n";
        temp += setters + "\t}\n";
        lst.add(temp);
        
        return lst;
    }
    
    public static String getConstructors(String table, HashMap<String, Class> columns){
        List<String> lst = getAllConstructors(table, columns);
        String res = "";
        for(String item : lst){
            res += item;
        }
        return res;
    }
}

