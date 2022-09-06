package com.devsuperior.uri2602.dtos;

import com.devsuperior.uri2602.projecoes.ClienteMinProjecao;

public class ClienteMinDTO {
	
	private String name;
	
	public ClienteMinDTO() {
	}

	public ClienteMinDTO(String name) {
		this.name = name;
	}

	public ClienteMinDTO(ClienteMinProjecao projecao) {
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
		return "ClienteMinDTO [name=" + name + "]";
	}
	
}
