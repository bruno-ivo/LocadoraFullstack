package br.com.bruno.locadora.repository;

import br.com.bruno.locadora.modelo.Filme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmeRepository extends JpaRepository<Filme, Long>{

	List<Filme> findByNomeDoFilme(String nomeDoFilme);

    Filme findByTipo(String tipo);
}
