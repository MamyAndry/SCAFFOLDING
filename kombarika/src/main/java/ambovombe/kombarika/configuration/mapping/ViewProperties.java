package ambovombe.kombarika.configuration.mapping;

import java.util.HashMap;

import lombok.Getter;
import lombok.Setter;
@Getter @Setter
public class ViewProperties {
    String CLIGenerator;
    String inputInsert;
    String inputUpdate;
    String tableHeader;
    String template;
    String routeTemplate;
    String routeFilename;
    String routeSyntax;
    String routeFileExtension;
    String routeImportSyntax;
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
