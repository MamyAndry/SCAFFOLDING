/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package generator;

import dao.DbConnection;
import generator.parser.FileParser;
import generator.service.DbService;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mamisoa
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Connection con = null;
        try{
            con = new DbConnection().connect();
            System.out.println(con);
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
                    case "Language":
                        lang = elt[1];
                        break;
                    default:
                        break;
                }
            }
            List<String> list = DbService.getAllTables(con);
            for(String item : list)
                CodeGenerator.generateJavaSource(con, path,item, packageName);
            
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try {
                con.close();
            } catch (SQLException ex) {
               ex.printStackTrace();
            }
        }
        
    }
    
}
