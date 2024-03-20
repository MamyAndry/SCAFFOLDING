package io.bootify.my_app.generatedrest.controller;

import io.bootify.my_app.generatedrest.entity.Commune;
import com.karana.etu2060.framework.annotation.*;
import com.karana.etu2060.framework.annotation.property.*;
import com.dao.database.GenericDao;

@RequestMapping(path = "/commune")
public class CommuneModel {
	private Commune entity = new Commune();

	@Url(method = HttpMethod.POST)
	@Json
	public Object save(@RequestBody Commune commune){
	 	try{
			GenericDao.save(null, commune);
			return "saved successfully";
		}catch(Exception e){
			return "Bad request";
		}
	}
	@Url(method = HttpMethod.PUT)
	@Json
	public Object update(@RequestBody Commune commune){
	 	try{
			GenericDao.update(null, commune);
			return "updated successfully";
		}catch(Exception e){
			return "Bad request";
		}
	}
	@Url(method = HttpMethod.DELETE)
	@Json
	public Object delete(@RequestBody Commune commune){
	 	try{
			GenericDao.delete(null, commune);
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
