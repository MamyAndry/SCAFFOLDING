/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package generator.service;

import generator.mapping.ClassMapping;
import generator.parser.FileParser;
import generator.utils.ObjectUtility;
import java.io.File;
import java.util.HashMap;
import java.util.List;

/**
  
 * @author Mamisoa
 */
public class Generator {
    private String importSyntax;
    private String packageSyntax;
    private String annotationSyntax;
    private String extension;
    private HashMap<String, String> types;

    
//SETTERS & GETTERS
    public String getImportSyntax() {
        return importSyntax;
    }

    public void setImportSyntax(String importSyntax) {
        this.importSyntax = importSyntax;
    }

    public String getPackageSyntax() {
        return packageSyntax;
    }

    public void setPackageSyntax(String packageSyntax) {
        this.packageSyntax = packageSyntax;
    }

    public String getAnnotationSyntax() {
        return annotationSyntax;
    }

    public void setAnnotationSyntax(String annotationSyntax) {
        this.annotationSyntax = annotationSyntax;
    }

    public HashMap<String, String> getTypes() {
        return types;
    }

    public void setTypes(HashMap<String, String> types) {
        this.types = types;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }
    

//CONSTRUCTORS
    public Generator() throws Exception{
        readFile();
    }

//METHODS
    public void readFile() throws Exception {
        String separator = File.separator;
        String confFile = System.getProperty("user.dir") + separator +"details.conf";
        List<String[]> lst = FileParser.readFile(confFile);
        for(String[] elt : lst ){
            if(elt[0].equals("language")) {
                HashMap<String, String> type = ClassMapping.getTypeMapping().get(elt[1].toLowerCase());
                this.setTypes(type);
            }
            else if (elt[0].equals("import_syntax")) this.setImportSyntax(elt[1]);
            else if (elt[0].equals("package_syntax")) this.setPackageSyntax(elt[1]);
            else if (elt[0].equals("annotation_syntax")) this.setAnnotationSyntax(elt[1]);
            else if (elt[0].equals("extension")) this.setExtension(elt[1]);
        }
    }
    
    public String getPackage(String packageName){
        return  this.getPackageSyntax() + " " + packageName + ";";
    }
    
    public String getClassName(String table){
        return ObjectUtility.capitalize(DbService.formatString(table));
    }
}
