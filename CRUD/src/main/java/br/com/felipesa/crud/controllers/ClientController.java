package br.com.felipesa.crud.controllers;

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

import br.com.felipesa.crud.dtos.ClientDTO;
import br.com.felipesa.crud.dtos.ClientDTOMin;
import br.com.felipesa.crud.services.ClientService;

@RestController
@RequestMapping(value="/clients")
public class ClientController {
	
	@Autowired
	private ClientService serviceClient;
	
	@GetMapping
	public ResponseEntity<List<ClientDTOMin>> busqueTodosMin(){
		List<ClientDTOMin> lista = serviceClient.busqueTodosMin();
		return ResponseEntity.ok().body(lista);
	}
	
	@GetMapping(value = "/{id}")
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
