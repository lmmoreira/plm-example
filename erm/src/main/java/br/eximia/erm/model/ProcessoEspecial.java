package br.eximia.erm.model;

import javax.persistence.AttributeOverride;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import br.eximia.erm.service.interfaces.ProcessoEspecialService;
import br.eximia.erm.service.interfaces.UsuarioService;
import br.eximia.springutils.data.domain.AbstractGenericEntity;

@Entity
@AttributeOverride(name="id", column=@Column(name="IDPROCESSO"))
@Table(name = "PROCESSO_ESPECIAL")
public class ProcessoEspecial extends AbstractGenericEntity<ProcessoEspecial, Long> {
	
	private static final long serialVersionUID = 1L;
    
	@Basic(optional = false)
    @Column(name = "NCODE")
	private Long ncode;
    @Basic(optional = false)
    @Column(name = "PROCESSO")
    @Size(max=255, message="{processo.processo.size}")
    @NotNull(message="{processo.processo.notNull}")
    @Pattern(regexp="^\\s*\\S.*$", message="{processo.processo.notEmpty}")
    private String processo;
    @Basic(optional = false)
    @Column(name = "ESPECIFICACAO")
    @Size(max=255, message="{processo.especificacao.size}")
    @NotNull(message="{processo.especificacao.notNull}")
    @Pattern(regexp="^\\s*\\S.*$", message="{processo.especificacao.notEmpty}")
    private String especificacao;
    @Column(name = "TEXTO")
    @Size(max=500, message="{processo.texto.size}")
    @NotNull(message="{processo.texto.notNull}")
    private String texto;
    @JoinColumn(name = "CRIADOR", referencedColumnName = "IDUSUARIO")
    @ManyToOne(optional = false, cascade=CascadeType.MERGE)
    @Valid
    private Usuario criador;

    public ProcessoEspecial() {
    }
    
    @PrePersist
    protected void ncodeIncrement() {
    	Long dataNcode = ((ProcessoEspecialService)this.getBean(ProcessoEspecialService.class)).getMaxNCode();
        ncode = dataNcode == null ? 1 : dataNcode + 1;
    }
    
	public Long getNcode() {
		return ncode;
	}

	public void setNcode(Long ncode) {
		this.ncode = ncode;
	}

	public String getProcesso() {
		return processo;
	}

	public void setProcesso(String processo) {
		this.processo = processo;
	}

	public String getEspecificacao() {
		return especificacao;
	}

	public void setEspecificacao(String especificacao) {
		this.especificacao = especificacao;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}
	
	public String getNcodeAsString() {
		return this.getNcode().toString();
	}

	@Override
    public String toString() {
		if(this.isNew())
			return this.getProcesso();
		else 		
			return this.getNcodeAsString() + " - " + this.getProcesso();
    }

	@Override
	public int compareTo(ProcessoEspecial o) {
		return this.getProcesso().compareTo(o.getProcesso());
	}

	public Usuario getCriador() {
		return criador;
	}

	public void setCriador(Usuario criador) {
		if(this.criador == null)
			this.criador = criador;
	}
	
	private Usuario getCurrentUser(){
		return ((UsuarioService) super.getBean(UsuarioService.class)).getLoggedUser();
	}
	
	public Boolean getEditavelPeloCriador(){
		return (this.getCriador() != null) && (this.getCriador().equals(this.getCurrentUser()));
	}

}
