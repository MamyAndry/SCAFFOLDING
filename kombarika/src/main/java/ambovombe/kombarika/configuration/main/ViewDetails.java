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
    String select;
    String option;
    String selectUpdate;
    String optionUpdate;
    HashMap<String, String> listMapping;

    @Override
    public void init() throws Exception {
        setJsonPath("ViewDetails.json");
        ViewDetails v = this.read();
        this.setInputInsert(v.getInputInsert());
        this.setInputUpdate(v.getInputUpdate());
        this.setListMapping(v.getListMapping());
        this.setTableHeader(v.getTableHeader());
        this.setOption(v.getOption());
        this.setSelect(v.getSelect());
        this.setOptionUpdate(v.getOptionUpdate());
        this.setSelectUpdate(v.getSelectUpdate());
        this.setTemplate(v.getTemplate());
    }
}
