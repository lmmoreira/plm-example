package br.eximia.erm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.eximia.erm.dao.MaterialDao;
import br.eximia.erm.dao.RadicalDao;
import br.eximia.erm.model.Ata;
import br.eximia.erm.model.Material;
import br.eximia.erm.model.Programa;
import br.eximia.erm.model.Radical;
import br.eximia.erm.model.SubAta;
import br.eximia.erm.model.Traco;
import br.eximia.erm.service.interfaces.RadicalService;
import br.eximia.springutils.data.AbstractDataService;

@Service("defaultRadicalService")
public class DefaultRadicalService extends AbstractDataService<Radical, Long> implements RadicalService {
	
	RadicalDao radicalDao;
	MaterialDao materialDao;

	@Autowired
	public DefaultRadicalService(RadicalDao radicalDao, MaterialDao materialDao) {
		super();
		this.radicalDao = radicalDao;
		this.materialDao = materialDao;
	}
	
	@Override
	@Transactional(readOnly=true)
	public Radical findByRadical(Long radical) {
		return radicalDao.findByRadical(radical);
	}

	@Override
	public JpaRepository<Radical, Long> getDao() {
		return radicalDao;
	}

	@Override
	public Radical findByProgramaAndAtaAndSubAta(Programa programa, Ata ata, SubAta subAta) {
		return radicalDao.findByProgramaAndAtaAndSubAta(programa, ata, subAta);
	}
	
	@Override
	@Transactional(readOnly=true)
	public Long getNextRadical(Programa programa, Ata ata, SubAta subAta, Traco traco, Boolean especular, Material pnEspecular){
		if((programa == null)||(ata == null)||(subAta == null))
			return 0L;
		
		if((traco != null) && (especular != null) && (especular)){
			if(pnEspecular != null){
				if(pnEspecular.getRadical() != null)
					return Long.parseLong(pnEspecular.getRadical());
			} else {
				List<Material> materiais = materialDao.findByProgramaAndAtaOAndSubAtaOAndTracoTraco(programa, ata, subAta, Long.parseLong(traco.getTraco().toString()));
				if(materiais.size() > 0){
					return Long.parseLong(materiais.get(0).getRadical());
				}
			}
		}
		
		Radical radical = this.findByProgramaAndAtaAndSubAta(programa, ata, subAta);
		
		if(radical == null)
			radical = this.save(new Radical(programa, ata, subAta));
		
		return radical.getRadical() + 1;
	} 
	
	@Override
	@Transactional
	public void plusOneToRadical(Programa programa, Ata ata, SubAta subAta){
		if((programa == null)||(ata == null)||(subAta == null))
			return;
		
		Radical radical = this.findByProgramaAndAtaAndSubAta(programa, ata, subAta);
		
		if(radical == null)
			this.save(new Radical(programa, ata, subAta));
		else
			this.save(radical.plusOne());
	}
}
