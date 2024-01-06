namespace solaire.entity;

using System;

[Table(name = "details")]
public class Details{
    
	[NotMapped]
	string secteur;
	[NotMapped]
	TimeOnly heure;
	[NotMapped]
	Double besoin;
	[NotMapped]
	Double puissanceDelivreeBatterie;
	[NotMapped]
	DateOnly dateDetails;
	[NotMapped]
	Double reserveBatterie;
	[NotMapped]
	Int id;
	[NotMapped]
	Double puissanceDelivree;
	[NotMapped]
	string etat;

    //SETTERS AND GETTERS
        
	[Column(name = "secteur")]
	 public string Secteur{
		 get{
			return this.secteur;
		}
		set{
			this.secteur = value;
		}
	}
	[Column(name = "heure")]
	 public TimeOnly Heure{
		 get{
			return this.heure;
		}
		set{
			this.heure = value;
		}
	}
	[Column(name = "besoin")]
	 public Double Besoin{
		 get{
			return this.besoin;
		}
		set{
			this.besoin = value;
		}
	}
	[Column(name = "puissance_delivree_batterie")]
	 public Double PuissanceDelivreeBatterie{
		 get{
			return this.puissanceDelivreeBatterie;
		}
		set{
			this.puissanceDelivreeBatterie = value;
		}
	}
	[Column(name = "date_details")]
	 public DateOnly DateDetails{
		 get{
			return this.dateDetails;
		}
		set{
			this.dateDetails = value;
		}
	}
	[Column(name = "reserve_batterie")]
	 public Double ReserveBatterie{
		 get{
			return this.reserveBatterie;
		}
		set{
			this.reserveBatterie = value;
		}
	}
	[Column(name = "id")]
	 public Int Id{
		 get{
			return this.id;
		}
		set{
			this.id = value;
		}
	}
	[Column(name = "puissance_delivree")]
	 public Double PuissanceDelivree{
		 get{
			return this.puissanceDelivree;
		}
		set{
			this.puissanceDelivree = value;
		}
	}
	[Column(name = "etat")]
	 public string Etat{
		 get{
			return this.etat;
		}
		set{
			this.etat = value;
		}
	}

    //CONSTRUCTORS

	public Details(){}
	public Details(string secteur, TimeOnly heure, Double besoin, Double puissanceDelivreeBatterie, DateOnly dateDetails, Double reserveBatterie, Int id, Double puissanceDelivree, string etat){
		setSecteur(secteur);
		setHeure(heure);
		setBesoin(besoin);
		setPuissanceDelivreeBatterie(puissanceDelivreeBatterie);
		setDateDetails(dateDetails);
		setReserveBatterie(reserveBatterie);
		setId(id);
		setPuissanceDelivree(puissanceDelivree);
		setEtat(etat);
	}

}
