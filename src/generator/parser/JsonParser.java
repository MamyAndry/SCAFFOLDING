/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package generator.parser;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import java.io.File;
import java.io.FileReader;

/**
 *
 * @author Mamisoa
 */
public class JsonParser {
    
    public static <T> T parseJson(String path, Object object) throws Exception{
        JsonReader reader = new JsonReader(new FileReader(new File(path)));
//        System.out.println(reader);
        System.out.println("DXJHB?<");
        Object temp = new Gson().fromJson(reader, object.getClass());
        System.out.println(temp);
        return (T)temp;
    }
}
