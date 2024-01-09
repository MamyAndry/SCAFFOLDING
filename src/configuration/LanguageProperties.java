package configuration;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author Mamisoa
 */
public class LanguageProperties {
    private String importSyntax;
    private String packageSyntax;
    private String annotationSyntax;
    private String encapsulation;
    private String extension;

    //GETTERS & SETTERS
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

    public String getEncapsulation() {
        return encapsulation;
    }

    public void setEncapsulation(String encapsulation) {
        this.encapsulation = encapsulation;
    }
    

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }
    
    //CONSTRUCTOR
    public LanguageProperties(){} 

}
