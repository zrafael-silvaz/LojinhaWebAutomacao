package br.com.rsilva.lojinhawebautomacao2.Pages;

import br.com.rsilva.lojinhawebautomacao2.BasePages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ListaDeProdutosPage extends BasePages {
    @FindBy(linkText = "Sair")
    private WebElement botaoLogout;
    @FindBy(css = "#nav-mobile > li:nth-child(1) > a")
    private WebElement elementoLogin;
    @FindBy(xpath = "/html/body/div[2]/div/ul/li[1]/a/i")
    private WebElement botaoRemover;
    @FindBy(linkText = "ADICIONAR PRODUTO")
    private WebElement botaoAdicionarProduto;
    @FindBy(css = ".title")
    private WebElement editarProduto;
    @FindBy(css = ".toast.rounded")
    private WebElement mensagem;

    public ListaDeProdutosPage(WebDriver driver) {
        super(driver);
    }
    public LoginPage logOut(){
        botaoLogout.click();
        return new LoginPage(driver);
    }
    public String verificarLogin(){
        return elementoLogin.getText();
    }
    public ListaDeProdutosPage removerProduto(){
        botaoRemover.click();
        return this;
    }
    public FormularioAdicaoProdutoPage acessarFormAddProduto(){
        botaoAdicionarProduto.click();
        return new FormularioAdicaoProdutoPage(driver);
    }
    public FormularioEdicaoProdutoPage editandoProduto(){
        editarProduto.click();
        return new FormularioEdicaoProdutoPage(driver);
    }
    public String capturaMensagemApresentada(){
        return mensagem.getText();
    }
}
