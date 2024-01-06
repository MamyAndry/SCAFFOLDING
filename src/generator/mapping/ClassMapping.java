
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package generator.mapping;

import java.util.HashMap;
/**
 *
 * @author Mamisoa
 */
public class ClassMapping {
    
    public static HashMap<String, HashMap<String, String>> getTypeMapping(){
        HashMap<String, HashMap<String, String>> mapping = new HashMap<>();
        mapping.put("java", getClassMapJava());
        mapping.put("dotnet", getClassMapDotnet());
        return mapping;
    }
    
    public static HashMap<String, Class> getClassMap(){
        HashMap<String, Class> mapping = new HashMap<>();
        
        mapping.put("int", Integer.class);
        mapping.put("Integer", Integer.class);
        mapping.put("double", Double.class);
        mapping.put("Double", Double.class);
        mapping.put("Float", Float.class);
        mapping.put("Float", Float.class);
        mapping.put("boolean", Boolean.class);
        mapping.put("Boolean", Boolean.class);
        mapping.put("byte", Byte.class);
        mapping.put("Byte", Byte.class);
        
        return mapping;
    } 

    public static HashMap<String, String> getIdAnnotationSyntax(){
        HashMap<String, String> mapping = new HashMap<>();
        mapping.put("ambovombe", "PrimaryKey(name = ?)");
        mapping.put("spring", "Id");
        mapping.put("dotnet", "Id");
        return mapping;
    }
    
    public static HashMap<String, String> getColumnAnnotationSyntax(){
        HashMap<String, String> mapping = new HashMap<>();
        mapping.put("ambovombe", "Column(name = ?)");
        mapping.put("spring", "Column(name = ?)");
        mapping.put("dotnet", "Column(name = ?)");
        return mapping;
    }
    
    public static HashMap<String, String> getClassMapJava(){
        HashMap<String, String> mapping = new HashMap<>();
        
        mapping.put("java.lang.Integer", "Integer");
        mapping.put("java.lang.Double", "Double");
        mapping.put("java.lang.Float", "Float");
        mapping.put("java.lang.String", "String");
        mapping.put("java.sql.Timestamp", "Timestamp");
        mapping.put("java.lang.Boolean", "Boolean");
        mapping.put("java.sql.Date", "Date");
        mapping.put("java.lang.Char", "Char");
        mapping.put("java.sql.Time", "Time");
        mapping.put("java.math.BigDecimal", "BigDecimal");
        mapping.put(" java.math.BigInt", "BigInt");
        mapping.put("[B", "Byte[]");
        mapping.put("org.postgresql.geometric.PGpoint", "String");

        return mapping;
    }
    
    public static HashMap<String, String> getClassMapDotnet(){
        HashMap<String, String> mapping = new HashMap<>();
        
        mapping.put("java.lang.Integer", "Int");
        mapping.put("java.lang.Double", "Double");
        mapping.put("java.lang.Float", "Float");
        mapping.put("java.lang.String", "string");
        mapping.put("java.sql.Timestamp", "DateTime");
        mapping.put("java.lang.Boolean", "bool");
        mapping.put("java.sql.Date", "DateOnly");
        mapping.put("java.lang.Char", "Char");
        mapping.put("java.sql.Time", "TimeOnly");
        mapping.put("java.math.BigDecimal", "decimal");
        mapping.put(" java.math.BigInt", "BigInteger");
        mapping.put("[B", "Byte[]");
        mapping.put("org.postgresql.geometric.PGpoint", "string");

        return mapping;
    }
}

