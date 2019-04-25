package br.eximia.erm.interfacetest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends AbstractPage<LoginPage> {

	private static final String LOGIN_PAGE = "http://localhost:8081/erm/index.xhtml";
	
	public LoginPage() {
		super(LOGIN_PAGE);
	}

	public LoginPage(WebDriver page) {
		super(page, LOGIN_PAGE);
	}

	public LoginPage efetuaLogin(String usuario, String senha){
		WebElement nome = this.getPage().findElement(By.name("j_username"));
		WebElement pwd = this.getPage().findElement(By.name("j_password"));
		WebElement entrar = this.getPage().findElement(By.className("enter"));
		nome.sendKeys(usuario);
		pwd.sendKeys(senha);
		entrar.click();
		return this;
	}
	
	public MenuPrincipalPage getMenuPrincipalPage(){
		return new MenuPrincipalPage(this.getPage());
	}
	
}
