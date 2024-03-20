package ambovombe.kombarika.generator.service.controller;

import ambovombe.kombarika.database.DbConnection;

import java.util.HashMap;
import java.util.Map;

import ambovombe.kombarika.configuration.mapping.*;
import ambovombe.kombarika.generator.service.DbService;
import ambovombe.kombarika.generator.service.GeneratorService;
import ambovombe.kombarika.generator.utils.ObjectUtility;
import ambovombe.kombarika.utils.Misc;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Controller{
    LanguageProperties languageProperties;
    FrameworkProperties frameworkProperties;
    CrudMethod crudMethod;
    ControllerProperty controllerProperty;
    AnnotationProperty annotationProperty;
    DbConnection dbConnection;
    Imports imports;

    /**
     * Generate the function that make the insert to the database
     * @param table
     * @param language
     * @param method
     * @param controllerProperty
     * @return the string form of the function
     */
    public String save(String table){
        String body = "";
        String args = "";
        args += this.getLanguageProperties().getAnnotationSyntax().replace("?", this.getControllerProperty().getAnnotationArgumentParameterFormData()) + " "
                + ObjectUtility.capitalize(ObjectUtility.formatToCamelCase(table)) + " "
                + ObjectUtility.formatToCamelCase(table);
        body += Misc.tabulate(this.getCrudMethod().getSave()
            .replace("?", ObjectUtility.formatToCamelCase(table))
            .replace("#object#", ObjectUtility.formatToCamelCase(table))
            .replace("?", ObjectUtility.capitalize(ObjectUtility.formatToCamelCase(table))));      
        String function =  this.getLanguageProperties().getMethodSyntax()
                .replace("#name#", "save")
                .replace("#type#", this.getControllerProperty().getReturnType().replace("?", ObjectUtility.capitalize(ObjectUtility.formatToCamelCase(table))))
                .replace("#arg#", args)
                .replace("#body#", body);
        return Misc.tabulate(this.getLanguageProperties().getAnnotationSyntax().replace("?", 
            this.getControllerProperty().getPost()
            .replace("?", ObjectUtility.formatToCamelCase(table))
        ) + "\n" + function);
    }

    public String update(String table) throws Exception{
        String body = "";
        String args = "";
        args += this.getLanguageProperties().getAnnotationSyntax().replace("?", this.getControllerProperty().getAnnotationArgumentParameterFormData()) + " "
                + ObjectUtility.capitalize(ObjectUtility.formatToCamelCase(table)) + " "
                + ObjectUtility.formatToCamelCase(table);
        body += Misc.tabulate(this.getCrudMethod().getUpdate()
            .replace("#object#", ObjectUtility.formatToCamelCase(table))
            .replace("?", ObjectUtility.capitalize(ObjectUtility.formatToCamelCase(table))));
        String function =  this.getLanguageProperties().getMethodSyntax()
                .replace("#name#", "update")
                .replace("#type#", this.getControllerProperty().getReturnType().replace("?", ObjectUtility.capitalize(ObjectUtility.formatToCamelCase(table))))
                .replace("#arg#", args)
                .replace("#body#", body);
        return Misc.tabulate(this.getLanguageProperties().getAnnotationSyntax().replace("?", 
            this.getControllerProperty().getPut()
            .replace("?", ObjectUtility.formatToCamelCase(table))
        ) + "\n" + function);
    }

    public String delete(String table) throws Exception{
        String body = "";   
        String args = "";
        args += this.getLanguageProperties().getAnnotationSyntax().replace("?", this.getControllerProperty().getAnnotationArgumentParameterFormData()) + " "
                + ObjectUtility.capitalize(ObjectUtility.formatToCamelCase(table)) + " "
                + ObjectUtility.formatToCamelCase(table);
        body += Misc.tabulate(this.getCrudMethod().getDelete()
        .replace("#object#", ObjectUtility.formatToCamelCase(table))
        .replace("?", ObjectUtility.capitalize(ObjectUtility.formatToCamelCase(table))));
        String function =  this.getLanguageProperties().getMethodSyntax()
        .replace("#name#", "delete")
        .replace("#type#", this.getControllerProperty().getReturnTypeDelete().replace("?", ObjectUtility.capitalize(ObjectUtility.formatToCamelCase(table))))
        .replace("#arg#", args)
        .replace("#body#", body);
        return Misc.tabulate(this.getLanguageProperties().getAnnotationSyntax().replace("?", 
        this.getControllerProperty().getDelete()
            .replace("?", ObjectUtility.formatToCamelCase(table))
        ) + "\n" + function);
    }

    public String getIncludedTerms(HashMap<String, String> columns, HashMap<String, String> foreignKeys){
        String res = "";
        if(foreignKeys.size() > 0){
            String values = "";
            for (Map.Entry<String,String> set: columns.entrySet()) {
                String temp = foreignKeys.get(set.getKey());
                if(temp != null){
                    values += ObjectUtility.formatToCamelCase(temp) + ".";
                }
            }
            values = values.substring(0, values.lastIndexOf('.'));
            res += this.getControllerProperty().getIncludedTerms().replace("#values#", values);
        }
        return res;
    }

    public String findAll(String table, HashMap<String, String> columns, HashMap<String, String> foreignKeys){
        String body = "";
        body += Misc.tabulate(this.getCrudMethod().getFindAll()
            .replace("#object#", ObjectUtility.formatToCamelCase(table))
            .replace("#between#", getIncludedTerms(columns, foreignKeys))
            .replace("?", ObjectUtility.capitalize(ObjectUtility.formatToCamelCase(table))));

        String function =  this.getLanguageProperties().getMethodSyntax()
                .replace("#name#", "findAll")
                .replace("#type#", this.getControllerProperty().getFindAllAsync()
                    .replace("?", this.getControllerProperty().getReturnType().replace("?", this.getFrameworkProperties().getListSyntax().replace("?",ObjectUtility.capitalize(ObjectUtility.formatToCamelCase(table))))))
                .replace("#arg#", "")
                .replace("#body#", body);

        return Misc.tabulate(this.getLanguageProperties().getAnnotationSyntax().replace("?", 
            this.getControllerProperty().getGet()
                .replace("?", ObjectUtility.formatToCamelCase(table))
            ) + "\n" + function);
    }

    public String findById(String table) throws Exception{
        String res = "";
        return res;
    }
    public String getCrudMethods(String table) throws Exception {
        StringBuilder stringBuilder = new StringBuilder();
        HashMap<String, String> columns = DbService.getDetailsColumn(this.getDbConnection().getConnection(), table);
        HashMap<String, String> foreignKeys = DbService.getForeignKeys(this.getDbConnection(), table);
        String save = save(table);
        String findAll = findAll(table, columns, foreignKeys);
        String update = update(table);
        String delete = delete(table);
        stringBuilder.append(save);
        stringBuilder.append("\n");
        stringBuilder.append(update);
        stringBuilder.append("\n");
        stringBuilder.append(delete);
        stringBuilder.append("\n");
        stringBuilder.append(findAll);
        return stringBuilder.toString();
    }

    public String getControllerField(String table, String repository){
        String res = "";
        if(!this.getControllerProperty().getField().equals("") && !this.getControllerProperty().getAnnotationField().equals("")){
            res += "\t"
                    + this.getLanguageProperties().getAnnotationSyntax().replace("?", this.getControllerProperty().getAnnotationField()) + "\n"
                    + "\t" 
                    + this.getControllerProperty().getField()
                        .replace("?", ObjectUtility.capitalize(ObjectUtility.formatToCamelCase(table)))
                        .replace("#name#", ObjectUtility.capitalize(ObjectUtility.formatToCamelCase(repository)))
                    + "\n";
        }else if (!this.getControllerProperty().getField().equals("")){
            res += "\t" + this.getControllerProperty().getField()
                    .replace("?", ObjectUtility.capitalize(ObjectUtility.formatToCamelCase(table)))
                    .replace("#name#", ObjectUtility.capitalize(ObjectUtility.formatToCamelCase(repository)))
                + "\n";
        }
        return res;
    }
    

    public String getControllerClass(String table, String framework){
        String res = "";
        if(!this.getAnnotationProperty().getController().equals("")){
            res += this.getLanguageProperties().getAnnotationSyntax()
                .replace("?", this.getAnnotationProperty().getController()) + "\n";
        }
        if(!this.getControllerProperty().getPath().equals("")){
            res += this.getLanguageProperties().getAnnotationSyntax()
                .replace("?", this.getControllerProperty().getPath().replace("?", ObjectUtility.formatToCamelCase(table)));
        }
        res = res.replace("?", ObjectUtility.formatToCamelCase(table)) + "\n"
            + this.getLanguageProperties().getClassSyntax() + " "
            + ObjectUtility.capitalize(ObjectUtility.formatToCamelCase(
                this.getLanguageProperties().getFrameworks().get(framework).getControllerProperty().getClassSyntax()).replace("?", ObjectUtility.formatToCamelCase(table))
            );
        return res;
    }

    public String getControllerImport(String repository, String entity, String table) throws Exception{
        String res = "";
        String imp = this.getLanguageProperties().getImportSyntax();
        for(String item : this.getImports().getController()){
            item = item
            .replace("packageName", repository)
            .replace("className", ObjectUtility.capitalize(ObjectUtility.formatToCamelCase(table)))
            .replace("entity", entity);
            res += imp+ " " + item + "" + this.getLanguageProperties().getEndOfInstruction() + "\n";
        }
        return res;
    }

    public String getConstructor(String table, String repository) throws Exception{
        String res = "";
        if(!this.getControllerProperty().getConstructor().equals("")){
            res = this.getControllerProperty().getConstructor()
                .replace("#name#", ObjectUtility.capitalize(ObjectUtility.formatToCamelCase(table)))
                .replace("?", ObjectUtility.capitalize(ObjectUtility.formatToCamelCase(repository)));
        }
        return res;
    }
    
    public String generateController(String template, String table, String packageName, String context, String repository, String entity, String framework) throws Exception {
        String res = template.replace("#package#", GeneratorService.getPackage(this.getLanguageProperties(), packageName))
                .replace("#imports#", getControllerImport(repository, entity, table))
                .replace("#class#", getControllerClass(table, framework))
                .replace("#open-bracket#", languageProperties.getOpenBracket())
                .replace("#close-bracket#", languageProperties.getCloseBracket())
                .replace("#fields#", getControllerField(table, context))
                .replace("#constructors#", getConstructor(table, context))
                .replace("#methods#", getCrudMethods(table))
                .replace("#encapsulation#", "");
        return res;
    }
}
