package br.eximia.nfp.interfacetest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.google.common.base.Function;

public class UsuariosListPageCheckFor implements Function<WebDriver,WebElement> {
    @Override
    public WebElement apply(WebDriver driver) {
        return driver.findElement(By.id("uListFrm:uListdt:btnAdd"));
    }
}