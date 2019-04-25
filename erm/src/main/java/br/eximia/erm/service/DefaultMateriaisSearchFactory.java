package br.eximia.erm.service;

import java.util.HashMap;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.eximia.erm.model.StatusMaterial;

@Component("defaultMateriaisSearchFactory")
public class DefaultMateriaisSearchFactory {
	
	@Autowired
	DefaultPendenteMaterialSearch defaultPendenteMaterialSearch;
	@Autowired
	DefaultAprovadoMaterialSearch defaultAprovadoMaterialSearch;
	@Autowired
	DefaultCanceladoMaterialSearch defaultCanceladoMaterialSearch;
	
	private HashMap<StatusMaterial, AbstractMaterialSearch> registeredSearch = new HashMap<StatusMaterial, AbstractMaterialSearch>();

	@PostConstruct
	public void postConstruct() {
		registeredSearch.put(StatusMaterial.Pendente, defaultPendenteMaterialSearch);
		registeredSearch.put(StatusMaterial.Aprovado, defaultAprovadoMaterialSearch);
		registeredSearch.put(StatusMaterial.Cancelado, defaultCanceladoMaterialSearch);
	}
	
	public AbstractMaterialSearch getInstance(StatusMaterial status){
		return registeredSearch.get(status);
	}
	
}
