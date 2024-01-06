namespace solaire.entity;

using System;

[Table(name = "pointage")]
public class Pointage{
    
	[NotMapped]
	TimeOnly heureFin;
	[NotMapped]
	string idSalle;
	[NotMapped]
	string id;
	[NotMapped]
	DateOnly daty;
	[NotMapped]
	TimeOnly heureDebut;
	[NotMapped]
	Int nombrePersonne;

    //SETTERS AND GETTERS
        
	[Column(name = "heure_fin")]
	 public TimeOnly HeureFin{
		 get{
			return this.heureFin;
		}
		set{
			this.heureFin = value;
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
	[Column(name = "id")]
	 public string Id{
		 get{
			return this.id;
		}
		set{
			this.id = value;
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
	[Column(name = "heure_debut")]
	 public TimeOnly HeureDebut{
		 get{
			return this.heureDebut;
		}
		set{
			this.heureDebut = value;
		}
	}
	[Column(name = "nombre_personne")]
	 public Int NombrePersonne{
		 get{
			return this.nombrePersonne;
		}
		set{
			this.nombrePersonne = value;
		}
	}

    //CONSTRUCTORS

	public Pointage(){}
	public Pointage(TimeOnly heureFin, string idSalle, string id, DateOnly daty, TimeOnly heureDebut, Int nombrePersonne){
		setHeureFin(heureFin);
		setIdSalle(idSalle);
		setId(id);
		setDaty(daty);
		setHeureDebut(heureDebut);
		setNombrePersonne(nombrePersonne);
	}

}
