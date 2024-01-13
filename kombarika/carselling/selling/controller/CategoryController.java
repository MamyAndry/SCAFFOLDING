package carselling.selling.controller;


import carselling.selling.repository.CategoryRepository;
import carselling.selling.entity.Category;
import org.springframework.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "category")
public class CategoryController
 {

	@Autowired
	private CategoryRepository repository;


	@PostMapping()
	public ResponseEntity<Category> save(@RequestBody Category category){
	 	return ResponseEntity.ok(repository.save(category));
	}
	@PutMapping()
	public ResponseEntity<Category> update(@RequestBody Category category){
	 	return ResponseEntity.ok(repository.save(category));
	}
	@DeleteMapping()
	public void delete(@RequestBody Category category){
	 	repository.delete(category);
	}
	@GetMapping()
	public ResponseEntity<Iterable<Category>> findAll(){
	 	return ResponseEntity.ok(repository.findAll());
	}





}
