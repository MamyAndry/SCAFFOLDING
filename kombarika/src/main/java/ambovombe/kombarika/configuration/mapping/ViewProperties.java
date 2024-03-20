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
    String serviceFilename;
    String serviceFileExtension;
    String serviceSpecTemplate;
    String serviceSpecFilename;
    String serviceSpecFileExtension;
    String componentTemplate;
    String componentFilename;
    String componentFileExtension;
    String mappingTemplate;
    String mappingFilename;
    String mappingFileExtension;
    String mappingImportSyntax;
    String mappingFieldSyntax;
    String componentSpecsTemplate;
    String componentSpecsFilename;
    String componentSpecsFileExtension;
    String routeTemplate;
    String routeFilename;
    String routeFileExtension;
    String styleTemplate;
    String styleFilename;
    String styleFileExtension;
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
    HashMap<String, String> listMappingInput;
}
