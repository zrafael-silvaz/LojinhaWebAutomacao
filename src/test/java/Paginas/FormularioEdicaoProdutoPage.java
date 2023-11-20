package Paginas;

import org.openqa.selenium.*;

public class FormularioEdicaoProdutoPage {
    WebDriver driver;

    public FormularioEdicaoProdutoPage(WebDriver driver) {
        this.driver = driver;
    }

    public FormularioAdicaoComponente adicionarComponente() {
        try {
            driver.findElement(By.cssSelector(".waves-effect.modal-trigger")).click();
            return new FormularioAdicaoComponente(driver);
        } catch (StaleElementReferenceException ex) {
            driver.findElement(By.cssSelector(".waves-effect.modal-trigger")).click();
            return new FormularioAdicaoComponente(driver);
        }
    }

    public FormularioEdicaoProdutoPage removerComponente() throws InterruptedException {
        Thread.sleep(5000);
        try {
            driver.findElement(By.xpath("//*[@id=\"listaComponentes\"]/li[1]/a/i")).click();
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
            nome.equalsIgnoreCase(driver.findElement(By.cssSelector("label[for='produtonome']")).getAttribute("value"));
            valor.equalsIgnoreCase(driver.findElement(By.cssSelector("label[for='produtovalor']")).getAttribute("value"));
            cores.equalsIgnoreCase(driver.findElement(By.cssSelector("label[for='produtocores']")).getAttribute("value"));
            return "Produto verificado";
        }catch (InvalidSelectorException e){
            return "Produto nao conforme";
        }
    }
}
