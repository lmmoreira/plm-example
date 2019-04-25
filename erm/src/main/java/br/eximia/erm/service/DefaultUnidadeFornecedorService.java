package br.eximia.erm.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.eximia.erm.dao.UnidadeFornecedorDao;
import br.eximia.erm.model.UnidadeFornecedor;
import br.eximia.erm.service.interfaces.UnidadeFornecedorService;
import br.eximia.springutils.data.AbstractDataService;

@Service("defaultUnidadeFornecedorService")
public class DefaultUnidadeFornecedorService extends AbstractDataService<UnidadeFornecedor, Long> implements UnidadeFornecedorService {
	
	UnidadeFornecedorDao unidadeFornecedorDao;

	@Autowired
	public DefaultUnidadeFornecedorService(UnidadeFornecedorDao unidadeFornecedorDao) {
		super();
		this.unidadeFornecedorDao = unidadeFornecedorDao;
	}
	
	@Override
	@Transactional(readOnly=true)
	public UnidadeFornecedor findByUnidade(String unidade) {
		return unidadeFornecedorDao.findByUnidade(unidade);
	}

	@Override
	public JpaRepository<UnidadeFornecedor, Long> getDao() {
		return unidadeFornecedorDao;
	}

	@Override
	@Transactional(readOnly=true)
	public List<UnidadeFornecedor> findByUnidadesArmazenagem() {
		List<String> unidades = new ArrayList<String>();
		unidades.add("Net");
		unidades.add("CAN");
		unidades.add("IN");
		unidades.add("IN²");
		unidades.add("Ft");
		unidades.add("Ft²");
		unidades.add("Ft³");
		unidades.add("PCK");
		unidades.add("DRUM");
		unidades.add("P Drum");
		unidades.add("BAR");
		return unidadeFornecedorDao.findByUnidadesArmazenagem(unidades);
	}
	
}
