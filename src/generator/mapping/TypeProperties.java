/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package generator.mapping;

import generator.parser.JsonUtility;
import java.util.HashMap;

/**
 *
 * @author Mamisoa
 */
public class TypeProperties {
    HashMap<String, TypeMapping> listProperties;

    //GETTERS & SETTERS
    public HashMap<String, TypeMapping> getListProperties() {
        return listProperties;
    }

    public void setListProperties(HashMap<String, TypeMapping> listProperties) {
        this.listProperties = listProperties;
    }
    
    //CONSTRUCTOR
    public TypeProperties() throws Exception{
//        read();
    }
    
    //METHODS
    public void read() throws Exception{
        String path = "fieldType.json";
        TypeProperties temp = JsonUtility.parseJson(path, this.getClass());
        this.setListProperties(temp.getListProperties());
    }
}
