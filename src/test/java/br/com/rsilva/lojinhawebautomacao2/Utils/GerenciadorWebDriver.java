package br.com.rsilva.lojinhawebautomacao2.Utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class GerenciadorWebDriver {
    public static WebDriver newDrive(){
        return WebDriverManager.chromedriver().create();
        //System.setProperty("webdriver.chrome.driver", "C:\\Users\\rafae\\Downloads\\chromedriver-win64\\chromedriver.exe");
        //WebDriverManager.chromedriver().setup();
        //return new ChromeDriver();
    }
}
