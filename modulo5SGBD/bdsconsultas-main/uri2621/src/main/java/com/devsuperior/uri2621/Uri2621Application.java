package com.devsuperior.uri2621;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.devsuperior.uri2621.dtos.ProdutoMinDTO;
import com.devsuperior.uri2621.projecoes.ProdutoMinProjecao;
import com.devsuperior.uri2621.repositories.ProductRepository;

@SpringBootApplication
public class Uri2621Application implements CommandLineRunner {

	@Autowired
	private ProductRepository repository;
	
	public static void main(String[] args) {
		SpringApplication.run(Uri2621Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		List<ProdutoMinProjecao> lista = repository.buscaNativa(10, 20, "P");
		List<ProdutoMinDTO> resultado1 = lista.stream().map(x -> new ProdutoMinDTO(x)).collect(Collectors.toList());
		
		System.out.println("\n*** RESULTADO SQL RAIZ (Natvive Query): ");
		for (ProdutoMinDTO obj : resultado1) {
			System.out.println(obj);
		}
		System.out.println("\n\n");
		
		List<ProdutoMinDTO> resultado2 = repository.buscaJPQL(10, 20, "P");
		
		for (ProdutoMinDTO obj : resultado2) {
			System.out.println(obj);
		}
	}
}
