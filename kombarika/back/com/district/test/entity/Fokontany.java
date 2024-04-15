package com.district.test.entity;

import com.dao.annotation.*;
import com.dao.annotation.conf.ForeignType;


@Table(name = "fokontany")
public class Fokontany {
	@Column(name = "nom_fokontany")
	String nomFokontany;

	@PrimaryKey
	@GeneratedValue(autoIncrement = true)
	@Column(name = "id")
	Integer id;

	@ForeignKey(mappedBy = "id_commune", foreignType = ForeignType.OneToMany)
	Commune idCommune;



	public Fokontany(){}

	public String getNomFokontany(){
		return this.nomFokontany;
	}
	public void setNomFokontany(String nomFokontany){
		this.nomFokontany = nomFokontany;
	}

	public Integer getId(){
		return this.id;
	}
	public void setId(Integer id){
		this.id = id;
	}

	public Commune getIdCommune(){
		return this.idCommune;
	}
	public void setIdCommune(Commune idCommune){
		this.idCommune = idCommune;
	}


}
