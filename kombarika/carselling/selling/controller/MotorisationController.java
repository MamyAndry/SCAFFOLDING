package carselling.selling.controller;


import carselling.selling.repository.MotorisationRepository;
import carselling.selling.entity.Motorisation;
import org.springframework.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "motorisation")
public class MotorisationController
 {

	@Autowired
	private MotorisationRepository repository;


	@PostMapping()
	public ResponseEntity<Motorisation> save(@RequestBody Motorisation motorisation){
	 	return ResponseEntity.ok(repository.save(motorisation));
	}
	@PutMapping()
	public ResponseEntity<Motorisation> update(@RequestBody Motorisation motorisation){
	 	return ResponseEntity.ok(repository.save(motorisation));
	}
	@DeleteMapping()
	public void delete(@RequestBody Motorisation motorisation){
	 	repository.delete(motorisation);
	}
	@GetMapping()
	public ResponseEntity<Iterable<Motorisation>> findAll(){
	 	return ResponseEntity.ok(repository.findAll());
	}





}
