package br.eximia.nfp.integrationtest;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.eximia.nfp.model.Usuario;
import br.eximia.nfp.service.interfaces.UsuarioService;
import br.eximia.springutils.test.AbstractSpringIntegrationTest;

public class UsuariosIntegrationTest extends AbstractSpringIntegrationTest{

	@Autowired
	UsuarioService defaultUsuarioService;
	
	@Test
	public void test(){
		System.out.println(defaultUsuarioService.findAll());
		
		Usuario o = new Usuario();
		UsuarioService sss = (UsuarioService) o.getBean(UsuarioService.class);
		System.out.println(sss.findAll());
		
	}
	
}
