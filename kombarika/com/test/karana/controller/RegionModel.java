package com.test.karana.controller;

import com.test.karana.entity.Region;
import com.karana.etu2060.framework.annotation.*;
import com.karana.etu2060.framework.annotation.property.*;
import com.dao.database.GenericDao;


public class RegionModel {
	private Region entity;

	@Url(url = "save-region.do")
	@Json(method = JsonMethods.POST)
	public Object save(@Argument Region region){
	 	try{
			GenericDao.save(null, region);
			return "saved successfully";
		}catch(Exception e){
			return "Bad request";
		}
	}
	@Url(url = "update-region.do")
	@Json(method = JsonMethods.PUT)
	public Object update(@Argument Region region){
	 	try{
			GenericDao.update(null, region);
			return "updated successfully";
		}catch(Exception e){
			return "Bad request";
		}
	}
	@Url(url = "delete-region.do")
	@Json(method = JsonMethods.DELETE)
	public Object delete(@Argument Region region){
	 	try{
			GenericDao.delete(null, region);
			return "deleted successfully";
		}catch(Exception e){
			return "Bad request";
		}
	}
	@Url(url = "find-region.do")
	@Json(method = JsonMethods.GET)
	public Object findAll(){
	 	try{
			return GenericDao.findAll(null, entity);
		}catch(Exception e){
			return "Bad request";
		}
	}


}
