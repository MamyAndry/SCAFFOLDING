/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package generator.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Mamisoa
 */
public class FileUtility {    
    
    public static String[] splitLine(String string){
        return string.split("=");
    }
    
    public static List<String[]> readFile(String path) throws Exception{
        File myObj = new File(path);
        Scanner myReader = new Scanner(myObj);
        List<String[]> res = new ArrayList<>();
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            res.add(splitLine(data));
        }
        myReader.close();
        return res;
    }
    public static String readOneFile(String path) throws Exception{
        StringBuilder builder = new StringBuilder();
        BufferedReader reader = new BufferedReader(new FileReader(path));
        String line;
        while((line = reader.readLine()) != null){
            builder.append(line).append("\n");
        }
        return builder.toString();
    }
    
    public static void createFile(String path, String fileName) throws Exception{
        String separator = File.separator;
        path = path + separator + fileName;
        File file = new File(path);
        System.out.println(file);
//        System.out.println(file.getAbsolutePath() + " succesfully created");
    }
    
    public static void writeFile(String path, String body) throws Exception{
        try (FileWriter writer = new FileWriter(path)) {
            writer.write(body);
        }
    }
    
    public static void generateFile(String path, String fileName, String body) throws Exception{
        createFile(path, fileName);
        path = path + File.separator + fileName;
        writeFile(path, body);
    }
    
}
