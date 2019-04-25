package br.eximia.erm.interfacetest;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.eximia.erm.interfacetest.pages.LoginPage;

public class WelcomeTest {

	private LoginPage loginPage;

	@Before
	public void inicializa() {
		loginPage = new LoginPage();
		loginPage.visita();
	}

	@Test
	public void loginFailed() {
		Assert.assertTrue(loginPage.efetuaLogin("admin", "2").retorno("incorrect"));
	}
	
	@Test
	public void loginSucess() {
		Assert.assertTrue(loginPage.efetuaLogin("admin", "1").retorno("Menu Principal"));
	}
	
	@Test
	public void loginAndLogOffSucess() {
		Assert.assertTrue(loginPage.efetuaLogin("admin", "1").getMenuPrincipalPage().sair().retorno("Entrar"));
	}
	
	@After
	public void destroy() {
		loginPage.finalizaVisita();
	}

}
