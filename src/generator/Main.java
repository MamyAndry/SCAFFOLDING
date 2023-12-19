/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package generator;

import generator.dao.DbConnection;
import java.sql.Connection;
import java.sql.SQLException;
import properties.DatabaseType;

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
            con = DbConnection.connect();
//            while(1 != 2){
//                Scanner scan = new Scanner(System.in);
//                System.out.print("Scaffold >> ");
//                String cli = scan.nextLine();
//                new CLIReader().read(con, cli);
//            }
//            DbService.getTableConstraints(con, "secteur");
            String query = DbConnection.getDatabaseType().getForeignKeyQuery();
            System.out.println(query);
            System.out.println(query.replace("?", "'secteur'"));
        }catch(Exception e){
            e.getMessage();
        }finally{
            con.close();
        }
        
    }
    
}
