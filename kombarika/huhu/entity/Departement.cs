namespace huhu.entity;


using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;



[Table(name = 'departement')]
public class Departement {

	[Column(name = 'nomdept')]
	string nomdept;
	[Id]
	[Column(name = 'numdept')]
	string numdept;




	public Departement(){}

	[Column(name = '?')]
	public String Nomdept{
		get{
			return #field;
		}
		set{
			#field = value;
		}
	}
	[Column(name = '?')]
	public String Numdept{
		get{
			return #field;
		}
		set{
			#field = value;
		}
	}


}
