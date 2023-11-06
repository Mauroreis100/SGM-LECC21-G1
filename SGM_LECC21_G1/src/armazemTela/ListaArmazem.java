package armazemTela;



import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.*;
import produto.Produto;
import armazem.Armazem;
import armazem.ArmazemOperacoes;
import armazem.ArmazemOperacoes;
import excepcoes.CampoVazioException;

public class ListaArmazem extends JFrame implements ActionListener {
	ArmazemOperacoes crudArmazem = new ArmazemOperacoes();
	Vector temp = crudArmazem.recuperarBD();// Preenchumento do vector de objectos do ficheiro na lista

	Armazem arm = new Armazem();// temporaria no progra
	private JLabel jl_nome;
	private JLabel jl_id;
	private JLabel jl_tipoProduto;
	private JLabel jl_quantidade;
	private JLabel jl_cell;

	private JTextField jt_nome;
	private JTextField jt_id;
	private JTextField jt_tipoProduto;
	private JTextField jt_quantidade;
	private JTextField jt_cell;

	private JButton bt_Criar;
	private JButton bt_Eliminar;
	private JButton bt_Editar;
	private JButton bt_filtrar;
	private JButton bt_Voltar;

	private JPanel jp_top;
	private JPanel jp_bottom;

	private JTable jt_Armazems;

	// Column Names
	private String[] coluna = { "Código", "Nome", "Tipo de Produto", "Telefone", "Quantidade" };

	public ListaArmazem() {
		jl_nome = new JLabel("Nome :");
		jt_nome = new JTextField(15);
		jl_id = new JLabel("codigo :");
		jt_id = new JTextField(5);
		jt_id.setEditable(false);
		jl_tipoProduto = new JLabel("Tipo de Produto :");
		jt_tipoProduto = new JTextField(15);
		jl_cell = new JLabel("Telefone :");
		jt_cell = new JTextField(15);
		jl_quantidade = new JLabel("Quantidade :");
		jt_quantidade = new JTextField(15);

		bt_Criar = new JButton("Registra");
		bt_Eliminar = new JButton("Remover");
		bt_Editar = new JButton("Edita");
		bt_filtrar = new JButton("Filtrar");
		bt_Voltar = new JButton("Voltar");

		bt_Criar.addActionListener(this);
		bt_Eliminar.addActionListener(this);
		bt_Editar.addActionListener(this);
		bt_filtrar.addActionListener(this);
		bt_Voltar.addActionListener(this);

		this.setLayout(new BorderLayout());
		// ------------POPULAR ARRAY COM O VECTOR DE OBJECTOS----------
		if (temp != null) {

			jt_Armazems = new JTable(listaArmazems(temp), coluna);
			jt_Armazems.setAutoCreateRowSorter(true);

		} else {
			temp = new Vector<>();
			jt_Armazems = new JTable(null);
		}
		JScrollPane sp = new JScrollPane(jt_Armazems);
		this.add(sp, BorderLayout.CENTER);

		// -----DEFINIÇÕES DA JANELA*INICIO-------
		this.setTitle("Lista de Armazens");// O tittulo da janela.
		this.setSize(1280, 720);// Width and Height em pixels.[Comprimento, Largura]
		this.setLocation(100, 100);// Onde o programa vai arrancar
		this.setLocationRelativeTo(null);// Onde o programa vai arrancar
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// Quando o utilizador clicar no x. Mata todos os

		jp_top = new JPanel();
		jp_top.add(jl_id);
		jp_top.add(jt_id);
		jp_top.add(jl_nome);
		jp_top.add(jt_nome);
		jp_top.add(jl_tipoProduto);
		jp_top.add(jt_tipoProduto);
		jp_top.add(jl_cell);
		jp_top.add(jt_cell);
		jp_top.add(jl_quantidade);
		jp_top.add(jt_quantidade);
		jp_top.setLayout(new FlowLayout(FlowLayout.CENTER));

		jp_bottom = new JPanel();
		jp_bottom.setLayout(new FlowLayout(FlowLayout.CENTER));

		jp_bottom.add(bt_Criar);
		jp_bottom.add(bt_Eliminar);
		jp_bottom.add(bt_Editar);
		jp_bottom.add(bt_filtrar);
		jp_bottom.add(bt_Voltar);

		this.add(jp_top, "North");
		this.add(jp_bottom, "South");
		// frames
		this.setVisible(true);
	}

	public static void main(String args[]) {
		new ListaArmazem();
	}

	public void FecharListarArmazem() {
		this.setVisible(false);
	}

