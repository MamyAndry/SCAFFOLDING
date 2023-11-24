/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package generator.parser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Mamisoa
 */
public class FileParser {    
    
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
}
