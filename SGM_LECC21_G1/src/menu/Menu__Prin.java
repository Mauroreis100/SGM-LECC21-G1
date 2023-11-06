package menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.JFrame;

import clientes_tela.ListaClientes;
import comprar.CompraSelect;

public class Menu__Prin extends JFrame implements ActionListener {
	private JButton jb1,jb2,jb3,jb4,jb5,jb6,jb7;
	private JPanel jp,jp1,jp3,jp4;
	private JLabel jl,jlemp,jl_img1,jl_img2,jlemp1,jl_img3;
	private JPanel jp5,jp6;
	//IMAGENS
	private ImageIcon img1,img2,img3,img4;
	
	public Menu__Prin () {
		jb1=new JButton("Armazém");
		jb2=new JButton("Forecedor");
		jb3=new JButton("Produto");
		jb4=new JButton("Cliente");
		jb5=new JButton("Sair");
		jb6=new JButton("Compra");
		jb7=new JButton("Relatório");
		
		jl=new JLabel("MENU PRINCIPAL");
		jl.setForeground(Color.GRAY);
		jl.setFont(new Font("Times New Roman",Font.BOLD,40));
		jlemp=new JLabel("We offer you the best and qulaity shooping expirience and help \r\n"
				+ "Money can buy because we are legit the best and only formost");
		jlemp1=new JLabel("We offer you the best and qulaity shooping expirience and help \r\n"
				+ "Money can buy because we are legit the best and only formost");
		jlemp.setFont(new Font("Times New Roman",getFont().PLAIN,12));
		jlemp1.setFont(new Font("Times New Roman",getFont().PLAIN,12));
		
		jp=new JPanel();
		jp1=new JPanel();
		jp3=new JPanel();
		jp4=new JPanel();

		
		this.setTitle("Menu");
		this.setSize(this.getMaximumSize());
		this.setLocation(0,0);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		
		jp.add(jb1);
		jp.add(jb2);
		jp.add(jb3);
		jp.add(jb4);
		jp.add(jb6);
		jp.add(jb5);
		jp.setBackground(Color.lightGray);
		
		jp1.add(jl);
		jp1.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		img1=new ImageIcon("assets/icons/that-concept-store.jpeg");
		jl_img1=new JLabel(img1);
		
		jp4.setLayout(new FlowLayout(FlowLayout.CENTER,20,20));
		jp4.add(jl_img1);

		
		jb1.addActionListener(this);
		jb2.addActionListener(this);
		jb3.addActionListener(this);
		jb4.addActionListener(this);
		jb5.addActionListener(this);
		jb6.addActionListener(this);
		
		jp3.setLayout(new BorderLayout());
		jp3.add(jp,"North");
		jp3.add(jp4,"Center");
		
		this.add(jp1,"North");
		this.add(jp3,"Center");
		
		
		this.setVisible(true);
	}
	public static void main(String args[]) {
		new Menu__Prin();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==jb1) {
			this.setVisible(false);
			//new ListaClientes();
		}
		if(e.getSource()==jb2) {
			this.setVisible(false);
			//new ListaClientes();
		}
		if(e.getSource()==jb3) {
			this.setVisible(false);
			//new ListaClientes();
		}
		if(e.getSource()==jb4) {
			this.setVisible(false);
			new ListaClientes();
		}
		if(e.getSource()==jb6) {
			new CompraSelect();
		}
		if(e.getSource()==jb5) {
			System.exit(0);
		}
		if(e.getSource()==jb7) {
			this.setVisible(false);
			//new ListaClientes();
		}
	}
}