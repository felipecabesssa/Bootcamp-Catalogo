package br.com.felipesa.catalogo.controllers;

import java.net.URI;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.felipesa.catalogo.dto.CategoriaDTO;
import br.com.felipesa.catalogo.services.CategoriaService;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaController {
	
	@Autowired
	private CategoriaService service;
	
	@GetMapping
	public ResponseEntity<List<CategoriaDTO>> buscaTodas(){
		List<CategoriaDTO> list = service.buscaTodasService();		
		return ResponseEntity.ok().body(list);
		
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<CategoriaDTO> buscaPorId(@PathVariable Long id){
		CategoriaDTO dto = service.buscaPorIdService(id);		
		return ResponseEntity.ok().body(dto);
		
	}
	
	@PostMapping
	public ResponseEntity<CategoriaDTO> insereCategoria(@RequestBody CategoriaDTO dto){
		dto = service.insereCategoriaService(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<CategoriaDTO> alteraCategoria(@PathVariable Long id, @RequestBody CategoriaDTO dto){
		dto = service.alteraCategoriaService(id, dto);
		return ResponseEntity.ok().body(dto);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deletaCategoria(@PathVariable Long id){
		service.deletaCategoriaService(id);
		return ResponseEntity.noContent().build();
	}

}
