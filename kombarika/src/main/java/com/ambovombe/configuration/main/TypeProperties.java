package com.ambovombe.configuration.main;

import com.ambovombe.configuration.Configuration;
import com.ambovombe.configuration.mapping.TypeMapping;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

@Getter @Setter
public class TypeProperties extends Configuration {
    HashMap<String, TypeMapping> listProperties;

    @Override
    public void init() throws Exception {
        setJsonPath("typeProperties.json");
        TypeProperties typeProperties = this.read();
        setListProperties(typeProperties.getListProperties());
    }
}
