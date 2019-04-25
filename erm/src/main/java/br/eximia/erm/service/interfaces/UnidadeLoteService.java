package br.eximia.erm.service.interfaces;

import br.eximia.erm.model.UnidadeLote;
import br.eximia.springutils.data.DataService;

public interface UnidadeLoteService extends DataService<UnidadeLote, Long> {
	
	UnidadeLote findByUnidade(String unidade);
	
}
