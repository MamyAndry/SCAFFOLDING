package carselling.selling.controller;


import carselling.selling.repository.FuelTypeRepository;
import carselling.selling.entity.FuelType;
import org.springframework.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "fuelType")
public class FuelTypeController
 {

	@Autowired
	private FuelTypeRepository repository;


	@PostMapping()
	public ResponseEntity<FuelType> save(@RequestBody FuelType fuelType){
	 	return ResponseEntity.ok(repository.save(fuelType));
	}
	@PutMapping()
	public ResponseEntity<FuelType> update(@RequestBody FuelType fuelType){
	 	return ResponseEntity.ok(repository.save(fuelType));
	}
	@DeleteMapping()
	public void delete(@RequestBody FuelType fuelType){
	 	repository.delete(fuelType);
	}
	@GetMapping()
	public ResponseEntity<Iterable<FuelType>> findAll(){
	 	return ResponseEntity.ok(repository.findAll());
	}





}
