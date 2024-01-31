package com.district.test.controller;


import com.district.test.repository.FokontanyRepository;
import com.district.test.entity.Fokontany;
import org.springframework.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin("*")
@RequestMapping(path = "fokontany")
public class FokontanyController
 {

	@Autowired
	private FokontanyRepository repository;


	@PostMapping()
	public ResponseEntity<Fokontany> save(@RequestBody Fokontany fokontany){
	 	return ResponseEntity.ok(repository.save(fokontany));
	}
	@PutMapping()
	public ResponseEntity<Fokontany> update(@RequestBody Fokontany fokontany){
	 	return ResponseEntity.ok(repository.save(fokontany));
	}
	@DeleteMapping()
	public void delete(@RequestBody Fokontany fokontany){
	 	repository.delete(fokontany);
	}
	@GetMapping()
	public ResponseEntity<Iterable<Fokontany>> findAll(){
	 	return ResponseEntity.ok(repository.findAll());
	}





}
