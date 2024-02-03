package ambovombe.kombarika.generator.service.view;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ambovombe.kombarika.configuration.main.ViewDetails;
import ambovombe.kombarika.database.DbConnection;
import ambovombe.kombarika.generator.parser.FileUtility;
import ambovombe.kombarika.generator.service.DbService;
import ambovombe.kombarika.generator.utils.ObjectUtility;
import ambovombe.kombarika.utils.Misc;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class View {
    ViewDetails viewDetails;

    public String getInputInsert(HashMap<String, String> columns, HashMap<String, String> foreignKeys, List<String> primaryKeys, String url, String id, String attribute) throws Exception{
        String res ="";
        String template = this.getViewDetails().getInputInsert();
        for (Map.Entry<String, String> set : columns.entrySet()) {
            if (!primaryKeys.contains(set.getKey())) {
                String temp = foreignKeys.get(set.getKey());
                if(temp != null){
                    String option = this.getViewDetails().getOption()
                        .replace("#url#", url)
                        .replace("#path#", ObjectUtility.formatToCamelCase(temp))
                        .replace("#label#", temp)
                        .replace("#id#", ObjectUtility.formatToCamelCase(id))
                        .replace("#value#", ObjectUtility.formatToCamelCase(attribute));
                    option = Misc.tabulate(Misc.tabulate(option));
                    res += this.getViewDetails().getSelect()
                    .replace("#name#", ObjectUtility.formatToCamelCase(temp))
                    .replace("#option#", option);
                    continue;
                }
                res += template
                .replace("#label#", ObjectUtility.formatToSpacedString(set.getKey()))
                .replace("#type#", this.getViewDetails().getListMapping().get(set.getValue().split("\\.")[set.getValue().split("\\.").length -1]))
                .replace("#name#", ObjectUtility.formatToCamelCase(set.getKey())) + "\n";        
            }
        }
        return Misc.tabulate(res);
    }

    public String getOptionUpdate(HashMap<String, String> foreignKeys, String url, String id, String attribute) throws Exception{
        String res = "";
        if (foreignKeys.isEmpty()) {
            return "";
        }
        for (Map.Entry<String, String> set : foreignKeys.entrySet()) {
            res += this.getViewDetails().getOptionUpdate()
                .replace("#url#", url)
                .replace("#path#", ObjectUtility.formatToCamelCase(set.getValue()))
                .replace("#Name#", ObjectUtility.capitalize(ObjectUtility.formatToCamelCase(set.getValue())))
                .replace("#label#", ObjectUtility.formatToCamelCase(set.getValue()))
                .replace("#id#", ObjectUtility.formatToCamelCase(id))                
                .replace("#value#", ObjectUtility.formatToCamelCase(attribute))
                ;
            res += "\n";
        }
        return Misc.tabulate(res);
    }

    public String getInputUpdate(HashMap<String, String> columns, HashMap<String, String> foreignKeys, List<String> primaryKeys, String url, String id) throws Exception{
        String res ="";
        String template = this.getViewDetails().getInputUpdate();
        for (Map.Entry<String, String> set : columns.entrySet()) {
            if (!primaryKeys.contains(set.getKey())) {
                String temp = foreignKeys.get(set.getKey());
                if(temp != null){
                    res += this.getViewDetails().getSelectUpdate()
                    .replace("#name#", ObjectUtility.formatToCamelCase(temp))
                    .replace("#id#", ObjectUtility.formatToCamelCase(id));
                    continue;
                }
                res +=  template
                .replace("#label#", ObjectUtility.formatToSpacedString(set.getKey()))
                .replace("#type#", this.getViewDetails().getListMapping().get(set.getValue().split("\\.")[set.getValue().split("\\.").length -1]))
                .replace("#name#", ObjectUtility.formatToCamelCase(set.getKey())) + "\n";        
            }else{
                res += template
                .replace("#label#", "")
                .replace("#type#", "hidden")
                .replace("#name#", ObjectUtility.formatToCamelCase(set.getKey())) + "\n";            
            }
        }
        return Misc.tabulate(res);
    }

    public String getHeaders(HashMap<String, String> columns){
        String res ="";
        String template = this.getViewDetails().getTableHeader();
        for (Map.Entry<String, String> set : columns.entrySet()) {
            res += "\t\t" + template
            .replace("#label#", ObjectUtility.formatToSpacedString(set.getKey())) + "\n";
        }
        return res;
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

    public String generateView(String table, String url, DbConnection dbConnection) throws Exception{
        String res = "";        
        String tempPath = Misc.getViewTemplateLocation().concat(File.separator).concat(this.getViewDetails().getTemplate());
        String template = FileUtility.readOneFile(tempPath);
        List<String> primaryKeys = DbService.getPrimaryKey(dbConnection, table);
        String path =  ObjectUtility.formatToCamelCase(table);
        HashMap<String, String> columns = DbService.getDetailsColumn(dbConnection.getConnection(), table);
        HashMap<String, String> foreignKeys = DbService.getForeignKeys(dbConnection, table);
        HashMap<String, String> idAndAttribute = this.getIdAndAttribute(dbConnection, foreignKeys);
        String id = idAndAttribute.get("id");
        String attribute = idAndAttribute.get("attribute");
        res = template.replace("#header#", getHeaders( columns))
        .replace("#inputInsert#", getInputInsert(columns, foreignKeys, primaryKeys, url, id, attribute))
        .replace("#inputUpdate#", getInputUpdate(columns, foreignKeys, primaryKeys, url, id))
        .replace("#optionUpdate#", getOptionUpdate(foreignKeys, url, id, attribute))
        .replace("#entity#", ObjectUtility.formatToSpacedString(table))
        .replace("#value#", ObjectUtility.formatToCamelCase(attribute))
        .replace("#url#", url)
        .replace("#path#", path)
        .replace("#label#", ObjectUtility.formatToCamelCase(primaryKeys.get(0)));

        return res;
    }
}
