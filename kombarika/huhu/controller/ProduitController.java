package huhu.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = 'produit')
public class ProduitController
 {

	@Autowired
	private ProduitRepository repository;


	@GetMapping()
	public ResponseEntity<List<Produit>> findAll(){
	 	repository.findAll()
	}
	@PutMapping()
	public ResponseEntity<Produit> save(@RequestParam Produit produit){
	 	repository.save(produit)
	}





}
