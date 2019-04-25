package br.eximia.erm.controller;

import java.util.Arrays;
import java.util.List;

import javax.faces.event.ComponentSystemEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Strings;

import br.eximia.erm.model.Ata;
import br.eximia.erm.model.Categoria;
import br.eximia.erm.model.ClasseCompra;
import br.eximia.erm.model.Fornecedor;
import br.eximia.erm.model.Material;
import br.eximia.erm.model.MaterialAprovador;
import br.eximia.erm.model.MaterialCampo;
import br.eximia.erm.model.MaterialInformacaoAlternativa;
import br.eximia.erm.model.MaterialMensagem;
import br.eximia.erm.model.MaterialMensagemVo;
import br.eximia.erm.model.ProcessoEspecial;
import br.eximia.erm.model.Programa;
import br.eximia.erm.model.StatusMaterial;
import br.eximia.erm.model.SubAta;
import br.eximia.erm.model.TipoMaterial;
import br.eximia.erm.model.Traco;
import br.eximia.erm.model.UnidadeFornecedor;
import br.eximia.erm.model.UnidadeLote;
import br.eximia.erm.model.UnidadeLoteFornecedor;
import br.eximia.erm.model.Usuario;
import br.eximia.erm.model.lazy.MaterialLazyModel;
import br.eximia.erm.service.DefaultMateriaisSearchFactory;
import br.eximia.erm.service.interfaces.AtaService;
import br.eximia.erm.service.interfaces.CategoriaService;
import br.eximia.erm.service.interfaces.FornecedorService;
import br.eximia.erm.service.interfaces.MaterialService;
import br.eximia.erm.service.interfaces.ProcessoEspecialService;
import br.eximia.erm.service.interfaces.ProgramaService;
import br.eximia.erm.service.interfaces.SubAtaService;
import br.eximia.erm.service.interfaces.TracoService;
import br.eximia.erm.service.interfaces.UnidadeFornecedorService;
import br.eximia.erm.service.interfaces.UnidadeLoteFornecedorService;
import br.eximia.erm.service.interfaces.UnidadeLoteService;
import br.eximia.erm.service.interfaces.UsuarioService;
import br.eximia.jsfutils.UrlUtils;
import br.eximia.springutils.controller.crud.AbstractLazyCrudController;

@Component
@Scope("session")
@Qualifier("materiaisController")
public class MateriaisController extends AbstractLazyCrudController<Material, Long> {

	CategoriaService defaultCategoriaService;
	ProgramaService defaultProgramaService;
	AtaService defaultAtaService;
	SubAtaService defaultSubAtaService;
	TracoService defaultTracoService;
	FornecedorService defaultFornecedorService;
	ProcessoEspecialService defaultProcessoEspecialService;
	UsuarioService defaultUsuarioService;
	UnidadeFornecedorService defaultUnidadeFornecedorService;
	UnidadeLoteFornecedorService defaultUnidadeLoteFornecedorService;
	UnidadeLoteService defaultUnidadeLoteService;
	MaterialService defaultMaterialService;
	DefaultMateriaisSearchFactory defaultMateriaisSearchFactory;
	
	MaterialMensagemVo materialMensagem = new MaterialMensagemVo();
	
	MaterialInformacaoAlternativa materialInformacaoAlternativa = new MaterialInformacaoAlternativa();
	
	Boolean bParaPendente = false;

	@Autowired
	public MateriaisController(
			CategoriaService defaultCategoriaService,
			ProgramaService defaultProgramaService,
			AtaService defaultAtaService,
			SubAtaService defaultSubAtaService,
			TracoService defaultTracoService,
			FornecedorService defaultFornecedorService,
			ProcessoEspecialService defaultProcessoEspecialService, 
			UsuarioService defaultUsuarioService,
			UnidadeFornecedorService defaultUnidadeFornecedorService,
			UnidadeLoteFornecedorService defaultUnidadeLoteFornecedorService,
			UnidadeLoteService defaultUnidadeLoteService,
			MaterialService defaultMaterialService,
			DefaultMateriaisSearchFactory defaultMateriaisSearchFactory) {
		super();
		this.defaultCategoriaService = defaultCategoriaService;
		this.defaultProgramaService = defaultProgramaService;
		this.defaultAtaService = defaultAtaService;
		this.defaultSubAtaService = defaultSubAtaService;
		this.defaultTracoService = defaultTracoService;
		this.defaultFornecedorService = defaultFornecedorService;
		this.defaultUsuarioService = defaultUsuarioService;
		this.defaultProcessoEspecialService = defaultProcessoEspecialService;
		this.defaultUnidadeFornecedorService = defaultUnidadeFornecedorService;
		this.defaultUnidadeLoteFornecedorService = defaultUnidadeLoteFornecedorService;
		this.defaultUnidadeLoteService = defaultUnidadeLoteService; 
		this.defaultMaterialService = defaultMaterialService;
		this.defaultMateriaisSearchFactory = defaultMateriaisSearchFactory;
	}

	public MaterialMensagemVo getMaterialMensagem() {
		return materialMensagem;
	}

	public void setMaterialMensagem(MaterialMensagemVo materialMensagem) {
		this.materialMensagem = materialMensagem;
	}

	public List<Categoria> getCategorias() {
		return defaultCategoriaService.findAll();
	}
	
	public List<Programa> getProgramas() {
		return defaultProgramaService.findAll();
	}
	
