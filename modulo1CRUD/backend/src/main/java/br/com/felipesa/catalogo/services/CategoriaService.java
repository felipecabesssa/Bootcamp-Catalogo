package br.com.felipesa.catalogo.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.felipesa.catalogo.dto.CategoriaDTO;
import br.com.felipesa.catalogo.entidades.Categoria;
import br.com.felipesa.catalogo.repositories.CategoriaRepository;
import br.com.felipesa.catalogo.services.exceptions.EntidadeNaoEncontradaException;
import br.com.felipesa.catalogo.services.exceptions.IntegridadeReferencialException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repositorio;

	@Transactional(readOnly = true)
	public Page<CategoriaDTO> buscaTodasServicePaginado(PageRequest pageRequest){		
		Page<Categoria> list = repositorio.findAll(pageRequest);
		return list.map(x -> new CategoriaDTO(x));
	}

	@Transactional(readOnly = true)
	public CategoriaDTO buscaPorIdService(Long id) {
		Optional<Categoria> obj = repositorio.findById(id);
		Categoria entity = obj.orElseThrow(() -> new EntidadeNaoEncontradaException("Entidade não encontrada"));
		return new CategoriaDTO(entity);
	}

	@Transactional
	public CategoriaDTO insereCategoriaService(CategoriaDTO dto) {
		Categoria entidade = new Categoria();
		entidade.setNome(dto.getNome());
		entidade = repositorio.save(entidade);
		
		return new CategoriaDTO(entidade);
	}

	@Transactional
	public CategoriaDTO alteraCategoriaService(Long id, CategoriaDTO dto) {
		try {
			Categoria entidade = repositorio.getReferenceById(id);
			entidade.setNome(dto.getNome());
			entidade = repositorio.save(entidade);
			return new CategoriaDTO(entidade);
		}
		catch (EntityNotFoundException e){
			throw new EntidadeNaoEncontradaException("Id " + id + " não encontrado");
		}
	}

	public void deletaCategoriaService(Long id) {
		try {
			repositorio.deleteById(id);
		}
		catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException("Id " + id + " não encontrado");
		}
		catch (DataIntegrityViolationException e) {
			throw new IntegridadeReferencialException("Violação de Integridade, a categoria tem produtos associados");
		}
	}
	
}
