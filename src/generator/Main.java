/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package generator;

import dao.DbConnection;
import static generator.CodeGenerator.createPackage;
import generator.parser.FileParser;
import generator.service.DbService;
import static generator.service.JavaGenerationService.splitByPoint;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.ObjectUtility;

/**
 *
 * @author Mamisoa
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        Connection con = null;
        try{
            System.out.println("Hi from " + System.getProperty("user.dir"));
            con = new DbConnection().connect();
            String path = "";
            String packageName = "";
            String lang = "";
            String confFile = System.getProperty("user.dir") + "\\scaffold.conf";
            
            if(System.getProperty("os.name").equals("Linux"))
                confFile = System.getProperty("user.dir") + "/scaffold.conf";
            List<String[]> lst = FileParser.readFile(confFile);
            
            for(String[] elt : lst ){
                switch (elt[0]) {
                    case "path":
                        path = elt[1];
                        break;
                    case "packageName":
                        packageName = elt[1];
                        break;
                    case "language":
                        lang = elt[1];
                        break;
                    default:
                        break;
                }
            }
            List<String> list = DbService.getAllTables(con);
            
            CodeGenerator.createPackage(packageName, path);
            if(lang.equals("JAVA")){
                for(String item : list)
                    CodeGenerator.generateSource(con, path,item, packageName, "java");
            }else if(lang.equals("DOTNET")){
                for(String item : list)
                    CodeGenerator.generateSource(con, path,item, packageName, "cs");
            }

//            HashMap<String, String> map = DbService.getDetailsColumn(con, "information");
//            for (Map.Entry<String, String> set : map.entrySet()) {
//                System.out.println("key = "+set.getKey() + " value = " + set.getValue());
//            }
        }catch(Exception e){
            e.printStackTrace();
        }
//        finally{
//            con.close();
//        }
        
    }
    
}
