namespace com.district.test.entity;


using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;



[Table("commune")]
public class Commune {

	[Column("nom_commune")]
	string nomCommune { get; set; }
	[ForeignKey("id_district")]
	District district { get; set; }
	[Key]
	[Column("id")]
	int id { get; set; }




	public Commune(){}



}
