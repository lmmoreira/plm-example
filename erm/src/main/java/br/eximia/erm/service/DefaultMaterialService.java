package br.eximia.erm.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.eximia.erm.dao.MaterialDao;
import br.eximia.erm.model.Ata;
import br.eximia.erm.model.Material;
import br.eximia.erm.model.ProcessoEspecial;
import br.eximia.erm.model.Programa;
import br.eximia.erm.model.StatusMaterial;
import br.eximia.erm.model.SubAta;
import br.eximia.erm.model.Usuario;
import br.eximia.erm.service.interfaces.MaterialService;
import br.eximia.springutils.data.AbstractDataService;

@Service("defaultMaterialService")
public class DefaultMaterialService extends AbstractDataService<Material, Long> implements MaterialService {
	
	MaterialDao materialDao;
	
	@Autowired
	public DefaultMaterialService(MaterialDao materialDao) {
		super();
		this.materialDao = materialDao;
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<Material> readNative(String sql) {
		return (super.readNative(sql));
	}
	
	
	
	@Override
	@Transactional(readOnly=true)
	public List<Material> readNativeProcesses(String sql) {
		return putProcessoEspecialListOnFields(super.readNative(sql));
	}
	
	@Override
	public Material save(Material entity) {
		this.putProcessoEspecialOnList(entity);
		return super.save(entity);
	}
	
	@Override
	@Transactional(readOnly=true)
	public Material findByEspecificacao(String especificacao) {
		return materialDao.findByEspecificacao(especificacao);
	}
	
	@Override
	public Long getMaxNCode() {
		return materialDao.getMaxNCode();
	}

	@Override
	public JpaRepository<Material, Long> getDao() {
		return materialDao;
	}

	@Override
	@Transactional(readOnly=true)
	public List<Material> findByAprovadoresStatusAndUser(StatusMaterial status, Usuario aprovador) {
		return putProcessoEspecialListOnFields(materialDao.findByAprovadoresStatusAndUser(status.name(), aprovador.getId()));
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<Material> findByAprovadoresStatusAndUserCampos(StatusMaterial status, Usuario aprovador) {
		return putProcessoEspecialListOnFields(materialDao.findByAprovadoresStatusAndUserCampos(status.name(), aprovador.getId()));
	}

	@Override
	@Transactional(readOnly=true)
	public List<Material> findByPnAndNcodeNotLike(String pn, Long ncode) {
		return putProcessoEspecialListOnFields(materialDao.findByPnAndNcodeNotLike(pn, ncode));
	}

	@Override
	@Transactional(readOnly=true)
	public List<Material> findByMateriaisAprovados() {
		return putProcessoEspecialListOnFields(materialDao.findByMateriaisAprovados());
	}

	@Override
	@Transactional(readOnly=true)
	public List<Material> findByMateriaisPendentes() {
		return putProcessoEspecialListOnFields(materialDao.findByMateriaisPendentes());
	}

	@Override
	@Transactional(readOnly=true)
	public List<Material> findByMateriaisCancelados() {
		return putProcessoEspecialListOnFields(materialDao.findByMateriaisCancelados());
	}

	@Override
	@Transactional(readOnly=true)
	public Material findByNcode(Long ncode) {
		return materialDao.findByNcode(ncode);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Material> findByDestinoNaoLido(Usuario destino) {
		return putProcessoEspecialListOnFields(materialDao.findByDestinoNaoLido(destino.getId()));
	}

	@Override
	@Transactional(readOnly=true)
	public List<Material> findByNcodeLike(String ncode) {
		return putProcessoEspecialListOnFields(materialDao.findByNcodeLike(ncode));
	}
	
	private void putProcessoEspecialOnList(Material entity){
		List<ProcessoEspecial> listProcessoEspecial = new ArrayList<ProcessoEspecial>();
		if(entity.getProcessoEspecial1() != null)
			listProcessoEspecial.add(entity.getProcessoEspecial1());
		if(entity.getProcessoEspecial2() != null)
			listProcessoEspecial.add(entity.getProcessoEspecial2());
		if(entity.getProcessoEspecial3() != null)
			listProcessoEspecial.add(entity.getProcessoEspecial3());
		entity.setProcessosEspeciais(listProcessoEspecial);
	}
	
	public List<Material> putProcessoEspecialListOnFields(List<Material> results){
		for(Material m : results){
			for(int i = 0; i<=m.getProcessosEspeciais().size()-1; i++){
				if(i == 0)	
					m.setProcessoEspecial1(m.getProcessosEspeciais().get(i));
				if(i == 1)	
					m.setProcessoEspecial2(m.getProcessosEspeciais().get(i));
				if(i == 2)	
					m.setProcessoEspecial3(m.getProcessosEspeciais().get(i));
			}
		}
		return results;
	}

	@Override
	@Transactional(readOnly=true)
	public List<Material> findByProgramaAndAtaOAndSubAtaOAndTracoTraco(Programa programa, Ata ata, SubAta subAta, Long traco) {
		return materialDao.findByProgramaAndAtaOAndSubAtaOAndTracoTraco(programa, ata, subAta, traco);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Material> findByPnLike(String pn) {
		return materialDao.findByPnLike(pn);
	}
	
}
