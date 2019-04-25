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
@AttributeOverride(name="id", column=@Column(name="IDATA"))
@Table(name = "ATAS")
public class Ata extends AbstractGenericEntity<Ata, Long> {
	
	private static final long serialVersionUID = 1L;
    
    @Basic(optional = false)
    @Column(name = "ATA")
    @Size(max=2, message="{ata.ata.size}")
    @NotNull(message="{ata.ata.notNull}")
    @Pattern(regexp="^\\s*\\S.*$", message="{ata.ata.notEmpty}")
    private String ata;
    @Basic(optional = false)
    @Column(name = "DESCRICAO")
    @Size(max=200, message="{ata.descricao.size}")
    @NotNull(message="{ata.descricao.notNull}")
    @Pattern(regexp="^\\s*\\S.*$", message="{ata.descricao.notEmpty}")
    private String descricao;

    public Ata() {
    }

	public String getAta() {
		return ata;
	}

	public void setAta(String ata) {
		this.ata = ata;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
    public String toString() {
        return this.getAta() + " - " + this.getDescricao();
    }

	@Override
	public int compareTo(Ata o) {
		return this.getAta().compareTo(o.getAta());
	}

}
