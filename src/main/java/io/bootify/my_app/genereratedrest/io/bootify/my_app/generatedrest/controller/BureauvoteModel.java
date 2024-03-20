package io.bootify.my_app.generatedrest.controller;

import io.bootify.my_app.generatedrest.entity.Bureauvote;
import com.karana.etu2060.framework.annotation.*;
import com.karana.etu2060.framework.annotation.property.*;
import com.dao.database.GenericDao;

@RequestMapping(path = "/bureauvote")
public class BureauvoteModel {
	private Bureauvote entity = new Bureauvote();

	@Url(method = HttpMethod.POST)
	@Json
	public Object save(@RequestBody Bureauvote bureauvote){
	 	try{
			GenericDao.save(null, bureauvote);
			return "saved successfully";
		}catch(Exception e){
			return "Bad request";
		}
	}
	@Url(method = HttpMethod.PUT)
	@Json
	public Object update(@RequestBody Bureauvote bureauvote){
	 	try{
			GenericDao.update(null, bureauvote);
			return "updated successfully";
		}catch(Exception e){
			return "Bad request";
		}
	}
	@Url(method = HttpMethod.DELETE)
	@Json
	public Object delete(@RequestBody Bureauvote bureauvote){
	 	try{
			GenericDao.delete(null, bureauvote);
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
