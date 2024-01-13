package carselling.selling.controller;


import carselling.selling.repository.ModelMotorRepository;
import carselling.selling.entity.ModelMotor;
import org.springframework.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "modelMotor")
public class ModelMotorController
 {

	@Autowired
	private ModelMotorRepository repository;


	@PostMapping()
	public ResponseEntity<ModelMotor> save(@RequestBody ModelMotor modelMotor){
	 	return ResponseEntity.ok(repository.save(modelMotor));
	}
	@PutMapping()
	public ResponseEntity<ModelMotor> update(@RequestBody ModelMotor modelMotor){
	 	return ResponseEntity.ok(repository.save(modelMotor));
	}
	@DeleteMapping()
	public void delete(@RequestBody ModelMotor modelMotor){
	 	repository.delete(modelMotor);
	}
	@GetMapping()
	public ResponseEntity<Iterable<ModelMotor>> findAll(){
	 	return ResponseEntity.ok(repository.findAll());
	}





}
