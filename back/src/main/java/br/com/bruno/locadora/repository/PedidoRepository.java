package br.com.bruno.locadora.repository;

import br.com.bruno.locadora.modelo.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
