package br.edu.up.entidades;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Carro {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String modelo;
	private float valorDia;
	private int unidades;
	
    @OneToMany(mappedBy = "carro", cascade = CascadeType.ALL)
    private List<Aluguel> alugueis;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public float getValorDia() {
		return valorDia;
	}
	public void setValorDia(float valorDia) {
		this.valorDia = valorDia;
	}
	public int getUnidades() {
		return unidades;
	}
	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}
	



	
}
