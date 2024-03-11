namespace com.district.test.repository;


using com.district.test.entity.Fokontany;
using using Microsoft.EntityFrameworkCore;


public class FokontanyController : DbContext {

	public DbSet<Fokontany> Fokontany { get; set; }







}
