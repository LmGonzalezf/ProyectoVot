package dao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;


import vos.VOVotante;


public class DAOVotante {
	
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
	public DAOVotante() {
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
	public ArrayList<VOVotante> darVotantes() throws SQLException, Exception {
		ArrayList<VOVotante> candidatos = new ArrayList<VOVotante>();

		String sql = "SELECT * FROM sistema.candidatos";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			Long id = rs.getLong("ID");
			String usuario = rs.getString("USUARIO");
			String genero = rs.getString("GENERO");
			Date fechaNacimiento = rs.getDate("FECHA_NACIMIENTO");
			String password = rs.getString("CONTRASE�A");
			candidatos.add(new VOVotante(id, usuario, genero, fechaNacimiento, password));
		}
		return candidatos;
	}
	
	public boolean hacerComprobacion(VOVotante credenciales) throws SQLException, Exception{
		boolean retorno = false;
		String sql = "SELECT \"USUARIO\", \"CONTRASE�A\" FROM sistema.votantes WHERE \"USUARIO\" = ?";
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		prepStmt.setString(1, credenciales.getUsuario());
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();
		String pass = "";
		while(rs.next()) {
			pass = rs.getString("CONTRASE�A");
		}
		if(pass.equals(credenciales.getPassword())){
			retorno = true;
		}
		else if(!pass.equals(credenciales.getPassword())){
			retorno = false;
		}
		return retorno;
	}
}
