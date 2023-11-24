/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package generator;

import dao.DbConnection;
import java.sql.Connection;
import generator.service.DbService;
import generator.service.GenerationService;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

/**
 *
 * @author Mamisoa
 */
public class CodeGenerator {
    public static void createPackage(String packageName, String path) throws Exception{
        String separator = "//";
        if(System.getProperty("os.name").equals("Linux"))
            separator = "/";
        Path directoryPath = Paths.get(path + separator + packageName)   ;
        Files.createDirectories(directoryPath);
        System.out.println("Package created");
    }
    
    public static void createFile(String packageName, String path, String fileName) throws Exception{
        String separator = "//";
        if(System.getProperty("os.name").equals("Linux"))
            separator = "/";

        path = path + separator + packageName + separator + fileName + ".java";
        File file = new File(path);
        System.out.println(file.getAbsolutePath() + " succesfully created");
    }
    
    public static void writeFile(Connection con, String table, String path, String packageName, String fileName) throws Exception{
        String separator = "//";
        if(System.getProperty("os.name").equals("Linux"))
            separator = "/";
        path = path + separator + packageName + separator + fileName + ".java";
        FileWriter writer = new FileWriter(path);
        HashMap<String, Class> mapp = DbService.getColumnNameAndType(con, table);
        
        String imports = GenerationService.getImports(mapp);
        String className = GenerationService.getClass(table);
        String fields = GenerationService.getFields(mapp);
        String getters = GenerationService.getGetters(mapp);
        String setters = GenerationService.getSetters(mapp);
        String constructors = GenerationService.getConstructors(table, mapp);
        
        writer.write("package " + packageName + ";\n\n");
        writer.write(imports + "\n");
        writer.write(className + "\n");
        writer.write(fields + "\n");
        writer.write("\t//GETTERS AND SETTERS \n");
        writer.write(getters + "\n");
        writer.write(setters + "\n");
        writer.write("\t//CONSTRUCTORS \n");
        writer.write(constructors + "\n");
        writer.write("}\n");
        writer.close();
    }
    
    public static void generateJavaSource(Connection con, String path, String table, String packageName) throws Exception{   
        boolean state = false;
        if(con == null){
            con = new DbConnection().connect();
            state = true;
        }
        String fileName = GenerationService.getClassName(table);
        createPackage(packageName, path);
        createFile(packageName, path, fileName);
        writeFile(con, table, path, packageName, fileName);
        
        if( state == true) con.close();
    }
    
}
