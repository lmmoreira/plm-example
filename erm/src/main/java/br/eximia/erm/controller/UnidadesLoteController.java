package br.eximia.erm.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.eximia.erm.model.UnidadeLote;
import br.eximia.springutils.controller.crud.AbstractCrudController;

@Component
@Scope("session")
@Qualifier("unidadesLoteController")
public class UnidadesLoteController extends AbstractCrudController<UnidadeLote, Long> {
	//
}
