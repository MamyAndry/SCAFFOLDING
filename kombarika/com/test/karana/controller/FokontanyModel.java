package com.test.karana.controller;

import com.test.karana.entity.Fokontany;
import com.karana.etu2060.framework.annotation.*;
import com.karana.etu2060.framework.annotation.property.*;
import com.dao.database.GenericDao;


public class FokontanyModel {
	private Fokontany entity;

	@Url(url = "save-fokontany.do")
	@Json(method = JsonMethods.POST)
	public Object save(@Argument Fokontany fokontany){
	 	try{
			GenericDao.save(null, fokontany);
			return "saved successfully";
		}catch(Exception e){
			return "Bad request";
		}
	}
	@Url(url = "update-fokontany.do")
	@Json(method = JsonMethods.PUT)
	public Object update(@Argument Fokontany fokontany){
	 	try{
			GenericDao.update(null, fokontany);
			return "updated successfully";
		}catch(Exception e){
			return "Bad request";
		}
	}
	@Url(url = "delete-fokontany.do")
	@Json(method = JsonMethods.DELETE)
	public Object delete(@Argument Fokontany fokontany){
	 	try{
			GenericDao.delete(null, fokontany);
			return "deleted successfully";
		}catch(Exception e){
			return "Bad request";
		}
	}
	@Url(url = "find-fokontany.do")
	@Json(method = JsonMethods.GET)
	public Object findAll(){
	 	try{
			return GenericDao.findAll(null, entity);
		}catch(Exception e){
			return "Bad request";
		}
	}


}
