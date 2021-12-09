package br.com.bruno.locadora.controller.dto;

import br.com.bruno.locadora.modelo.Filme;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class FilmeDto {
	
	private Long id;
	private String nomeDoFilme;	
	private String tipo;
	private String codigo;
	private BigDecimal valorDoFilme;


	public FilmeDto(Filme filme) {
		this.id = filme.getId();
		this.nomeDoFilme = filme.getNomeDoFilme();
		this.tipo = filme.getTipo().toString();
		this.codigo = filme.getCodigoDoFilme();
		this.valorDoFilme = filme.getValorUnitarioDoFilme();
	}
	
	public static List<FilmeDto> converter(List<Filme> filmes) {
		List<FilmeDto> dtos = filmes.stream()
				.map(filme -> {
					FilmeDto dto = new FilmeDto(filme);
					return dto;
				})
				.collect(Collectors.toList());

		return dtos;
	}




	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeDoFilme() {
		return nomeDoFilme;
	}

	public void setNomeDoFilme(String nomeDoFilme) {
		this.nomeDoFilme = nomeDoFilme;
	}



	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public BigDecimal getValorDoFilme() {
		return valorDoFilme;
	}

	public void setValorDoFilme(BigDecimal valorDoFilme) {
		this.valorDoFilme = valorDoFilme;
	}
}
