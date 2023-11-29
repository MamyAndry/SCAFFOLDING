/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package generator.mapping;

import java.util.HashMap;
import java.sql.Timestamp;
import java.sql.Date;
import java.time.LocalTime;
/**
 *
 * @author Mamisoa
 */
public class ClassMapping {
    
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

