package br.edu.up.front;
public class Principal {
	public static void main(String[] args) {
		int opc;
		do{
			System.out.println("\n\n");
			System.out.println("MENU PRINCIPAL");
			System.out.println("1 - Carros");
			System.out.println("2 - Clientes");
			System.out.println("3 - Aluguel");
			System.out.println("4 - Sair");
			opc = Console.readInt("Opção: ");
			switch(opc){
				case 1:
					new PrincipalCarro();
					break;
				case 2:
					new PrincipalCliente();
					break;
				case 3:
					new PrincipalAluguel();
			}
		}while(opc != 4);
	}
}
