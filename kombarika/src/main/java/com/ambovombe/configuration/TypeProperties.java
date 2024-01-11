/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ambovombe.configuration;

import com.ambovombe.configuration.mapping.TypeMapping;
import com.ambovombe.generator.parser.JsonUtility;

import java.util.HashMap;

/**
 *
 * @author Mamisoa
 */
public class TypeProperties extends Configuration{
    HashMap<String, TypeMapping> listProperties;

    //GETTERS & SETTERS
    public HashMap<String, TypeMapping> getListProperties() {
        return listProperties;
    }

    public void setListProperties(HashMap<String, TypeMapping> listProperties) {
        this.listProperties = listProperties;
    }

    //CONSTRUCTOR
    public TypeProperties(){}

    @Override
    public void init() throws Exception{
        setJsonPath("typeProperties.json");
        TypeProperties typeProperties = this.read();
        setListProperties(typeProperties.getListProperties());
    }
}
