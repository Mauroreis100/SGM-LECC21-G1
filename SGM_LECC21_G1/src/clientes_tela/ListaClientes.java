package clientes_tela;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.*;

import clientes.Cliente;
import clientes.ClienteOperacoes;
import excepcoes.CampoVazioException;

public class ListaClientes extends JFrame implements ActionListener {
	ClienteOperacoes crudCliente = new ClienteOperacoes();
	Vector temp = crudCliente.recuperarClientesBD();// Preenchumento do vector de objectos do ficheiro na lista

	Cliente cliente = new Cliente();// temporaria no progra
	private JLabel jl_nome;
	private JLabel jl_id;
	private JLabel jl_BI;
	private JLabel jl_saldo;
	private JLabel jl_cell;

	private JTextField jt_nome;
	private JTextField jt_id;
	private JTextField jt_BI;
	private JTextField jt_saldo;
	private JTextField jt_cell;

	private JButton bt_Criar;
	private JButton bt_Eliminar;
	private JButton bt_Editar;
	private JButton bt_filtrar;
	private JButton bt_Voltar;

	private JPanel jp_top;
	private JPanel jp_bottom;

	private JTable jt_Clientes;

	// Column Names
	private String[] coluna = { "Código", "Nome", "BI", "Telefone", "Saldo" };

	public ListaClientes() {
		jl_nome = new JLabel("Nome :");
		jt_nome = new JTextField(15);
		jl_id = new JLabel("codigo :");
		jt_id = new JTextField(5);
		jt_id.setEditable(false);
		jl_BI = new JLabel("BI :");
		jt_BI = new JTextField(15);
		jl_cell = new JLabel("Telefone :");
		jt_cell = new JTextField(15);
		jl_saldo = new JLabel("Saldo :");
		jt_saldo = new JTextField(15);

		bt_Criar = new JButton("Registra");
		bt_Eliminar = new JButton("Remover");
		bt_Editar = new JButton("Edita");
		bt_filtrar = new JButton("Filtrar");
		bt_Voltar = new JButton("Voltar");

		bt_Criar.addActionListener(this);
		bt_Eliminar.addActionListener(this);
		bt_Editar.addActionListener(this);

		this.setLayout(new BorderLayout());
		// ------------POPULAR ARRAY COM O VECTOR DE OBJECTOS----------
		if (temp != null) {
			
			jt_Clientes = new JTable(listaClientes(temp), coluna);
			jt_Clientes.setAutoCreateRowSorter(true);

		} else {
			temp=new Vector<>();
			jt_Clientes = new JTable(null);
		}
		JScrollPane sp = new JScrollPane(jt_Clientes);
		this.add(sp, BorderLayout.CENTER);

		// -----DEFINIÇÕES DA JANELA*INICIO-------
		this.setTitle("Lista de Clientes");// O tittulo da janela.
		this.setSize(1280, 720);// Width and Height em pixels.[Comprimento, Largura]
		this.setLocation(100, 100);// Onde o programa vai arrancar
		this.setLocationRelativeTo(null);// Onde o programa vai arrancar
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// Quando o utilizador clicar no x. Mata todos os

		jp_top = new JPanel();
		jp_top.add(jl_id);
		jp_top.add(jt_id);
		jp_top.add(jl_nome);
		jp_top.add(jt_nome);
		jp_top.add(jl_BI);
		jp_top.add(jt_BI);
		jp_top.add(jl_cell);
		jp_top.add(jt_cell);
		jp_top.add(jl_saldo);
		jp_top.add(jt_saldo);
		jp_top.setLayout(new FlowLayout(FlowLayout.LEFT));

		jp_bottom = new JPanel();
		jp_bottom.setLayout(new FlowLayout(FlowLayout.CENTER));

		jp_bottom.add(bt_Criar);
		jp_bottom.add(bt_Eliminar);
		jp_bottom.add(bt_Editar);

		this.add(jp_top, "North");
		this.add(jp_bottom, "South");
		// frames
		this.setVisible(true);
	}

