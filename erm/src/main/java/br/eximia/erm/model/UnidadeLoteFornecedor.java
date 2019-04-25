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
@AttributeOverride(name="id", column=@Column(name="IDUNIDADELF"))
@Table(name = "UNIDADES_LOTE_FORNECEDOR")
public class UnidadeLoteFornecedor extends AbstractGenericEntity<UnidadeLoteFornecedor, Long> {
	
	private static final long serialVersionUID = 1L;
    
    @Basic(optional = false)
    @Column(name = "UNIDADE")
    @Size(max=20, message="{unidadeLoteFornecedor.unidade.size}")
    @NotNull(message="{unidadeLoteFornecedor.unidade.notNull}")
    @Pattern(regexp="^\\s*\\S.*$", message="{unidadeLoteFornecedor.unidade.notEmpty}")
    private String unidade;

    public UnidadeLoteFornecedor() {
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
	public int compareTo(UnidadeLoteFornecedor o) {
		return this.getUnidade().compareTo(o.getUnidade());
	}

}
