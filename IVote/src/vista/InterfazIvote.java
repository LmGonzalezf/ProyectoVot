package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import sun.security.util.Password;







/**
 * Clase principal de la interfaz.
 */
public class InterfazIvote extends JFrame implements ActionListener
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

	private logIn entrada ;
	
	private VotoFrame voto ;
	
	private EleccionesFrame elecciones;
	
	private VotacionesFrame votaciones;
	
	// -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye la interfaz de la aplicación e inicializa el mundo. <br>
     * <b>post: </b> Se inicializó la ventana principal de la aplicación con sus paneles.
     */
    public InterfazIvote( ) {
        	entrada = new logIn(this);
        	entrada.setVisible(true);
	}
    
    public void siguientePanel(String panel){
    	
    	if(panel.equals("elecciones")){
    		elecciones = new EleccionesFrame(this);
    		entrada.setVisible(false);
    		elecciones.setVisible(true);
    		
    	}else if(panel.equals("votaciones")){
    		
    		votaciones = new VotacionesFrame(this);
    		elecciones.setVisible(false);
    		votaciones.setVisible(true);
    		
    	}else if(panel.equals("voto")){
    		voto = new VotoFrame(this);
    		votaciones.setVisible(false);
    		voto.setVisible(true);
    	}
    	

    }
    
    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------
	public void reqFuncOpcion1() {
		// TODO Auto-generated method stub
		
	}

    
    // -----------------------------------------------------------------
    // Main
    // -----------------------------------------------------------------

    /**
     * Ejecuta la aplicación.
     * @param pArgs Parámetros de la ejecución. No son necesarios.
     */
    public static void main( String[] pArgs )
    {
        try
        {
            // Unifica la interfaz para Mac y para Windows.
            UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName( ) );

            InterfazIvote interfaz = new InterfazIvote();
           
            interfaz.setVisible( true );
        }
        catch( Exception e )
        {
            e.printStackTrace( );
        }
    }

	public void actionPerformed(ActionEvent pEvento) {
        String comando = pEvento.getActionCommand( );

        if( comando.equals( "login" ) )
        {
        	siguientePanel("elecciones");
        	
        }else if(comando.equals("elecciones")){
        	
        	siguientePanel("votaciones");
        }
        else if(comando.equals("votaciones")){
        	
        	siguientePanel("voto");
        }
	}



}
