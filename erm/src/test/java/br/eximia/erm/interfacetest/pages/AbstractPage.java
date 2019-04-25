package br.eximia.erm.interfacetest.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public abstract class AbstractPage<T> {
	
	private WebDriver page;
	private String link;
	
	public AbstractPage(String link) {
		page = new FirefoxDriver();
		this.link = link;
	}
	
	public AbstractPage(WebDriver page, String link) {
		this.page = page;
		this.link = link;
	}
	
	@SuppressWarnings("unchecked")
	public T visita(){
		page.get(link);
		return ((T) this);
	}
	
	public boolean retorno(String mensagem){
		String pageSource = page.getPageSource();
		return pageSource.contains(mensagem);
	}

	public WebDriver getPage() {
		return page;
	}

	public void setPage(WebDriver page) {
		this.page = page;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
	
	public void finalizaVisita(){
		this.getPage().close();
	}
	
}
