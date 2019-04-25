package br.eximia.erm.service.interfaces;

import br.eximia.erm.model.Ata;
import br.eximia.springutils.data.DataService;

public interface AtaService extends DataService<Ata, Long> {
	
	Ata findByAta(String ata);
	
}
