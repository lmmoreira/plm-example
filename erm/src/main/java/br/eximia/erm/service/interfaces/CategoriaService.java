package br.eximia.erm.service.interfaces;

import br.eximia.erm.model.Categoria;
import br.eximia.springutils.data.DataService;

public interface CategoriaService extends DataService<Categoria, Long> {
	
	Categoria findByCategoria(String categoria);
	
}
