namespace com.dao.test.repository;


using com.dao.test.entity.District;
using Microsoft.EntityFrameworkCore;


public class DistrictDbContext : DbContext {

	public DbSet<District> District { get; set; }







}
