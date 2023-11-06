package vendas;

import java.io.File;
import java.util.Vector;

import leitura_escrita.Leitura_Escrita;

public class OperacoesVenda_Dinheiro {
	// Instância das classes de gravação e remoção de objectos no ficheiro
	Leitura_Escrita grava_le = new Leitura_Escrita();

	// Método que recupera o vector de objectos Produtono ficheiro
	public Vector recuperarVendas() {
		Vector lista = new Vector();
		// Recuperação de todos os clientes
		File fileClientes = new File("bd/VendasDB.dat");
		if (fileClientes.length() != 0) { // Vê se está vazio
			return lista = (Vector) grava_le.recuperarObjecto("bd/VendasDB.dat");// Recuperação
		}
		return null;
	}

	// Método que grava o vector de produtos na base de dados. Na verdade grava
	// qualquer vector. [INTERFACE]
	public boolean gravarVendas(Vector venda) {
		return grava_le.gravarObjecto(venda, "bd/VendasDB.dat");
	}

}
