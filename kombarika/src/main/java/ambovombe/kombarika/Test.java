package ambovombe.kombarika;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */


import ambovombe.kombarika.configuration.main.ViewDetails;
import ambovombe.kombarika.generator.CodeGenerator;
import ambovombe.kombarika.generator.parser.JsonUtility;
import ambovombe.kombarika.generator.service.DbService;
import ambovombe.kombarika.generator.utils.ObjectUtility;

import java.sql.SQLException;

/**
 *
 *  @author Mamisoa
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
        String packageName = "com.district.test";
        String entity = "entity";
        String controller = "controller";
        String repository = "repository";
        String view = "view";
        String url = "http://localhost:8080";
        try{
            String[] tables = {"region"};
            // String[] tables = DbService.getAllTablesArrays(codeGenerator.getDbConnection());
            // for(String table: tables)
            //     System.out.println(table);
            codeGenerator.generateAll(path, packageName, entity, controller, repository, view, url, tables, framework);
            // codeGenerator.generateEntity(path, "car", packageName+".entity", framework);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            codeGenerator.getDbConnection().close();
        }
    }
}
