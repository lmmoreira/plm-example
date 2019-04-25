package br.eximia.erm.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import br.eximia.erm.service.interfaces.UsuarioService;
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
    @Column(name = "DATA_REAVALIACAO")
    @Basic(optional = false)
    @Temporal(TemporalType.DATE)
    private Date dataReavaliacao = new Date();
    @Column(name = "PRODUTO")
    @Size(max=500, message="{fornecedor.produto.size}")
    @NotNull(message="{fornecedor.produto.notNull}")
    private String produto;
    @Column(name = "IQF")
    private Double iqf = 0.0;
    @Column(name = "IQPF")
    private Double iqpf = 0.0;
    @Column(name = "IDE")
    private Double ide = 0.0;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "fornecedor", cascade= CascadeType.ALL, orphanRemoval=true)
    private List<FornecedorAprovador> aprovadores = new ArrayList<FornecedorAprovador>();
    @JoinColumn(name = "CRIADOR", referencedColumnName = "IDUSUARIO")
    @ManyToOne(optional = false, cascade=CascadeType.MERGE)
    @Valid
    private Usuario criador;
    @Column(name = "PUBLICADO")
    private Boolean publicado;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "fornecedor", cascade= CascadeType.ALL, orphanRemoval=true)
    private List<FornecedorMensagem> mensagens = new ArrayList<FornecedorMensagem>();
    
    
    
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

	public Date getDataReavaliacao() {
		return dataReavaliacao;
	}

	public void setDataReavaliacao(Date dataReavaliacao) {
		this.dataReavaliacao = dataReavaliacao;
	}

	public String getProduto() {
		return produto;
	}

	public void setProduto(String produto) {
		this.produto = produto;
	}

	public Double getIqf() {
		return iqf;
	}

	public void setIqf(Double iqf) {
		this.iqf = iqf;
	}

	public Double getIqpf() {
		return iqpf;
	}

	public void setIqpf(Double iqpf) {
		this.iqpf = iqpf;
	}

	public Double getIde() {
		return ide = 0.4 * this.getIqf() + 0.6 * this.getIqpf();
	}

	public void setIde(Double ide) {
		this.ide = ide;
	}
	
	public List<FornecedorAprovador> getAprovadores() {
		return aprovadores;
	}
	
	public String getAprovadoresAsString() {
		return aprovadores.toString();
	}

	public void setAprovadores(List<FornecedorAprovador> aprovadores) {
		this.aprovadores = aprovadores;
	}
	
	public Usuario getCriador() {
		return criador;
	}

	public void setCriador(Usuario criador) {
		if(this.criador == null)
			this.criador = criador;
	}
	
	public Boolean getPublicado() {
		return publicado;
	}

	public void setPublicado(Boolean publicado) {
		this.publicado = publicado;
	}

	public List<FornecedorMensagem> getMensagens() {
		return mensagens;
	}

	public void setMensagens(List<FornecedorMensagem> mensagens) {
		this.mensagens = mensagens;
	}
	
	public List<FornecedorMensagem> getMensagensCurrentUser() {
		List<FornecedorMensagem> currentUserMessages = new ArrayList<FornecedorMensagem>();
				
		for(FornecedorMensagem mensagem : this.getMensagens()){
			if(mensagem.getLido() || (!this.getCurrentUser().equals(mensagem.getDestino())))
				continue;
			currentUserMessages.add(mensagem);
		}
		
		return currentUserMessages;
	}
	
	public void lerMensagensCurrentUser() {
		for(FornecedorMensagem mensagem : this.getMensagens()){
			if(mensagem.getLido() || (!this.getCurrentUser().equals(mensagem.getDestino())))
				continue;
			mensagem.setLido(true);
			mensagem.setDataLeitura(new Date());
		}
	}

	public List<Usuario> getAprovadoresAsUsers(){
		List<Usuario> usuarios = new ArrayList<Usuario>();
		for(FornecedorAprovador fornecedorAprovador : this.aprovadores){
			usuarios.add(fornecedorAprovador.getUsuario());
		}
		return usuarios;
	}
	
	public void aprove(Usuario usuario){
		for(FornecedorAprovador fornecedorAprovador : this.aprovadores){
			if((fornecedorAprovador.getUsuario().equals(usuario))){
				fornecedorAprovador.setStatus(StatusFornecedor.Aprovado);
				fornecedorAprovador.setData(new Date());
				break;
			}
		}
	}
	
	public void reprove(Usuario usuario){
		for(FornecedorAprovador fornecedorAprovador : this.aprovadores){
			if((fornecedorAprovador.getUsuario().equals(usuario))){
				fornecedorAprovador.setStatus(StatusFornecedor.Cancelado);
				fornecedorAprovador.setData(new Date());
				break;
			}
		}
	}
	
	public void topendente(){
		for(FornecedorAprovador fornecedorAprovador : this.aprovadores){
			fornecedorAprovador.setStatus(StatusFornecedor.Pendente);
			fornecedorAprovador.setData(new Date());
			break;
		}
	}
	
	public StatusFornecedor getStatus(){
		if(this.getPendente())
			return StatusFornecedor.Pendente;
		if(this.getAprovado())
			return StatusFornecedor.Aprovado;
		if(this.getCancelado())
			return StatusFornecedor.Cancelado;
		return null;
	}
	
	public Boolean getVencido(){
		return this.getDataReavaliacao().before(new Date());
	}
	
	public Boolean getPendente(){
		boolean pendente = false;
		for(FornecedorAprovador fornecedorAprovador : aprovadores){
			if(fornecedorAprovador.getStatus().equals(StatusFornecedor.Pendente)){
				pendente = true;
				break;
			}
		}
		
		if(this.aprovadores.isEmpty())
			pendente = true;
		
		return pendente;
	}
	
	public Boolean getAprovado(){
		boolean aprovado = true;
		for(FornecedorAprovador fornecedorAprovador : aprovadores){
			if(!fornecedorAprovador.getStatus().equals(StatusFornecedor.Aprovado)){
				aprovado = false;
				break;
			}
		}
		
		if(this.aprovadores.isEmpty())
			aprovado = false;
		
		return aprovado;
	}
	
	public Boolean getCancelado(){
		boolean cancelado = false;
		for(FornecedorAprovador fornecedorAprovador : aprovadores){
			if(fornecedorAprovador.getStatus().equals(StatusFornecedor.Cancelado)){
				cancelado = true;
				break;
			}
		}
		
		if(this.aprovadores.isEmpty())
			cancelado = false;
		
		return cancelado;
	}
	
	public Boolean getAprovable(){
		return (((this.aprovadores != null) && (!this.aprovadores.isEmpty()))&&(this.getPendente()) && (containsAprovador(this.getCurrentUser(), StatusFornecedor.Pendente)));
	}
	
	private Boolean containsAprovador(Usuario usuario, StatusFornecedor status){
		boolean contains = false;
		for(FornecedorAprovador fornecedorAprovador : this.aprovadores){
			if((fornecedorAprovador.getUsuario().equals(usuario)) && (fornecedorAprovador.getStatus().equals(status))){
				contains = true;
				break;
			}
		}
		return contains;
	}
	
	private Usuario getCurrentUser(){
		return ((UsuarioService) super.getBean(UsuarioService.class)).getLoggedUser();
	}
	
	public Boolean getEditavel(){
		boolean pendentesSoPeloAutor = (this.getPendente()) && ((this.criador.equals(this.getCurrentUser())) || (this.getCurrentUser().getAdministrador()));
		boolean naoCancelados =  (!this.getCancelado());
		return (this.getPendente()) ? pendentesSoPeloAutor : naoCancelados;
	}
	
	public List<FornecedorAprovador> getAtualFornecedoresAprovadoresComTodosUsuarios(){
		List<Usuario> usuarios = ((UsuarioService) super.getBean(UsuarioService.class)).findByAprovador(true);
		List<FornecedorAprovador> fornecedoresAprovadores = new ArrayList<FornecedorAprovador>();
		Set<FornecedorAprovador> entityFornecedorAprovador = new HashSet<FornecedorAprovador>(this.getAprovadores());
		
		for(Usuario usuario : usuarios){
			fornecedoresAprovadores.add(new FornecedorAprovador(this, usuario));
		}
		
		entityFornecedorAprovador.addAll(fornecedoresAprovadores);
		return new ArrayList<FornecedorAprovador>(entityFornecedorAprovador); 
	}

}
