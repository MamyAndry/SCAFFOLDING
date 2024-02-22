namespace com.district.test.entity;


using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;



[Table("region")]
public class Region {

	[Column("nom_region")]
	string nomRegion { get; set; }
	[Key]
	[Column("id")]
	int id { get; set; }




	public Region(){}



}
