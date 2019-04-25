package br.eximia.erm.model;

import java.util.Date;

import javax.persistence.AttributeOverride;
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
@AttributeOverride(name="id", column=@Column(name="IDFORNECEDOR_APROVADOR"))
@Table(name = "FORNECEDORES_APROVADORES")
public class FornecedorAprovador extends AbstractGenericEntity<FornecedorAprovador, Long> {
	
	private static final long serialVersionUID = 1L;
	
	@JoinColumn(name = "FORNECEDOR", referencedColumnName = "IDFORNECEDOR")
	@ManyToOne(optional = false, cascade=CascadeType.MERGE)
    @Valid
    private Fornecedor fornecedor;
	@JoinColumn(name = "USUARIO", referencedColumnName = "IDUSUARIO")
    @ManyToOne(optional = false, cascade=CascadeType.MERGE)
    @Valid
    private Usuario usuario;
	@Enumerated(EnumType.STRING)
	@Column(name = "STATUS")
    private StatusFornecedor status = StatusFornecedor.Pendente;
	@Column(name = "DATA")
    @Temporal(TemporalType.DATE)
    private Date data = new Date();

    public FornecedorAprovador() {
    }

	public FornecedorAprovador(Fornecedor fornecedor, Usuario usuario) {
		super();
		this.fornecedor = fornecedor;
		this.usuario = usuario;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public StatusFornecedor getStatus() {
		return status;
	}

	public void setStatus(StatusFornecedor status) {
		this.status = status;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	
	@Override
	public String getIdAsString() {
		return String.valueOf(this.hashCode());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 12;
		result = prime * result + ((fornecedor == null) ? 0 : fornecedor.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		return this.hashCode() == obj.hashCode();
	}

	@Override
    public String toString() {
        return this.getUsuario() + " - " + this.getStatus().getValue();
    }

	@Override
	public int compareTo(FornecedorAprovador o) {
		return this.getData().compareTo(o.getData());
	}

}
