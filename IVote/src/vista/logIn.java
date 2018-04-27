package vista;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

public class logIn extends JFrame {
	
	
	private InterfazIvote principal;
	
	private JTextField usuario;
	
	private JPasswordField clave ;
	
	

	public logIn ( InterfazIvote pprincipal){
		
		principal = pprincipal;
		setTitle( "i-vote" );
        setSize( 565, 700 );
        setLocation(750,200);
        setResizable(false);
        setDefaultCloseOperation( EXIT_ON_CLOSE );
        
        setLayout( new BorderLayout( ) );
        
        JLabel imagen = new JLabel( );
        imagen.setIcon(new ImageIcon( new ImageIcon( "./data/banner.jpg" ).getImage( ).getScaledInstance( 565, 170, Image.SCALE_DEFAULT ) ) );
        add( imagen, BorderLayout.NORTH );
        
        
        JPanel login = new JPanel();
        login.setLayout(new GridLayout(4,1));
        JLabel titulo = new JLabel("BIENVENIDO",SwingConstants.CENTER);
        usuario = new JTextField();
        usuario.setBorder( new TitledBorder( "Usuario"));
        clave = new JPasswordField();
        clave.setBorder( new TitledBorder( "Password"));
        //login.add(new JLabel());
        login.add(titulo);
        login.add(usuario);
        login.add(clave);
        
        JButton botonlogin = new JButton();
        botonlogin.setActionCommand("login");
        botonlogin.addActionListener(principal);
        
        botonlogin.setIcon(new ImageIcon( new ImageIcon( "./data/loginbutton.png" ).getImage( ).getScaledInstance( 565, 170, Image.SCALE_DEFAULT ) ));
        login.add(botonlogin);
        add(login, BorderLayout.CENTER);
        add(new JLabel(), BorderLayout.EAST);
        JLabel abajo = new JLabel();
        abajo.setIcon(new ImageIcon( new ImageIcon( "./data/personas.jpg" ).getImage( ).getScaledInstance( 565, 170, Image.SCALE_DEFAULT ) ));
        add(abajo , BorderLayout.SOUTH);
        
	}



	public JTextField getUsuario() {
		return usuario;
	}



	public void setUsuario(JTextField usuario) {
		this.usuario = usuario;
	}



	public JPasswordField getClave() {
		return clave;
	}



	public void setClave(JPasswordField clave) {
		this.clave = clave;
	}
	
	

}
