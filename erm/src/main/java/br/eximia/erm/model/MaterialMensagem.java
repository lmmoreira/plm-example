
package br.eximia.erm.model;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;

import br.eximia.springutils.data.domain.AbstractGenericEntity;

@Entity
@AttributeOverride(name="id", column=@Column(name="IDMENSAGEM"))
@Table(name = "MATERIAIS_MENSAGENS")
public class MaterialMensagem extends AbstractGenericEntity<MaterialMensagem, Long> {
	
	private static final long serialVersionUID = 1L;
	
	@JoinColumn(name = "MATERIAL", referencedColumnName = "IDMATERIAL")
	@ManyToOne(optional = false, cascade=CascadeType.MERGE)
    @Valid
    private Material material;
	@JoinColumn(name = "ORIGEM", referencedColumnName = "IDUSUARIO")
	@ManyToOne(optional = false, cascade=CascadeType.MERGE)
    @Valid
    private Usuario origem;
	@JoinColumn(name = "DESTINO", referencedColumnName = "IDUSUARIO")
	@ManyToOne(optional = false, cascade=CascadeType.MERGE)
    @Valid
    private Usuario destino;
	@Column(name = "DATA_MENSAGEM")
    @Basic(optional = false)
    @Temporal(TemporalType.DATE)
    private Date dataMensagem = new Date();
	@Column(name = "DATA_LEITURA")
    @Basic(optional = false)
    @Temporal(TemporalType.DATE)
    private Date dataLeitura;
	@Column(name = "LIDO")
    private Boolean lido;
	@Column(name = "MENSAGEM")
    private String mensagem;
    
    public MaterialMensagem() {
    }

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public Usuario getOrigem() {
		return origem;
	}

	public void setOrigem(Usuario origem) {
		this.origem = origem;
	}

	public Usuario getDestino() {
		return destino;
	}

	public void setDestino(Usuario destino) {
		this.destino = destino;
	}

	public Date getDataMensagem() {
		return dataMensagem;
	}

	public void setDataMensagem(Date dataMensagem) {
		this.dataMensagem = dataMensagem;
	}

	public Date getDataLeitura() {
		return dataLeitura;
	}

	public void setDataLeitura(Date dataLeitura) {
		this.dataLeitura = dataLeitura;
	}

	public Boolean getLido() {
		
		if(lido == null)
			return false;
		
		return lido;
	}

	public void setLido(Boolean lido) {
		this.lido = lido;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	@Override
	public int compareTo(MaterialMensagem arg0) {
		return this.getId().compareTo(arg0.getId());
	}
	
	public static MaterialMensagem novaMensagem(Usuario origem, Usuario destino, String smensagem, Material material){
		MaterialMensagem mensagem = new MaterialMensagem();
		mensagem.setDataMensagem(new Date());
		mensagem.setDestino(destino);
		mensagem.setOrigem(origem);
		mensagem.setMensagem(smensagem);
		mensagem.setMaterial(material);
		return mensagem;
	}

}
