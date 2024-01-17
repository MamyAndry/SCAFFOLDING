package ambovombe.kombarika.configuration.main;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import ambovombe.kombarika.configuration.Configuration;
import ambovombe.kombarika.configuration.mapping.LanguageProperties;

import java.util.HashMap;

/**
 *
 * @author Mamisoa
 */
public class LanguageDetails extends Configuration {
    HashMap<String, LanguageProperties> languages;

    //GETTERS & SETTERS

    public HashMap<String, LanguageProperties> getLanguages() {
        return languages;
    }

    public void setLanguages(HashMap<String, LanguageProperties> languages) {
        this.languages = languages;
    }

    //CONSTRUCTOR
    public LanguageDetails(){}


    @Override
    public void init() throws Exception{
        setJsonPath("languageDetails.json");
        LanguageDetails languageDetails = this.read();
        setLanguages(languageDetails.getLanguages());
    }
}
