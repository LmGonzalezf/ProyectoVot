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

import vos.VOGanadores;
import vos.VOVotaciones;
import vos.VOVotante;


public class DAOVotacion {
	
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
	public DAOVotacion() {
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
	
	
	/**
	 * @throws Exception 
	 * 
	 */
	public ArrayList<VOVotaciones> darVotaciones() throws Exception
	{
		ArrayList<VOVotaciones> votaciones = new ArrayList<VOVotaciones>();
		String sql = "SELECT * FROM sistema.votaciones";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			Long id = rs.getLong(1);
			Date fechaInicio = rs.getDate(2);
			Date fechaFinal = rs.getDate(3);
			Long idEleccion = rs.getLong(4);
			String nombre = rs.getString(5);
			votaciones.add(new VOVotaciones(nombre,idEleccion,id, fechaInicio, fechaFinal));
		}
		return votaciones;
	}
	
	public ArrayList<VOVotaciones> darVotacionesPorId(Long id) throws Exception
	{
		ArrayList<VOVotaciones> votaciones = new ArrayList<VOVotaciones>();
		String sql = "SELECT * FROM sistema.votaciones WHERE \"ID_ELECCION\" = ?";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		prepStmt.setLong(1, id);
		System.out.println("HOLA SQL: "+sql+id);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while(rs.next()) {
			Long id2 = rs.getLong(1);
			Date fechaInicio = rs.getDate(2);
			Date fechaFinal = rs.getDate(3);
			Long idEleccion = rs.getLong(4);
			String nombre = rs.getString(5);
			System.out.println(nombre);
			votaciones.add(new VOVotaciones(nombre,idEleccion,id2, fechaInicio, fechaFinal));
		}
		
		return votaciones;
	}
	
	
}