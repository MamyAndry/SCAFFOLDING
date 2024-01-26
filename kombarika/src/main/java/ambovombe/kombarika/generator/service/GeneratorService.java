package ambovombe.kombarika.generator.service;

import ambovombe.kombarika.configuration.mapping.LanguageProperties;
import ambovombe.kombarika.generator.utils.ObjectUtility;

public class GeneratorService {
 
    public static String getPackage(LanguageProperties languageProperties, String packageName){
        return languageProperties.getPackageSyntax() + " " + packageName + ";\n";
    }

    public static String getFileName(String table, String extension){
        return ObjectUtility.capitalize(ObjectUtility.formatToCamelCase(table)).concat("." + extension);
    }

}
