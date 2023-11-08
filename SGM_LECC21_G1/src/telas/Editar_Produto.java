package telas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import armazem.Armazem;
import armazem.ArmazemOperacoes;
import carrinho.Carritxo;
import excepcoes.CampoVazioException;
import produto.OperacoesProduto;
import produto.Produto;

public class Editar_Produto extends JFrame implements ActionListener {
	OperacoesProduto crudProduto = new OperacoesProduto();
	Vector temp = crudProduto.recuperarBD();
	Produto produto = new Produto();
	ArmazemOperacoes opArmazens = new ArmazemOperacoes(); // temporaria no progra

	private JPanel jp_form;

	private JPanel jp_codigo;
	private JPanel jp_nome;
	private JPanel jp_qtdInicial;
	private JPanel jp_preco;

	private JPanel jp_buttons;

	private JLabel jb_titulo;
	private JPanel jp_armazem;
	private JPanel jp_fornecedor, jp_validade;

	private JLabel jb_codigo;
	private JLabel jb_nome;
	private JLabel jb_preco;
	private JLabel jb_qtdInicial;
	private JLabel jb_armazem;
	private JLabel jb_fornecedor, lb_validade, lb_foto;
	private JLabel jb_stock;

	private JTextField tf_codigo;
	private JTextField tf_nome;
	private JTextField tf_preco;
	private JTextField tf_qtdInicial;
	private JComboBox cb_armazem;
	private JComboBox cb_fornecedor, cb_dia, cb_mes, cb_ano;

	private JButton bt_Editar, bt_foto;
	private JButton bt_Cancelar;

	public Editar_Produto(Produto prod) {

		jb_titulo = new JLabel("REGISTRE O PRODUTO");
		jb_codigo = new JLabel("Código");
		jb_nome = new JLabel("Nome");
		jb_preco = new JLabel("Preco");
		jb_stock = new JLabel("Stock Minimo");
		jb_qtdInicial = new JLabel("Quantidade");
		jb_armazem = new JLabel("ARMAZÉM");
		lb_foto = new JLabel("Editar Foto");
		jb_fornecedor = new JLabel("Fornecedor");
		lb_validade = new JLabel("Valido até:");

		tf_codigo = new JTextField(20);
		tf_codigo.setText(prod.getId() + "");
		tf_codigo.setEnabled(false);
		tf_nome = new JTextField(20);
		tf_nome.setText(prod.getNome());
		tf_qtdInicial = new JTextField(20);
		tf_qtdInicial.setText(prod.getQtd() + "");
		tf_preco = new JTextField(20);
		tf_preco.setText(prod.getPreco() + "");

		String dia[] = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16",
				"17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" };
		String mes[] = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" };
		String ano[] = new String[50];
		int anoActual = 2023;
		for (int i = 0; i < ano.length; i++) {

			ano[i] = (anoActual + i) + "";
		}
		cb_dia = new JComboBox(dia);
		cb_mes = new JComboBox(mes);
		cb_ano = new JComboBox(ano);
		String fornecedores[] = { "1-A", "2-B", "Manga", "Milho" };
		cb_fornecedor = new JComboBox(fornecedores);// Só deixa escolher 1 item

		String armazem[] = new String[opArmazens.recuperarBD().size()];

		for (int i = 0; i < opArmazens.recuperarBD().size(); i++) {
			armazem[i] = ((Armazem) opArmazens.recuperarBD().get(i)).getId() + "-"
					+ ((Armazem) opArmazens.recuperarBD().get(i)).getNome();
		}
		cb_armazem = new JComboBox(armazem);// Só deixa escolher 1 item

