package br.eximia.erm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import br.eximia.erm.dao.ProcessoEspecialDao;
import br.eximia.erm.model.ProcessoEspecial;
import br.eximia.erm.service.interfaces.ProcessoEspecialService;
import br.eximia.springutils.data.AbstractDataService;

@Service("defaultProcessoEspecialService")
public class DefaultProcessoEspecialService extends AbstractDataService<ProcessoEspecial, Long> implements ProcessoEspecialService {
	
	ProcessoEspecialDao processoEspecialDao;

	@Autowired
	public DefaultProcessoEspecialService(ProcessoEspecialDao processoEspecialDao) {
		super();
		this.processoEspecialDao = processoEspecialDao;
	}

	@Override
	public ProcessoEspecial findByProcesso(String processo) {
		return processoEspecialDao.findByProcesso(processo);
	}
	
	@Override
	public JpaRepository<ProcessoEspecial, Long> getDao() {
		return processoEspecialDao;
	}

	@Override
	public Long getMaxNCode() {
		return processoEspecialDao.getMaxNCode();
	}
	
}
