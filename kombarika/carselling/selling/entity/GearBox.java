package carselling.selling.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;



@Entity
@Table(name = "gear_box")
public class GearBox {

	@Id
	@Column(name = "id_gear_box")
	String idGearBox;
	@Column(name = "name")
	String name;




	public GearBox(){}

	public String getIdGearBox(){
		return this.idGearBox;
	}
	public void setIdGearBox(String idGearBox){
		this.idGearBox = idGearBox;
	}
	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name = name;
	}


}
