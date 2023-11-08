package autenticacao;

import telas.ListaProdutos;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;

import menu.Menu__Prin;

public class Autenticar extends JFrame implements ActionListener{
	private JLabel jl_user,jl_pass;
	private JTextField jt_user;
	private JPasswordField jp_pass;
	private JButton jb_acess,jb_sair;
	private JLabel timeLabel,jp_content;

	
	public Autenticar() {
		jl_user=new JLabel("Username :");
		jt_user=new JTextField(15);
		jl_pass=new JLabel("Password :");
		jp_pass=new JPasswordField(15);
		jb_acess=new JButton("Entrar");
		jb_sair=new JButton("Sair");
		timeLabel = new JLabel();
		
		jb_acess.addActionListener(this);
		jb_sair.addActionListener(this);
		
		this.add(jl_user);
		this.add(jt_user);
		this.add(jl_pass);
		this.add(jp_pass);
		this.add(jb_sair);
		this.add(jb_acess);

		        timeLabel.setHorizontalAlignment(SwingConstants.CENTER);

		        // Create a Timer to update the time label every second
		        Timer timer = new Timer(1000, new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
		                updateTimeLabel(timeLabel);
		            }
		        });
		        timer.start();

		        // Initialize the time label
		        updateTimeLabel(timeLabel);
	JPanel hora=new JPanel();
	timeLabel.setFont(new Font("Verdana", Font.PLAIN, 18));
	hora.add(timeLabel);

		this.setLayout(new GridLayout(4,2,10,10));
		this.setTitle("Autenticação");
		this.setSize(500,250);
		this.setLocation(500,150);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		this.add(new JLabel("TIME:"));
		this.add(hora);
		this.setVisible(true);
	}
    private static void updateTimeLabel(JLabel label) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String currentTime = sdf.format(new Date());
        label.setText(currentTime);
    }
	public static void main(String args[]) {
		new Autenticar();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==jb_acess) {
			if(jt_user.getText().equals("Admin")&&jp_pass.getText().equals("0000")) {
				//-Inserção do nosso Menu ao inves de ListaProdutos
				new Menu__Prin();
				this.setVisible(false);
				
			}else {
			JOptionPane.showMessageDialog(null, "Username or Password Incorrect","ERROR",JOptionPane.WARNING_MESSAGE);
			}
		}
		if(e.getSource()==jb_sair) {
			System.exit(0);
		}
	}
}
