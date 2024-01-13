package carselling.selling.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;



@Entity
@Table(name = "car_status")
public class CarStatus {

	@Id
	@Column(name = "id_car_status")
	Integer idCarStatus;
	@Column(name = "name")
	String name;




	public CarStatus(){}

	public Integer getIdCarStatus(){
		return this.idCarStatus;
	}
	public void setIdCarStatus(Integer idCarStatus){
		this.idCarStatus = idCarStatus;
	}
	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name = name;
	}


}
