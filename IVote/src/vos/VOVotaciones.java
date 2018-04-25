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
	
	public VOVotaciones(Long idVotacion, Date horaInicio, Date horaFinal) {
		super();
		this.idVotacion = idVotacion;
		this.horaInicio = horaInicio;
		this.horaFinal = horaFinal;
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
}
