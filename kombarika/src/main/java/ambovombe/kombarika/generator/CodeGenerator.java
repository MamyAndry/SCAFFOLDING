/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ambovombe.kombarika.generator;

import ambovombe.kombarika.configuration.main.LanguageDetails;
import ambovombe.kombarika.configuration.main.TypeProperties;
import ambovombe.kombarika.configuration.main.ViewDetails;
import ambovombe.kombarika.configuration.mapping.*;
import ambovombe.kombarika.database.DbConnection;
import ambovombe.kombarika.generator.parser.FileUtility;
import ambovombe.kombarika.generator.service.DbService;
import ambovombe.kombarika.generator.service.GeneratorService;
import ambovombe.kombarika.generator.service.controller.Controller;
import ambovombe.kombarika.generator.service.entity.Entity;
import ambovombe.kombarika.generator.service.repository.Repository;
import ambovombe.kombarika.generator.service.view.View;
import ambovombe.kombarika.generator.utils.ObjectUtility;
import ambovombe.kombarika.utils.Command;
import lombok.Getter;
import lombok.Setter;

import java.io.*;
import java.util.List;


/**
 * @author Mamisoa
 * @author rakharrs
 */
@Getter @Setter
public class CodeGenerator {
    DbConnection dbConnection;
    LanguageDetails languageDetails;
    TypeProperties typeProperties;
    ViewDetails viewDetails;
    FrameworkProperties frameworkProperties;

    public CodeGenerator() throws Exception {
        this.dbConnection = new DbConnection();
        this.dbConnection.init();
        this.languageDetails = new LanguageDetails();
        this.languageDetails.init();
        this.typeProperties = new TypeProperties();
        this.typeProperties.init();
        this.viewDetails = new ViewDetails();
        this.viewDetails.init();
    }

    public String buildEntity(String table, String packageName, String language, String framework) throws Exception {
        LanguageProperties languageProperties = getLanguageDetails().getLanguages().get(language);
        TypeMapping typeMapping = getTypeProperties().getListProperties().get(language);
        FrameworkProperties frameworkProperties = languageProperties.getFrameworks().get(framework);
        String template = frameworkProperties.getTemplate();
        Entity entity = new Entity();
        entity.setAnnotationProperty(frameworkProperties.getAnnotationProperty());
        entity.setLanguageProperties(languageProperties);
        entity.setTypeMapping(typeMapping);
        entity.setImports(frameworkProperties.getImports());
        return entity.generateEntity(getDbConnection(), template, table, packageName);
    }

    public void generateEntityFile(
        String path, 
        String table, 
        String packageName, 
        String language, 
        String framework
    ) throws Exception{
        String entity = buildEntity(table, packageName, language, framework);
        LanguageProperties languageProperties = getLanguageDetails().getLanguages().get(language);
        String directory = packageName.replace(".", File.separator);
        FileUtility.createDirectory(directory,path);
        path = path + File.separator + directory;
        FileUtility.generateFile(path, GeneratorService.getFileNameCapitalized(table, languageProperties.getExtension()), entity);
    }
    public void generateEntity(
        String path, 
        String table, 
        String packageName, 
        String lang)
    throws Exception{
        String[] splittedLang = lang.split(":");
        String language = splittedLang[0]; String framework = splittedLang[1];
        this.setFrameworkProperties(this.getLanguageDetails().getLanguages().get(language).getFrameworks().get(framework));
        generateEntityFile(path, table, packageName, language, framework);
    }

    public String buildController(String table, String packageName, String context, String repository, String entity, String language, String framework) throws Exception{
        LanguageProperties languageProperties = getLanguageDetails().getLanguages().get(language);
        FrameworkProperties frameworkProperties = languageProperties.getFrameworks().get(framework);
        String template = frameworkProperties.getTemplate();
        Controller controller = new Controller();
        controller.setAnnotationProperty(frameworkProperties.getAnnotationProperty());
        controller.setControllerProperty(frameworkProperties.getControllerProperty());
        controller.setCrudMethod(frameworkProperties.getCrudMethod());
        controller.setImports(frameworkProperties.getImports());
        controller.setLanguageProperties(languageProperties);
        controller.setFrameworkProperties(frameworkProperties);
        controller.setDbConnection(dbConnection);
        return controller.generateController(template, table, packageName, context, repository, entity, framework);
    }

