namespace com.district.test.repository;


using entity;
using Microsoft.EntityFrameworkCore;


public class RepositoryDbContext : DbContext {

	public DbSet<Bureauvote> Bureauvote { get; set; }
	public DbSet<Commune> Commune { get; set; }
	public DbSet<District> District { get; set; }
	public DbSet<Fokontany> Fokontany { get; set; }
	public DbSet<Region> Region { get; set; }








}
