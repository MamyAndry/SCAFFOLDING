/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package generator;

import configuration.LanguageDetails;
import generator.dao.DbConnection;
import generator.parser.FileUtility;
import generator.parser.JsonUtility;
import java.net.URL;
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
            
            con = new DbConnection().connect();
            
//            while(1 != 2){
//                Scanner scan = new Scanner(System.in);
//                System.out.print("Scaffold >> ");
//                String cli = scan.nextLine();
//                new CLIReader().read(con, cli);
//            }
//            System.out.println(DbConnection.getDatabaseType());
//            CodeGenerator.generateSource(con,"E:\\ITU\\L3\\Mr_Naina\\SCAFFOLDING\\test", "secteur", "test", "cs");
//            String test = "{\"databaseType\":\"POSTGRESQL\",\"datasource\":\"jdbc:postgresql://localhost:5432/solaire\",\"username\":\"mamisoa\",\"password\":\"prom15\"}";
//            String primaryKey = DbService.getPrimaryKey(con, "secteur");
//            System.out.println(primaryKey );
//            String separator = File.separator; 
            URL url = Thread.currentThread().getContextClassLoader().getResource("database.json");
            System.out.println(url);
            String pathJson = ".\\ressources\\languageDetails.json";
            String path = ".\\ressources";
            String huhu= "Column(name = )";
//            String path = ;
            LanguageDetails lg = JsonUtility.parseJson(pathJson, new LanguageDetails().getClass());
//            System.out.println(FileParser.readOneFile(path));
//            System.out.println(JsonUtility.encodeJson(lg));
//            DbConnection onj = new DbConnection();
            FileUtility.generateFile(path, "test.txt", lg.getLanguages().get("dotnet").getEncapsulation());
            
//            JsonReader reader = new JsonReader(new BufferedReader(new FileReader(path)));
//            onj = new Gson().fromJson(reader, onj.getClass());
//            System.out.println(onj.getListConnection());
//            System.out.println(onj.getDatabaseType().getDriver());
//            System.out.println(new Gson().toJson(onj));
//            HashMap<String, ArrayList<LanguageType>> mapping = new HashMap<>();
//            String huhu = CodeGenerator.getTemplate(Main.class.getResourceAsStream("fieldType.json"));
//            System.out.println(huhu);
//            String path = ".\\ressources\\fieldType.json";
//            TypeProperties types = JsonUtility.parseJson(path, TypeProperties.class);
//            System.out.println(types.getListProperties().get("java").getListMapping().get("Integer").getType()  );
//            mapping = JsonParser.parseJson(path, mapping.getClass());
//            System.out.println(mapping.get("java").size());
//            System.out.println(JsonUtility.encodeJson(types));
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            con.close();
        }
        
    }
    
}
