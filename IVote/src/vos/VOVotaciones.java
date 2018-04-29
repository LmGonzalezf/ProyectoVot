package vos;

import java.sql.Date;

import org.codehaus.jackson.annotate.JsonProperty;

public class VOVotaciones {
	
	@JsonProperty(value="id")
	Long idVotacion;
	@JsonProperty(value="horaInicio")
	Date horaInicio;
	@JsonProperty(value="horaFinal")
	Date horaFinal;
	@JsonProperty(value="idEleccion")
	Long idEleccion;
	@JsonProperty(value="nombre")
	String nombre;
	
	public VOVotaciones(@JsonProperty(value="nombre")String nombre,@JsonProperty(value="idEleccion")Long idEleccion,@JsonProperty(value="id") Long idVotacion,@JsonProperty(value="horaInicio") Date horaInicio,@JsonProperty(value="horaFinal") Date horaFinal) {
		super();
		this.idVotacion = idVotacion;
		this.horaInicio = horaInicio;
		this.horaFinal = horaFinal;
		this.idEleccion = idEleccion;
		this.nombre = nombre;
	}
	public VOVotaciones() {
		// TODO Auto-generated constructor stub
	}
	public Long getIdVotacion() {
		return idVotacion;
	}
	public void setIdVotacion(Long idVotacion) {
		this.idVotacion = idVotacion;
	}
	public Date getHoraInicio() {
		return horaInicio;
	}
	public void setHoraInicio(Date horaInicio) {
		this.horaInicio = horaInicio;
	}
	public Date getHoraFinal() {
		return horaFinal;
	}
	public void setHoraFinal(Date horaFinal) {
		this.horaFinal = horaFinal;
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
	
}
