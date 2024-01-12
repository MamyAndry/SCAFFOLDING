package com.ambovombe.generator.service.controller;

import com.ambovombe.configuration.mapping.LanguageProperties;
import com.ambovombe.configuration.mapping.*;
import com.ambovombe.generator.service.GeneratorService;
import com.ambovombe.generator.utils.ObjectUtility;
import com.ambovombe.utils.Misc;

public class RepositoryService {

    public static String getRepositoryImport(LanguageProperties lp, FrameworkProperties property, String packageName, String table) throws Exception{
        String res = "";
        String imp = lp.getImportSyntax();
        for(String item : property.getImports().getRepository())
            res += imp+ " " + item
            .replace("packageName", packageName)
            .replace("className", ObjectUtility.capitalize(ObjectUtility.formatToCamelCase(table))) 
            + "" + lp.getEndOfInstruction() + "\n";
        return res;
    }
    
    public static String getRepositoryClass(String table, FrameworkProperties property) throws Exception{
        String res = "";
        res += property.getRepositoryProperty().getClassSyntax().replace("?", ObjectUtility.capitalize(ObjectUtility.formatToCamelCase(table)))
             + "\n";
        return res;
    }

    public static String generateRepository(
        String table, 
        LanguageProperties languageProperties, 
        FrameworkProperties property,
        String packageName,
        String entityPackage
    ) throws Exception{
        String res = "";
        if(property.getRepository().equals(""))
            return res;
        String path = Misc.getSourceTemplateLocation().concat(File.separator).concat(property.getRepository());
        String template = FileUtility.readOneFile(path);

        String res = template.replace("#package#", GeneratorService.getPackage(languageProperties, packageName))
                .replace("#imports#", getRepositoryImport(languageProperties, property, entityPackage, table))
                .replace("#class#", getRepositoryClass(table, property))
                .replace("#fields#", "")
                .replace("#constructors#", "")
                .replace("#methods#", "")
                .replace("#encapsulation#", "");
        return res;
    }
}