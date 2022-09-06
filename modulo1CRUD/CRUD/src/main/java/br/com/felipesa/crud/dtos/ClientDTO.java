package br.com.felipesa.crud.dtos;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Column;

import br.com.felipesa.crud.entities.Client;

public class ClientDTO  implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	private String cpf;
	private Double income;
	
	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private Instant birthDate;
	
	private Integer children;
	
	public ClientDTO() {
	}
	
	public ClientDTO(Long id, String name, String cpf, Double income, Instant birthDate, Integer children) {
		this.id = id;
		this.name = name;
		this.cpf = cpf;
		this.income = income;
		this.birthDate = birthDate;
		this.children = children;
	}

	public ClientDTO(Client entidade) {
		this.id = entidade.getId();
		this.name = entidade.getName();
		this.cpf = entidade.getCpf();
		this.income = entidade.getIncome();
		this.birthDate = entidade.getBirthDate();
		this.children = entidade.getChildren();
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

	public Double getIncome() {
		return income;
	}
	public void setIncome(Double income) {
		this.income = income;
	}

	public Instant getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Instant birthDate) {
		this.birthDate = birthDate;
	}

	public Integer getChildren() {
		return children;
	}
	public void setChildren(Integer children) {
		this.children = children;
	}
	
}
