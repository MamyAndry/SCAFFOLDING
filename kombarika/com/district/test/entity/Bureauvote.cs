namespace com.district.test.entity;


using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;



[Table("bureauvote")]
public class Bureauvote {

	[Column("code_bureau")]
	string codeBureau { get; set; }
	[Column("centre_vote")]
	string centreVote { get; set; }
	[ForeignKey("id_fokontany")]
	Fokontany fokontany { get; set; }
	[Key]
	[Column("id")]
	string id { get; set; }
	[Column("bureau_vote")]
	string bureauVote { get; set; }




	public Bureauvote(){}



}
