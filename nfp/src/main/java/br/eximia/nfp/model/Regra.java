package br.eximia.nfp.model;

import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import br.eximia.springutils.data.domain.AbstractGenericEntity;

@Entity
@AttributeOverride(name="id", column=@Column(name="IDREGRA"))
@Table(name = "REGRAS")
public class Regra extends AbstractGenericEntity<Regra, Long> {
	
	private static final long serialVersionUID = 1L;
	
    @Basic(optional = false)
    @Column(name = "REGRA")
    private String regra;
    @ManyToMany(mappedBy="regras", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    private List<Usuario> usuarios;

    public Regra() {
    }

	public String getRegra() {
		return regra;
	}

	public void setRegra(String regra) {
		this.regra = regra;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	public boolean isAdministrador(){
		return this.getRegra().equals(this.getMessages().getMessage("perfil.administrador"));
	}
	
	public boolean isProjeto(){
		return this.getRegra().equals(this.getMessages().getMessage("perfil.projetos"));
	}
	
	public boolean isQualidade(){
		return this.getRegra().equals(this.getMessages().getMessage("perfil.qualidade"));
	}
	
	public boolean isManufatura(){
		return this.getRegra().equals(this.getMessages().getMessage("perfil.manufatura"));
	}
	
	public boolean isCompras(){
		return this.getRegra().equals(this.getMessages().getMessage("perfil.compras"));
	}

	@Override
    public String toString() {
        return this.getRegra();
    }

	@Override
	public int compareTo(Regra o) {
		return this.getRegra().compareTo(o.getRegra());
	}

}
