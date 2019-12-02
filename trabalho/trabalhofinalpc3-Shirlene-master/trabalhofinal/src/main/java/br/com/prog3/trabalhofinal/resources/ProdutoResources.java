package br.com.prog3.trabalhofinal.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.prog3.trabalhofinal.domain.Produto;
import br.com.prog3.trabalhofinal.repository.IProdutoRepository;

@RestController
@RequestMapping("/produtos")
public class ProdutoResources {
	@Autowired
	private IProdutoRepository repository;

	ProdutoResources(IProdutoRepository produtoRepository) {
		this.repository = produtoRepository;
	}

	@GetMapping
	public List<Produto> findAll() {
		return repository.findAll();
	}

	@GetMapping(path = { "/{id}" })
	public ResponseEntity<Produto> findById(@PathVariable long id) {
		return repository.findById(id).map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	public Produto create(@RequestBody Produto produto) {
		return repository.save(produto);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Produto> update(@PathVariable("id") long id, @RequestBody Produto produto) {
		return repository.findById(id).map(record -> {
			record.setNome(produto.getNome());
			record.setDescricao(produto.getDescricao());
			record.setPreco_compra(produto.getPreco_compra());
			record.setPreco_venda(produto.getPreco_venda());
			record.setDesconto(produto.getDesconto());
			record.setImagem(produto.getImagem());
			record.setGarantia(produto.getGarantia());
			record.setFabricante(produto.getFabricante());
			Produto updated = repository.save(record);
			return ResponseEntity.ok().body(updated);
		}).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping(path = { "/{id}" })
	public ResponseEntity<?> delete(@PathVariable long id) {
		return repository.findById(id).map(record -> {
			repository.deleteById(id);
			return ResponseEntity.ok().build();
		}).orElse(ResponseEntity.notFound().build());
	}

	
}
