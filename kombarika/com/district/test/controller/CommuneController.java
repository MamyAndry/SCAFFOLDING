package com.district.test.controller;


import com.district.test.repository.CommuneRepository;
import com.district.test.entity.Commune;
import org.springframework.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin("*")
@RequestMapping(path = "commune")
public class CommuneController
 {

	@Autowired
	private CommuneRepository repository;


	@PostMapping()
	public ResponseEntity<Commune> save(@RequestBody Commune commune){
	 	return ResponseEntity.ok(repository.save(commune));
	}
	@PutMapping()
	public ResponseEntity<Commune> update(@RequestBody Commune commune){
	 	return ResponseEntity.ok(repository.save(commune));
	}
	@DeleteMapping()
	public void delete(@RequestBody Commune commune){
	 	repository.delete(commune);
	}
	@GetMapping()
	public ResponseEntity<Iterable<Commune>> findAll(){
	 	return ResponseEntity.ok(repository.findAll());
	}





}
