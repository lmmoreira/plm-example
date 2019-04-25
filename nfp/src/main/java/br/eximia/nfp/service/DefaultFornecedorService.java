package br.eximia.nfp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.eximia.nfp.dao.FornecedorDao;
import br.eximia.nfp.model.Fornecedor;
import br.eximia.nfp.service.interfaces.FornecedorService;
import br.eximia.nfp.service.interfaces.UsuarioService;
import br.eximia.springutils.data.AbstractDataService;

@Service("defaultFornecedorService")
public class DefaultFornecedorService extends AbstractDataService<Fornecedor, Long> implements FornecedorService {
	
	FornecedorDao fornecedorDao;
	UsuarioService defaultUsuarioService;

	@Autowired
	public DefaultFornecedorService(FornecedorDao fornecedorDao, UsuarioService defaultUsuarioService) {
		super();
		this.fornecedorDao = fornecedorDao;
		this.defaultUsuarioService = defaultUsuarioService;
	}

	@Override
	@Transactional(readOnly=true)
	public Fornecedor findByFornecedor(String fornecedor) {
		return fornecedorDao.findByFornecedor(fornecedor);
	}

	@Override
	public JpaRepository<Fornecedor, Long> getDao() {
		return fornecedorDao;
	}
	
}
