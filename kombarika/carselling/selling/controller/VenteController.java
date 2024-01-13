package carselling.selling.controller;


import carselling.selling.repository.VenteRepository;
import carselling.selling.entity.Vente;
import org.springframework.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "vente")
public class VenteController
 {

	@Autowired
	private VenteRepository repository;


	@PostMapping()
	public ResponseEntity<Vente> save(@RequestBody Vente vente){
	 	return ResponseEntity.ok(repository.save(vente));
	}
	@PutMapping()
	public ResponseEntity<Vente> update(@RequestBody Vente vente){
	 	return ResponseEntity.ok(repository.save(vente));
	}
	@DeleteMapping()
	public void delete(@RequestBody Vente vente){
	 	repository.delete(vente);
	}
	@GetMapping()
	public ResponseEntity<Iterable<Vente>> findAll(){
	 	return ResponseEntity.ok(repository.findAll());
	}





}
