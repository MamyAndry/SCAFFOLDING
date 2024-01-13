package carselling.selling.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;



@Entity
@Table(name = "model")
public class Model {

	@Column(name = "id_brand")
	String idBrand;
	@Column(name = "id_category")
	String idCategory;
	@Column(name = "name")
	String name;
	@Id
	@Column(name = "id_model")
	String idModel;




	public Model(){}

	public String getIdBrand(){
		return this.idBrand;
	}
	public void setIdBrand(String idBrand){
		this.idBrand = idBrand;
	}
	public String getIdCategory(){
		return this.idCategory;
	}
	public void setIdCategory(String idCategory){
		this.idCategory = idCategory;
	}
	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getIdModel(){
		return this.idModel;
	}
	public void setIdModel(String idModel){
		this.idModel = idModel;
	}


}
