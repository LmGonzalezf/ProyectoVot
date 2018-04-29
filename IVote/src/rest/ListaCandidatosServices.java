package rest;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tm.IVoteTM;
import vos.VOCandidatoLista;
import vos.VOListaVotacion;

@Path("listaCandidatos")
public class ListaCandidatosServices {
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
	
	@GET
	@Path("{id}")
	public Response getListaCandidatosPorId(@PathParam("id") Long id) {
		IVoteTM tm = new IVoteTM(getPath());
		ArrayList<VOCandidatoLista> votaciones;
		VOCandidatoLista[] pasar;
		int index = 0;
		try {
			index = 0;
			votaciones = tm.candidatosPorListaId(id);
			pasar = new VOCandidatoLista[votaciones.size()];
			for(VOCandidatoLista x:votaciones) {
				pasar[index] = x;
				index++;
			}
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(pasar).build();
	}
}
