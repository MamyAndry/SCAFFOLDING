namespace com.district.test.controller;


using System.Diagnostics;
using Microsoft.AspNetCore.Mvc;
using com.district.test.repository.RegionContext;
using com.district.test.entity;
using ;


[ApiController]
[Route("api/[[region]]")]
public class RegionController : Controller {

	private readonly RegionDbContext _context;
	private readonly ILogger<RegionController> _logger;


	[HttpPost]
	public ActionResult<Region> save([FromBody] Region region){
	 	_context.Region.Add(region);
		_context.SaveChanges();
		return Ok();
	}
	[HttpPut]
	public ActionResult<Region> update([FromBody] Region region){
	 	var temp = region;
		_context.SaveChanges();
		return Ok();
	}
	[HttpDelete]
	public ActionResult<Region> delete([FromBody] Region region){
	 	_context.Region.Remove(region);
		_context.SaveChanges();
	}
	[HttpGet]
	public ActionResult<IEnumerable<Region>> findAll(){
	 	return Ok(_context.Region.ToList());
	}

	public RegionController(RegionDbContext context) { _context = context; }



}
