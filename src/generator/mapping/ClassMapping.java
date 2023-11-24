/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package generator.mapping;

import java.util.HashMap;
import java.sql.Timestamp;
import java.sql.Date;
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
    
    public static HashMap<Integer, Class> getClassMapTable(){
        HashMap<Integer, Class> mapping = new HashMap<>();
        
        mapping.put(4, Integer.class);
        mapping.put(-5, Integer.class);
        mapping.put(5, Integer.class);
        mapping.put(-6, Integer.class);
        mapping.put(3, Double.class);
        mapping.put(8, Double.class);
        mapping.put(2, Double.class);
        mapping.put(6, Float.class);
        mapping.put(12, String.class);
//        mapping.put(2014, Timestamp.class);
        mapping.put(93, Timestamp.class);
        mapping.put(0, null);
        mapping.put(91, Date.class);
        
        return mapping;
    
    }
}

