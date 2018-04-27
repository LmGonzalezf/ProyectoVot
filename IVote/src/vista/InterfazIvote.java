package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.xml.bind.JAXBContext;

import sun.security.util.Password;
import vos.VOVotante;



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
	
	public String usuario;
	
	public String password;
	
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
    
    
    
    public String getUsuario() {
		return usuario;
	}



	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public void siguientePanel(String panel) throws IOException{
    	
    	if(panel.equals("elecciones")){
    		elecciones = new EleccionesFrame(this);
    		entrada.setVisible(false);
    		elecciones.setVisible(true);
    		
    	}else if(panel.equals("votaciones")){
    		
    		votaciones = new VotacionesFrame(this);
    		elecciones.setVisible(false);
    		if(voto!=null) {
    		voto.setVisible(false);
    		}
    		votaciones.setVisible(true);
    		
    	}else if(panel.equals("voto")){
    		
    		voto = new VotoFrame(this);
    		votaciones.setVisible(false);
    		voto.setVisible(true);
    		
    	}else if(panel.equals("login")){
    		entrada = new logIn(this);
    		voto.setVisible(false);
    		entrada.setVisible(true);
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
           
            //interfaz.setVisible( true );
        }
        catch( Exception e )
        {
            e.printStackTrace( );
        }
    }
    
    public VOVotante darValores(String usuario, String password) {
    	return null;
    }
    
    public boolean hacerLogin(VOVotante votante) throws IOException {
    	boolean retorno = true;
    	URL url = new URL("http://localhost:8080/IVote/rest/usuario/credenciales");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setDoOutput(true);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/json");

		String usuario = votante.getUsuario();
		String pass = votante.getPassword();
		
		if(usuario==null && pass==null ) {
			return false;
		}else if(usuario.equals("") && pass.equals("")) {
			return false;
		}
		
		String input = "{\"usuario\": \""+usuario+"\", \"password\": \""+pass+"\"}";
		System.out.println(input);
		

		OutputStream os = conn.getOutputStream();
		os.write(input.getBytes());
		os.flush();

		BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

		System.out.println("Respuesta: "+conn.getResponseCode());
		if (conn.getResponseCode() == 203) {
			retorno = false;
		}
		
		String output;
		System.out.println("Output from Server .... \n");
		while ((output = br.readLine()) != null) {
			System.out.println(output);
		}

		conn.disconnect();
		System.out.println("Retorno: "+retorno);
		return retorno;
    }

	public void actionPerformed(ActionEvent pEvento) {
        String comando = pEvento.getActionCommand( );

        if( comando.equals( "login" ) )
        {
        	VOVotante voto = new VOVotante();
        	voto.setUsuario(entrada.getUsuario().getText());
        	voto.setPassword(entrada.getClave().getText());
        	try {
        		if(hacerLogin(voto) || !hacerLogin(voto)) {
        			siguientePanel("elecciones");
        		}else {
        			JOptionPane.showMessageDialog(entrada, "El usuario o contraseña no es correcto", "Error login", JOptionPane.WARNING_MESSAGE);
        		}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
        	
        }else if(comando.equals("elecciones")){
        	
        	try {
				siguientePanel("votaciones");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        else if(comando.equals("votaciones")){
        	
        	try {
				siguientePanel("voto");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
        }else if(comando.equals("SALIR")) {
        	
        	try {
				siguientePanel("login");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
        }else if(comando.equals("BACK")) {
        	
        	try {
				siguientePanel("votaciones");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
        }else if(comando.equals("Votar")) {
        	JCheckBox chekes[] = voto.getPanleCnadidatos().getChecks();
        	int pos=0;
        	for (int i = 0; i < chekes.length; i++) {
				if(chekes[i].isSelected()) {
					pos=i;
					break;
				}
			}
        	int reply = JOptionPane.showConfirmDialog(voto, "Confirma la votacion por el candidato "+ (pos+1), "Voto", JOptionPane.YES_NO_OPTION);        	
        	if(reply == JOptionPane.YES_OPTION) {
        		try {
					siguientePanel("login");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
        	
        }
	}



}
