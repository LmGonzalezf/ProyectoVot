package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.VOCandidato;
import vos.VOCandidatoLista;

public class DAOCandidatoLista {
	private ArrayList<Object> recursos;

	/**
	 * Atributo que genera la conexión a la base de datos
	 */
	private Connection conn;

	/**
	 * Metodo constructor que crea DAOVideo
	 * <b>post: </b> Crea la instancia del DAO e inicializa el Arraylist de recursos
	 */
	public DAOCandidatoLista() {
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
	
	public ArrayList<VOCandidatoLista> darCandidatosListaPorId(Long id2) throws SQLException, Exception {
		ArrayList<VOCandidatoLista> candidatos = new ArrayList<VOCandidatoLista>();

		String sql = "SELECT * FROM sistema.candidato_lista WHERE \"ID_LISTA\" = ?";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		prepStmt.setLong(1, id2);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			Long id = rs.getLong("ID_CANDIDATO");
			Long idVotacion = rs.getLong("ID_LISTA");
			Long numeroLista = rs.getLong("NUMERO_LISTA");
			candidatos.add(new VOCandidatoLista(id, idVotacion, numeroLista));
		}
		return candidatos;
	}
	
}
