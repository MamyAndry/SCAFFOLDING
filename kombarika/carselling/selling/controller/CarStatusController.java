package carselling.selling.controller;


import carselling.selling.repository.CarStatusRepository;
import carselling.selling.entity.CarStatus;
import org.springframework.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "carStatus")
public class CarStatusController
 {

	@Autowired
	private CarStatusRepository repository;


	@PostMapping()
	public ResponseEntity<CarStatus> save(@RequestBody CarStatus carStatus){
	 	return ResponseEntity.ok(repository.save(carStatus));
	}
	@PutMapping()
	public ResponseEntity<CarStatus> update(@RequestBody CarStatus carStatus){
	 	return ResponseEntity.ok(repository.save(carStatus));
	}
	@DeleteMapping()
	public void delete(@RequestBody CarStatus carStatus){
	 	repository.delete(carStatus);
	}
	@GetMapping()
	public ResponseEntity<Iterable<CarStatus>> findAll(){
	 	return ResponseEntity.ok(repository.findAll());
	}





}
