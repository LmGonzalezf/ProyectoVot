package vista;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;



public class PanelVoto extends JPanel implements ActionListener
	{
	    // -----------------------------------------------------------------
	    // Constantes
	    // -----------------------------------------------------------------

	    /**
	     * Constante para el método de extensión 1.
	     */
	    public final static String COMANDO_1 = "UNO";

	    /**
	     * Constante para el método de extensión 2.
	     */
	    public final static String COMANDO_2 = "DOS";

	    // -----------------------------------------------------------------
	    // Atributos
	    // -----------------------------------------------------------------

	    /**
	     * Ventana principal 
	     */
	    private VotoFrame principal;

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

	    // -----------------------------------------------------------------
	    // Constructores
	    // -----------------------------------------------------------------

	    /**
	     * Constructor del panel para los métodos de extensión.
	     * @param pPrincipal Ventana principal de la aplicación.
	     */
	    public PanelVoto( VotoFrame pPrincipal )
	    {
	        principal = pPrincipal;
	        setLayout( new GridLayout( 1, 2 ) );
	        setBorder( new TitledBorder( "Panel Voto" ) );

	        btnOpc1 = new JButton( "Opción 1" );
	        btnOpc1.setActionCommand( COMANDO_1 );
	        btnOpc1.addActionListener( this );
	        add( btnOpc1 );

	        btnOpc2 = new JButton( "Opción 2" );
	        btnOpc2.setActionCommand( COMANDO_2 );
	        btnOpc2.addActionListener( this );
	        add( btnOpc2 );

	    }

	    // -----------------------------------------------------------------
	    // Métodos
	    // -----------------------------------------------------------------

	    /**
	     * Manejo de los eventos de los botones.
	     * @param pEvento Acción que generó el evento.
	     */
	    public void actionPerformed( ActionEvent pEvento )
	    {
	        String comando = pEvento.getActionCommand( );

	        if( comando.equals( COMANDO_1 ) )
	        {
	           // principal.reqFuncOpcion1( );
	        }
	        else
	        {
	           //principal.reqFuncOpcion1( );
	        }

	    }
	}
