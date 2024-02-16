package com.district.test.controller;




@
@RequestMapping(path = "region")
public class RegionController
 {

	@Autowired
	private RegionRepository repository;


	@PostMapping()
	public ResponseEntity<Region> save(@RequestBody Region region){
	 	return ResponseEntity.ok(repository.save(region));
	}
	@PutMapping()
	public ResponseEntity<Region> update(@RequestBody Region region){
	 	return ResponseEntity.ok(repository.save(region));
	}
	@DeleteMapping()
	public void delete(@RequestBody Region region){
	 	return ResponseEntity.ok(repository.delete(region));
	}
	@GetMapping()
	public ResponseEntity<Iterable<Region>> findAll(){
	 	return ResponseEntity.ok(repository.findAll());
	}





}
