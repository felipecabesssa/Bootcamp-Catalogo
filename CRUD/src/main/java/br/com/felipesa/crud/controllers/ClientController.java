package br.com.felipesa.crud.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.felipesa.crud.dtos.ClientDTO;
import br.com.felipesa.crud.dtos.ClientDTOMin;
import br.com.felipesa.crud.services.ClientService;

@RestController
@RequestMapping(value="/clients")
public class ClientController {
	
	@Autowired
	private ClientService serviceClient;
	
	@GetMapping
	public ResponseEntity<Page<ClientDTO>> busqueTodos(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction,			
			@RequestParam(value = "orderBy", defaultValue = "name") String orderBy
			){

		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);

		Page<ClientDTO> lista = serviceClient.busqueTodosPaginado(pageRequest);
		
		return ResponseEntity.ok().body(lista);
	}
	
	@GetMapping(value = "/min")
	public ResponseEntity<List<ClientDTOMin>> busqueTodosMin(){
		List<ClientDTOMin> lista = serviceClient.busqueTodosMin();
		return ResponseEntity.ok().body(lista);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ClientDTO> busquePorId(@PathVariable Long id){
		ClientDTO dto = serviceClient.busquePorId(id);
		return ResponseEntity.ok().body(dto);
	}
	
	@GetMapping(value = "/{id}/min")
	public ResponseEntity<ClientDTOMin> busquePorIdMin(@PathVariable Long id){
		ClientDTOMin dto = serviceClient.busquePorIdMin(id);
		return ResponseEntity.ok().body(dto);
	}
	
	
	@PostMapping
	public ResponseEntity<ClientDTO> insereCliente(@RequestBody ClientDTO dto){
		dto = serviceClient.insereClientService(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
		
		return ResponseEntity.created(uri).body(dto);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<ClientDTO> alteraCliente(@PathVariable Long id, @RequestBody ClientDTO dto){
		dto = serviceClient.alteraClientService(id, dto);		
		return ResponseEntity.ok().body(dto);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<ClientDTO> deletaClientePorId(@PathVariable Long id){
		serviceClient.deletaClientService(id);
		return ResponseEntity.noContent().build();
		
	}

}
