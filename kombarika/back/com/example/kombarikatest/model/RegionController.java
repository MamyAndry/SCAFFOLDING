package com.example.kombarikatest.model;

import com.example.kombarikatest.entity.Region;
import ambovombe.merana.utils.mapping.*;
import static ambovombe.merana.utils.mapping.method.HttpMethod.*;
import com.dao.database.GenericDao;
import java.util.HashMap;
import com.dao.database.DbConnection;
import com.dao.database.Pageable;

@RequestUrl("/region")
public class RegionController {
	private Region entity = new Region();

	@Url(method = POST)
	@Auth(user = "admin")
	public Object save(@RequestBody Region region){
	 	try{
			GenericDao.save(null, region);
			return "saved successfully";
		}catch(Exception e){
			return e.getMessage();
		}
	}

	@Url(method = PUT)
	@Auth(user = "admin")
	public Object update(@RequestBody Region region){
	 	try{
			GenericDao.update(null, region);
			return "updated successfully";
		}catch(Exception e){
			return e.getMessage();
		}
	}

	@Url(method = DELETE)
	@Auth(user = "admin")
	public Object delete(@RequestBody Region region){
	 	try{
			GenericDao.delete(null, region);
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
