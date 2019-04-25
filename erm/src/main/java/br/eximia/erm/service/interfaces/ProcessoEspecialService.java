package br.eximia.erm.service.interfaces;

import br.eximia.erm.model.ProcessoEspecial;
import br.eximia.springutils.data.DataService;

public interface ProcessoEspecialService extends DataService<ProcessoEspecial, Long> {
	
	ProcessoEspecial findByProcesso(String processo);
	
	Long getMaxNCode();
	
}
