package br.eximia.erm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.eximia.erm.dao.UnidadeLoteFornecedorDao;
import br.eximia.erm.model.UnidadeLoteFornecedor;
import br.eximia.erm.service.interfaces.UnidadeLoteFornecedorService;
import br.eximia.springutils.data.AbstractDataService;

@Service("defaultUnidadeLoteFornecedorService")
public class DefaultUnidadeLoteFornecedorService extends AbstractDataService<UnidadeLoteFornecedor, Long> implements UnidadeLoteFornecedorService {
	
	UnidadeLoteFornecedorDao unidadeLoteFornecedorDao;

	@Autowired
	public DefaultUnidadeLoteFornecedorService(UnidadeLoteFornecedorDao unidadeLoteFornecedorDao) {
		super();
		this.unidadeLoteFornecedorDao = unidadeLoteFornecedorDao;
	}
	
	@Override
	@Transactional(readOnly=true)
	public UnidadeLoteFornecedor findByUnidade(String unidade) {
		return unidadeLoteFornecedorDao.findByUnidade(unidade);
	}

	@Override
	public JpaRepository<UnidadeLoteFornecedor, Long> getDao() {
		return unidadeLoteFornecedorDao;
	}
	
}
