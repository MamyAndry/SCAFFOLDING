package com.test.karana.controller;

import com.test.karana.entity.Commune;
import com.karana.etu2060.framework.annotation.*;
import com.karana.etu2060.framework.annotation.property.*;
import com.dao.database.GenericDao;


public class CommuneModel {
	private Commune entity;

	@Url(url = "save-commune.do")
	@Json(method = JsonMethods.POST)
	public Object save(@Argument Commune commune){
	 	try{
			GenericDao.save(null, commune);
			return "saved successfully";
		}catch(Exception e){
			return "Bad request";
		}
	}
	@Url(url = "update-commune.do")
	@Json(method = JsonMethods.PUT)
	public Object update(@Argument Commune commune){
	 	try{
			GenericDao.update(null, commune);
			return "updated successfully";
		}catch(Exception e){
			return "Bad request";
		}
	}
	@Url(url = "delete-commune.do")
	@Json(method = JsonMethods.DELETE)
	public Object delete(@Argument Commune commune){
	 	try{
			GenericDao.delete(null, commune);
			return "deleted successfully";
		}catch(Exception e){
			return "Bad request";
		}
	}
	@Url(url = "find-commune.do")
	@Json(method = JsonMethods.GET)
	public Object findAll(){
	 	try{
			return GenericDao.findAll(null, entity);
		}catch(Exception e){
			return "Bad request";
		}
	}


}
