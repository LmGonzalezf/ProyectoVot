package vista;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class EleccionesFrame extends JFrame 
{
	private InterfazIvote principal;
	JButton [] botones;
	
	
	public EleccionesFrame (InterfazIvote pprincipal){
		principal= pprincipal;
    	setTitle( "i-vote" );
    	setSize( 565, 700 );
    	setDefaultCloseOperation( EXIT_ON_CLOSE );
    	setLayout( new BorderLayout( ) );

    	JLabel imagen = new JLabel( );
    	imagen.setIcon(new ImageIcon( new ImageIcon( "./data/banner.jpg" ).getImage( ).getScaledInstance( 565, 170, Image.SCALE_DEFAULT ) ) );
    	add( imagen, BorderLayout.NORTH );
    	
    	JPanel cental = new JPanel();
    	cental.setLayout(new GridLayout(2,3));
    	
    	botones = new JButton[6];
        for( int i = 0; i < botones.length; i++ )
        {
            botones [ i ] = new JButton();
            botones[ i ].setIcon( new ImageIcon( new ImageIcon( "./data/elecciones.png" ).getImage( ).getScaledInstance( 133, 133, Image.SCALE_DEFAULT ) ) );
            botones[i].setActionCommand("elecciones");
            botones[i].addActionListener( principal);
            cental.add(botones[i]);
        }
        add(cental, BorderLayout.CENTER);
    	
    	
        JLabel abajo = new JLabel();
        abajo.setIcon(new ImageIcon( new ImageIcon( "./data/personas.jpg" ).getImage( ).getScaledInstance( 565, 170, Image.SCALE_DEFAULT ) ));
        add(abajo , BorderLayout.SOUTH);
		
		
		
	}
	

}
