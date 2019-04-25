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
@AttributeOverride(name="id", column=@Column(name="IDUNIDADEF"))
@Table(name = "UNIDADES_FORNECEDOR")
public class UnidadeFornecedor extends AbstractGenericEntity<UnidadeFornecedor, Long> {
	
	private static final long serialVersionUID = 1L;
    
    @Basic(optional = false)
    @Column(name = "UNIDADE")
    @Size(max=20, message="{unidadeFornecedor.unidade.size}")
    @NotNull(message="{unidadeFornecedor.unidade.notNull}")
    @Pattern(regexp="^\\s*\\S.*$", message="{unidadeFornecedor.unidade.notEmpty}")
    private String unidade;

    public UnidadeFornecedor() {
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
	public int compareTo(UnidadeFornecedor o) {
		return this.getUnidade().compareTo(o.getUnidade());
	}

}
