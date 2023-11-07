package produto;

import java.io.Serializable;

public class Produto implements Serializable {
	private int codigoProd, armazen_nr;
	private String nome;
	private String foto="assets/icons/Camera.png";
	private int stockMinimo=5;
	private String fornecedor;
	private int qtd;
	private int vendas=0;// Váriavel que conta, quantas vezes o produto foi vendido
//	private String ou int de Fornecedor
	private double preco;
	//PRAZO DE VALIDADE

	/*
	 * No momento que lês para inserir um produto no vector Decidi não lidar com a
	 * quantidade, Para colocar no carrinho, a quantidade muda "Virtualmente" Ou
	 * seja não fez a mudança necessariamente porque pode remover do carrinho que
	 * seria devolver o produto a prateleira
	 */
public Produto() {}
	public Produto(int codigoProd,int armazen_nr, String nome, int qtd, double preco,int vendas, String fornecedor) {
		this.codigoProd = codigoProd;
		this.nome = nome;
		this.armazen_nr=armazen_nr;
		this.qtd = qtd;
		this.stockMinimo=5;
		this.preco = preco;
		this.vendas=vendas;
		this.fornecedor=fornecedor;
	}
//	public Produto(int stockMinimo,	String fornecedor, String foto) {
//		this.fornecedor=fornecedor;
//		this.foto=foto;
//		this.stockMinimo=stockMinimo;
//	}

	public int getArmazen_nr() {
		return armazen_nr;
	}
	public void setArmazen_nr(int armazen_nr) {
		this.armazen_nr = armazen_nr;
	}
	public int getCodigoProd() {
		return codigoProd;
	}
	public void setCodigoProd(int codigoProd) {
		this.codigoProd = codigoProd;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = "assets/icons/Camera.png";
	}
	public int getStockMinimo() {
		return stockMinimo;
	}
	public void setStockMinimo(int stockMinimo) {
		this.stockMinimo = stockMinimo;
	}
	public String getFornecedor() {
		return fornecedor;
	}
	public void setFornecedor(String fornecedor) {
		this.fornecedor = fornecedor;
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
		return nome+"\t\t"+preco+"\t"+qtd+"\t"+preco+"\t\t"+(preco*qtd)+"\n";
	}
	public String printProduto() {
		
		return "Produto [codigoProd=" + codigoProd + ", armazen_nr=" + armazen_nr + ", nome=" + nome + ", foto=" + foto
				+ ", stockMinimo=" + stockMinimo + ", fornecedor=" + fornecedor + ", qtd=" + qtd + ", vendas=" + vendas
				+ ", preco=" + preco + "]";
	}
	
}
