package com.ambovombe.generator.service.controller;

import com.ambovombe.configuration.mapping.LanguageProperties;
import com.ambovombe.configuration.mapping.*;
import com.ambovombe.generator.service.GeneratorService;
import com.ambovombe.generator.utils.ObjectUtility;
import com.ambovombe.utils.Misc;

public class ControllerService {
    /**
     * Generate the function that make the insert to the database
     * @param table
     * @param language
     * @param method
     * @param controllerProperty
     * @return the string form of the function
     */
    public static String save(String table, LanguageProperties language, CrudMethod method, ControllerProperty controllerProperty){
        String body = "";
        String args = "";
        args += language.getAnnotationSyntax().replace("?", controllerProperty.getAnnotationArgumentParameterFormData()) + " "
                + ObjectUtility.capitalize(ObjectUtility.formatToCamelCase(table)) + " "
                + ObjectUtility.formatToCamelCase(table);
        System.out.println(body);
        body += method.getSave().replace("#object#", ObjectUtility.formatToCamelCase(table));
        String function =  language.getMethodSyntax()
                .replace("#name#", "save")
                .replace("#type#", controllerProperty.getReturnType().replace("?", ObjectUtility.capitalize(ObjectUtility.formatToCamelCase(table))))
                .replace("#arg#", args)
                .replace("#body#", body);
        return Misc.tabulate(language.getAnnotationSyntax().replace("?", controllerProperty.getPut()) + "\n" + function);
    }
    public static String save(String table, LanguageProperties language,  FrameworkProperties frameworkProperties){
        String body = "";
        String args = "";
        args += language.getAnnotationSyntax().replace("?", controllerProperty.getAnnotationArgumentParameterFormData()) + " "
                + ObjectUtility.capitalize(ObjectUtility.formatToCamelCase(table)) + " "
                + ObjectUtility.formatToCamelCase(table);
        System.out.println(body);
        body += frameworkProperties.getCrudMethods().getSave().replace("#object#", ObjectUtility.formatToCamelCase(table));
        String function =  language.getMethodSyntax()
                .replace("#name#", "save")
                .replace("#type#", frameworkProperties.getControllerProperty().getReturnType().replace("?", ObjectUtility.capitalize(ObjectUtility.formatToCamelCase(table))))
                .replace("#arg#", args)
                .replace("#body#", body);
        return Misc.tabulate(language.getAnnotationSyntax().replace("?", controllerProperty.getPut()) + "\n" + function);
    }

    public static String findAll(String table, LanguageProperties language,  CrudMethod method, ControllerProperty controllerProperty){
        String body = "";
        body += method.getFindAll().replace("#object#", ObjectUtility.formatToCamelCase(table));
        String function =  language.getMethodSyntax()
                .replace("#name#", "findAll")
                .replace("#type#", controllerProperty.getReturnType().replace("?", language.getListSyntax().replace("?",ObjectUtility.capitalize(ObjectUtility.formatToCamelCase(table)))))
                .replace("#arg#", "")
                .replace("#body#", body);
        return Misc.tabulate(language.getAnnotationSyntax().replace("?", controllerProperty.getGet()) + "\n" + function);
    }
    public static String findAll(String table, LanguageProperties language, FrameworkProperties frameworkProperties){
        String body = "";
        body += frameworkProperties.getCrudMethods().getFindAll().replace("#object#", ObjectUtility.formatToCamelCase(table));
        String function =  language.getMethodSyntax()
                .replace("#name#", "findAll")
                .replace("#type#", frameworkProperties.getControllerProperty().getReturnType().replace("?", language.getListSyntax().replace("?",ObjectUtility.capitalize(ObjectUtility.formatToCamelCase(table)))))
                .replace("#arg#", "")
                .replace("#body#", body);
        return Misc.tabulate(language.getAnnotationSyntax().replace("?", controllerProperty.getGet()) + "\n" + function);
    }

