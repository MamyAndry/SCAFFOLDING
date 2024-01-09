package generator.service;

import java.util.HashMap;
import java.util.Map;

import configuration.*;

public class GeneratorService {
    LanguageDetails languageDetails;
    ImportList importList;
    TypeProperties typeProperties;

    public String generatePackage(LanguageProperties languageProperties, String packageName){
        return languageProperties.getPackageSyntax() + " " + packageName + ";\n";
    }

    public String getAllImport(LanguageProperties lp, Imports imports, HashMap<String, String> columns, TypeMapping typeMapping){
        String res = "";
        String imp = lp.getImportSyntax();
        for(String item : imports.getEntity()){
            res += imp+ " " +item+";\n";
        }

        for (Map.Entry<String, String> set : columns.entrySet()) {
            res += imp+ " " +typeMapping.getListMapping().get(set.getValue()).getPackageName()+";\n";
        }

        return res;
    }

    public String getClasse(LanguageProperties lp, String table){
        String rep = lp.getAnnotationSyntax().replace("?", "Table")+"(name = " + "\"" + table + "\")\n";
        rep += "public class " + table;
        return rep;
    }

    public String generateGet(){
        return "";
    }

    public String generate(String template, String language, String table) {

        return template;
    }
}
