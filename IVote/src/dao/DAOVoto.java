package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

import vos.VOCandidato;
import vos.VOConsultaVotos;
import vos.VOVoto;

public class DAOVoto {
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
	public DAOVoto() {
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
	
	public ArrayList<VOConsultaVotos> darVotos() throws SQLException {
		String sql = "SELECT \"ESTADO\", COUNT(*) FROM sistema.votos GROUP BY \"ESTADO\"";
		ArrayList<VOConsultaVotos> retorno = new ArrayList<VOConsultaVotos>();
		
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();
		
		while (rs.next()) {
			String tipo = rs.getString("ESTADO");
			Long cantidad = rs.getLong(2);
			VOConsultaVotos consulta = new VOConsultaVotos();
			consulta.setTipo(tipo);
			consulta.setVotos(cantidad);
			retorno.add(consulta);
		}
		return retorno;
	}
	
	
	public Long darUltimoId() throws SQLException, Exception {
		String sql = "SELECT * FROM sistema.votos";
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();
		Long idFinal = 0L;
		while (rs.next()) {
			Long id = rs.getLong("ID");
			idFinal = id;
		}
		return idFinal+1;
	}
	
	public void votar(VOVoto voto) throws SQLException, Exception {
		String sql = "INSERT INTO sistema.votos VALUES (?, ?, ?, ?, ?, ?);";
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		prepStmt.setLong(1, darUltimoId());
		prepStmt.setLong(2, voto.getIdCandidato());
		prepStmt.setLong(3, voto.getIdLista());
		prepStmt.setDate(4, voto.getFecha());
		prepStmt.setString(5, voto.getUsuario());
		prepStmt.setString(6, voto.getEstado());
		recursos.add(prepStmt);
		prepStmt.executeUpdate();
	}
}
