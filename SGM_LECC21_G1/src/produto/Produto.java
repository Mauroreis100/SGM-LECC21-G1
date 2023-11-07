package produto;

import java.io.Serializable;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Produto implements Serializable {
	private int id, armazen_nr;
	private String nome;
	private String foto = "assets/icons/Camera.png";
	private int stockMinimo = 5;
	private String fornecedor;
	private int qtd;
	private int vendas = 0;// Váriavel que conta, quantas vezes o produto foi vendido
//	private String ou int de Fornecedor
	private double preco;
	private Date validade;

	/*
	 * No momento que lês para inserir um produto no vector Decidi não lidar com a
	 * quantidade, Para colocar no carrinho, a quantidade muda "Virtualmente" Ou
	 * seja não fez a mudança necessariamente porque pode remover do carrinho que
	 * seria devolver o produto a prateleira
	 */
	public Produto() {
	}

	public Produto(int id, int armazen_nr, String nome, int qtd, double preco, int vendas, String fornecedor) {
		this.id = id;
		this.nome = nome;
		this.armazen_nr = armazen_nr;
		this.qtd = qtd;
		this.stockMinimo = 5;
		this.preco = preco;
		this.vendas = vendas;
		this.fornecedor = fornecedor;
		this.validade = validade;
	}
//	public Produto(int stockMinimo,	String fornecedor, String foto) {
//		this.fornecedor=fornecedor;
//		this.foto=foto;
//		this.stockMinimo=stockMinimo;
//	}

	@Override
	public String toString() {
		return nome + "\t\t" + preco + "\t" + qtd + "\t" + preco + "\t\t" + (preco * qtd) + "\n";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getArmazen_nr() {
		return armazen_nr;
	}

	public void setArmazen_nr(int armazen_nr) {
		this.armazen_nr = armazen_nr;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
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

	public int getQtd() {
		return qtd;
	}

	public void setQtd(int qtd) {
		this.qtd = qtd;
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

	public Date getValidade() {
		return validade;
	}

	public void setValidade(String validadeString){
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yy"); // Define your date format
//		this.validade = dateFormat.parse(validadeString);

	}

	public String printProduto() {

		return "Produto [id=" + id + ", armazen_nr=" + armazen_nr + ", nome=" + nome + ", foto=" + foto
				+ ", stockMinimo=" + stockMinimo + ", fornecedor=" + fornecedor + ", qtd=" + qtd + ", vendas=" + vendas
				+ ", preco=" + preco + "]";
	}

}
