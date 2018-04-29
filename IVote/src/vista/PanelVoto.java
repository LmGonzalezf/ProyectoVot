package vista;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import vos.VOListaVotacion;
import vos.VOVotaciones;



public class PanelVoto extends JPanel
	{
	    // -----------------------------------------------------------------
	    // Constantes
	    // -----------------------------------------------------------------

	
		Gson jsonD = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
	    /**
	     * Constante para el método de extensión 1.
	     */
	    public final static String COMANDO_SALIR = "SALIR::0";

	    /**
	     * Constante para el método de extensión 2.
	     */
	    public final static String COMANDO_BACK = "BACK::0";

	    // -----------------------------------------------------------------
	    // Atributos
	    // -----------------------------------------------------------------

	    /**
	     * Ventana principal 
	     */
	    private InterfazIvote principal;
	    
	    private VotoFrame frameVoto;
	    
	    public int valor = 0;
	    
	    public VOListaVotacion[] lista;
	    
	    public int getValor() {
			return valor;
		}


		public void setValor(int valor) {
			this.valor = valor;
		}
		
		
		
		

	    // -----------------------------------------------------------------
	    // Atributos de Interfaz
	    // -----------------------------------------------------------------

	    public InterfazIvote getPrincipal() {
			return principal;
		}


		public void setPrincipal(InterfazIvote principal) {
			this.principal = principal;
		}





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
	        btnOpc3.setActionCommand( "Votar::0" );
	        btnOpc3.addActionListener( principal );
	        add( btnOpc3 );

	    }

	    // -----------------------------------------------------------------
	    // Métodos
	    // -----------------------------------------------------------------

	}
