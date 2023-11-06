package comprar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import clientes.Cliente;
import clientes.ClienteOperacoes;
import produto.OperacoesProduto;
import produto.Produto;

public class Compra extends JFrame implements ActionListener, MouseListener {
	ClienteOperacoes crudCliente = new ClienteOperacoes();
	Vector temp = crudCliente.recuperarClientesBD();// Preenchumento do vector de objectos do ficheiro na lista
	Cliente cliente = new Cliente();

	OperacoesProduto crudProduto = new OperacoesProduto();
	Vector temp_p = crudProduto.recuperarBD();
	Produto produto = new Produto();

	private JLabel jl, jl2, jl3;
	private JPanel jp,jp1;

	private JTable jt_produtos;
	private DefaultTableModel tm_listagemModel;

	private JTextField jtf;

	private JButton jb1, jb2, jb3;

	private String[] coluna = { "Código", "Nome", "Quantidade", "Preço" };

	public Compra(int i) {
		jl = new JLabel("Nome : " + ((Cliente) temp.get(i)).getNome() + "              ");
		jl2 = new JLabel("Saldo : " + ((Cliente) temp.get(i)).getSaldo());
		jl.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		jl2.setFont(new Font("Times New Roman", Font.PLAIN, 20));

		jb1 = new JButton("Compra");

		jb1.addActionListener(this);
		jb1.addMouseListener(this);

		jt_produtos = new JTable(listarProdutos(temp_p), coluna);
		jt_produtos.setAutoCreateRowSorter(true);
		JScrollPane sp = new JScrollPane(jt_produtos);

		// -----DEFINIÇÕES DA JANELA*INICIO-------
		this.setTitle("Lista de Produtos");// O tittulo da janela.
		this.setSize(800, 800);// Width and Height em pixels.[Comprimento, Largura]
		this.setLocation(100, 100);// Onde o programa vai arrancar
		this.setLocationRelativeTo(null);// Onde o programa vai arrancar
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// Quando o utilizador clicar no x. Mata todos os
		this.setLayout(new BorderLayout());

		jp = new JPanel();
		jp.add(jl);
		jp.add(jl2);
		jp.setLayout(new FlowLayout(FlowLayout.TRAILING));
		jp.setBackground(Color.LIGHT_GRAY);

		jp1=new JPanel();
		jp1.add(jb1);
		
		this.add(jp, "North");
		this.add(sp, BorderLayout.CENTER);
		this.add(jp1,"South");

		this.setVisible(true);
	}

	private String[][] listarProdutos(Vector temp_p) {
		// ESTA IMPLEMENTAÇÃO COLOCA TODOS OS DADOS DO VECTOR NUMA LISTA
		// MULTIDIMENSIONAL PARA A TABELA
		String[][] dados = new String[temp_p.size()][6];
		for (int i = 0; i < temp_p.size(); i++) {
			dados[i][0] = (((Produto) temp_p.get(i)).getId()) + "";
			dados[i][1] = (((Produto) temp_p.get(i)).getNome()) + "";
			dados[i][2] = (((Produto) temp_p.get(i)).getQtd()) + "";
			dados[i][3] = (((Produto) temp_p.get(i)).getPreco()) + "";
			System.out.println(((Produto) temp_p.get(i)).toString());

		}
		return dados;// O CONSTRUTOR RETORNA A LISTA MULTIDIMENSIONAL
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == jb1) {
			int a=Integer.parseInt(JOptionPane.showInputDialog("Insira a quantidade de :  que pretende comprar"));
			if(!(a==0)) {
				System.out.println("MY BALLZ");
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
