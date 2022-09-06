package com.devsuperior.uri2611.dtos;

import com.devsuperior.uri2611.projecoes.FilmeMinProjecao;

public class FilmeMinDTO {
	
	private Long id;
	private String name;
	
	public FilmeMinDTO() {
	}

	public FilmeMinDTO(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public FilmeMinDTO(FilmeMinProjecao projecao) {
		id = projecao.getId();
		name = projecao.getName();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "FilmeMinDTO [id=" + id + ", name=" + name + "]";
	}
	
	
}
