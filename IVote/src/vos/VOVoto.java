package vos;

import java.sql.Date;

import org.codehaus.jackson.annotate.JsonProperty;

public class VOVoto {
	@JsonProperty(value="id")
	Long id;
	@JsonProperty(value="idCandidato")
	Long idCandidato;
	@JsonProperty(value="idLista")
	Long idLista;
	@JsonProperty(value="idUsuario")
	Long idUsuario;
	@JsonProperty(value="fecha")
	Date fecha;
	public VOVoto(@JsonProperty(value="id")Long id,@JsonProperty(value="idCandidato") Long idCandidato, @JsonProperty(value="idLista")Long idLista,@JsonProperty(value="idUsuario") Long idUsuario, @JsonProperty(value="fecha")Date fecha) {
		super();
		this.id = id;
		this.idCandidato = idCandidato;
		this.idLista = idLista;
		this.idUsuario = idUsuario;
		this.fecha = fecha;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getIdCandidato() {
		return idCandidato;
	}
	public void setIdCandidato(Long idCandidato) {
		this.idCandidato = idCandidato;
	}
	public Long getIdLista() {
		return idLista;
	}
	public void setIdLista(Long idLista) {
		this.idLista = idLista;
	}
	public Long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	
}
