package br.eximia.erm.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.eximia.erm.model.Traco;
import br.eximia.erm.model.TracoLado;
import br.eximia.springutils.controller.crud.AbstractCrudController;

@Component
@Scope("session")
@Qualifier("tracosController")
public class TracosController extends AbstractCrudController<Traco, Long> {
	
	public List<TracoLado> getTracosLados(){
		return Arrays.asList(TracoLado.class.getEnumConstants());
    }
	
}
