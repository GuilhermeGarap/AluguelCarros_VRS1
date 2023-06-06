package br.edu.up.front;

import br.edu.up.persistencia.*;
import br.edu.up.entidades.*;
import br.edu.up.negocio.AluguelNegocio;
import java.util.List;
import br.edu.up.entidades.Aluguel;
import br.edu.up.persistencia.AluguelPersistencia;

public class PrincipalAluguel {
	public PrincipalAluguel(){
		int opc;
		do{
			System.out.println("\n\nALUGAR");
			System.out.println("1 - Alugar um Carro");
			System.out.println("2 - Editar um Aluguel de Carro");
			System.out.println("3 - Excluir um Aluguel de Carro");
			System.out.println("4 - Listar Aluguéis");			
			System.out.println("5 - Buscar Aluguel");
			System.out.println("6 - Voltar");
			opc = Console.readInt("Opção: ");
			switch(opc){
				case 1:
					criarAluguel();
					break;
				case 2:
					alterarAluguel();
					break;
				case 3:
					excluirAluguel();
					break;
				case 4:
					listarAlugueis();
					break;
				case 5:
					buscarAluguel();
			}
		}while(opc != 6);
	}
	
    private static void criarAluguel() {
        Aluguel objAluguel = new Aluguel();
        Cliente objCliente = new Cliente();
        objCliente.setCpf(Console.readString("Informe o CPF do cliente: "));
        objCliente = ClientePersistencia.procurarPorCPF(objCliente);
        if (objCliente != null) {
            objAluguel.setCliente(objCliente);
            System.out.println("\n\n-----------------------");
            System.out.println("ID: " + objCliente.getId());
            System.out.println("Nome: " + objCliente.getNome());
            System.out.println("CPF: " + objCliente.getCpf());
            System.out.println("-----------------------");

            Carro objCarro = new Carro();
            objCarro.setModelo(Console.readString("\nInforme o modelo do carro: "));
            objCarro = CarroPersistencia.procurarPorModelo(objCarro);
            if (objCarro != null) {
                objAluguel.setCarro(objCarro);
                objAluguel.setDiasAlugados(Console.readInt("Informe quantos dias o carro será alugado: "));
                objAluguel.setDataInicio(Console.readString("Informe a data de início do aluguel: "));
                if (AluguelPersistencia.incluir(objAluguel)) {
                    System.out.println("\n\nO aluguel foi cadastrado");
                } else {
                    System.out.println("Aluguel não cadastrado");
                }
            } else {
                System.out.println("Modelo de Carro não cadastrado");
            }
        } else {
            System.out.println("Cliente não cadastrado");
        }
    }

    private static void buscarAluguel() {
        System.out.println("\n\nBUSCA DE ALUGUEL");
        Aluguel objAluguel = new Aluguel();
        objAluguel.setId(Console.readInt("Insira o ID do aluguel: "));
        objAluguel = AluguelPersistencia.procurarPorId(objAluguel);
        if (objAluguel != null) {
            System.out.println("\n\n-----------------------");
                System.out.println("ID: " + objAluguel.getId());
                System.out.println("Nome do Cliente: " + objAluguel.getCliente().getNome());
                System.out.println("Modelo do Carro " + objAluguel.getCarro().getModelo());
                System.out.println("Dias Alugados: " + objAluguel.getDiasAlugados());
                System.out.println("Data de Início: " + objAluguel.getDataInicio());
                System.out.println("Valor Total: " + AluguelNegocio.calcularValorTotal(objAluguel));
                System.out.println("-----------------------");
        } else {
        	System.out.println("Não foi encontrado nenhum aluguel com esse ID");
        }
    }
        

    private static void listarAlugueis() {
        System.out.println("\n\nLISTA DE ALUGUEÍS");
        System.out.println("\n\n-----------------------");
        List<Aluguel> alugueis = AluguelPersistencia.getAlugueis();
        for (Aluguel aluguel : alugueis) {
            System.out.println("ID: " + aluguel.getId());
            System.out.println("Nome do Cliente: " + aluguel.getCliente().getNome());
            System.out.println("Modelo do Carro: " + aluguel.getCarro().getModelo());
            System.out.println("Dias Alugados: " + aluguel.getDiasAlugados());
            System.out.println("Data de Início: " + aluguel.getDataInicio());
            System.out.println("Valor Total: " + AluguelNegocio.calcularValorTotal(aluguel));
            System.out.println("-------------------------------");
        }
    }

