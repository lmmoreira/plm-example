package br.eximia.erm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.eximia.erm.dao.MaterialMensagemDao;
import br.eximia.erm.model.MaterialMensagem;
import br.eximia.erm.model.Usuario;
import br.eximia.erm.service.interfaces.MaterialMensagemService;
import br.eximia.springutils.data.AbstractDataService;

@Service("defaultMaterialMensagemService")
public class DefaultMaterialMensagemService extends AbstractDataService<MaterialMensagem, Long> implements MaterialMensagemService {
	
	MaterialMensagemDao materialMensagemDao;

	@Autowired
	public DefaultMaterialMensagemService(MaterialMensagemDao materialMensagemDao) {
		super();
		this.materialMensagemDao = materialMensagemDao;
	}
	
	@Override
	public JpaRepository<MaterialMensagem, Long> getDao() {
		return materialMensagemDao;
	}

	@Override
	@Transactional(readOnly=true)
	public List<MaterialMensagem> findByDestinoNaoLido(Usuario destino) {
		return materialMensagemDao.findByDestinoNaoLido(destino.getId());
	}
	
}
