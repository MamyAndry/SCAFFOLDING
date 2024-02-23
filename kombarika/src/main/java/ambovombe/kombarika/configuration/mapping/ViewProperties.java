package ambovombe.kombarika.configuration.mapping;

import java.util.HashMap;

import lombok.Getter;
import lombok.Setter;
@Getter @Setter
public class ViewProperties {
    String inputInsert;
    String inputUpdate;
    String tableHeader;
    String template;
    String select;
    String option;
    String selectUpdate;
    String optionUpdate;
    String handleInputChange;
    String handleSelectChange;
    String values;
    String tableValue;
    String fetch;
    String extension;
    String handleSelectItem;
    HashMap<String, String> listMapping;
}
