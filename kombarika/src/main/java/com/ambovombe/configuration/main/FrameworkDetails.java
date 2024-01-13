package com.ambovombe.configuration.main;

import com.ambovombe.configuration.Configuration;
import com.ambovombe.configuration.mapping.FrameworkProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

/**
 * @author rakharrs
 */
@Getter @Setter
public class FrameworkDetails extends Configuration {
    HashMap<String, FrameworkProperties> frameworks;
    @Override
    public void init() throws Exception {
        setJsonPath("frameworkDetails.json");
        FrameworkDetails frameworkDetails = this.read();
        setFrameworks(frameworkDetails.getFrameworks());
    }
}
