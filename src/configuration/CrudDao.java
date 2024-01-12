package configuration;

import com.ambovombe.configuration.mapping.CrudMethod;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
@Getter @Setter
public class CrudDao extends Configuration {
    HashMap<String, CrudMethod> languages;

    @Override
    public void init() throws Exception{
        setJsonPath("crudDao.json");
        CrudDao crudDao = this.read();
        setLanguages(crudDao.getLanguages());
    }
}
