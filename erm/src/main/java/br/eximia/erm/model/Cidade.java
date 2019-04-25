package br.eximia.erm.model;

import javax.persistence.AttributeOverride;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import br.eximia.springutils.data.domain.AbstractGenericEntity;

@Entity
@AttributeOverride(name="id", column=@Column(name="IDCIDADE"))
@Table(name = "CIDADES")
public class Cidade extends AbstractGenericEntity<Cidade, Long> {
	
	private static final long serialVersionUID = 1L;
    
    @Basic(optional = false)
    @Column(name = "CIDADE")
    @Size(max=20, message="{cidade.cidade.size}")
    @NotNull(message="{cidade.cidade.notNull}")
    @Pattern(regexp="^\\s*\\S.*$", message="{cidade.cidade.notEmpty}")
    private String cidade;
    @Basic(optional = false)
    @Enumerated(EnumType.STRING)
    @Column(name="ESTADO")
    @NotNull(message="{cidade.estado.notNull}")
    private Estado estado;

    public Cidade() {
    }

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	@Override
    public String toString() {
        return this.getCidade();
    }

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	@Override
	public int compareTo(Cidade o) {
		return this.getCidade().compareTo(o.getCidade());
	}

}
