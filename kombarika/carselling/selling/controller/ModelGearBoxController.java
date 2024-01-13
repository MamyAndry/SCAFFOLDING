package carselling.selling.controller;


import carselling.selling.repository.ModelGearBoxRepository;
import carselling.selling.entity.ModelGearBox;
import org.springframework.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "modelGearBox")
public class ModelGearBoxController
 {

	@Autowired
	private ModelGearBoxRepository repository;


	@PostMapping()
	public ResponseEntity<ModelGearBox> save(@RequestBody ModelGearBox modelGearBox){
	 	return ResponseEntity.ok(repository.save(modelGearBox));
	}
	@PutMapping()
	public ResponseEntity<ModelGearBox> update(@RequestBody ModelGearBox modelGearBox){
	 	return ResponseEntity.ok(repository.save(modelGearBox));
	}
	@DeleteMapping()
	public void delete(@RequestBody ModelGearBox modelGearBox){
	 	repository.delete(modelGearBox);
	}
	@GetMapping()
	public ResponseEntity<Iterable<ModelGearBox>> findAll(){
	 	return ResponseEntity.ok(repository.findAll());
	}





}
