package br.eximia.nfp.interfacetest.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import br.eximia.nfp.model.Usuario;

public class UsuariosEditPage extends AbstractPage<UsuariosEditPage> {

	private static final String USUARIOS_EDIT_PAGE = "http://localhost:8081/erm/pages/admin/usuariosEdit.xhtml";
	
	public UsuariosEditPage() {
		super(USUARIOS_EDIT_PAGE);
	}

	public UsuariosEditPage(WebDriver page) {
		super(page, USUARIOS_EDIT_PAGE);
	}
	
	public UsuariosEditPage addUser(Usuario usuario){
		WebElement txtNome = this.getPage().findElement(By.id("uEdtFrm:uEdtAc:nome"));
		WebElement txtEmail = this.getPage().findElement(By.id("uEdtFrm:uEdtAc:email"));
		WebElement txtUsuario = this.getPage().findElement(By.id("uEdtFrm:uEdtAc:usuario"));
		WebElement txtSenha = this.getPage().findElement(By.id("uEdtFrm:uEdtAc:senha"));
		WebElement ckStatus = this.getPage().findElement(By.id("uEdtFrm:uEdtAc:status"));
																
		txtNome.sendKeys(usuario.getNome());
		txtEmail.sendKeys(usuario.getEmail());
		txtUsuario.sendKeys(usuario.getUsuario());
		txtSenha.sendKeys(usuario.getSenha());
		
		if(usuario.getStatus())
			ckStatus.click();
		
		WebElement confirmar = this.getPage().findElement(By.id("uEdtFrm:btnSave"));
		confirmar.click();
		
		Wait<WebDriver> wait = new FluentWait<WebDriver>(this.getPage())
		   .withTimeout(30, TimeUnit.SECONDS)
		   .pollingEvery(5, TimeUnit.SECONDS)
		   .ignoring(NoSuchElementException.class);
		wait.until(new UsuariosListPageCheckFor());
		
		return this;
	}
	
	public UsuariosListPage getUsuariosListPage(){
		return new UsuariosListPage(this.getPage());
	}
	
}
