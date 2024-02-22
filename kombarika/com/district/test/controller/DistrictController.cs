namespace com.district.test.controller;


using System.Diagnostics;
using Microsoft.AspNetCore.Mvc;
using com.district.test.repository.DistrictContext;
using com.district.test.entity;
using ;


[ApiController]
[Route("api/[[district]]")]
public class DistrictController : Controller {

	private readonly DistrictDbContext _context;
	private readonly ILogger<DistrictController> _logger;


	[HttpPost]
	public ActionResult<District> save([FromBody] District district){
	 	_context.District.Add(district);
		_context.SaveChanges();
		return Ok();
	}
	[HttpPut]
	public ActionResult<District> update([FromBody] District district){
	 	var temp = district;
		_context.SaveChanges();
		return Ok();
	}
	[HttpDelete]
	public ActionResult<District> delete([FromBody] District district){
	 	_context.District.Remove(district);
		_context.SaveChanges();
	}
	[HttpGet]
	public ActionResult<IEnumerable<District>> findAll(){
	 	return Ok(_context.District.ToList());
	}

	public DistrictController(DistrictDbContext context) { _context = context; }



}
