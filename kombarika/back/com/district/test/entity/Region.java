package com.district.test.entity;

import com.dao.annotation.*;
import com.dao.annotation.conf.ForeignType;


@Table(name = "region")
public class Region {
	@Column(name = "nom_region")
	String nomRegion;

	@PrimaryKey
	@GeneratedValue(autoIncrement = true)
	@Column(name = "id")
	Integer id;



	public Region(){}

	public String getNomRegion(){
		return this.nomRegion;
	}
	public void setNomRegion(String nomRegion){
		this.nomRegion = nomRegion;
	}

	public Integer getId(){
		return this.id;
	}
	public void setId(Integer id){
		this.id = id;
	}


}
