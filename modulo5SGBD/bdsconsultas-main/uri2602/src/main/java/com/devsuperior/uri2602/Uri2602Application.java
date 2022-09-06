package com.devsuperior.uri2602;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.devsuperior.uri2602.dtos.ClienteMinDTO;
import com.devsuperior.uri2602.projecoes.ClienteMinProjecao;
import com.devsuperior.uri2602.repositorios.ClienteRepositorio;

@SpringBootApplication
public class Uri2602Application implements CommandLineRunner{
	
	@Autowired
	private ClienteRepositorio repositorio;

	public static void main(String[] args) {
		SpringApplication.run(Uri2602Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		List<ClienteMinProjecao> lista = repositorio.buscaNativeQuery("rs");
		List<ClienteMinDTO> resultado1 = lista.stream().map(x -> new ClienteMinDTO(x)).collect(Collectors.toList());
		
		System.out.println("\n*** RESULTADO SQL RAIZ (Natvive Query): ");
		for (ClienteMinDTO obj : resultado1) {
			System.out.println(obj);
		}
		System.out.println("\n\n");
		
		List<ClienteMinDTO> resultado2 = repositorio.buscaJPQL("rs");
		
		System.out.println("\n*** RESULTADO JPQL: ");
		for (ClienteMinDTO obj : resultado2) {
			System.out.println(obj);
		}
		
	}
}
