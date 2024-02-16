package com.district.test.controller;




@
@RequestMapping(path = "district")
public class DistrictController
 {

	@Autowired
	private DistrictRepository repository;


	@PostMapping()
	public ResponseEntity<District> save(@RequestBody District district){
	 	return ResponseEntity.ok(repository.save(district));
	}
	@PutMapping()
	public ResponseEntity<District> update(@RequestBody District district){
	 	return ResponseEntity.ok(repository.save(district));
	}
	@DeleteMapping()
	public void delete(@RequestBody District district){
	 	return ResponseEntity.ok(repository.delete(district));
	}
	@GetMapping()
	public ResponseEntity<Iterable<District>> findAll(){
	 	return ResponseEntity.ok(repository.findAll());
	}





}
