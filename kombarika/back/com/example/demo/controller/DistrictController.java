package com.example.demo.controller;

import com.example.demo.entity.District;
import ambovombe.merana.utils.mapping.*;
import static ambovombe.merana.utils.mapping.method.HttpMethod.*;
import com.dao.database.GenericDao;

@RequestUrl("/district")
public class DistrictController {
	private District entity = new District();

	@Url(method = POST)
	public Object save(@RequestBody District district){
	 	try{
			GenericDao.save(null, district);
			return "saved successfully";
		}catch(Exception e){
			return e.getMessage();
		}
	}

	@Url(method = PUT)
	public Object update(@RequestBody District district){
	 	try{
			GenericDao.update(null, district);
			return "updated successfully";
		}catch(Exception e){
			return e.getMessage();
		}
	}

	@Url(method = DELETE)
	public Object delete(@RequestBody District district){
	 	try{
			GenericDao.delete(null, district);
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
