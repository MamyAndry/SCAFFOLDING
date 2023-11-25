/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package generator;

import dao.DbConnection;
import java.sql.Connection;
import generator.service.DbService;
import generator.service.DotnetGenerationService;
import generator.service.JavaGenerationService;
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
    
    public static void createJavaFile(String packageName, String path, String fileName) throws Exception{
        String separator = "//";
        if(System.getProperty("os.name").equals("Linux"))
            separator = "/";

        path = path + separator + packageName + separator + fileName + ".java";
        File file = new File(path);
        System.out.println(file.getAbsolutePath() + " succesfully created");
    }
    
    public static void writeJavaFile(Connection con, String table, String path, String packageName, String fileName) throws Exception{
        String separator = "//";
        if(System.getProperty("os.name").equals("Linux"))
            separator = "/";
        path = path + separator + packageName + separator + fileName + ".java";
        FileWriter writer = new FileWriter(path);
        HashMap<String, Class> mapp = DbService.getColumnNameAndType(con, table);
        
        String temp = JavaGenerationService.getTemplate("Template.code");
        
        temp = temp.replace("%package%", JavaGenerationService.getPackage(packageName));
        temp = temp.replace("%imports%", JavaGenerationService.getImports(mapp));
        temp = temp.replace("%class%", JavaGenerationService.getClass(table));
        temp = temp.replace("%fields%", JavaGenerationService.getFields(mapp));
        temp = temp.replace("%encapsulation%", JavaGenerationService.getGettersAndSetters(mapp));
//        temp = temp.replace("%setters%", JavaGenerationService.getSetters(mapp));
        temp = temp.replace("%constructors%", JavaGenerationService.getConstructors(table, mapp));
        
        writer.write(temp);
        writer.close();
    }
    
    public static void generateJavaSource(Connection con, String path, String table, String packageName) throws Exception{   
        boolean state = false;
        if(con == null){
            con = new DbConnection().connect();
            state = true;
        }
        String fileName = JavaGenerationService.getClassName(table);
        createPackage(packageName, path);
        createJavaFile(packageName, path, fileName);
        writeJavaFile(con, table, path, packageName, fileName);
        
        if( state == true) con.close();
    }
    public static void createDotnetFile(String packageName, String path, String fileName) throws Exception{
        String separator = "//";
        if(System.getProperty("os.name").equals("Linux"))
            separator = "/";

        path = path + separator + packageName + separator + fileName + ".cs";
        File file = new File(path);
        System.out.println(file.getAbsolutePath() + " succesfully created");
    }
    
    public static void writeDotnetFile(Connection con, String table, String path, String packageName, String fileName) throws Exception{
        String separator = "//";
        if(System.getProperty("os.name").equals("Linux"))
            separator = "/";
        path = path + separator + packageName + separator + fileName + ".cs";
        FileWriter writer = new FileWriter(path);
        HashMap<String, Class> mapp = DbService.getColumnNameAndType(con, table);
        
        String temp = JavaGenerationService.getTemplate("Template.code");
        
        temp = temp.replace("%package%", DotnetGenerationService.getPackage(packageName));
        temp = temp.replace("%imports%", DotnetGenerationService.getImports(mapp));
        temp = temp.replace("%class%", DotnetGenerationService.getClass(table));
        temp = temp.replace("%fields%", DotnetGenerationService.getFields(mapp));
        temp = temp.replace("%encapsulation%", DotnetGenerationService.getGettersAndSetters(mapp));
//        temp = temp.replace("%setters%", JavaGenerationService.getSetters(mapp));
        temp = temp.replace("%constructors%", DotnetGenerationService.getConstructors(table, mapp));
        
        writer.write(temp);
        writer.close();
    }
    
    public static void generateDotnetSource(Connection con, String path, String table, String packageName) throws Exception{   
        boolean state = false;
        if(con == null){
            con = new DbConnection().connect();
            state = true;
        }
        String fileName = DotnetGenerationService.getClassName(table);
        createPackage(packageName, path);
        createDotnetFile(packageName, path, fileName);
        writeDotnetFile(con, table, path, packageName, fileName);
        
        if( state == true) con.close();
    }
    
}
