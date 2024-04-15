package com.example.demo.controller;

import com.example.demo.entity.Fokontany;
import ambovombe.merana.utils.mapping.*;
import static ambovombe.merana.utils.mapping.method.HttpMethod.*;
import com.dao.database.GenericDao;

@RequestUrl("/fokontany")
public class FokontanyController {
	private Fokontany entity = new Fokontany();

	@Url(method = POST)
	public Object save(@RequestBody Fokontany fokontany){
	 	try{
			GenericDao.save(null, fokontany);
			return "saved successfully";
		}catch(Exception e){
			return e.getMessage();
		}
	}

	@Url(method = PUT)
	public Object update(@RequestBody Fokontany fokontany){
	 	try{
			GenericDao.update(null, fokontany);
			return "updated successfully";
		}catch(Exception e){
			return e.getMessage();
		}
	}

	@Url(method = DELETE)
	public Object delete(@RequestBody Fokontany fokontany){
	 	try{
			GenericDao.delete(null, fokontany);
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
