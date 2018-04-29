package tm;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import dao.DAOCandidato;
import dao.DAOCandidatoLista;
import dao.DAOEleccion;
import dao.DAOGanadores;
import dao.DAOListaVotacion;
import dao.DAOVotacion;
import dao.DAOVotante;
import dao.DAOVoto;
import vos.VOCandidato;
import vos.VOCandidatoLista;
import vos.VOConsultaVotos;
import vos.VOEleccion;
import vos.VOGanadores;
import vos.VOListaVotacion;
import vos.VOVotaciones;
import vos.VOVotante;
import vos.VOVoto;

public class IVoteTM {
	/**
	 * Atributo estatico que contiene el path relativo del archivo que tiene los datos de la conexion
	 */
	private static final String CONNECTION_DATA_FILE_NAME_REMOTE = "/conexion.properties";

	/**
	 * Atributo estatico que contiene el path absoluto del archivo que tiene los datos de la conexion
	 */
	private  String connectionDataPath;

	/**
	 * Atributo que guarda el usuario que se va a usar para conectarse a la base de datos.
	 */
	private String user;

	/**
	 * Atributo que guarda la clave que se va a usar para conectarse a la base de datos.
	 */
	private String password;

	/**
	 * Atributo que guarda el URL que se va a usar para conectarse a la base de datos.
	 */
	private String url;

	/**
	 * Atributo que guarda el driver que se va a usar para conectarse a la base de datos.
	 */
	private String driver;

	/**
	 * conexion a la base de datos
	 */
	private Connection conn;


	/**
	 * Metodo constructor de la clase VideoAndesMaster, esta clase modela y contiene cada una de las 
	 * transacciones y la logica de negocios que estas conllevan.
	 * <b>post: </b> Se crea el objeto VideoAndesTM, se inicializa el path absoluto del archivo de conexion y se
	 * inicializa los atributos que se usan par la conexion a la base de datos.
	 * @param contextPathP - path absoluto en el servidor del contexto del deploy actual
	 */
	public IVoteTM(String contextPathP) {
		connectionDataPath = contextPathP + CONNECTION_DATA_FILE_NAME_REMOTE;
		initConnectionData();
	}

