package br.com.bruno.locadora.controller.form;

import br.com.bruno.locadora.modelo.Filme;
import br.com.bruno.locadora.modelo.TipoDeMidiaEnum;
import br.com.bruno.locadora.repository.FilmeRepository;

import java.math.BigDecimal;

public class FilmeForm {
	
	private String nomeDoFilme;

	private String tipo;
	private String codigoDoFilme;

	private BigDecimal valorUnitarioDoFilme;




	public BigDecimal getValorUnitarioDoFilme() {
		return valorUnitarioDoFilme;
	}

	public void setValorUnitarioDoFilme(BigDecimal valorUnitarioDoFilme) {
		this.valorUnitarioDoFilme = valorUnitarioDoFilme;
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

	public String getCodigoDoFilme() {
		return codigoDoFilme;
	}

	public void setCodigoDoFilme(String codigoDoFilme) {
		this.codigoDoFilme = codigoDoFilme;
	}

	public Filme converter() {
		Filme f = new Filme();
		f.setNomeDoFilme(nomeDoFilme);
		f.setCodigoDoFilme(codigoDoFilme);
		f.setTipo(TipoDeMidiaEnum.valueOf(tipo));
		f.setValorUnitarioDoFilme(valorUnitarioDoFilme);

		return f;
	}


    public Filme atualizar(long id, FilmeRepository filmeRepository) {
		Filme f = filmeRepository.getById(id);
		f.setNomeDoFilme(this.nomeDoFilme);
		f.setCodigoDoFilme(this.codigoDoFilme);
		f.setTipo(TipoDeMidiaEnum.valueOf(tipo));
		f.setValorUnitarioDoFilme(valorUnitarioDoFilme);

		return f;
    }
}
