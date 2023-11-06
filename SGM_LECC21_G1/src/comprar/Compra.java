package comprar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.*;

import clientes.Cliente;
import clientes.ClienteOperacoes;

public class Compra extends JFrame {
	ClienteOperacoes crudCliente = new ClienteOperacoes();
	Vector temp = crudCliente.recuperarClientesBD();// Preenchumento do vector de objectos do ficheiro na lista
	Cliente cliente = new Cliente();

	private JLabel jl, jl2;
	private JPanel jp;

	public Compra(int i) {
		jl=new JLabel("Nome : "+((Cliente)temp.get(i)).getNome());
		jl2=new JLabel("Saldo : "+((Cliente)temp.get(i)).getSaldo());
		// -----DEFINIÇÕES DA JANELA*INICIO-------
		this.setTitle("Lista de Produtos");// O tittulo da janela.
		this.setSize(1280, 720);// Width and Height em pixels.[Comprimento, Largura]
		this.setLocation(100, 100);// Onde o programa vai arrancar
		this.setLocationRelativeTo(null);// Onde o programa vai arrancar
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// Quando o utilizador clicar no x. Mata todos os
		this.setLayout(new BorderLayout());
		
		jp=new JPanel();
		jp.add(jl);
		jp.add(jl2);
		jp.setLayout(new FlowLayout(FlowLayout.TRAILING));
		jp.setBackground(Color.LIGHT_GRAY);
		
		this.add(jp,"North");
		
		this.setVisible(true);
	}

}
