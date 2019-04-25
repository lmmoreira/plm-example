package br.eximia.erm.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.eximia.erm.model.Fornecedor;
import br.eximia.erm.model.FornecedorAprovador;
import br.eximia.erm.model.FornecedorMensagem;
import br.eximia.erm.model.FornecedorMensagemVo;
import br.eximia.erm.model.StatusFornecedor;
import br.eximia.erm.model.Usuario;
import br.eximia.erm.service.DefaultFornecedoresSearchFactory;
import br.eximia.erm.service.interfaces.FornecedorService;
import br.eximia.erm.service.interfaces.UsuarioService;
import br.eximia.jsfutils.UrlUtils;
import br.eximia.springutils.controller.crud.AbstractCrudController;

import com.google.common.base.Strings;

@Component
@Scope("session")
@Qualifier("fornecedoresController")
public class FornecedoresController extends AbstractCrudController<Fornecedor, Long> {

	UsuarioService defaultUsuarioService;
	FornecedorService defaultFornecedorService;
	DefaultFornecedoresSearchFactory defaultFornecedoresSearchFactory;
	
    FornecedorMensagemVo fornecedorMensagem = new FornecedorMensagemVo();
	
	@Autowired
	public FornecedoresController(UsuarioService defaultUsuarioService, FornecedorService defaultFornecedorService, DefaultFornecedoresSearchFactory defaultFornecedoresSearchFactory){
		this.defaultUsuarioService = defaultUsuarioService;
		this.defaultFornecedorService = defaultFornecedorService;
		this.defaultFornecedoresSearchFactory = defaultFornecedoresSearchFactory;
	}
	
	public FornecedorMensagemVo getFornecedorMensagem() {
		return fornecedorMensagem;
	}

	public void setFornecedorMensagem(FornecedorMensagemVo fornecedorMensagem) {
		this.fornecedorMensagem = fornecedorMensagem;
	}

	public List<StatusFornecedor> getStatusFornecedor(){
		return Arrays.asList(StatusFornecedor.class.getEnumConstants());
    }
	
	public List<Usuario> getUsuarios() {
		return defaultUsuarioService.findAll();
	}
	
	public void aprove(){
		this.getEntity().aprove(defaultUsuarioService.getLoggedUser());
		super.save();
	}
	
	public void reprove(){
		this.getEntity().reprove(defaultUsuarioService.getLoggedUser());
		super.save();
	}
	
	public void topendente(){
		this.getEntity().topendente();
		super.save();
	}
	
	@Override
	public List<Fornecedor> getList() {
		if((!Strings.isNullOrEmpty(UrlUtils.getUrlParam("status")))){
			this.setList(defaultFornecedoresSearchFactory.getInstance(StatusFornecedor.valueOf(UrlUtils.getUrlParam("status"))).getFornecedores());
		}
		return super.getList();
	}
	
	@Override
	public void save() {
		this.getEntity().setCriador(defaultUsuarioService.getLoggedUser());
		super.save();
	}
	
	public void lerMensagens(){
		this.getEntity().lerMensagensCurrentUser();
		this.defaultFornecedorService.save(this.getEntity());
	}
	
	public void newMessage(){
		this.setFornecedorMensagem(new FornecedorMensagemVo());
	}
	
	public void sendMessageRework(){
		FornecedorMensagem mensagem = FornecedorMensagem.novaMensagem(defaultUsuarioService.getLoggedUser(), 
									this.getEntity().getCriador(), 
									this.getFornecedorMensagem().getMensagem(), 
									this.getEntity());
		this.getEntity().getMensagens().add(mensagem);
		this.getEntity().setPublicado(false);
		this.defaultFornecedorService.save(this.getEntity());
		this.newMessage();
	}
	
	public void sendMessage(){
		for(FornecedorAprovador aprovador : this.getFornecedorMensagem().getPara()){
			FornecedorMensagem mensagem = FornecedorMensagem.novaMensagem(defaultUsuarioService.getLoggedUser(), 
					aprovador.getUsuario(), 
					this.getFornecedorMensagem().getMensagem(), 
					this.getEntity());
			this.getEntity().getMensagens().add(mensagem);
		}
		
		this.defaultFornecedorService.save(this.getEntity());
		this.newMessage();
	}
	
}
