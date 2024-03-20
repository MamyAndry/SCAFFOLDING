package io.bootify.my_app.generatedrest.controller;

import io.bootify.my_app.generatedrest.entity.Region;
import com.karana.etu2060.framework.annotation.*;
import com.karana.etu2060.framework.annotation.property.*;
import com.dao.database.GenericDao;

@RequestMapping(path = "/region")
public class RegionModel {
	private Region entity = new Region();

	@Url(method = HttpMethod.POST)
	@Json
	public Object save(@RequestBody Region region){
	 	try{
			GenericDao.save(null, region);
			return "saved successfully";
		}catch(Exception e){
			return "Bad request";
		}
	}
	@Url(method = HttpMethod.PUT)
	@Json
	public Object update(@RequestBody Region region){
	 	try{
			GenericDao.update(null, region);
			return "updated successfully";
		}catch(Exception e){
			return "Bad request";
		}
	}
	@Url(method = HttpMethod.DELETE)
	@Json
	public Object delete(@RequestBody Region region){
	 	try{
			GenericDao.delete(null, region);
			return "deleted successfully";
		}catch(Exception e){
			return "Bad request";
		}
	}
	@Url(method = HttpMethod.GET)
	@Json
	public Object findAll(){
	 	try{
			return GenericDao.findAll(null, entity);
		}catch(Exception e){
			return "Bad request";
		}
	}


}
