package com.test.karana.entity;

import com.dao.annotation.*;
import com.dao.annotation.conf.ForeignType;
import com.dao.database.BddObject;


@Table(name = "district")
public class District {
	@Column(name = "nom_district")
	String nomDistrict;
	@ForeignKey(mappedBy = "id_region", foreignType = ForeignType.OneToMany)
	Region region;
	@PrimaryKey
	@GeneratedValue(autoIncrement = true)
	@Column(name = "id")
	Integer id;


	public District(){}
	public String getNomDistrict(){
		return this.nomDistrict;
	}
	public void setNomDistrict(String nomDistrict){
		this.nomDistrict = nomDistrict;
	}
	public Region getRegion(){
		return this.region;
	}
	public void setRegion(Region region){
		this.region = region;
	}
	public Integer getId(){
		return this.id;
	}
	public void setId(Integer id){
		this.id = id;
	}

}
