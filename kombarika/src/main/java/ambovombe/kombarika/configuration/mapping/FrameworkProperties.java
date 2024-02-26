package ambovombe.kombarika.configuration.mapping;

import ambovombe.kombarika.utils.Misc;
import ambovombe.kombarika.generator.parser.FileUtility;
import lombok.Getter;
import lombok.Setter;

import java.io.File;

/**
 * @author rakharrs
 */
@Getter @Setter
public class FrameworkProperties {
    String template;
    String repository;
    boolean isOneRepository;
    String listSyntax;
    Imports imports;
    AnnotationProperty annotationProperty;
    CrudMethod crudMethod;
    ControllerProperty controllerProperty;
    RepositoryProperty repositoryProperty;
    boolean init = false;

    public FrameworkProperties(){}

    public String getTemplatePath(){
        return Misc.getSourceTemplateLocation() + File.separator + this.template;
    }

    public String getTemplate(){
        if(!init){
            String path = getTemplatePath();
            try {
                setTemplate(FileUtility.readOneFile(path));
                setInit(true);
            } catch (Exception e) {
                e.printStackTrace(System.out);
                throw new RuntimeException(e);
            }
        }
        return this.template;
    }
}
