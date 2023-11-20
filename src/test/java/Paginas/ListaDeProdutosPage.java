package Paginas;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ListaDeProdutosPage {
    WebDriver driver;

    public ListaDeProdutosPage(WebDriver driver) {
        this.driver = driver;
    }

    public LoginPage logOut(){
        driver.findElement(By.linkText("Sair")).click();
        return new LoginPage(driver);
    }
    public String verificarLogin(){
        return driver.findElement(By.cssSelector("#nav-mobile > li:nth-child(1) > a")).getText();
    }
    public ListaDeProdutosPage removerProduto(){
        driver.findElement(By.xpath("/html/body/div[2]/div/ul/li[1]/a/i")).click();
        return this;
    }
    public FormularioAdicaoProdutoPage acessarFormAddProduto(){
        driver.findElement(By.linkText("ADICIONAR PRODUTO")).click();
        return new FormularioAdicaoProdutoPage(driver);
    }
    public FormularioEdicaoProdutoPage editandoProduto(){
        driver.findElement(By.cssSelector(".title")).click();
        return new FormularioEdicaoProdutoPage(driver);
    }
    public String capturaMensagemApresentada(){
        return driver.findElement(By.cssSelector(".toast.rounded")).getText();
    }
}
