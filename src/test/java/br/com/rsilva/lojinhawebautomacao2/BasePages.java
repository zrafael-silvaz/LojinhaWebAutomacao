package br.com.rsilva.lojinhawebautomacao2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePages {
    protected WebDriver driver;
    public BasePages(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
}
