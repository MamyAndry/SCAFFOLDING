namespace com.district.test.repository;


using com.district.test.entity.Bureauvote;
using using Microsoft.EntityFrameworkCore;


public class BureauvoteController : DbContext {

	public DbSet<Bureauvote> Bureauvote { get; set; }







}
