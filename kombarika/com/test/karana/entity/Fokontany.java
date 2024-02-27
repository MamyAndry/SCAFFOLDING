package com.test.karana.entity;

import com.dao.annotation.*;
import com.dao.annotation.conf.ForeignType;
import com.dao.database.BddObject;


@Table(name = "fokontany")
public class Fokontany {
	@Column(name = "nom_fokontany")
	String nomFokontany;
	@PrimaryKey
	@GeneratedValue(autoIncrement = true)
	@Column(name = "id")
	Integer id;
	@ForeignKey(mappedBy = "id_commune", foreignType = ForeignType.OneToMany)
	Commune commune;


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
	public Commune getCommune(){
		return this.commune;
	}
	public void setCommune(Commune commune){
		this.commune = commune;
	}

}
