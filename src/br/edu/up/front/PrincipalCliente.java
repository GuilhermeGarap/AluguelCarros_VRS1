package br.edu.up.front;

import br.edu.up.entidades.Aluguel;
import br.edu.up.entidades.Cliente;
import br.edu.up.negocio.AluguelNegocio;
import br.edu.up.negocio.ClienteNegocio;
import br.edu.up.persistencia.ClientePersistencia;
public class PrincipalCliente {
	public PrincipalCliente()
	{
		int opc;
		do{
			System.out.println("\n\nCLIENTES");
			System.out.println("1 - Criar Cliente");
			System.out.println("2 - Editar Cliente");
			System.out.println("3 - Excluir Cliente");
			System.out.println("4 - Listar Clientes");
			System.out.println("5 - Buscar Clientes");
			System.out.println("6 - Sair");		
			opc = Console.readInt("Opção: ");
			switch(opc){
				case 1:
					incluirCliente();
					break;
				case 2:
					alterarCliente();
					break;
				case 3:
					excluirCliente();
					break;
				case 4:
					listarClientes();
					break;
				case 5:
					buscarCliente();
					break;
			}
		}while(opc != 6);
	}
	private static void incluirCliente(){
		Cliente objCliente = new Cliente();
		objCliente.setCpf(Console.readString("\n\nInforme o CPF do cliente: "));
		if(ClienteNegocio.isCPF(objCliente.getCpf())) {			
			if(ClientePersistencia.procurarPorCPF(objCliente) == null) {
				objCliente.setNome(Console.readString("Informe o Nome do cliente: "));
				ClientePersistencia.incluir(objCliente);
				System.out.println("\n\nCliente Cadastrado!");
			}
			else {
				System.out.println("\n\nCliente já cadastrado");
			}
		}
		else {
			System.out.println("\n\nCPF inválido");
		}
	}
	private static void listarClientes(){
		System.out.println("\n\nLISTAGEM DE CLIENTES");
		Cliente objCliente = new Cliente();
		objCliente.setNome(Console.readString("Informe uma parte do nome que deseja listar: "));
		System.out.println("-------------------------------");
		for(Cliente item: ClientePersistencia.getClientes(objCliente)) {
			System.out.println("ID: " + item.getId());
			System.out.println("Nome: " + item.getNome());
			System.out.println("CPF: " + item.getCpf());
			System.out.println("-------------------------------");
		}
		
	}
	private static void buscarCliente() {
		System.out.println("\n\nBUSCA DE CLIENTES");
		Cliente objCliente = new Cliente();
		objCliente.setCpf(Console.readString("Informe o CPF a ser consultado: "));
		objCliente = ClientePersistencia.procurarPorCPF(objCliente);
		if(objCliente != null) {
			System.out.println("\n\n-----------------------");
			System.out.println("ID: " + objCliente.getId());
			System.out.println("Nome: " + objCliente.getNome());
			System.out.println("CPF: " + objCliente.getCpf());
			System.out.println("Aluguéis do Cliente:");
			System.out.println("-----------------------");
			for(Aluguel item: objCliente.getAlugueis()) {
	            System.out.println("ID: " + item.getId());
	            System.out.println("Modelo do Carro: " + item.getCarro().getModelo());
	            System.out.println("Dias Alugados: " + item.getDiasAlugados());
	            System.out.println("Data de Início: " + item.getDataInicio());
	            System.out.println("Valor Total: " + AluguelNegocio.calcularValorTotal(item));
				System.out.println("-----------------------");
			}
		}
		else {
			System.out.println("\n\nCliente não encontrado");
		}
	}
	private static void alterarCliente() {
		System.out.println("\n\nALTERAÇÃO DE CLIENTES");
		Cliente objCliente = new Cliente();
		objCliente.setCpf(Console.readString("Informe o CPF a ser a alterado: "));
		objCliente = ClientePersistencia.procurarPorCPF(objCliente);
		if(objCliente != null) {
			System.out.println("\n\n-----------------------");
			System.out.println("ID: " + objCliente.getId());
			System.out.println("Nome: " + objCliente.getNome());
			System.out.println("-----------------------");
			String resp = Console.readString("\n\nQuer alterar os dados desse cliente?(S/N) ");
			if(resp.equals("S")) {
				objCliente.setNome(Console.readString("Informe um novo nome para o cliente: "));
				if(ClientePersistencia.alterar(objCliente) == true) {
					System.out.println("\n\nA alteração foi realizada");
				}
				else {
					System.out.println("\n\nA alteração não pôde ser realizada no momento");
				}
			}
		}
		else {
			System.out.println("\n\nCliente não encontrado");
		}
	}
	private static void excluirCliente() {
		System.out.println("\n\nEXCLUSÃO DE CLIENTES");
		Cliente objCliente = new Cliente();
		objCliente.setCpf(Console.readString("Informe o CPF a ser excluído: "));
		objCliente = ClientePersistencia.procurarPorCPF(objCliente);
		if(objCliente != null) {
			System.out.println("\n\n-----------------------");
			System.out.println("ID: " + objCliente.getId());
			System.out.println("Nome: " + objCliente.getNome());
			System.out.println("CPF: " + objCliente.getCpf());
			System.out.println("-----------------------");
			String resp = Console.readString("\n\nQuer excluir esse cliente?(S/N) ");
			if(resp.equals("S")) {
				if(ClientePersistencia.excluir(objCliente) == true) {
					System.out.println("\n\nA exclusão foi realizada");
				}
				else {
					System.out.println("\n\nA exclusão não pôde ser realizada no momento");
				}
			}
		}
		else {
			System.out.println("\n\nCliente não encontrado");
		}
	}
}
