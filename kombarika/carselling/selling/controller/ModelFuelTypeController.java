package carselling.selling.controller;


import carselling.selling.repository.ModelFuelTypeRepository;
import carselling.selling.entity.ModelFuelType;
import org.springframework.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "modelFuelType")
public class ModelFuelTypeController
 {

	@Autowired
	private ModelFuelTypeRepository repository;


	@PostMapping()
	public ResponseEntity<ModelFuelType> save(@RequestBody ModelFuelType modelFuelType){
	 	return ResponseEntity.ok(repository.save(modelFuelType));
	}
	@PutMapping()
	public ResponseEntity<ModelFuelType> update(@RequestBody ModelFuelType modelFuelType){
	 	return ResponseEntity.ok(repository.save(modelFuelType));
	}
	@DeleteMapping()
	public void delete(@RequestBody ModelFuelType modelFuelType){
	 	repository.delete(modelFuelType);
	}
	@GetMapping()
	public ResponseEntity<Iterable<ModelFuelType>> findAll(){
	 	return ResponseEntity.ok(repository.findAll());
	}





}
