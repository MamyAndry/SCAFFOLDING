package carselling.selling.controller;


import carselling.selling.repository.BrandRepository;
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
	 	repository.save(brand);
	}
	@PutMapping()
	public ResponseEntity<Brand> update(@RequestParam Brand brand){
	 	repository.save(brand);
	}
	@PutMapping()
	public ResponseEntity<Brand> delete(@RequestParam Brand brand){
	 	repository.delete(brand);
	}
	@GetMapping()
	public ResponseEntity<List<Brand>> findAll(){
	 	repository.findAll();
	}





}
