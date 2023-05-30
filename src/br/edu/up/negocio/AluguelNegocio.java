package br.edu.up.negocio;

import br.edu.up.entidades.Aluguel;

public class AluguelNegocio {

	public static float calcularValorTotal(Aluguel aluguel) {
		return aluguel.getDiasAlugados() * aluguel.getCarro().getValorDia();
	}
}
