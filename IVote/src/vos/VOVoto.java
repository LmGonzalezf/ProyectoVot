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
	@JsonProperty(value="usuario")
	String usuario;
	@JsonProperty(value="fecha")
	Date fecha;
	@JsonProperty(value="estado")
	String estado;
	
	public VOVoto(@JsonProperty(value="id")Long id, @JsonProperty(value="idCandidato")Long idCandidato, @JsonProperty(value="idLista")Long idLista, @JsonProperty(value="usuario")String usuario, @JsonProperty(value="fecha")Date fecha, @JsonProperty(value="estado")String estado) {
		super();
		this.id = id;
		this.idCandidato = idCandidato;
		this.idLista = idLista;
		this.usuario = usuario;
		this.fecha = fecha;
		this.estado = estado;
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
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
	
	
}
