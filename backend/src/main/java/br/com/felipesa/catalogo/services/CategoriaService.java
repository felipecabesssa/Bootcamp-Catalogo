package br.com.felipesa.catalogo.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.felipesa.catalogo.dto.CategoriaDTO;
import br.com.felipesa.catalogo.entidades.Categoria;
import br.com.felipesa.catalogo.repositories.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repository;

	@Transactional(readOnly = true)
	public List<CategoriaDTO> findAll(){		
		List<Categoria> list = repository.findAll();
		return list.stream().map(x -> new CategoriaDTO(x)).collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public CategoriaDTO findBYId(Long id) {
		Optional<Categoria> obj = repository.findById(id);
		Categoria entity = obj.get();
		
		return new CategoriaDTO(entity);
	}
	
}
