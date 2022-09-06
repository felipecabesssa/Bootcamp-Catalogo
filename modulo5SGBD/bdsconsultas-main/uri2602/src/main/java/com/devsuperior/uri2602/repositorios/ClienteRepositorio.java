package com.devsuperior.uri2602.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.uri2602.dtos.ClienteMinDTO;
import com.devsuperior.uri2602.entities.Customer;
import com.devsuperior.uri2602.projecoes.ClienteMinProjecao;

public interface ClienteRepositorio extends JpaRepository<Customer, Long>{
	
	@Query(nativeQuery = true, value = "SELECT name "
			+ "FROM customers "
			+ "WHERE UPPER(state) = UPPER(:state)")
	List<ClienteMinProjecao> buscaNativeQuery(String state);

	@Query("SELECT new com.devsuperior.uri2602.dtos.ClienteMinDTO(obj.name) "
			+ "FROM Customer obj "
			+ "WHERE UPPER(obj.state) = UPPER(:state)")
	List<ClienteMinDTO> buscaJPQL(String state);	
	
}
