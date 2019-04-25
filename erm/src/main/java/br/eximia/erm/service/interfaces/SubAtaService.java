package br.eximia.erm.service.interfaces;

import br.eximia.erm.model.SubAta;
import br.eximia.springutils.data.DataService;

public interface SubAtaService extends DataService<SubAta, Long> {
	
	SubAta findBySubAta(String subAta);
	
}
