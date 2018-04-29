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
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import oracle.sql.DATE;
import sun.security.util.Password;
import vos.VOEleccion;
import vos.VOListaVotacion;
import vos.VOVotaciones;
import vos.VOVotante;
import vos.VOVoto;



/**
 * Clase principal de la interfaz.
 */
public class InterfazIvote extends JFrame implements ActionListener
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

	Gson jsonD = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
	
	public VOVotaciones[] votacionesT;
	
	public int entradaVotacion;
	
	public int entradaLista;
	
	private logIn entrada ;
	
	private VotoFrame voto ;
	
	private EleccionesFrame elecciones;
	
	private VotacionesFrame votaciones;
	
	public String usuario;
	
	public String password;
	
	public Long idVoto;
	
	public Long idCandidato;
	
	public Long idLista;
	
	public Date fecha;
	
	public String usuarioF;
	
	public String estado;
	
	
	
	// -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    public Long getIdVoto() {
		return idVoto;
	}





	public void setIdVoto(Long idVoto) {
		this.idVoto = idVoto;
	}





	public Long getIdCandidato() {
		return idCandidato;
	}





	public void setIdCandidato(Long idCandidato) {
		this.idCandidato = idCandidato;
	}





	public Long getIdLista() {
		return idLista;
	}





	public void setIdLista(Long idLista) {
		this.idLista = idLista;
	}





	public Date getFecha() {
		return fecha;
	}





	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}





	public String getUsuarioF() {
		return usuarioF;
	}





	public void setUsuarioF(String usuarioF) {
		this.usuarioF = usuarioF;
	}





	public String getEstado() {
		return estado;
	}





	public void setEstado(String estado) {
		this.estado = estado;
	}





	/**
     * Construye la interfaz de la aplicación e inicializa el mundo. <br>
     * <b>post: </b> Se inicializó la ventana principal de la aplicación con sus paneles.
     */
    public InterfazIvote( ) {
        	entrada = new logIn(this);
        	entrada.setVisible(true);
	}
    
    
    
    
    
    public int getEntradaLista() {
		return entradaLista;
	}





	public void setEntradaLista(int entradaLista) {
		this.entradaLista = entradaLista;
	}





	public int getEntradaVotacion() {
		return entradaVotacion;
	}





	public void setEntradaVotacion(int entradaVotacion) {
		this.entradaVotacion = entradaVotacion;
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

	public VOVotaciones[] getVotaciones() throws IOException {
    	URL url = new URL("http://localhost:8080/IVote/rest/votaciones");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setDoOutput(true);
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept", "application/json");
		
		BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

		String output;
		System.out.println("Output from Server .... \n");
		String json = "";
		while ((output = br.readLine()) != null) {
			System.out.println(output);
			json = output;
		}
		
		VOVotaciones[] votaciones = jsonD.fromJson(json, VOVotaciones[].class);
		
		System.out.println(votaciones.length);
		
		conn.disconnect();
		votacionesT = votaciones;
		return votaciones;
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
        String lcomando = comando.split("::")[0];
        String id = comando.split("::")[1];
        

        if( lcomando.equals( "login" ) )
        {
        	VOVotante voto = new VOVotante();
        	voto.setUsuario(entrada.getUsuario().getText());
        	setUsuarioF(entrada.getUsuario().getText());
        	voto.setPassword(entrada.getClave().getText());
        	try {
        		if(hacerLogin(voto)) {
        			siguientePanel("elecciones");
        		}else {
        			JOptionPane.showMessageDialog(entrada, "El usuario o contraseña no es correcto", "Error login", JOptionPane.WARNING_MESSAGE);
        		}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
        	
        }else if(lcomando.equals("elecciones")){
        	
        	try {
        		String parte = pEvento.getActionCommand().split("::")[1];
        		int buscar = Integer.parseInt(parte);
        		System.out.println("HOLA: "+pEvento.getActionCommand());
        		VOEleccion[] eleccionest = elecciones.getEleccionesT();
        		System.out.println(eleccionest[buscar].getNombre());
        		//VOEleccion escogida = eleccionesT[comando[posicion]];
				siguientePanel("votaciones");
				votaciones.setValor(buscar);
				votacionesT = getVotaciones();
				votaciones.setVotaciones(votacionesT);
				setEntradaVotacion(buscar);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        else if(lcomando.equals("votaciones")){
        	
        	try {
        		String parte = pEvento.getActionCommand().split("::")[1];
        		int buscar = Integer.parseInt(parte);
				siguientePanel("voto");
				setEntradaLista(buscar);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
        }else if(lcomando.equals("SALIR")) {
        	System.out.println("HOÑA");
        	try {
        		JCheckBox chekes[] = voto.getPanelCandidatos().getChecks();
            	int pos=0;
            	for (int i = 0; i < chekes.length; i++) {
    				if(chekes[i].isSelected()) {
    					pos=i;
    					break;
    				}
    			}
            	setIdCandidato(voto.panelCandidatos.candidatos[pos].getId());
            	Long idLis = (long) getEntradaVotacion();
            	setIdLista(idLis+1);
            	setFecha(new Date(Calendar.getInstance().getTime().getTime()));
            	setEstado("CANCELADO");
				siguientePanel("login");
				votar();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(voto, "El usuario ya ha votado para esta votación.");
				e.printStackTrace();
			}
        	
        }else if(lcomando.equals("BACK")) {
        	
        	try {
				siguientePanel("votaciones");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
        }else if(lcomando.equals("Votar")) {
        	JCheckBox chekes[] = voto.getPanelCandidatos().getChecks();
        	int pos=0;
        	for (int i = 0; i < chekes.length; i++) {
				if(chekes[i].isSelected()) {
					pos=i;
					break;
				}
			}
        	String nombre = (voto.panelCandidatos.candidatos[pos].getNombre());
        	setIdCandidato(voto.panelCandidatos.candidatos[pos].getId());
        	Long idLis = (long) getEntradaVotacion();
        	setIdLista(idLis+1);
        	setFecha(new Date(Calendar.getInstance().getTime().getTime()));
        	setEstado("VALIDADO");
        	int reply = JOptionPane.showConfirmDialog(voto, "¿Confirma la votacion por el candidato "+ nombre+"?", "Voto", JOptionPane.YES_NO_OPTION);        	
        	if(reply == JOptionPane.YES_OPTION) {
        		try {
        			siguientePanel("login");
        			votar();
					
				} catch (IOException e) {
					JOptionPane.showMessageDialog(voto, "El usuario ya ha votado para esta votación.");
					e.printStackTrace();
				}
        	}
        	
        }
	}
	
	public void votar() throws IOException {
    	URL url = new URL("http://localhost:8080/IVote/rest/votos");
    	System.out.println(url.toString());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setDoOutput(true);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/json");
		VOVoto votoF = new VOVoto(0L, getIdCandidato(), getIdLista(), getUsuarioF(), getFecha(), getEstado());
		String json = jsonD.toJson(votoF, VOVoto.class);
		System.out.println(json);
		OutputStream os = conn.getOutputStream();
		os.write(json.getBytes());
		os.flush();
		
		BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

		String output;
		while ((output = br.readLine()) != null) {
			System.out.println(output);
			json += output;
		}
		
		conn.disconnect();
    }



}