	/**
	 * Metodo que  inicializa los atributos que se usan para la conexion a la base de datos.
	 * <b>post: </b> Se han inicializado los atributos que se usan par la conexion a la base de datos.
	 */
	private void initConnectionData() {
		try {
			File arch = new File(this.connectionDataPath);
			Properties prop = new Properties();
			FileInputStream in = new FileInputStream(arch);
			prop.load(in);
			in.close();
			this.url = prop.getProperty("url");
			this.user = prop.getProperty("usuario");
			this.password = prop.getProperty("clave");
			this.driver = prop.getProperty("driver");
			Class.forName(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Metodo que  retorna la conexion a la base de datos
	 * @return Connection - la conexion a la base de datos
	 * @throws SQLException - Cualquier error que se genere durante la conexion a la base de datos
	 */
	private Connection darConexion() throws SQLException {
		System.out.println("Connecting to: " + url + " With user: " + user);
		return DriverManager.getConnection(url, user, password);
	}
	
	public List<VOCandidato> darCandidatos() throws Exception {
		List<VOCandidato> candidatos;
		DAOCandidato daoCandidato = new DAOCandidato();
		try 
		{
			//////transaccion
			
			this.conn = darConexion();
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(conn.TRANSACTION_SERIALIZABLE);
			daoCandidato.setConn(conn);
			candidatos = daoCandidato.darCandidatos();
			
			conn.commit();
		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			conn.rollback();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			try {
				daoCandidato.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				conn.rollback();
				throw exception;
			}
		}
		return candidatos;
	}
	
	public List<VOVotante> darVotantes() throws Exception{
		List<VOVotante> votantes;
		DAOVotante daovotante = new DAOVotante();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(conn.TRANSACTION_SERIALIZABLE);
			daovotante.setConn(conn);
			votantes = daovotante.darVotantes();
			conn.commit();
			
		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			conn.rollback();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			try {
				daovotante.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				conn.rollback();
				throw exception;
			}
		}
		return votantes;
	}
	
	public List<VOConsultaVotos> darVotosPorTipo() throws Exception{
		List<VOConsultaVotos> votos;
		DAOVoto daoVoto = new DAOVoto();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(conn.TRANSACTION_SERIALIZABLE);
			daoVoto.setConn(conn);
			votos = daoVoto.darVotos();
			conn.commit();
			
		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			conn.rollback();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			try {
				daoVoto.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				conn.rollback();
				throw exception;
			}
		}
		return votos;
	}
	
	public boolean validarCredenciales(VOVotante credenciales) throws Exception{
		boolean retorno;
		DAOVotante votante = new DAOVotante();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(conn.TRANSACTION_SERIALIZABLE);
			votante.setConn(conn);
			retorno = votante.hacerComprobacion(credenciales);
			conn.commit();
			
		} catch (SQLException e) {
			conn.rollback();
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			conn.rollback();
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				votante.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				conn.rollback();
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return retorno;
	}

	public void registrarVotante(VOVotante votantenuevo) throws Exception {
		DAOVotante daovotante = new DAOVotante();
		try 
		{
			this.conn = darConexion();
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(conn.TRANSACTION_SERIALIZABLE);
			
			daovotante.setConn(conn);
			daovotante.registrarUsuario(votantenuevo);
			conn.commit();

		} catch (SQLException e) {
			conn.rollback();
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			conn.rollback();
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daovotante.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				conn.rollback();
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		
	}
	
	public List<VOGanadores> darGanadores() throws Exception 
	{
		List<VOGanadores> ganadores;
		DAOGanadores daoganadores = new DAOGanadores();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(conn.TRANSACTION_SERIALIZABLE);
			
			daoganadores.setConn(conn);
			ganadores = daoganadores.consultarGanadores();
			conn.commit();
			
		} catch (SQLException e) {
			conn.rollback();
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			conn.rollback();
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoganadores.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				conn.rollback();
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return ganadores;
	}
	
	
	public List<VOVotaciones> darVotaciones() throws Exception
	{
		List<VOVotaciones> votaciones;
		DAOVotacion daovotacion = new DAOVotacion();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(conn.TRANSACTION_SERIALIZABLE);
			
			daovotacion.setConn(conn);
			votaciones = daovotacion.darVotaciones();
			conn.commit();
			
		} catch (SQLException e) {
			conn.rollback();
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			conn.rollback();
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daovotacion.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				conn.rollback();
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return votaciones;
	}
	
	public List<VOEleccion> darEleccioness() throws Exception
	{
		List<VOEleccion> elecciones;
		DAOEleccion daoeleccion = new DAOEleccion();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(conn.TRANSACTION_SERIALIZABLE);
			
			daoeleccion.setConn(conn);
			elecciones = daoeleccion.darElecciones();
			conn.commit();
			
		} catch (SQLException e) {
			conn.rollback();
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			conn.rollback();
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoeleccion.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				conn.rollback();
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return elecciones;
	}
	
	public List<VOListaVotacion> darListasVotaciones() throws Exception
	{
		List<VOListaVotacion> listas;
		DAOListaVotacion daolista = new DAOListaVotacion();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(conn.TRANSACTION_SERIALIZABLE);
			
			daolista.setConn(conn);
			listas = daolista.darListasVotacion();
			conn.commit();
			
		} catch (SQLException e) {
			conn.rollback();
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			conn.rollback();
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daolista.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				conn.rollback();
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return listas;
	}
	
	public ArrayList<VOVotaciones> votacionPorId(Long id) throws SQLException, Exception{
		ArrayList<VOVotaciones> votacion;
		DAOVotacion daoVotacion = new DAOVotacion();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(conn.TRANSACTION_SERIALIZABLE);
			
			daoVotacion.setConn(conn);
			votacion = daoVotacion.darVotacionesPorId(id);
			
			conn.commit();
			
		} catch (SQLException e) {
			conn.rollback();
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			conn.rollback();
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoVotacion.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				conn.rollback();
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return votacion;
	}
	
	public ArrayList<VOListaVotacion> listasPorId(Long id) throws SQLException, Exception{
		ArrayList<VOListaVotacion> votacion;
		DAOListaVotacion daoVotacion = new DAOListaVotacion();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(conn.TRANSACTION_SERIALIZABLE);
			
			daoVotacion.setConn(conn);
			votacion = daoVotacion.darListasVotacionPorIdVotacion(id);
			conn.commit();
			
		} catch (SQLException e) {
			conn.rollback();
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			conn.rollback();
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				
				daoVotacion.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				conn.rollback();
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return votacion;
	}
	
	public ArrayList<VOCandidatoLista> candidatosPorListaId(Long id) throws SQLException, Exception{
		ArrayList<VOCandidatoLista> votacion;
		DAOCandidatoLista daoVotacion = new DAOCandidatoLista();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(conn.TRANSACTION_SERIALIZABLE);
			
			daoVotacion.setConn(conn);
			votacion = daoVotacion.darCandidatosListaPorId(id);
			conn.commit();
			
		} catch (SQLException e) {
			conn.rollback();
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			conn.rollback();
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoVotacion.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				conn.rollback();
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return votacion;
	}
	
	public ArrayList<VOCandidato> candidatosId(Long id) throws SQLException, Exception{
		ArrayList<VOCandidato> votacion;
		DAOCandidato daoVotacion = new DAOCandidato();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(conn.TRANSACTION_SERIALIZABLE);
			
			daoVotacion.setConn(conn);
			votacion = daoVotacion.darCandidatosPorID(id);
			conn.commit();
			
		} catch (SQLException e) {
			conn.rollback();
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			conn.rollback();
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoVotacion.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				conn.rollback();
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return votacion;
	}
	
	public void votar(VOVoto voto) throws SQLException, Exception{
		DAOVoto daoVotacion = new DAOVoto();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(conn.TRANSACTION_SERIALIZABLE);
			
			daoVotacion.setConn(conn);
			daoVotacion.votar(voto);
			conn.commit();
			
		} catch (SQLException e) {
			conn.rollback();
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			conn.rollback();
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoVotacion.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
}