    public void generateControllerFile(
        String path,
        String table,
        String packageName,
        String language,
        String framework,
        String content
    ) throws Exception{
        LanguageProperties languageProperties = getLanguageDetails().getLanguages().get(language);
        String directory = packageName.replace(".", File.separator);
        FileUtility.createDirectory(directory,path);
        path = path + File.separator + directory;
        FileUtility.generateFile(path, GeneratorService.getFileNameCapitalized(table + this.getFrameworkProperties().getControllerProperty().getName() , languageProperties.getExtension()), content);
    }

    public void generateController(
        String path, 
        String table, 
        String packageName,
        String context, 
        String repository, 
        String entity, 
        String lang
    ) throws Exception{
        String[] splittedLang = lang.split(":");
        String language = splittedLang[0]; String framework = splittedLang[1];
        String controller = buildController(table, packageName, context, repository, entity, language, framework);
        generateControllerFile(path, table, packageName, language, framework, controller);
    }

    public void generateRepositoryFile(
        String path,
        String table,
        String packageName,
        String language,
        String framework,
        String content
    ) throws Exception{
        LanguageProperties languageProperties = getLanguageDetails().getLanguages().get(language);
        if(languageProperties.getFrameworks().get(framework).getRepository().equals("")) return;
        String directory = packageName.replace(".", File.separator);
        FileUtility.createDirectory(directory, path);
        path = path + File.separator + directory;
        FileUtility.generateFile(path, GeneratorService.getFileNameCapitalized(table + languageProperties.getFrameworks().get(framework).getRepositoryProperty().getName(), languageProperties.getExtension()), content);
    }

    public String buildRepository(
        String table, 
        String packageName, 
        String entityPackage, 
        String language, 
        String framework
    ) throws Exception{
        LanguageProperties languageProperties = getLanguageDetails().getLanguages().get(language);
        FrameworkProperties frameworkProperties = languageProperties.getFrameworks().get(framework);
        TypeMapping typeMapping = getTypeProperties().getListProperties().get(language);
        List<String> primaryKeysType = DbService.getPrimaryKeyType(dbConnection, table);
        Repository repository = new Repository();
        repository.setFrameworkProperties(frameworkProperties);
        repository.setLanguageProperties(languageProperties);
        repository.setTypeMapping(typeMapping);
        return repository.generateRepository(table, packageName, entityPackage, primaryKeysType);
    }

    public String buildRepository(
        String[] tables, 
        String context,
        String packageName, 
        String entityPackage, 
        String language, 
        String framework
    ) throws Exception{
        LanguageProperties languageProperties = getLanguageDetails().getLanguages().get(language);
        FrameworkProperties frameworkProperties = languageProperties.getFrameworks().get(framework);
        TypeMapping typeMapping = getTypeProperties().getListProperties().get(language);
        List<String> primaryKeysType = DbService.getPrimaryKeyType(dbConnection, tables[0]);
        Repository repository = new Repository();
        repository.setFrameworkProperties(frameworkProperties);
        repository.setLanguageProperties(languageProperties);
        repository.setTypeMapping(typeMapping);
        return repository.generateRepository(tables, context, packageName, entityPackage, primaryKeysType);
    }
    
    public void generateRepository(
        String path, 
        String table, 
        String packageName, 
        String entityPackage, 
        String lang
    ) throws Exception{
        String[] splittedLang = lang.split(":");
        String language = splittedLang[0]; String framework = splittedLang[1];
        String repository = buildRepository(table, packageName, entityPackage, language, framework);
        generateRepositoryFile(path, table, packageName, language, framework, repository);
    }

    public void generateRepository(
        String path, 
        String[] tables, 
        String context,
        String packageName, 
        String entityPackage, 
        String lang
    ) throws Exception{
        String[] splittedLang = lang.split(":");
        String language = splittedLang[0]; String framework = splittedLang[1];
        String repository = buildRepository(tables, context, packageName, entityPackage, language, framework);
        generateRepositoryFile(path, context, packageName, language, framework, repository);
    }

    /**
     * eg : generate -p path -t table1, table2, table3 -package name -l java:spring-boot
     * @author rakharrs
     */
    public String buildView(String table, String viewType, String url) throws Exception{
        View view = new View();
        view.setViewProperties(this.getViewDetails().getViews().get(viewType));
        view.setFrameworkProperties(this.getFrameworkProperties());
        return view.generateView(table, url, this.getDbConnection());
    }

    public String buildRouter(String[] tables, String viewType) throws Exception{
        View view = new View();
        view.setViewProperties(this.getViewDetails().getViews().get(viewType));
        view.setFrameworkProperties(this.getFrameworkProperties());
        return view.generateRoutes(tables);
    }

