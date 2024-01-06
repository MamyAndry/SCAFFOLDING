/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package generator.parser;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 *
 * @author Mamisoa
 */
public class JsonParser {
    
    public static <T> T parseJson(String path, Class<?> object) throws Exception{
        File file = new File(path);
        JsonReader reader = new JsonReader(new BufferedReader(new FileReader(path)));
        Object temp = new Gson().fromJson(reader, object);
        return (T)temp;
    }
}
