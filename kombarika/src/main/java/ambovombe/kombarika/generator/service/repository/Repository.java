package ambovombe.kombarika.generator.service.repository;

import ambovombe.kombarika.configuration.mapping.LanguageProperties;
import ambovombe.kombarika.configuration.mapping.*;
import ambovombe.kombarika.generator.parser.FileUtility;
import ambovombe.kombarika.generator.service.GeneratorService;
import ambovombe.kombarika.generator.utils.ObjectUtility;
import ambovombe.kombarika.utils.Misc;
import lombok.Getter;
import lombok.Setter;

import java.io.File;

@Getter @Setter
public class Repository {
    LanguageProperties languageProperties;
    FrameworkProperties frameworkProperties;

    public String getRepositoryImport(String packageName, String table) throws Exception{
        String res = "";
        String imp = this.getLanguageProperties().getImportSyntax();
        for(String item : this.getFrameworkProperties().getImports().getRepository())
            res += imp+ " " + item
            .replace("packageName", packageName)
            .replace("className", ObjectUtility.capitalize(ObjectUtility.formatToCamelCase(table))) 
            + "" + this.getLanguageProperties().getEndOfInstruction() + "\n";
        return res;
    }
    
    public String getRepositoryClass(String table) throws Exception{
        String res = "";
        res += this.getFrameworkProperties().getRepositoryProperty().getClassSyntax().replace("?", ObjectUtility.capitalize(ObjectUtility.formatToCamelCase(table)));
        return res;
    }

    public String generateRepository(
        String table, 
        String packageName,
        String entityPackage
    ) throws Exception{
        String res = "";
        if(this.getFrameworkProperties().getRepository().equals(""))
            return res;
        String path = Misc.getSourceTemplateLocation().concat(File.separator).concat(this.getFrameworkProperties().getRepository());
        String template = FileUtility.readOneFile(path);
        res = template.replace("#package#", GeneratorService.getPackage(this.getLanguageProperties(), packageName))
                .replace("#imports#", getRepositoryImport(entityPackage, table))
                .replace("#class#", getRepositoryClass(table))
                .replace("#open-bracket#", languageProperties.getOpenBracket())
                .replace("#close-bracket#", languageProperties.getCloseBracket())
                .replace("#fields#", "")
                .replace("#constructors#", "")
                .replace("#methods#", "")
                .replace("#encapsulation#", "");
        return res;
    }
}