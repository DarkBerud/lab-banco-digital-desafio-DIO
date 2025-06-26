
public class Main {

	public static void main(String[] args) {
		java.util.Scanner scanner = new java.util.Scanner(System.in);
		while (true) { 
			System.out.println("==========================");
			System.out.println("1 - Criar Conta Corrente");
			System.out.println("2 - Criar Conta Poupança");
			System.out.println("3 - Listar Contas");
			System.out.println("4 - Depositar");
			System.out.println("5 - Sacar");
			System.out.println("6 - Transferir");
			System.out.println("7 - Sair");
			System.out.print("Escolha uma opção: ");
			String input = scanner.nextLine();
			int opcao;
			System.out.println("==========================");
			try {
				opcao = Integer.parseInt(input);
			} catch (NumberFormatException e) {
				System.out.println("Opção invalida");
				continue;
			}
			switch (opcao) {
				case 1:
					Cliente clienteCorrente = new Cliente();
					Conta contaCorrente = new ContaCorrente(clienteCorrente);
					System.out.print("Digite o nome do cliente: ");
					clienteCorrente.setNome(scanner.nextLine());
					Banco.addConta(contaCorrente);
					System.out.println("Conta Corrente criada para " + clienteCorrente.getNome());
					System.out.println("Número da conta: " + contaCorrente.getNumero());
					System.out.println("Agência: " + contaCorrente.getAgencia());
					System.out.println("Saldo: " + contaCorrente.getSaldo());
					break;
				case 2:
					Cliente clientePoupanca = new Cliente();
					Conta contaPoupanca = new ContaPoupanca(clientePoupanca);
					System.out.print("Digite o nome do cliente: ");
					clientePoupanca.setNome(scanner.nextLine());
					Banco.addConta(contaPoupanca);
					System.out.println("Conta Poupança criada para " + clientePoupanca.getNome());
					System.out.println("Número da conta: " + contaPoupanca.getNumero());
					System.out.println("Agência: " + contaPoupanca.getAgencia());
					System.out.println("Saldo: " + contaPoupanca.getSaldo());
					break;
				case 3:
					System.out.println("Listando contas...");
					if (Banco.getContas().isEmpty()) {
						System.out.println("Nenhuma conta cadastrada.");
						break;
					} else {
						System.out.println("Contas cadastradas:");
						System.out.println(Banco.getContas().stream()
							.map(c -> "Numero da Conta: " + c.getNumero() + " - Tipo de conta: " + c.getClass().getSimpleName() + " - Agencia: " + c.getAgencia() + " - Titular: " + c.getCliente().getNome()  + " - Saldo: " + c.getSaldo())
							.reduce("", (a, b) -> a + "\n" + b));	
						}
					break;
				case 4:
					System.out.print("Digite o número da conta: ");
					int numeroContaDeposito = scanner.nextInt();
					System.out.print("Digite o valor a depositar: ");
					double valorDeposito = scanner.nextDouble();
					scanner.nextLine(); // Limpa o buffer
					Conta contaDeposito = Banco.getContas().stream()
							.filter(c -> c.getNumero() == numeroContaDeposito)
							.findFirst()
							.orElse(null);
					if (contaDeposito != null) {
						contaDeposito.depositar(valorDeposito);
						System.out.println("Depósito realizado com sucesso!");
					} else {
						System.out.println("Conta não encontrada!");
					}
					break;
				case 5:
					System.out.print("Digite o número da conta: ");
					int numeroContaSaque = scanner.nextInt();
					System.out.print("Digite o valor a sacar: ");
					double valorSaque = scanner.nextDouble();
					scanner.nextLine(); // Limpa o buffer
					Conta contaSaque = Banco.getContas().stream()
							.filter(c -> c.getNumero() == numeroContaSaque)
							.findFirst()
							.orElse(null);
					if (contaSaque != null) {
						if (valorSaque > contaSaque.getSaldo()) {
							System.out.println("Saldo insuficiente para saque!");
							break;
						} else if (valorSaque <= 0) {
							System.out.println("Valor de saque inválido!");
							break;
						} else {
							contaSaque.sacar(valorSaque);
						System.out.println("Saque realizado com sucesso!");
						}
					} else {
						System.out.println("Conta não encontrada!");
					}
					break;
				case 6:
					System.out.print("Digite o número da conta de origem: ");	
					int numeroContaOrigem = scanner.nextInt();
					System.out.print("Digite o número da conta de destino: ");
					int numeroContaDestino = scanner.nextInt();
					System.out.print("Digite o valor a transferir: ");
					double valorTransferencia = scanner.nextDouble();
					scanner.nextLine(); // Limpa o buffer
					Conta contaOrigem = Banco.getContas().stream()
							.filter(c -> c.getNumero() == numeroContaOrigem)
							.findFirst()
							.orElse(null);
					Conta contaDestino = Banco.getContas().stream()
							.filter(c -> c.getNumero() == numeroContaDestino)
							.findFirst()
							.orElse(null);
					if (contaOrigem != null && contaDestino != null) {
						if (valorTransferencia > contaOrigem.getSaldo()) {
							System.out.println("Saldo insuficiente para transferência!");
						} else if (valorTransferencia <= 0) {
							System.out.println("Valor de transferência inválido!");
						} else {
							contaOrigem.transferir(valorTransferencia, contaDestino);
							System.out.println("Transferência realizada com sucesso!");
						}
					} else {
						System.out.println("Conta de origem ou destino não encontrada!");
					}
					break;					
				case 7:
					System.out.println("Saindo...");
					return; 
				default:
					System.out.println("Opção invalida");
			}
		}


		// Cliente venilton = new Cliente();
		// venilton.setNome("Venilton");
		
		// Conta cc = new ContaCorrente(venilton);
		// Conta poupanca = new ContaPoupanca(venilton);

		// cc.depositar(100);
		// cc.transferir(100, poupanca);
		
		// cc.imprimirExtrato();
		// poupanca.imprimirExtrato();
	}

}
