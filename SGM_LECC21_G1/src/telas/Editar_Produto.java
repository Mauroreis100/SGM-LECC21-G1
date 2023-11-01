package telas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import excepcoes.CampoVazioException;
import produto.OperacoesProduto;
import produto.Produto;

public class Editar_Produto extends JFrame implements ActionListener {
	OperacoesProduto crudProduto = new OperacoesProduto();
	Vector temp = crudProduto.recuperarProdutoBD();
	Produto produto = new Produto();

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

	private JButton bt_Editar;
	private JButton bt_Cancelar;

	public Editar_Produto(Produto prod) {

		jb_titulo = new JLabel("REGISTRE O PRODUTO");
		jb_codigo = new JLabel("Código");
		jb_nome = new JLabel("Nome");
		jb_preco = new JLabel("Preco");
		jb_qtdInicial = new JLabel("Quantidade");

		tf_codigo = new JTextField(20);
		tf_codigo.setText(prod.getId() + "");
		tf_codigo.setEnabled(false);
		tf_nome = new JTextField(20);
		tf_nome.setText(prod.getNome());
		tf_qtdInicial = new JTextField(20);
		tf_qtdInicial.setText(prod.getQtd() + "");
		tf_preco = new JTextField(20);
		tf_preco.setText(prod.getPreco() + "");

		bt_Editar = new JButton("CONFIRMAR EDIÇÕES");
		bt_Cancelar = new JButton("CANCELAR EDIÇÕES");

		jp_form = new JPanel();

		jp_preco = new JPanel();
		jp_codigo = new JPanel();
		jp_qtdInicial = new JPanel();
		jp_nome = new JPanel();

		jp_buttons = new JPanel();
		// -----DEFINIÇÕES DA JANELA*INICIO---------------------------------------
		this.setTitle("EDITAR PREÇO");// O tittulo da janela.
		this.setSize(720, 720);// Width and Height em pixels.[Comprimento, Largura]
		this.setLocation(100, 100);// Onde o programa vai arrancar
		this.setLocationRelativeTo(null);// Onde o programa vai arrancar
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// Quando o utilizador clicar no x. Mata todos os
															// frames
		// ------DEFINIÇÕES DA JANELA*FIM----------------------------------------

		// -----ACTION LISNETERS*INICIO-------------------------------
		bt_Cancelar.addActionListener(this);
		bt_Editar.addActionListener(this);
		// ----ACTION LISTENERS*FIM----------------------------------

		jp_form.setLayout(new FlowLayout(FlowLayout.CENTER, 2, 30));

		this.add(jb_titulo);

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

		jp_buttons.add(bt_Cancelar);
		jp_buttons.add(bt_Editar);
		this.add(jp_form, BorderLayout.CENTER);
		this.add(jp_buttons, BorderLayout.SOUTH);

		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == bt_Cancelar) {
			System.exit(0);
		}
		if ((e.getSource() == bt_Editar)) {
			if (!(tf_nome.getText().isBlank() && tf_preco.getText().isBlank() && tf_qtdInicial.getText().isBlank())) {

				int posicaoProdutoVector = crudProduto.procurarCodigo(temp, Integer.parseInt(tf_codigo.getText()));
				if (posicaoProdutoVector != -1) {
					Produto prod = (Produto) temp.get(posicaoProdutoVector);
						prod.setNome(tf_nome.getText());
						prod.setPreco(Double.parseDouble(tf_preco.getText()));
						prod.setQtd(Integer.parseInt(tf_qtdInicial.getText()));
						temp.set(posicaoProdutoVector, prod);
						crudProduto.gravarProdutos(temp);
						JOptionPane.showMessageDialog(null, "PRODUTO EDITADO COM SUCESSO!", "",
								JOptionPane.INFORMATION_MESSAGE); // OK
						this.setVisible(false);
						new ListaProdutos();
				} else {
					JOptionPane.showMessageDialog(null, "PRODUTO NÃO FOI ENCONTRADO", "", JOptionPane.ERROR_MESSAGE); // OK

				}
			} else {
				JOptionPane.showMessageDialog(null, "EXISTÊNCIA DE CAMPOS VAZIOS! PREENCHA TODOS OS CAMPOS", "",
						JOptionPane.WARNING_MESSAGE); // OK
				throw new CampoVazioException("CAMPOS VAZIOS");
			}
		}

	}
}
