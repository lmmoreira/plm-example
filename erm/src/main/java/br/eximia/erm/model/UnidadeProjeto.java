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
@AttributeOverride(name="id", column=@Column(name="IDUNIDADEP"))
@Table(name = "UNIDADES_PROJETO")
public class UnidadeProjeto extends AbstractGenericEntity<UnidadeProjeto, Long> {
	
	private static final long serialVersionUID = 1L;
    
    @Basic(optional = false)
    @Column(name = "UNIDADE")
    @Size(max=20, message="{unidadeProjeto.unidade.size}")
    @NotNull(message="{unidadeProjeto.unidade.notNull}")
    @Pattern(regexp="^\\s*\\S.*$", message="{unidadeProjeto.unidade.notEmpty}")
    private String unidade;

    public UnidadeProjeto() {
    }

	public String getUnidade() {
		return unidade;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}

	@Override
    public String toString() {
        return this.getUnidade();
    }

	@Override
	public int compareTo(UnidadeProjeto o) {
		return this.getUnidade().compareTo(o.getUnidade());
	}

}
