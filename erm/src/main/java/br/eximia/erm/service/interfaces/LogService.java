package br.eximia.erm.service.interfaces;


import java.util.List;

import br.eximia.erm.model.Log;
import br.eximia.springutils.data.DataService;

public interface LogService extends DataService<Log, Long> {

	List<Log> findByLocal(String local);
	
}
