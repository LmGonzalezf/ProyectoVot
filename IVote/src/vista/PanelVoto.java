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
	     * Constante para el m�todo de extensi�n 1.
	     */
	    public final static String COMANDO_1 = "UNO";

	    /**
	     * Constante para el m�todo de extensi�n 2.
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
	     * Bot�n opci�n 1.
	     */
	    private JButton btnOpc1;

	    /**
	     * Bot�n opci�n 2.
	     */
	    private JButton btnOpc2;

	    // -----------------------------------------------------------------
	    // Constructores
	    // -----------------------------------------------------------------

	    /**
	     * Constructor del panel para los m�todos de extensi�n.
	     * @param pPrincipal Ventana principal de la aplicaci�n.
	     */
	    public PanelVoto( VotoFrame pPrincipal )
	    {
	        principal = pPrincipal;
	        setLayout( new GridLayout( 1, 2 ) );
	        setBorder( new TitledBorder( "Panel Voto" ) );

	        btnOpc1 = new JButton( "Opci�n 1" );
	        btnOpc1.setActionCommand( COMANDO_1 );
	        btnOpc1.addActionListener( this );
	        add( btnOpc1 );

	        btnOpc2 = new JButton( "Opci�n 2" );
	        btnOpc2.setActionCommand( COMANDO_2 );
	        btnOpc2.addActionListener( this );
	        add( btnOpc2 );

	    }

	    // -----------------------------------------------------------------
	    // M�todos
	    // -----------------------------------------------------------------

	    /**
	     * Manejo de los eventos de los botones.
	     * @param pEvento Acci�n que gener� el evento.
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
