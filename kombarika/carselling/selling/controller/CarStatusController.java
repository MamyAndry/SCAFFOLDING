package carselling.selling.controller;


import carselling.selling.repository.CarStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "carStatus")
public class CarStatusController
 {

	@Autowired
	private CarStatusRepository repository;


	@PutMapping()
	public ResponseEntity<CarStatus> save(@RequestParam CarStatus carStatus){
	 	repository.save(carStatus);
	}
	@PutMapping()
	public ResponseEntity<CarStatus> update(@RequestParam CarStatus carStatus){
	 	repository.save(carStatus);
	}
	@PutMapping()
	public ResponseEntity<CarStatus> delete(@RequestParam CarStatus carStatus){
	 	repository.delete(carStatus);
	}
	@GetMapping()
	public ResponseEntity<List<CarStatus>> findAll(){
	 	repository.findAll();
	}





}
