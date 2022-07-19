package br.com.felipesa.catalogo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.felipesa.catalogo.entidades.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

}
