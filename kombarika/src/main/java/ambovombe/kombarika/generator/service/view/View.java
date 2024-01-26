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

    public String getInputInsert(HashMap<String, String> columns, List<String> primaryKeys){
        String res ="";
        String template = this.getViewDetails().getInputInsert();
        for (Map.Entry<String, String> set : columns.entrySet()) {
            if (!primaryKeys.contains(set.getKey())) {
                res += template
                .replace("#label#", ObjectUtility.formatToSpacedString(set.getKey()))
                .replace("#type#", this.getViewDetails().getListMapping().get(set.getValue().split("\\.")[set.getValue().split("\\.").length -1]))
                .replace("#name#", ObjectUtility.formatToCamelCase(set.getKey())) + "\n";        
            }
        }
        return res;
    }

    public String getInputUpdate(HashMap<String, String> columns, List<String> primaryKeys){
        String res ="";
        String template = this.getViewDetails().getInputUpdate();
        for (Map.Entry<String, String> set : columns.entrySet()) {
            if (!primaryKeys.contains(set.getKey())) {
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
        HashMap<String, String> columns = DbService.getDetailsColumn(dbConnection.getConnection(), table);
        res = template.replace("#header#", getHeaders( columns))
        .replace("#inputInsert#", getInputInsert(columns, primaryKeys))
        .replace("#inputUpdate#", getInputUpdate(columns, primaryKeys))
        .replace("#entity#", ObjectUtility.formatToSpacedString(table))
        .replace("#url#", url)
        .replace("#path#", ObjectUtility.formatToCamelCase(table))
        .replace("#label#", ObjectUtility.formatToCamelCase(primaryKeys.get(0)));

        return res;
    }
}
