package br.eximia.erm.interfacetest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MenuPrincipalPage extends AbstractPage<MenuPrincipalPage> {

	private static final String MENU_PAGE = "http://localhost:8081/erm/pages/main.xhtml";
	
	public MenuPrincipalPage() {
		super(MENU_PAGE);
	}

	public MenuPrincipalPage(WebDriver page) {
		super(page, MENU_PAGE);
	}
	
	public MenuPrincipalPage sair(){
		WebElement sair = this.getPage().findElement(By.linkText("Sair"));
		sair.click();
		return this;
	}
	
	public UsuariosListPage getUsuariosPage(){
		return new UsuariosListPage(this.getPage()).visita();
	}
	
}
