package com.district.test.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;



@Entity
@Table(name = "district")
public class District {

	@Column(name = "nom_district")
	String nomDistrict;
	@Column(name = "id_region")
	Integer idRegion;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	Integer id;




	public District(){}

	public String getNomDistrict(){
		return this.nomDistrict;
	}
	public void setNomDistrict(String nomDistrict){
		this.nomDistrict = nomDistrict;
	}
	public Integer getIdRegion(){
		return this.idRegion;
	}
	public void setIdRegion(Integer idRegion){
		this.idRegion = idRegion;
	}
	public Integer getId(){
		return this.id;
	}
	public void setId(Integer id){
		this.id = id;
	}


}
