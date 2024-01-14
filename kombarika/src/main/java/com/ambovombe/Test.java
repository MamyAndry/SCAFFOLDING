package com.ambovombe;/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */


import com.ambovombe.generator.CodeGenerator;
import com.ambovombe.generator.service.DbService;

import java.sql.SQLException;

/**
 *
 * @author Mamisoa
 */
public class Test {

    /**
     * @param args the command line arguments
     * @throws SQLException
     */
     
    public static void main(String[] args) throws Exception {
        CodeGenerator codeGenerator = new CodeGenerator();
        String path = "./";
        String framework = "java:spring-boot";
        String packageName = "carselling.selling";
        String entity = "entity";
        String controller = "controller";
        String repository = "repository";
        try{
            String[] tables = {"car"};

            // String[] tables = DbService.getAllTablesArrays(codeGenerator.getDbConnection());
            codeGenerator.generateAll(path, packageName, entity, controller, repository, tables, framework);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            codeGenerator.getDbConnection().close();
        }
    }

}
