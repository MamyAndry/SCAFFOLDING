package com.example.demo.model;

import com.example.demo.entity.Bureauvote;
import ambovombe.merana.utils.mapping.*;
import static ambovombe.merana.utils.mapping.method.HttpMethod.*;
import com.dao.database.GenericDao;
import java.util.HashMap;
import com.dao.database.DbConnection;
import com.dao.database.Pageable;

@RequestUrl("/bureauvote")
public class BureauvoteController {
	private Bureauvote entity = new Bureauvote();

	@Url(method = POST)
	public Object save(@RequestBody Bureauvote bureauvote){
	 	try{
			GenericDao.save(null, bureauvote);
			return "saved successfully";
		}catch(Exception e){
			return e.getMessage();
		}
	}

	@Url(method = PUT)
	public Object update(@RequestBody Bureauvote bureauvote){
	 	try{
			GenericDao.update(null, bureauvote);
			return "updated successfully";
		}catch(Exception e){
			return e.getMessage();
		}
	}

	@Url(method = DELETE)
	public Object delete(@RequestBody Bureauvote bureauvote){
	 	try{
			GenericDao.delete(null, bureauvote);
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

	@Url(value = "pagination" ,method = GET)
	
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
