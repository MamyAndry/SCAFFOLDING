package com.example.kombarikatest.model;

import com.example.kombarikatest.entity.Commune;
import ambovombe.merana.utils.mapping.*;
import static ambovombe.merana.utils.mapping.method.HttpMethod.*;
import com.dao.database.GenericDao;
import java.util.HashMap;
import com.dao.database.DbConnection;
import com.dao.database.Pageable;

@RequestUrl("/commune")
public class CommuneController {
	private Commune entity = new Commune();

	@Url(method = POST)
	@Auth(user = "admin")
	public Object save(@RequestBody Commune commune){
	 	try{
			GenericDao.save(null, commune);
			return "saved successfully";
		}catch(Exception e){
			return e.getMessage();
		}
	}

	@Url(method = PUT)
	@Auth(user = "admin")
	public Object update(@RequestBody Commune commune){
	 	try{
			GenericDao.update(null, commune);
			return "updated successfully";
		}catch(Exception e){
			return e.getMessage();
		}
	}

	@Url(method = DELETE)
	@Auth(user = "admin")
	public Object delete(@RequestBody Commune commune){
	 	try{
			GenericDao.delete(null, commune);
			return "deleted successfully";
		}catch(Exception e){
			return e.getMessage();
		}
	}

	@Url(method = GET)
	public Object findAll(){
	 	try{
			return GenericDao.findAll(null, entity);
		}catch(Exception e){
			return e.getMessage();
		}
	}

	@Url(value = "/pagination" ,method = GET)
	
	public Object pagination(@Param("start") int start, @Param("length") int length){
	 	DbConnection connection = new DbConnection();
		try{
			connection.init();
			Pageable pageable = new Pageable(length, start);
			HashMap<String, Object> res = new HashMap<>();
			res.put("count",GenericDao.getLineCount(connection, entity));
			res.put("data", GenericDao.findAll(connection, entity, pageable));
			return res;
		}catch(Exception e){
			return e.getMessage();
		} finally{
			try {
				connection.close();
			} catch (Exception ex) {
				return ex.getMessage();
			}
		}
	}


}
