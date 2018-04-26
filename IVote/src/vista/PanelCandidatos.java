package vista;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class PanelCandidatos extends JPanel implements ActionListener
{ // -----------------------------------------------------------
	  // Constantes
	  // -----------------------------------------------------------
	    
	    /**
	     * Ruta donde se encuentran todas las im�genes.
	     */
	    private final static String RUTA = "./data/imagenes/";
	    // -----------------------------------------------------------
	    // Atributos
	    // -----------------------------------------------------------

	    /**
	     * Ventana principal de la aplicaci�n.
	     */
	    private VotoFrame principal;

	    // -----------------------------------------------------------
	    // Atributos de interfaz
	    // -----------------------------------------------------------

	    /**
	     * Bot�n para candidato.
	     */
	    private JCheckBox[] checks;

	    // -----------------------------------------------------------
	    // Constructores
	    // -----------------------------------------------------------

	    /**
	     * Constructor del panel. <br>
	     * <b>post: </b> Se inicializ� el panel.
	     * @param pPrincipal Ventana principal. pPrincipal != null.
	     * @param pAnimales Lista de palabras a mostrar.
	     */
	    public PanelCandidatos( VotoFrame pPrincipal )
	    {
	        principal = pPrincipal;
	        setLayout( new GridLayout( 2, 3 ) );

	        checks = new JCheckBox[6];
	        for( int i = 0; i < checks.length; i++ )
	        {
	        	JPanel lugar  = new JPanel();
	        	lugar.setLayout(new BorderLayout());
	            checks [ i ] = new JCheckBox();
	            JLabel boton = new JLabel( );
	            boton.setIcon( new ImageIcon( new ImageIcon( RUTA + "candi.png" ).getImage( ).getScaledInstance( 133, 133, Image.SCALE_DEFAULT ) ) );
	            checks[i].setActionCommand("elegir");
	            checks[i].addActionListener(this);
	            lugar.add( boton , BorderLayout.CENTER);
	            lugar.add(checks[i], BorderLayout.SOUTH);
	            add(lugar);
	        }

	    }

	    /**
	     * Manejo de los eventos de los botones.
	     * @param pEvento Evento de click sobre un bot�n. pEvento != null.
	     */
	    public void actionPerformed(ActionEvent pEvento) {
	        String comando = pEvento.getActionCommand( );
	        
	        int n =0;
	        
	        if( comando.equals( "elegir" ) )
	        {
	        	 for( int i = 0; i < checks.length; i++ )
	 	        {
	        		 if(checks [ i ].isSelected()){
	        			 n++;
	        		 }
	 	        }
	        	if(n>1){
	        		 for( int i = 0; i < checks.length; i++ )
	 	 	        {
	        			 checks [ i ].setSelected(false);
	 	 	        }
	        	}
	        }
	    }
	}
