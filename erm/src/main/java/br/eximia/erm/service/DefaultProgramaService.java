package br.eximia.erm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.eximia.erm.dao.ProgramaDao;
import br.eximia.erm.model.Programa;
import br.eximia.erm.service.interfaces.ProgramaService;
import br.eximia.springutils.data.AbstractDataService;

@Service("defaultProgramaService")
public class DefaultProgramaService extends AbstractDataService<Programa, Long> implements ProgramaService {
	
	ProgramaDao programaDao;

	@Autowired
	public DefaultProgramaService(ProgramaDao programaDao) {
		super();
		this.programaDao = programaDao;
	}
	
	@Override
	@Transactional(readOnly=true)
	public Programa findByPrograma(String programa) {
		return programaDao.findByPrograma(programa);
	}

	@Override
	public JpaRepository<Programa, Long> getDao() {
		return programaDao;
	}
	
}
