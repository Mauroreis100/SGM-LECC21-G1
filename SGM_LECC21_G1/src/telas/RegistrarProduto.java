package telas;

import javax.swing.*;

import produto.OperacoesProduto;
import produto.Produto;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.Vector;


//
public class RegistrarProduto extends JFrame implements ActionListener {
	OperacoesProduto crudProduto = new OperacoesProduto();
	Produto produto = new Produto();
	private JFrame jf_registrar;

	private JPanel jp_form;

	private JPanel jp_codigo;
	private JPanel jp_nome;
	private JPanel jp_qtdInicial;
	private JPanel jp_preco;

	private JPanel jp_buttons;

	private JLabel jb_titulo;

	private JLabel jb_codigo;
	private JLabel jb_nome;
	private JLabel jb_preco;
	private JLabel jb_qtdInicial;

	private JTextField tf_codigo;
	private JTextField tf_nome;
	private JTextField tf_preco;
	private JTextField tf_qtdInicial;

	private JButton bt_Criar;
	private JButton bt_Cancelar;


	public RegistrarProduto() {
		jf_registrar = new JFrame();
		jb_titulo = new JLabel("REGISTRE O PRODUTO");
		jb_codigo = new JLabel("Código");
		jb_nome = new JLabel("Nome");
		jb_preco = new JLabel("Preco");
		jb_qtdInicial = new JLabel("Quantidade Inicial");

		tf_codigo = new JTextField(20);
		tf_nome = new JTextField(20);
		tf_qtdInicial = new JTextField(20);
		tf_preco = new JTextField(20);


		bt_Criar = new JButton("Criar");
		bt_Cancelar = new JButton("Cancelar");

		jp_form = new JPanel();

		jp_preco = new JPanel();
		jp_codigo = new JPanel();
		jp_qtdInicial = new JPanel();
		jp_nome = new JPanel();

		jp_buttons = new JPanel();
		// -----DEFINIÇÕES DA JANELA*INICIO---------------------------------------
		jf_registrar.setTitle("Template de JForm");// O tittulo da janela.
		jf_registrar.setSize(1280, 720);// Width and Height em pixels.[Comprimento, Largura]
		jf_registrar.setLocation(100, 100);// Onde o programa vai arrancar
		jf_registrar.setLocationRelativeTo(null);// Onde o programa vai arrancar
		jf_registrar.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// Quando o utilizador clicar no x. Mata todos os
																	// frames
		// ------DEFINIÇÕES DA JANELA*FIM----------------------------------------
		
		//-----ACTION LISNETERS*INICIO-------------------------------
		bt_Cancelar.addActionListener(this);
		bt_Criar.addActionListener(this);
		//----ACTION LISTENERS*FIM----------------------------------
		
		jp_form.setLayout(new FlowLayout(FlowLayout.CENTER, 2, 30));
		
	
 
		
		jf_registrar.add(jb_titulo);

		jp_codigo.add(jb_codigo);
		jp_codigo.add(tf_codigo);

		jp_form.add(jp_codigo);

		jp_nome.add(jb_nome);
		jp_nome.add(tf_nome);

		jp_form.add(jp_nome);

		jp_qtdInicial.add(jb_qtdInicial);
		jp_qtdInicial.add(tf_qtdInicial);

		jp_form.add(jp_qtdInicial);

		jp_preco.add(jb_preco);
		jp_preco.add(tf_preco);

		jp_form.add(jp_preco);

		jp_buttons.add(bt_Cancelar);
		jp_buttons.add(bt_Criar);
		jf_registrar.add(jp_form, BorderLayout.CENTER);
		jf_registrar.add(jp_buttons, BorderLayout.SOUTH);
		
			jf_registrar.setVisible(true);
	}
	
//	public static void main(String[] args) {
//		new RegistrarProduto();
//	}
	public void fechaRegistrarProduto() {
		jf_registrar.setVisible(false);
	}
	public void editarProduto() {
		tf_nome.setText("HOLA");
		tf_qtdInicial.setText("200");
		tf_preco.setText("Set");
	}
	
//	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == bt_Cancelar) {
			System.exit(0);
		}
		if (e.getSource() == bt_Criar) {
			Vector temp=new Vector();//Vector temporário para recuperar o ficheiro
				temp = (Vector) crudProduto.recuperarProdutoBD();//Preenchimento do vector temporario que vem do ficheiro
				if(temp!=null) {
					produto = new Produto(11, tf_nome.getText(), Integer.parseInt(tf_qtdInicial.getText()),
							Double.parseDouble(tf_preco.getText()));//ADIÇÃO DO OBJECTO PRODUTO, BASEANDO-SE NOS CAMPOS DO FORMULÁRIO
					temp.add(produto);//ACTUALIZANDO O VECTOR DE OBJECTOS DO PRODUTO
					//VERIFICAR SE CAMPOS ESTÃO VAZIOS
					if(crudProduto.gravarProdutos(temp)) {//CASO TUDO CORRA BEM
						JOptionPane.showMessageDialog(null, "PRODUTO REGISTRADO COM SUCESSO");//MENSAGEM DE SUCESSO
					};
				}//ELSE PARA VERIFICAR SE PRODUTO JÁ EXISTE
				System.out.println((Produto)temp.lastElement());

		}
	}
}
