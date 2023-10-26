package produto;

import java.io.*;
import java.util.Vector;

import leitura_escrita.Leitura_Escrita;
import telas.ListaProdutos;

public class OperacoesProduto {
	Leitura_Escrita grava_le=new Leitura_Escrita();
	public Vector adicionarNovoProduto(Vector lista, Produto prod) {
		lista.add(prod);
		return lista; //Retorna a lista toda
	}
	public Vector recuperarProdutoBD() {
		Vector lista=new Vector();
		// Recuperação de todos os clientes
		File fileClientes = new File("bd/ProdutosDB.dat");
		if (fileClientes.length() != 0) { // Vê se está vazio
			return lista = (Vector) grava_le.recuperarObjecto("bd/ProdutosDB.dat");
		}
		return null;
	}
	
	public boolean gravarProdutos(Vector clientes) {
		return grava_le.gravarObjecto(clientes, "bd/ProdutosDB.dat");
	}
	
	public int procurarCodigo(Vector lista, int codigo) {//DEVOLVE-ME A POSIÇÃO
		for (int i = 0; i < lista.size(); i++) {
			if (((Produto) lista.get(i)).getId() == codigo) {
				return i;
			}
		}
		return -1;
	}
	
	public Produto produtoStock(int id, Vector lista) {
		for (int i = 0; i < lista.size(); i++) {
			if (((Produto) lista.get(i)).getId() == id) {
				return (Produto) lista.get(i);
			}
		}
		return null;
	}

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
