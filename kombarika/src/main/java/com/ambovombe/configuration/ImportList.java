package com.ambovombe.configuration;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import com.ambovombe.configuration.mapping.Imports;
import com.ambovombe.generator.parser.JsonUtility;
import java.util.HashMap;

/**
 *
 * @author Mamisoa
 */
public class ImportList extends Configuration{
    HashMap<String, Imports> listImport;

    public HashMap<String, Imports> getListImport() {
        return listImport;
    }

    public void setListImport(HashMap<String, Imports> listImport) {
        this.listImport = listImport;
    }

    public ImportList(){}

    @Override
    public void init() throws Exception{
        setJsonPath("import.json");
        ImportList importList = this.read();
        setListImport(importList.getListImport());
    }
}
