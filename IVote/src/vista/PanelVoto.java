package vista;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;



public class PanelVoto extends JPanel
	{
	    // -----------------------------------------------------------------
	    // Constantes
	    // -----------------------------------------------------------------

	    /**
	     * Constante para el método de extensión 1.
	     */
	    public final static String COMANDO_SALIR = "SALIR";

	    /**
	     * Constante para el método de extensión 2.
	     */
	    public final static String COMANDO_BACK = "BACK";

	    // -----------------------------------------------------------------
	    // Atributos
	    // -----------------------------------------------------------------

	    /**
	     * Ventana principal 
	     */
	    private InterfazIvote principal;
	    
	    private VotoFrame frameVoto;

	    // -----------------------------------------------------------------
	    // Atributos de Interfaz
	    // -----------------------------------------------------------------

	    /**
	     * Botón opción 1.
	     */
	    private JButton btnOpc1;

	    /**
	     * Botón opción 2.
	     */
	    private JButton btnOpc2;
	    
	    private JButton btnOpc3;


	    // -----------------------------------------------------------------
	    // Constructores
	    // -----------------------------------------------------------------

	    /**
	     * Constructor del panel para los métodos de extensión.
	     * @param pPrincipal Ventana principal de la aplicación.
	     */
	    public PanelVoto( VotoFrame pvotoFrame , InterfazIvote pprincipal )
	    {
	        frameVoto = pvotoFrame;
	        principal= pprincipal;
	        setLayout( new GridLayout( 1, 3 ) );
	        setBorder( new TitledBorder( "Opciones" ) );

	        btnOpc1 = new JButton( "Salir" );
	        btnOpc1.setActionCommand( COMANDO_SALIR );
	        btnOpc1.addActionListener( principal );
	        add( btnOpc1 );

	        btnOpc2 = new JButton( "Anterior" );
	        btnOpc2.setActionCommand( COMANDO_BACK );
	        btnOpc2.addActionListener( principal );
	        add( btnOpc2 );
	        
	        btnOpc3 = new JButton( "Votar" );
	        btnOpc3.setActionCommand( "Votar" );
	        btnOpc3.addActionListener( principal );
	        add( btnOpc3 );

	    }

	    // -----------------------------------------------------------------
	    // Métodos
	    // -----------------------------------------------------------------

	}
