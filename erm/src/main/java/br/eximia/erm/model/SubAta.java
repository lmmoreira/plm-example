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
@AttributeOverride(name="id", column=@Column(name="IDSUBATA"))
@Table(name = "SUB_ATAS")
public class SubAta extends AbstractGenericEntity<SubAta, Long> {
	
	private static final long serialVersionUID = 1L;
    
    @Basic(optional = false)
    @Column(name = "SUBATA")
    @Size(max=2, message="{subata.subata.size}")
    @NotNull(message="{subata.subata.notNull}")
    @Pattern(regexp="^\\s*\\S.*$", message="{subata.subata.notEmpty}")
    private String subAta;
    @Basic(optional = false)
    @Column(name = "DESCRICAO")
    @Size(max=200, message="{subata.descricao.size}")
    @NotNull(message="{subata.descricao.notNull}")
    @Pattern(regexp="^\\s*\\S.*$", message="{subata.descricao.notEmpty}")
    private String descricao;

    public SubAta() {
    }

	public String getSubAta() {
		return subAta;
	}

	public void setSubAta(String subAta) {
		this.subAta = subAta;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
    public String toString() {
        return this.getSubAta() + " - " + this.getDescricao();
    }

	@Override
	public int compareTo(SubAta o) {
		return this.getSubAta().compareTo(o.getSubAta());
	}

}
