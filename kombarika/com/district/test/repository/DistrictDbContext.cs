namespace com.district.test.repository;


using com.district.test.entity.District;
using using Microsoft.EntityFrameworkCore;


public class DistrictController : DbContext {

	public DbSet<District> District { get; set; }







}
