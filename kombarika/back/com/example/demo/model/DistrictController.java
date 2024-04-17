package com.example.demo.model;

import com.example.demo.entity.District;
import ambovombe.merana.utils.mapping.*;
import static ambovombe.merana.utils.mapping.method.HttpMethod.*;
import com.dao.database.GenericDao;
import java.util.HashMap;
import com.dao.database.DbConnection;
import com.dao.database.Pageable;

@RequestUrl("/district")
public class DistrictController {
	private District entity = new District();

	@Url(method = POST)
	@Auth(user = "admin")
	public Object save(@RequestBody District district){
	 	try{
			GenericDao.save(null, district);
			return "saved successfully";
		}catch(Exception e){
			return e.getMessage();
		}
	}

	@Url(method = PUT)
	public Object update(@RequestBody District district){
	 	try{
			GenericDao.update(null, district);
			return "updated successfully";
		}catch(Exception e){
			return e.getMessage();
		}
	}

	@Url(method = DELETE)
	public Object delete(@RequestBody District district){
	 	try{
			GenericDao.delete(null, district);
			return "deleted successfully";
		}catch(Exception e){
			return e.getMessage();
		}
	}

	@Url(method = GET)
	@Auth(user = "admin")
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
