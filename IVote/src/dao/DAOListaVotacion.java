package dao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import vos.VOEleccion;
import vos.VOGanadores;
import vos.VOVotaciones;
import vos.VOVotante;
import vos.VOListaVotacion;


public class DAOListaVotacion {
	
	/**
	 * Arraylits de recursos que se usan para la ejecución de sentencias SQL
	 */
	private ArrayList<Object> recursos;

	/**
	 * Atributo que genera la conexión a la base de datos
	 */
	private Connection conn;
	/**
	 * Metodo constructor que crea DAOVideo
	 * <b>post: </b> Crea la instancia del DAO e inicializa el Arraylist de recursos
	 */
	public DAOListaVotacion() {
		recursos = new ArrayList<Object>();
	}
	/**
	 * Metodo que cierra todos los recursos que estan enel arreglo de recursos
	 * <b>post: </b> Todos los recurso del arreglo de recursos han sido cerrados
	 */
	public void cerrarRecursos() {
		for(Object ob : recursos){
			if(ob instanceof PreparedStatement)
				try {
					((PreparedStatement) ob).close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
		}
	}
	/**
	 * Metodo que inicializa la connection del DAO a la base de datos con la conexión que entra como parametro.
	 * @param con  - connection a la base de datos
	 */
	public void setConn(Connection con){
		this.conn = con;
	}
	
	public ArrayList<VOListaVotacion> darListasVotacion() throws Exception
	{
		ArrayList<VOListaVotacion> listas = new ArrayList<VOListaVotacion>();
		String sql = "SELECT * FROM sistema.lista_votacion";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			Long id = rs.getLong(1);
			Long idVotacion = rs.getLong(2);
			String nombre = rs.getString(3);
			listas.add(new VOListaVotacion(id, nombre, idVotacion));
		}
		return listas;
	}
	
	public ArrayList<VOListaVotacion> darListasVotacionPorIdVotacion(Long id) throws Exception
	{
		ArrayList<VOListaVotacion> listas = new ArrayList<VOListaVotacion>();
		String sql = "SELECT * FROM sistema.lista_votacion WHERE \"ID_VOTACION\" = ?";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		prepStmt.setLong(1, id);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			Long id2 = rs.getLong(1);
			Long idVotacion = rs.getLong(2);
			String nombre = rs.getString(3);
			listas.add(new VOListaVotacion(id2, nombre, idVotacion));
		}
		return listas;
	}
}
