package telas;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import armazem.*;
import produto.OperacoesProduto;
import produto.Produto;
import excepcoes.CampoVazioException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ListaProdutos implements ActionListener, MouseListener {
	OperacoesProduto crudProduto = new OperacoesProduto();
	Vector temp = crudProduto.recuperarBD();// Preenchumento do vector de objectos do ficheiro na lista
	ArmazemOperacoes opArmazens = new ArmazemOperacoes(); // temporaria no progra
	Produto produto = new Produto();
	private JFrame jf_registrar;

	private JPanel jp_tabela;
	private JPanel jp_butoes;
	private JPanel jp_butoes_norte;

	private JPanel jp_codigo;
	private JPanel jp_nome;
	private JPanel jp_qtdInicial;
	private JPanel jp_preco;
	private JPanel jp_armazem;
	private JPanel jp_fornecedor;
	private JPanel jp_form;
	private JPanel jp_foto, jp_validade;
	private JLabel jb_titulo;
	private ImageIcon img_icon = new ImageIcon("assets/icons/Camera.png"); // Substitua pelo caminho do arquivo da
																			// imagem

	private JLabel lb_foto;;
	private JLabel jb_codigo;
	private JLabel jb_nome;
	private JLabel jb_preco;
	private JLabel jb_qtdInicial;
	private JLabel jb_armazem;
	private JLabel jb_fornecedor, lb_validade, lb_dia, lb_mes, lb_ano;

	private JTextField tf_codigo;
	private JTextField tf_nome;
	private JTextField tf_preco;
	private JTextField tf_qtdInicial, tf_dia, tf_mes, tf_ano;
	private JComboBox cb_armazem;
	private JComboBox cb_fornecedor,cb_dia,cb_mes,cb_ano;

	private JButton bt_Criar;
	private JButton bt_Eliminar;
	private JButton bt_Editar;
	private JButton bt_filtrar;
	private JButton bt_Voltar, bt_Relatorio;

	private JTable jt_produtos;
	private DefaultTableModel tm_listagemModel;
//	 private String datePattern = "dd-MM-yy";
//	   private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

//	private UtilDateModel model;
//	private JDatePanelImpl datePanel;
//	private JDatePickerImpl datePicker;

	// Column Names
	private String[] coluna = { "Código", "Armazem", "Nome", "Stock Minímo", "Quantidade", "Preço", "Nº Vendas",
			"Fornecedor" };

	private JFileChooser jf;

	public ListaProdutos() {
		int larguraDesejada = 50;
		int alturaDesejada = 50;
		Image imagemRedimensionada = img_icon.getImage().getScaledInstance(larguraDesejada, alturaDesejada,
				Image.SCALE_SMOOTH);
		ImageIcon novoIcon = new ImageIcon(imagemRedimensionada);
		lb_foto = new JLabel(novoIcon);
		jf_registrar = new JFrame();
//		UtilDateModel model = new UtilDateModel();
//		JDatePanelImpl datePanel = new JDatePanelImpl(model, null);
//		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, null);
//		Properties p=new Properties();
//		p.put("text.day","Day");
//		p.put("text.month","Month");
//		p.put("text.year","Year");
//		UtilDateModel model = new UtilDateModel();
//		datePanel=new JDatePanelImpl(model,p);
//		datePicker=new JDatePickerImpl(datePanel,null);

		jb_titulo = new JLabel("REGISTRE O PRODUTO");
		jb_titulo = new JLabel("REGISTRE O PRODUTO");
		jb_codigo = new JLabel("Código");
		jb_nome = new JLabel("Nome");
		jb_preco = new JLabel("Preco");
		jb_qtdInicial = new JLabel("Quantidade Inicial");
		jb_armazem = new JLabel("Armazém");
		jb_fornecedor = new JLabel("Fornecedor:");
		lb_validade = new JLabel("Prazo de Validade:");
		lb_dia = new JLabel("Validade -> Dia:");
		lb_mes = new JLabel("Mês:");
		lb_ano = new JLabel("Ano:");
//		jt_produtos.setEnabled(false);
		tf_codigo = new JTextField(5);
		tf_codigo.setEnabled(false);
		tf_nome = new JTextField(8);
		tf_qtdInicial = new JTextField(8);
		tf_preco = new JTextField(8);
		tf_dia = new JTextField(3);
		tf_mes = new JTextField(3);
		tf_ano = new JTextField(3);
		String dia[] = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16",
				"17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" };
		String mes[] = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" };
		String ano[]=new String[50];
		int anoActual=2023;
		for(int i=0;i<ano.length;i++) {
			
			ano[i]=(anoActual+i)+"";
		}
		cb_dia=new JComboBox(dia);
		cb_mes=new JComboBox(mes);
		cb_ano=new JComboBox(ano);
		String fornecedores[] = { "1-A", "2-B", "Manga", "Milho" };
		cb_fornecedor = new JComboBox(fornecedores);// Só deixa escolher 1 item

		String armazem[] = new String[opArmazens.recuperarBD().size()];

		for (int i = 0; i < opArmazens.recuperarBD().size(); i++) {
			armazem[i] = ((Armazem) opArmazens.recuperarBD().get(i)).getId() + "-"
					+ ((Armazem) opArmazens.recuperarBD().get(i)).getNome();
		}
		cb_armazem = new JComboBox(armazem);// Só deixa escolher 1 item

		bt_Voltar = new JButton("Voltar");

		jp_form = new JPanel();

		jp_preco = new JPanel();
		jp_codigo = new JPanel();
		jp_qtdInicial = new JPanel();
		jp_nome = new JPanel();
		jp_fornecedor = new JPanel();
		jp_armazem = new JPanel();
		jp_foto = new JPanel();
		jp_butoes_norte = new JPanel();
		jp_validade = new JPanel();
		bt_Criar = new JButton("REGISTRAR NOVO PRODUTO");
		bt_Editar = new JButton("EDITAR PRODUTO");
		bt_Eliminar = new JButton("ELIMINAR PRODUTO");
		bt_filtrar = new JButton("FILTRO");
		bt_Relatorio = new JButton("RELATÓRIO");

		jp_tabela = new JPanel();
		jp_butoes = new JPanel();

		jf = new JFileChooser();
		jf_registrar.setLayout(new BorderLayout());
		// ------------POPULAR ARRAY COM O VECTOR DE OBJECTOS----------
		if (temp != null) {
			jt_produtos = new JTable(listarProdutos(temp), coluna);
			jt_produtos.setAutoCreateRowSorter(true);

		} else {
			temp = new Vector<>();
			jt_produtos = new JTable(null);
		}
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

		// -----ACTION LISNETERS*INICIO---------------
		bt_Criar.addActionListener(this);
		bt_Editar.addActionListener(this);
		bt_Eliminar.addActionListener(this);
		bt_filtrar.addActionListener(this);

		// ----ACTION LISTENERS*FIM-------------------

		// -----MOUSE LISNETERS*INICIO--------
		tf_nome.addMouseListener(this);
		tf_preco.addMouseListener(this);
		tf_qtdInicial.addMouseListener(this);
		jp_foto.addMouseListener(this);
		// ----MOUSE LISTENERS*FIM----------
		jp_butoes.setLayout(new FlowLayout());
		jp_form.setLayout(new FlowLayout(FlowLayout.LEFT, 2, 30));

//		jf_registrar.add(jb_titulo);
		jp_foto.add(lb_foto);

		jp_form.add(jp_foto);
		jp_codigo.add(jb_codigo);
		jp_codigo.add(tf_codigo);

		jp_form.add(jp_codigo);

		jp_nome.add(jb_nome);
		jp_nome.add(tf_nome);

		jp_form.add(jp_nome);

		jp_qtdInicial.add(jb_qtdInicial);
		jp_qtdInicial.add(tf_qtdInicial);

		jp_form.add(jp_qtdInicial);

		jp_fornecedor.add(jb_fornecedor);
		jp_fornecedor.add(cb_fornecedor);

		jp_form.add(jp_fornecedor);

		jp_armazem.add(jb_armazem);
		jp_armazem.add(cb_armazem);

		jp_form.add(jp_armazem);

//		jp_armazem.add(jb_fornecedor);

		jp_preco.add(jb_preco);
		jp_preco.add(tf_preco);

		jp_form.add(jp_preco);

//		jp_validade.add(lb_validade);
		jp_validade.add(lb_dia);
		jp_validade.add(cb_dia);
		jp_validade.add(lb_mes);
		jp_validade.add(cb_mes);
		jp_validade.add(lb_ano);
		jp_validade.add(cb_ano);

		jp_form.add(jp_validade);

		jp_butoes_norte.add(bt_Criar);
		jp_butoes.add(bt_Criar);
		jp_butoes.add(bt_Editar);
		jp_butoes.add(bt_Eliminar);
		jp_butoes.add(bt_filtrar);

		jp_tabela.setLayout(new FlowLayout());

		jf_registrar.add(jp_butoes, BorderLayout.SOUTH);
		jf_registrar.add(jp_form, BorderLayout.NORTH);
////		jf_registrar.add(jp_tabela);
		jf_registrar.pack();
		jf_registrar.setVisible(true);
	}

	public static void main(String[] args) {
		new ListaProdutos();
	}

	public void FecharListarProdutos() {
		jf_registrar.setVisible(false);
	}

	private String[][] listarProdutos(Vector temp) {
		// ESTA IMPLEMENTAÇÃO COLOCA TODOS OS DADOS DO VECTOR NUMA LISTA
		// MULTIDIMENSIONAL PARA A TABELA
		String[][] dados = new String[temp.size()][8];
		for (int i = 0; i < temp.size(); i++) {
			dados[i][0] = (((Produto) temp.get(i)).getId()) + "";
			dados[i][1] = (((Produto) temp.get(i)).getArmazen_nr()) + "";
			dados[i][2] = (((Produto) temp.get(i)).getNome()) + "";
			dados[i][3] = (((Produto) temp.get(i)).getStockMinimo()) + "";
			dados[i][4] = (((Produto) temp.get(i)).getQtd()) + "";
			dados[i][5] = (((Produto) temp.get(i)).getPreco()) + "";
			dados[i][6] = (((Produto) temp.get(i)).getVendas()) + "";
			dados[i][7] = (((Produto) temp.get(i)).getFornecedor()) + "";
			System.out.println(((Produto) temp.get(i)).toString());

		}
		return dados;// O CONSTRUTOR RETORNA A LISTA MULTIDIMENSIONAL
	}

	public void actionPerformed(ActionEvent e) {

		if ((e.getSource() == bt_Criar)) {
			String data = tf_dia.getText() + "-" + tf_mes.getText() + "-" + tf_ano.getText();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy"); // Define your date format
			LocalDate myDate = LocalDate.parse(data, formatter);

			if (!(tf_nome.getText().equals("") && tf_preco.getText().equals("")
					&& tf_qtdInicial.getText().equals(""))) {

				if (crudProduto.existe(tf_nome.getText(), temp)) {
					JOptionPane.showMessageDialog(null, "ATENÇÃO",
							"PRODUTO COM NOME " + tf_nome.getText() + " JÁ EXISTE", JOptionPane.WARNING_MESSAGE); // OK
				} else {
					produto = new Produto(Integer.parseInt(tf_codigo.getText()), (cb_armazem.getSelectedIndex()),
							tf_nome.getText(), Integer.parseInt(tf_qtdInicial.getText()),
							Double.parseDouble(tf_preco.getText()), 0, cb_fornecedor.getSelectedItem().toString()); // Preenchendo
					// objecto
					// com o
					// formulário
					temp.add(produto);// Actualização do vector temporário com o novo objecto
					if (crudProduto.gravarObjecto(temp)) {//
						JOptionPane.showMessageDialog(null, "PRODUTO REGISTRADO COM SUCESSO", "REGISTRADO COM SUCESSO",
								JOptionPane.WARNING_MESSAGE);// MENSAGEM DE SUCESSO
						new ListaProdutos();// Fecha
						FecharListarProdutos();// Rerenderizar a página com produtos novoss
					} // ERRO NA GRAVAÇÃO
				}
			} else {

				JOptionPane.showMessageDialog(null, "EXISTÊNCIA DE CAMPOS VAZIOS! PREENCHA TODOS OS CAMPOS", "",
						JOptionPane.WARNING_MESSAGE); // OK
				throw new CampoVazioException("CAMPOS VAZIOS");
			}
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

					crudProduto.gravarObjecto(crudProduto.removerObjecto(temp, codigo));// GRAVA UMA REMOÇÃO FEITA
					JOptionPane.showMessageDialog(null, "ELIMINADO COM SUCESSO!", "", JOptionPane.ERROR_MESSAGE); // OK
					FecharListarProdutos();
					new ListaProdutos();
				}
			} else {
				JOptionPane.showMessageDialog(null, "NÃO FOI ENCONTRADO", "PRODUTO NÃO FOI ENCONTRADO",
						JOptionPane.WARNING_MESSAGE); // OK
			}

		}
		if (e.getSource() == bt_filtrar) {
//			String[] options = { "ID", "NOME" };
//			int x = JOptionPane.showOptionDialog(null, "Selecione o teu filtro", "FILTRAR POR...",
//					JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
//			if (x == 0) {
//				int codigo = Integer.parseInt(JOptionPane.showInputDialog("Código que pretende procurar"));
//				Produto prod = crudProduto.produtoStock(codigo, temp);
//				if (prod != null) {
//					
//				} else {
//					JOptionPane.showMessageDialog(null, "NÃO FOI ENCONTRADO", "PRODUTO NÃO FOI ENCONTRADO",
//							JOptionPane.WARNING_MESSAGE); // OK
//				}
//			}
			new FiltrarProdutos(new Vector());
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == jp_foto) {
			jf.showOpenDialog(null);
			jf_registrar.remove(jp_foto);
//			jf_registrar.add()
			lb_foto.setText(jf.getSelectedFile().getAbsolutePath());
		}
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
			if (temp != null) {
				tf_codigo.setText(temp.size() + 1 + "");
			} else {
				tf_codigo.setText(((Produto) temp.lastElement()).getId() + 1 + "");
			}
		}

	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (tf_nome.getText().equals("") && tf_preco.getText().equals("") && tf_qtdInicial.getText().equals("")) {
			tf_codigo.setText("");
		}
	}

}