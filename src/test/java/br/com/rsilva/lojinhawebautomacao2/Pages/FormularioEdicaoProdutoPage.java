package br.com.rsilva.lojinhawebautomacao2.Pages;

import br.com.rsilva.lojinhawebautomacao2.BasePages;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

public class FormularioEdicaoProdutoPage extends BasePages {
    @FindBy(css = ".waves-effect.modal-trigger")
    private WebElement botaoAdicionarComponente;
    @FindBy(xpath = "//*[@id='listaComponentes']/li[1]/a/i")
    private WebElement botaoRemoverComponente;
    @FindBy(linkText = "LISTA DE PRODUTOS")
    private WebElement linkListaDeProdutos;
    @FindBy(css = ".toast.rounded")
    private WebElement mensagemToast;
    @FindBy(id = "produtonome")
    private WebElement campoNomeProduto;
    @FindBy(id = "produtovalor")
    private WebElement campoValorProduto;
    @FindBy(id = "produtocores")
    private WebElement campoCoresProduto;

    public FormularioEdicaoProdutoPage(WebDriver driver) {
        super(driver);
    }
    public FormularioAdicaoComponentePage adicionarComponente() {
        try {
            botaoAdicionarComponente.click();
            return new FormularioAdicaoComponentePage(driver);
        } catch (StaleElementReferenceException ex) {
            botaoAdicionarComponente.click();
            return new FormularioAdicaoComponentePage(driver);
        }
    }

    public FormularioEdicaoProdutoPage removerComponente() throws InterruptedException {

        try {
            botaoRemoverComponente.click();
            Thread.sleep(4000);
            return this;
        } catch (StaleElementReferenceException ex) {
            botaoRemoverComponente.click();
            return this;
        }
    }
    public ListaDeProdutosPage navegarListaProduto() {
        linkListaDeProdutos.click();
        return new ListaDeProdutosPage(driver);
    }

    public String capturaMensagemApresentada() {
        try {
            return mensagemToast.getText();
        } catch (NoSuchElementException e) {
            return "Nao ha mensagem";
        }
    }

    public String verificaMascaraValor( String valor) {
        if (valor.equals(campoValorProduto.getAttribute("value"))) {
            return "Produto conforme";
        } else {
            return "Produto nao conforme";
        }
    }
}
