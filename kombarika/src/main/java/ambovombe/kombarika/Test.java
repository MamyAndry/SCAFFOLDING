package ambovombe.kombarika;
/*entity
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

import ambovombe.kombarika.generator.CodeGenerator;
import ambovombe.kombarika.generator.service.DbService;
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
        // String path = "/home/mamisoa/ITU/L3/Mr_Naina/TEST/test/src/main/java";
        // String path = "/Users/rakharrs/S5/Naina/testMerana/demo/src/main/java";
        // String viewPath = "/home/mamisoa/ITU/L3/Mr_Naina/REACT/crud/src/components";
        
        String path = "./back";
        String viewPath = "./front";


        // String path = "/home/mamisoa/ITU/L3/Mr_Naina/FRAMEWORK/testkarana/src/main/java";
        // String viewPath = "/Users/rakharrs/S5/Naina/mamisoa/angulartemp/src/app";

        // String path = "/home/mamisoa/ITU/L3";
        // String viewPath = "/home/mamisoa/ITU/L3";


        String framework = "java:merana";
        String packageName = "com.example.demo";
        String entity = "entity";
        String controller = "controller";
        String repository = "repository";
        String view = "";
        String viewType = "react";
        String url = "http://localhost:8080/demo_war_exploded/";
        try{
            // String[] tables = {"media_publication"};
            // DbConnection dbConnection = codeGenerator.getDbConnection();
            // String str = dbConnection.getListConnection().get(dbConnection.getInUseConnection()).getDatabaseType().getForeignKeyQuery();
            // str = str.replace("?", "commune");
            // System.out.println(str);
            // HashMap<String, String> foreignKeys = DbService.getForeignKeys(dbConnection, "commune");
            // for (Map.Entry<String, String> set : foreignKeys.entrySet()) {
            //     System.out.println(set.getKey() + " " + set.getValue());
            // }
            String[] tables = DbService .getAllTablesArrays(codeGenerator.getDbConnection());
            
            codeGenerator.generateAll(path, viewPath, packageName, entity, controller, repository, view, viewType, url, tables, framework);

        }catch(Exception e){
            e.printStackTrace();
        }finally{
            codeGenerator.getDbConnection().close();
        }    
    }
}
