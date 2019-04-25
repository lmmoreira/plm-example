package br.eximia.erm.model;

import javax.persistence.AttributeOverride;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import br.eximia.springutils.data.domain.AbstractGenericEntity;

@Entity
@AttributeOverride(name="id", column=@Column(name="IDCATEGORIA"))
@Table(name = "CATEGORIAS")
public class Categoria extends AbstractGenericEntity<Categoria, Long> {
	
	private static final long serialVersionUID = 1L;
    
    @Basic(optional = false)
    @Column(name = "CATEGORIA")
    @Size(max=20, message="{categoria.categoria.size}")
    @NotNull(message="{categoria.categoria.notNull}")
    @Pattern(regexp="^\\s*\\S.*$", message="{categoria.categoria.notEmpty}")
    private String categoria;
    @JoinColumn(name = "TECNOLOGIA", referencedColumnName = "IDTECNOLOGIA")
    @ManyToOne(optional = false, cascade=CascadeType.MERGE)
    @NotNull(message="{categoria.tecnologia.notNull}")
    @Valid
    private Tecnologia tecnologia;

    public Categoria() {
    }

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	public Tecnologia getTecnologia() {
		return tecnologia;
	}

	public void setTecnologia(Tecnologia tecnologia) {
		this.tecnologia = tecnologia;
	}

	@Override
    public String toString() {
        return this.getCategoria();
    }

	@Override
	public int compareTo(Categoria o) {
		return this.getCategoria().compareTo(o.getCategoria());
	}

}
