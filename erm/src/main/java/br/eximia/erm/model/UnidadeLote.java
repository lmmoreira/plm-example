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
@AttributeOverride(name="id", column=@Column(name="IDUNIDADEL"))
@Table(name = "UNIDADES_LOTE")
public class UnidadeLote extends AbstractGenericEntity<UnidadeLote, Long> {
	
	private static final long serialVersionUID = 1L;
    
    @Basic(optional = false)
    @Column(name = "UNIDADE")
    @Size(max=20, message="{unidadeLote.unidade.size}")
    @NotNull(message="{unidadeLote.unidade.notNull}")
    @Pattern(regexp="^\\s*\\S.*$", message="{unidadeLote.unidade.notEmpty}")
    private String unidade;

    public UnidadeLote() {
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
	public int compareTo(UnidadeLote o) {
		return this.getUnidade().compareTo(o.getUnidade());
	}

}
