package com.devsuperior.uri2621.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.uri2621.dtos.ProdutoMinDTO;
import com.devsuperior.uri2621.entities.Product;
import com.devsuperior.uri2621.projecoes.ProdutoMinProjecao;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
	@Query(nativeQuery = true, value = "SELECT products.name  "
			+ "FROM products "
			+ "INNER JOIN providers ON products.id_providers = providers.id "
			+ "WHERE products.amount BETWEEN :min AND :max "
			+ "AND providers.name LIKE CONCAT(:beginName, '%')")
	List<ProdutoMinProjecao> buscaNativa(Integer min, Integer max, String beginName);
	
	@Query("SELECT new com.devsuperior.uri2621.dtos.ProdutoMinDTO(obj.name)  "
			+ "FROM Product obj "
			+ "WHERE obj.amount BETWEEN :min AND :max "
			+ "AND obj.provider.name LIKE CONCAT(:beginName, '%')")
	List<ProdutoMinDTO> buscaJPQL(Integer min, Integer max, String beginName);

}
