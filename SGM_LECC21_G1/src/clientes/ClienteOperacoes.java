package clientes;

import java.io.*;
import java.util.Vector;

import leitura_escrita.Leitura_Escrita;


public class ClienteOperacoes {
	// Instância das classes de gravação e remoção de objectos no ficheiro
	Leitura_Escrita grava_le = new Leitura_Escrita();

	// Método que recupera o vector de objectos Produtono ficheiro
	public Vector recuperarClientesBD() {
		Vector lista = new Vector();
		// Recuperação de todos os clientes
		File fileClientes = new File("bd/ClientesDB.dat");
		if (fileClientes.length() != 0) { // Vê se está vazio
			return lista = (Vector) grava_le.recuperarObjecto("bd/ClientesDB.dat");// Recuperação
		}
		return null;
	}

	// Método que grava o vector de produtos na base de dados. Na verdade grava
	// qualquer vector. [INTERFACE]
	public boolean gravarClientes(Vector clientes) {
		return grava_le.gravarObjecto(clientes, "bd/ClientesDB.dat");
	}

	// Método que me retorna a posição do objecto no vector. Qualquer vector com
	// identificador nos seus objectos [INTERFACE]
	public int procurarCodigo(Vector lista, int codigo) {
		for (int i = 0; i < lista.size(); i++) {
			if (((Cliente) lista.get(i)).getId() == codigo) {
				return i;
			}
		}
		return -1;
	}

	// Método que retorna o objecto produto no vector. É especificado, por isso os
	// castings foram necessários
	public Cliente procuraClienteID(int id, Vector lista) {
		for (int i = 0; i < lista.size(); i++) {
			if (((Cliente) lista.get(i)).getId() == id) {
				return (Cliente) lista.get(i);
			}
		}
		return null;
	}

	// Método que retorna o vector com objecto removido do vector. Qualquer lista e
	// qualquer vector [INTERFACE]
	public Vector removerCliente(Vector lista, int codigo) {
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
		if(lista==null) {
			return false;
			//Caso a lista esteja vazia retorna falso, o que significa que não existe nenhum ficheiro para comparar
		}
		for (int i = 0; i < lista.size(); i++) {
			// Porcura por strings com o mesmo nome, ignorando a capitalização
			if ((((Cliente) lista.get(i)).getNome().replace(" ", "")).equalsIgnoreCase(nome)) {
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
			if ((((Cliente) lista.get(i)).getNome().replace(" ", "")).equalsIgnoreCase(nome)) {
				return i;
			}
		}
		return -1;
	}

}
