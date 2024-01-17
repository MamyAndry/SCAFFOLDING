package ambovombe.kombarika.generator.service;

import java.sql.Connection;
import java.util.*;

import ambovombe.kombarika.configuration.main.TypeProperties;
import ambovombe.kombarika.configuration.mapping.LanguageProperties;
import ambovombe.kombarika.configuration.mapping.*;
import ambovombe.kombarika.database.DbConnection;
import ambovombe.kombarika.generator.parser.JsonUtility;
import ambovombe.kombarika.generator.utils.ObjectUtility;

public class GeneratorService {
    public static String getPackage(LanguageProperties languageProperties, String packageName){
        return languageProperties.getPackageSyntax() + " " + packageName + ";\n";
    }

    public  static String getEntityClass(String table, LanguageProperties language, AnnotationProperty annotation){
        String res = "";
        if(!annotation.getEntity().equals(""))
            res += language.getAnnotationSyntax().replace("?", annotation.getEntity())  + "\n";
        res +=  language.getAnnotationSyntax().replace("?", annotation.getTable()).replace("?", table) + "\n"
                + language.getClassSyntax() + " "
                + ObjectUtility.capitalize(ObjectUtility.formatToCamelCase(table));
        return res;
    }

    public static String getConstructor(LanguageProperties languageProperties,String table){
        String res = "";
        res =  "\t"
                + languageProperties.getConstructorSyntax().replace("?", ObjectUtility.capitalize(ObjectUtility.formatToCamelCase(table)));
        return res;
    }
    public static Set<String> getAllImports(HashMap<String, String> columns, TypeMapping type, LanguageProperties language) {
        Set<String> lst = new HashSet<>();
        
        for (Map.Entry<String, String> set : columns.entrySet()) {
            if(type.getListMapping().get(set.getValue()).getPackageName().equals("")) { continue; }
            lst.add(type.getListMapping().get(set.getValue()).getPackageName()+
                    "" + language.getEndOfInstruction()+
                    "\n");
        }
        return lst;
    }

    public static String getImports(HashMap<String, String> columns, TypeMapping type, LanguageProperties language) throws Exception{
        String res = "";
        String imp = language.getImportSyntax();
        Set<String> imports = getAllImports(columns, type, language);
        for (String elem : imports) {
            res += imp + " " + elem;
        }
        return res;
    }

    public static String getEntityImport(HashMap<String, String> columns, TypeMapping typeMapping,LanguageProperties lp, Imports imports) throws Exception{
        String res = "";
        String imp = lp.getImportSyntax();
        for(String item : imports.getEntity()){
            res += imp+ " " + item + "" + lp.getEndOfInstruction() + "\n";
        }
        res += "\n";
        res += getImports(columns, typeMapping, lp);        
        return res;
    }

    public static String generateEntityField(HashMap<String, String> columns, List<String> primaryKeys, TypeMapping typeMapping, LanguageProperties lp, AnnotationProperty annotation){
        String res = "";

        for (Map.Entry<String, String> set : columns.entrySet()) {
            if (primaryKeys.contains(set.getKey())) {
                res += "\t"
                        + lp.getAnnotationSyntax().replace("?", annotation.getConstraints().getPrimaryKey()) + "\n";
            }
            res += "\t"
                    + lp.getAnnotationSyntax().replace("?", annotation.getColumn()).replace("?", set.getKey()) + "\n";

            res += "\t"
                + typeMapping.getListMapping().get(set.getValue()).getType() + " "
                + ObjectUtility.formatToCamelCase(set.getKey())
                + lp.getEndOfInstruction()
                + "\n";
        }
        return res;
    }

    public static String generateEncapsulation(LanguageProperties lp, HashMap<String, String> columns){
        String rep = "";
        for (Map.Entry<String, String> set : columns.entrySet()) {
            rep += lp.getEncapsulation()
            .replace("#type#", set.getValue())
            .replace("#Field#", ObjectUtility.formatToCamelCase(ObjectUtility.capitalize(set.getKey())))
            .replace("#field#", ObjectUtility.formatToCamelCase(set.getKey()));
        }
        return rep;
    }

    public static String generateEntity(
            DbConnection dbConnection,
            String template, String table,
            String packageName,
            LanguageProperties languageProperties,
            FrameworkProperties frameworkProperties,
            TypeMapping typeMapping
    ) throws Exception{
        Connection con = dbConnection.getConnection();
        Imports imports = frameworkProperties.getImports();
        AnnotationProperty annotationProperty = frameworkProperties.getAnnotationProperty();

        return generateEntityString(con, dbConnection, template, table, packageName, languageProperties, typeMapping, imports, annotationProperty);
    }

    public static String generateEntityString(Connection con, DbConnection dbConnection, String template, String table, String packageName, LanguageProperties languageProperties, TypeMapping properties, Imports imports, AnnotationProperty annotationProperty) throws Exception {
        boolean t = con.isClosed();
        HashMap<String, String> columns = DbService.getColumnNameAndType(con, table);
        List<String> primaryKeyColumn = DbService.getPrimaryKey(con, dbConnection, table);
        String res = template.replace("#package#", getPackage(languageProperties, packageName))
                .replace("#imports#", getEntityImport(columns, properties, languageProperties, imports))
                .replace("#class#", getEntityClass(table, languageProperties, annotationProperty))
                .replace("#open-bracket#", languageProperties.getOpenBracket())
                .replace("#close-bracket#", languageProperties.getCloseBracket())
                .replace("#fields#", generateEntityField(columns, primaryKeyColumn, properties, languageProperties, annotationProperty))
                .replace("#constructors#", getConstructor(languageProperties, table))
                .replace("#methods#", "")
                .replace("#encapsulation#", generateEncapsulation(languageProperties, columns));
        return res;
    }

    public static String getFileName(String table, LanguageProperties languageProperties){
        return ObjectUtility.capitalize(ObjectUtility.formatToCamelCase(table)).concat("." + languageProperties.getExtension());
    }

}