		bt_Editar = new JButton("CONFIRMAR EDIÇÕES");
		bt_Cancelar = new JButton("CANCELAR EDIÇÕES");
		ImageIcon icon = new ImageIcon(prod.getFoto());
		int larguraDesejada = 150;
		int alturaDesejada = 150;
		Image imagemRedimensionada = icon.getImage().getScaledInstance(larguraDesejada, alturaDesejada,
				Image.SCALE_SMOOTH);
		ImageIcon novoIcon = new ImageIcon(imagemRedimensionada);
		bt_foto = new JButton("FOTO", novoIcon);
		jp_form = new JPanel();
		jp_armazem = new JPanel();
		jp_preco = new JPanel();
		jp_codigo = new JPanel();
		jp_qtdInicial = new JPanel();
		jp_nome = new JPanel();
		jp_fornecedor = new JPanel();
		jp_buttons = new JPanel();
		jp_validade = new JPanel();
		// -----DEFINIÇÕES DA JANELA*INICIO---------------------------------------
		this.setTitle("EDITAR PREÇO");// O tittulo da janela.
		this.setSize(720, 720);// Width and Height em pixels.[Comprimento, Largura]
		this.setLocation(100, 100);// Onde o programa vai arrancar
		this.setLocationRelativeTo(null);// Onde o programa vai arrancar
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// Quando o utilizador clicar no x. Mata todos os
															// frames
		// ------DEFINIÇÕES DA JANELA*FIM----------------------------------------

		// -----ACTION LISNETERS*INICIO-------------------------------
//		bt_foto.addActionListener(this);
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

		jp_armazem.add(jb_armazem);
		jp_armazem.add(cb_armazem);

		jp_form.add(jp_armazem);

		jp_fornecedor.add(jb_fornecedor);
		jp_fornecedor.add(cb_fornecedor);

		jp_form.add(jp_fornecedor);

		jp_validade.add(lb_validade);
		jp_validade.add(cb_dia);
		jp_validade.add(cb_mes);
		jp_validade.add(cb_ano);

		jp_form.add(jp_validade);
		this.add(bt_foto, BorderLayout.NORTH);
		jp_buttons.add(bt_Cancelar);
		jp_buttons.add(bt_Editar);
		this.add(jp_form, BorderLayout.CENTER);
		this.add(jp_buttons, BorderLayout.SOUTH);

		this.setVisible(true);
	}

	public void fechaTela() {
		this.setVisible(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == bt_Cancelar) {
			fechaTela();
			new ListaProdutos();
		}
//		if(e.getSource()==bt_foto) {
//	
//		}
		if ((e.getSource() == bt_Editar)) {
			if (!(tf_nome.getText().isBlank() && tf_preco.getText().isBlank() && tf_qtdInicial.getText().isBlank())) {

				int posicaoProdutoVector = crudProduto.procurarCodigo(temp, Integer.parseInt(tf_codigo.getText()));
				if (posicaoProdutoVector != -1) {
					Produto prod = (Produto) temp.get(posicaoProdutoVector);
					prod.setNome(tf_nome.getText());
					// EDITAR FOTO
//					bt_foto.addActionListener(new ActionListener() {
//						@Override
//						public void actionPerformed(ActionEvent e) {
//							System.out.println("FUNCIONA?");
//							fechaTela();
//							new Editar_Produto(prod);
//								
//							JFileChooser fileChooser = new JFileChooser();
//							int result = fileChooser.showOpenDialog(null);
//							if (result == JFileChooser.APPROVE_OPTION) {
//								File selectedFile = fileChooser.getSelectedFile();
//								try {
//									BufferedImage image = ImageIO.read(selectedFile);
//									ImageIcon icon = new ImageIcon(image);
//									int larguraDesejada = 50;
//									int alturaDesejada = 50;
//									Image imagemRedimensionada = icon.getImage().getScaledInstance(larguraDesejada,
//											alturaDesejada, Image.SCALE_SMOOTH);
//									ImageIcon novoIcon = new ImageIcon(imagemRedimensionada);
//									String pathFoto = selectedFile.getAbsolutePath();
//									prod.setFoto(pathFoto);
//									
//								} catch (Exception ex) {
//									ex.printStackTrace();
//									JOptionPane.showMessageDialog(null, "Error loading image", "Error",
//											JOptionPane.ERROR_MESSAGE);
//								}
//							}
//						}
//					});
//					
					prod.setPreco(Double.parseDouble(tf_preco.getText()));
					prod.setQtd(Integer.parseInt(tf_qtdInicial.getText()));
					prod.setFornecedor(cb_fornecedor.getSelectedItem().toString());
//						prod.setArmazen_nr(posicaoProdutoVector);ARMAZEM PELO ID OU PELO NOME AFFF
					temp.set(posicaoProdutoVector, prod);
					crudProduto.gravarObjecto(temp);
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
