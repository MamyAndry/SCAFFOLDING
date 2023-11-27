/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package generator;

import generator.dao.DbConnection;
import java.sql.Connection;
import generator.service.DbService;
import generator.service.DotnetGenerationService;
import generator.service.JavaGenerationService;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
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
        System.out.println(directoryPath.toString() + " created");
    }
    
    public static String getTemplate(InputStream path) throws Exception{
        StringBuilder builder = new StringBuilder();
        InputStreamReader fis = new InputStreamReader(path);
        BufferedReader reader = new BufferedReader(fis);
        String line;
        while((line = reader.readLine()) != null){
            builder.append(line).append("\n");
        }
        return builder.toString();
    }
    
    public static String getTemplate(String path) throws Exception{
        StringBuilder builder = new StringBuilder();
        BufferedReader reader = new BufferedReader(new FileReader(path));
        String line;
        while((line = reader.readLine()) != null){
            builder.append(line).append("\n");
        }
        return builder.toString();
    }
    
    public static void createFile(String packageName, String path, String fileName, String extension) throws Exception{
        String separator = "\\";
        if(System.getProperty("os.name").equals("Linux"))
            separator = "/";

        path = path + separator + packageName + separator + fileName + "." + extension;
        File file = new File(path);
        System.out.println(file.getAbsolutePath() + " succesfully created");
    }
    
    public static void writeFile(Connection con, String table, String path, String packageName, String fileName, String extension) throws Exception{
        String separator = File.separator;
        path = path + separator + packageName + separator + fileName + "." + extension;
        FileWriter writer = new FileWriter(path);
        HashMap<String, String> mapp = DbService.getColumnNameAndType(con, table);
        
        String template = getTemplate(CodeGenerator.class.getResourceAsStream("/Template.code"));
//        String template = getTemplate("Template.code");
        if(extension.equals("java")){
            template = JavaGenerationService.generate(template, packageName, mapp, table);
        }else if(extension.equals("cs")){
            template = DotnetGenerationService.generate(template, packageName, mapp, table);
        }
        
        writer.write(template);
        writer.close();
    }
    
    public static void generateSource(Connection con, String path, String table, String packageName, String extension) throws Exception{   
        boolean state = false;
        if(con == null){
            con = new DbConnection().connect();
            state = true;
        }
        String fileName = JavaGenerationService.getClassName(table);
        createFile(packageName, path, fileName, extension);
        writeFile(con, table, path, packageName, fileName, extension);
        if( state == true) con.close();
    }
}
