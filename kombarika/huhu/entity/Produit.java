package huhu.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;



@Entity
@Table(name = 'produit')
public class Produit {

	@Column(name = 'id_categorie')
	String idCategorie;
	@Id
	@Column(name = 'id_produit')
	String idProduit;
	@Column(name = 'nom')
	String nom;
	@Column(name = 'id_style')
	String idStyle;




	public Produit(){}

	public String getIdCategorie(){
		return this.idCategorie;
	}
	public void setIdCategorie(String idCategorie){
		this.idCategorie = idCategorie;
	}
	public String getIdProduit(){
		return this.idProduit;
	}
	public void setIdProduit(String idProduit){
		this.idProduit = idProduit;
	}
	public String getNom(){
		return this.nom;
	}
	public void setNom(String nom){
		this.nom = nom;
	}
	public String getIdStyle(){
		return this.idStyle;
	}
	public void setIdStyle(String idStyle){
		this.idStyle = idStyle;
	}


}