    public static String getCrudMethods(String table, LanguageProperties language,  CrudMethod method, ControllerProperty controllerProperty){
        StringBuilder stringBuilder = new StringBuilder();
        String save = save(table, language,  method, controllerProperty);
        String findAll = findAll(table, language,  method, controllerProperty);
        stringBuilder.append(findAll);
        stringBuilder.append("\n");
        stringBuilder.append(save);
        return stringBuilder.toString();
    }

    public static String getCrudMethods(String table, LanguageProperties language, FrameworkDetails frameworkDetails){
        StringBuilder stringBuilder = new StringBuilder();
        String save = save(table, language, frameworkDetails);
        String findAll = findAll(table, language, frameworkDetails);
        stringBuilder.append(findAll);
        stringBuilder.append("\n");
        stringBuilder.append(save);
        return stringBuilder.toString();
    }

    public static String getControllerField(String table, LanguageProperties languageProperties, ControllerProperty controllerProperty){
        String res = "";
        if(!controllerProperty.getField().equals(""))
            res += "\t"
                    + languageProperties.getAnnotationSyntax().replace("?", controllerProperty.getAnnotationField()) + "\n"
                    + "\t" + controllerProperty.getField().replace("?", ObjectUtility.capitalize(ObjectUtility.formatToCamelCase(table))) + "\n"
                    ;
        return res;
    }

    public  static String getControllerClass(String table, LanguageProperties language, AnnotationProperty annotations, ControllerProperty controllerProperty){
        String res = "";
        res += language.getAnnotationSyntax()
                .replace("?", annotations.getController()) + "\n"
                + language.getAnnotationSyntax()
                .replace("?", controllerProperty.getPath())
                .replace("?", ObjectUtility.formatToCamelCase(table)) + "\n"
                + language.getClassSyntax() + " "
                + ObjectUtility.capitalize(ObjectUtility.formatToCamelCase(table).concat("Controller")) + "\n";
        return res;

    }
    public  static String getControllerClass(String table, LanguageProperties language, FrameworkProperties frameworkProperties){
        String res = "";
        res += language.getAnnotationSyntax()
                .replace("?", frameworkProperties.getAnnotationProperty().getController()) + "\n"
                + language.getAnnotationSyntax()
                .replace("?", frameworkProperties.getControllerProperty().getPath())
                .replace("?", ObjectUtility.formatToCamelCase(table)) + "\n"
                + language.getClassSyntax() + " "
                + ObjectUtility.capitalize(ObjectUtility.formatToCamelCase(table).concat("Controller")) + "\n";
        return res;

    }

    public static String getControllerImport(LanguageProperties lp, Imports imports) throws Exception{
        String res = "";
        String imp = lp.getImportSyntax();
        for(String item : imports.getController())
            res += imp+ " " + item + "" + lp.getEndOfInstruction() + "\n";
        return res;
    }
    public static String generateController(
            CrudMethod crudMethod,
            String template,
            String table,
            String packageName,
            ControllerProperty property,
            LanguageProperties languageProperties,
            Imports imports,
            AnnotationProperty annotationProperty
    ) throws Exception {
        String res = template.replace("#package#", GeneratorService.getPackage(languageProperties, packageName))
                .replace("#imports#", getControllerImport(languageProperties, imports))
                .replace("#class#", getControllerClass(table, languageProperties, annotationProperty, property))
                .replace("#fields#", getControllerField(table, languageProperties, property))
                .replace("#constructors#", "")
                .replace("#methods#", getCrudMethods(table, languageProperties, crudMethod, property))
                .replace("#encapsulation#", "");

        return res;
    }
    public static String generateController(
            CrudMethod crudMethod,
            String template,
            String table,
            String packageName,
            FrameworkDetails frameworkDetails
    ) throws Exception {
        String res = template.replace("#package#", GeneratorService.getPackage(languageProperties, packageName))
                .replace("#imports#", getControllerImport(languageProperties, imports))
                .replace("#class#", getControllerClass(table, languageProperties, frameworkDetails.getAnnotationProperty(), property))
                .replace("#fields#", getControllerField(table, languageProperties, property))
                .replace("#constructors#", "")
                .replace("#methods#", getCrudMethods(table, languageProperties, frameworkDetails))
                .replace("#encapsulation#", "");

        return res;
    }
}
