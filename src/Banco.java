import java.util.List;

public class Banco {

	private String nome;
	private static List<Conta> contas = new java.util.ArrayList<>();

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public static List<Conta> getContas() {
		return contas;
	}

	public void setContas(List<Conta> contas) {
		this.contas = contas;
	}

	public static void addConta(Conta conta) {
		contas.add(conta);
	}

}
