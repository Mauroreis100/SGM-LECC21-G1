package carrinho;

import java.io.Serializable;

public class Carritxo implements Serializable{
	int id;
	private int qtd;
	private String nome;
	private double preco,total;
	

	public Carritxo(int id,String nome, int qtd, double preco, double total) {
		super();
		this.id = id;
		this.qtd = qtd;
		this.nome = nome;
		this.preco = preco;
		this.total = total;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQtd() {
		return qtd;
	}

	public void setQtd(int qtd) {
		this.qtd = qtd;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return nome+"\t\t"+preco+"\t"+qtd+"\t"+preco+"\t\t"+(preco*qtd)+"\n";
	}
	
	
}
