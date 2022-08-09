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
import br.com.felipesa.catalogo.services.exceptions.EntityNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repositorio;

	@Transactional(readOnly = true)
	public List<CategoriaDTO> buscaTodasService(){		
		List<Categoria> list = repositorio.findAll();
		return list.stream().map(x -> new CategoriaDTO(x)).collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public CategoriaDTO buscaPorIdService(Long id) {
		Optional<Categoria> obj = repositorio.findById(id);
		Categoria entity = obj.orElseThrow(() -> new EntityNotFoundException("Entidade não encontrada"));
		return new CategoriaDTO(entity);
	}

	@Transactional
	public CategoriaDTO insereCategoriaService(CategoriaDTO dto) {
		Categoria entidade = new Categoria();
		entidade.setNome(dto.getNome());
		entidade = repositorio.save(entidade);
		
		return new CategoriaDTO(entidade);
	}
	
}
