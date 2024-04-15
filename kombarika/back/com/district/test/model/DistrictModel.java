package com.district.test.model;

import com.district.test.entity.District;
import com.karana.etu2060.framework.annotation.*;
import com.karana.etu2060.framework.annotation.property.*;
import java.util.HashMap;
import com.dao.database.DbConnection;
import com.dao.database.GenericDao;
import com.dao.database.Pageable;

@RequestMapping(path = "/district")
@Cors(allowedOrigin = "*")
public class DistrictModel {
	private District entity = new District();

	@Url(method = HttpMethod.POST)
	@Json
	public Object save(@RequestBody District district){
	 	try{
			GenericDao.save(null, district);
			return "saved successfully";
		}catch(Exception e){
			return e.getMessage();
		}
	}

	@Url(method = HttpMethod.PUT)
	@Json
	public Object update(@RequestBody District district){
	 	try{
			GenericDao.update(null, district);
			return "updated successfully";
		}catch(Exception e){
			return e.getMessage();
		}
	}

	@Url(method = HttpMethod.DELETE)
	@Json
	public Object delete(@RequestBody District district){
	 	try{
			GenericDao.delete(null, district);
			return "deleted successfully";
		}catch(Exception e){
			return e.getMessage();
		}
	}

	@Url(method = HttpMethod.GET)
	@Json
	public Object findAll(){
	 	try{
			return GenericDao.findAll(null, entity);
		}catch(Exception e){
			return e.getMessage();
		}
	}

	@Url(url = "/pagination" ,method = HttpMethod.GET)
	@Json
	public Object pagination(@RequestParam(parameterName = "start") int start, @RequestParam(parameterName = "length") int length){
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
