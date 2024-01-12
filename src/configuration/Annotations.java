package configuration;

import com.ambovombe.configuration.mapping.AnnotationProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

@Getter @Setter
public class Annotations extends Configuration{
    HashMap<String, AnnotationProperty> languages;

    public Annotations(){}
    @Override
    public void init() throws Exception{
        setJsonPath("annotations.json");
        Annotations annotations = this.read();
        setLanguages(annotations.getLanguages());
    }
}
