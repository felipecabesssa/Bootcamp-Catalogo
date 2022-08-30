package br.com.felipesa.crud.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.felipesa.crud.dtos.ClientDTO;
import br.com.felipesa.crud.dtos.ClientDTOMin;
import br.com.felipesa.crud.entities.Client;
import br.com.felipesa.crud.repositories.ClientRepository;
import br.com.felipesa.crud.services.exceptions.EntidadeNaoEncontradaException;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository repositorioCliente;
	
	@Transactional(readOnly = true)
	public List<ClientDTOMin> busqueTodosMin() {
		List<Client> lista = repositorioCliente.findAll();
		return lista.stream().map(x -> new ClientDTOMin(x)).collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public ClientDTOMin busquePorIdMin(Long id) {
		Optional<Client> obj = repositorioCliente.findById(id);
		Client entidade = obj.get();

		return new ClientDTOMin(entidade);
	}
	
	@Transactional
	public ClientDTO insereClientService(ClientDTO dto) {
		Client entidade = new Client();
		entidade.setName(dto.getName());
		entidade.setCpf(dto.getCpf());
		entidade.setIncome(dto.getIncome());
		entidade.setChildren(dto.getChildren());
		entidade.setBirthDate(dto.getBirthDate());
		entidade = repositorioCliente.save(entidade);

		return new ClientDTO(entidade);
	}

	public ClientDTO alteraClientService(Long id, ClientDTO dto) {
		try{
			Client entidade = repositorioCliente.getReferenceById(id);
			entidade.setName(dto.getName());
			entidade.setCpf(dto.getCpf());
			entidade.setIncome(dto.getIncome());
			entidade.setChildren(dto.getChildren());
			entidade.setBirthDate(dto.getBirthDate());
			entidade = repositorioCliente.save(entidade);
			
			return new ClientDTO(entidade);
		} 
		catch (EntityNotFoundException e){
			throw new EntidadeNaoEncontradaException("Id" + id + "não encontrado");
		}
	}

	public void deletaClientService(Long id) {
		try {
			repositorioCliente.deleteById(id);
		}
		catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException("Id " + id + " não encontrado");
		}
		
	}



}
