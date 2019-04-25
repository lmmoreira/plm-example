package br.eximia.erm.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import br.eximia.springutils.data.domain.AbstractGenericEntity;

@Entity
@AttributeOverride(name="id", column=@Column(name="IDUSUARIO"))
@Table(name = "USUARIOS")
public class Usuario extends AbstractGenericEntity<Usuario, Long> {

	private static final long serialVersionUID = 1L;
	
    @Basic(optional = false)
    @Column(name = "USUARIO")
    @Size(max=20, message="{usuario.usuario.size}")
    @NotNull(message="{usuario.usuario.notNull}")
    @Pattern(regexp="^\\s*\\S.*$", message="{usuario.usuario.notEmpty}")
    private String usuario;
    @Basic(optional = false)
    @Column(name = "NOME")
    @Size(max=50, message="{usuario.nome.size}")
    @NotNull(message="{usuario.nome.notNull}")
    @Pattern(regexp="^\\s*\\S.*$", message="{usuario.nome.notEmpty}")
    private String nome;
    @Basic(optional = false)
    @Column(name = "EMAIL")
    @Size(max=50, message="{usuario.email.size}")
    @NotNull(message="{usuario.email.notNull}")
    @Pattern(regexp=".+@.+\\..+", message="{usuario.email.notValid}")
    private String email;
    @Basic(optional = false)
    @Column(name = "SENHA")
    @NotNull(message="{usuario.senha.notNull}")
    @Pattern(regexp="^\\s*\\S.*$", message="{usuario.senha.notEmpty}")
    private String senha;
    @Basic(optional = false)
    @Column(name = "STATUS")
    private Boolean status;
    @Basic(optional = false)
    @Column(name = "APROVADOR")
    private Boolean aprovador;
    @ManyToMany(cascade=CascadeType.DETACH)
    @JoinTable(name = "USUARIOS_REGRAS", joinColumns = {@JoinColumn(name="USUARIO")}, inverseJoinColumns = {@JoinColumn(name="REGRA")})
    private List<Regra> regras = new ArrayList<Regra>();

    public Usuario() {
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Boolean getStatus() {
		return status;
	}
	
	public String getLabeledStatus() {
		return status == null ? this.getMessages().getMessage("boolean.false") :  status ? this.getMessages().getMessage("boolean.true") : this.getMessages().getMessage("boolean.false");
	}
	
	public String getLabeledStatusAproval() {
		return aprovador == null ? this.getMessages().getMessage("boolean.false") :  aprovador ? this.getMessages().getMessage("boolean.true") : this.getMessages().getMessage("boolean.false");
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getAprovador() {
		return aprovador;
	}

	public void setAprovador(Boolean aprovador) {
		this.aprovador = aprovador;
	}

	@Override
    public String toString() {
        return this.getUsuario();
    }

	public int compareTo(Usuario o) {
		return this.getUsuario().compareTo(o.getUsuario());
	}

	public List<Regra> getRegras() {
		return regras;
	}
	
	public Boolean getAdministrador(){
		return  this.hasRegra(this.getMessages().getMessage("perfil.administrador"));
	}
	
	public Boolean getAdministradorGerenteProjeto(){
		return  this.hasRegra(this.getMessages().getMessage("perfil.administrador")) || this.hasRegra(this.getMessages().getMessage("perfil.gp"));
	}
	
	public boolean getProjeto(){
		return this.hasRegra(this.getMessages().getMessage("perfil.projetos"));
	}
	
	public boolean getQualidade(){
		return this.hasRegra(this.getMessages().getMessage("perfil.qualidade"));
	}
	
	public boolean getManufatura(){
		return this.hasRegra(this.getMessages().getMessage("perfil.manufatura"));
	}
	
	public boolean getCompras(){
		return this.hasRegra(this.getMessages().getMessage("perfil.compras"));
	}
	
	public boolean hasRegra(String sRegra){
		boolean has = false;
		for(Regra regra : this.getRegras()){
			if(regra.getRegra().equals(sRegra)){
				has = true;
				break;
			}
		}
		return has;
	}

	public void setRegras(List<Regra> regras) {
		this.regras = regras;
	}
	
	public String getRegrasString(){
		return regras.toString();
	}
	  
}
