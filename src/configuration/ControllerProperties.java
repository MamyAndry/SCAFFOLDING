package configuration;

import com.ambovombe.configuration.mapping.ControllerProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

@Getter @Setter
public class ControllerProperties extends Configuration{
    HashMap<String, ControllerProperty> languages;

    @Override
    public void init() throws Exception{
        setJsonPath("controllerProperties.json");
        ControllerProperties controllerProperties = this.read();
        setLanguages(controllerProperties.getLanguages());
    }
}