	private String[][] listaArmazems(Vector temp) {
//ESTA IMPLEMENTAÇÃO COLOCA TODOS OS DADOS DO VECTOR NUMA LISTA MULTIDIMENSIONAL PARA A TABELA

		String[][] dados = new String[temp.size()][6];
		for (int i = 0; i < temp.size(); i++) {
				dados[i][0] = (((Armazem) temp.get(i)).getId()) + "";
				dados[i][1] = (((Armazem) temp.get(i)).getNome()) + "";
				dados[i][2] = (((Armazem) temp.get(i)).getTipoProduto()) + "";
				dados[i][3] = (((Armazem) temp.get(i)).getQuantidade()) + "";
				System.out.println(((Armazem) temp.get(i)).toString());
			
		}
		return dados;// O CONSTRUTOR RETORNA A LISTA MULTIDIMENSIONAL

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if ((e.getSource() == bt_Criar)) {
			if (!(jt_tipoProduto.getText().equals("") && jt_cell.getText().equals(""))) {
				// SE O Tipo de Produto FOR IGUAL A UM CLIENTE JA EXISTENTE
				if (crudArmazem.existe(jt_tipoProduto.getText(), temp)) {
					JOptionPane.showMessageDialog(null, "O Armazem com Tipo de Produto " + jt_tipoProduto.getText() + " já existe", "ATENÇÃO",
							JOptionPane.WARNING_MESSAGE); // OK
				}
				// SE O NUMERO FOR IGUAL A UM JA EXISTENTE
				else if (crudArmazem.existe(jt_cell.getText(), temp)) {
					JOptionPane.showMessageDialog(null, "O Armazem com o numero " + jt_cell.getText() + " já existe",
							"ATENÇÃO", JOptionPane.WARNING_MESSAGE); // OK) {

				} else {
					// Inserção do ID de forma dinamico
					jt_id.setText((temp.size() + 1) + "");
					// FIX HERE. VIMOS SOLUÇÃO JUNTOS!!!
					arm = new Armazem(); // Preenchendo
					// objecto
					// com o
					// formulário
					temp.add(arm);// Actualização do vector temporário com o novo objecto
					if (crudArmazem.gravarObjecto(temp)) {//
						JOptionPane.showMessageDialog(null, "Armazem Registrado com sucesso", "SUCESSO",
								JOptionPane.PLAIN_MESSAGE);// MENSAGEM DE SUCESSO
						new ListaArmazem();// Fecha
						FecharListarArmazem();// Rerenderizar a página com produtos novoss
					} // ERRO NA GRAVAÇÃO
				}
			} else {
				JOptionPane.showMessageDialog(null, "EXISTÊNCIA DE CAMPOS VAZIOS! PREENCHA TODOS OS CAMPOS", "",
						JOptionPane.WARNING_MESSAGE); // OK
				throw new CampoVazioException("CAMPOS VAZIOS");
			}
		}
		
		if (e.getSource() == bt_Editar) {
			int codigo = Integer.parseInt(JOptionPane.showInputDialog("Insira o id do Armazem que pretende editar"));
			Armazem cli = crudArmazem.produtoStock(codigo, temp);
			//PROCURA /VERIFICA SE O CODIGO EXISTE
			if (codigo <= temp.size()) {
				if (cli != null) {
					new EditarArmazem(arm);
					this.setVisible(false);
				}
			} else {
				JOptionPane.showMessageDialog(null, "Armazem não foi encontrado", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}

		if (e.getSource() == bt_Eliminar) {
			int codigo = Integer.parseInt(JOptionPane.showInputDialog("Insira o id do Armazem que pretende eliminar"));
			// VERIFICAÇÃO DE EXISTÊNCIA
			Armazem cli = crudArmazem.produtoStock(codigo, temp);
			
			int a=crudArmazem.procurarCodigo(temp,codigo);
			if (a!=-1) {
				if (cli != null) {
					int opcao = JOptionPane.showConfirmDialog(null,
							"Confirma que pretende apagar o arm  " + cli.getNome() + " do sistema?", "ELIMINAR",
							JOptionPane.YES_NO_OPTION);// YES apaga mesmo e NO não faz nada,
					if (opcao == 0) {
						crudArmazem.gravarObjecto(crudArmazem.removerObjecto(temp, codigo));// GRAVA UMA REMOÇÃO FEITA
						JOptionPane.showMessageDialog(null, "Eliminado com sucesso!", "ELIMINADO",
								JOptionPane.ERROR_MESSAGE); // OK
						FecharListarArmazem();
						new ListaArmazem();
					}
				}
			} else {
				JOptionPane.showMessageDialog(null, "Armazem não foi encontrado!", "ERRO", JOptionPane.WARNING_MESSAGE); // OK
			}

		}
//		if(e.getSource()==bt_filtrar) {
//			new FiltrarArmazems();
//		}
//		if(e.getSource()==bt_Voltar) {
//			//new Menu();
//		}
	}
}
