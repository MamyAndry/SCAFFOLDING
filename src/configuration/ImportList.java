package configuration;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import generator.parser.JsonUtility;
import java.util.HashMap;

/**
 *
 * @author Mamisoa
 */
public class ImportList {
    HashMap<String, Imports> listImport;

    public HashMap<String, Imports> getListImport() {
        return listImport;
    }

    public void setListImport(HashMap<String, Imports> listImport) {
        this.listImport = listImport;
    }

    public ImportList() {
    }
    
    //METHODS
    public void read() throws Exception{
        String path = "./ressources/Imports.json";
        ImportList temp = JsonUtility.parseJson(path, this.getClass());
        this.setListImport(temp.getListImport());
    }
}
