package br.eximia.erm.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.eximia.erm.dao.FornecedorDao;
import br.eximia.erm.model.Fornecedor;
import br.eximia.erm.model.FornecedorAprovador;
import br.eximia.erm.model.StatusFornecedor;
import br.eximia.erm.model.Usuario;
import br.eximia.erm.service.interfaces.FornecedorService;
import br.eximia.erm.service.interfaces.UsuarioService;
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

	@Override
	@Transactional(readOnly=true)
	public List<Fornecedor> findByAprovadoresStatusAndUser(StatusFornecedor status, Usuario aprovador) {
		return fornecedorDao.findByAprovadoresStatusAndUser(status.name(), aprovador.getId());
	}

	@Override
	@Transactional(readOnly=true)
	public List<Fornecedor> findByFornecedoresAprovados() {
		return fornecedorDao.findByFornecedoresAprovados();
	}

	@Override
	@Transactional(readOnly=true)
	public List<Fornecedor> findByFornecedoresPendentes() {
		return fornecedorDao.findByFornecedoresPendentes();
	}

	@Override
	@Transactional(readOnly=true)
	public List<Fornecedor> findByFornecedoresCancelados() {
		return fornecedorDao.findByFornecedoresCancelados();
	}
	
	@Override
	@Transactional
	public void checkFornecedorRevalidacao(){
		List<Fornecedor> pendencias = this.findByAprovadoresStatusAndUser(StatusFornecedor.Pendente, defaultUsuarioService.getLoggedUser());
		
		for(Fornecedor pendencia : pendencias){
			
			if(pendencia.getVencido()){
				Calendar reavaliacao = Calendar.getInstance();
				reavaliacao.setTime(pendencia.getDataReavaliacao());
				reavaliacao.add(Calendar.YEAR, 1);
				pendencia.setDataReavaliacao(reavaliacao.getTime());
				
				for(FornecedorAprovador aprovador : pendencia.getAprovadores()){
					aprovador.setStatus(StatusFornecedor.Pendente);
					aprovador.setData(new Date());
				}
			}
			
			this.save(pendencia);
		}
		
	} 
	
	@Override
	@Transactional(readOnly=true)
	public List<Fornecedor> findByDestinoNaoLido(Usuario destino) {
		return fornecedorDao.findByDestinoNaoLido(destino.getId());
	}
	
}
