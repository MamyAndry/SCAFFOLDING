namespace com.district.test.entity;


using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;



[Table("fokontany")]
public class Fokontany {

	[Column("nom_fokontany")]
	string nomFokontany { get; set; }
	[Key]
	[Column("id")]
	int id { get; set; }
	[ForeignKey("id_commune")]
	Commune commune { get; set; }




	public Fokontany(){}



}
