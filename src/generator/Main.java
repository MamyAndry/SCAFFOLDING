/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package generator;

import com.google.gson.Gson;
import generator.dao.DbConnection;
import generator.parser.JsonParser;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Mamisoa
 */
public class Main {

    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     */
    public static void main(String[] args) throws SQLException {
        Connection con = null;
        try{// TODO code application logic here
            
//            con = DbConnection.connect();
//            while(1 != 2){
//                Scanner scan = new Scanner(System.in);
//                System.out.print("Scaffold >> ");
//                String cli = scan.nextLine();
//                new CLIReader().read(con, cli);
//            }
//            System.out.println(DbConnection.getDatabaseType());
//            CodeGenerator.generateSource(con,"E:\\ITU\\L3\\Mr_Naina\\SCAFFOLDING\\test", "secteur", "test", "cs");
//            String primaryKey = DbService.getPrimaryKey(con, "secteur");
//            System.out.println(primaryKey );
            String separator = File.separator;
//            String test = "{\"databaseType\":\"POSTGRESQL\",\"datasource\":\"jdbc:postgresql://localhost:5432/solaire\",\"username\":\"mamisoa\",\"password\":\"prom15\"}";
//            DbConnection onj = new Gson().fromJson(test, DbConnection.class);
            DbConnection trmp = new DbConnection();
            DbConnection onj = JsonParser.parseJson("E:\\ITU\\L3\\Mr_Naina\\SCAFFOLDING\\database.json", DbConnection.class);
            
            System.out.println(new Gson().toJson(onj));
            System.out.println(onj.getDatabaseType().getDriver());
        }catch(Exception e){
            e.printStackTrace();
        }finally{
//            con.close();
        }
        
    }
    
}
