package ambovombe.kombarika.generator.service.repository;

import ambovombe.kombarika.configuration.mapping.*;
import ambovombe.kombarika.database.DbConnection;
import ambovombe.kombarika.generator.parser.FileUtility;
import ambovombe.kombarika.generator.service.DbService;
import ambovombe.kombarika.generator.service.GeneratorService;
import ambovombe.kombarika.generator.utils.ObjectUtility;
import ambovombe.kombarika.utils.Misc;

import java.io.File;
import java.util.List;

public class RepositoryService {

    public static String getRepositoryImport(LanguageProperties lp, FrameworkProperties property, String packageName, String table) throws Exception{
        String res = "";
        String imp = lp.getImportSyntax();
        for(String item : property.getImports().getRepository())
            res += imp+ " " + item
            .replace("packageName", packageName)
            .replace("className", ObjectUtility.capitalize(ObjectUtility.formatToCamelCase(table))) 
            + "" + lp.getEndOfInstruction() + "\n";
        return res;
    }
    
    public static String getRepositoryClass(String table, FrameworkProperties property,List<String> primaryKeyType) throws Exception{
        String res = "";
        res += property.getRepositoryProperty().getClassSyntax().replace("?", ObjectUtility.capitalize(ObjectUtility.formatToCamelCase(table)));
        res = res.replace("#type#", (primaryKeyType.get(0)));
        return res;
    }

    public static String generateRepository(
        String table, 
        LanguageProperties languageProperties, 
        FrameworkProperties property,
        String packageName,
        String entityPackage,
        DbConnection dbConnection
    ) throws Exception{
        String res = "";
        if(property.getRepository().equals(""))
            return res;
        String path = Misc.getSourceTemplateLocation().concat(File.separator).concat(property.getRepository());
        String template = FileUtility.readOneFile(path);
        List<String> primaryKeyType = DbService.getPrimaryKeyType(dbConnection, table);
        res = template.replace("#package#", GeneratorService.getPackage(languageProperties, packageName))
                .replace("#imports#", getRepositoryImport(languageProperties, property, entityPackage, table))
                .replace("#class#", getRepositoryClass(table, property, primaryKeyType))
                .replace("#open-bracket#", languageProperties.getOpenBracket())
                .replace("#close-bracket#", languageProperties.getCloseBracket())
                .replace("#fields#", "")
                .replace("#constructors#", "")
                .replace("#methods#", "")
                .replace("#encapsulation#", "");
        return res;
    }
}