    public String buildMapping(String table, String viewType) throws Exception{
        View view = new View();
        view.setViewProperties(this.getViewDetails().getViews().get(viewType));
        view.setFrameworkProperties(this.getFrameworkProperties());
        return view.generateMapping(table, this.getDbConnection());
    }

    public String buildService(String table, String viewType, String url) throws Exception{
        View view = new View();
        view.setViewProperties(this.getViewDetails().getViews().get(viewType));
        view.setFrameworkProperties(this.getFrameworkProperties());
        return view.generateService(table, url);
    } 

    public String buildServiceSpec(String table, String viewType) throws Exception{
        View view = new View();
        view.setViewProperties(this.getViewDetails().getViews().get(viewType));
        view.setFrameworkProperties(this.getFrameworkProperties());
        return view.generateServiceSpec(table);
    }


    public String buildComponentSpecs(String table, String viewType) throws Exception{
        View view = new View();
        view.setViewProperties(this.getViewDetails().getViews().get(viewType));
        view.setFrameworkProperties(this.getFrameworkProperties());
        return view.generateComponentSpecs(table);
    }

    public String buildComponent(String table, String viewType) throws Exception{
        View view = new View();
        view.setViewProperties(this.getViewDetails().getViews().get(viewType));
        view.setFrameworkProperties(this.getFrameworkProperties());
        return view.generateComponent(table, this.getDbConnection());
    }

    public String buildStyle(String viewType) throws Exception{
        View view = new View();
        view.setViewProperties(this.getViewDetails().getViews().get(viewType));
        view.setFrameworkProperties(this.getFrameworkProperties());
        return view.generateStyle();
    }

    public int checkStyle(String viewType) throws Exception{
        View view = new View();
        view.setViewProperties(this.getViewDetails().getViews().get(viewType));
        view.setFrameworkProperties(this.getFrameworkProperties());
        return view.checkStyle();
    }

    public void generateView(
        String path, 
        String table,
        String directory, 
        String viewType,
        String url
    ) throws Exception{
        String view = buildView(table, viewType, url);
        FileUtility.createDirectory(directory,path);
        path = path + File.separator + directory;
        String filename = GeneratorService.getFileName(table, this.getViewDetails().getViews().get(viewType).getExtension());
        String newDirectory = ObjectUtility.formatToCamelCase(table);
        FileUtility.createDirectory(newDirectory, path);
        FileUtility.generateFile(path + File.separator + newDirectory, filename, view);
    }

    public void generateRouter(String path, String viewType, String[] tables) throws Exception{
        String route = this.buildRouter(tables, viewType);
        if(route.equals(""))
            return;
        String filename = GeneratorService.getFileName(this.getViewDetails().getViews().get(viewType).getRouteFilename(), this.getViewDetails().getViews().get(viewType).getRouteFileExtension());
        FileUtility.generateFile(path, filename, route);
    }

    public void generateMapping(String path, String viewType, String table) throws Exception{
        String mapping = this.buildMapping(table, viewType);
        if(mapping.equals(""))
            return;
        path = path + File.separator + ObjectUtility.formatToCamelCase(table);
        String filename = GeneratorService.getFileNameCapitalized(
            this.getViewDetails().getViews().get(viewType).getMappingFilename()
                .replace("#name#", ObjectUtility.formatToCamelCase(table)), 
            this.getViewDetails().getViews().get(viewType).getMappingFileExtension());
        FileUtility.generateFile(path, filename, mapping);
    }
    
    public void generateService(String path, String viewType, String table, String url) throws Exception{
        String service = this.buildService(table, viewType, url);
        if(service.equals(""))
            return;
        path = path + File.separator + ObjectUtility.formatToCamelCase(table);
        String filename = GeneratorService.getFileName(
            this.getViewDetails().getViews().get(viewType).getServiceFilename()
                .replace("#name#", ObjectUtility.formatToCamelCase(table)), 
            this.getViewDetails().getViews().get(viewType).getServiceFileExtension());
        FileUtility.generateFile(path, filename, service);
    } 

    public void generateServiceSpec(String path, String viewType, String table) throws Exception{
        String serviceSpec = this.buildServiceSpec(table, viewType);
        if(serviceSpec.equals(""))
            return;
        path = path + File.separator + ObjectUtility.formatToCamelCase(table);
        String filename = GeneratorService.getFileName(
            this.getViewDetails().getViews().get(viewType).getServiceSpecFilename()
                .replace("#name#", ObjectUtility.formatToCamelCase(table)), 
            this.getViewDetails().getViews().get(viewType).getServiceSpecFileExtension());
        FileUtility.generateFile(path, filename, serviceSpec);
    }

