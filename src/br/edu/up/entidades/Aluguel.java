package br.edu.up.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Aluguel {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@OneToOne
	private Cliente cliente;
	@OneToOne
	private Carro carro;
	private int diasAlugados;
	private String dataInicio;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Carro getCarro() {
		return carro;
	}
	public void setCarro(Carro carro) {
		this.carro = carro;
	}
	public int getDiasAlugados() {
		return diasAlugados;
	}
	public void setDiasAlugados(int diasAlugados) {
		this.diasAlugados = diasAlugados;
	}
	public String getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(String dataInicio) {
		this.dataInicio = dataInicio;
	}
	
	
	
}
