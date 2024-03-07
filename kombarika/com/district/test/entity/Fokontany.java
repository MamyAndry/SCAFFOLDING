package com.district.test.entity;


import jakarta.persistence.*;



@Entity
@Table(name = "fokontany")
public class Fokontany {

	@Column(name = "nom_fokontany")
	String nomFokontany;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	Integer id;
	@JoinColumn(name = "id_commune")
	@ManyToOne
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
