package br.eximia.erm.model;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;

import br.eximia.springutils.data.domain.AbstractGenericEntity;

@Entity
@AttributeOverride(name="id", column=@Column(name="IDMATERIAL_APROVADOR"))
@Table(name = "MATERIAIS_APROVADORES_CAMPOS")
public class MaterialAprovadorCampo extends AbstractGenericEntity<MaterialAprovadorCampo, Long> {
	
	private static final long serialVersionUID = 1L;
	
	@JoinColumn(name = "MATERIAL", referencedColumnName = "IDMATERIAL")
	@ManyToOne(optional = false, cascade=CascadeType.MERGE)
    @Valid
    private Material material;
	@JoinColumn(name = "USUARIO", referencedColumnName = "IDUSUARIO")
	@ManyToOne(optional = false, cascade=CascadeType.MERGE)
    @Valid
    private Usuario usuario;
	@Enumerated(EnumType.STRING)
	@Column(name = "STATUS")
    private StatusMaterial status = StatusMaterial.Pendente;
	@Column(name = "DATA")
    @Temporal(TemporalType.DATE)
    private Date data = new Date();
	@Enumerated(EnumType.STRING)
	@Column(name = "CAMPO")
    private MaterialCampo campo;

    public MaterialAprovadorCampo() {
    }

	public MaterialAprovadorCampo(Material material, Usuario usuario, MaterialCampo campo) {
		super();
		this.material = material;
		this.usuario = usuario;
		this.campo = campo;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public StatusMaterial getStatus() {
		return status;
	}

	public void setStatus(StatusMaterial status) {
		this.status = status;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	
	public MaterialCampo getCampo() {
		return campo;
	}

	public void setCampo(MaterialCampo campo) {
		this.campo = campo;
	}

	@Override
	public String getIdAsString() {
		return String.valueOf(this.hashCode());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 12;
		result = prime * result + ((material == null) ? 0 : material.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		return this.hashCode() == obj.hashCode();
	}

	@Override
    public String toString() {
        return this.getMaterial() + " - " + this.getUsuario() + " - " + this.getStatus();
    }

	@Override
	public int compareTo(MaterialAprovadorCampo o) {
		return this.getData().compareTo(o.getData());
	}
	
	public boolean eCampo(String campo){
		return this.campo.toString().replace("enums.materialCampo.", "").equalsIgnoreCase(campo);
	}

}
