
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

import br.eximia.springutils.data.domain.AbstractGenericEntity;

@Entity
@AttributeOverride(name="id", column=@Column(name="IDINFORMACAO"))
@Table(name = "MATERIAIS_INFORMACOES")
public class MaterialInformacaoAlternativa extends AbstractGenericEntity<MaterialInformacaoAlternativa, Long> {
	
	private static final long serialVersionUID = 1L;
	
	@JoinColumn(name = "MATERIAL", referencedColumnName = "IDMATERIAL")
	@ManyToOne(optional = false, cascade=CascadeType.MERGE)
    @Valid
    private Material material;
	@JoinColumn(name = "NCODE_ALTERNATIVO", referencedColumnName = "IDMATERIAL")
    @ManyToOne(optional = true, cascade=CascadeType.MERGE)
    private Material ncodeAlternativo;
	
	@Basic(optional = true)
	@Column(name = "DESCRICAO_ALTERNATIVA")
	private String descricaoAlternativa;
	    
	@Basic(optional = true)
	@Column(name = "PN_ALTERNATIVO")
	private String pnAlternativo;
	
    
    public MaterialInformacaoAlternativa() {
    }

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}
	
	public void configurarMaterial(Material material){
		this.material = material;
	}

	public Material getNcodeAlternativo() {
		return ncodeAlternativo;
	}

	public void setNcodeAlternativo(Material ncodeAlternativo) {
		this.ncodeAlternativo = ncodeAlternativo;
		
		if(ncodeAlternativo != null){
			this.descricaoAlternativa = ncodeAlternativo.getMaterial();
			this.pnAlternativo = ncodeAlternativo.getPn();
		} else {
			this.descricaoAlternativa = "";
			this.pnAlternativo = "";
		}
	}

	public String getDescricaoAlternativa() {
		return descricaoAlternativa;
	}

	public void setDescricaoAlternativa(String descricaoAlternativa) {
		this.descricaoAlternativa = descricaoAlternativa;
	}

	public String getPnAlternativo() {
		return pnAlternativo;
	}

	public void setPnAlternativo(String pnAlternativo) {
		this.pnAlternativo = pnAlternativo;
	}

	@Override
	public int compareTo(MaterialInformacaoAlternativa arg0) {
		return this.getId().compareTo(arg0.getId());
	}

	@Override
	public String toString() {
		return  ncodeAlternativo.getNcode().toString() + " - " + pnAlternativo + " - " + descricaoAlternativa;
	}
}
