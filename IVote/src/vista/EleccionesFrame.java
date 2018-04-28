package vista;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import vos.VOEleccion;
import vos.VOVotante;

public class EleccionesFrame extends JFrame 
{
	
	public VOEleccion[] eleccionesT;
	public VOEleccion escogida;
	private InterfazIvote principal;
	JButton [] botones;
	Gson jsonD = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
	
	public VOEleccion[] getEleccionesT() {
		return eleccionesT;
	}

	public void setEleccionesT(VOEleccion[] eleccionesT) {
		this.eleccionesT = eleccionesT;
	}

	public VOEleccion getEscogida() {
		return escogida;
	}

	public void setEscogida(VOEleccion escogida) {
		this.escogida = escogida;
	}

	public VOEleccion[] getElecciones() throws IOException {
    	URL url = new URL("http://localhost:8080/IVote/rest/elecciones");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setDoOutput(true);
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept", "application/json");
		
		ArrayList<VOEleccion> elecciones = new ArrayList<VOEleccion>();
		
		BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

		String output;
		System.out.println("Output from Server .... \n");
		String json = "";
		while ((output = br.readLine()) != null) {
			System.out.println(output);
			json = output;
		}
		
		VOEleccion[] eleccion = jsonD.fromJson(json, VOEleccion[].class);
		
		System.out.println(eleccion.length);
		
		conn.disconnect();
		eleccionesT = eleccion;
		return eleccion;
    }
	
	public EleccionesFrame (InterfazIvote pprincipal) throws IOException{
		VOEleccion[] elecciones = getElecciones();
		System.out.println("Size: "+elecciones.length);
		principal= pprincipal;
    	setTitle( "i-vote" );
    	setSize( 565, 700 );
        setLocation(750,200);
        setResizable(false);
    	setDefaultCloseOperation( EXIT_ON_CLOSE );
    	//GridBagConstraints c = new GridBagConstraints();
    	setLayout( new BorderLayout( ) );
    	
    	JLabel imagen = new JLabel( );
    	imagen.setIcon(new ImageIcon( new ImageIcon( "./data/banner.jpg" ).getImage( ).getScaledInstance( 565, 170, Image.SCALE_DEFAULT ) ) );
    	add( imagen, BorderLayout.NORTH );
    	
    	int tnumeroEleciones = elecciones.length ;
    	int colum = 3;
    	int fil = 2;
    	while(tnumeroEleciones>(colum*fil)) {
    		fil++;
    		if(tnumeroEleciones>(colum*fil)) {
    		colum++;
    		}
    	}
    	JPanel cental = new JPanel();
    	cental.setLayout(new GridLayout(fil,colum));
    	botones = new JButton[(colum*fil)];
        for( int i = 0; i < botones.length ; i++ )
        {
            botones [ i ] = new JButton();
            botones[i].setActionCommand("elecciones");
            botones[i].addActionListener( principal);
            botones[i].setEnabled(false);
            if(tnumeroEleciones>i) {
            	Image imagenF = new ImageIcon( "./data/elecciones.png" ).getImage( ).getScaledInstance( 133, 133, Image.SCALE_AREA_AVERAGING );
            	botones[ i ].setIcon(new ImageIcon(imagenF));
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
