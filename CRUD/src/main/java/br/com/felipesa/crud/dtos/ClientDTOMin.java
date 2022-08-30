package br.com.felipesa.crud.dtos;

import java.io.Serializable;

import br.com.felipesa.crud.entities.Client;

public class ClientDTOMin  implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	private String cpf;
	
	public ClientDTOMin() {
	}
	
	public ClientDTOMin(Long id, String name, String cpf) {
		this.id = id;
		this.name = name;
		this.cpf = cpf;
	}

	public ClientDTOMin(Client entidade) {
		this.id = entidade.getId();
		this.name = entidade.getName();
		this.cpf = entidade.getCpf();
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

	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
}
