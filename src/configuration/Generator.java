/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package configuration;

import generator.service.DbService;
import generator.utils.ObjectUtility;
import java.sql.Connection;
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
    ImportList importList;
    
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

    public ImportList getImportList() {
        return importList;
    }

    public void setImportList(ImportList importList) {
        this.importList = importList;
    }

//CONSTRUCTORS
    public Generator() throws Exception {
        
    }

//METHODS
    
    public String getPackage(String packageName, String language){
        return  this.getDetails().getLanguages().get(language).getPackageSyntax()
                + " " + packageName 
                + this.getDetails().getLanguages().get(language).getEndOfInstruction();
    }
    
    public String getClass(String table, String language){
        return this.getDetails().getLanguages().get(language).getClasseSyntaxe() + " " 
                + ObjectUtility.capitalize(ObjectUtility.formatToCamelCase(table));
    }
    
    public Set<String> getAllImports(HashMap<String, String> columns, String language){
        Set<String> lst = new HashSet<>();
        for (Map.Entry<String, String> set : columns.entrySet()) {
            lst.add(this.getTypes().getListProperties().get(language).getListMapping().get(set.getValue()).getPackageName()+
                    ""+this.getDetails().getLanguages().get(language).getEndOfInstruction()+
                    "\n");
        }
        return lst;
    }

    public String getImports(Connection con, String table, String language) throws Exception{
        String res = "";
        HashMap<String, String> column = DbService.getColumnNameAndType(con, table); 
        Set<String> imports = this.getAllImports(column, language);
        for (String elem : imports) {
            res += elem;
        }
        return res;
    }
    
    public String getField(String table, String language) throws Exception{
        String res = "";
        return res;
    }
    
//    public String getEncapsulation(Connection con, String table, String language) throws Exception{
//        HashMap<String, String> columns = DbService.getColumnNameAndType(con, table);
//        String res = "";
//        for (Map.Entry<String, String> set : columns.entrySet()) {
//            String field = set.getKey();
//            String type = this.getTypes().getListProperties().get(language).getListMapping().get(set.getKey()).getType();
//            String 
//        }
//        return res;
//    }
}
