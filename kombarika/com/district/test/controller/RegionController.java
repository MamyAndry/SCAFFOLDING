package com.district.test.controller;


import com.district.test.repository.RegionRepository;
import com.district.test.entity.Region;
import org.springframework.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin("*")
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
	 	repository.delete(region);
	}
	@GetMapping()
	public ResponseEntity<Iterable<Region>> findAll(){
	 	return ResponseEntity.ok(repository.findAll());
	}





}
