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

    public String getInputInsert(HashMap<String, String> columns, HashMap<String, String> foreignKeys, List<String> primaryKeys, String url){
        String res ="";
        String template = this.getViewDetails().getInputInsert();
        for (Map.Entry<String, String> set : columns.entrySet()) {
            if (!primaryKeys.contains(set.getKey())) {
                String temp = foreignKeys.get(set.getKey());
                if(temp != null){
                    res += this.getViewDetails().getSelect()
                    .replace("#name#", ObjectUtility.formatToCamelCase(temp))
                    .replace(
                            "#option#", this.getViewDetails().getOption()
                            .replace("#url#", url)
                            .replace("#path#", ObjectUtility.formatToCamelCase(temp))
                            .replace("#label#", temp)
                            .replace("#id#", ObjectUtility.formatToCamelCase(temp))
                            .replace("#value#", ObjectUtility.formatToCamelCase(set.getKey()))
                        );
                    continue;
                }
                res += template
                .replace("#label#", ObjectUtility.formatToSpacedString(set.getKey()))
                .replace("#type#", this.getViewDetails().getListMapping().get(set.getValue().split("\\.")[set.getValue().split("\\.").length -1]))
                .replace("#name#", ObjectUtility.formatToCamelCase(set.getKey())) + "\n";        
            }
        }
        return res;
    }

    public String getOptionUpdate(HashMap<String, String> foreignKeys, String url){
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
                .replace("#id#", ObjectUtility.formatToCamelCase(set.getKey()))                
                .replace("#value#", ObjectUtility.formatToCamelCase(set.getKey()))
                ;
            res += "\n";
        }
        return res;
    }

    public String getInputUpdate(HashMap<String, String> columns, HashMap<String, String> foreignKeys, List<String> primaryKeys, String url){
        String res ="";
        String template = this.getViewDetails().getInputUpdate();
        for (Map.Entry<String, String> set : columns.entrySet()) {
            if (!primaryKeys.contains(set.getKey())) {
                String temp = foreignKeys.get(set.getKey());
                if(temp != null){
                    res += this.getViewDetails().getSelectUpdate()
                    .replace("#name#", ObjectUtility.formatToCamelCase(temp))
                    .replace("#id#", ObjectUtility.formatToCamelCase(set.getKey()));
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
        return res;
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

    public String generateView(String table, String url, DbConnection dbConnection) throws Exception{
        String res = "";        
        String tempPath = Misc.getViewTemplateLocation().concat(File.separator).concat(this.getViewDetails().getTemplate());
        String template = FileUtility.readOneFile(tempPath);
        List<String> primaryKeys = DbService.getPrimaryKey(dbConnection, table);
        String path =  ObjectUtility.formatToCamelCase(table);
        HashMap<String, String> columns = DbService.getDetailsColumn(dbConnection.getConnection(), table);
        HashMap<String, String> foreignKeys = DbService.getForeignKeys(dbConnection, table);
        res = template.replace("#header#", getHeaders( columns))
        .replace("#inputInsert#", getInputInsert(columns, foreignKeys, primaryKeys, url))
        .replace("#inputUpdate#", getInputUpdate(columns, foreignKeys, primaryKeys, url))
        .replace("#optionUpdate#", getOptionUpdate(foreignKeys, url))
        .replace("#entity#", ObjectUtility.formatToSpacedString(table))
        .replace("#url#", url)
        .replace("#path#", path)
        .replace("#label#", ObjectUtility.formatToCamelCase(primaryKeys.get(0)));

        return res;
    }
}
