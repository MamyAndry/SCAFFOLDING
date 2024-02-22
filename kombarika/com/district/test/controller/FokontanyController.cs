namespace com.district.test.controller;


using System.Diagnostics;
using Microsoft.AspNetCore.Mvc;
using com.district.test.repository.FokontanyContext;
using com.district.test.entity;
using ;


[ApiController]
[Route("api/[[fokontany]]")]
public class FokontanyController : Controller {

	private readonly FokontanyDbContext _context;
	private readonly ILogger<FokontanyController> _logger;


	[HttpPost]
	public ActionResult<Fokontany> save([FromBody] Fokontany fokontany){
	 	_context.Fokontany.Add(fokontany);
		_context.SaveChanges();
		return Ok();
	}
	[HttpPut]
	public ActionResult<Fokontany> update([FromBody] Fokontany fokontany){
	 	var temp = fokontany;
		_context.SaveChanges();
		return Ok();
	}
	[HttpDelete]
	public ActionResult<Fokontany> delete([FromBody] Fokontany fokontany){
	 	_context.Fokontany.Remove(fokontany);
		_context.SaveChanges();
	}
	[HttpGet]
	public ActionResult<IEnumerable<Fokontany>> findAll(){
	 	return Ok(_context.Fokontany.ToList());
	}

	public FokontanyController(FokontanyDbContext context) { _context = context; }



}
