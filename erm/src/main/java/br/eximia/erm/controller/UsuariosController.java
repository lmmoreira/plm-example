package br.eximia.erm.controller;

import java.util.ArrayList;
import java.util.List;

import org.primefaces.model.DualListModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.eximia.erm.model.Regra;
import br.eximia.erm.model.Usuario;
import br.eximia.erm.service.interfaces.RegraService;
import br.eximia.erm.service.interfaces.UsuarioService;
import br.eximia.springutils.controller.crud.AbstractCrudController;

@Component
@Scope("session")
@Qualifier("usuariosController")
public class UsuariosController extends AbstractCrudController<Usuario, Long> {
	
	RegraService defaultRegraService;
	UsuarioService defaultUsuarioService;
	
	DualListModel<Regra> regras;
    
    @Autowired
    public UsuariosController(RegraService defaultRegraService, UsuarioService defaultUsuarioService) {
    	this.defaultRegraService = defaultRegraService;
    	this.defaultUsuarioService = defaultUsuarioService;
	}
    
    public List<Regra> getSource() {
    	List<Regra> source = defaultRegraService.findAll();
    	source.removeAll(this.getTarget());
    	return source;
	}
	
    public List<Regra> getTarget() {
    	List<Regra> target = (this.isNew()) ? new ArrayList<Regra>() : defaultUsuarioService.findById(super.getEntity().getId()).getRegras();
		return target;
	}
	
	public DualListModel<Regra> getRegras() {
		if(regras == null)
			regras = new DualListModel<Regra>(this.getSource(), this.getTarget());
		
		return regras;
	}
	
	public void setRegras(DualListModel<Regra> regras) {
		this.regras = regras;
	}

	@Override
	public void reset() {
		super.reset();
		this.regras = null;
	}
	
	@Override
	public void save() {
		this.getEntity().setRegras(this.getRegras().getTarget());
		super.save();
	}
	
}
