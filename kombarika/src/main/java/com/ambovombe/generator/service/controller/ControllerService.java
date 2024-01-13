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
        body += Misc.tabulate(method.getSave().replace("#object#", ObjectUtility.formatToCamelCase(table)));
        String function =  language.getMethodSyntax()
                .replace("#name#", "save")
                .replace("#type#", controllerProperty.getReturnType().replace("?", ObjectUtility.capitalize(ObjectUtility.formatToCamelCase(table))))
                .replace("#arg#", args)
                .replace("#body#", body);
        return Misc.tabulate(language.getAnnotationSyntax().replace("?", controllerProperty.getPut()) + "\n" + function);
    }

    public static String update(String table, LanguageProperties language, CrudMethod method, ControllerProperty controllerProperty) throws Exception{
        String body = "";
        String args = "";
        args += language.getAnnotationSyntax().replace("?", controllerProperty.getAnnotationArgumentParameterFormData()) + " "
                + ObjectUtility.capitalize(ObjectUtility.formatToCamelCase(table)) + " "
                + ObjectUtility.formatToCamelCase(table);
        body += Misc.tabulate(method.getUpdate().replace("#object#", ObjectUtility.formatToCamelCase(table)));
        String function =  language.getMethodSyntax()
                .replace("#name#", "update")
                .replace("#type#", controllerProperty.getReturnType().replace("?", ObjectUtility.capitalize(ObjectUtility.formatToCamelCase(table))))
                .replace("#arg#", args)
                .replace("#body#", body);
        return Misc.tabulate(language.getAnnotationSyntax().replace("?", controllerProperty.getPut()) + "\n" + function);
    }
    public static String delete(String table, LanguageProperties language, CrudMethod method, ControllerProperty controllerProperty) throws Exception{
        String body = "";
        String args = "";
        args += language.getAnnotationSyntax().replace("?", controllerProperty.getAnnotationArgumentParameterFormData()) + " "
                + ObjectUtility.capitalize(ObjectUtility.formatToCamelCase(table)) + " "
                + ObjectUtility.formatToCamelCase(table);
        body += Misc.tabulate(method.getDelete().replace("#object#", ObjectUtility.formatToCamelCase(table)));
        String function =  language.getMethodSyntax()
                .replace("#name#", "delete")
                .replace("#type#", controllerProperty.getReturnType().replace("?", ObjectUtility.capitalize(ObjectUtility.formatToCamelCase(table))))
                .replace("#arg#", args)
                .replace("#body#", body);
        return Misc.tabulate(language.getAnnotationSyntax().replace("?", controllerProperty.getPut()) + "\n" + function);
    }
    public static String findAll(String table, LanguageProperties language,  CrudMethod method, ControllerProperty controllerProperty){
        String body = "";
        body += "\t" + method.getFindAll().replace("#object#", ObjectUtility.formatToCamelCase(table));
        String function =  language.getMethodSyntax()
                .replace("#name#", "findAll")
                .replace("#type#", controllerProperty.getReturnType().replace("?", language.getListSyntax().replace("?",ObjectUtility.capitalize(ObjectUtility.formatToCamelCase(table)))))
                .replace("#arg#", "")
                .replace("#body#", body);
        return Misc.tabulate(language.getAnnotationSyntax().replace("?", controllerProperty.getGet()) + "\n" + function);
    }

    public static String findById(String table, LanguageProperties language, CrudMethod method, ControllerProperty controllerProperty) throws Exception{
        String res = "";
        return res;
    }
    public static String getCrudMethods(String table, LanguageProperties language,  CrudMethod method, ControllerProperty controllerProperty) throws Exception {
        StringBuilder stringBuilder = new StringBuilder();
        String save = save(table, language,  method, controllerProperty);
        String findAll = findAll(table, language,  method, controllerProperty);
        String update = update(table, language,  method, controllerProperty);
        String delete = delete(table, language,  method, controllerProperty);
        stringBuilder.append(save);
        stringBuilder.append("\n");
        stringBuilder.append(update);
        stringBuilder.append("\n");
        stringBuilder.append(delete);
        stringBuilder.append("\n");
        stringBuilder.append(findAll);
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
}
