package br.com.bruno.locadora.controller;

import br.com.bruno.locadora.controller.dto.ClienteDto;
import br.com.bruno.locadora.controller.form.ClienteForm;
import br.com.bruno.locadora.modelo.Cliente;
import br.com.bruno.locadora.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@GetMapping
	public List<Cliente> listaClientes (String nomeDoCliente){
		if(nomeDoCliente == null) {
			return clienteRepository.findAll();
		} else {
			return clienteRepository.findByNome(nomeDoCliente);
		}
		
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<Cliente> cadastrarCliente (@RequestBody Cliente cliente, UriComponentsBuilder uriBuilder) {
//		Cliente cliente = form.converter();
		clienteRepository.save(cliente);

		//O uri gera um codigo 201(Conteudo criado), ao inves de codigo 200
		URI uri = uriBuilder.path("/clientes/{id}").buildAndExpand(cliente.getId()).toUri();
		return ResponseEntity.created(uri).body(cliente);
	}

	@PutMapping("/{id}")
	@Transactional  //Avisa para o spring que é para dar commit no metodo
	public ResponseEntity<ClienteDto> atualizarCliente (@PathVariable Long id,@RequestBody ClienteForm form){
		Optional<Cliente> c = clienteRepository.findById(id);
		if (c.isPresent()){
		Cliente cliente = form.atualizar(id, clienteRepository);
		return  ResponseEntity.ok(new ClienteDto(cliente));
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	@Transactional
	//O ResponseEntity pede um generics por isso foi usado o <?>
	public ResponseEntity<?> removerCliente (@PathVariable Long id){
		Optional<Cliente> c = clienteRepository.findById(id);
		if (c.isPresent()){
		clienteRepository.deleteById(id);
		return ResponseEntity.ok().build();
		}
		//Validação caso ocorra um 404 - Not Found
		return ResponseEntity.notFound().build();
	}

}
