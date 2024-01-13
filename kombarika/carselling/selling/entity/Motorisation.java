package carselling.selling.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;



@Entity
@Table(name = "motorisation")
public class Motorisation {

	@Id
	@Column(name = "id_motorisation")
	String idMotorisation;
	@Column(name = "name")
	String name;




	public Motorisation(){}

	public String getIdMotorisation(){
		return this.idMotorisation;
	}
	public void setIdMotorisation(String idMotorisation){
		this.idMotorisation = idMotorisation;
	}
	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name = name;
	}


}
