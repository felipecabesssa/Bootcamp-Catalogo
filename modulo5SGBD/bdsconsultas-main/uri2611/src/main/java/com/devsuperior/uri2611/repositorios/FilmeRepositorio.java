package com.devsuperior.uri2611.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.uri2611.dtos.FilmeMinDTO;
import com.devsuperior.uri2611.entities.Movie;
import com.devsuperior.uri2611.projecoes.FilmeMinProjecao;

public interface FilmeRepositorio extends JpaRepository<Movie, Long>{
	
	@Query(nativeQuery = true, value = "SELECT movies.id, movies.name "
			+ "FROM movies "
			+ "INNER JOIN genres ON movies.id_genres = genres.id  "
			+ "WHERE genres.description = :genreName")
	List<FilmeMinProjecao> buscaQueryNativa(String genreName);
	
	@Query("SELECT new com.devsuperior.uri2611.dtos.FilmeMinDTO(obj.id, obj.name) "
			+ "FROM Movie obj "
			+ "WHERE obj.genre.description = :genreName")
	List<FilmeMinDTO> buscaJPQL(String genreName);

}
