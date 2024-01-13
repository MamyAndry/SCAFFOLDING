package carselling.selling.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;



@Entity
@Table(name = "vente")
public class Vente {

	@Column(name = "id_annonce")
	String idAnnonce;
	@Column(name = "price_payed")
	BigDecimal pricePayed;
	@Id
	@Column(name = "id_vente")
	String idVente;
	@Column(name = "date_sell")
	TimeStamp dateSell;
	@Column(name = "id_users")
	String idUsers;




	public Vente(){}

	public String getIdAnnonce(){
		return this.idAnnonce;
	}
	public void setIdAnnonce(String idAnnonce){
		this.idAnnonce = idAnnonce;
	}
	public BigDecimal getPricePayed(){
		return this.pricePayed;
	}
	public void setPricePayed(BigDecimal pricePayed){
		this.pricePayed = pricePayed;
	}
	public String getIdVente(){
		return this.idVente;
	}
	public void setIdVente(String idVente){
		this.idVente = idVente;
	}
	public Timestamp getDateSell(){
		return this.dateSell;
	}
	public void setDateSell(Timestamp dateSell){
		this.dateSell = dateSell;
	}
	public String getIdUsers(){
		return this.idUsers;
	}
	public void setIdUsers(String idUsers){
		this.idUsers = idUsers;
	}


}
