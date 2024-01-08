/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package generator.service;

import generator.mapping.TypeProperties;
import generator.utils.ObjectUtility;

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
}
