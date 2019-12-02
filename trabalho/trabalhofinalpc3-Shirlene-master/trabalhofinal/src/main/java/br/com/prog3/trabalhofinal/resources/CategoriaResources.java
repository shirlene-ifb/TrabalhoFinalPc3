package br.com.prog3.trabalhofinal.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.prog3.trabalhofinal.domain.Categoria;
import br.com.prog3.trabalhofinal.repository.ICategoriaRepository;

@RestController
@RequestMapping("/categorias")
public class CategoriaResources {
	@Autowired
	private ICategoriaRepository repository;

	CategoriaResources(ICategoriaRepository categoriaRepository) {
		this.repository = categoriaRepository;
	}

	@GetMapping
	public ResponseEntity<List<Categoria>> findAll() {
		List<Categoria> categorias = (List<Categoria>) repository.findAll();
		if (categorias == null || categorias.isEmpty()) {
			return new ResponseEntity<List<Categoria>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Categoria>>(categorias, HttpStatus.OK);
	}

	@PostMapping
	public Categoria create(@RequestBody Categoria categoria) {
		return repository.save(categoria);
	}

	@GetMapping(path = { "/{id}" })
	public ResponseEntity<?> findById(@PathVariable Long id) {
		return repository.findById(id).map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Categoria> update(@PathVariable("id") Long id, @RequestBody Categoria categoria) {
		return repository.findById(id).map(record -> {
			record.setNome(categoria.getNome());
			record.setDesconto(categoria.getDesconto());
			record.setProduto(categoria.getProduto());
			Categoria updated = repository.save(record);
			return ResponseEntity.ok().body(updated);
		}).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping(path = { "/{id}" })
	public ResponseEntity<?> delete(@PathVariable Long id) {
		return repository.findById(id).map(record -> {
			repository.deleteById(id);
			return ResponseEntity.ok().build();
		}).orElse(ResponseEntity.notFound().build());
	}

}
