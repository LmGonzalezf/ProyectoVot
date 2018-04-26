package vos;

import java.sql.Date;

import org.codehaus.jackson.annotate.JsonProperty;

public class VOEleccion {

	@JsonProperty(value="id")
	Long idEleccion;
	@JsonProperty(value="nombre")
	String nombre;
	@JsonProperty(value="fechaFinal")
	Date fechaFinal;
	@JsonProperty(value="fechaInicio")
	Date fechaInicio;
	
	
	
	public VOEleccion(@JsonProperty(value="id")Long idEleccion,@JsonProperty(value="nombre") String nombre,@JsonProperty(value="fechaFinal") Date fechaFinal,@JsonProperty(value="fechaInicio") Date fechaInicio) {
		super();
		this.idEleccion = idEleccion;
		this.nombre = nombre;
		this.fechaFinal = fechaFinal;
		this.fechaInicio = fechaInicio;
		
	}
	public Long getIdEleccion() {
		return idEleccion;
	}
	public void setIdEleccion(Long idEleccion) {
		this.idEleccion = idEleccion;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Date getFechaFinal() {
		return fechaFinal;
	}
	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
}
