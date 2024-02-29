package ambovombe.kombarika.configuration.mapping;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

/**
 *
 * @author Mamisoa
 */
@Setter @Getter
public class LanguageProperties {
    //GETTERS & SETTERS
    private String name;
    private String importSyntax;
    private String packageSyntax;
    private String openBracket;
    private String closeBracket;
    private String annotationSyntax;
    private String encapsulation;
    private String extension;
    private String classSyntax;
    private String fieldSyntax;
    private String methodSyntax;
    private String endOfInstruction;
    private String constructorSyntax;
    private HashMap<String, FrameworkProperties> frameworks;

    //CONSTRUCTOR
    public LanguageProperties(){}

}
