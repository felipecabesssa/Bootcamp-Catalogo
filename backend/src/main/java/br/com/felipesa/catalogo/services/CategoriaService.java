package br.com.felipesa.catalogo.services;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.felipesa.catalogo.entidades.Categoria;
import br.com.felipesa.catalogo.repositories.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repository;

	@Transactional(readOnly = true)
	public List<Categoria> findAll(){
		
		return repository.findAll();
		
	}
	
}
