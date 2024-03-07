namespace com.district.test.repository;


using com.district.test.entity.Commune;
using using Microsoft.EntityFrameworkCore;


public class CommuneController : DbContext {

	public DbSet<Commune> Commune { get; set; }







}
