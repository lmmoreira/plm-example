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
@AttributeOverride(name="id", column=@Column(name="IDTECNOLOGIA"))
@Table(name = "TECNOLOGIAS")
public class Tecnologia extends AbstractGenericEntity<Tecnologia, Long> {
	
	private static final long serialVersionUID = 1L;
    
    @Basic(optional = false)
    @Column(name = "TECNOLOGIA")
    @Size(max=20, message="{tecnologia.tecnologia.size}")
    @NotNull(message="{tecnologia.tecnologia.notNull}")
    @Pattern(regexp="^\\s*\\S.*$", message="{tecnologia.tecnologia.notEmpty}")
    private String tecnologia;

    public Tecnologia() {
    }

	public String getTecnologia() {
		return tecnologia;
	}

	public void setTecnologia(String tecnologia) {
		this.tecnologia = tecnologia;
	}
	
	public Boolean getMateriaPrima(){
		return ((this.getTecnologia().toUpperCase().indexOf("RAW") > -1) || (this.getTecnologia().toUpperCase().indexOf("CONSUMABLES") > -1));
	}

	@Override
    public String toString() {
        return this.getTecnologia();
    }

	@Override
	public int compareTo(Tecnologia o) {
		return this.getTecnologia().compareTo(o.getTecnologia());
	}

}
