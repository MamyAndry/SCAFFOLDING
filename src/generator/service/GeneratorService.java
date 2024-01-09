package generator.service;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import configuration.*;
import generator.utils.ObjectUtility;

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

    public String generateGetSet(LanguageProperties lp, HashMap<String, String> columns){
        String rep = "";
        for (Map.Entry<String, String> set : columns.entrySet()) {
            rep += lp.getEncapsulation()
            .replace("type", set.getValue())
            .replace("#Field#", ObjectUtility.capitalize(set.getKey()))
            .replace("#field#", set.getKey());
        }
        return rep;
    }

    public String generate(Connection con, String template, String language, String table) throws Exception {
        LanguageDetails languageDetails = new LanguageDetails();
        TypeProperties properties = new TypeProperties();
        HashMap<String, String> columns = DbService.getColumnNameAndType(con, table);
        return generateGetSet(languageDetails.getLanguages().get(language), columns);
    }
}
