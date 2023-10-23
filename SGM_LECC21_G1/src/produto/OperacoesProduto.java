package produto;

import java.util.Vector;

public class OperacoesProduto {
	public Vector adicionarNovoProduto(Vector lista, Produto prod) {
//		if (existe(prod.getNome(), lista)) {
//			int index = procurarNome(prod.getNome(), lista);
//			System.out.println("Produto com este nome já existe, foi aumentada mais " + prod.getQtd() + " unidade(s)");
//			return aumentarQtd(lista, ((Produto) lista.elementAt(index)).getId(), prod.getQtd());
//		}
		lista.add(prod);
		return lista; //Retorna a lista toda
	}
}
