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
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.google.common.base.Strings;

import br.eximia.erm.model.validator.ValidProcessoEspecialList;
import br.eximia.erm.service.interfaces.LogService;
import br.eximia.erm.service.interfaces.MaterialService;
import br.eximia.erm.service.interfaces.RadicalService;
import br.eximia.erm.service.interfaces.UsuarioService;
import br.eximia.springutils.data.domain.AbstractGenericEntity;

@Entity
@AttributeOverride(name="id", column=@Column(name="IDMATERIAL"))
@Table(name = "MATERIAIS")
public class Material extends AbstractGenericEntity<Material, Long> {
	
	private static final long serialVersionUID = 1L;
    
	@Basic(optional = false)
    @Column(name = "NCODE")
	private Long ncode;
    @Basic(optional = false)
    @Column(name = "MATERIAL")
    @Size(max=200, message="{material.material.size}")
    private String material;
    @Basic(optional = true)
    @Column(name = "PN")
    @Size(max=30, message="{material.pn.size}")
    private String pn;
    @JoinColumn(name = "CATEGORIA", referencedColumnName = "IDCATEGORIA")
    @ManyToOne(optional = false, cascade=CascadeType.MERGE)
    @NotNull(message="{material.categoria.notNull}")
    @Valid
    private Categoria categoria;
    @Basic(optional = false)
    @Enumerated(EnumType.STRING)
    @Column(name="TIPO")
    @NotNull(message="{material.tipo.notNull}")
    private TipoMaterial tipo;
    @JoinColumn(name = "FORNECEDOR", referencedColumnName = "IDFORNECEDOR")
    @ManyToOne(optional = true, cascade=CascadeType.MERGE)
    @Valid
    private Fornecedor fornecedor;
    @Basic(optional = true)
    @Column(name = "ESPECIFICACAO")
    private String especificacao;
    @JoinTable(name = "MATERIAIS_PROCESSOS", joinColumns = {@JoinColumn(name = "MATERIAL", referencedColumnName = "IDMATERIAL")}, inverseJoinColumns = {@JoinColumn(name = "PROCESSO", referencedColumnName = "IDPROCESSO")})
    @ManyToMany(cascade=javax.persistence.CascadeType.MERGE, fetch= FetchType.LAZY)
    @Valid
    @ValidProcessoEspecialList(message = "{material.processo.three}")
    private List<ProcessoEspecial> processosEspeciais = new ArrayList<ProcessoEspecial>();
    @Basic(optional = false)
    @Column(name = "PESO")
    private Double peso;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "material", cascade= CascadeType.ALL, orphanRemoval=true)
    private List<MaterialAprovador> aprovadores = new ArrayList<MaterialAprovador>();
    @JoinColumn(name = "UN_FORNECEDOR", referencedColumnName = "IDUNIDADEF")
    @ManyToOne(optional = false, cascade=CascadeType.MERGE)
    @Valid
    private UnidadeFornecedor unidadeFornecedor;
    @Column(name = "LOTE")
    private Long lote;
    @JoinColumn(name = "UN_L_FORNECEDOR", referencedColumnName = "IDUNIDADELF")
    @ManyToOne(optional = false, cascade=CascadeType.MERGE)
    @Valid
    private UnidadeLoteFornecedor unidadeLoteFornecedor;
    @Enumerated(EnumType.STRING)
    @Column(name="CLASSE_COMPRA")
    private ClasseCompra classeCompra;
    @Column(name = "PRATELEIRA")
    private Boolean prateleira;
    @Column(name = "CALIBRACAO")
    private Long calibracao;
    @Column(name = "VIDA")
    private Long vida;
    @Column(name = "AMBIENTE")
    private Boolean ambienteControlado;
    @Column(name = "INDICACAO_DE")
    private Long indicacaoDe = 0L;
    @Column(name = "INDICACAO_A")
    private Long indicacaoA = 0L;
    @Column(name = "CICLOS")
    private Long ciclos;
    @Column(name = "LEAD")
    private Double lead;
    @Column(name = "MONTAGEM")
    private Double montagem;
    @Column(name = "LOTE_M")
    private Long loteM;
    @JoinColumn(name = "UN_L", referencedColumnName = "IDUNIDADEL")
    @ManyToOne(optional = false, cascade=CascadeType.MERGE)
    @Valid
    private UnidadeLote unidadeLote;
    @JoinColumn(name = "CRIADOR", referencedColumnName = "IDUSUARIO")
    @ManyToOne(optional = false, cascade=CascadeType.MERGE)
    @Valid
    @NotNull(message="{material.usuario.notNull}")
    private Usuario criador;
    @Column(name = "PN_FORNECEDOR")
    @Size(max=20, message="{material.pn.fornecedor.size}")
    private String pnFornecedor;
    @Column(name = "NCM")
    @Size(max=20, message="{material.ncm.size}")
    private String ncm;
    @Column(name = "PN_MATERIAL")
    @Size(max=20, message="{material.pnmat.size}")
    private String pnmat;
    @Column(name = "QTDE_MATERIAL")
    @Size(max=255, message="{material.qtdemat.size}")
    private String qtdeMat;
    @Column(name = "PUBLICADO")
    private Boolean publicado;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "material", cascade= CascadeType.ALL, orphanRemoval=true)
    private List<MaterialMensagem> mensagens = new ArrayList<MaterialMensagem>();
    @Column(name = "ATA")
    @Size(max=25, message="{material.ata.size}")
    private String ata;
    @Column(name = "SUB_ATA")
    @Size(max=25, message="{material.subAta.size}")
    private String subAta;
    @Column(name = "DENSIDADE")
    @Size(max=25, message="{material.densidade.size}")
    private String densidade;
    @Basic(optional = true)
    @Column(name = "DESCRICAO_PT")
    private String descricaoPT;
    @Basic(optional = true)
    @Column(name = "APLICACAO")
    private String aplicacao;
    @Basic(optional = true)
    @Column(name = "COMPOSICAO")
    private String composicao;
    @JoinColumn(name = "UNIDADE_ARMAZENAGEM", referencedColumnName = "IDUNIDADEF")
    @ManyToOne(optional = false, cascade=CascadeType.MERGE)
    @Valid
    private UnidadeFornecedor unidadeArmazenagem;
    @Basic(optional = false)
    @Column(name = "PESO_BRUTO")
    private Double pesoBruto;
    @Basic(optional = true)
    @Column(name = "NUMERO_PI")
    private String numeroPI;
    
    @JoinColumn(name = "NCODE_ALTERNATIVO", referencedColumnName = "IDMATERIAL")
    @ManyToOne(optional = true, cascade=CascadeType.MERGE)
    private Material ncodeAlternativo;
    
    @Basic(optional = true)
    @Column(name = "DESCRICAO_ALTERNATIVA")
    private String descricaoAlternativa;
    
    @Basic(optional = true)
    @Column(name = "PN_ALTERNATIVO")
    private String pnAlternativo;
    
    @Basic(optional = false)
    @Column(name = "LIBERADO")
    private Boolean liberadoSerie;
    
    @Basic(optional = false)
    @Column(name = "PROTOTIPO")
    private Boolean prototipo;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "material", cascade= CascadeType.ALL, orphanRemoval=true)
    private List<MaterialInformacaoAlternativa> informacoesAlternativas = new ArrayList<MaterialInformacaoAlternativa>();
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "material", cascade= CascadeType.ALL, orphanRemoval=true)
    private List<MaterialAprovadorCampo> aprovadoresCampos = new ArrayList<MaterialAprovadorCampo>();
    
    @JoinColumn(name = "PROGRAMA", referencedColumnName = "IDPROGRAMA")
    @ManyToOne(optional = false, cascade=CascadeType.MERGE)
    @Valid
    private Programa programa;
    
    @JoinColumn(name = "ATA_O", referencedColumnName = "IDATA")
    @ManyToOne(optional = false, cascade=CascadeType.MERGE)
    @Valid
    private Ata ataO;

    @JoinColumn(name = "SUB_ATA_O", referencedColumnName = "IDSUBATA")
    @ManyToOne(optional = false, cascade=CascadeType.MERGE)
    @Valid
    private SubAta subAtaO;
    
    @JoinColumn(name = "TRACO", referencedColumnName = "IDTRACO")
    @ManyToOne(optional = false, cascade=CascadeType.MERGE)
    @Valid
    private Traco traco;
    
    @Column(name = "RADICAL")
    private String radical;
    
    @Column(name = "ESPECULAR")
    private Boolean especular = false;
    
    @Transient
    Material pnEspecular;
    
    @Transient
    ProcessoEspecial processoEspecial1;
    
    @Transient
    ProcessoEspecial processoEspecial2;
    
    @Transient
    ProcessoEspecial processoEspecial3;
    
    public Material() {
    }
    
    @PrePersist
    protected void ncodeIncrement() {
    	Long dataNcode = ((MaterialService)this.getBean(MaterialService.class)).getMaxNCode();
        ncode = dataNcode == null ? 1 : dataNcode + 1;
    }
    
	public Long getNcode() {
		return ncode;
	}

	public void setNcode(Long ncode) {
		this.ncode = ncode;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}
	
	public ProcessoEspecial getProcessoEspecial1() {
		return processoEspecial1;
	}

	public void setProcessoEspecial1(ProcessoEspecial processoEspecial1) {
		this.processoEspecial1 = processoEspecial1;
	}

	public ProcessoEspecial getProcessoEspecial2() {
		return processoEspecial2;
	}

	public void setProcessoEspecial2(ProcessoEspecial processoEspecial2) {
		this.processoEspecial2 = processoEspecial2;
	}

	public ProcessoEspecial getProcessoEspecial3() {
		return processoEspecial3;
	}

	public void setProcessoEspecial3(ProcessoEspecial processoEspecial3) {
		this.processoEspecial3 = processoEspecial3;
	}

	public String getPn() {
		return pn;
	}

	public void setPn(String pn) {
		this.pn = pn;
	}

	public Material getPnEspecular() {
		return pnEspecular;
	}

	public void setPnEspecular(Material pnEspecular) {
		this.pnEspecular = pnEspecular;
	}

	public String getRadical() {
		return radical;
	}

	public void setRadical(String radical) {
		this.radical = radical;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public Boolean getLiberadoSerie() {
		return liberadoSerie;
	}
	
	public String getLabeledLiberadoSerie() {
		return liberadoSerie == null ? "" :  liberadoSerie ? this.getMessages().getMessage("boolean.true") : this.getMessages().getMessage("boolean.false");
	}

	public void setLiberadoSerie(Boolean liberadoSerie) {
		this.liberadoSerie = liberadoSerie;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public TipoMaterial getTipo() {
		return tipo;
	}

	public void setTipo(TipoMaterial tipo) {
		this.tipo = tipo;
	}

	public Boolean getPrototipo() {
		return prototipo;
	}
	
	public String getLabeledPrototipo() {
		return prototipo == null ? "" :  prototipo ? this.getMessages().getMessage("boolean.true") : this.getMessages().getMessage("boolean.false");
	}

	public void setPrototipo(Boolean prototipo) {
		this.prototipo = prototipo;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public String getEspecificacao() {
		return especificacao;
	}

	public void setEspecificacao(String especificacao) {
		this.especificacao = especificacao;
	}

	public List<ProcessoEspecial> getProcessosEspeciais() {
		return processosEspeciais;
	}

	public void setProcessosEspeciais(List<ProcessoEspecial> processosEspeciais) {
		this.processosEspeciais = processosEspeciais;
	}

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	public List<MaterialAprovador> getAprovadores() {
		return aprovadores;
	}

	public void setAprovadores(List<MaterialAprovador> aprovadores) {
		this.aprovadores = aprovadores;
	}

	public UnidadeFornecedor getUnidadeFornecedor() {
		return unidadeFornecedor;
	}

	public void setUnidadeFornecedor(UnidadeFornecedor unidadeFornecedor) {
		this.unidadeFornecedor = unidadeFornecedor;
	}

	public Long getLote() {
		return lote;
	}

	public void setLote(Long lote) {
		this.lote = lote;
	}

	public UnidadeLoteFornecedor getUnidadeLoteFornecedor() {
		return unidadeLoteFornecedor;
	}

	public void setUnidadeLoteFornecedor(UnidadeLoteFornecedor unidadeLoteFornecedor) {
		this.unidadeLoteFornecedor = unidadeLoteFornecedor;
	}

	public ClasseCompra getClasseCompra() {
		return classeCompra;
	}

	public void setClasseCompra(ClasseCompra classeCompra) {
		this.classeCompra = classeCompra;
	}

	public Boolean getPrateleira() {
		return prateleira;
	}
	
	public String getLabeledPrateleira() {
		return prateleira == null ? "" :  prateleira ? this.getMessages().getMessage("boolean.true") : this.getMessages().getMessage("boolean.false");
	}

	public void setPrateleira(Boolean prateleira) {
		this.prateleira = prateleira;
	}

	public Long getCalibracao() {
		return calibracao;
	}

	public void setCalibracao(Long calibracao) {
		this.calibracao = calibracao;
	}

	public Long getVida() {
		return vida;
	}

	public void setVida(Long vida) {
		this.vida = vida;
	}

	public Boolean getAmbienteControlado() {
		return ambienteControlado;
	}
	
	public String getLabeledAmbienteControlado() {
		return ambienteControlado == null ? "" :  ambienteControlado ? this.getMessages().getMessage("boolean.true") : this.getMessages().getMessage("boolean.false");
	}

	public void setAmbienteControlado(Boolean ambienteControlado) {
		this.ambienteControlado = ambienteControlado;
	}

	public Long getIndicacaoDe() {
		return indicacaoDe;
	}

	public void setIndicacaoDe(Long indicacaoDe) {
		this.indicacaoDe = indicacaoDe;
	}

	public Long getIndicacaoA() {
		return indicacaoA;
	}

	public void setIndicacaoA(Long indicacaoA) {
		this.indicacaoA = indicacaoA;
	}

	public Long getCiclos() {
		return ciclos;
	}

	public void setCiclos(Long ciclos) {
		this.ciclos = ciclos;
	}

	public Double getLead() {
		return lead;
	}

	public void setLead(Double lead) {
		this.lead = lead;
	}

	public Double getMontagem() {
		return montagem;
	}

	public void setMontagem(Double montagem) {
		this.montagem = montagem;
	}

	public Long getLoteM() {
		return loteM;
	}

	public void setLoteM(Long loteM) {
		this.loteM = loteM;
	}

	public UnidadeLote getUnidadeLote() {
		return unidadeLote;
	}

	public void setUnidadeLote(UnidadeLote unidadeLote) {
		this.unidadeLote = unidadeLote;
	}

	public Usuario getCriador() {
		return criador;
	}

	public void setCriador(Usuario criador) {
		this.criador = criador;
	}

	public String getPnFornecedor() {
		return pnFornecedor;
	}

	public void setPnFornecedor(String pnFornecedor) {
		this.pnFornecedor = pnFornecedor;
	}

	public String getNcm() {
		return ncm;
	}

	public void setNcm(String ncm) {
		this.ncm = ncm;
	}

	public String getPnmat() {
		return pnmat;
	}

	public void setPnmat(String pnmat) {
		this.pnmat = pnmat;
	}

	public String getQtdeMat() {
		return qtdeMat;
	}

	public void setQtdeMat(String qtdeMat) {
		this.qtdeMat = qtdeMat;
	}

	public Boolean getPublicado() {
		return publicado;
	}

	public void setPublicado(Boolean publicado) {
		this.publicado = publicado;
	}

	public List<MaterialMensagem> getMensagens() {
		return mensagens;
	}

	public void setMensagens(List<MaterialMensagem> mensagens) {
		this.mensagens = mensagens;
	}

	public String getAta() {
		return ata;
	}

	public void setAta(String ata) {
		this.ata = ata;
	}

	public String getSubAta() {
		return subAta;
	}

	public void setSubAta(String subAta) {
		this.subAta = subAta;
	}

	public String getDensidade() {
		return densidade;
	}

	public void setDensidade(String densidade) {
		this.densidade = densidade;
	}

	public String getDescricaoPT() {
		return descricaoPT;
	}

	public void setDescricaoPT(String descricaoPT) {
		this.descricaoPT = descricaoPT;
	}

	public String getAplicacao() {
		return aplicacao;
	}

	public void setAplicacao(String aplicacao) {
		this.aplicacao = aplicacao;
	}

	public String getComposicao() {
		return composicao;
	}

	public void setComposicao(String composicao) {
		this.composicao = composicao;
	}

	public UnidadeFornecedor getUnidadeArmazenagem() {
		return unidadeArmazenagem;
	}

	public void setUnidadeArmazenagem(UnidadeFornecedor unidadeArmazenagem) {
		this.unidadeArmazenagem = unidadeArmazenagem;
	}

	public Programa getPrograma() {
		return programa;
	}

	public void setPrograma(Programa programa) {
		this.programa = programa;
	}

	public Boolean getEspecular() {
		return especular;
	}

	public void setEspecular(Boolean especular) {
		this.especular = especular;
	}

	public Ata getAtaO() {
		return ataO;
	}

	public void setAtaO(Ata ataO) {
		this.ataO = ataO;
	}

	public SubAta getSubAtaO() {
		return subAtaO;
	}

	public void setSubAtaO(SubAta subAtaO) {
		this.subAtaO = subAtaO;
	}

	public Traco getTraco() {
		return traco;
	}

	public void setTraco(Traco traco) {
		this.traco = traco;
	}

	public Double getPesoBruto() {
		return pesoBruto;
	}

	public void setPesoBruto(Double pesoBruto) {
		this.pesoBruto = pesoBruto;
	}

	public String getNumeroPI() {
		return numeroPI;
	}

	public void setNumeroPI(String numeroPI) {
		this.numeroPI = numeroPI;
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

	public List<MaterialInformacaoAlternativa> getInformacoesAlternativas() {
		return informacoesAlternativas;
	}
	
	public String getInformacoesAlternativasAsString(){
		informacoesAlternativas.size();
		return informacoesAlternativas.toString();
	}

	public void setInformacoesAlternativas(List<MaterialInformacaoAlternativa> informacoesAlternativas) {
		this.informacoesAlternativas = informacoesAlternativas;
	}

	public List<MaterialAprovadorCampo> getAprovadoresCampos() {
		return aprovadoresCampos;
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
	
	public List<MaterialAprovadorCampo> getAprovadoresCamposCurrentUser() {
		List<MaterialAprovadorCampo> currentUser = new ArrayList<MaterialAprovadorCampo>();
		
		for(MaterialAprovadorCampo materialAprovadorCampo : this.aprovadoresCampos){
			if(materialAprovadorCampo.getUsuario().equals(this.getCurrentUser())){
				currentUser.add(materialAprovadorCampo);
			}
		}
		
		return currentUser;
	}
	
	public Boolean getCorrenteAprovadoresPendentesCampos(String campo) {
		Boolean visibility = false;
		
		for(MaterialAprovadorCampo materialAprovadorCampo : this.aprovadoresCampos){
			if(materialAprovadorCampo.getUsuario().equals(this.getCurrentUser()) && (materialAprovadorCampo.getStatus().equals(StatusMaterial.Pendente))){
				visibility = true;
			}
		}
		
		return visibility;
	}
	
	public List<MaterialAprovadorCampo> getAprovadoresCampos(String campo) {
		List<MaterialAprovadorCampo> campos = new ArrayList<MaterialAprovadorCampo>();
		
		for(MaterialAprovadorCampo materialAprovadorCampo : this.aprovadoresCampos){
			if(materialAprovadorCampo.eCampo(campo)){
				campos.add(materialAprovadorCampo);
			}
		}
		
		return campos;
	}

	public void setAprovadoresCampos(List<MaterialAprovadorCampo> aprovadoresCampos) {
		this.aprovadoresCampos = aprovadoresCampos;
	}

	@Override
	public int compareTo(Material o) {
		return this.getMaterial().compareTo(o.getMaterial());
	}
	
	public List<MaterialMensagem> getMensagensCurrentUser() {
		List<MaterialMensagem> currentUserMessages = new ArrayList<MaterialMensagem>();
				
		for(MaterialMensagem mensagem : this.getMensagens()){
			if(mensagem.getLido() || (!this.getCurrentUser().equals(mensagem.getDestino())))
				continue;
			currentUserMessages.add(mensagem);
		}
		
		return currentUserMessages;
	}
	
	public void lerMensagensCurrentUser() {
		for(MaterialMensagem mensagem : this.getMensagens()){
			if(mensagem.getLido() || (!this.getCurrentUser().equals(mensagem.getDestino())))
				continue;
			mensagem.setLido(true);
			mensagem.setDataLeitura(new Date());
		}
	}
	
	public List<Usuario> getAprovadoresAsUsers(){
		List<Usuario> usuarios = new ArrayList<Usuario>();
		for(MaterialAprovador materialAprovador : this.aprovadores){
			usuarios.add(materialAprovador.getUsuario());
		}
		return usuarios;
	}
	
	public List<Usuario> getAprovadoresCamposAsUsers(String campo){
		List<Usuario> usuarios = new ArrayList<Usuario>();
		for(MaterialAprovadorCampo materialAprovador : this.getAprovadoresCampos(campo)){
			usuarios.add(materialAprovador.getUsuario());
		}
		return usuarios;
	}
	
	public void aprove(Usuario usuario){
		for(MaterialAprovador materialAprovador : this.aprovadores){
			if((materialAprovador.getUsuario().equals(usuario))){
				materialAprovador.setStatus(StatusMaterial.Aprovado);
				materialAprovador.setData(new Date());
				break;
			}
		}
	}
	
	public void aproveCampos(Usuario usuario, MaterialCampo campo){
		for(MaterialAprovadorCampo materialAprovador : this.aprovadoresCampos){
			if((materialAprovador.getUsuario().equals(usuario)) && (materialAprovador.getCampo().equals(campo))){
				materialAprovador.setStatus(StatusMaterial.Aprovado);
				materialAprovador.setData(new Date());
				break;
			}
		}
	}
	
	public void reprove(Usuario usuario){
		for(MaterialAprovador materialAprovador : this.aprovadores){
			if((materialAprovador.getUsuario().equals(usuario))){
				materialAprovador.setStatus(StatusMaterial.Cancelado);
				materialAprovador.setData(new Date());
				break;
			}
		}
	}
	
	public void reproveCampos(Usuario usuario, MaterialCampo campo){
		for(MaterialAprovadorCampo materialAprovador : this.aprovadoresCampos){
			if((materialAprovador.getUsuario().equals(usuario)) && (materialAprovador.getCampo().equals(campo))){
				materialAprovador.setStatus(StatusMaterial.Cancelado);
				materialAprovador.setData(new Date());
				break;
			}
		}
	}
	
	public StatusMaterial getStatus(){
		if(this.getPendente())
			return StatusMaterial.Pendente;
		if(this.getAprovado())
			return StatusMaterial.Aprovado;
		if(this.getCancelado())
			return StatusMaterial.Cancelado;
		return null;
	}
	
	public void paraPendente(){
		for(MaterialAprovador materialAprovador : aprovadores){
			materialAprovador.setStatus(StatusMaterial.Pendente);
			materialAprovador.setData(new Date());
		}
		this.setPublicado(true);
	}
	
	public Boolean getPendente(){
		boolean pendente = false;
		for(MaterialAprovador materialAprovador : aprovadores){
			if(materialAprovador.getStatus().equals(StatusMaterial.Pendente)){
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
		for(MaterialAprovador materialAprovador : aprovadores){
			if(!materialAprovador.getStatus().equals(StatusMaterial.Aprovado)){
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
		for(MaterialAprovador materialAprovador : aprovadores){
			if(materialAprovador.getStatus().equals(StatusMaterial.Cancelado)){
				cancelado = true;
				break;
			}
		}
		
		if(this.aprovadores.isEmpty())
			cancelado = false;
		
		return cancelado;
	}
	
	public Boolean getDuplicatedPN(){
		if(this.getPn() != null)
			return ((((MaterialService) super.getBean(MaterialService.class)).findByPnAndNcodeNotLike(this.getPn(), this.getNcode() == null ? 0L : this.getNcode())).size() > 0);
		
		return false;
	}
	
	public Boolean getEditavel(){
		boolean pendentesSoPeloAutor = (this.getPendente()) && ((this.criador.equals(this.getCurrentUser())) || (this.getCurrentUser().getAdministrador()));
		boolean naoCancelados =  (!this.getCancelado());
		return (this.getPendente()) ? pendentesSoPeloAutor :  naoCancelados;
	}
	
	public Boolean getRemovivel(){
		boolean aprovadosSoPodemSerRemovidosPeloAdmin = (this.getAprovado() && this.getCurrentUser().getAdministradorGerenteProjeto());
		boolean pendentesSoPodemSerRemovidosPeloAdmin = (this.getPendente() && this.getCurrentUser().getAdministradorGerenteProjeto());
		return aprovadosSoPodemSerRemovidosPeloAdmin || pendentesSoPodemSerRemovidosPeloAdmin;
	}
	
	public Boolean getBuy(){
		return (this.getTipo() != null) && this.getTipo().equals(TipoMaterial.BUY);
	}
	
	public Boolean getMake(){
		return (this.getTipo() != null) && this.getTipo().equals(TipoMaterial.MAKE);
	}
	
	public Boolean getAprovable(){
		return (((this.aprovadores != null) && (!this.aprovadores.isEmpty()))&&(this.getPendente()) && (containsAprovador(this.getCurrentUser(), StatusMaterial.Pendente)));
	}
	
	private Boolean containsAprovador(Usuario usuario, StatusMaterial status){
		boolean contains = false;
		for(MaterialAprovador materialAprovador : this.aprovadores){
			if((materialAprovador.getUsuario().equals(usuario)) && (materialAprovador.getStatus().equals(status))){
				contains = true;
				break;
			}
		}
		return contains;
	}
	
	private Usuario getCurrentUser(){
		return ((UsuarioService) super.getBean(UsuarioService.class)).getLoggedUser();
	}
	
	public List<MaterialAprovador> getAtualMateriaisAprovadoresComTodosUsuarios(){
		List<Usuario> usuarios = ((UsuarioService) super.getBean(UsuarioService.class)).findByAprovador(true);
		List<MaterialAprovador> materiaisAprovadores = new ArrayList<MaterialAprovador>();
		Set<MaterialAprovador> entityMaterialAprovador = new HashSet<MaterialAprovador>(this.getAprovadores());
		
		for(Usuario usuario : usuarios){
			materiaisAprovadores.add(new MaterialAprovador(this, usuario));
		}
		
		entityMaterialAprovador.addAll(materiaisAprovadores);
		return new ArrayList<MaterialAprovador>(entityMaterialAprovador); 
	}
	
	public List<MaterialAprovadorCampo> getAtualMateriaisAprovadoresCamposComTodosUsuarios(String campo){
		List<Usuario> usuarios = ((UsuarioService) super.getBean(UsuarioService.class)).findByAprovador(true);
		List<MaterialAprovadorCampo> materiaisAprovadoresCampos = new ArrayList<MaterialAprovadorCampo>();
		Set<MaterialAprovadorCampo> entityMaterialAprovadorCampo = new HashSet<MaterialAprovadorCampo>(this.getAprovadoresCampos(campo));
		
		for(Usuario usuario : usuarios){
			materiaisAprovadoresCampos.add(new MaterialAprovadorCampo(this, usuario, MaterialCampo.valueOf(campo)));
		}
		
		entityMaterialAprovadorCampo.addAll(materiaisAprovadoresCampos);
		return new ArrayList<MaterialAprovadorCampo>(entityMaterialAprovadorCampo); 
	}
	
	public String getTargetInfo(){
		return this.getPn() + " - " + this.getMaterial();
	}
	
	@PreRemove
	public void onPreRemove() {
		Log log = new Log();
		log.setUsuario(this.getCurrentUser());
		log.setLocal(this.getClass().getSimpleName());
		log.setTarget(this.getTargetInfo());
		log.setAcao(LogAction.DELETE);
		((LogService) super.getBean(LogService.class)).save(log);
	}
	
	public void generateRadical() {
		if((this.getPrograma() != null ) &&  (this.getAtaO() != null) && (this.getSubAtaO() != null)){
			Long lRadical = ((RadicalService) this.getBean(RadicalService.class)).getNextRadical(programa, ataO, subAtaO, traco, especular, pnEspecular);
			this.radical = this.formatRadical(lRadical);
		}
	}
	
	public String formatRadical(Long radical) {
		return String.format("%04d", radical);
	}
	
	public String formataTraco(Traco traco){
		return traco.getTraco() + "0" + ((traco.getLado().equals(TracoLado.Esquerdo)) ? "1" : "2");
	}
	
	public String formataTraco(Integer traco){
		return String.format("%03d", traco);
	}
	
	public void gerarPN(Boolean notUpRadical, String sTraco){
		if((this.getPrograma() != null ) && (this.getAtaO() != null) && (this.getSubAtaO() != null) && (!Strings.isNullOrEmpty(this.getRadical())) && (this.getTraco() != null)){
			String traco = (sTraco == null) ? this.formataTraco(this.getTraco()) : sTraco;
			this.pn = this.getPrograma().getPrograma() + "-" + this.getAtaO().getAta() + "-" + this.getSubAtaO().getSubAta() + "-" + this.getRadical() + "-" +  traco;
			
			if(!notUpRadical){
				if(traco != null){
					if(!especular){
						((RadicalService) this.getBean(RadicalService.class)).plusOneToRadical(programa, ataO, subAtaO);
					}
				}
			}
		}
	}
	
	public void gerarPN(){
		this.gerarPN(false, null);
	}
	
	public Material evoluir(){
		this.newMaterial();
		this.gerarPN(true, this.evoluirTraco());
		return this;
	}
	
	public Material evoluirNotGen(){
		this.newMaterial();
		String traco = this.evoluirTraco();
		String pn = this.getPn().substring(0, this.getPn().length() - 3);
		this.setPn(pn + traco);
		return this;
	}
	
	public void newMaterial(){
		this.setId(null);
		
		for(MaterialMensagem mm : this.getMensagens()){
			mm.cleanId();
		}
		
		for(MaterialInformacaoAlternativa mia : this.getInformacoesAlternativas()){
			mia.cleanId();
		}
		
		for(MaterialAprovadorCampo mac : this.getAprovadoresCampos()){
			mac.cleanId();
		}
		
		for(MaterialAprovador ma : this.getAprovadores()){
			ma.cleanId();
		}
	}
	
	public Boolean getWasGenerated(){
		return ((this.getPrograma() != null ) && (this.getAtaO() != null) && (this.getSubAtaO() != null) && (!Strings.isNullOrEmpty(this.getRadical())) && (this.getTraco() != null));
	}
	
	public String getTracoFromPn(){
		return this.pn.split("-")[this.pn.split("-").length -1];
	}
	
	public String evoluirTraco(){
		return this.formataTraco((Integer.parseInt(this.getTracoFromPn()) + 2));
	}
	
	@Override
    public String toString() {
        return this.getMaterial();
    }

}
