package br.eximia.erm.service.interfaces;

import br.eximia.erm.model.Programa;
import br.eximia.springutils.data.DataService;

public interface ProgramaService extends DataService<Programa, Long> {
	
	Programa findByPrograma(String programa);
	
}
