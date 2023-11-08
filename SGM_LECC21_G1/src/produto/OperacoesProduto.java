package produto;

import java.io.*;
import java.util.Vector;

import interfaces.Operacoes;
import leitura_escrita.Leitura_Escrita;
import telas.ListaProdutos;

public class OperacoesProduto implements Operacoes {
	// Instância das classes de gravação e remoção de objectos no ficheiro
	Leitura_Escrita grava_le = new Leitura_Escrita();

	// Método que recupera o vector de objectos Produtono ficheiro
	public Vector recuperarBD() {
		Vector lista = new Vector();
		// Recuperação de todos os clientes
		File fileClientes = new File("bd/ProdutosDB.dat");
		if (fileClientes.length() != 0) { // Vê se está vazio
			return lista = (Vector) grava_le.recuperarObjecto("bd/ProdutosDB.dat");// Recuperação
		}
		return null;
	}

	// Método que grava o vector de produtos na base de dados. Na verdade grava
	// qualquer vector. [INTERFACE]
	public boolean gravarObjecto(Vector produtos) {
		return grava_le.gravarObjecto(produtos, "bd/ProdutosDB.dat");
	}

	// Método que me retorna a posição do objecto no vector. Qualquer vector com
	// identificador nos seus objectos [INTERFACE]
	public int procurarCodigo(Vector lista, int codigo) {
		for (int i = 0; i < lista.size(); i++) {
			if (((Produto) lista.get(i)).getId() == codigo) {
				return i;
			}
		}
		return -1;
	}

	// Método que retorna o objecto produto no vector. É especificado, por isso os
	// castings foram necessários
	public Produto produtoStock(int id, Vector lista) {
		for (int i = 0; i < lista.size(); i++) {
			if (((Produto) lista.get(i)).getId() == id) {
				return (Produto) lista.get(i);
			}
		}
		return null;
	}

//Método que retorna o vector com objecto removido do vector. Qualquer lista e qualquer vector [INTERFACE]
	public Vector removerObjecto(Vector lista, int codigo) {
		int index = procurarCodigo(lista, codigo);
		for (int i = 0; i < lista.size(); i++) {
			if (index != -1) {
				lista.remove(index);
				return lista;
			}
		}
		return lista;
	}

	public boolean existe(String nome, Vector lista) {
		// Retira qualquer espaços que possa ter no nome
		nome = nome.replace(" ", "");
		if (lista == null) {
			return false;
		}
		for (int i = 0; i < lista.size(); i++) {
			// Porcura por strings com o mesmo nome, ignorando a capitalização
			if ((((Produto) lista.get(i)).getNome().replace(" ", "")).equalsIgnoreCase(nome)) {
				return true;
			}
		}
		return false;
	}

	// Procura com o nome já....
	public int procurarNome(String nome, Vector lista) {
		nome = nome.replace(" ", "");
		for (int i = 0; i < lista.size(); i++) {
			// Porcura por strings com o mesmo nome, ignorando a capitalização
			if ((((Produto) lista.get(i)).getNome().replace(" ", "")).equalsIgnoreCase(nome)) {
				return i;
			}
		}
		return -1;
	}

	public Vector filtrarNomes(String nome, Vector vec) {
		nome = nome.replace(" ", "");
		nome=nome.toLowerCase();
		Vector vectorNomes = new Vector();
		for (int i = 0; i < vec.size(); i++) {
			if ((((Produto) vec.get(i)).getNome()).toLowerCase().startsWith(nome)) {
				vectorNomes.add((Produto)vec.get(i));
			}
		}
		return vectorNomes;
	}
	
	//Mostra todos os produtos, abaixo de 5
		public void abaixoDe5(Vector lista) {
			for (int i = 0; i < lista.size(); i++) {
				if (((Produto) lista.get(i)).getQtd() < 5) {
					System.out.println("\n!!!!" + ((Produto) lista.get(i)).getNome()
							+ "- não esta disponível, apenas TEM " + ((Produto) lista.get(i)).getQtd() + " unidades\nENCOMENDE DO FORNECEDOR\n");
				}
			}
		}

		//Lista todos os produtos mais vendidos de forma crescente. Isto usa o algoritmo de ordenação 
		//
		public void maisVendidos(Vector lista) {

			Vector vendaDecrescente = lista;
			boolean troca = true;
			while (troca) {
				troca = false;
				for (int i = 0; i < lista.size() - 1; i++) {
					if (((Produto) lista.get(i)).getVendas() > ((Produto) lista.get(i + 1)).getVendas()) {
						Produto temp = (Produto) lista.get(i);
						lista.set(i, lista.get(i + 1));
						lista.set(i + 1, temp);
						troca = true;
					}
				}
			}
			for (int i = 0; i < lista.size(); i++) {

//				System.out.println(((Produto) vendaDecrescente.get(i)).imprimiVendas());

			}

		}
}
