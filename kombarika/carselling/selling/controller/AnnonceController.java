package carselling.selling.controller;


import carselling.selling.repository.AnnonceRepository;
import carselling.selling.entity.Annonce;
import org.springframework.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "annonce")
public class AnnonceController
 {

	@Autowired
	private AnnonceRepository repository;


	@PostMapping()
	public ResponseEntity<Annonce> save(@RequestBody Annonce annonce){
	 	return ResponseEntity.ok(repository.save(annonce));
	}
	@PutMapping()
	public ResponseEntity<Annonce> update(@RequestBody Annonce annonce){
	 	return ResponseEntity.ok(repository.save(annonce));
	}
	@DeleteMapping()
	public void delete(@RequestBody Annonce annonce){
	 	repository.delete(annonce);
	}
	@GetMapping()
	public ResponseEntity<Iterable<Annonce>> findAll(){
	 	return ResponseEntity.ok(repository.findAll());
	}





}
