package br.eximia.erm.integrationtest;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.eximia.erm.model.Categoria;
import br.eximia.erm.model.Fornecedor;
import br.eximia.erm.model.Material;
import br.eximia.erm.model.MaterialAprovador;
import br.eximia.erm.model.TipoMaterial;
import br.eximia.erm.model.Usuario;
import br.eximia.erm.service.interfaces.CategoriaService;
import br.eximia.erm.service.interfaces.FornecedorService;
import br.eximia.erm.service.interfaces.MaterialService;
import br.eximia.erm.service.interfaces.UsuarioService;
import br.eximia.springutils.test.AbstractSpringIntegrationTest;

public class MateriaisIntegrationTest extends AbstractSpringIntegrationTest{

	@Autowired
	MaterialService defaultMaterialService;
	
	@Autowired
	CategoriaService defaultCategoriaService;
	
	@Autowired
	FornecedorService defaultFornecedorService;
	
	@Autowired
	UsuarioService defaultUsuarioService;
	
	
	@Test
	public void test(){

		Categoria c = defaultCategoriaService.findById(2L);
		Fornecedor f = defaultFornecedorService.findById(5L);
		Usuario u = defaultUsuarioService.findById(101L);
		
		Material m = new Material();
		m.setMaterial("INSERIDO NO TESTE");
		m.setPn("INSERIDO NO TESTE");
		m.setCategoria(c);
		m.setTipo(TipoMaterial.BUY);
		m.setFornecedor(f);
		m.setEspecificacao("espc");
		
		
		List<MaterialAprovador> ma = new ArrayList<MaterialAprovador>();
		ma.add(new MaterialAprovador(m, u));
		
		m.setAprovadores(ma);
		
		//defaultMaterialService.save(m);
		
		System.out.println(m);
		
		
	}
	
}
