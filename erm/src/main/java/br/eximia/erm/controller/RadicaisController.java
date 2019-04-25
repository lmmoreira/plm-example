package br.eximia.erm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.eximia.erm.model.Ata;
import br.eximia.erm.model.Programa;
import br.eximia.erm.model.Radical;
import br.eximia.erm.model.SubAta;
import br.eximia.erm.service.interfaces.AtaService;
import br.eximia.erm.service.interfaces.ProgramaService;
import br.eximia.erm.service.interfaces.SubAtaService;
import br.eximia.springutils.controller.crud.AbstractCrudController;

@Component
@Scope("session")
@Qualifier("radicaisController")
public class RadicaisController extends AbstractCrudController<Radical, Long> {
	
	ProgramaService defaultProgramaService;
	AtaService defaultAtaService;
	SubAtaService defaultSubAtaService;
	
	@Autowired
	public RadicaisController(ProgramaService defaultProgramaService, AtaService defaultAtaService, SubAtaService defaultSubAtaService) {
		super();
		this.defaultProgramaService = defaultProgramaService;
		this.defaultAtaService = defaultAtaService;
		this.defaultSubAtaService = defaultSubAtaService;
	}

	public List<Ata> getAtas(){
		return defaultAtaService.findAll();
    }
	
	public List<Programa> getProgramas(){
		return defaultProgramaService.findAll();
    }
	
	public List<SubAta> getSubAtas(){
		return defaultSubAtaService.findAll();
    }
	
}
