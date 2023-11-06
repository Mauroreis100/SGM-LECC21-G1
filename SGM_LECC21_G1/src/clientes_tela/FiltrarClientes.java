package clientes_tela;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.*;

import clientes.Cliente;
import clientes.ClienteOperacoes;

public class FiltrarClientes extends JFrame implements ActionListener {

	private JButton jb_nome;
	private JButton jb_cell;
	private JButton jb_saldo;
	private JButton jt_nome_bt;
	
	private JLabel jl_f;

	private JPanel jp;

	public FiltrarClientes() {
		jl_f = new JLabel("Filtra Atravé do...");
		jb_nome = new JButton("Nome");
		jb_cell = new JButton("Número");
		jb_saldo = new JButton("Saldo");
		jt_nome_bt = new JButton("Procura");

		jb_nome.addActionListener(this);
		jb_cell.addActionListener(this);
		jb_saldo.addActionListener(this);
		jt_nome_bt.addActionListener(this);
		// -----DEFINIÇÕES DA JANELA*INICIO-------
		this.setTitle("Filtrar");// O tittulo da janela.
		this.setSize(300, 100);// Width and Height em pixels.[Comprimento, Largura]
		this.setLocation(980, 500);// Onde o programa vai arrancar
		this.setLocationRelativeTo(null);// Onde o programa vai arrancar
		this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);// Quando o utilizador clicar no x. Mata todos os
		this.setResizable(false);
		this.setLayout(new FlowLayout(FlowLayout.CENTER));

		jp = new JPanel();
		jp.add(jb_nome);
		jp.add(jb_cell);
		jp.add(jb_saldo);

		this.add(jl_f);
		this.add(jp);

		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == jb_nome) {
			this.setVisible(false);
			new NomeFiltro();
		}
		if (e.getSource() == jb_cell) {
			this.setVisible(false);
			new NumeroFiltro();
		}
		if (e.getSource() == jb_saldo) {
			this.setVisible(false);
			new SaldoFiltro();
		}
	}
}
