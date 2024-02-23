package ambovombe.kombarika.configuration.main;

import java.util.HashMap;

import ambovombe.kombarika.configuration.Configuration;
import ambovombe.kombarika.configuration.mapping.ViewProperties;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Mamisoa
 */
@Getter @Setter
public class ViewDetails extends Configuration{
    HashMap<String, ViewProperties> views;

    @Override
    public void init() throws Exception {
        setJsonPath("ViewDetails.json");
        ViewDetails v = this.read();
        this.setViews(v.getViews());
    }
}
