package ambovombe.kombarika.configuration.mapping;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ControllerProperty {
    String path;
    String get;
    String post;
    String put;
    String delete;
    String name;
    String classSyntax;
    String pathSyntax;
    String field;
    String returnType;
    String annotationField;
    String annotationMethodParameter;
    String annotationArgumentParameterFormData;
    String annotationArgumentParameterLink;
    String constructor;
}
