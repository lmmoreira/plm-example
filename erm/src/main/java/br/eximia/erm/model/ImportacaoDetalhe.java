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
@AttributeOverride(name="id", column=@Column(name="IDDETALHE"))
@Table(name = "IMPORTACOES_DETALHES")
public class ImportacaoDetalhe extends AbstractGenericEntity<ImportacaoDetalhe, Long> {

	private static final long serialVersionUID = 1L;
    
	@Column(name = "DATA")
	@Temporal(TemporalType.DATE)
	private Date data = new Date();
	@JoinColumn(name = "IMPORTACAO", referencedColumnName = "IDIMPORTACAO")
	@ManyToOne(optional = false, cascade=CascadeType.MERGE)
    @Valid
    private Importacao importacao;
	@Basic(optional = false)
    @Column(name = "MENSAGEM")
	private String mensagem;
	
	public Date getData() {
		return data;
	}
	
	public void setData(Date data) {
		this.data = data;
	}

	public Importacao getImportacao() {
		return importacao;
	}

	public void setImportacao(Importacao importacao) {
		this.importacao = importacao;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	@Override
    public String toString() {
        return this.getMensagem();
    }

	@Override
	public int compareTo(ImportacaoDetalhe o) {
		return this.getImportacao().compareTo(o.getImportacao());
	}

}
