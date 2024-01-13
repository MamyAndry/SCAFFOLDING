package carselling.selling.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;



@Entity
@Table(name = "users")
public class Users {

	@Column(name = "is_admin")
	Boolean isAdmin;
	@Column(name = "password")
	String password;
	@Column(name = "birth_date")
	Date birthDate;
	@Column(name = "name")
	String name;
	@Id
	@Column(name = "id_users")
	String idUsers;
	@Column(name = "first_name")
	String firstName;
	@Column(name = "email")
	String email;




	public Users(){}

	public Boolean getIsAdmin(){
		return this.isAdmin;
	}
	public void setIsAdmin(Boolean isAdmin){
		this.isAdmin = isAdmin;
	}
	public String getPassword(){
		return this.password;
	}
	public void setPassword(String password){
		this.password = password;
	}
	public Date getBirthDate(){
		return this.birthDate;
	}
	public void setBirthDate(Date birthDate){
		this.birthDate = birthDate;
	}
	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getIdUsers(){
		return this.idUsers;
	}
	public void setIdUsers(String idUsers){
		this.idUsers = idUsers;
	}
	public String getFirstName(){
		return this.firstName;
	}
	public void setFirstName(String firstName){
		this.firstName = firstName;
	}
	public String getEmail(){
		return this.email;
	}
	public void setEmail(String email){
		this.email = email;
	}


}
