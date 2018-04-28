package vista;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VotacionesFrame  extends JFrame
{
	private InterfazIvote principal;
	JButton [] botones;
	
	
	public VotacionesFrame (InterfazIvote pprincipal){
		principal= pprincipal;
    	setTitle( "i-vote" );
    	setSize( 565, 700 );
        setLocation(750,200);
        setResizable(false);
    	setDefaultCloseOperation( EXIT_ON_CLOSE );
    	setLayout( new BorderLayout( ) );

    	JLabel imagen = new JLabel( );
    	imagen.setIcon(new ImageIcon( new ImageIcon( "./data/banner.jpg" ).getImage( ).getScaledInstance( 565, 170, Image.SCALE_DEFAULT ) ) );
    	add( imagen, BorderLayout.NORTH );
    	
    	int tnumeroVotaciones = 8;
    	int colum = 3;
    	int fil = 2;
    	while(tnumeroVotaciones>(colum*fil)) {
    		fil++;
    		if(tnumeroVotaciones>(colum*fil)) {
    		colum++;
    		}
    	}
    	
    	JPanel cental = new JPanel();
    	cental.setLayout(new GridLayout(fil,colum));
    	
    	botones = new JButton[(colum*fil)];
        for( int i = 0; i < botones.length; i++ )
        {
            botones [ i ] = new JButton();
            botones[i].setActionCommand("votaciones");
            botones[i].addActionListener( principal);
            botones[i].setEnabled(false);
            if(tnumeroVotaciones>i) {
            	botones[ i ].setIcon( new ImageIcon( new ImageIcon( "./data/votaciones.png" ).getImage( ).getScaledInstance( 133, 133, Image.SCALE_DEFAULT ) ) );
            	botones[i].setEnabled(true);
            }
            cental.add(botones[i]);

        }
        add(cental, BorderLayout.CENTER);
    	
    	
        JLabel abajo = new JLabel();
        abajo.setIcon(new ImageIcon( new ImageIcon( "./data/personas.jpg" ).getImage( ).getScaledInstance( 565, 170, Image.SCALE_DEFAULT ) ));
        add(abajo , BorderLayout.SOUTH);
		
		
		
	}
}
