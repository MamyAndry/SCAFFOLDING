namespace solaire.entity;

using System;

[Table(name = "besoin_secteur")]
public class BesoinSecteur{
    
	[NotMapped]
	Int nombrePersonneMatin;
	[NotMapped]
	Int nombrePersonneApresMidi;
	[NotMapped]
	string idSecteur;
	[NotMapped]
	Double puissanceMoyenne;
	[NotMapped]
	DateOnly daty;
	[NotMapped]
	Int idBesoin;
	[NotMapped]
	TimeOnly heureCoupure;

    //SETTERS AND GETTERS
        
	[Column(name = "nombre_personne_matin")]
	 public Int NombrePersonneMatin{
		 get{
			return this.nombrePersonneMatin;
		}
		set{
			this.nombrePersonneMatin = value;
		}
	}
	[Column(name = "nombre_personne_apres_midi")]
	 public Int NombrePersonneApresMidi{
		 get{
			return this.nombrePersonneApresMidi;
		}
		set{
			this.nombrePersonneApresMidi = value;
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
	[Column(name = "puissance_moyenne")]
	 public Double PuissanceMoyenne{
		 get{
			return this.puissanceMoyenne;
		}
		set{
			this.puissanceMoyenne = value;
		}
	}
	[Column(name = "daty")]
	 public DateOnly Daty{
		 get{
			return this.daty;
		}
		set{
			this.daty = value;
		}
	}
	[Column(name = "id_besoin")]
	 public Int IdBesoin{
		 get{
			return this.idBesoin;
		}
		set{
			this.idBesoin = value;
		}
	}
	[Column(name = "heure_coupure")]
	 public TimeOnly HeureCoupure{
		 get{
			return this.heureCoupure;
		}
		set{
			this.heureCoupure = value;
		}
	}

    //CONSTRUCTORS

	public BesoinSecteur(){}
	public BesoinSecteur(Int nombrePersonneMatin, Int nombrePersonneApresMidi, string idSecteur, Double puissanceMoyenne, DateOnly daty, Int idBesoin, TimeOnly heureCoupure){
		setNombrePersonneMatin(nombrePersonneMatin);
		setNombrePersonneApresMidi(nombrePersonneApresMidi);
		setIdSecteur(idSecteur);
		setPuissanceMoyenne(puissanceMoyenne);
		setDaty(daty);
		setIdBesoin(idBesoin);
		setHeureCoupure(heureCoupure);
	}

}
