package br.eximia.erm.model;

import javax.persistence.AttributeOverride;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import br.eximia.springutils.data.domain.AbstractGenericEntity;

@Entity
@AttributeOverride(name="id", column=@Column(name="IDPROGRAMA"))
@Table(name = "PROGRAMAS")
public class Programa extends AbstractGenericEntity<Programa, Long> {
	
	private static final long serialVersionUID = 1L;
    
    @Basic(optional = false)
    @Column(name = "PROGRAMA")
    @Size(max=10, message="{programa.programa.size}")
    @NotNull(message="{programa.programa.notNull}")
    @Pattern(regexp="^\\s*\\S.*$", message="{programa.programa.notEmpty}")
    private String programa;
    @Basic(optional = false)
    @Column(name = "DESCRICAO")
    @Size(max=200, message="{programa.descricao.size}")
    @NotNull(message="{programa.descricao.notNull}")
    @Pattern(regexp="^\\s*\\S.*$", message="{programa.descricao.notEmpty}")
    private String descricao;

    public Programa() {
    }
	
	public String getPrograma() {
		return programa;
	}

	public void setPrograma(String programa) {
		this.programa = programa;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
    public String toString() {
        return this.getPrograma() + " - " + this.getDescricao();
    }

	@Override
	public int compareTo(Programa o) {
		return this.getPrograma().compareTo(o.getPrograma());
	}

}
