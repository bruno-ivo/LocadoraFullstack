package br.com.bruno.locadora.controller.form;

import br.com.bruno.locadora.modelo.Cliente;
import br.com.bruno.locadora.repository.ClienteRepository;

public class ClienteForm {
	
	private String nome;
	private String codigoDoCLiente;

	public String getCodigoDoCLiente() {
		return codigoDoCLiente;
	}

	public void setCodigoDoCLiente(String codigoDoCLiente) {
		this.codigoDoCLiente = codigoDoCLiente;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Cliente converter() {
		Cliente c = new Cliente();
		c.setNome(nome);
		c.setCodigoDoCliente(codigoDoCLiente);
		return c;
	}


    public Cliente atualizar(long id, ClienteRepository clienteRepository) {
		Cliente cliente = clienteRepository.getById(id);
		cliente.setNome(this.nome);
		cliente.setCodigoDoCliente(this.codigoDoCLiente);
		return cliente;
    }
}
