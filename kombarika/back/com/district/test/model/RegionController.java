package com.district.test.model;

import com.district.test.entity.Region;
import ambovombe.merana.utils.mapping.*;
import static ambovombe.merana.utils.mapping.method.HttpMethod.*;
import com.dao.database.GenericDao;

@RequestUrl("/region")
public class RegionController {
	private Region entity = new Region();

	@Url(method = POST)
	public Object save(@RequestBody Region region){
	 	try{
			GenericDao.save(null, region);
			return "saved successfully";
		}catch(Exception e){
			return e.getMessage();
		}
	}

	@Url(method = PUT)
	public Object update(@RequestBody Region region){
	 	try{
			GenericDao.update(null, region);
			return "updated successfully";
		}catch(Exception e){
			return e.getMessage();
		}
	}

	@Url(method = DELETE)
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




}
