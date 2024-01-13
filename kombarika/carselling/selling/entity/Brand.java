package carselling.selling.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;



@Entity
@Table(name = "brand")
public class Brand {

	@Id
	@Column(name = "id_brand")
	String idBrand;
	@Column(name = "name")
	String name;




	public Brand(){}

	public String getIdBrand(){
		return this.idBrand;
	}
	public void setIdBrand(String idBrand){
		this.idBrand = idBrand;
	}
	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name = name;
	}


}
