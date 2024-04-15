package com.district.test.entity;

import com.dao.annotation.*;
import com.dao.annotation.conf.ForeignType;


@Table(name = "commune")
public class Commune {
	@Column(name = "nom_commune")
	String nomCommune;

	@ForeignKey(mappedBy = "id_district", foreignType = ForeignType.OneToMany)
	District idDistrict;

	@PrimaryKey
	@GeneratedValue(autoIncrement = true)
	@Column(name = "id")
	Integer id;



	public Commune(){}

	public String getNomCommune(){
		return this.nomCommune;
	}
	public void setNomCommune(String nomCommune){
		this.nomCommune = nomCommune;
	}

	public District getIdDistrict(){
		return this.idDistrict;
	}
	public void setIdDistrict(District idDistrict){
		this.idDistrict = idDistrict;
	}

	public Integer getId(){
		return this.id;
	}
	public void setId(Integer id){
		this.id = id;
	}


}
