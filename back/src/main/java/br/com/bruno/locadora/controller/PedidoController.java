package br.com.bruno.locadora.controller;

import br.com.bruno.locadora.modelo.ItemPedido;
import br.com.bruno.locadora.modelo.Pedido;
import br.com.bruno.locadora.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> getPedido (@PathVariable Long id, UriComponentsBuilder uriBuilder){
        Optional <Pedido> p = pedidoRepository.findById(id);
        if(p.isPresent()){
            return ResponseEntity.ok().body(p.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public List<Pedido> listarPedidos (){
        List<Pedido> pedidos = pedidoRepository.findAll();
        return pedidos;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Pedido> cadastrarPedido (@RequestBody Pedido pedido, UriComponentsBuilder uriBuilder){
        pedido.setValorTotal(BigDecimal.ZERO);
        for (ItemPedido item : pedido.getItensDoPedido()) {
            item.setPedido(pedido);
            item.setValorTotal(item.getFilme().getValorUnitarioDoFilme().multiply(new BigDecimal(item.getQuantidade())));
            pedido.setValorTotal(pedido.getValorTotal().add(item.getValorTotal()));
        }

        pedidoRepository.save(pedido);


        URI uri = uriBuilder.path("/pedidos/{id}").buildAndExpand(pedido.getId()).toUri();
        return ResponseEntity.created(uri).body(pedido);
    }

    @PutMapping
    @Transactional
    public ResponseEntity <Pedido> atualizarPedido(@RequestBody Pedido pedido){
        pedido.setValorTotal(BigDecimal.ZERO);
        for (ItemPedido item : pedido.getItensDoPedido()) {
            item.setPedido(pedido);
            item.setValorTotal(item.getFilme().getValorUnitarioDoFilme().multiply(new BigDecimal(item.getQuantidade())));
            pedido.setValorTotal(pedido.getValorTotal().add(item.getValorTotal()));
        }
        pedidoRepository.save(pedido);

        return ResponseEntity.ok(pedido);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity <?> deletarPedido (@PathVariable Long id){
        Optional <Pedido> p = pedidoRepository.findById(id);
        if(p.isPresent()){
            pedidoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
