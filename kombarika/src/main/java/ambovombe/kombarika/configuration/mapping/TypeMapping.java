/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ambovombe.kombarika.configuration.mapping;

import java.util.HashMap;

/**
 *
 * @author Mamisoa
 */
public class TypeMapping {
    HashMap<String, TypeProperty> listMapping;

    //GETTERS & SETTERS

    public HashMap<String, TypeProperty> getListMapping() {
        return listMapping;
    }

    public void setListMapping(HashMap<String, TypeProperty> listMapping) {
        this.listMapping = listMapping;
    }
    
    
    //CONSTRUCTOR
    public TypeMapping(){}
}