	public List<Ata> getAtas() {
		return defaultAtaService.findAll();
	}
	
	public List<SubAta> getSubAtas() {
		return defaultSubAtaService.findAll();
	}
	
	public List<Traco> getTracos() {
		return defaultTracoService.findAll();
	}
	
	public List<Usuario> getUsuarios() {
		return defaultUsuarioService.findAll();
	}

	public List<Fornecedor> getFornecedores() {
		return defaultFornecedorService.findAll();
	}

	public List<ProcessoEspecial> getProcessos() {
		return defaultProcessoEspecialService.findAll();
	}
	
	public List<UnidadeFornecedor> getUnidadesFornecedor() {
		return defaultUnidadeFornecedorService.findAll();
	}
	
	public List<UnidadeFornecedor> getUnidadesArmazenagem() {
		return defaultUnidadeFornecedorService.findByUnidadesArmazenagem();
	}
	
	public List<UnidadeLoteFornecedor> getUnidadesLotesFornecedor() {
		return defaultUnidadeLoteFornecedorService.findAll();
	}
	
	public List<Material> getMateriaisComplete(String query) {
		List<Material> materialList = defaultMaterialService.findByNcodeLike(query + "%");
		return materialList.subList(0, materialList.size() >= 10 ? 10 : materialList.size());
	}
	
	public List<Material> getMateriaisCompletePn(String query) {
		List<Material> materialList = defaultMaterialService.findByPnLike(query + "%");
		return materialList.subList(0, materialList.size() >= 10 ? 10 : materialList.size());
	}
	
	public List<UnidadeLote> getUnidadesLote() {
		return defaultUnidadeLoteService.findAll();
	}
	
	public List<ClasseCompra> getClassesCompra(){
		return Arrays.asList(ClasseCompra.class.getEnumConstants());
    }

	public List<TipoMaterial> getTipos() {
		return Arrays.asList(TipoMaterial.class.getEnumConstants());
	}
	
	public MaterialInformacaoAlternativa getMaterialInformacaoAlternativa() {
		return materialInformacaoAlternativa;
	}

	public void setMaterialInformacaoAlternativa(MaterialInformacaoAlternativa materialInformacaoAlternativa) {
		this.materialInformacaoAlternativa = materialInformacaoAlternativa;
	}
	
	public void reinitInformacao() {
		this.materialInformacaoAlternativa = new MaterialInformacaoAlternativa();
	}

	public void aprove(){
		this.getEntity().aprove(defaultUsuarioService.getLoggedUser());
		super.save();
	}
	
	public void aproveCampos(MaterialCampo campo){
		this.getEntity().aproveCampos(defaultUsuarioService.getLoggedUser(), campo);
		super.save();
	}

	public void reproveCampos(MaterialCampo campo){
		this.getEntity().reproveCampos(defaultUsuarioService.getLoggedUser(), campo);
		super.save();
	}

	public void reprove(){
		this.getEntity().reprove(defaultUsuarioService.getLoggedUser());
		super.save();
	}
	
	public void evoluir(){
		this.defaultMaterialService.save(this.getEntity().evoluir());
		super.save();
	}
	
	public void evoluirNotGen(){
		this.defaultMaterialService.save(this.getEntity().evoluirNotGen());
		super.save();
	}
	
	@Override
	public void save() {
		if(this.getEntity().isNew())
			this.getEntity().setCriador(defaultUsuarioService.getLoggedUser());
		
		defaultMaterialService.save(this.getEntity());
		super.reset();
	}
	
	@Override
	public void pageLoad(ComponentSystemEvent event) {
		if((!Strings.isNullOrEmpty(UrlUtils.getUrlParam("status")))){
			this.setList(new MaterialLazyModel(StatusMaterial.valueOf(UrlUtils.getUrlParam("status"))));
		}
	}
	
	public void lerMensagens(){
		this.getEntity().lerMensagensCurrentUser();
		this.defaultMaterialService.save(this.getEntity());
	}
	
	public void newMessage(){
		this.setMaterialMensagem(new MaterialMensagemVo());
	}
	
	public void sendMessageRework(){
		MaterialMensagem mensagem = MaterialMensagem.novaMensagem(defaultUsuarioService.getLoggedUser(), 
									this.getEntity().getCriador(), 
									this.getMaterialMensagem().getMensagem(), 
									this.getEntity());
		this.getEntity().getMensagens().add(mensagem);
		this.getEntity().setPublicado(false);
		this.defaultMaterialService.save(this.getEntity());
		this.newMessage();
	}
	
	public void sendMessage(){
		for(MaterialAprovador aprovador : this.getMaterialMensagem().getPara()){
			MaterialMensagem mensagem = MaterialMensagem.novaMensagem(defaultUsuarioService.getLoggedUser(), 
					aprovador.getUsuario(), 
					this.getMaterialMensagem().getMensagem(), 
					this.getEntity());
			this.getEntity().getMensagens().add(mensagem);
		}
		
		this.defaultMaterialService.save(this.getEntity());
		this.newMessage();
	}
	
	@Override
	public void reset() {
		bParaPendente = false;
		super.reset();
	}
	
	public void paraPendente(){
		bParaPendente = true;
	}

	public Boolean getbParaPendente() {
		return bParaPendente;
	}

	public void setbParaPendente(Boolean bParaPendente) {
		this.bParaPendente = bParaPendente;
	}

}

