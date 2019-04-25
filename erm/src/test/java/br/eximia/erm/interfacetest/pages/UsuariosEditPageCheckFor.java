package br.eximia.erm.interfacetest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.google.common.base.Function;

public class UsuariosEditPageCheckFor implements Function<WebDriver,WebElement> {
    @Override
    public WebElement apply(WebDriver driver) {
        return driver.findElement(By.id("uEdtFrm:btnSave"));
    }
}