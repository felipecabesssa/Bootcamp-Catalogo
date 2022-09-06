package com.devsuperior.uri2611;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.devsuperior.uri2611.dtos.FilmeMinDTO;
import com.devsuperior.uri2611.projecoes.FilmeMinProjecao;
import com.devsuperior.uri2611.repositorios.FilmeRepositorio;

@SpringBootApplication
public class Uri2611Application implements CommandLineRunner{
	
	@Autowired
	private FilmeRepositorio repositorio;
	
	public static void main(String[] args) {
		SpringApplication.run(Uri2611Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		List<FilmeMinProjecao> lista = repositorio.buscaQueryNativa("Action");
		List<FilmeMinDTO> resultado1 = lista.stream().map(x -> new FilmeMinDTO(x)).collect(Collectors.toList());
		
		System.out.println("\n*** RESULTADO SQL RAIZ (Natvive Query): ");
		for (FilmeMinDTO obj : resultado1) {
			System.out.println(obj);
		}
		System.out.println("\n\n");
		
		List<FilmeMinDTO> resultado2 = repositorio.buscaJPQL("Action");
		
		System.out.println("\n*** RESULTADOJPQL: ");
		for (FilmeMinDTO obj : resultado2) {
			System.out.println(obj);
		}
		
	}
}
