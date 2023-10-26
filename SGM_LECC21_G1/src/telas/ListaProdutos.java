package telas;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.Vector;

import javax.swing.*;

import produto.Editar_Produto;
import produto.OperacoesProduto;
import produto.Produto;

public class ListaProdutos implements ActionListener, MouseListener {
	OperacoesProduto crudProduto = new OperacoesProduto();
	Vector temp = crudProduto.recuperarProdutoBD();// Preenchumento do vector de objectos do ficheiro na lista
													// temporaria no progra
	Produto produto = new Produto();
	private JFrame jf_registrar;

	private JPanel jp_tabela;
	private JPanel jp_butoes;
	private JPanel jp_butoes_norte;

	private JPanel jp_codigo;
	private JPanel jp_nome;
	private JPanel jp_qtdInicial;
	private JPanel jp_preco;
	private JPanel jp_form;
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
	private JButton bt_Voltar;

	private JTable jt_produtos;

	// Column Names
	private String[] coluna = { "Código", "Nome", "Quantidade", "Preço", "Vendidos", "Action" };

	public ListaProdutos() {
		jf_registrar = new JFrame();
		jb_titulo = new JLabel("REGISTRE O PRODUTO");
		jb_titulo = new JLabel("REGISTRE O PRODUTO");
		jb_codigo = new JLabel("Código");
		jb_nome = new JLabel("Nome");
		jb_preco = new JLabel("Preco");
		jb_qtdInicial = new JLabel("Quantidade Inicial");

		tf_codigo = new JTextField(20);
		tf_nome = new JTextField(20);
		tf_qtdInicial = new JTextField(20);
		tf_preco = new JTextField(20);

		bt_Voltar = new JButton("Voltar");

		jp_form = new JPanel();

		jp_preco = new JPanel();
		jp_codigo = new JPanel();
		jp_qtdInicial = new JPanel();
		jp_nome = new JPanel();

		jp_butoes_norte = new JPanel();
		bt_Criar = new JButton("REGISTRAR NOVO PRODUTO");
		bt_Editar = new JButton("EDITAR PRODUTO");
		bt_Eliminar = new JButton("ELIMINAR PRODUTO");

		jp_tabela = new JPanel();
		jp_butoes = new JPanel();

		jf_registrar.setLayout(new BorderLayout());
		// ------------POPULAR ARRAY COM O VECTOR DE OBJECTOS----------
		jt_produtos = new JTable(listarProdutos(temp), coluna);
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
		// -----MOUSE LISNETERS*INICIO--------
		tf_nome.addMouseListener(this);
		tf_preco.addMouseListener(this);
		tf_qtdInicial.addMouseListener(this);
				// ----MOUSE LISTENERS*FIM----------
		jp_butoes.setLayout(new FlowLayout());
		jp_form.setLayout(new FlowLayout(FlowLayout.CENTER, 2, 30));

//		jf_registrar.add(jb_titulo);

		jp_codigo.add(jb_codigo);
		jp_codigo.add(tf_codigo);

		jp_form.add(jp_codigo);

		jp_nome.add(jb_nome);
		jp_nome.add(tf_nome);

		jp_form.add(jp_nome);

		jp_qtdInicial.add(jb_qtdInicial);
		jp_qtdInicial.add(tf_qtdInicial);

		jp_form.add(jp_qtdInicial);

		jp_preco.add(jb_preco);
		jp_preco.add(tf_preco);

		jp_form.add(jp_preco);

		jp_butoes_norte.add(bt_Criar);
//		jp_buttons.add(bt_Voltar);
		jp_butoes.add(bt_Criar);
		jp_butoes.add(bt_Editar);
		jp_butoes.add(bt_Eliminar);

		jp_tabela.setLayout(new FlowLayout());
//		for (int i = 0; i < 3; i++) {
//			jp_tabela.add(new JButton("Botão"));
//		}
		jf_registrar.add(jp_butoes, BorderLayout.SOUTH);
		jf_registrar.add(jp_form, BorderLayout.NORTH);
////		jf_registrar.add(jp_tabela);
		jf_registrar.setVisible(true);
	}

