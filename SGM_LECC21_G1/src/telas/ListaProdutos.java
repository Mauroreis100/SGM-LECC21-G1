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

	private JPanel jp_form;

	private JPanel jp_codigo;
	private JPanel jp_nome;
	private JPanel jp_qtdInicial;
	private JPanel jp_preco;

	private JPanel jp_buttons;

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
	private JButton bt_Cancelar;

	private JTable jt_produtos;

	
	
	// Column Names
	private String[] coluna = { "Código", "Nome", "Quantidade", "Preço","Vendidos" };

	public ListaProdutos() {
		jf_registrar = new JFrame();
		jb_titulo = new JLabel("REGISTRE O PRODUTO");
		jb_codigo = new JLabel("Código");
		jb_nome = new JLabel("Nome");
		jb_preco = new JLabel("Preco");
		jb_qtdInicial = new JLabel("Quantidade Inicial");

		tf_codigo = new JTextField(20);
		tf_nome = new JTextField(20);
		tf_qtdInicial = new JTextField(20);
		tf_preco = new JTextField(20);
//		tf_codigo=new JTextField(20);

		bt_Criar = new JButton("Criar");
		bt_Cancelar = new JButton("Cancelar");

		jp_form = new JPanel();

		jp_preco = new JPanel();
		jp_codigo = new JPanel();
		jp_qtdInicial = new JPanel();
		jp_nome = new JPanel();

		jp_buttons = new JPanel();
		
		//------------POPULAR ARRAY COM O VECTOR DE OBJECTOS----------
		// Recuperação de todos os Produtos
		Vector temp=new Vector();
				String caminhoProduto = "bd/ProdutosDB.dat";
				File fileProdutos = new File(caminhoProduto);
				if (fileProdutos.length() != 0) {
					temp = (Vector) crudProduto.recuperarObjecto(temp,caminhoProduto);
					System.out.println(((Produto)temp.get(0)).getNome());
				}else {
					System.out.println("Erro no FRAME");
				}
		
				// Data to be displayed in the JTable
		String[][] dados= new String[temp.size()][5];
		for(int i=0;i<5;i++) {
			for(int j=i;j<temp.size();j++) {
				dados[i][0]=(((Produto)temp.get(j)).getId())+"";
				dados[i][1]=(((Produto)temp.get(j)).getNome())+"";
				dados[i][2]=(((Produto)temp.get(j)).getQtd())+"";
				dados[i][3]=(((Produto)temp.get(j)).getPreco())+"";
				dados[i][4]=(((Produto)temp.get(j)).getQtd())+"";
			}
		}
		
		jt_produtos=new JTable(dados,coluna);
		JScrollPane sp = new JScrollPane(jt_produtos);
		jf_registrar.add(sp);
		
		// -----DEFINIÇÕES DA JANELA*INICIO-------
		jf_registrar.setTitle("Template de JForm");// O tittulo da janela.
		jf_registrar.setSize(1280, 720);// Width and Height em pixels.[Comprimento, Largura]
		jf_registrar.setLocation(100, 100);// Onde o programa vai arrancar
		jf_registrar.setLocationRelativeTo(null);// Onde o programa vai arrancar
		jf_registrar.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// Quando o utilizador clicar no x. Mata todos os
																	// frames
		// ------DEFINIÇÕES DA JANELA*FIM--------

		// -----ACTION LISNETERS*INICIO--------
		bt_Cancelar.addActionListener(this);
		bt_Criar.addActionListener(this);
		// ----ACTION LISTENERS*FIM----------

		jp_form.setLayout(new FlowLayout(FlowLayout.CENTER, 2, 30));


		jf_registrar.setVisible(true);
	}

	public static void main(String[] args) {
		new ListaProdutos();
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == bt_Cancelar) {
			System.exit(0);
		}
		if (e.getSource() == bt_Criar) {

		}
	}
}
