package telas;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.*;

import clientes.Cliente;
import clientes.ClienteOperacoes;
import produto.OperacoesProduto;
import produto.Produto;

public class FiltrarProdutos extends JFrame implements ActionListener {
	OperacoesProduto crudProduto= new OperacoesProduto();
	Vector temp = crudProduto.recuperarBD();// Preenchumento do vector de objectos do ficheiro na lista
	
	
	Produto prod = new Produto();// temporaria no progra
	private JTable jt_Clientes, jf_Clientes;
	private JPanel jp;
	private JLabel jl_filtrar,lb_nome,lb_id;
	private JTextField tf_codigo,tf_nome;

	private JButton jt_nome_bt;

	private String[] coluna = { "Código", "Nome", "Quantidade", "Preço", "Vendidos" };

	public FiltrarProdutos() {
//		jl_filtrar = new JLabel(":");
		lb_nome=new JLabel("Nome:");
		tf_nome=new JTextField(8);
		lb_id=new JLabel("ID:");
		tf_codigo = new JTextField(15);
		jt_nome_bt = new JButton("Procura");

		jt_nome_bt.addActionListener(this);
		// -----DEFINIÇÕES DA JANELA*INICIO-------
		this.setTitle("Filtrar por");// O tittulo da janela.
		this.setSize(500, 500);// Width and Height em pixels.[Comprimento, Largura]
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
		jp.setLayout(new FlowLayout(FlowLayout.LEFT));

	
		this.add(jp, "North");
		Tabela(temp);
		JScrollPane sp = new JScrollPane(jt_Clientes);
		this.add(sp, BorderLayout.CENTER);

		this.setVisible(true);
	}

	private void Tabela(Vector temp) {
		String[][] dados = new String[temp.size()][6];
		for (int i = 0; i < temp.size(); i++) {
		
				dados[i][0] = (((Produto) temp.get(i)).getId()) + "";
				dados[i][1] = (((Produto) temp.get(i)).getNome()) + "";
				dados[i][2] = (((Produto) temp.get(i)).getQtd()) + "";
				dados[i][3] = (((Produto) temp.get(i)).getPreco()) + "";
				dados[i][4] = (((Produto) temp.get(i)).getQtd()) + "";
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
			this.setVisible(false);
			if (!(tf_codigo.getText().equals(""))) {
				String[][] dados = new String[temp.size()][5];
				for (int i = 0; i < temp.size(); i++) {
					if (((Produto) temp.get(i)).getNome().equalsIgnoreCase(tf_codigo.getText())) {
						for (int j = 0; j < 5; j++) {
							dados[i][0] = (((Produto) temp.get(i)).getId()) + "";
							dados[i][1] = (((Produto) temp.get(i)).getNome()) + "";
							dados[i][2] = (((Produto) temp.get(i)).getQtd()) + "";
							dados[i][3] = (((Produto) temp.get(i)).getPreco()) + "";
							dados[i][4] = (((Produto) temp.get(i)).getQtd()) + "";
						}
					}
				}
				JFrame jf=new JFrame();
				JTable tb=new JTable(dados,coluna);
				
				jf.setTitle("Pesquisa de :  "+tf_codigo.getText());// O tittulo da janela.
				jf.setSize(500, 500);// Width and Height em pixels.[Comprimento, Largura]
				jf.setLocation(980, 500);// Onde o programa vai arrancar
				jf.setLocationRelativeTo(null);// Onde o programa vai arrancar
				jf.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);// Quando o utilizador clicar no x. Mata todos os
				jf.setResizable(false);
				jf.setLayout(new BorderLayout());
				JScrollPane sp = new JScrollPane(tb);
				jf.add(sp, BorderLayout.CENTER);
				
		
				jf.setVisible(true);	
			} else {
				JOptionPane.showMessageDialog(null, "Pesquisa Vazia!", "",JOptionPane.WARNING_MESSAGE); 
			}
		}
	}

}