package com.ambovombe.configuration.mapping;

    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import com.ambovombe.generator.parser.JsonUtility;

/**
 *
 * @author Mamisoa
 */
public class Imports {
    private String[] controller;
    private String[] entity;
    
    //GETTERS & SETTERS 
    public String[] getController() {
        return controller;
    }

    public void setController(String[] controller) {
        this.controller = controller;
    }
    
    public String[] getEntity() {
        return entity;
    }

    public void setEntity(String[] entity) {
        this.entity = entity;
    }
    //CONSTRUCTOR
    public Imports(){}  
    
    //METHODS
    
}
