package br.eximia.erm.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.eximia.erm.model.Cidade;
import br.eximia.erm.model.Estado;
import br.eximia.springutils.controller.crud.AbstractCrudController;

@Component
@Scope("session")
@Qualifier("cidadesController")
public class CidadesController extends AbstractCrudController<Cidade, Long> {
	
	public List<Estado> getEstados(){
		return Arrays.asList(Estado.class.getEnumConstants());
    }
	
}
