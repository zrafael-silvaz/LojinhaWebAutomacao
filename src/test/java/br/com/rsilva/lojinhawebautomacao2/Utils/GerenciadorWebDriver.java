package br.com.rsilva.lojinhawebautomacao2.Utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;

public class GerenciadorWebDriver {
    public static WebDriver newDrive(){
        return WebDriverManager.chromedriver().create();
    }
}
