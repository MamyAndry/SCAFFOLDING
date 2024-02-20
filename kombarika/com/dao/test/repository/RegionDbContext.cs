namespace com.dao.test.repository;


using com.dao.test.entity;
using Microsoft.EntityFrameworkCore;


public class RegionDbContext : DbContext {

	public DbSet<Region> Region { get; set; }







}
