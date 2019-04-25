package br.eximia.erm.model;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.eclipse.persistence.annotations.CascadeOnDelete;

import br.eximia.springutils.data.domain.AbstractGenericEntity;

@Entity
@AttributeOverride(name="id", column=@Column(name="IDIMPORTACAO"))
@Table(name = "IMPORTACOES")
public class Importacao extends AbstractGenericEntity<Importacao, Long> {

	private static final long serialVersionUID = 1L;
    
	@Column(name = "DATA")
	@Temporal(TemporalType.DATE)
	private Date data = new Date();
	@Enumerated(EnumType.STRING)
	@Column(name = "TIPO")
    private TipoImportacao tipo;
	@Enumerated(EnumType.STRING)
	@Column(name = "STATUS")
    private StatusImportacao status;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "importacao", cascade= CascadeType.ALL, orphanRemoval=true)
	@CascadeOnDelete
	private List<ImportacaoDetalhe> detalhes = new ArrayList<ImportacaoDetalhe>();
	@Transient
	private InputStream fileStream;
	@Transient
	private List<?> items;
	
	public Importacao() {
		super();
	}
	
	public Importacao(TipoImportacao tipo, InputStream fileStream) {
		super();
		this.tipo = tipo;
		this.fileStream = fileStream;
	}

	public Date getData() {
		return data;
	}
	
	public void setData(Date data) {
		this.data = data;
	}
	
	public TipoImportacao getTipo() {
		return tipo;
	}
	
	public void setTipo(TipoImportacao tipo) {
		this.tipo = tipo;
	}
	
	public StatusImportacao getStatus() {
		return status;
	}

	public void setStatus(StatusImportacao status) {
		this.status = status;
	}

	public List<ImportacaoDetalhe> getDetalhes() {
		return detalhes;
	}

	public void setDetalhes(List<ImportacaoDetalhe> detalhes) {
		this.detalhes = detalhes;
	}

	public InputStream getFileStream() {
		return fileStream;
	}

	public void setFileStream(InputStream fileStream) {
		this.fileStream = fileStream;
	}

	public List<?> getItems() {
		return items;
	}

	public void setItems(List<?> items) {
		this.items = items;
	}

	@Override
    public String toString() {
        return this.getTipo() + " - " + this.getStatus();
    }

	@Override
	public int compareTo(Importacao o) {
		return this.getTipo().compareTo(o.getTipo());
	}

}
