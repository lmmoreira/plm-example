package br.eximia.nfp.interfacetest.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

public class UsuariosListPage extends AbstractPage<UsuariosListPage> {

	private static final String LOGIN_PAGE = "http://localhost:8081/erm/pages/admin/usuarios.xhtml";

	public UsuariosListPage() {
		super(LOGIN_PAGE);
	}

	public UsuariosListPage(WebDriver page) {
		super(page, LOGIN_PAGE);
	}

	public UsuariosEditPage getAdicionarPage() {
		WebElement usuariosAddPage = this.getPage().findElement(By.id("uListFrm:uListdt:btnAdd"));
		usuariosAddPage.click();
		
		Wait<WebDriver> wait = new FluentWait<WebDriver>(this.getPage())
				.withTimeout(30, TimeUnit.SECONDS)
				.pollingEvery(5, TimeUnit.SECONDS)
				.ignoring(NoSuchElementException.class);
		wait.until(new UsuariosEditPageCheckFor());

		return new UsuariosEditPage(this.getPage());
	}

}
