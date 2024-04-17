package com.example.demo.model;

import com.example.demo.entity.Fokontany;
import ambovombe.merana.utils.mapping.*;
import static ambovombe.merana.utils.mapping.method.HttpMethod.*;
import com.dao.database.GenericDao;
import java.util.HashMap;
import com.dao.database.DbConnection;
import com.dao.database.Pageable;

@RequestUrl("/fokontany")
public class FokontanyController {
	private Fokontany entity = new Fokontany();

	@Url(method = POST)
	@Auth(user = "admin")
	public Object save(@RequestBody Fokontany fokontany){
	 	try{
			GenericDao.save(null, fokontany);
			return "saved successfully";
		}catch(Exception e){
			return e.getMessage();
		}
	}

	@Url(method = PUT)
	@Auth(user = "admin")
	public Object update(@RequestBody Fokontany fokontany){
	 	try{
			GenericDao.update(null, fokontany);
			return "updated successfully";
		}catch(Exception e){
			return e.getMessage();
		}
	}

	@Url(method = DELETE)
	@Auth(user = "admin")
	public Object delete(@RequestBody Fokontany fokontany){
	 	try{
			GenericDao.delete(null, fokontany);
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
