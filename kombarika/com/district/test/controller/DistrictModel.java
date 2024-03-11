package com.district.test.controller;

import com.district.test.entity.District;
import com.karana.etu2060.framework.annotation.*;
import com.karana.etu2060.framework.annotation.property.*;
import com.dao.database.GenericDao;

@RequestMapping(path = "/district")
public class DistrictModel {
	private District entity = new District();

	@Url(method = HttpMethod.POST)
	@Json
	public Object save(@RequestBody District district){
	 	try{
			GenericDao.save(null, district);
			return "saved successfully";
		}catch(Exception e){
			return "Bad request";
		}
	}
	@Url(method = HttpMethod.PUT)
	@Json
	public Object update(@RequestBody District district){
	 	try{
			GenericDao.update(null, district);
			return "updated successfully";
		}catch(Exception e){
			return "Bad request";
		}
	}
	@Url(method = HttpMethod.DELETE)
	@Json
	public Object delete(@RequestBody District district){
	 	try{
			GenericDao.delete(null, district);
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
