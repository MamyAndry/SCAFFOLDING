package ambovombe.kombarika.configuration;

import ambovombe.kombarika.utils.Misc;
import ambovombe.kombarika.generator.parser.JsonUtility;
import lombok.Getter;
import lombok.Setter;

import java.io.File;


/**
 * @author rakharrs
 */
public abstract class Configuration {
    @Getter @Setter
    String jsonPath;

    /**
     * Mamaky anle fichier de configuration
     * @return anle configuration miantso
     * @throws Exception
     */
    public <T> T read() throws Exception{
        String separator = File.separator;
        String path = Misc.getGeneratorConfLocation() + separator + jsonPath;
        Object temp = JsonUtility.parseJson(path, this.getClass());
        return (T)temp;
    }

    /**
     * need to be overrided<hr>
     * Need to be defined
     * For setting attribute of the configuration class
     */
    public abstract void init() throws Exception;
}
