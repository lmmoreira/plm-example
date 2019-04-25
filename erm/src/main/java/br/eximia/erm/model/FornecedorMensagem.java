
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
@Table(name = "FORNECEDORES_MENSAGENS")
public class FornecedorMensagem extends AbstractGenericEntity<FornecedorMensagem, Long> {
	
	private static final long serialVersionUID = 1L;
	
	@JoinColumn(name = "FORNECEDOR", referencedColumnName = "IDFORNECEDOR")
	@ManyToOne(optional = false, cascade=CascadeType.MERGE)
    @Valid
    private Fornecedor fornecedor;
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
    
    public FornecedorMensagem() {
    }

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
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
	public int compareTo(FornecedorMensagem arg0) {
		return this.getId().compareTo(arg0.getId());
	}
	
	public static FornecedorMensagem novaMensagem(Usuario origem, Usuario destino, String smensagem, Fornecedor fornecedor){
		FornecedorMensagem mensagem = new FornecedorMensagem();
		mensagem.setDataMensagem(new Date());
		mensagem.setDestino(destino);
		mensagem.setOrigem(origem);
		mensagem.setMensagem(smensagem);
		mensagem.setFornecedor(fornecedor);
		return mensagem;
	}

}
