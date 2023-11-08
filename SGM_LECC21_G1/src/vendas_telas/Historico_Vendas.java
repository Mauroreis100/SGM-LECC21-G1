package vendas_telas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import carrinho.Carritxo;
import carrinho.OperacoesCarrinho;
import clientes.Cliente;
import clientes.ClienteOperacoes;
import menu.Menu__Prin;
import produto.OperacoesProduto;
import produto.Produto;
import telas.ListaProdutos;
import vendas.OperacoesVenda_Dinheiro;
import vendas.Venda_Dinheiro;
import vendas_telas.Compra_Sucesso;

public class Historico_Vendas extends JFrame implements ActionListener {

	OperacoesProduto crudProduto = new OperacoesProduto();
	Vector temp = crudProduto.recuperarBD();

	OperacoesVenda_Dinheiro vendas = new OperacoesVenda_Dinheiro();
	Vector vendasLista = vendas.recuperarVendas();

	ClienteOperacoes crudCliente = new ClienteOperacoes();
	Vector clientes = crudCliente.recuperarClientesBD();

	private JTable jt_Clientes;
	private JPanel jp, jp_produtos, jp_produto, jp_carrinho, jp_carrinho_butoes;
	private JLabel jl_f;
	private JTextField jtf_se;

	private JButton bt_remover, bt_finalizar, bt_Voltar, bt_Detalhes;

	private String[] coluna = { "Código da Venda", "Nome do Cliente", "Total", "Data e Hora" };
	private JTable tb_listagem;
	private DefaultTableModel tm_listagemModel;

	public Historico_Vendas() {
		jtf_se = new JTextField(15);
		bt_remover = new JButton("Remover Item");
		bt_Detalhes = new JButton("Detalhes");
		bt_Voltar = new JButton("Voltar");
		jp = new JPanel();
		jp_produto = new JPanel();
		jp_produtos = new JPanel();
		jp_carrinho = new JPanel();
		jp_carrinho_butoes = new JPanel();
		tm_listagemModel = new DefaultTableModel();
		tm_listagemModel.addColumn("Código da Venda");
		tm_listagemModel.addColumn("Nome do Cliente");
		tm_listagemModel.addColumn("Total");
		tm_listagemModel.addColumn("Data e Hora");
		tb_listagem = new JTable(tm_listagemModel);

		bt_Detalhes.addActionListener(this);
		bt_Voltar.addActionListener(this);
		// -----DEFINIÇÕES DA JANELA*INICIO-------
		this.setTitle("Histórico");// O tittulo da janela.
		this.setSize(950, 700);// Width and Height em pixels.[Comprimento, Largura]
		this.setLocation(980, 500);// Onde o programa vai arrancar
		this.setLocationRelativeTo(null);// Onde o programa vai arrancar
		this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);// Quando o utilizador clicar no x. Mata todos os
		this.setResizable(true);

		jp_carrinho_butoes.setLayout(new FlowLayout());

		tm_listagemModel.setRowCount(0);
		if(vendasLista==null) {
			for (int i = 0; i < vendasLista.size(); i++) {
				tm_listagemModel.addRow(new Object[] { ((Venda_Dinheiro) vendasLista.get(i)).getId_venda(),
						((Venda_Dinheiro) vendasLista.get(i)).getCliente().getNome(),
						((Venda_Dinheiro) vendasLista.get(i)).getTotal(),
						((Venda_Dinheiro) vendasLista.get(i)).getTotal() });
			}
			
		}else {
			vendasLista=new Vector();
		}

		jp_produtos.setLayout(new FlowLayout(FlowLayout.LEFT));

		jp_produtos.add(jp);

		JScrollPane sp = new JScrollPane(jp_produtos);
		jp_carrinho.add(sp);
		jp_carrinho_butoes.add(bt_Voltar);
		jp_carrinho_butoes.add(bt_Detalhes);
		jp_carrinho.add(new JScrollPane(tb_listagem));
//		jp_carrinho.add(jp_carrinho_butoes, BorderLayout.SOUTH);
		this.add(jp_carrinho,BorderLayout.CENTER);
		this.add(jp_carrinho_butoes,BorderLayout.SOUTH);
		

		this.setVisible(true);
	}

//	@Override
	public static void main(String[] args) {
		new Historico_Vendas();
	}

	public void fecharJanela() {
		this.setVisible(false);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == bt_Voltar) {
			fecharJanela();
			new Menu__Prin();
		}
		if (e.getSource() == bt_Detalhes) {
//			int id = Integer.parseInt(JOptionPane.showInputDialog("ID do Produto que pretende remover do carrinho?"));
//			temp = op_Carrinho.devolverQuantidade(id, temp, carrinho);
//			carrinho = op_Carrinho.removerProduto(id, carrinho, temp);
//
//			tm_listagemModel.setRowCount(0);
//			for (int i = 0; i < carrinho.size(); i++) {
//				tm_listagemModel.addRow(
//						new Object[] { ((Carritxo) carrinho.get(i)).getId(), ((Carritxo) carrinho.get(i)).getNome(),
//								((Carritxo) carrinho.get(i)).getQtd(), ((Carritxo) carrinho.get(i)).getPreco(),
//								((Carritxo) carrinho.get(i)).getPreco() * ((Carritxo) carrinho.get(i)).getQtd() });
//			}
//			System.out.println(carrinho.toString());
//		}
		}
	}
}
