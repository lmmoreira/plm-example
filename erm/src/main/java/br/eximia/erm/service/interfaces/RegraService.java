package br.eximia.erm.service.interfaces;

import br.eximia.erm.model.Regra;
import br.eximia.springutils.data.DataService;

public interface RegraService extends DataService<Regra, Long> {
	
	Regra findByRegra(String regra);
	
}
