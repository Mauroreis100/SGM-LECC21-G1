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

import clientes.Cliente;
import clientes.ClienteOperacoes;
import menu.Menu__Prin;
import produto.OperacoesProduto;
import produto.Produto;

public class CompraSele extends JFrame implements ActionListener {
	OperacoesProduto crudProduto = new OperacoesProduto();
	Vector temp = crudProduto.recuperarBD();// Preenchumento do vector de objectos do ficheiro na listanto do vector de objectos do ficheiro na lista	
	
	private JTable jt_Clientes;
	private JPanel jp,jp_produtos,jp_produto;
	private JLabel jl_f;
	private JTextField jtf_se;

	private JButton jt_nome_bt;

	private String[] coluna = { "Código", "Nome", "BI", "Telefone", "Saldo" };

	public CompraSele() {
		jl_f = new JLabel("Escolha o ID do cliente para fazer a compra");
		jtf_se = new JTextField(15);
		jt_nome_bt = new JButton("Compra");
		jp=new JPanel();
		jp_produto=new JPanel();
		jp_produtos=new JPanel();
		jt_nome_bt.addActionListener(this);
		// -----DEFINIÇÕES DA JANELA*INICIO-------
		this.setTitle("Escolha");// O tittulo da janela.
		this.setSize(950, 500);// Width and Height em pixels.[Comprimento, Largura]
		this.setLocation(980, 500);// Onde o programa vai arrancar
		this.setLocationRelativeTo(null);// Onde o programa vai arrancar
		this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);// Quando o utilizador clicar no x. Mata todos os
		this.setResizable(true);
		this.setLayout(new GridLayout(1,2));
		jp.setLayout(new GridLayout(2,1,2,2));

//		jp = new JPanel();
		for (int i = 0; i < temp.size(); i++) {
			ImageIcon img_icon=new ImageIcon(((Produto)temp.get(i)).getFoto());
			int larguraDesejada = 100;
			int alturaDesejada = 100;
			Image imagemRedimensionada = img_icon.getImage().getScaledInstance(larguraDesejada, alturaDesejada,
					Image.SCALE_SMOOTH);
			ImageIcon novoIcon = new ImageIcon(imagemRedimensionada);
			JButton bt_produtos = new JButton(((Produto)temp.get(i)).getNome(),novoIcon);
			bt_produtos.setVerticalTextPosition(JButton.BOTTOM);
			bt_produtos.setHorizontalTextPosition(JButton.CENTER);
			bt_produtos.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                // Your action code here
	                JOptionPane.showInputDialog("Quantidade");
	            }
	        });
			jp.add(bt_produtos);

		}

		jp_produtos.setLayout(new FlowLayout(FlowLayout.LEFT));

//		if (temp != null) {
//			jt_Clientes = new JTable(listaClientes(temp), coluna);
//			jt_Clientes.setAutoCreateRowSorter(true);
//		} else {
//			temp = new Vector<>();
//			jt_Clientes = new JTable(null);
//		}
		jp_produtos.add(jp);
//		this.add(jp, BorderLayout.CENTER);
//		jt_Clientes.setEnabled(false);
		JScrollPane sp = new JScrollPane(jp_produtos);
		this.add(sp);

		this.setVisible(true);
	}

//	private String[][] listaClientes(Vector temp) {
//		// ESTA IMPLEMENTAÇÃO COLOCA TODOS OS DADOS DO VECTOR NUMA LISTA
//		// MULTIDIMENSIONAL PARA A TABELA
//
//		String[][] dados = new String[temp.size()][6];
//		for (int i = 0; i < temp.size(); i++) {
//				dados[i][0] = (((Cliente) temp.get(i)).getId()) + "";
//				dados[i][1] = (((Cliente) temp.get(i)).getNome()) + "";
//				dados[i][2] = (((Cliente) temp.get(i)).getBI()) + "";
//				dados[i][3] = (((Cliente) temp.get(i)).getCell()) + "";
//				dados[i][4] = (((Cliente) temp.get(i)).getSaldo()) + "";
//				System.out.println(((Cliente) temp.get(i)).toString());
//		}
//		return dados;// O CONSTRUTOR RETORNA A LISTA MULTIDIMENSIONAL
//
//	}
//
//	@Override
	public static void main(String[] args) {
		new CompraSele();
	}
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == jt_nome_bt) {
//			this.setVisible(false);
//			if (!(jtf_se.getText().equals(""))) {
//				String[][] dados = new String[temp.size()][5];
//				for (int i = 0; i < temp.size(); i++) {
//					if (((Cliente) temp.get(i)).getId()==Integer.parseInt(jtf_se.getText())) {
//							dados[i][0] = (((Cliente) temp.get(i)).getId()) + ""; 
//							dados[i][1] = (((Cliente) temp.get(i)).getNome()) + "";
//							dados[i][2] = (((Cliente) temp.get(i)).getBI()) + "";
//							dados[i][3] = (((Cliente) temp.get(i)).getCell()) + "";
//							dados[i][4] = (((Cliente) temp.get(i)).getSaldo()) + "";
//							System.out.println(((Cliente) temp.get(i)).toString());
//							new Compra(i);
//					}
//				}
//			} else {
//				JOptionPane.showMessageDialog(null, "Pesquisa Vazia!", "",JOptionPane.WARNING_MESSAGE); 
//			}
		}
	}

}
