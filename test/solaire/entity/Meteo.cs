namespace solaire.entity;

using System;

[Table(name = "meteo")]
public class Meteo{
    
	[NotMapped]
	Double luminosite;
	[NotMapped]
	TimeOnly heureFin;
	[NotMapped]
	string id;
	[NotMapped]
	DateOnly dateMeteo;
	[NotMapped]
	TimeOnly heureDebut;

    //SETTERS AND GETTERS
        
	[Column(name = "luminosite")]
	 public Double Luminosite{
		 get{
			return this.luminosite;
		}
		set{
			this.luminosite = value;
		}
	}
	[Column(name = "heure_fin")]
	 public TimeOnly HeureFin{
		 get{
			return this.heureFin;
		}
		set{
			this.heureFin = value;
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
	[Column(name = "date_meteo")]
	 public DateOnly DateMeteo{
		 get{
			return this.dateMeteo;
		}
		set{
			this.dateMeteo = value;
		}
	}
	[Column(name = "heure_debut")]
	 public TimeOnly HeureDebut{
		 get{
			return this.heureDebut;
		}
		set{
			this.heureDebut = value;
		}
	}

    //CONSTRUCTORS

	public Meteo(){}
	public Meteo(Double luminosite, TimeOnly heureFin, string id, DateOnly dateMeteo, TimeOnly heureDebut){
		setLuminosite(luminosite);
		setHeureFin(heureFin);
		setId(id);
		setDateMeteo(dateMeteo);
		setHeureDebut(heureDebut);
	}

}
