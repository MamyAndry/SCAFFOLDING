namespace huhu.controller;


using System.Diagnostics;
using Microsoft.AspNetCore.Mvc;


[ApiController]
[Route('[departement]')]
public class DepartementController
 {

	[]
	private readonly Ilogger<DepartementController> _logger;


	[HttpPut(Name = '?')]
	public IEnumerable<Departement> save([] Departement departement){
	 	
	}
	[HttpPut(Name = '?')]
	public IEnumerable<Departement> update([] Departement departement){
	 	
	}
	[HttpPut(Name = '?')]
	public IEnumerable<Departement> delete([] Departement departement){
	 	
	}
	[HttpGet(Name = '?')]
	public IEnumerable<List<Departement>> findAll(){
	 	
	}





}
