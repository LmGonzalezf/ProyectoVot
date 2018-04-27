package rest;

import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tm.IVoteTM;
import vos.VOCandidato;
import vos.VOVotante;

@Path("usuario")
public class VotanteServices {
	@Context
	private ServletContext context;

	/**
	 * Metodo que retorna el path de la carpeta WEB-INF/ConnectionData en el deploy actual dentro del servidor.
	 * @return path de la carpeta WEB-INF/ConnectionData en el deploy actual.
	 */
	private String getPath() {
		return context.getRealPath("WEB-INF/ConnectionData");
	}


	private String doErrorMessage(Exception e){
		return "{ \"ERROR\": \""+ e.getMessage() + "\"}" ;
	}
	
	/**
	 * Metodo que expone servicio REST usando GET que busca el video con el nombre que entra como parametro
	 * <b>URL: </b> http://"ip o nombre de host":8080/VideoAndes/rest/videos/nombre/nombre?nombre=<<nombre>>" para la busqueda"
	 * @param name - Nombre del video a buscar que entra en la URL como parametro 
	 * @return Json con el/los videos encontrados con el nombre que entra como parametro o json con 
	 * el error que se produjo
	 */
	@GET
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response getVotantes() {
		IVoteTM tm = new IVoteTM(getPath());
		List<VOVotante> votantes;
		try {
			votantes = tm.darVotantes();
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(votantes).build();
	}
	
	/**
	 * Método que expone servicio REST usando POST que registra un usuario en la base de datos con una contraseña autogenerada
	 * <b>URL: </b> 
	 * @param
	 * @return
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response registrarUsuario(VOVotante votante) {
		IVoteTM tm = new IVoteTM(getPath());
		VOVotante votantenuevo = new VOVotante();
		try
		{
			votantenuevo = votante;
			tm.registrarVotante(votantenuevo);
		}
		catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(votantenuevo).build();
	}
	
	@POST
	@Path("/credenciales")
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response validarUsuario(VOVotante credenciales) {
		IVoteTM tm = new IVoteTM(getPath());
		boolean retorno = false;
		try {
			retorno = tm.validarCredenciales(credenciales);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		if(!retorno) {
			return Response.status(203).entity(retorno).build();
		}
		else if (retorno) {
			return Response.status(200).entity(retorno).build();
		}
		return null;
		
	}
}
