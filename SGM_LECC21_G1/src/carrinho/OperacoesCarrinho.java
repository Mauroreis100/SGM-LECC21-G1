package carrinho;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Random;
import java.util.Vector;

import javax.swing.JOptionPane;

import produto.OperacoesProduto;
import produto.Produto;

public class OperacoesCarrinho {
	OperacoesProduto op_Prod = new OperacoesProduto(); // Instância da classe de operações dos produtos=armazém=stock
	static final double iva = 0.17;

	// Este é o método que adiciona o produto
	/*
	 * Ele recebe: -- id - do produto que quer adicionar carrinho --- Vector do
	 * carrinho (que quero adicionar os produtos) e ---- lista - a lista de todos os
	 * produtos existentes no stock e -----quantidade - e a quantos produtos quero
	 * adicionar ao carrinho
	 */
	public Vector adicionarProduto(int id, Vector carrinho, Vector lista, int quantidade) {

		// Váriavel para saber se a situação do produto no stock
		boolean situacaoStock = verificacaoQuantidade(id, lista, quantidade);

		// Váriavel que devolve a posição do produto no _carrinho_
		int indexExiste = verificaExistenciaCarinho(id, carrinho);

		/*
		 * Sim podia fazer verificacaoQuantidade() e verificaExistenciaCarinho() em um
		 * método Mas uso verificaExistência para outros fins e aproveitei usar aqui
		 */
		if (situacaoStock) {// Verifica se o produto )que pretende adicionar tem no stock

			// Várivel que recebe o objecto produto que foi encontrado ou não no stock.
			Produto encontrado = produtoStock(id, lista);

			// Caso o produto já esteja inserido no carrinho:
			if (indexExiste != -1) {

				// No produto encontrado no stock e remove a quantidade escolhida
				encontrado.setQtd(encontrado.getQtd() - quantidade);

				// E para o produto que já existe está no carrinho, só mudamos a quantidade
				// A verificação de se a quantidade no stock permite, já foi feita
				((Carritxo) carrinho.get(indexExiste))
						.setQtd((((Carritxo) carrinho.get(indexExiste))).getQtd() + quantidade);
				(((Carritxo) carrinho.get(indexExiste)))
						.setTotal(encontrado.getPreco() * (((Carritxo) carrinho.get(indexExiste))).getQtd());
			} else {// Caso não encontre no carrinho, simplesmente:

				// No produto encontrado no stock e remove a quantidade escolhida
				encontrado.setQtd(encontrado.getQtd() - quantidade);

				// Cria uma váriavel do tipo carrinho, faz a multiplicação de preço e quantidade
				Carritxo produto = new Carritxo(id, encontrado.getNome(), quantidade, encontrado.getPreco(),
						encontrado.getPreco() * quantidade);

				// E adiciona tudo no vector carrinho
				carrinho.add(produto);

			}
		}
		return carrinho;
	}

//Método que faz a verificação de quantidade do produto no stock (Uma operação de stock)...
//Recebe id do produto, lista dos produtos no stock e a quantidade
	public boolean verificacaoQuantidade(int id, Vector lista, int quantidade) {

		// Váriavel que recebe a posição do produto no stock
		int posicao = op_Prod.procurarCodigo(lista, id);
		if (posicao != -1) {
			Produto encontrado = ((Produto) lista.get(posicao));
			if (quantidade > encontrado.getQtd()) { // Quantidade pretendida acima da que está no stock
				JOptionPane.showMessageDialog(null, "Quantidade maior que Stock","", JOptionPane.WARNING_MESSAGE);
				return false;
			} else if (encontrado.getQtd() == 0) {// ...
				JOptionPane.showMessageDialog(null, "Fora de Stock","", JOptionPane.WARNING_MESSAGE);
				return false;
			} else if (quantidade < 0) {// Quantidade menor que 0
				JOptionPane.showMessageDialog(null, "INSIRA UMA QUANTIDADE VÁLIDA","", JOptionPane.WARNING_MESSAGE);
				return false;
			} else {
				return true;// Produto existe e tem a quantidade pretendida
			}
		} else {// Produto não existe
			return false;
		}
	}

	// Método que devolve o objecto Produto, do stock
	public Produto produtoStock(int id, Vector lista) {
		for (int i = 0; i < lista.size(); i++) {
			if (((Produto) lista.get(i)).getId() == id) {
				return (Produto) lista.get(i);
			}
		}
		return null;
	}

	// Método que devolve o objecto Produto, do stock
	public Carritxo produtoCarrinho(int id, Vector lista) {
		for (int i = 0; i < lista.size(); i++) {
			if (((Carritxo) lista.get(i)).getId() == id) {
				return (Carritxo) lista.get(i);
			}
		}
		return null;
	}

//	Método que devolve a posição, do produto no stock
	public int verificaExistenciaCarinho(int id, Vector carrinho) {
		for (int i = 0; i < carrinho.size(); i++) {
			// Ele peda o id do Produto
			if (((Carritxo) carrinho.get(i)).getId() == id) {
				return i;
			}
		}
		return -1;
	}

