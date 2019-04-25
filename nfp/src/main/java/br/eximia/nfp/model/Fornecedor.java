package br.eximia.nfp.model;

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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import br.eximia.springutils.data.domain.AbstractGenericEntity;

@Entity
@AttributeOverride(name="id", column=@Column(name="IDFORNECEDOR"))
@Table(name = "FORNECEDORES")
public class Fornecedor extends AbstractGenericEntity<Fornecedor, Long> {
	
	private static final long serialVersionUID = 1L;
    
    @Basic(optional = false)
    @Column(name = "FORNECEDOR")
    @Size(max=255, message="{fornecedor.fornecedor.size}")
    @NotNull(message="{fornecedor.fornecedor.notNull}")
    @Pattern(regexp="^\\s*\\S.*$", message="{fornecedor.fornecedor.notEmpty}")
    private String fornecedor;
    @Basic(optional = false)
    @Column(name = "CNPJ")
    @Size(max=255, message="{fornecedor.cnpj.size}")
    @NotNull(message="{fornecedor.cnpj.notNull}")
    private String cnpj;
    @Basic(optional = false)
    @Column(name = "CONTATO")
    @Size(max=255, message="{fornecedor.contato.size}")
    @NotNull(message="{fornecedor.contato.notNull}")
    @Pattern(regexp="^\\s*\\S.*$", message="{fornecedor.contato.notEmpty}")
    private String contato;
    @Basic(optional = false)
    @Column(name = "ENDERECO")
    @Size(max=255, message="{fornecedor.endereco.size}")
    @NotNull(message="{fornecedor.endereco.notNull}")
    @Pattern(regexp="^\\s*\\S.*$", message="{fornecedor.endereco.notEmpty}")
    private String endereco;
    @Basic(optional = false)
    @Column(name = "TELEFONE")
    @Size(max=255, message="{fornecedor.telefone.size}")
    @NotNull(message="{fornecedor.telefone.notNull}")
    private String telefone;
    @Column(name = "EMAIL")
    @Size(max=255, message="{fornecedor.email.size}")
    @NotNull(message="{fornecedor.email.notNull}")
    @Pattern(regexp=".+@.+\\..+", message="{fornecedor.email.notValid}")
    private String email;
    @Column(name = "DATA_CADASTRO")
    @Basic(optional = false)
    @Temporal(TemporalType.DATE)
    private Date dataCadastro = new Date();
    @JoinColumn(name = "CRIADOR", referencedColumnName = "IDUSUARIO")
    @ManyToOne(optional = false, cascade=CascadeType.MERGE)
    @Valid
    private Usuario criador;
    
    public Fornecedor() {
    }

	public String getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(String fornecedor) {
		this.fornecedor = fornecedor;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	@Override
    public String toString() {
        return this.getFornecedor();
    }

	@Override
	public int compareTo(Fornecedor o) {
		return this.getFornecedor().compareTo(o.getFornecedor());
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	
	public Usuario getCriador() {
		return criador;
	}

	public void setCriador(Usuario criador) {
		if(this.criador == null)
			this.criador = criador;
	}

}