    private static void alterarAluguel() {
        System.out.println("\n\nALTERAÇÃO NO ALUGUEL");
        Aluguel objAluguel = new Aluguel();
        objAluguel.setId(Console.readInt("Informe o ID do aluguel: "));
        objAluguel = AluguelPersistencia.procurarPorId(objAluguel);
        if (objAluguel != null) {
            System.out.println("\n\n-----------------------");
            System.out.println("ID: " + objAluguel.getId());
            System.out.println("Modelo do Carro: " + objAluguel.getCarro().getModelo());
            System.out.println("CPF do Cliente: " + objAluguel.getCliente().getCpf());
            System.out.println("Dias Alugados: " +objAluguel.getDiasAlugados());
            System.out.println("Data de Início: " + objAluguel.getDataInicio());
            System.out.println("Valor Total: " + AluguelNegocio.calcularValorTotal(objAluguel));
            System.out.println("-----------------------");
            String resp = Console.readString("\n\nQuer alterar os dados desse aluguel? (S/N)");
            if (resp.equalsIgnoreCase("S")) {
                Carro novoCarro = new Carro();
                novoCarro.setModelo(Console.readString("Informe novo modelo de carro: "));
                novoCarro = CarroPersistencia.procurarPorModelo(novoCarro);
                if (novoCarro != null) {
                    objAluguel.setCarro(novoCarro);
                    if (CarroPersistencia.alterar(objAluguel.getCarro())) {
                        System.out.println("A alteração do carro foi realizada");
                    } else {
                        System.out.println("\n\nA alteração do carro não pôde ser realizada no momento");
                    }
                } else {
                    System.out.println("\n\nModelo de Carro não cadastrado");
                }

                Cliente novoCliente = new Cliente();
                novoCliente.setCpf(Console.readString("\nInforme o CPF do novo cliente: "));
                novoCliente = ClientePersistencia.procurarPorCPF(novoCliente);
                if (novoCliente != null) {
                    objAluguel.setCliente(novoCliente);
                    if (ClientePersistencia.alterar(objAluguel.getCliente())) {
                        System.out.println("\n\nA alteração do cliente foi realizada");
                    } else {
                        System.out.println("\n\nA alteração do cliente não pôde ser realizada no momento");
                    }
                } else {
                    System.out.println("\n\nCliente não cadastrado");
                }
            }
        } else {
            System.out.println("\n\nAluguel não encontrado");
        }
    }


    private static void excluirAluguel() {
        System.out.println("\n\nEXCLUSÃO DE ALUGUEL");
        Aluguel objAluguel = new Aluguel();
        objAluguel.setId(Console.readInt("Informe o ID do aluguel: "));
        objAluguel = AluguelPersistencia.procurarPorId(objAluguel);
        if (objAluguel != null) {
            System.out.println("\n\n-----------------------");
            System.out.println("ID: " + objAluguel.getCarro().getId());
            System.out.println("Modelo: " + objAluguel.getCarro().getModelo());
            System.out.println("Valor por Dia: " + objAluguel.getCarro().getValorDia());
            System.out.println("Quantidade de carros disponíveis para aluguel: " + objAluguel.getCarro().getUnidades());
            System.out.println("-----------------------");
            String resp = Console.readString("\n\nQuer excluir esse modelo de carro? (S/N)");
            if (resp.equalsIgnoreCase("S")) {
                if (AluguelPersistencia.excluir(objAluguel)) {
                    System.out.println("\n\nA exclusão foi realizada");
                } else {
                    System.out.println("\n\nA exclusão não pôde ser realizada no momento");
                }
            }
        } else {
            System.out.println("\n\nAluguel não encontrado");
        }
    }
}
