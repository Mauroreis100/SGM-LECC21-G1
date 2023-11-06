package armazem;

import java.io.*;
import java.util.Vector;

import leitura_escrita.Leitura_Escrita;
import interfaces.Operacoes;

public class ArmazemOperacoes implements Operacoes {
	// Instância das classes de gravação e remoção de objectos no ficheiro
	Leitura_Escrita grava_le = new Leitura_Escrita();

	@Override
	public Vector recuperarBD() {
		// TODO Auto-generated method stub
		Vector lista = new Vector();
		File fileArmazem = new File("bd/Armazem.dat");

		if(fileArmazem.length() != 0) {
			return lista = (Vector) grava_le.recuperarObjecto("bd/Armazem.dat");
		}
		return null;
	}

	@Override
	public boolean gravarObjecto(Vector armazem) {
		// TODO Auto-generated method stub
		return grava_le.gravarObjecto(armazem, "bd/Armazem.dat");

	}

	@Override
	public int procurarCodigo(Vector lista, int codigo) {
		for (int i = 0; i < lista.size(); i++) {
			if (((Armazem) lista.get(i)).getId() == codigo) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public Vector removerObjecto(Vector lista, int id) {
		// TODO Auto-generated method stub
		int index = procurarCodigo(lista, id);
		for (int i = 0; i < lista.size(); i++) {
			if (index != -1) {
				lista.remove(index);
				return lista;
			}
		}
		return lista;
	}

	@Override
	public boolean existe(String nome, Vector lista) {
		// TODO Auto-generated method stub
		nome = nome.replace(" ", "");
		if(lista==null) {
			return false;
			//Caso a lista esteja vazia retorna falso, o que significa que não existe nenhum ficheiro para comparar
		}
		for (int i = 0; i < lista.size(); i++) {
			// Porcura por strings com o mesmo nome, ignorando a capitalização
			if ((((Armazem) lista.get(i)).getNome().replace(" ", "")).equalsIgnoreCase(nome)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int procurarNome(String nome, Vector lista) {
		// TODO Auto-generated method stub
		nome = nome.replace(" ", "");
		for (int i = 0; i < lista.size(); i++) {
			// Porcura por strings com o mesmo nome, ignorando a capitalização
			if ((((Armazem) lista.get(i)).getNome().replace(" ", "")).equalsIgnoreCase(nome)) {
				return i;
			}
		}
		return -1;
	}

	public void inserirArmazem(Vector lista, Armazem armazem) {

		lista.addElement(armazem);

	}

	public void armazemUpdateName(String nome, String novoNome, Vector lista) {

		for (int i = 0; i < lista.size(); i++) {

			if (((Armazem) lista.get(i)).getNome() == nome) {
				((Armazem) lista.get(i)).setNome(novoNome);

			}
		}

	}

	public void armazemUpdateTipoProduto(String nome, String tipoProduto, Vector lista) {

		for (int i = 0; i < lista.size(); i++) {

			if (((Armazem) lista.get(i)).getNome() == nome) {
				((Armazem) lista.get(i)).setTipoProduto(tipoProduto);

			}
		}

	}

	public Armazem produtoStock(int id, Vector lista) {
		// TODO Auto-generated method stub

		for (int i = 0; i < lista.size(); i++) {
			if (((Armazem) lista.get(i)).getId() == id) {
				return (Armazem) lista.get(i);
			}
		}
		return null;

	}




}
