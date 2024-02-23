namespace com.district.test.controller;


using System.Diagnostics;
using Microsoft.AspNetCore.Mvc;
using com.district.test.repository.CommuneContext;
using com.district.test.entity;
using ;


[ApiController]
[Route("api/[[commune]]")]
public class CommuneController : Controller {

	private readonly CommuneDbContext _context;
	private readonly ILogger<CommuneController> _logger;


	[HttpPost]
	public ActionResult<Commune> save([FromBody] Commune commune){
	 	_context.Commune.Add(commune);
		_context.SaveChanges();
		return Ok();
	}
	[HttpPut]
	public ActionResult<Commune> update([FromBody] Commune commune){
	 	var temp = commune;
		_context.SaveChanges();
		return Ok();
	}
	[HttpDelete]
	public ActionResult<Commune> delete([FromBody] Commune commune){
	 	_context.Commune.Remove(commune);
		_context.SaveChanges();
	}
	[HttpGet]
	public ActionResult<IEnumerable<Commune>> findAll(){
	 	return Ok(_context.Commune.ToList());
	}

	public CommuneController(CommuneDbContext context) { _context = context; }



}
