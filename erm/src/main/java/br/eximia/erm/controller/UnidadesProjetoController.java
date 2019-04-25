package br.eximia.erm.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.eximia.erm.model.UnidadeProjeto;
import br.eximia.springutils.controller.crud.AbstractCrudController;

@Component
@Scope("session")
@Qualifier("unidadesProjetoController")
public class UnidadesProjetoController extends AbstractCrudController<UnidadeProjeto, Long> {
	//
}
