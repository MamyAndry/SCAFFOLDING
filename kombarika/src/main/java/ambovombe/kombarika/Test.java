package ambovombe.kombarika;
/*entity
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

import ambovombe.kombarika.configuration.mapping.ViewProperties;
import ambovombe.kombarika.database.DbProperties;
import ambovombe.kombarika.generator.CodeGenerator;
import ambovombe.kombarika.generator.service.DbService;

import java.sql.SQLException;
import java.util.Map;

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
        String path = "./";
        // String viewPath = "/home/mamisoa/ITU/L3/Mr_Naina/REACT/crud/src/components";
        
        // String path = "/home/mamisoa/ITU/L3/Mr_Naina/FRAMEWORK/testkarana/src/main/java";
        String viewPath = "./";

        // String path = "/home/mamisoa/ITU/L3";
        // String viewPath = "/home/mamisoa/ITU/L3";


        String framework = "java:karana";
        String packageName = "com.district.test";
        String entity = "entity";
        String controller = "controller";
        String repository = "repository";
        String view = "";
        String viewType = "vue";
        String url = "http://localhost:8080/testkarana/";
        try{
            // String[] tables = {"district","region"};
            // DbConnection dbConnection = codeGenerator.getDbConnection();
            // String str = dbConnection.getListConnection().get(dbConnection.getInUseConnection()).getDatabaseType().getForeignKeyQuery();
            // str = str.replace("?", "commune");
            // System.out.println(str);
            // HashMap<String, String> foreignKeys = DbService.getForeignKeys(dbConnection, "commune");
            // for (Map.Entry<String, String> set : foreignKeys.entrySet()) {
            //     System.out.println(set.getKey() + " " + set.getValue());
            // }
            // String[] tables = DbService .getAllTablesArrays(codeGenerator.getDbConnection());
            // for(String table: tables)
            //     System.out.println(table);
            // codeGenerator.generateAll(path, viewPath, packageName, entity, controller, repository, view, viewType, url, tables, framework);
            // codeGenerator.generateViewEnvironement(viewPath, viewType, "huhuhu");
                // DbProperties db = new DbProperties();
            // db.addConnection("huhuhu");
            for (Map.Entry<String, ViewProperties> set:codeGenerator.getViewDetails().getViews().entrySet()){
                System.out.println(set.getKey()+"  "+set.getValue());
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            codeGenerator.getDbConnection().close();
        }    
    }
}
