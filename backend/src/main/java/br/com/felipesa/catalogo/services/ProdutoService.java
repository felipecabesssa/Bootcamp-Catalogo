package br.com.felipesa.catalogo.services;

import java.util.Optional;
import java.util.Set;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.felipesa.catalogo.dto.ProdutoDTO;
import br.com.felipesa.catalogo.entidades.Categoria;
import br.com.felipesa.catalogo.entidades.Produto;
import br.com.felipesa.catalogo.repositories.ProdutoRepository;
import br.com.felipesa.catalogo.services.exceptions.EntidadeNaoEncontradaException;
import br.com.felipesa.catalogo.services.exceptions.IntegridadeReferencialException;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository repositorio;

	@Transactional(readOnly = true)
	public Page<ProdutoDTO> buscaTodasServicePaginado(PageRequest pageRequest){		
		Page<Produto> list = repositorio.findAll(pageRequest);
		return list.map(x -> new ProdutoDTO(x));
	}

	@Transactional(readOnly = true)
	public ProdutoDTO buscaPorIdService(Long id) {
		Optional<Produto> obj = repositorio.findById(id);
		Produto entidade = obj.orElseThrow(() -> new EntidadeNaoEncontradaException("Entidade não encontrada"));
		return new ProdutoDTO(entidade, entidade.getCategorias());
	}

	@Transactional
	public ProdutoDTO insereProdutoService(ProdutoDTO dto) {
		Produto entidade = new Produto();
		//entidade.setNome(dto.getNome());
		entidade = repositorio.save(entidade);
		
		return new ProdutoDTO(entidade);
	}

	@Transactional
	public ProdutoDTO alteraProdutoService(Long id, ProdutoDTO dto) {
		try {
			Produto entidade = repositorio.getReferenceById(id);
			//entidade.setNome(dto.getNome());
			entidade = repositorio.save(entidade);
			return new ProdutoDTO(entidade);
		}
		catch (EntityNotFoundException e){
			throw new EntidadeNaoEncontradaException("Id " + id + " não encontrado");
		}
	}

	public void deletaProdutoService(Long id) {
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