	public static void main(String args[]) {
		new ListaClientes();
	}

	public void FecharListarCliente() {
		this.setVisible(false);
	}

	private String[][] listaClientes(Vector temp) {
//ESTA IMPLEMENTAÇÃO COLOCA TODOS OS DADOS DO VECTOR NUMA LISTA MULTIDIMENSIONAL PARA A TABELA

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
		if ((e.getSource() == bt_Criar)) {
			if (!(jt_nome.getText().equals("") && jt_BI.getText().equals("") && jt_cell.getText().equals(""))) {
				
				if (crudCliente.existe(jt_nome.getText(), temp)) {
					JOptionPane.showMessageDialog(null, "O Cliente com BI " + jt_BI.getText() + " já existe", "ATENÇÃO",
							JOptionPane.WARNING_MESSAGE); // OK
				} else {
					//FIX HERE. VIMOS SOLUÇÃO JUNTOS!!!
					cliente = new Cliente(Integer.parseInt(jt_id.getText()), jt_nome.getText(), jt_BI.getText(),
							jt_cell.getText(), Double.parseDouble(jt_saldo.getText())); // Preenchendo
					// objecto
					// com o
					// formulário
					temp.add(cliente);// Actualização do vector temporário com o novo objecto
					if (crudCliente.gravarClientes(temp)) {//
						JOptionPane.showMessageDialog(null, "Cliente Registrado com sucesso", "SUCESSO",
								JOptionPane.PLAIN_MESSAGE);// MENSAGEM DE SUCESSO
						new ListaClientes();// Fecha
						FecharListarCliente();// Rerenderizar a página com produtos novoss
					} // ERRO NA GRAVAÇÃO
				}
			} else {
				JOptionPane.showMessageDialog(null, "EXISTÊNCIA DE CAMPOS VAZIOS! PREENCHA TODOS OS CAMPOS", "",
						JOptionPane.WARNING_MESSAGE); // OK
				throw new CampoVazioException("CAMPOS VAZIOS");
			}
		}
		if (e.getSource() == bt_Editar) {
			int codigo = Integer.parseInt(JOptionPane.showInputDialog("Insira o id do Cliente que pretende editar"));
			Cliente cli = crudCliente.produtoStock(codigo, temp);
			if (cli != null) {
				new EditarClientes(cli);
				this.setVisible(false);
			}
		}

		if (e.getSource() == bt_Eliminar) {
			int codigo = Integer.parseInt(JOptionPane.showInputDialog("Insira o id do Cliente que pretende eliminar"));
			// VERIFICAÇÃO DE EXISTÊNCIA
			Cliente cli = crudCliente.produtoStock(codigo, temp);
			if (cli != null) {
				int opcao = JOptionPane.showConfirmDialog(null,
						"Confirma que pretende apagar o cliente" + cli.getNome() + " do sistema?", "ELIMINAR",
						JOptionPane.YES_NO_OPTION);// YES apaga mesmo e NO não faz nada,
				if (opcao == 0) {
					crudCliente.gravarClientes(crudCliente.removerCliente(temp, codigo));// GRAVA UMA REMOÇÃO FEITA
					JOptionPane.showMessageDialog(null, "Eliminado com sucesso!", "ELIMINADO",
							JOptionPane.ERROR_MESSAGE); // OK
					FecharListarCliente();
					new ListaClientes();
				}
			} else {
				JOptionPane.showMessageDialog(null, "Cliente não foi encontrado!", "ERRO", JOptionPane.WARNING_MESSAGE); // OK
			}

		}

	}

	public void mouseEntered(MouseEvent e) {
		if (e.getSource() == jt_nome || e.getSource() == jt_BI || e.getSource() == jt_cell) {
			jt_id.setText((temp.size() + 1) + "");
		}

	}

}
