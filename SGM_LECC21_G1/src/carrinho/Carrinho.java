package carrinho;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import clientes.Cliente;
import clientes.ClienteOperacoes;
import produto.OperacoesProduto;
import produto.Produto;

public class Carrinho extends JFrame implements ActionListener {

	ClienteOperacoes crudCliente = new ClienteOperacoes();
	Vector temp = crudCliente.recuperarClientesBD();// Preenchumento do vector de objectos do ficheiro na lista
	Cliente cliente = new Cliente();

	OperacoesProduto crudProduto = new OperacoesProduto();
	Vector temp_p = crudProduto.recuperarBD();
	Produto produto = new Produto();

	Vector v = new Vector();
//	OperacoesProduto crudCarrinho = new OperacoesProduto();
//	Vector carr = crudProduto.recuperarBD();
//	Carrinho carrin = new Carrinho(qtd, defaultCloseOperation);
//	
	private JTable jt;
	private JButton jb;

	private String[] coluna = { "Código", "Nome", "Preço", "Quantidade", "Total" };

	public Carrinho(int qtd, int id) {
		JTable table = new JTable(listaClientes(v),coluna);
		jb=new JButton("Finalizar");
		jb.setLayout(new FlowLayout(FlowLayout.TRAILING));
		
		jb.addActionListener(this);
		
		this.setTitle("Carrinho");// O tittulo da janela.
		this.setSize(300,250);// Width and Height em pixels.[Comprimento, Largura]
		this.setLocation(75, 50);// Onde o programa vai arrancar
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// Quando o utilizador clicar no x. Mata todos os
		this.setLayout(new BorderLayout());
		
		
		JScrollPane sp = new JScrollPane(table);
		this.add(sp, BorderLayout.CENTER);
		this.add(jb,"South");
		
		this.setVisible(true);
	}

	private String[][] listaClientes(Vector v) {
		// ESTA IMPLEMENTAÇÃO COLOCA TODOS OS DADOS DO VECTOR NUMA LISTA
		// MULTIDIMENSIONAL PARA A TABELA

		String[][] dados = new String[v.size()][6];
		for (int i = 0; i < v.size(); i++) {
			dados[i][0] = (((Carritxo) v.get(i)).getId())+"";
			dados[i][1] = (((Carritxo) v.get(i)).getNome()) + "";
			dados[i][2] = (((Carritxo) v.get(i)).getPreco()) + "";
			dados[i][3] = (((Carritxo) v.get(i)).getQtd()) + "";
			dados[i][4] = (((Carritxo) v.get(i)).getTotal()) + "";
			System.out.println(((Carritxo) v.get(i)).toString());
			
//			Carritxo Carro =new Carritxo((((Produto)temp_p.get(i)).getId()),(((Produto)temp_p.get(i)).getNome()),
//					(((Produto)temp_p.get(i)).getQtd()),Double.parseDouble(((Produto)temp_p.get(i)).getPreco()),Double.parseDouble((Produto)temp_p.get(i);
			
			v.add(dados);


		}
		return dados;// O CONSTRUTOR RETORNA A LISTA MULTIDIMENSIONAL

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
