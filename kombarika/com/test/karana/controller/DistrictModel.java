package com.test.karana.controller;

import com.test.karana.entity.District;
import com.karana.etu2060.framework.annotation.*;
import com.karana.etu2060.framework.annotation.property.*;
import com.dao.database.GenericDao;


public class DistrictModel {
	private District entity;

	@Url(url = "save-district.do")
	@Json(method = JsonMethods.POST)
	public Object save(@Argument District district){
	 	try{
			GenericDao.save(null, district);
			return "saved successfully";
		}catch(Exception e){
			return "Bad request";
		}
	}
	@Url(url = "update-district.do")
	@Json(method = JsonMethods.PUT)
	public Object update(@Argument District district){
	 	try{
			GenericDao.update(null, district);
			return "updated successfully";
		}catch(Exception e){
			return "Bad request";
		}
	}
	@Url(url = "delete-district.do")
	@Json(method = JsonMethods.DELETE)
	public Object delete(@Argument District district){
	 	try{
			GenericDao.delete(null, district);
			return "deleted successfully";
		}catch(Exception e){
			return "Bad request";
		}
	}
	@Url(url = "find-district.do")
	@Json(method = JsonMethods.GET)
	public Object findAll(){
	 	try{
			return GenericDao.findAll(null, entity);
		}catch(Exception e){
			return "Bad request";
		}
	}


}
