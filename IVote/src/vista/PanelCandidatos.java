package vista;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import vos.VOCandidato;
import vos.VOCandidatoLista;
import vos.VOEleccion;
import vos.VOListaVotacion;
import vos.VOVotaciones;


public class PanelCandidatos extends JPanel implements ActionListener
{ // -----------------------------------------------------------
	  // Constantes
	  // -----------------------------------------------------------
	    
	    /**
	     * Ruta donde se encuentran todas las imágenes.
	     */
	    private final static String RUTA = "./data/imagenes/";
	    // -----------------------------------------------------------
	    // Atributos
	    // -----------------------------------------------------------

	    /**
	     * Ventana principal de la aplicación.
	     */
	    private VotoFrame principal;

	    // -----------------------------------------------------------
	    // Atributos de interfaz
	    // -----------------------------------------------------------

	    /**
	     * Botón para candidato.
	     */
	    Gson jsonD = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
	    public VOListaVotacion[] lista;
	    public VOCandidatoLista[] listaCandidatos;
	    public VOCandidato[] candidatos;
	    public VOCandidato candidato;
	    
	    public VOListaVotacion[] getVotacionesF(int entrada) throws IOException {
			entrada = entrada + 1; 
	    	URL url = new URL("http://localhost:8080/IVote/rest/listas/"+entrada);
	    	System.out.println(url.toString());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output;
			System.out.println("Output from Server LISTAS GRANDE .... \n");
			String json = "";
			while ((output = br.readLine()) != null) {
				System.out.println(output);
				json += output;
			}
			
			VOListaVotacion[] votaciones = jsonD.fromJson(json, VOListaVotacion[].class);
			
			System.out.println(votaciones.length);
			
			conn.disconnect();
			lista = votaciones;
			return votaciones;
	    }
	    
	    public VOCandidato[] getCandidatosDatosF(int entrada) throws IOException {
	    	URL url = new URL("http://localhost:8080/IVote/rest/candidato/"+entrada);
	    	System.out.println(url.toString());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output;
			System.out.println("Output from Server VOTACIONES .... \n");
			String json = "";
			while ((output = br.readLine()) != null) {
				System.out.println(output);
				json += output;
			}
			
			VOCandidato[] votaciones = jsonD.fromJson(json, VOCandidato[].class);
			
			System.out.println(votaciones.length);
			
			conn.disconnect();
			candidato = votaciones[0];
			return votaciones;
	    }
	    
	    public VOCandidatoLista[] getCandidatosF(int entrada) throws IOException {
			entrada = entrada + 1; 
	    	URL url = new URL("http://localhost:8080/IVote/rest/listaCandidatos/"+entrada);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output;
			System.out.println("Output from Server LISTA .... \n");
			String json = "";
			while ((output = br.readLine()) != null) {
				System.out.println(output);
				json += output;
			}
			
			VOCandidatoLista[] votaciones = jsonD.fromJson(json, VOCandidatoLista[].class);
			
			System.out.println(votaciones.length);
			
			conn.disconnect();
			listaCandidatos = votaciones;
			return votaciones;
	    }
	    
	    private JCheckBox[] checks;
	    
	    private ButtonGroup grupo= new ButtonGroup() ;

	    // -----------------------------------------------------------
	    // Constructores
	    // -----------------------------------------------------------

	    /**
	     * Constructor del panel. <br>
	     * <b>post: </b> Se inicializó el panel.
	     * @param pPrincipal Ventana principal. pPrincipal != null.
	     * @param pAnimales Lista de palabras a mostrar.
	     * @throws IOException 
	     */
	    public PanelCandidatos( VotoFrame pPrincipal ) throws IOException
	    {

	        principal = pPrincipal;
	        setLayout( new GridLayout( 2, 3 ) );
	        int valorEscogido = principal.getPrincipal().getEntradaLista();
	        lista = getVotacionesF(valorEscogido);
	        
	    	for(VOListaVotacion x:lista) {
	    		System.out.println("FOR EACH Candidatos: "+x.getIdEleccion());
	    	}
	        Long idLista = lista[0].getIdLista();
	        principal.getPrincipal().setIdLista(idLista);
	        int idListaFinal = (int) (idLista - 1);
	        System.out.println("Lista Final INT: "+idListaFinal);
	        
	        listaCandidatos = getCandidatosF(principal.getPrincipal().getEntradaVotacion());
	        
	        candidatos = new VOCandidato[listaCandidatos.length];
	        int contador = 0;
	        for(VOCandidatoLista x: listaCandidatos) {
	        	System.out.println("FOR EACH Candidatos Lista: "+x.getIdCandidato());
	        	int entradaC = (int) (x.getIdCandidato() + 0);
	        	getCandidatosDatosF(entradaC);
	        	candidatos[contador] = candidato;
	        	contador++;
	        }
	        
	        for(VOCandidato x: candidatos) {
	        	System.out.println("FOR EACH CANDIDATO FINAL: "+x.getNombre());
	        }
	        
	        int tnumeroCandidatos = candidatos.length;
	        int column = 3;
	        int fil = 2;
	        while(tnumeroCandidatos>(column*fil)) {
	    		fil++;
	    		if(tnumeroCandidatos>(column*fil)) {
	    		column++;
	    		}
	    	}
	        checks = new JCheckBox[(column*fil)];
	        for( int i = 0; i < checks.length; i++ )
	        {
	        	JPanel lugar  = new JPanel();
	        	lugar.setLayout(new BorderLayout());
	            checks [ i ] = new JCheckBox();
	            checks[i].setEnabled(false);
	            if(tnumeroCandidatos>i) {
	            	JLabel foto = new JLabel( );
		            foto.setIcon( new ImageIcon( new ImageIcon( RUTA + "candi.png" ).getImage( ).getScaledInstance( 133, 133, Image.SCALE_DEFAULT ) ) );
		            checks[i].setActionCommand("elegir::"+i);
		            checks[i].addActionListener(this);
		            grupo.add(checks[i]);
		            lugar.add( foto , BorderLayout.CENTER);
		            lugar.add(checks[i], BorderLayout.SOUTH);
		            checks[i].setEnabled(true);
	            }
	            
	            
	            add(lugar);
	            
	        }

	    }
	    
	    
	    
	    

	    public JCheckBox[] getChecks() {
			return checks;
		}





		public void setChecks(JCheckBox[] checks) {
			this.checks = checks;
		}





		public ButtonGroup getGrupo() {
			return grupo;
		}





		public void setGrupo(ButtonGroup grupo) {
			this.grupo = grupo;
		}





		/**
	     * Manejo de los eventos de los botones.
	     * @param pEvento Evento de click sobre un botón. pEvento != null.
	     */
	    public void actionPerformed(ActionEvent pEvento) {
	        String comando = pEvento.getActionCommand( );
	        
	        int n =0;
	        
	        if( comando.equals( "elegir" ) )
	        {
	        	
	        }
	    }
	}
