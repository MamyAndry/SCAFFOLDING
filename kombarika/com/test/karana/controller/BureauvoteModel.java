package com.test.karana.controller;

import com.test.karana.entity.Bureauvote;
import com.karana.etu2060.framework.annotation.*;
import com.karana.etu2060.framework.annotation.property.*;
import com.dao.database.GenericDao;


public class BureauvoteModel {
	private Bureauvote entity;

	@Url(url = "save-bureauvote.do")
	@Json(method = JsonMethods.POST)
	public Object save(@Argument Bureauvote bureauvote){
	 	try{
			GenericDao.save(null, bureauvote);
			return "saved successfully";
		}catch(Exception e){
			return "Bad request";
		}
	}
	@Url(url = "update-bureauvote.do")
	@Json(method = JsonMethods.PUT)
	public Object update(@Argument Bureauvote bureauvote){
	 	try{
			GenericDao.update(null, bureauvote);
			return "updated successfully";
		}catch(Exception e){
			return "Bad request";
		}
	}
	@Url(url = "delete-bureauvote.do")
	@Json(method = JsonMethods.DELETE)
	public Object delete(@Argument Bureauvote bureauvote){
	 	try{
			GenericDao.delete(null, bureauvote);
			return "deleted successfully";
		}catch(Exception e){
			return "Bad request";
		}
	}
	@Url(url = "find-bureauvote.do")
	@Json(method = JsonMethods.GET)
	public Object findAll(){
	 	try{
			return GenericDao.findAll(null, entity);
		}catch(Exception e){
			return "Bad request";
		}
	}


}
