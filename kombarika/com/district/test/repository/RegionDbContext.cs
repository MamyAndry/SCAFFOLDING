namespace com.district.test.repository;


using com.district.test.entity.Region;
using using Microsoft.EntityFrameworkCore;


public class RegionController : DbContext {

	public DbSet<Region> Region { get; set; }







}
