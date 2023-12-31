package comprar;

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
import vendas_telas.Compra_Sucesso;

public class CompraSele extends JFrame implements ActionListener {
	OperacoesProduto crudProduto = new OperacoesProduto();
	Vector temp = crudProduto.recuperarBD();// Preenchumento do vector de objectos do ficheiro na listanto do vector de
											// objectos do ficheiro na lista
	Vector carrinho = new Vector();
	ClienteOperacoes crudCliente = new ClienteOperacoes();
	Vector clientes = crudCliente.recuperarClientesBD();
	OperacoesCarrinho op_Carrinho = new OperacoesCarrinho();
	private JTable jt_Clientes;
	private JPanel jp, jp_produtos, jp_produto, jp_carrinho, jp_cliente, jp_carrinho_butoes;
	private JLabel jl_f, lb_codigo, lb_nome, lb_BI;
	private JTextField jtf_se;

	private JButton bt_remover, bt_finalizar, bt_Voltar;

	private String[] coluna = { "Código", "Nome", "BI", "Telefone", "Saldo" };
	private JTable tb_listagem;
	private DefaultTableModel tm_listagemModel;

	public CompraSele(int id_cliente) {
		
		jtf_se = new JTextField(15);
		bt_remover = new JButton("Remover Item");
		bt_finalizar = new JButton("Finalizar");
		bt_Voltar = new JButton("Voltar");
		jp = new JPanel();
		jp_produto = new JPanel();
		jp_cliente=new JPanel();
		jp_produtos = new JPanel();
		jp_carrinho = new JPanel();
		jp_carrinho_butoes = new JPanel();
		tm_listagemModel = new DefaultTableModel();
		tm_listagemModel.addColumn("ID");
		tm_listagemModel.addColumn("Nome");
		tm_listagemModel.addColumn("Quantidade");
		tm_listagemModel.addColumn("Preço p/ Uni");
		tm_listagemModel.addColumn("Valor");
		tb_listagem = new JTable(tm_listagemModel);

		bt_remover.addActionListener(this);
		bt_Voltar.addActionListener(this);
		// -----DEFINIÇÕES DA JANELA*INICIO-------
		this.setTitle("Escolha");// O tittulo da janela.
		this.setSize(950, 500);// Width and Height em pixels.[Comprimento, Largura]
		this.setLocation(980, 500);// Onde o programa vai arrancar
		this.setLocationRelativeTo(null);// Onde o programa vai arrancar
		this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);// Quando o utilizador clicar no x. Mata todos os
		this.setResizable(true);
		this.setLayout(new GridLayout(1, 2));
		jp.setLayout(new GridLayout(2, 1, 2, 2));
		jp_carrinho_butoes.setLayout(new FlowLayout());

//		jp = new JPanel();
		for (int i = 0; i < temp.size(); i++) {
			ImageIcon img_icon = new ImageIcon(((Produto) temp.get(i)).getFoto());
			int larguraDesejada = 100;
			int alturaDesejada = 100;
			Image imagemRedimensionada = img_icon.getImage().getScaledInstance(larguraDesejada, alturaDesejada,
					Image.SCALE_SMOOTH);
			ImageIcon novoIcon = new ImageIcon(imagemRedimensionada);
			JButton bt_produtos = new JButton(((Produto) temp.get(i)).getNome(), novoIcon);
			bt_produtos.setVerticalTextPosition(JButton.BOTTOM);
			bt_produtos.setHorizontalTextPosition(JButton.CENTER);
			String nomeProd = ((Produto) temp.get(i)).getNome();
			Produto prod = (Produto) temp.get(i);
			int posicao = i;
			jp.add(bt_produtos);
			tm_listagemModel.setRowCount(0);
			bt_produtos.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// Your action code here

					int quantidade = Integer.parseInt(JOptionPane.showInputDialog("Quantidade de " + nomeProd));
//	            	carrinho.add(new Carritxo(prod.getId(),prod.getNome(),quantidade,prod.getPreco(),prod.getPreco()*quantidade));
					carrinho = op_Carrinho.adicionarProduto(prod.getId(), carrinho, temp, quantidade);
					tm_listagemModel.setRowCount(0);
					for (int i = 0; i < carrinho.size(); i++) {
						tm_listagemModel.addRow(new Object[] { ((Carritxo) carrinho.get(i)).getId(),
								((Carritxo) carrinho.get(i)).getNome(), ((Carritxo) carrinho.get(i)).getQtd(),
								((Carritxo) carrinho.get(i)).getPreco(),
								prod.getPreco() * ((Carritxo) carrinho.get(i)).getQtd() });
					}
					System.out.println(carrinho.toString());
				}
			});

		}
		bt_finalizar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Object[] options = { "Sim", "Continuar a comprar" };
				int n = JOptionPane.showOptionDialog(null, "Deseja finalizar a compra?", "Confirme",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, // do not use a custom Icon
						options, // the titles of buttons
						options[0]); // default button title int choice = JOptionPane.showConfirmDialog(null, "Deseja
										// finalizar a compra?", "Confirme", JOptionPane.YES_NO_OPTION);
				if (n == 0) {
					if (crudProduto.gravarObjecto(temp)) {
						// VENDA DEVE AUMENTAR
						fecharJanela();
						new Compra_Sucesso(crudCliente.procuraClienteID(id_cliente, clientes), carrinho);

					}
				}
			}
		});

		jp_produtos.setLayout(new FlowLayout(FlowLayout.LEFT));

		jp_produtos.add(jp);

		JScrollPane sp = new JScrollPane(jp_produtos);
		jp_carrinho.add(sp);
		jp_carrinho_butoes.add(bt_Voltar);
		jp_carrinho_butoes.add(bt_remover);
		jp_carrinho_butoes.add(bt_finalizar);
		jp_carrinho.add(new JScrollPane(tb_listagem));
		jp_carrinho.add(jp_carrinho_butoes, BorderLayout.SOUTH);
		this.add(sp);
		this.add(jp_carrinho);

		this.setVisible(true);
	}

//	@Override
	public static void main(String[] args) {
		new CompraSele(1);
	}

	public void fecharJanela() {
		this.setVisible(false);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == bt_Voltar) {
			fecharJanela();
			new Menu__Prin();
		}
		if (e.getSource() == bt_remover) {
			int id = Integer.parseInt(JOptionPane.showInputDialog("ID do Produto que pretende remover do carrinho?"));
			temp = op_Carrinho.devolverQuantidade(id, temp, carrinho);
			carrinho = op_Carrinho.removerProduto(id, carrinho, temp);

			tm_listagemModel.setRowCount(0);
			for (int i = 0; i < carrinho.size(); i++) {
				tm_listagemModel.addRow(
						new Object[] { ((Carritxo) carrinho.get(i)).getId(), ((Carritxo) carrinho.get(i)).getNome(),
								((Carritxo) carrinho.get(i)).getQtd(), ((Carritxo) carrinho.get(i)).getPreco(),
								((Carritxo) carrinho.get(i)).getPreco() * ((Carritxo) carrinho.get(i)).getQtd() });
			}
			System.out.println(carrinho.toString());
		}
	}

}
