package carrinho;

public class Carritxo {
	String id;
	private int qtd;
	private String nome;
	private double preco,total;
	

	public Carritxo(String id,String nome, int qtd, double preco, double total) {
		super();
		this.id = id;
		this.qtd = qtd;
		this.nome = nome;
		this.preco = preco;
		this.total = total;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
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
		return "Carritxo [id=" + id + ", qtd=" + qtd + ", nome=" + nome + ", preco=" + preco + ", total=" + total + "]";
	}
	
	
}
