namespace com.dao.test.entity;


using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;



[Table("district")]
public class District {

	[Column("nom_district")]
	string nomDistrict { get; set; }
	[ForeignKey("id_region")]
	Region region { get; set; }
	[Key]
	[Column("id")]
	int id { get; set; }




	public District(){}



}
