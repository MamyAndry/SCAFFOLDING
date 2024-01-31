package com.district.test.controller;


import com.district.test.repository.DistrictRepository;
import com.district.test.entity.District;
import org.springframework.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin("*")
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
	 	repository.delete(district);
	}
	@GetMapping()
	public ResponseEntity<Iterable<District>> findAll(){
	 	return ResponseEntity.ok(repository.findAll());
	}





}
