package com.district.test.controller;


import com.district.test.repository.BureauvoteRepository;
import com.district.test.entity.Bureauvote;
import org.springframework.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin("*")
@RequestMapping(path = "bureauvote")
public class BureauvoteController
 {

	@Autowired
	private BureauvoteRepository repository;


	@PostMapping()
	public ResponseEntity<Bureauvote> save(@RequestBody Bureauvote bureauvote){
	 	return ResponseEntity.ok(repository.save(bureauvote));
	}
	@PutMapping()
	public ResponseEntity<Bureauvote> update(@RequestBody Bureauvote bureauvote){
	 	return ResponseEntity.ok(repository.save(bureauvote));
	}
	@DeleteMapping()
	public void delete(@RequestBody Bureauvote bureauvote){
	 	repository.delete(bureauvote);
	}
	@GetMapping()
	public ResponseEntity<Iterable<Bureauvote>> findAll(){
	 	return ResponseEntity.ok(repository.findAll());
	}





}
