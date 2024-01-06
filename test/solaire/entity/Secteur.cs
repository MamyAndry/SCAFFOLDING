namespace solaire.entity;

using System;

[Table(name = "secteur")]
public class Secteur{
    
	[NotMapped]
	string idSecteur;
	[NotMapped]
	string nom;

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

	public Secteur(){}
	public Secteur(string idSecteur, string nom){
		setIdSecteur(idSecteur);
		setNom(nom);
	}

}
