package br.eximia.erm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.eximia.erm.dao.CidadeDao;
import br.eximia.erm.model.Cidade;
import br.eximia.erm.service.interfaces.CidadeService;
import br.eximia.springutils.data.AbstractDataService;

@Service("defaultCidadeService")
public class DefaultCidadeService extends AbstractDataService<Cidade, Long> implements CidadeService {
	
	CidadeDao cidadeDao;

	@Autowired
	public DefaultCidadeService(CidadeDao cidadeDao) {
		super();
		this.cidadeDao = cidadeDao;
	}

	@Override
	@Transactional(readOnly=true)
	public Cidade findByCidade(String cidade) {
		return cidadeDao.findByCidade(cidade);
	}

	@Override
	public JpaRepository<Cidade, Long> getDao() {
		return cidadeDao;
	}
	
}
