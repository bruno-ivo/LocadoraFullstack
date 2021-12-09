package br.com.bruno.locadora.modelo;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "filmes")
public class Filme {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String codigoDoFilme;
	private String nomeDoFilme;

	
	@Enumerated(EnumType.STRING)
	private TipoDeMidiaEnum tipo;

	private BigDecimal valorUnitarioDoFilme;




	public BigDecimal getValorUnitarioDoFilme() {
		return valorUnitarioDoFilme;
	}

	public void setValorUnitarioDoFilme(BigDecimal valorUnitarioDoFilme) {
		this.valorUnitarioDoFilme = valorUnitarioDoFilme;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigoDoFilme() {
		return codigoDoFilme;
	}

	public void setCodigoDoFilme(String codigo) {
		this.codigoDoFilme = codigo;
	}

	public String getNomeDoFilme() {
		return nomeDoFilme;
	}

	public void setNomeDoFilme(String nomeDoFilme) {
		this.nomeDoFilme = nomeDoFilme;
	}


	public TipoDeMidiaEnum getTipo() {
		return tipo;
	}

	public void setTipo(TipoDeMidiaEnum tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "Filme [Titulo : " + nomeDoFilme +  ", tipo = " + tipo + "]";
	}	
	

}
