package vista;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import vos.VOEleccion;
import vos.VOVotaciones;

public class VotacionesFrame  extends JFrame
{
	Gson jsonD = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
	private InterfazIvote principal;
	JButton [] botones;
	VOVotaciones[] votacioness = new VOVotaciones[0];
	public int valor = 0;
	
	
	
	public int getValor() {
		return valor;
	}


	public void setValor(int valor) {
		this.valor = valor;
	}


	public VOVotaciones[] getVotacionesF(int entrada) throws IOException {
		entrada = entrada + 1; 
    	URL url = new URL("http://localhost:8080/IVote/rest/votaciones/"+entrada);
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
		
		VOVotaciones[] votaciones = jsonD.fromJson(json, VOVotaciones[].class);
		
		System.out.println(votaciones.length);
		
		conn.disconnect();
		votacioness = votaciones;
		return votaciones;
    }
	
	
	public VOVotaciones[] getVotaciones() {
		return votacioness;
	}




	public void setVotaciones(VOVotaciones[] votaciones) {
		this.votacioness = new VOVotaciones[votaciones.length];
		this.votacioness = votaciones;
	}
	
	public void setVotacionesFuera(VOVotaciones[] votaciones) {
		setVotaciones(votaciones);
	}




	public VotacionesFrame (InterfazIvote pprincipal) throws IOException{
		principal= pprincipal;
		int valorEscogido = principal.getEntradaVotacion();
    	votacioness = getVotacionesF(valorEscogido);
    	for(VOVotaciones x:votacioness) {
    		System.out.println("FOR EACH: "+x.getNombre());
    	}
    	setTitle( "i-vote" );
    	setSize( 565, 700 );
        setLocation(750,200);
        setResizable(false);
    	setDefaultCloseOperation( EXIT_ON_CLOSE );
    	setLayout( new BorderLayout( ) );

    	JLabel imagen = new JLabel( );
    	imagen.setIcon(new ImageIcon( new ImageIcon( "./data/banner.jpg" ).getImage( ).getScaledInstance( 565, 170, Image.SCALE_DEFAULT ) ) );
    	add( imagen, BorderLayout.NORTH );
    	
    	int tnumeroVotaciones = votacioness.length;
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
            botones[i].setActionCommand("votaciones::"+i);
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
