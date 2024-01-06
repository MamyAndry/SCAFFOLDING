namespace solaire.entity;

using System;

[Table(name = "salle")]
public class Salle{
    
	[NotMapped]
	Int capaciteMax;
	[NotMapped]
	string idSecteur;
	[NotMapped]
	string idSalle;
	[NotMapped]
	string nom;

    //SETTERS AND GETTERS
        
	[Column(name = "capacite_max")]
	 public Int CapaciteMax{
		 get{
			return this.capaciteMax;
		}
		set{
			this.capaciteMax = value;
		}
	}
	[Column(name = "id_secteur")]
	 public string IdSecteur{
		 get{
			return this.idSecteur;
		}
		set{
			this.idSecteur = value;
		}
	}
	[Column(name = "id_salle")]
	 public string IdSalle{
		 get{
			return this.idSalle;
		}
		set{
			this.idSalle = value;
		}
	}
	[Column(name = "nom")]
	 public string Nom{
		 get{
			return this.nom;
		}
		set{
			this.nom = value;
		}
	}

    //CONSTRUCTORS

	public Salle(){}
	public Salle(Int capaciteMax, string idSecteur, string idSalle, string nom){
		setCapaciteMax(capaciteMax);
		setIdSecteur(idSecteur);
		setIdSalle(idSalle);
		setNom(nom);
	}

}
