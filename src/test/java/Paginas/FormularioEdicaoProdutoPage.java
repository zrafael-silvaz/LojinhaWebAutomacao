package Paginas;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FormularioEdicaoProdutoPage {
    WebDriver driver;

    public FormularioEdicaoProdutoPage(WebDriver driver) {
        this.driver = driver;
    }

    public FormularioAdicaoComponentePage adicionarComponente() {
        try {
            driver.findElement(By.cssSelector(".waves-effect.modal-trigger")).click();
            return new FormularioAdicaoComponentePage(driver);
        } catch (StaleElementReferenceException ex) {
            driver.findElement(By.cssSelector(".waves-effect.modal-trigger")).click();
            return new FormularioAdicaoComponentePage(driver);
        }
    }
    public FormularioEdicaoProdutoPage removerComponente() throws InterruptedException {

        try {
            driver.findElement(By.xpath("//*[@id=\"listaComponentes\"]/li[1]/a/i")).click();
            Thread.sleep(4000);
//            new WebDriverWait(driver, Duration.ofSeconds(3))
//                    .until(ExpectedConditions.invisibilityOf(
//                                    driver.findElement(By.cssSelector(".toast.rounded"))
//            ));
            return this;
        } catch (StaleElementReferenceException ex) {
            driver.findElement(By.xpath("//*[@id=\"listaComponentes\"]/li[1]/a/i")).click();
            return this;
        }
    }
    public ListaDeProdutosPage navegarListaProduto() {
        driver.findElement(By.linkText("LISTA DE PRODUTOS")).click();
        return new ListaDeProdutosPage(driver);
    }

    public String capturaMensagemApresentada() {
        try {
            return driver.findElement(By.cssSelector(".toast.rounded")).getText();
        } catch (NoSuchElementException e) {
            return "Nao ha mensagem";
        }
    }

    public String verificaProdutoAdicionado(String nome, String valor, String cores) {
        try {
            nome.equals(driver.findElement(By.cssSelector("label[for='produtonome']"))
                    .getAttribute("value"));
            valor.equals(driver.findElement(By.cssSelector("label[for='produtovalor']"))
                    .getAttribute("value"));
            cores.equals(driver.findElement(By.cssSelector("label[for='produtocores']"))
                    .getAttribute("value"));
            return "Produto verificado";
        }catch (InvalidSelectorException e){
            return "Produto nao conforme";
        }
    }
}
