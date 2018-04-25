package vos;

import java.sql.Date;

import org.codehaus.jackson.annotate.JsonProperty;

public class VOListaVotacion {

	@JsonProperty(value="id")
	Long idLista;
	@JsonProperty(value="nombre")
	String nombre;
	@JsonProperty(value="idEleccion")
	Long idEleccion;
	public VOListaVotacion(@JsonProperty(value="id")Long idLista,@JsonProperty(value="nombre") String nombre,@JsonProperty(value="idEleccion") Long idEleccion) {
		super();
		this.idLista = idLista;
		this.nombre = nombre;
		this.idEleccion = idEleccion;
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
		return idEleccion;
	}
	public void setIdEleccion(Long idEleccion) {
		this.idEleccion = idEleccion;
	}

}
