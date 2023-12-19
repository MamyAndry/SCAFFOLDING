/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package generator.service;

import generator.utils.ObjectUtility;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * 
 * @author Mamisoa
 */
public class JavaGenerationService {
    
    public static String getPackage(String packageName){
        return "package "+packageName+";";
    }
    
    public static List<String> getAllImports(HashMap<String, String> columns){
        List<String> lst = new ArrayList<>();
        
        lst.add("import annotation.PrimaryKey;\n");
        lst.add("import annotation.Column;\n");
        lst.add("import annotation.Table;\n");
        lst.add("import java.sql.Connection;\n");
        
        for (Map.Entry<String, String> set : columns.entrySet()) {  
            if(set.getValue().equals("java.sql.Date"))
                lst.add("import java.sql.Date;\n");
            else if(set.getValue().equals("java.sql.Timestamp"))
                lst.add("import java.sql.Timestamp;\n");
            else if(set.getValue().equals("java.sql.Time"))
                lst.add("import java.sql.Time;\n");
            else if(set.getValue().equals("java.math.BigDecimal"))
                lst.add("import java.math.BigDecimal;\n");
//            else if(set.getValue().equals("org.postgresql.geometric.PGpoint"))
//                lst.add("import org.postgresql.geometric.PGpoint;\n");
        } 
        return lst;
    }
    
    public static String getImports(HashMap<String, String> columns){
        List<String> lst = getAllImports(columns);
        String res = "";
        for(String item : lst){
            res += item;
        }
        return res;
    }
    
    public static String splitByPoint(String str){
        if(str.equals("[B"))
            return "Byte[]";
        else if(str.equals("org.postgresql.geometric.PGpoint"))
            return "String";
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

    public static List<String> getAllGettersAndSetters(HashMap<String, String> columns){
        List<String> lst = new ArrayList<>();
        
        for (Map.Entry<String, String> set : columns.entrySet()) {
            String field = DbService.formatString(set.getKey());
            String type = splitByPoint(set.getValue());
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
    
    public static String getGettersAndSetters(HashMap<String, String> columns){
        List<String> lst = getAllGettersAndSetters(columns);
        String res = "";
        for(String item : lst){
            res += item;
        }
        return res;
    }
   
    public static List<String> getAllFields(HashMap<String, String> columns){
        List<String> lst = new ArrayList<>();
        
        for (Map.Entry<String, String> set : columns.entrySet()) {
            String field = DbService.formatString(set.getKey());
            String type = splitByPoint(set.getValue());
            String temp = "\t@Column(name = \""+set.getKey()+"\")\n";
            temp += "\t" + type + " " + field + ";\n";
            lst.add(temp);
        }
        
        return lst;
    }
    public static String getFields(HashMap<String, String> columns){
        List<String> lst = getAllFields(columns);
        String res = "";
        for(String item : lst){
            res += item;
        }
        return res;
    }
    
    public static List<String> getAllConstructors(String table, HashMap<String, String> columns){
        List<String> lst = new ArrayList<>();

        lst.add("\tpublic " + getClassName(table) + "(){}\n");
        String temp = "\tpublic " + getClassName(table) + "(";
        String args = "";
        String  setters = "";
        for (Map.Entry<String, String> set : columns.entrySet()) {
            String field = DbService.formatString(set.getKey());
            String type = splitByPoint(set.getValue());
            args += type + " " + field + ", ";
            setters += "\t\tthis.set" + ObjectUtility.capitalize(field) +"("+field+");\n";
        }
        args = args.substring(0, args.length() - 2);
        temp += args + "){\n";
        temp += setters + "\t}\n";
        lst.add(temp);
        
        return lst;
    }
    
    public static String getConstructors(String table, HashMap<String, String> columns){
        List<String> lst = getAllConstructors(table, columns);
        String res = "";
        for(String item : lst){
            res += item;
        }
        return res;
    }
    
    public static String generate(String template, String packageName, HashMap<String, String> mapp, String table){
        String temp = template;
        temp = temp.replace("%package%", JavaGenerationService.getPackage(packageName));
        temp = temp.replace("%imports%", JavaGenerationService.getImports(mapp));
        temp = temp.replace("%class%", JavaGenerationService.getClass(table));
        temp = temp.replace("%fields%", JavaGenerationService.getFields(mapp));
        temp = temp.replace("%encapsulation%", JavaGenerationService.getGettersAndSetters(mapp));
        temp = temp.replace("%constructors%", JavaGenerationService.getConstructors(table, mapp));
        return temp;
    }
}

