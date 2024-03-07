package com.district.test.entity;


import jakarta.persistence.*;



@Entity
@Table(name = "region")
public class Region {

	@Column(name = "nom_region")
	String nomRegion;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
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
