namespace solaire.entity;

using System;

[Table(name = "coupure")]
public class Coupure{
    
	[NotMapped]
	string idSecteur;
	[NotMapped]
	TimeOnly heure;
	[NotMapped]
	DateOnly dateJour{get; set;}
	[NotMapped]
	string id;

    //SETTERS AND GETTERS
        
		
	[Column(name = "id_secteur")]
	 public string IdSecteur{
		 get{
			return this.idSecteur;
		}
		set{
			this.idSecteur = value;
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
	[Column(name = "date_jour")]
	 public DateOnly DateJour{
		 get{
			return this.dateJour;
		}
		set{
			this.dateJour = value;
		}
	}
	[Column(name = "id")]
	 public string Id{
		 get{
			return this.id;
		}
		set{
			this.id = value;
		}
	}

    //CONSTRUCTORS

	public Coupure(){}
	public Coupure(string idSecteur, TimeOnly heure, DateOnly dateJour, string id){
		setIdSecteur(idSecteur);
		setHeure(heure);
		setDateJour(dateJour);
		setId(id);
	}

}
