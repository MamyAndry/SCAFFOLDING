package com.district.test.entity;


import jakarta.persistence.*;



@Entity
@Table(name = "commune")
public class Commune {

	@Column(name = "nom_commune")
	String nomCommune;
	@JoinColumn(name = "id_district")
	@ManyToOne
	District district;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
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
