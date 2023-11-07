package clientes;

import java.io.Serializable;

public class Cliente implements Serializable{
	//Tens de implementar o Serializable para que a classe possa ser gravada num ficheiro objecto. Solução: https://stackoverflow.com/questions/22299528/an-error-java-io-notserializableexception
	private String nome;
	private int id ;
	private String BI,cell;
	private double saldo;
	
	public Cliente() {}
	public Cliente( int id,String nome, String BI, String cell, double saldo) {
		super();
		this.nome = nome;
		this.id = id;
		this.BI = BI;
		this.cell = cell;
		this.saldo = saldo;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getBI() {
		return BI;
	}


	public void setBI(String BI) {
		this.BI = BI;
	}


	public String getCell() {
		return cell;
	}


	public void setCell(String cell) {
		this.cell = cell;
	}


	public double getSaldo() {
		return saldo;
	}


	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}


	@Override
	public String toString() {
		return "Cliente [nome=" + nome + ", id=" + id + ", BI=" + BI + ", cell=" + cell + ", saldo=" + saldo + "]";
	}
	
}
