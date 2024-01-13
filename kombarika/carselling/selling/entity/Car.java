package carselling.selling.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;



@Entity
@Table(name = "car")
public class Car {

	@Column(name = "photo_1")
	String photo1;
	@Column(name = "id_model_fuel_type")
	String idModelFuelType;
	@Column(name = "photo_2")
	String photo2;
	@Column(name = "id_users")
	String idUsers;
	@Id
	@Column(name = "id_car")
	String idCar;
	@Column(name = "id_model_gear_box")
	String idModelGearBox;
	@Column(name = "door_number")
	Integer doorNumber;
	@Column(name = "_photo_3")
	String Photo3;
	@Column(name = "id_model_motor")
	String idModelMotor;
	@Column(name = "kilometrage")
	BigDecimal kilometrage;
	@Column(name = "id_car_status")
	Integer idCarStatus;
	@Column(name = "status")
	Integer status;
	@Column(name = "id_model")
	String idModel;




	public Car(){}

	public String getPhoto1(){
		return this.photo1;
	}
	public void setPhoto1(String photo1){
		this.photo1 = photo1;
	}
	public String getIdModelFuelType(){
		return this.idModelFuelType;
	}
	public void setIdModelFuelType(String idModelFuelType){
		this.idModelFuelType = idModelFuelType;
	}
	public String getPhoto2(){
		return this.photo2;
	}
	public void setPhoto2(String photo2){
		this.photo2 = photo2;
	}
	public String getIdUsers(){
		return this.idUsers;
	}
	public void setIdUsers(String idUsers){
		this.idUsers = idUsers;
	}
	public String getIdCar(){
		return this.idCar;
	}
	public void setIdCar(String idCar){
		this.idCar = idCar;
	}
	public String getIdModelGearBox(){
		return this.idModelGearBox;
	}
	public void setIdModelGearBox(String idModelGearBox){
		this.idModelGearBox = idModelGearBox;
	}
	public Integer getDoorNumber(){
		return this.doorNumber;
	}
	public void setDoorNumber(Integer doorNumber){
		this.doorNumber = doorNumber;
	}
	public String getPhoto3(){
		return this.Photo3;
	}
	public void setPhoto3(String Photo3){
		this.Photo3 = Photo3;
	}
	public String getIdModelMotor(){
		return this.idModelMotor;
	}
	public void setIdModelMotor(String idModelMotor){
		this.idModelMotor = idModelMotor;
	}
	public BigDecimal getKilometrage(){
		return this.kilometrage;
	}
	public void setKilometrage(BigDecimal kilometrage){
		this.kilometrage = kilometrage;
	}
	public Integer getIdCarStatus(){
		return this.idCarStatus;
	}
	public void setIdCarStatus(Integer idCarStatus){
		this.idCarStatus = idCarStatus;
	}
	public Integer getStatus(){
		return this.status;
	}
	public void setStatus(Integer status){
		this.status = status;
	}
	public String getIdModel(){
		return this.idModel;
	}
	public void setIdModel(String idModel){
		this.idModel = idModel;
	}


}
