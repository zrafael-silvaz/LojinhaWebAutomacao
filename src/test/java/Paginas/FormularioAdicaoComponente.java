package Paginas;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FormularioAdicaoComponente {
    WebDriver driver;

    public FormularioAdicaoComponente(WebDriver driver) {
        this.driver = driver;
    }

    public FormularioAdicaoComponente informaNomeComponente(String nomeComponente){
        driver.findElement(By.id("componentenomeadicionar")).sendKeys(nomeComponente);
        return this;
    }
    public FormularioAdicaoComponente informaQTDComponente(String qtdComponente){
        driver.findElement(By.id("componentequantidadeadicionar")).sendKeys(qtdComponente);
        return this;
    }
    public FormularioEdicaoProdutoPage submeterComponente() throws InterruptedException {
        Thread.sleep(4000);
        driver.findElement(By.linkText("SALVAR COMPONENTE")).click();
        return new FormularioEdicaoProdutoPage(driver);
    }
}
