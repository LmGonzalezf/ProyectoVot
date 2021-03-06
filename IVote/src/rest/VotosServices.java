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
import vos.VOConsultaVotos;
import vos.VOVoto;

@Path("votos")
public class VotosServices {
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
	public Response getVotos() {
		IVoteTM tm = new IVoteTM(getPath());
		List<VOConsultaVotos> votos;
		try {
			votos = tm.darVotosPorTipo();
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(votos).build();
	}
	
	@POST
	@Produces( { MediaType.APPLICATION_JSON } )
	@Consumes( { MediaType.APPLICATION_JSON } )
	public Response votar(VOVoto voto) {
		IVoteTM tm = new IVoteTM(getPath());
		try {
			tm.votar(voto);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).build();
	}
}
