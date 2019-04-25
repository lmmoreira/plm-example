package br.eximia.erm.model;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;

import br.eximia.springutils.data.domain.AbstractGenericEntity;

@Entity
@AttributeOverride(name = "id", column = @Column(name = "IDLOG"))
@Table(name = "LOGS")
public class Log extends AbstractGenericEntity<Log, Long> {

	private static final long serialVersionUID = 1L;

	@JoinColumn(name = "USUARIO", referencedColumnName = "IDUSUARIO")
	@ManyToOne(optional = false, cascade = CascadeType.MERGE)
	@Valid
	private Usuario usuario;
	
	@Basic(optional = false)
	@Column(name = "LOCAL")
	private String local;
	
	@Basic(optional = false)
	@Enumerated(EnumType.STRING)
	@Column(name = "ACAO")
	private LogAction acao;
	
	@Basic(optional = false)
	@Column(name = "TARGET")
	private String target;
	
	@Column(name = "DATA")
	@Basic(optional = false)
	@Temporal(TemporalType.DATE)
	private Date data = new Date();

	public Log() {
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public LogAction getAcao() {
		return acao;
	}

	public void setAcao(LogAction acao) {
		this.acao = acao;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	@Override
	public String toString() {
		return this.getUsuario() + " - " + this.getLocal() + " - " + this.getAcao() + " - " + this.getTarget();
	}

	@Override
	public int compareTo(Log o) {
		return this.getData().compareTo(o.getData());
	}

}
