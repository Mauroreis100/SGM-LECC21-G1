package telas;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.Vector;

import javax.swing.*;

import produto.OperacoesProduto;
import produto.Produto;

public class ListaProdutos implements ActionListener {
	OperacoesProduto crudProduto = new OperacoesProduto();
	Produto produto = new Produto();
	private JFrame jf_registrar;

	private JPanel jp_tabela;
	private JPanel jp_butoes;

	private JPanel jp_codigo;
	private JPanel jp_nome;
	private JPanel jp_qtdInicial;
	private JPanel jp_preco;

	private JLabel jb_titulo;

	private JLabel jb_codigo;
	private JLabel jb_nome;
	private JLabel jb_preco;
	private JLabel jb_qtdInicial;

	private JTextField tf_codigo;
	private JTextField tf_nome;
	private JTextField tf_preco;
	private JTextField tf_qtdInicial;

	private JButton bt_Criar;
	private JButton bt_Eliminar;
	private JButton bt_Editar;
	private JButton bt_filtrar;

	private JTable jt_produtos;

	// Column Names
	private String[] coluna = { "Código", "Nome", "Quantidade", "Preço", "Vendidos", "Action" };

	public ListaProdutos() {
		jf_registrar = new JFrame();
		jb_titulo = new JLabel("REGISTRE O PRODUTO");

		bt_Criar = new JButton("REGISTRAR NOVO PRODUTO");
		bt_Editar = new JButton("EDITAR PRODUTO");
		bt_Eliminar = new JButton("ELIMINAR PRODUTO");

		jp_tabela = new JPanel();
		jp_butoes = new JPanel();

		// ------------POPULAR ARRAY COM O VECTOR DE OBJECTOS----------
		// Recuperação de todos os Produtos
		Vector temp = new Vector();
		String caminhoProduto = "bd/ProdutosDB.dat";
		File fileProdutos = new File(caminhoProduto);
		if (fileProdutos.length() != 0) {
			temp = (Vector) crudProduto.recuperarObjecto(temp, caminhoProduto);
		} else {
			System.out.println("Erro no FRAME");
		}

		// Data to be displayed in the JTable
		String[][] dados = new String[temp.size()][6];
		for (int i = 0; i < temp.size(); i++) {
			for (int j = 0; j < 5; j++) {
				dados[i][0] = (((Produto) temp.get(i)).getId()) + "";
				dados[i][1] = (((Produto) temp.get(i)).getNome()) + "";
				dados[i][2] = (((Produto) temp.get(i)).getQtd()) + "";
				dados[i][3] = (((Produto) temp.get(i)).getPreco()) + "";
				dados[i][4] = (((Produto) temp.get(i)).getQtd()) + "";
//				dados[i][5] = new JButton("Delete") + "";
				System.out.println(((Produto) temp.get(j)).toString());
			}
		}
		jf_registrar.setLayout(new BorderLayout());

		jt_produtos = new JTable(dados, coluna);
		jt_produtos.setAutoCreateRowSorter(true);
		JScrollPane sp = new JScrollPane(jt_produtos);
		jf_registrar.add(sp, BorderLayout.CENTER);

		// -----DEFINIÇÕES DA JANELA*INICIO-------
		jf_registrar.setTitle("Template de JForm");// O tittulo da janela.
		jf_registrar.setSize(1280, 720);// Width and Height em pixels.[Comprimento, Largura]
		jf_registrar.setLocation(100, 100);// Onde o programa vai arrancar
		jf_registrar.setLocationRelativeTo(null);// Onde o programa vai arrancar
		jf_registrar.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// Quando o utilizador clicar no x. Mata todos os
																	// frames

		// ------DEFINIÇÕES DA JANELA*FIM--------

		// -----ACTION LISNETERS*INICIO--------
		bt_Criar.addActionListener(this);
		bt_Editar.addActionListener(this);
		bt_Eliminar.addActionListener(this);
		// ----ACTION LISTENERS*FIM----------
		jp_butoes.setLayout(new FlowLayout());

		jp_butoes.add(bt_Criar);
		jp_butoes.add(bt_Editar);
		jp_butoes.add(bt_Eliminar);

		jp_tabela.setLayout(new FlowLayout());
//		for (int i = 0; i < 3; i++) {
//			jp_tabela.add(new JButton("Botão"));
//		}
		jf_registrar.add(jp_butoes, BorderLayout.NORTH);
//		jf_registrar.add(jp_tabela);

		jf_registrar.setVisible(true);
	}

	public static void main(String[] args) {
		new ListaProdutos();
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
//		if (e.getSource() == bt_Cancelar)  BOTÃO SAIR?
//			System.exit(0);
//		}
		if (e.getSource() == bt_Criar) {
			new RegistrarProduto();
		}
	}
}
