package ambovombe.kombarika.configuration.mapping;

import lombok.Getter;
import lombok.Setter;
@Getter @Setter

public class AnnotationProperty {
    String table;
    String column;
    String entity;
    String controller;
    Constraint constraints;
}
