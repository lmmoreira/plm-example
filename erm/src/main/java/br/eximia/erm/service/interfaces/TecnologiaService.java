package br.eximia.erm.service.interfaces;

import br.eximia.erm.model.Tecnologia;
import br.eximia.springutils.data.DataService;

public interface TecnologiaService extends DataService<Tecnologia, Long> {
	
	Tecnologia findByTecnologia(String tecnologia);
	
}
