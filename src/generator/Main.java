/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package generator;

import generator.dao.DbConnection;
import generator.service.DbService;
import java.sql.Connection;
import java.util.Scanner;

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
            con = new DbConnection().connect();
//            while(1 != 2){
//                Scanner scan = new Scanner(System.in);
//                System.out.print("Scaffold >> ");
//                String cli = scan.nextLine();
//                new CLIReader().read(con, cli);
//            }
            DbService.getTableConstraints(con, "photo");
        }catch(Exception e){
            e.printStackTrace();
        }
        finally{
            con.close();
        }
        
    }
    
}
