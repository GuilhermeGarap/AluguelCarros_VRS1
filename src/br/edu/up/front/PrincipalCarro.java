package br.edu.up.front;

import br.edu.up.entidades.Carro;
import br.edu.up.persistencia.CarroPersistencia;



public class PrincipalCarro {
	public PrincipalCarro(){
		int opc;
		do{
			System.out.println("\n\nMODELOS DE CARRO");
			System.out.println("1 - Criar Modelo de Carro");
			System.out.println("2 - Editar Modelo de Carro");
			System.out.println("3 - Excluir Modelo de Carro");
			System.out.println("4 - Listar Modelos de Carro");			
			System.out.println("5 - Buscar Modelo de Carro");	
			System.out.println("6 - Voltar");
			opc = Console.readInt("Opção: ");
			switch(opc){
				case 1:
					criarCarro();
					break;
				case 2:
					alterarCarro();
					break;
				case 3:
					excluirCarro();
					break;
				case 4:
					listarCarros();
					break;
				case 5:
					buscarCarro();
					break;
			}
		}while(opc != 6);
	}
	
	private static void criarCarro() {
		Carro objCarro = new Carro();
		objCarro.setModelo(Console.readString("Informe o modelo do Carro: "));
		if(CarroPersistencia.procurarPorModelo(objCarro) == null) {
			objCarro.setValorDia(Console.readFloat("Informe o valor do aluguel por dia: "));
			objCarro.setUnidades(Console.readInt("Quantidade de carros disponíveis para aluguel: "));
			if(CarroPersistencia.incluir(objCarro) == true) {
			System.out.println("\n\nCarro Cadastrado!");
			} else {
				System.out.println("Modelo de carro não cadastrado");
			}
		} else {
			System.out.println("Modelo de carro já cadastrado!");
		}
	}
	private static void buscarCarro(){
		System.out.println("\n\nBUSCAR MODELO DE CARRO");
		Carro objCarro = new Carro();
		objCarro.setModelo(Console.readString("Informe o nome do modelo que deseja buscar: "));
		objCarro = CarroPersistencia.procurarPorModelo(objCarro);
		if(objCarro != null) {
			System.out.println("-------------------------------");
			System.out.println("ID: " + objCarro.getId());
			System.out.println("Modelo: " + objCarro.getModelo());
			System.out.println("Valor por Dia: " + objCarro.getValorDia());
			System.out.println("Quantidade de carros disponíveis para aluguel: " + objCarro.getUnidades());
			System.out.println("-------------------------------");
		} else {
			System.out.println("Nenhum modelo de carro encontrado");
		}
	}
	
	private static void listarCarros() {
		System.out.println("\n\nLISTAGEM DE MARCAS DE CARROS");
		Carro objCarro = new Carro();
		objCarro.setModelo(Console.readString("Informe uma parte do nome do modelo que deseja listar: "));
		System.out.println("-------------------------------");
		for(Carro item: CarroPersistencia.getCarros(objCarro)) {
			System.out.println("ID: " + item.getId());
			System.out.println("Modelo: " + item.getModelo());
			System.out.println("Quantidade de carros disponíveis para aluguel: " + item.getUnidades());
			System.out.println("Valor do aluguel por dia: " + item.getValorDia());
			System.out.println("-------------------------------");
		}
	}
	
	private static void alterarCarro() {
		System.out.println("\n\nALTERAÇÃO NO MODELO DO CARRO");
		Carro objCarro = new Carro();
		objCarro.setModelo(Console.readString("Informe o Modelo de Carro a ser alterado: "));
		objCarro = CarroPersistencia.procurarPorModelo(objCarro);
		if(objCarro != null) {
			System.out.println("\n\n-----------------------");
			System.out.println("ID: " + objCarro.getId());
			System.out.println("Modelo: " + objCarro.getModelo());
			System.out.println("Valor por Dia: " + objCarro.getValorDia());
			System.out.println("Quantidade de carros disponíveis para aluguel: " + objCarro.getUnidades());
			System.out.println("-----------------------");
			String resp = Console.readString("\n\nQuer alterar os dados desse modelo?(S/N)");
			if(resp.equals("S")) {
				objCarro.setModelo(Console.readString("Informe um novo nome para o modelo do carro: "));
				objCarro.setValorDia(Console.readFloat("Informe um novo valor por dia do modelo do carro:  "));
				objCarro.setUnidades(Console.readInt("Nova quantidade de carros disponíveis para aluguel: "));
				if(CarroPersistencia.alterar(objCarro) == true) {
					System.out.println("\n\nA alteração foi realizada");
				}
				else {
					System.out.println("\n\nA alteração não pôde ser realizada no momento");
				}
			}
		}
		else {
			System.out.println("\n\nModelo de Carro não encontrado");
		}
	}
	private static void excluirCarro() {
		System.out.println("\n\nEXCLUSÃO DE CARROS");
		Carro objCarro = new Carro();
		objCarro.setModelo(Console.readString("Informe o Modelo de Carro a ser excluído: "));
		objCarro = CarroPersistencia.procurarPorModelo(objCarro);
		if(objCarro != null) {
			System.out.println("\n\n-----------------------");
			System.out.println("ID: " + objCarro.getId());
			System.out.println("Modelo: " + objCarro.getModelo());
			System.out.println("Valor por Dia: " + objCarro.getValorDia());
			System.out.println("Quantidade de carros disponíveis para aluguel: " + objCarro.getUnidades());
			System.out.println("-----------------------");
			String resp = Console.readString("\n\nQuer excluir esse modelo de carro(S/N)? ");
			if(resp.equals("S")) {
				if(CarroPersistencia.excluir(objCarro) == true) {
					System.out.println("\n\nA exclusão foi realizada");
				}
				else {
					System.out.println("\n\nA exclusão não pôde ser realizada no momento");
				}
			}
		}
		else {
			System.out.println("\n\nModelo de Carro não encontrado");
		}
	}
}
