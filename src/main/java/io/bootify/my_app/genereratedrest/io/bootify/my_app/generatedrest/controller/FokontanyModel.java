package io.bootify.my_app.generatedrest.controller;

import io.bootify.my_app.generatedrest.entity.Fokontany;
import com.karana.etu2060.framework.annotation.*;
import com.karana.etu2060.framework.annotation.property.*;
import com.dao.database.GenericDao;

@RequestMapping(path = "/fokontany")
public class FokontanyModel {
	private Fokontany entity = new Fokontany();

	@Url(method = HttpMethod.POST)
	@Json
	public Object save(@RequestBody Fokontany fokontany){
	 	try{
			GenericDao.save(null, fokontany);
			return "saved successfully";
		}catch(Exception e){
			return "Bad request";
		}
	}
	@Url(method = HttpMethod.PUT)
	@Json
	public Object update(@RequestBody Fokontany fokontany){
	 	try{
			GenericDao.update(null, fokontany);
			return "updated successfully";
		}catch(Exception e){
			return "Bad request";
		}
	}
	@Url(method = HttpMethod.DELETE)
	@Json
	public Object delete(@RequestBody Fokontany fokontany){
	 	try{
			GenericDao.delete(null, fokontany);
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