    public void generateComponentSpecs(String path, String viewType, String table) throws Exception{
        String componentSpec = this.buildComponentSpecs(table, viewType);
        if(componentSpec.equals(""))
            return;
        path = path + File.separator + ObjectUtility.formatToCamelCase(table);
        String filename = GeneratorService.getFileName(
            this.getViewDetails().getViews().get(viewType).getComponentSpecsFilename()
                .replace("#name#", ObjectUtility.formatToCamelCase(table)), 
            this.getViewDetails().getViews().get(viewType).getComponentSpecsFileExtension());
        FileUtility.generateFile(path, filename, componentSpec);
    }

    public void generateComponent(String path, String viewType, String table) throws Exception{
        String componentSpec = this.buildComponent(table, viewType);
        if(componentSpec.equals(""))
            return;
        path = path + File.separator + ObjectUtility.formatToCamelCase(table);
        String filename = GeneratorService.getFileName(
            this.getViewDetails().getViews().get(viewType).getComponentFilename()
                .replace("#name#", ObjectUtility.formatToCamelCase(table)), 
            this.getViewDetails().getViews().get(viewType).getComponentFileExtension());
        FileUtility.generateFile(path, filename, componentSpec);
    }

    public void generateStyle(String path, String viewType, String table) throws Exception{
        int check = this.checkStyle(viewType);
        if(check == 0)
            return;
        String style = this.buildStyle(viewType); 
        path = path + File.separator + ObjectUtility.formatToCamelCase(table);
        String filename = GeneratorService.getFileName(
            this.getViewDetails().getViews().get(viewType).getStyleFilename()
                .replace("#name#", ObjectUtility.formatToCamelCase(table)), 
            this.getViewDetails().getViews().get(viewType).getStyleFileExtension());
        FileUtility.generateFile(path, filename, style);
    }
    

    public void generateAllEntity(
        String path, 
        String[] tables, 
        String packageName, 
        String entity, 
        String framework
    )  throws Exception{
        for (String table : tables) {
            generateEntity(path, table, packageName + "." + entity, framework);
        }
    }
    public void generateAllController(
        String path, 
        String[] tables,
        String packageName, 
        String entity, 
        String controller, 
        String repository,
        String framework
    )  throws Exception{
        for (String table : tables) {
            generateController(path, table, packageName + "." + controller, repository, packageName + "." + repository, packageName + "." + entity, framework);  
        }
    }
    
    public void generateAllRepository(
        String path, 
        String[] tables,
        String packageName, 
        String entity,
        String repository,
        String framework
    )  throws Exception{
        if(this.getFrameworkProperties().isOneRepository()){
            generateRepository(path, tables, ObjectUtility.capitalize(repository), packageName + "." + repository, entity, framework);
        }else{
            for (String table : tables) {
                generateRepository(path, table, packageName + "." + repository, packageName + "." + entity, framework);
            }
        }
    }

    public void generateAllView(
        String path, 
        String[] tables,
        String view,
        String viewType,
        String url
    )  throws Exception{
        for (String table : tables) {
            generateView(path, table, view, viewType, url); 
            generateMapping(path, viewType, table);
            generateStyle(path, viewType, table);
            generateService(path, viewType, table, url);
            generateServiceSpec(path, viewType, table);
            generateComponentSpecs(path, viewType, table);
            generateComponent(path, viewType, table);
        }
        generateRouter(path, viewType, tables);
    }

    public void generateAll(
        String path, 
        String viewPath,
        String packageName, 
        String entity, 
        String controller, 
        String repository,
        String view,
        String viewType,
        String url,
        String[] tables, 
        String framework
    ) throws Exception{
        generateAllEntity(path, tables, packageName ,entity, framework);
        generateAllRepository(path, tables, packageName , entity, repository, framework);
        generateAllController(path, tables, packageName, entity, controller, repository, framework);  
        generateAllView(viewPath, tables, view, viewType, url);    
    }

    public void generateViewEnvironement(String path, String viewType, String projectName) throws Exception{
        String command = this.getViewDetails().getViews().get(viewType).getCLIGenerator()
            .replace("#path#", path)
            .replace("#projectName#", projectName); 
        System.out.println(command);
        Command.executeCommand(command);
    }
}
