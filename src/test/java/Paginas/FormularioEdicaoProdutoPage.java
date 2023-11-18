package Paginas;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;

public class FormularioEdicaoProdutoPage {
    WebDriver driver;

    public FormularioEdicaoProdutoPage(WebDriver driver) {
        this.driver = driver;
    }

    public FormularioAdicaoComponente adicionarComponente(){
        try{
            driver.findElement(By.cssSelector(".waves-effect.modal-trigger")).click();
            return new FormularioAdicaoComponente(driver);
        }catch (StaleElementReferenceException ex){
            driver.findElement(By.cssSelector(".waves-effect.modal-trigger")).click();
            return new FormularioAdicaoComponente(driver);
        }
    }
    public FormularioEdicaoProdutoPage removerComponente() throws InterruptedException {
        Thread.sleep(5000);
        try{
            driver.findElement(By.xpath("//*[@id=\"listaComponentes\"]/li[1]/a/i")).click();
            return this;
        }catch (StaleElementReferenceException ex){
            driver.findElement(By.xpath("//*[@id=\"listaComponentes\"]/li[1]/a/i")).click();
            return this;
        }
    }
    public String  capturaMensagemApresentada(){
        return driver.findElement(By.cssSelector(".toast.rounded")).getText();
    }
}
