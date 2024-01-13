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


	@PutMapping()
	public ResponseEntity<Brand> save(@RequestParam Brand brand){
	 	return ResponseEntity.ok(repository.save(brand));
	}
	@PutMapping()
	public ResponseEntity<Brand> update(@RequestParam Brand brand){
	 	return ResponseEntity.ok(repository.save(brand));
	}
	@PutMapping()
	public void delete(@RequestParam Brand brand){
	 	return ResponseEntity.ok(repository.deleteById(brand));
	}
	@GetMapping()
	public ResponseEntity<Iterable<Brand>> findAll(){
	 	return ResponseEntity.ok(repository.findAll());
	}





}
