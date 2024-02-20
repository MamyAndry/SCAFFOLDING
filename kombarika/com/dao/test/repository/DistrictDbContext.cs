namespace com.dao.test.repository;


using com.dao.test.entity;
using Microsoft.EntityFrameworkCore;


public class DistrictDbContext : DbContext {

	public DbSet<District> District { get; set; }







}
