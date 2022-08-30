package br.com.felipesa.crud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.felipesa.crud.entities.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{

}
