package br.eximia.erm.interfacetest;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.eximia.erm.interfacetest.pages.LoginPage;
import br.eximia.erm.interfacetest.pages.UsuariosListPage;
import br.eximia.erm.model.Usuario;

public class UsuariosTest {

	private LoginPage loginPage;
	private UsuariosListPage usuariosListPage;

	@Before
	public void inicializa() {
		loginPage = new LoginPage();
		loginPage.visita();
		usuariosListPage = loginPage.efetuaLogin("admin", "1").getMenuPrincipalPage().getUsuariosPage();
	}

	@Test
	public void addUser() {
		Usuario u = new Usuario();
		u.setNome("selenium");
		u.setEmail("selenium@test.com");
		u.setSenha("1");
		u.setUsuario("selenium");
		u.setStatus(true);
		
		Assert.assertTrue(usuariosListPage.getAdicionarPage().addUser(u).getUsuariosListPage().retorno("selenium"));
	}
	
	@Test
	public void gridUser() {
		Assert.assertTrue(usuariosListPage.retorno("Administrador"));
	}
	
	@After
	public void destroy() {
		loginPage.finalizaVisita();
	}

}
