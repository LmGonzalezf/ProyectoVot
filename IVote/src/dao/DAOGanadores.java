package dao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.UUID;

import vos.VOGanadores;
import vos.VOVotante;


public class DAOGanadores {
	
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
	public DAOGanadores() {
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
	 * M�todo que permite la consulta de los ganadores historicos de todas las elecciones
	 * @throws Exception 
	 */
	public ArrayList<VOGanadores> consultarGanadores() throws Exception
	{
		ArrayList<VOGanadores> ganadores = new ArrayList<VOGanadores>();

		String sql = "SELECT * FROM sistema.ganadores";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			Long id = rs.getLong(1);
			Long idCandidato = rs.getLong(2);
			Long idEleccion = rs.getLong(3);
			Long votos = rs.getLong(4);
			ganadores.add(new VOGanadores(id, idCandidato, idEleccion, votos));
		}
		return ganadores;
	}
}
