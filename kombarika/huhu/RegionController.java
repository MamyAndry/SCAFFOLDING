package huhu;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = 'region')
public class RegionController
 {

	@Autowired
	private RegionRepository repository;


	@GetMapping()
	public ResponseEntity<List<Region>> findAll(){
	 
	}
	@PutMapping()
	public ResponseEntity<Region> save(@RequestParam Region region){
	 
	}





}
