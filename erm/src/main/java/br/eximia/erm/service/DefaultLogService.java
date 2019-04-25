package br.eximia.erm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.eximia.erm.dao.LogDao;
import br.eximia.erm.model.Log;
import br.eximia.erm.service.interfaces.LogService;
import br.eximia.springutils.data.AbstractDataService;

@Service("defaultLogService")
public class DefaultLogService extends AbstractDataService<Log, Long> implements LogService {
	
	LogDao logDao;

	@Autowired
	public DefaultLogService(LogDao logDao) {
		super();
		this.logDao = logDao;
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<Log> findByLocal(String local) {
		return logDao.findByLocal(local);
	}

	@Override
	public JpaRepository<Log, Long> getDao() {
		return logDao;
	}
	
}
