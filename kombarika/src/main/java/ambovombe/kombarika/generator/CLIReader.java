/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ambovombe.kombarika.generator;

/**
 *
 * @author Mamisoa
 */
public class CLIReader {
    String path;
    String packageName;
    String lang;
    String table;

    //GETTERS AND SETTERS
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }
    
    //METHODS
    public void getData(String[] str) throws Exception{
        if(!str[0].equals("generate"))
            throw new Exception("Wrong syntax");
        else if(str[0].equals("exit"))
            throw new Exception("Farewell");
        for(int i = 0; i < str.length; i++){
            if(str[i].equals("-p")){
                this.setPackageName(str[i+1]);
            }
            if(str[i].equals("-t")){
                this.setTable(str[i+1]);
            }
            if(str[i].equals("-path")){
                this.setPath(str[i+1]);
            }
            if(str[i].equals("-l")){
                this.setLang(str[i+1].toLowerCase());
            }
        }
    }
}

