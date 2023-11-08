package telas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.*;

import clientes.Cliente;
import clientes.ClienteOperacoes;
import produto.OperacoesProduto;
import produto.Produto;

public class FiltrarProdutos extends JFrame implements ActionListener, MouseListener {
	OperacoesProduto crudProduto = new OperacoesProduto();
	Vector temp = crudProduto.recuperarBD();// Preenchumento do vector de objectos do ficheiro na lista

	Produto prod = new Produto();// temporaria no progra
	private JTable jt_Clientes, jf_Clientes;
	private JPanel jp, jp_south;
	private JLabel jl_filtrar, lb_nome, lb_id;
	private JTextField tf_codigo, tf_nome;

	private JButton jt_nome_bt, bt_stock, bt_prazo;
	private JRadioButton jrb_nome, jrb_id;
	private ButtonGroup bg_select;
	private String[] coluna = { "Código", "Nome", "Quantidade", "Preço", "Stock Minimo", "Vendidos", "Validade" };

	public FiltrarProdutos(Vector lista) {

//		jl_filtrar = new JLabel(":");
		lb_nome = new JLabel("Nome:");
		tf_nome = new JTextField(8);
		lb_id = new JLabel("ID:");
		tf_codigo = new JTextField(5);
		jt_nome_bt = new JButton("Procura");
		bt_stock = new JButton("Stock Minímo");// FAZER TOOLTIP PARA O BOTÃO
		bt_prazo = new JButton("Para Expirar");
		jrb_nome = new JRadioButton("ID");
		jrb_id = new JRadioButton("NOME");
		bg_select = new ButtonGroup();
		bg_select.add(jrb_nome);
		bg_select.add(jrb_id);
		jp_south = new JPanel();

		// -----------------------------------
		jt_nome_bt.addActionListener(this);
		bt_stock.addActionListener(this);

		// --------------------
		tf_codigo.addMouseListener(this);
		tf_nome.addMouseListener(this);
		// -----DEFINIÇÕES DA JANELA*INICIO-------
		this.setTitle("Filtrar por");// O tittulo da janela.
		this.setSize(900, 500);// Width and Height em pixels.[Comprimento, Largura]
		this.setLocation(980, 500);// Onde o programa vai arrancar
		this.setLocationRelativeTo(null);// Onde o programa vai arrancar
		this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);// Quando o utilizador clicar no x. Mata todos os
		this.setResizable(false);
		this.setLayout(new BorderLayout());

		jp = new JPanel();
//		jp.add(jl_filtrar);
		jp.add(lb_nome);
		jp.add(tf_nome);
		jp.add(lb_id);
		jp.add(tf_codigo);
		jp.add(jt_nome_bt);
		jp_south.add(bt_stock);
		jp_south.add(bt_prazo);

		jp.setLayout(new FlowLayout(FlowLayout.LEFT));

		this.add(jp, "North");
		if (lista != null) {
			Tabela(lista);
		} else {
			lista = new Vector();
			jt_Clientes = new JTable(null);
		}
		JScrollPane sp = new JScrollPane(jt_Clientes);
		this.add(sp, BorderLayout.CENTER);
		JPanel rb = new JPanel();

//		this.add(rb, BorderLayout.SOUTH);
		this.add(jp_south, BorderLayout.SOUTH);
		this.setVisible(true);
	}

	public void closeJanela() {
		this.setVisible(false);
	}

	private void Tabela(Vector temp) {
		String[][] dados = new String[temp.size()][7];
		for (int i = 0; i < temp.size(); i++) {
			dados[i][0] = (((Produto) temp.get(i)).getId()) + "";
			dados[i][1] = (((Produto) temp.get(i)).getNome()) + "";
			dados[i][2] = (((Produto) temp.get(i)).getQtd()) + "";
			dados[i][3] = (((Produto) temp.get(i)).getPreco()) + "";
			dados[i][4] = (((Produto) temp.get(i)).getStockMinimo()) + "";
			dados[i][5] = (((Produto) temp.get(i)).getVendas()) + "";
			dados[i][6] = (((Produto) temp.get(i)).getValidade()) + "";
		}
		if (temp != null) {
			jt_Clientes = new JTable(dados, coluna);
			jt_Clientes.setAutoCreateRowSorter(true);
		} else {
			temp = new Vector<>();
			jt_Clientes = new JTable(null);
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		if (e.getSource() == jt_nome_bt) {
			if ((tf_codigo.getText().equals(""))) {
				Vector nomes = crudProduto.filtrarNomes(tf_nome.getText(), temp);
				closeJanela();
				new FiltrarProdutos(nomes);
			}else if ((tf_nome.getText().equals(""))) {
				Vector idProds = new Vector();
				Produto encontra_prodID = crudProduto.produtoStock(Integer.parseInt(tf_codigo.getText()), temp);
				if(encontra_prodID!=null) {
					idProds.add(encontra_prodID);
					closeJanela();
					new FiltrarProdutos(idProds);
				}else {
					closeJanela();
					new FiltrarProdutos(idProds);
				}
			}else {
				JOptionPane.showMessageDialog(null, "Pesquisa Vazia!", "", JOptionPane.WARNING_MESSAGE);
			} 

		}
		if (e.getSource() == bt_stock) {
			Vector minimo = new Vector();
			for (int i = 0; i < temp.size(); i++) {
				if (((Produto) temp.get(i)).getQtd() < ((Produto) temp.get(i)).getStockMinimo()) {
					minimo.add((Produto) temp.get(i));
				}
			}
			closeJanela();
			new FiltrarProdutos(minimo);
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
		if (e.getSource() == tf_codigo) {
			tf_nome.setText("");
		}
		if (e.getSource() == tf_nome) {
			tf_codigo.setText("");
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
