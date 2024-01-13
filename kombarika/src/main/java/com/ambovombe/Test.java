package com.ambovombe;/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */


import com.ambovombe.generator.CodeGenerator;

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
        String table = "departement";
        String framework = "dotnet:dotnet";
        codeGenerator.generateController(path, table, "huhu.controller", framework);
        codeGenerator.generateEntity(path, table, "huhu.entity", framework);
        codeGenerator.generateRepository(path, table, "huhu.repository", "huhu.entity", framework);

//        Connection con = null;
//        try{
//            DbConnection dbConnection = new DbConnection();
//            con = dbConnection.connect();
//
//            LanguageDetails languageDetails = new LanguageDetails();
//            languageDetails.init();
//            TypeProperties properties = new TypeProperties();
//            properties.init();
//            ImportList list = new ImportList();
//            list.init();
//            Annotations annotations = new Annotations();
//            annotations.init();
//            ControllerProperties controllerProperties = new ControllerProperties();
//            controllerProperties.init();
//            CrudDao crudDao = new CrudDao();
//            crudDao.init();
//
//            String language = "java";
//            String framework = "spring-boot";
//
//            String table = "region";
//
//            //CONSTRUCTORS
//
//            LanguageProperties lp = languageDetails.getLanguages().get(language);
//            TypeMapping type = properties.getListProperties().get(language);
//            Imports imports = list.getListImport().get(framework);
//            AnnotationProperty ann = annotations.getLanguages().get(framework);
//            ControllerProperty controller = controllerProperties.getLanguages().get(framework);
//            CrudMethod method = crudDao.getLanguages().get(framework);
//            String path = ".";
////            System.out.println(ControllerService.getControllerClass(table, lp, ann, controller));
////            System.out.println(ControllerService.getControllerField(table, lp, controller));
////            System.out.println(ControllerService.getCrudMethods(table, lp, method, controller));
//            String template = Misc.getSourceTemplateLocation() + File.separator + "EntityTemplate.code";
//            template = FileUtility.readOneFile(template);
//            System.out.println(ControllerService.generateController(method, template, table, "huu", controller, lp, imports, ann));
////            FileUtility.createDirectory(table, ".");
////            System.out.println(printlnGeneratorService.generate(con, "", "region", "huhu", lp, type);
//
////            CodeGenerator.generatorate(path, table, "huhu", con, dbConnection,lp, type, imports, ann);
//        }catch (Exception e){
//            e.printStackTrace();
    //    }finally {
        //    con.close();
    //    }
    }

}
