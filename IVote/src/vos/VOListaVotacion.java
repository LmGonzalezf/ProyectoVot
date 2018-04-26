package vos;

import java.sql.Date;

import org.codehaus.jackson.annotate.JsonProperty;

public class VOListaVotacion {

	@JsonProperty(value="id")
	Long idLista;
	@JsonProperty(value="nombre")
	String nombre;
	@JsonProperty(value="idVotacion")
	Long idVotacion;
	public VOListaVotacion(@JsonProperty(value="id")Long idLista,@JsonProperty(value="nombre") String nombre,@JsonProperty(value="idVotacion") Long idVotacion) {
		super();
		this.idLista = idLista;
		this.nombre = nombre;
		this.idVotacion = idVotacion;
	}
	public Long getIdLista() {
		return idLista;
	}
	public void setIdLista(Long idLista) {
		this.idLista = idLista;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Long getIdEleccion() {
		return idVotacion;
	}
	public void setIdEleccion(Long idEleccion) {
		this.idVotacion = idEleccion;
	}

}
