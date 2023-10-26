package produto;

import java.io.*;
import java.util.Vector;

import leitura_escrita.Leitura_Escrita;
import telas.ListaProdutos;

public class OperacoesProduto {
	//Instância das classes de gravação e remoção de objectos no ficheiro
	Leitura_Escrita grava_le=new Leitura_Escrita();

	//Método que recupera o vector de objectos Produtono ficheiro
	public Vector recuperarProdutoBD() {
		Vector lista=new Vector();
		// Recuperação de todos os clientes
		File fileClientes = new File("bd/ProdutosDB.dat");
		if (fileClientes.length() != 0) { // Vê se está vazio
			return lista = (Vector) grava_le.recuperarObjecto("bd/ProdutosDB.dat");//Recuperação
		}
		return null;
	}
	
	//Método que grava o vector de produtos na base de dados. Na verdade grava qualquer vector. [INTERFACE]
	public boolean gravarProdutos(Vector produtos) {
		return grava_le.gravarObjecto(produtos, "bd/ProdutosDB.dat");
	}
	
	//Método que me retorna a posição do objecto no vector. Qualquer vector com identificador nos seus objectos [INTERFACE]
	public int procurarCodigo(Vector lista, int codigo) {
		for (int i = 0; i < lista.size(); i++) {
			if (((Produto) lista.get(i)).getId() == codigo) {
				return i;
			}
		}
		return -1;
	}
	
	//Método que retorna o objecto produto no vector. É especificado, por isso os castings foram necessários
	public Produto produtoStock(int id, Vector lista) {
		for (int i = 0; i < lista.size(); i++) {
			if (((Produto) lista.get(i)).getId() == id) {
				return (Produto) lista.get(i);
			}
		}
		return null;
	}

//Método que retorna o vector com objecto removido do vector. Qualquer lista e qualquer vector [INTERFACE]
	public Vector removerProduto(Vector lista, int codigo) {
		int index = procurarCodigo(lista, codigo);
		for (int i = 0; i < lista.size(); i++) {
			if (index != -1) {
				lista.remove(index);
				return lista;
			}
		}
		return lista;
	}
	
	

}
