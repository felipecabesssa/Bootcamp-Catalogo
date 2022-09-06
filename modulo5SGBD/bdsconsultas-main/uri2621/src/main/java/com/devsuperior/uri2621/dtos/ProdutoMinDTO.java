package com.devsuperior.uri2621.dtos;

import com.devsuperior.uri2621.projecoes.ProdutoMinProjecao;

public class ProdutoMinDTO {
	
	private String name;
	
	public ProdutoMinDTO() {
	}

	public ProdutoMinDTO(String name) {
		this.name = name;
	}

	public ProdutoMinDTO(ProdutoMinProjecao projecao) {
		name = projecao.getName();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "ProdutoMinDTO [name=" + name + "]";
	}	
	
	
	
}
