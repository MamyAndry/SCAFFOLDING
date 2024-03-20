package io.bootify.my_app.generatedrest.entity;

import com.dao.annotation.*;
import com.dao.annotation.conf.ForeignType;


@Table(name = "commune")
public class Commune {
	@Column(name = "nom_commune")
	String nomCommune;
	@ForeignKey(mappedBy = "id_district", foreignType = ForeignType.OneToMany)
	District district;
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
	public District getDistrict(){
		return this.district;
	}
	public void setDistrict(District district){
		this.district = district;
	}
	public Integer getId(){
		return this.id;
	}
	public void setId(Integer id){
		this.id = id;
	}

}
