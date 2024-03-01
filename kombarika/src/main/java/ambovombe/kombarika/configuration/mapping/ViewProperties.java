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
    String extension;
    String serviceTemplate;
    String serviceFileName;
    String serviceFileExtension;
    String componentTemplate;
    String componentFileName;
    String componentFileExtension;
    String routeTemplate;
    String routeFilename;
    String routeFileExtension;
    String routeSyntax;
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
    String handleSelectItem;
    HashMap<String, String> listMapping;
}
