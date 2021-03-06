package vista;

import java.awt.BorderLayout;
import java.awt.Image;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VotoFrame  extends JFrame{
	
	public PanelVoto panelVoto;
	
	public PanelCandidatos panelCandidatos;
	
	private InterfazIvote principal;
	
	
	
	public InterfazIvote getPrincipal() {
		return principal;
	}


	public void setPrincipal(InterfazIvote principal) {
		this.principal = principal;
	}


	public VotoFrame (InterfazIvote pprincipal) throws IOException{
		
		principal= pprincipal;
    	setTitle( "i-vote" );
        setLocation(750,200);
        setResizable(false);
    	setSize( 565, 700 );
    	setDefaultCloseOperation( EXIT_ON_CLOSE );
    	setLayout( new BorderLayout( ) );

    	JLabel imagen = new JLabel( );
    	imagen.setIcon(new ImageIcon( new ImageIcon( "./data/banner.jpg" ).getImage( ).getScaledInstance( 565, 170, Image.SCALE_DEFAULT ) ) );
    	add( imagen, BorderLayout.NORTH );
    	JPanel panelCentro = new JPanel( );
    	panelCentro.setLayout( new BorderLayout( ) );
    	add( panelCentro, BorderLayout.CENTER );

    	panelCandidatos = new PanelCandidatos( this);
    	panelCentro.add( panelCandidatos, BorderLayout.CENTER );

    	panelVoto = new PanelVoto( this , principal );
    	add( panelVoto, BorderLayout.SOUTH );	
	}

	
	public PanelCandidatos getPanelCandidatos() {
		return panelCandidatos;
	}
}
