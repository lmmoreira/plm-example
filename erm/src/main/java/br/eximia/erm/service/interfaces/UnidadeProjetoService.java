package br.eximia.erm.service.interfaces;

import br.eximia.erm.model.UnidadeProjeto;
import br.eximia.springutils.data.DataService;

public interface UnidadeProjetoService extends DataService<UnidadeProjeto, Long> {
	
	UnidadeProjeto findByUnidade(String unidade);
	
}
