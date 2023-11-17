package Paginas;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FormularioAdicaoProdutoPage {

    WebDriver driver;

    public FormularioAdicaoProdutoPage(WebDriver driver) {
        this.driver = driver;
    }
    public FormularioAdicaoProdutoPage informarNomeProduto(String produtoNome){
        driver.findElement(By.id("produtonome")).sendKeys(produtoNome);
        return this;
    }
    public FormularioAdicaoProdutoPage informarValorProduto(String produtoValor){
        driver.findElement(By.id("produtovalor")).sendKeys(produtoValor);
        return this;
    }
    public FormularioAdicaoProdutoPage informarCoresProduto(String produtoCor){
        driver.findElement(By.id("produtocores")).sendKeys(produtoCor);
        return this;
    }
    public ListaDeProdutosPage submeterFormularioAdicaoComErro(){
        driver.findElement(By.cssSelector("[type='submit']")).click();
        return new ListaDeProdutosPage(driver);
    }
    public FormularioEdicaoProdutoPage submeterFormularioAdicao(){
        driver.findElement(By.cssSelector("[type='submit']")).click();
        return new FormularioEdicaoProdutoPage(driver);
    }
}
