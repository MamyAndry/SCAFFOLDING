/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package generator.service;

import generator.mapping.ClassMapping;
import generator.utils.ObjectUtility;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Mamisoa
 */
public class DotnetGenerationService {
    
    public static String getPackage(String packageName){
        return "namespace "+ packageName +";";
    }
    
    public static List<String> getAllImports(HashMap<String, String> columns){
        List<String> lst = new ArrayList<>();
        
        lst.add("using System;");
        for (Map.Entry<String, String> set : columns.entrySet()) {  

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

    public static String getClassName(String table){
        return ObjectUtility.capitalize(DbService.formatString(table));
    }
    
    public static String getClass(String table){
        String res = "[Table(name = \""+table+"\")]\n";
        res += "public class " + getClassName(table) + "";
        return res;
    }

    public static List<String> getAllGettersAndSetters(HashMap<String, String> columns){
        List<String> lst = new ArrayList<>();
        
        for (Map.Entry<String, String> set : columns.entrySet()) {
            String field = DbService.formatString(set.getKey()); 
            String type = ClassMapping.getClassMapDotnet().get(set.getValue());
            String temp = "\t[Column(name = \""+set.getKey()+"\")]\n";
            temp += "\t public " + type + " " + ObjectUtility.capitalize(field) + "{\n";
            temp += "\t\t get{\n";
            temp += "\t\t\treturn this." + field + ";\n";
            temp += "\t\t}\n";
            temp += "\t\tset{\n";
            temp += "\t\t\tthis." + field + " = value;\n";
            temp += "\t\t}\n";
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
            String type = ClassMapping.getClassMapDotnet().get(set.getValue());
            String temp = "\t[NotMapped]\n";
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
            String type = ClassMapping.getClassMapDotnet().get(set.getValue());
            args += type + " " + field + ", ";
            setters += "\t\tset" + ObjectUtility.capitalize(field) +"("+field+");\n";
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
        System.out.println("HHUHUHUHU");
        temp = temp.replace("%package%", DotnetGenerationService.getPackage(packageName));
        temp = temp.replace("%imports%", DotnetGenerationService.getImports(mapp));
        temp = temp.replace("%class%", DotnetGenerationService.getClass(table));
        temp = temp.replace("%fields%", DotnetGenerationService.getFields(mapp));
        temp = temp.replace("%encapsulation%", DotnetGenerationService.getGettersAndSetters(mapp));
        temp = temp.replace("%constructors%", DotnetGenerationService.getConstructors(table, mapp));
        return temp;
    }
}
