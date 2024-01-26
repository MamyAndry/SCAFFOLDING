package ambovombe.kombarika.generator.service.entity;

import java.sql.Connection;
import java.util.*;

import ambovombe.kombarika.configuration.mapping.*;
import ambovombe.kombarika.database.DbConnection;
import ambovombe.kombarika.generator.service.DbService;
import ambovombe.kombarika.generator.service.GeneratorService;
import ambovombe.kombarika.generator.utils.ObjectUtility;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Entity {
    LanguageProperties languageProperties;
    AnnotationProperty annotationProperty; 
    TypeMapping typeMapping;
    Imports imports;

    public  String getEntityClass(String table){
        String res = "";
        if(!this.getAnnotationProperty().getEntity().equals(""))
            res += this.getLanguageProperties().getAnnotationSyntax().replace("?", this.getAnnotationProperty().getEntity())  + "\n";
        res +=  this.getLanguageProperties().getAnnotationSyntax().replace("?", this.getAnnotationProperty().getTable()).replace("?", table) + "\n"
                + this.getLanguageProperties().getClassSyntax() + " "
                + ObjectUtility.capitalize(ObjectUtility.formatToCamelCase(table));
        return res;
    }

    public String getConstructor(String table){
        String res = "";
        res =  "\t"
                + this.getLanguageProperties().getConstructorSyntax().replace("?", ObjectUtility.capitalize(ObjectUtility.formatToCamelCase(table)));
        return res;
    }
    
    public Set<String> getAllImports(HashMap<String, String> columns) {
        Set<String> lst = new HashSet<>();
        for (Map.Entry<String, String> set : columns.entrySet()) {
            if(this.getTypeMapping().getListMapping().get(set.getValue()).getPackageName().equals("")) { continue; }
            lst.add(this.getTypeMapping().getListMapping().get(set.getValue()).getPackageName()+
                    "" + this.getLanguageProperties().getEndOfInstruction()+
                    "\n");
        }
        return lst;
    }

    public String getImports(HashMap<String, String> columns) throws Exception{
        String res = "";
        String imp = this.getLanguageProperties().getImportSyntax();
        Set<String> imports = getAllImports(columns);
        for (String elem : imports) {
            res += imp + " " + elem;
        }
        return res;
    }

    public String getEntityImport(HashMap<String, String> columns) throws Exception{
        String res = "";
        String imp = this.getLanguageProperties().getImportSyntax();
        for(String item : this.getImports().getEntity()){
            res += imp+ " " + item + "" + this.getLanguageProperties().getEndOfInstruction() + "\n";
        }
        res += "\n";
        res += getImports(columns);        
        return res;
    }

    public String getEntityField(HashMap<String, String> columns, List<String> primaryKeys){
        String res = "";

        for (Map.Entry<String, String> set : columns.entrySet()) {
            if (primaryKeys.contains(set.getKey())) {
                res += "\t"
                        + this.getLanguageProperties().getAnnotationSyntax().replace("?", this.getAnnotationProperty().getConstraints().getPrimaryKey()) + "\n";
                if(set.getValue().equals("Integer") && !this.getAnnotationProperty().getAutoIncrement().equals("")){
                    if (primaryKeys.contains(set.getKey())) {
                        res += "\t"
                                + this.getLanguageProperties().getAnnotationSyntax().replace("?", this.getAnnotationProperty().getAutoIncrement()) + "\n";
                    }
                }
            }
            res += "\t"
                    + this.getLanguageProperties().getAnnotationSyntax().replace("?", this.getAnnotationProperty().getColumn()).replace("?", set.getKey()) + "\n";

            res += "\t"
                + typeMapping.getListMapping().get(set.getValue()).getType() + " "
                + ObjectUtility.formatToCamelCase(set.getKey())
                + this.getLanguageProperties().getEndOfInstruction()
                + "\n";
        }
        return res;
    }

    public String getEncapsulation(HashMap<String, String> columns){
        String rep = "";
        for (Map.Entry<String, String> set : columns.entrySet()) {
            rep += this.getLanguageProperties().getEncapsulation()
            .replace("#type#", set.getValue())
            .replace("#Field#", ObjectUtility.formatToCamelCase(ObjectUtility.capitalize(set.getKey())))
            .replace("#field#", ObjectUtility.formatToCamelCase(set.getKey()));
        }
        return rep;
    }

    public String generateEntity(DbConnection dbConnection, String template, String table, String packageName) throws Exception {
        HashMap<String, String> columns = DbService.getColumnNameAndType(dbConnection.getConnection(), table);
        List<String> primaryKeyColumn = DbService.getPrimaryKey(dbConnection, table);
        String res = template.replace("#package#", GeneratorService.getPackage(this.getLanguageProperties(), packageName))
                .replace("#imports#", getEntityImport(columns))
                .replace("#class#", getEntityClass(table))
                .replace("#open-bracket#", this.getLanguageProperties().getOpenBracket())
                .replace("#close-bracket#", this.getLanguageProperties().getCloseBracket())
                .replace("#fields#", getEntityField(columns, primaryKeyColumn))
                .replace("#constructors#", getConstructor(table))
                .replace("#methods#", "")
                .replace("#encapsulation#", getEncapsulation(columns));
        return res;
    }

    public String getFileName(String table){
        return ObjectUtility.capitalize(ObjectUtility.formatToCamelCase(table)).concat("." + this.getLanguageProperties().getExtension());
    }

}
