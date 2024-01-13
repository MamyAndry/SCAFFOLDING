package carselling.selling.controller;


import carselling.selling.repository.CarRepository;
import carselling.selling.entity.Car;
import org.springframework.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "car")
public class CarController
 {

	@Autowired
	private CarRepository repository;


	@PostMapping()
	public ResponseEntity<Car> save(@RequestBody Car car){
	 	return ResponseEntity.ok(repository.save(car));
	}
	@PutMapping()
	public ResponseEntity<Car> update(@RequestBody Car car){
	 	return ResponseEntity.ok(repository.save(car));
	}
	@DeleteMapping()
	public void delete(@RequestBody Car car){
	 	repository.delete(car);
	}
	@GetMapping()
	public ResponseEntity<Iterable<Car>> findAll(){
	 	return ResponseEntity.ok(repository.findAll());
	}





}
