package br.eximia.erm.model;

import javax.persistence.AttributeOverride;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import br.eximia.springutils.data.domain.AbstractGenericEntity;

@Entity
@AttributeOverride(name="id", column=@Column(name="IDTRACO"))
@Table(name = "TRACOS")
public class Traco extends AbstractGenericEntity<Traco, Long> {
	
	private static final long serialVersionUID = 1L;
    
    @Basic(optional = false)
    @Column(name = "TRACO")
    @NotNull(message="{traco.traco.notNull}")
    @Digits(fraction = 0, integer = 1, message="{traco.traco.digitos}")
    private Integer traco;
    @Basic(optional = false)
    @Column(name = "DESCRICAO")
    @Size(max=200, message="{traco.descricao.size}")
    @NotNull(message="{traco.descricao.notNull}")
    @Pattern(regexp="^\\s*\\S.*$", message="{traco.descricao.notEmpty}")
    private String descricao;
    @Enumerated(EnumType.STRING)
    @Column(name="LADO")
    @NotNull(message="{traco.lado.notNull}")
    private TracoLado lado;

    public Traco() {
    }

	public Integer getTraco() {
		return traco;
	}

	public void setTraco(Integer traco) {
		this.traco = traco;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
    public String toString() {
        return this.getTraco().toString() + " - " + this.getDescricao() + " - " + this.getLado().name();
    }

	public TracoLado getLado() {
		return lado;
	}

	public void setLado(TracoLado lado) {
		this.lado = lado;
	}

	@Override
	public int compareTo(Traco o) {
		return this.getTraco().compareTo(o.getTraco());
	}

}
