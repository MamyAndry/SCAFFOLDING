package carselling.selling.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;



@Entity
@Table(name = "fuel_type")
public class FuelType {

	@Column(name = "name")
	String name;
	@Id
	@Column(name = "id_fuel_type")
	String idFuelType;




	public FuelType(){}

	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getIdFuelType(){
		return this.idFuelType;
	}
	public void setIdFuelType(String idFuelType){
		this.idFuelType = idFuelType;
	}


}
