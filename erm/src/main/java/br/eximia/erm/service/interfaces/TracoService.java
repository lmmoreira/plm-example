package br.eximia.erm.service.interfaces;

import br.eximia.erm.model.Traco;
import br.eximia.erm.model.TracoLado;
import br.eximia.springutils.data.DataService;

public interface TracoService extends DataService<Traco, Long> {
	
	Traco findByTraco(Integer traco);
	Traco findByTracoAndLado(Integer traco, TracoLado lado);
}
