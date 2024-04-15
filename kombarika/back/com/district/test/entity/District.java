package com.district.test.entity;

import com.dao.annotation.*;
import com.dao.annotation.conf.ForeignType;


@Table(name = "district")
public class District {
	@Column(name = "nom_district")
	String nomDistrict;

	@ForeignKey(mappedBy = "id_region", foreignType = ForeignType.OneToMany)
	Region idRegion;

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

	public Region getIdRegion(){
		return this.idRegion;
	}
	public void setIdRegion(Region idRegion){
		this.idRegion = idRegion;
	}

	public Integer getId(){
		return this.id;
	}
	public void setId(Integer id){
		this.id = id;
	}


}
