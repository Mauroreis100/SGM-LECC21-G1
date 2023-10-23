package produto;

import java.io.*;
import java.util.Vector;

import leitura_escrita.Leitura_Escrita;

public class OperacoesProduto {
	Leitura_Escrita grava_le=new Leitura_Escrita();
	public Vector adicionarNovoProduto(Vector lista, Produto prod) {
//		if (existe(prod.getNome(), lista)) {
//			int index = procurarNome(prod.getNome(), lista);
//			System.out.println("Produto com este nome já existe, foi aumentada mais " + prod.getQtd() + " unidade(s)");
//			return aumentarQtd(lista, ((Produto) lista.elementAt(index)).getId(), prod.getQtd());
//		}
		lista.add(prod);
		return lista; //Retorna a lista toda
	}
	public void recuperar(Vector clientes, String caminhoClientes) {
		// Recuperação de todos os clientes
				File fileClientes = new File(caminhoClientes);
				if (fileClientes.length() != 0) { // Vê se está vazio
					clientes = (Vector) grava_le.recuperarObjecto(caminhoClientes);
				}
	}
	
	public boolean gravar(Vector clientes, String caminhoClientes) {
		return grava_le.gravarObjecto(clientes, caminhoClientes);
	}
}