	public static void main(String[] args) {
		new ListaProdutos();
	}

	public void FecharListarProdutos() {
		jf_registrar.setVisible(false);
	}

	private String[][] listarProdutos(Vector temp) {

//ESTA IMPLEMENTAÇÃO COLOCA TODOS OS DADOS DO VECTOR NUMA LISTA MULTIDIMENSIONAL PARA A TABELA
		String[][] dados = new String[temp.size()][6];
		for (int i = 0; i < temp.size(); i++) {
			for (int j = 0; j < 5; j++) {
				dados[i][0] = (((Produto) temp.get(i)).getId()) + "";
				dados[i][1] = (((Produto) temp.get(i)).getNome()) + "";
				dados[i][2] = (((Produto) temp.get(i)).getQtd()) + "";
				dados[i][3] = (((Produto) temp.get(i)).getPreco()) + "";
				dados[i][4] = (((Produto) temp.get(i)).getQtd()) + "";
//					dados[i][5] = new JButton("Delete") + "";
				System.out.println(((Produto) temp.get(j)).toString());
			}
		}
		return dados;//O CONSTRUTOR RETORNA A LISTA MULTIDIMENSIONAL
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == bt_Criar) {
			if (temp != null) {
				produto = new Produto(Integer.parseInt(tf_codigo.getText()), tf_nome.getText(),
						Integer.parseInt(tf_qtdInicial.getText()), Double.parseDouble(tf_preco.getText())); // Objecto
																											// preenchido
																											// com o
																											// formulário
				temp.add(produto);// Actualização do vector temporário com o novo objecto
				if (crudProduto.gravarProdutos(temp)) {//
					JOptionPane.showMessageDialog(null, "PRODUTO REGISTRADO COM SUCESSO", "",
							JOptionPane.ERROR_MESSAGE);// MENSAGEM DE SUCESSO
				}
			} // ELSE PARA VERIFICAR SE PRODUTO JÁ EXISTE PELO NOME!!! -
			new ListaProdutos();// Fecha
			FecharListarProdutos();// Rerenderizar a página com produtos novoss
		}
		if (e.getSource() == bt_Editar) {
			int codigo = Integer
					.parseInt(JOptionPane.showInputDialog("INSIRA O CÓDIGO DO PRODUTO QUE PRETENDE EDITAR"));
			Produto prod = crudProduto.produtoStock(codigo, temp);
			if (prod != null) {
				new Editar_Produto(prod);
				jf_registrar.setVisible(false);
			}
		}
		if (e.getSource() == bt_Eliminar) {
			int codigo = Integer
					.parseInt(JOptionPane.showInputDialog("INSIRA O CÓDIGO DO PRODUTO QUE PRETENDE ELIMINAR"));
			// VERIFICAÇÃO DE EXISTÊNCIA
			Produto prod = crudProduto.produtoStock(codigo, temp);
			if (prod != null) {
				int opcao = JOptionPane.showConfirmDialog(null,
						"TEM A CERTEZA QUE QUER APAGAR \"+\"(DSADAS\"+\" DO STOCK?", "ELIMINAR",
						JOptionPane.YES_NO_OPTION);// YES apaga mesmo e NO não faz nada,
				if (opcao == 0) {

					crudProduto.gravarProdutos(crudProduto.removerProduto(temp, codigo));// GRAVA UMA REMOÇÃO FEITA
					JOptionPane.showMessageDialog(null, "ELIMINADO COM SUCESSO!", "", JOptionPane.ERROR_MESSAGE); // OK
					FecharListarProdutos();
					new ListaProdutos();
				}
			} else {
				JOptionPane.showMessageDialog(null, "NÃO FOI ENCONTRADO", "PRODUTO NÃO FOI ENCONTRADO",
						JOptionPane.WARNING_MESSAGE); // OK
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
		if (e.getSource() == tf_nome || e.getSource() == tf_preco || e.getSource() == tf_qtdInicial) {
			tf_codigo.setText((temp.size()+1)+"");
		}
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (tf_nome.getText().equals("") && tf_preco.getText().equals("") && tf_qtdInicial.getText().equals("")) {
			tf_codigo.setText("");
		}
	}
}
