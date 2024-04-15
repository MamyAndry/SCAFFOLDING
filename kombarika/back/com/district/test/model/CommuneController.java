package com.district.test.model;

import com.district.test.entity.Commune;
import ambovombe.merana.utils.mapping.*;
import static ambovombe.merana.utils.mapping.method.HttpMethod.*;
import com.dao.database.GenericDao;

@RequestUrl("/commune")
public class CommuneController {
	private Commune entity = new Commune();

	@Url(method = POST)
	public Object save(@RequestBody Commune commune){
	 	try{
			GenericDao.save(null, commune);
			return "saved successfully";
		}catch(Exception e){
			return e.getMessage();
		}
	}

	@Url(method = PUT)
	public Object update(@RequestBody Commune commune){
	 	try{
			GenericDao.update(null, commune);
			return "updated successfully";
		}catch(Exception e){
			return e.getMessage();
		}
	}

	@Url(method = DELETE)
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




}
