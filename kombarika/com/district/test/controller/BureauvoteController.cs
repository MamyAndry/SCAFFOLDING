namespace com.district.test.controller;


using System.Diagnostics;
using Microsoft.AspNetCore.Mvc;
using com.district.test.repository.BureauvoteContext;
using com.district.test.entity;
using ;


[ApiController]
[Route("api/[[bureauvote]]")]
public class BureauvoteController : Controller {

	private readonly BureauvoteDbContext _context;
	private readonly ILogger<BureauvoteController> _logger;


	[HttpPost]
	public ActionResult<Bureauvote> save([FromBody] Bureauvote bureauvote){
	 	_context.Bureauvote.Add(bureauvote);
		_context.SaveChanges();
		return Ok();
	}
	[HttpPut]
	public ActionResult<Bureauvote> update([FromBody] Bureauvote bureauvote){
	 	var temp = bureauvote;
		_context.SaveChanges();
		return Ok();
	}
	[HttpDelete]
	public ActionResult<Bureauvote> delete([FromBody] Bureauvote bureauvote){
	 	_context.Bureauvote.Remove(bureauvote);
		_context.SaveChanges();
	}
	[HttpGet]
	public ActionResult<IEnumerable<Bureauvote>> findAll(){
	 	return Ok(_context.Bureauvote.ToList());
	}

	public BureauvoteController(BureauvoteDbContext context) { _context = context; }



}
