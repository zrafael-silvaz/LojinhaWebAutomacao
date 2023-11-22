package br.com.rsilva.lojinhawebautomacao2.Pages;

import br.com.rsilva.lojinhawebautomacao2.BasePages;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

public class FormularioAdicaoProdutoPage extends BasePages {

    @FindBy(id = "produtonome")
    private WebElement campoNomeProduto;

    @FindBy(id = "produtovalor")
    private WebElement campoValorProduto;

    @FindBy(id = "produtocores")
    private WebElement campoCoresProduto;

    @FindBy(css = "[type='submit']")
    private WebElement botaoSubmit;

    public FormularioAdicaoProdutoPage(WebDriver driver) {
        super(driver);
    }

    public FormularioAdicaoProdutoPage informarNomeProduto(String produtoNome) {
        campoNomeProduto.sendKeys(produtoNome);
        return this;
    }

    public FormularioAdicaoProdutoPage informarValorProduto(String produtoValor) {
        campoValorProduto.sendKeys(produtoValor);
        return this;
    }

    public FormularioAdicaoProdutoPage informarCoresProduto(String produtoCor) {
        campoCoresProduto.sendKeys(produtoCor);
        return this;
    }

    public ListaDeProdutosPage submeterFormularioAdicaoComErro() {
        botaoSubmit.click();
        return new ListaDeProdutosPage(driver);
    }

    public FormularioEdicaoProdutoPage submeterFormularioAdicao() {
        botaoSubmit.click();
        return new FormularioEdicaoProdutoPage(driver);
    }
}

