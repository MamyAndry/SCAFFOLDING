package com.district.test.model;

import com.district.test.entity.Bureauvote;
import ambovombe.merana.utils.mapping.*;
import static ambovombe.merana.utils.mapping.method.HttpMethod.*;
import com.dao.database.GenericDao;

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




}
