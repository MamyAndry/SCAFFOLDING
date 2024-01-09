/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package configuration;

import generator.service.DbService;
import generator.utils.ObjectUtility;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
  
 * @author Mamisoa
 */
public class Generator {
    LanguageDetails details;
    TypeProperties types;
    
//SETTERS & GETTERS
    public LanguageDetails getDetails() {
        return details;
    }
    public void setDetails(LanguageDetails details) {    
        this.details = details;
    }

    public TypeProperties getTypes() {
        return types;
    }
    public void setTypes(TypeProperties types) {    
        this.types = types;
    }

//CONSTRUCTORS
    public Generator() throws Exception {
        
    }

//METHODS
    
    public String getPackage(String packageName, String language){
        return  this.getDetails().getLanguages().get(language.toLowerCase()).getPackageSyntax() + " " + packageName + ";";
    }
    
    public String getClassName(String table){
        return ObjectUtility.capitalize(DbService.formatString(table));
    }
    
    public static Set<String> getAllImports(HashMap<String, String> columns){
        Set<String> lst = new HashSet<>();

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

    public String getImports(String table, String language) throws Exception{
        return "";
    }
    
    public String getField(String table, String language) throws Exception{
        return "";
    }
}
