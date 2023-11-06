package armazem;
import java.io.Serializable;
public class Armazem implements Serializable{

	private String nome, tipoProduto;
	private int id, quantidade;
	
	
	
	public Armazem() {}

	public Armazem(int id, String nome, String tipoProduto, int quantidade) {
		super();
		this.nome = nome;
		this.tipoProduto = tipoProduto;
		this.id = id;
		this.quantidade = quantidade;
	}
	
	//GETTERS AND SETTERS
	public String getTipoProduto() {return tipoProduto;}
	
	public void setTipoProduto(String tipoProduto) {this.tipoProduto = tipoProduto;}
	
	public int getQuantidade() {return quantidade;}
	
	public void setQuantidade(int quantidade) {this.quantidade = quantidade;}
	
	public String getNome() {return nome;}
	
	public void setNome(String nome) {this.nome = nome;}

	public int getId() {return id;}
	
	public void setId(int id) {this.id = id;}

	@Override
	public String toString() {
		return "Armazem [nome=" + nome + ", tipoProduto=" + tipoProduto + ", id=" + id + ", quantidade=" + quantidade
				+ "]";
	}
	
	
	
	
	

}
