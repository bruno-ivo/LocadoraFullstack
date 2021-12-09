package br.com.bruno.locadora.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.bruno.locadora.modelo.Cliente;


public interface ClienteRepository extends JpaRepository<Cliente, Long>{

	List<Cliente> findByNome(String nomeDoCliente);

}
