package ambovombe.kombarika.generator.service.view;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ambovombe.kombarika.configuration.mapping.FrameworkProperties;
import ambovombe.kombarika.configuration.mapping.ViewProperties;
import ambovombe.kombarika.database.DbConnection;
import ambovombe.kombarika.generator.parser.FileUtility;
import ambovombe.kombarika.generator.service.DbService;
import ambovombe.kombarika.generator.utils.ObjectUtility;
import ambovombe.kombarika.utils.Misc;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class View {
    ViewProperties viewProperties;
    FrameworkProperties frameworkProperties;

    public String getInputInsert(HashMap<String, String> columns, HashMap<String, String> foreignKeys, List<String> primaryKeys, String url, String id, String attribute, String table) throws Exception{
        String res ="";
        String template = this.getViewProperties().getInputInsert();
        for (Map.Entry<String, String> set : columns.entrySet()) {
            if (!primaryKeys.contains(set.getKey())) {
                String temp = foreignKeys.get(set.getKey());
                if(temp != null){
                    temp = set.getKey();
                    String option = this.getViewProperties().getOption()
                        .replace("#url#", url)
                        .replace("#table#", ObjectUtility.formatToCamelCase(table))
                        .replace("#path#", ObjectUtility.formatToCamelCase(temp))
                        .replace("#name#", ObjectUtility.formatToCamelCase(set.getValue()))
                        .replace("#label#", temp)
                        .replace("#id#", ObjectUtility.formatToCamelCase(id))
                        .replace("#attribute#", ObjectUtility.formatToCamelCase(attribute));
                    option = Misc.tabulate(Misc.tabulate(option));
                    res += this.getViewProperties().getSelect()
                    .replace("#label#", ObjectUtility.capitalize(temp))
                    .replace("#name#", ObjectUtility.formatToCamelCase(temp))
                    .replace("#option#", option) + "\n";
                    continue;
                }
                res += template
                .replace("#label#", ObjectUtility.formatToSpacedString(set.getKey()))
                .replace("#type#", this.getViewProperties().getListMappingInput().get(set.getValue().split("\\.")[set.getValue().split("\\.").length -1]))
                .replace("#name#", ObjectUtility.formatToCamelCase(set.getKey())) + "\n";        
            }
        }
        return Misc.tabulate(Misc.tabulate(Misc.tabulate(Misc.tabulate(Misc.tabulate(res)))));
    }

    public String getOptionUpdate(HashMap<String, String> foreignKeys, String url, String id, String attribute, String table) throws Exception{
        String res = "";
        if (foreignKeys.isEmpty()) {
            return "";
        }
        for (Map.Entry<String, String> set : foreignKeys.entrySet()) {
            res += this.getViewProperties().getOptionUpdate()
                .replace("#url#", url)
                .replace("#table#", ObjectUtility.formatToCamelCase(table))
                .replace("#path#", ObjectUtility.formatToCamelCase(set.getValue()))
                .replace("#name#", ObjectUtility.formatToCamelCase(set.getValue()))
                .replace("#Name#", ObjectUtility.capitalize(ObjectUtility.formatToCamelCase(set.getValue())))
                .replace("#label#", ObjectUtility.formatToCamelCase(set.getValue()))
                .replace("#id#", ObjectUtility.formatToCamelCase(id))                
                .replace("#attribute#", ObjectUtility.formatToCamelCase(attribute));
        }
        return Misc.tabulate(Misc.tabulate(res));
    }

    public String getInputUpdate(HashMap<String, String> columns, HashMap<String, String> foreignKeys, List<String> primaryKeys, String url, String id, String attribute) throws Exception{
        String res ="";
        String template = this.getViewProperties().getInputUpdate();
        for (Map.Entry<String, String> set : columns.entrySet()) {
            if (!primaryKeys.contains(set.getKey())) {
                String temp = foreignKeys.get(set.getKey());
                if(temp != null){
                    temp = set.getKey();
                    res += this.getViewProperties().getSelectUpdate()
                    .replace("#name#", ObjectUtility.formatToCamelCase(temp))
                    .replace("#label#", ObjectUtility.capitalize(ObjectUtility.formatToCamelCase(temp)))
                    .replace("#id#", ObjectUtility.formatToCamelCase(id))
                    .replace("#Name#", ObjectUtility.capitalize(ObjectUtility.formatToCamelCase(temp)))
                    .replace("#optionUpdate#", this.getOptionUpdate(foreignKeys, url, id, attribute, temp)) + "\n";
                }else{
                    res +=  template
                    .replace("#label#", ObjectUtility.formatToSpacedString(set.getKey()))
                    .replace("#type#", this.getViewProperties().getListMappingInput().get(set.getValue().split("\\.")[set.getValue().split("\\.").length -1]))
                    .replace("#Name#", ObjectUtility.capitalize(ObjectUtility.formatToCamelCase(set.getKey())))
                    .replace("#name#", ObjectUtility.formatToCamelCase(set.getKey())) + "\n";     
                }   
            }else{
                res += template
                .replace("#label#", "")
                .replace("#type#", "hidden")                
                .replace("#Name#", ObjectUtility.capitalize(ObjectUtility.formatToCamelCase(set.getKey())))
                .replace("#name#", ObjectUtility.formatToCamelCase(set.getKey())) + "\n";          
            }   
        }
        return Misc.tabulate(Misc.tabulate(Misc.tabulate(Misc.tabulate(Misc.tabulate(res)))));
    }

    public String getHeaders(HashMap<String, String> columns){
        String res ="";
        String template = this.getViewProperties().getTableHeader();
        for (Map.Entry<String, String> set : columns.entrySet()) {
            res += "\t\t" + template
            .replace("#label#", ObjectUtility.formatToSpacedString(set.getKey())) + "\n";
        }
        return Misc.tabulate(Misc.tabulate(res));
    }

    public String getTableValue(HashMap<String, String> columns, HashMap<String, String> foreignKeys, String attribute){
        String res ="";
        String template = this.getViewProperties().getTableValue();
        for (Map.Entry<String, String> set : columns.entrySet()) {
            String temp = foreignKeys.get(set.getKey());
            if(temp != null){
                temp = set.getKey();
                res += "\t\t" + template
                .replace("#values#", ObjectUtility.formatToCamelCase(temp) + "." + ObjectUtility.formatToCamelCase(attribute)) + "\n";                
            }else{
                res += "\t\t" + template
                .replace("#values#", ObjectUtility.formatToCamelCase(set.getKey())) + "\n";
            }
        }
        return Misc.tabulate(res);
    }

    public String getHandleInputSelectChange(HashMap<String, String> columns, HashMap<String, String> foreignKeys, List<String> primaryKeys){
        String res = "";
        String template = this.getViewProperties().getHandleInputChange();
        for (Map.Entry<String, String> set : columns.entrySet()) {
            String temp = foreignKeys.get(set.getKey());
            if(temp != null){
                res += this.getViewProperties().getHandleSelectChange()
                .replace("#Name#", ObjectUtility.capitalize(ObjectUtility.formatToCamelCase(temp)))
                .replace("#name#", ObjectUtility.formatToCamelCase(temp)) + "\n";
                continue;
            }
            res +=  template                    
            .replace("#Name#", ObjectUtility.capitalize(ObjectUtility.formatToCamelCase(set.getKey())))
            .replace("#name#", ObjectUtility.formatToCamelCase(set.getKey())) + "\n"; 
        }       
        return Misc.tabulate(Misc.tabulate(res));
    }

    public String getValues(HashMap<String, String> columns, HashMap<String, String> foreignKeys, String table){
        String res = "";
        String template = this.getViewProperties().getValues();
        res += template
            .replace("#entity#", table)
            .replace("#Entity#", ObjectUtility.capitalize(table)) + "\n";
        for (Map.Entry<String, String> set : columns.entrySet()) {
            String temp = foreignKeys.get(set.getKey());
            if(temp != null){
                temp = set.getKey();
                res += this.getViewProperties().getValues()
                .replace("#entity#", ObjectUtility.formatToCamelCase(temp))
                .replace("#Entity#", ObjectUtility.capitalize(ObjectUtility.formatToCamelCase(temp))) + "\n";
                continue;
            }
        }
        return Misc.tabulate(res);
    }

    public String getFetcher(HashMap<String, String> columns, HashMap<String, String> foreignKeys, String table){
        String res = "";
        String template = this.getViewProperties().getFetch();
        res += template
            .replace("#entity#", table)
            .replace("#Entity#", ObjectUtility.capitalize(table));
        for (Map.Entry<String, String> set : columns.entrySet()) {
            String temp = foreignKeys.get(set.getKey());
            if(temp != null){
                temp = set.getKey();
                res += this.getViewProperties().getFetch()
                .replace("#entity#", ObjectUtility.formatToCamelCase(temp))
                .replace("#Entity#", ObjectUtility.capitalize(ObjectUtility.formatToCamelCase(temp)));
                continue;
            }
        }
        return Misc.tabulate(res);
    }

    public HashMap<String, String> getIdAndAttribute(DbConnection dbConnection, HashMap<String, String> foreignKeys) throws Exception{
        String attribute = "";
        String id = "";
        HashMap<String,String> map = new HashMap<>();
        for (Map.Entry<String, String> set : foreignKeys.entrySet()) {
            List<String> tempPrimaryKey = DbService.getPrimaryKey(dbConnection, set.getValue());
            id = tempPrimaryKey.get(0);
            HashMap<String, String> tempColumns = DbService.getDetailsColumn(dbConnection.getConnection(), set.getValue());
            for (Map.Entry<String, String> set2 : tempColumns.entrySet()) {
                if(set2.getValue().equals("java.lang.String")){
                    attribute = set2.getKey();
                    break;
                }
            }
            break;
        }
        map.put("attribute", attribute);
        map.put("id", id);
        return map;
    }

    public String getImports(String[] tables){
        String res = "";
        for (String table : tables) {
            String temp = ObjectUtility.formatToCamelCase(table);
            res += this.getViewProperties().getRouteImportSyntax()
                    .replaceAll("#path#", temp)
                    .replace("#element#", ObjectUtility.capitalize(temp))
                     + "\n";
        }
        return res;
    }
    public String getRoutes(String[] tables){
        String res = "";
        for (String table : tables) {
            String temp = ObjectUtility.formatToCamelCase(table);
            res += this.getViewProperties().getRouteSyntax()
                    .replaceAll("#path#", temp)
                    .replace("#element#", ObjectUtility.capitalize(temp))
                     + "\n";
        }
        return (Misc.tabulate(res));
    }

    public String generateRoutes(String[] tables) throws Exception{
        String res = "";
        if(this.getViewProperties().getRouteTemplate().equals(""))
            return res;
        String tempPath = Misc.getViewTemplateLocation().concat(File.separator).concat(this.getViewProperties().getRouteTemplate());
        res = FileUtility.readOneFile(tempPath);
        res = res.replace("${IMPORTS}", this.getImports(tables))
                .replace("${ROUTES}", this.getRoutes(tables));
        return res;
    }

    public String generateMapping(String table,  DbConnection dbConnection) throws Exception{
        String res = "";
        if(this.getViewProperties().getMappingTemplate().equals("")){
            return res;
        }
        HashMap<String, String> foreignKeys = DbService.getForeignKeys(dbConnection, table);
        HashMap<String, String> columns = DbService.getColumnNameAndType(dbConnection.getConnection(), table);
        String tempPath = Misc.getViewTemplateLocation().concat(File.separator).concat(this.getViewProperties().getMappingTemplate());
        res = FileUtility.readOneFile(tempPath);
        String fields = "";
        String imports = "";
        String temp = "";
        String temp2 = "";
        String temp3 = "";
        for (Map.Entry<String, String> set : columns.entrySet()) {
            if(foreignKeys.get(set.getKey()) != null){
                temp2 = set.getKey();
                temp3 = foreignKeys.get(set.getKey());
                String path = ObjectUtility.formatToCamelCase(temp3);
                String element = ObjectUtility.capitalize(path);
                temp = this.getViewProperties().getMappingFieldSyntax()
                    .replace("#type#", element + " = new " + element)
                    .replace("#field#", ObjectUtility.formatToCamelCase(temp2))
                    + "\n" + "\n";

                imports += this.getViewProperties().getMappingImportSyntax()
                    .replace("#element#", element)
                    .replace("#path#", path)
                    + "\n" + "\n";
                fields += temp;
            }else{
                temp = this.getViewProperties().getMappingFieldSyntax()
                    .replace("#type#", this.getViewProperties().getListMapping().get(set.getValue()))
                    .replace("#field#", ObjectUtility.formatToCamelCase(set.getKey()))
                    + "\n" + "\n";
                fields += temp;
            }
        }
        return res
            .replace("#class#", ObjectUtility.capitalize(ObjectUtility.formatToCamelCase(table)))
            .replace("#imports#", imports)
            .replace("#fields#", Misc.tabulate(fields));
    }

    public String generateService(String table, String path) throws Exception{
        String res = "";
        if(this.getViewProperties().getServiceTemplate().equals("")){
            return res;
        }
        String tempPath = Misc.getViewTemplateLocation().concat(File.separator).concat(this.getViewProperties().getServiceTemplate());
        res = FileUtility.readOneFile(tempPath);
        String name = ObjectUtility.formatToCamelCase(table);
        res = res
            .replace("#name#", name)
            .replace("#Name#", ObjectUtility.capitalize(name))
            .replace("#path#", path);
        return res;
    }

    public String generateServiceSpec(String table) throws Exception{
        String res = "";
        if(this.getViewProperties().getServiceSpecTemplate().equals("")){
            return res;
        }
        String tempPath = Misc.getViewTemplateLocation().concat(File.separator).concat(this.getViewProperties().getServiceSpecTemplate());
        res = FileUtility.readOneFile(tempPath);
        String name = ObjectUtility.formatToCamelCase(table);
        res = res
            .replace("#name#", name)
            .replace("#Name#", ObjectUtility.capitalize(name));
        return res;
    }

    public String generateComponentSpecs(String table) throws Exception{
        String res = "";
        if(this.getViewProperties().getComponentSpecsTemplate().equals("")){
            return res;
        }
        String tempPath = Misc.getViewTemplateLocation().concat(File.separator).concat(this.getViewProperties().getComponentSpecsTemplate());
        res = FileUtility.readOneFile(tempPath);
        String name = ObjectUtility.formatToCamelCase(table);
        res = res
            .replace("#name#", name)
            .replace("#Name#", ObjectUtility.capitalize(name));
        return res;
    }

    public String generateComponent(String table, DbConnection dbConnection) throws Exception{
        String res = "";
        if(this.getViewProperties().getComponentTemplate().equals("")){
            return res;
        }
        String tempPath = Misc.getViewTemplateLocation().concat(File.separator).concat(this.getViewProperties().getComponentTemplate());
        res = FileUtility.readOneFile(tempPath);
        HashMap<String, String> foreignKeys = DbService.getForeignKeys(dbConnection, table);
        List<String> ids = DbService.getPrimaryKey(dbConnection, table);
        String id = ids.get(0);
        String imports = "";
        String fields = "";
        String init = "";
        String constructor = "";
        String other = " ";
        String name = ObjectUtility.formatToCamelCase(table);
        String temp = "";
        String Temp = "";
        for (Map.Entry<String, String> set : foreignKeys.entrySet()) {
            // temp = ObjectUtility.formatToCamelCase(set.getValue());
            temp = ObjectUtility.formatToCamelCase(set.getKey());
            Temp = ObjectUtility.capitalize(temp);
            other += ", " + Temp + "Service ";
            imports += "import { " + Temp + " } from \"../" + temp + "/" + Temp + "\";\n"; 
            imports += "import { " + Temp + "Service } from \"../" + temp + "/" + temp + ".service\";"; 
            fields += "\t" + temp + "Data : any;";
            constructor += ",private " + temp  + ":" + Temp + "Service";
            init += Misc.tabulate(Misc.tabulate("this." + temp +".get().subscribe(\n\t(data) => {\n\t\tthis." + temp + "Data = data;\t},\n\t(error) => {\n\t\tconsole.error('Error fetching data:', error);\n\t})"));
        }
        res = res
            .replace("#imports#", imports)
            .replace("#fields#", fields)
            .replace("#init#", init)
            .replace("#id#", id)
            .replace("#other#",other )
            .replace("#constructor#", constructor)
            .replace("#name#", name)
            .replace("#Name#", ObjectUtility.capitalize(name));
        return res;
    }

    public int checkStyle(){
        int res = 0;
        if(this.getViewProperties().getStyleTemplate().equals(""))
            return res;
        return 1;
    }

    public String generateStyle() throws Exception{
        String res = "";
        if(this.getViewProperties().getStyleTemplate().equals("")){
            return res;
        }           
        String tempPath = Misc.getViewTemplateLocation().concat(File.separator).concat(this.getViewProperties().getStyleTemplate());
        res += FileUtility.readOneFile(tempPath);
        return res;
    }

    
    public String generateView(String table, String url, DbConnection dbConnection) throws Exception{
        String res = "";        
        String tempPath = Misc.getViewTemplateLocation().concat(File.separator).concat(this.getViewProperties().getTemplate());
        String template = FileUtility.readOneFile(tempPath);
        List<String> primaryKeys = DbService.getPrimaryKey(dbConnection, table);
        String path =  ObjectUtility.formatToCamelCase(table);
        path = this.getFrameworkProperties().getControllerProperty().getPathSyntax().replace("?", path);
        HashMap<String, String> columns = DbService.getDetailsColumn(dbConnection.getConnection(), table);
        HashMap<String, String> foreignKeys = DbService.getForeignKeys(dbConnection, table);
        HashMap<String, String> idAndAttribute = this.getIdAndAttribute(dbConnection, foreignKeys);
        String id = idAndAttribute.get("id");
        String attribute = idAndAttribute.get("attribute");
        res = template.replace("#header#", getHeaders( columns))
        .replace("#inputInsert#", getInputInsert(columns, foreignKeys, primaryKeys, url, id, attribute, table))
        .replace("#inputUpdate#", getInputUpdate(columns, foreignKeys, primaryKeys, url, id, attribute))
        .replace("#handleInputSelectChange#", getHandleInputSelectChange(columns, foreignKeys, primaryKeys))
        .replace("#getValues#", getFetcher(columns, foreignKeys, table))
        .replace("#values#", getValues(columns, foreignKeys, table))
        .replace("#entity#", ObjectUtility.formatToSpacedString(table))
        .replace("#tableValue#", getTableValue(columns, foreignKeys, attribute))
        .replace("#url#", url)
        .replace("#id#", ObjectUtility.formatToCamelCase(primaryKeys.get(0)))
        .replace("#path#", path)
        .replace("#label#", ObjectUtility.formatToCamelCase(primaryKeys.get(0)));

        return res;
    }
}
