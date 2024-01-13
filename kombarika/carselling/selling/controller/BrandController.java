package carselling.selling.controller;


import carselling.selling.repository.BrandRepository;
import carselling.selling.entity.Brand;
import org.springframework.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "brand")
public class BrandController
 {

	@Autowired
	private BrandRepository repository;


	@PostMapping()
	public ResponseEntity<Brand> save(@RequestBody Brand brand){
	 	return ResponseEntity.ok(repository.save(brand));
	}
	@PutMapping()
	public ResponseEntity<Brand> update(@RequestBody Brand brand){
	 	return ResponseEntity.ok(repository.save(brand));
	}
	@DeleteMapping()
	public void delete(@RequestBody Brand brand){
	 	repository.delete(brand);
	}
	@GetMapping()
	public ResponseEntity<Iterable<Brand>> findAll(){
	 	return ResponseEntity.ok(repository.findAll());
	}





}
