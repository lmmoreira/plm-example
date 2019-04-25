package br.eximia.erm.service.interfaces;

import br.eximia.erm.model.Cidade;
import br.eximia.springutils.data.DataService;

public interface CidadeService extends DataService<Cidade, Long> {
	
	Cidade findByCidade(String cidade);
	
}
