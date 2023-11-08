package armazemTela;


import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.Vector;
import javax.swing.*;
import armazem.*;
import menu.*;
import clientes.Cliente;
import produto.*;
import excepcoes.CampoVazioException;

public class ListaArmazem extends JFrame implements ActionListener {
	ArmazemOperacoes crudArmazem = new ArmazemOperacoes();
	Vector temp = crudArmazem.recuperarBD();
	Armazem arm = new Armazem();
	private JLabel jl_nome;
	private JLabel jl_id;
	
	private JLabel jl_quantidade;

	private JTextField jt_nome;
	private JTextField jt_id;

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
	private String[] coluna = { "Código", "Nome" , "Quantidade" };

	public ListaArmazem() {

		ImageIcon icon = new ImageIcon("assets/icons/delete_garbage.png");
		ImageIcon icon2 = new ImageIcon("assets/icons/save_disk.png");
		ImageIcon icon3 = new ImageIcon("assets/icons/edit.png");
		ImageIcon icon4 = new ImageIcon("assets/icons/filter_funil.png");
		ImageIcon icon5 = new ImageIcon("assets/icons/Back.png");
		
		int larguraDesejada = 10;
		int alturaDesejada = 10;

		Image imagemRedimensionada = icon.getImage().getScaledInstance(larguraDesejada, alturaDesejada, Image.SCALE_SMOOTH);
		Image imagemRedimensionada2 = icon2.getImage().getScaledInstance(larguraDesejada, alturaDesejada, Image.SCALE_SMOOTH);
		Image imagemRedimensionada3 = icon3.getImage().getScaledInstance(larguraDesejada, alturaDesejada, Image.SCALE_SMOOTH);
		Image imagemRedimensionada4 = icon4.getImage().getScaledInstance(larguraDesejada, alturaDesejada, Image.SCALE_SMOOTH);
		Image imagemRedimensionada5 = icon5.getImage().getScaledInstance(larguraDesejada, alturaDesejada, Image.SCALE_SMOOTH);
		
		ImageIcon novoIcon = new ImageIcon(imagemRedimensionada);
		ImageIcon novoIcon2 = new ImageIcon(imagemRedimensionada2);
		ImageIcon novoIcon3 = new ImageIcon(imagemRedimensionada3);
		ImageIcon novoIcon4 = new ImageIcon(imagemRedimensionada4);
		ImageIcon novoIcon5 = new ImageIcon(imagemRedimensionada5);
		
		jl_nome = new JLabel("Nome :");
		jt_nome = new JTextField(15);
		jl_id = new JLabel("codigo :");
		jt_id = new JTextField(5);
		jt_id.setEditable(false);

		//		jl_cell = new JLabel("Telefone :");
		//		jt_cell = new JTextField(15);
		jl_quantidade = new JLabel("Quantidade :");
		jt_quantidade = new JTextField(15);

		bt_Criar = new JButton(novoIcon2);
		bt_Criar.setText("Registra");
		bt_Eliminar = new JButton(novoIcon);
		bt_Eliminar.setText("Remover");
		bt_Editar = new JButton(novoIcon3);
		bt_Editar.setText("Edita");
		bt_filtrar = new JButton(novoIcon4);
		bt_filtrar.setText("Filtrar");
		bt_Voltar = new JButton(novoIcon5);
		bt_Voltar.setText("Voltar");

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
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// Quando o utilizador armazemCcar no x. Mata todos os

		jp_top = new JPanel();
		jp_top.add(jl_id);
		jp_top.add(jt_id);
		jp_top.add(jl_nome);
		jp_top.add(jt_nome);

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
		OperacoesProduto crudProduto = new OperacoesProduto();
		Vector recuperarProd = crudProduto.recuperarBD();
		
		String[][] dados = new String[temp.size()][6];
		int qtd= 0;
		for (int i = 0; i < temp.size(); i++) {
			qtd = 0;
			dados[i][0] = (((Armazem) temp.get(i)).getId()) + "";
			dados[i][1] = (((Armazem) temp.get(i)).getNome()) + "";
			
			for (int j = 0; j <recuperarProd.size(); j++) {
				
				if(((Armazem) temp.get(i)).getId() == ((Produto)recuperarProd.get(j)).getArmazen_nr()) {
					qtd += ((Produto)recuperarProd.get(j)).getQtd() ;
				}
			}
			dados[i][2] = qtd + "";
			System.out.println(((Armazem) temp.get(i)).toString());

		}
		return dados;// O CONSTRUTOR RETORNA A LISTA MULTIDIMENSIONAL

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if ((e.getSource() == bt_Criar)) {
			if (!(jt_nome.getText().equals("") && jt_cell.getText().equals(""))) {
				// SE O armazem FOR IGUAL A UM JA EXISTENTE
				if (crudArmazem.existe(jt_nome.getText(), temp)) {
					JOptionPane.showMessageDialog(null, "O armazém com nome " + jt_nome.getText() + " já existe", "ATENÇÃO",
							JOptionPane.WARNING_MESSAGE); // OK
				}

				else {
					// Inserção do ID de forma dinamico
					
					jt_id.setText((temp.size() ) + "");
					arm = new Armazem(Integer.parseInt(jt_id.getText()), jt_nome.getText()); // Preenchendo
					jt_id.setText((temp.size() + 1) + "");
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
			Armazem armazemC = crudArmazem.produtoStock(codigo, temp);
			//PROCURA /VERIFICA SE O CODIGO EXISTE
			if (codigo <= temp.size()) {
				if (armazemC != null) {
					new EditarArmazem(armazemC);
					this.setVisible(false);
				}
			} else {
				JOptionPane.showMessageDialog(null, "Armazem não foi encontrado", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}

		if (e.getSource() == bt_Eliminar) {
			int codigo = Integer.parseInt(JOptionPane.showInputDialog("Insira o id do Armazem que pretende eliminar"));
			// VERIFICAÇÃO DE EXISTÊNCIA
			Armazem armazemC = crudArmazem.produtoStock(codigo, temp);

			int a=crudArmazem.procurarCodigo(temp,codigo);
			if (a!=-1) {
				if (armazemC != null) {
					int opcao = JOptionPane.showConfirmDialog(null,
							"Confirma que pretende apagar o armazem  " + armazemC.getNome() + " do sistema?", "ELIMINAR",
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
				if(e.getSource()==bt_filtrar) {
//					new FiltrarArmazem(new Vector());
				}
				if(e.getSource()==bt_Voltar) {
					new Menu__Prin();
				}
	}
}
