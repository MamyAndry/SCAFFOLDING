package carselling.selling.controller;


import carselling.selling.repository.ModelRepository;
import carselling.selling.entity.Model;
import org.springframework.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "model")
public class ModelController
 {

	@Autowired
	private ModelRepository repository;


	@PostMapping()
	public ResponseEntity<Model> save(@RequestBody Model model){
	 	return ResponseEntity.ok(repository.save(model));
	}
	@PutMapping()
	public ResponseEntity<Model> update(@RequestBody Model model){
	 	return ResponseEntity.ok(repository.save(model));
	}
	@DeleteMapping()
	public void delete(@RequestBody Model model){
	 	repository.delete(model);
	}
	@GetMapping()
	public ResponseEntity<Iterable<Model>> findAll(){
	 	return ResponseEntity.ok(repository.findAll());
	}





}
