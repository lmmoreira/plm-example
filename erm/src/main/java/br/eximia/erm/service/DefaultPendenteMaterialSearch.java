package br.eximia.erm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.eximia.erm.model.Material;
import br.eximia.erm.service.interfaces.MaterialService;

@Component("defaultPendenteMaterialSearch")
public class DefaultPendenteMaterialSearch extends AbstractMaterialSearch {

	@Autowired
	MaterialService materialService;
	
	@Override
	public List<Material> getMateriais() {
		return materialService.findByMateriaisPendentes();
	}
	
}
