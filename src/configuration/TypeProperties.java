/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package configuration;

import generator.parser.JsonUtility;

import java.nio.file.Paths;
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
        read();
    }

    //METHODS
    public void read() throws Exception{
        String path = "ressources/fieldType.json";
        // String confFile = System.getProperty("user.dir");
        // System.out.println(confFile);
        TypeProperties temp = JsonUtility.parseJson(path, this.getClass());
        this.setListProperties(temp.getListProperties());
    }
}
