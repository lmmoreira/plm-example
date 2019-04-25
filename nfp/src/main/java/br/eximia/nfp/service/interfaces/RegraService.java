package br.eximia.nfp.service.interfaces;

import br.eximia.nfp.model.Regra;
import br.eximia.springutils.data.DataService;

public interface RegraService extends DataService<Regra, Long> {
	
	Regra findByRegra(String regra);
	
}