	// NÃO USEI ESTE MÉTODO. removerProduto()
	/*
	 * Ideia de implementação: Usar este método para quando, quiser tirar todo
	 * produto (Quantidade ficar 0 no carrinho) Chamando,
	 * removerProdutoQuantidade(ID,CARRINHO,vector temporario de
	 * produtos,produto.quantidade)
	 */
	// Método para remover o produto. Devolve o vector carrinho, caso remova ou não
	public Vector removerProduto(int id, Vector carrinho, Vector temporario) {
		int index = procuraProdutoCarrinho(id, carrinho);
		for (int i = 0; i < carrinho.size(); i++) {
			if (((Carritxo) carrinho.get(i)).getId() == id) {
				carrinho.remove(i); // Remoção do produto no carrinho
				JOptionPane.showMessageDialog(null, "REMOVIDO COM SUCESSO");
				return carrinho;
			}
		}
		JOptionPane.showMessageDialog(null, "Produto não está no Carrinho");

		return carrinho;
	}

//Método para remover a quantidade específica de um produto. Temporário=Vector de ma
//Manipulação do stock antes de actualizar o stock no fim
	public Vector removerProdutoQuantidade(int id, Vector carrinho, Vector temporario, int quantidade) {
		int index = procuraProdutoCarrinho(id, carrinho);

		int indexStock = op_Prod.procurarCodigo(temporario, id);// Procura stock o produto

		if (index != -1) {// Caso encontre no stock

			// Caso encontre carrinho
			if (((Produto) carrinho.get(index)).getId() == ((Produto) temporario.get(indexStock)).getId()) {

				/*
				 * Proposito: Ver se pode remover a quantidade inserida do carrinho
				 */
				if (verificacaoQuantidade(id, carrinho, quantidade)) {

					// Devolução da quantidade do stock, Como devolver a prateleira
					((Produto) temporario.get(indexStock))
							.setQtd(((Produto) temporario.get(indexStock)).getQtd() + quantidade);

					// Remoção da quantidade no carrinho
					((Produto) carrinho.get(index)).setQtd(((Produto) carrinho.get(index)).getQtd() - quantidade);

					// Quando a quantidade do produto no carrinho for 0, remove completamente do
					// carrinho.
					// Ter 0 significa não estar no carrinho
					if (((Produto) carrinho.get(index)).getQtd() <= 0) {
						carrinho.remove(index);
					}
				} else {
					System.out.println("Quantidade de invalida!");
				}
			}
		} else {
			System.out.println("Produto não está no carrinho");
		}

		return carrinho; // Retorno o vector carrinho com as modificações feitas
	}

	// Método para procurar a o produto no carrinho
	public int procuraProdutoCarrinho(int id, Vector carrinho) {
		for (int i = 0; i < carrinho.size(); i++) {
			if (((Carritxo) carrinho.get(i)).getId() == id) {
				return i;
			}
		}
		return -1;
	}

	public Vector devolverQuantidade(int id, Vector produtos, Vector carrinho) {
		int id_produto_carrinho = procuraProdutoCarrinho(id, carrinho);
		if (id_produto_carrinho != -1) {
			Carritxo produto_Carrinho = produtoCarrinho(id, carrinho);
			Produto prod_QTD_anterior = op_Prod.produtoStock(id, produtos);
			prod_QTD_anterior.setQtd(prod_QTD_anterior.getQtd() + produto_Carrinho.getQtd());
			int id_QTD_nova = op_Prod.procurarCodigo(produtos, id);
			produtos.setElementAt(prod_QTD_anterior, id_QTD_nova);
			return produtos;
		} else {
			return produtos;
		}
	}

	// Método para ver se o carrinhi está vazio
	public boolean estaVazio(Vector carrinho) {
		if (carrinho.size() == 0) {
			return false;
		}
		return true;
	}

	// Actualiza o numero de vendas por 1 de todos os produtos no carrinho, após
	// efectuar a compra
	public Vector actualizarVendas(Vector carrinho, Vector produtos) {
		for (int i = 0; i < carrinho.size(); i++) {
			for (int j = 0; j < produtos.size(); j++) {
				if (((Carritxo) carrinho.get(i)).getId() == ((Produto) produtos.get(j)).getId()) {
					((Produto) produtos.get(j)).setVendas((((Produto) produtos.get(j)).getVendas() + 1));
					return produtos;
				}

			}
		}
		return produtos;
	}

//Faz a adição do carrinho todo ao vector de compras Cliente
//	public boolean vendaDinheiro(int id, Vector carrinho, Cliente cl) {
//
//		// Total de cada cliente começa de 0
//		double total = 0;
//
//		// Instância do vector de compras do cliente
//		Vector compraCliente = cl.getCompras();
//
//		// Somar cada total do carrinho
//		for (int i = 0; i < carrinho.size(); i++) {
//			total += ((Carrinho) carrinho.get(i)).getTotal();
//		}
//
//		// Se está vazio, não adiciona
//		if (estaVazio(carrinho)) { // isEmpty faz isso
//			Calendar cal = Calendar.getInstance();// Instância do calendar
//
//			/*
//			 * Novo objecto compras que recebe: cal- tipo de dado calendar - id do cliente
//			 * -nome do cliente -Vector carrinho - e um total que é o total e o iva
//			 */
//			Compras venda = new Compras(cal, id, cl.getId(), cl.getNome(), carrinho, (total + total * iva));
//			cl.getCompras().add(venda);// Para o cliente especificado, adiciona a venda=carrinho=compras
//			System.out.println("\nSEM IVA: " + total + "MZN\nACRÉSCIMO DE IVA: " + total * iva + "MZN\nTOTAL="
//					+ (total + total * iva) + "MZN");
//			System.out.println(((Compras) cl.getCompras().get(cl.getCompras().size() - 1)).toString());
//			System.out.println("Compra feita com sucesso\n");
//			return true;
//		}
//		return false;
//	}

}
