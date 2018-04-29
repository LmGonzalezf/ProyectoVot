package rest;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tm.IVoteTM;
import vos.VOEleccion;
import vos.VOGanadores;
import vos.VOListaVotacion;
import vos.VOVotaciones;

@Path("listas")
public class ListaVotacionesServices {
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
	public Response getElecciones() {
		IVoteTM tm = new IVoteTM(getPath());
		List<VOListaVotacion> listas;
		try {
			listas = tm.darListasVotaciones();
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(listas).build();
	}
	
	@GET
	@Path("{id}")
	public Response getVotacionesPorId(@PathParam("id") Long id) {
		IVoteTM tm = new IVoteTM(getPath());
		ArrayList<VOListaVotacion> votaciones;
		VOListaVotacion[] pasar;
		int index = 0;
		try {
			index = 0;
			votaciones = tm.listasPorId(id);
			pasar = new VOListaVotacion[votaciones.size()];
			for(VOListaVotacion x:votaciones) {
				pasar[index] = x;
				index++;
			}
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(pasar).build();
	}
}