package carselling.selling.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;



@Entity
@Table(name = "category")
public class Category {

	@Id
	@Column(name = "id_category")
	String idCategory;
	@Column(name = "name")
	String name;




	public Category(){}

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


}
