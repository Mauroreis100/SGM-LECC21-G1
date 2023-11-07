package clientes_tela;

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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import clientes.Cliente;
import clientes.ClienteOperacoes;

public class NomeFiltro extends JFrame implements ActionListener {
	ClienteOperacoes crudCliente = new ClienteOperacoes();
	Vector temp = crudCliente.recuperarClientesBD();// Preenchumento do vector de objectos do ficheiro na lista
	Vector nome = crudCliente.recuperarClientesBD();
	
	Cliente cliente = new Cliente();// temporaria no progra
	private JTable jt_Clientes,jf_Clientes;
	private JPanel jp;
	private JLabel jl_f;
	private JTextField jtf_se;

	private JButton jt_nome_bt;

	private String[] coluna = { "Código", "Nome", "BI", "Telefone", "Saldo" };

	public NomeFiltro() {
		jl_f = new JLabel("Insira o nome que pretende filtar");
		jtf_se = new JTextField(15);
		jt_nome_bt = new JButton("Procura");

		jt_nome_bt.addActionListener(this);
		// -----DEFINIÇÕES DA JANELA*INICIO-------
		this.setTitle("Nome");// O tittulo da janela.
		this.setSize(500, 500);// Width and Height em pixels.[Comprimento, Largura]
		this.setLocation(980, 500);// Onde o programa vai arrancar
		this.setLocationRelativeTo(null);// Onde o programa vai arrancar
		this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);// Quando o utilizador clicar no x. Mata todos os
		this.setResizable(false);
		this.setLayout(new BorderLayout());

		jp = new JPanel();
		jp.add(jl_f);
		jp.add(jtf_se);
		jp.add(jt_nome_bt);
		jp.setLayout(new FlowLayout(FlowLayout.LEFT));

		if (temp != null) {
			jt_Clientes = new JTable(listaClientes(temp), coluna);
			jt_Clientes.setAutoCreateRowSorter(true);
		} else {
			temp = new Vector<>();
			jt_Clientes = new JTable(null);
		}
		jt_Clientes.setEnabled(false);
		this.add(jp, "North");
		JScrollPane sp = new JScrollPane(jt_Clientes);
		this.add(sp, BorderLayout.CENTER);

		this.setVisible(true);
	}

	private String[][] listaClientes(Vector temp) {
		// ESTA IMPLEMENTAÇÃO COLOCA TODOS OS DADOS DO VECTOR NUMA LISTA
		// MULTIDIMENSIONAL PARA A TABELA

		String[][] dados = new String[temp.size()][6];
		for (int i = 0; i < temp.size(); i++) {
			for (int j = 0; j < 5; j++) {
				dados[i][0] = (((Cliente) temp.get(i)).getId()) + "";
				dados[i][1] = (((Cliente) temp.get(i)).getNome()) + "";
				dados[i][2] = (((Cliente) temp.get(i)).getBI()) + "";
				dados[i][3] = (((Cliente) temp.get(i)).getCell()) + "";
				dados[i][4] = (((Cliente) temp.get(i)).getSaldo()) + "";
				System.out.println(((Cliente) temp.get(i)).toString());
			}
		}
		return dados;// O CONSTRUTOR RETORNA A LISTA MULTIDIMENSIONAL

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == jt_nome_bt) {
			this.setVisible(false);
			if (!(jtf_se.getText().equals(""))) {
				String[][] dados = new String[temp.size()][5];
				for (int i = 0; i < temp.size(); i++) {
					if (((Cliente) temp.get(i)).getNome().equalsIgnoreCase(jtf_se.getText())) {
						for (int j = 0; j < 5; j++) {
							dados[i][0] = (((Cliente) temp.get(i)).getId()) + "";
							dados[i][1] = (((Cliente) temp.get(i)).getNome()) + "";
							dados[i][2] = (((Cliente) temp.get(i)).getBI()) + "";
							dados[i][3] = (((Cliente) temp.get(i)).getCell()) + "";
							dados[i][4] = (((Cliente) temp.get(i)).getSaldo()) + "";
							System.out.println(((Cliente) temp.get(i)).toString());
			
						}
					}
				}
				JFrame jf=new JFrame();
				JTable tb=new JTable(dados,coluna);
				
				jf.setTitle("Pesquisa de :  "+jtf_se.getText());// O tittulo da janela.
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
