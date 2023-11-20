package Paginas;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FormularioAdicaoComponentePage {
    WebDriver driver;

    public FormularioAdicaoComponentePage(WebDriver driver) {
        this.driver = driver;
    }

    public FormularioAdicaoComponentePage informaNomeComponente(String nomeComponente) {
        driver.findElement(By.id("componentenomeadicionar")).sendKeys(nomeComponente);
        return this;
    }

    public FormularioAdicaoComponentePage informaQTDComponente(String qtdComponente) {
        driver.findElement(By.id("componentequantidadeadicionar")).sendKeys(qtdComponente);
        return this;
    }
    public FormularioEdicaoProdutoPage cancelarAdicaoComponente() {
        driver.findElement(By.linkText("CANCELAR")).click();
        return new FormularioEdicaoProdutoPage(driver);
    }
    public FormularioEdicaoProdutoPage submeterComponente() throws InterruptedException {
        driver.findElement(By.linkText("SALVAR COMPONENTE")).click();
        Thread.sleep(4000);
        return new FormularioEdicaoProdutoPage(driver);

    }
}
