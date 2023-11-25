/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package generator.service;

import java.io.BufferedReader;
import java.io.FileReader;
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
public class JavaGenerationService {
    
    public static String getPackage(String packageName){
        return "package "+packageName+";";
    }
    
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
        res += "public class " + getClassName(table) + "";
        return res;
    }

    public static List<String> getAllGettersAndSetters(HashMap<String, Class> columns){
        List<String> lst = new ArrayList<>();
        
        for (Map.Entry<String, Class> set : columns.entrySet()) {
            String field = DbService.formatString(set.getKey());
            String type = splitByPoint(set.getValue().getName());
            String temp = "\tpublic " + type + " get" + ObjectUtility.capitalize(field) + "(){\n";
            temp += "\t\treturn this." + field + ";\n";
            temp += "\t}\n";
            temp += "\tpublic void set" + ObjectUtility.capitalize(field) + "(" + type + " " + field+"){\n";
            temp += "\t\tthis." + field+" = " + field + ";\n";
            temp += "\t}\n";
            lst.add(temp);
        }
            
        return lst;
    } 
    
    public static String getGettersAndSetters(HashMap<String, Class> columns){
        List<String> lst = getAllGettersAndSetters(columns);
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
    
    public static String getTemplate(String path) throws Exception{
        StringBuilder builder = new StringBuilder();
        BufferedReader reader = new BufferedReader(new FileReader(path));
        String line;
        while((line = reader.readLine()) != null){
            builder.append(line).append("\n");
        }
        return builder.toString();
    }
}

