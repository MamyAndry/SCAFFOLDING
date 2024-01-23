package ambovombe.kombarika.configuration.main;

import java.util.HashMap;

import ambovombe.kombarika.configuration.Configuration;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Mamisoa
 */
@Getter @Setter
public class ViewDetails extends Configuration{
    String inputInsert;
    String inputUpdate;
    String tableHeader;
    String template;
    HashMap<String, String> listMapping;

    @Override
    public void init() throws Exception {
        setJsonPath("ViewDetails.json");
        ViewDetails v = this.read();
        this.setInputInsert(v.getInputInsert());
        this.setInputUpdate(v.getInputUpdate());
        this.setListMapping(v.getListMapping());
        this.setTableHeader(v.getTableHeader());
        this.setTemplate(v.getTemplate());
    }
}
