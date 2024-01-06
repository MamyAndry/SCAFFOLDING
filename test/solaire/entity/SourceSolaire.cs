namespace solaire.entity;

using System;

[Table(name = "source_solaire")]
public class SourceSolaire{
    
	[NotMapped]
	string idSecteur;
	[NotMapped]
	Double reserveMaxBatterie;
	[NotMapped]
	string idSource;
	[NotMapped]
	Double puissanceMax;

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
	[Column(name = "reserve_max_batterie")]
	 public Double ReserveMaxBatterie{
		 get{
			return this.reserveMaxBatterie;
		}
		set{
			this.reserveMaxBatterie = value;
		}
	}
	[Column(name = "id_source")]
	 public string IdSource{
		 get{
			return this.idSource;
		}
		set{
			this.idSource = value;
		}
	}
	[Column(name = "puissance_max")]
	 public Double PuissanceMax{
		 get{
			return this.puissanceMax;
		}
		set{
			this.puissanceMax = value;
		}
	}

    //CONSTRUCTORS

	public SourceSolaire(){}
	public SourceSolaire(string idSecteur, Double reserveMaxBatterie, string idSource, Double puissanceMax){
		setIdSecteur(idSecteur);
		setReserveMaxBatterie(reserveMaxBatterie);
		setIdSource(idSource);
		setPuissanceMax(puissanceMax);
	}

}
