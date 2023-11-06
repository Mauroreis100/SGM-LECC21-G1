package produto;

import java.io.Serializable;

public class Produto implements Serializable {
	private int codigoProd;
	private String nome;
	private String foto;
	private int stockMinimo;
	private String fornecedor;
	private int qtd;
	private int vendas;// Váriavel que conta, quantas vezes o produto foi vendido
//	private String ou int de Fornecedor
	private double preco;

	/*
	 * No momento que lês para inserir um produto no vector Decidi não lidar com a
	 * quantidade, Para colocar no carrinho, a quantidade muda "Virtualmente" Ou
	 * seja não fez a mudança necessariamente porque pode remover do carrinho que
	 * seria devolver o produto a prateleira
	 */
public Produto() {}
	public Produto(int codigoProd, String nome, int qtd, double preco) {
		this.codigoProd = codigoProd;
		this.nome = nome;
		this.qtd = qtd;
		this.preco = preco;
	}

	public int getVendas() {
		return vendas;
	}

	public void setVendas(int vendas) {
		this.vendas = vendas;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public int getId() {
		return codigoProd;
	}

	public void setId(int codigoProd) {
		this.codigoProd = codigoProd;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getQtd() {
		return qtd;
	}

	public void setQtd(int qtd) {
		this.qtd = qtd;
	}

	@Override
	public String toString() {
		return "Produto [codigoProd=" + codigoProd + ", nome=" + nome + ", qtd=" + qtd + ", vendas=" + vendas
				+ ", preco=" + preco + "]";
	}
	
}
