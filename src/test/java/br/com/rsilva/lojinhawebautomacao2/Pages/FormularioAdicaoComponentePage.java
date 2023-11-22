package br.com.rsilva.lojinhawebautomacao2.Pages;

import br.com.rsilva.lojinhawebautomacao2.BasePages;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FormularioAdicaoComponentePage extends BasePages {

    @FindBy(id = "componentenomeadicionar")
    private WebElement campoNomeComponente;

    @FindBy(id = "componentequantidadeadicionar")
    private WebElement campoQtdComponente;

    @FindBy(linkText = "CANCELAR")
    private WebElement linkCancelar;

    @FindBy(linkText = "SALVAR COMPONENTE")
    private WebElement linkSalvarComponente;

    public FormularioAdicaoComponentePage(WebDriver driver) {
        super(driver);
    }

    public FormularioAdicaoComponentePage informaNomeComponente(String nomeComponente) {
        campoNomeComponente.sendKeys(nomeComponente);
        return this;
    }

    public FormularioAdicaoComponentePage informaQTDComponente(String qtdComponente) {
        campoQtdComponente.sendKeys(qtdComponente);
        return this;
    }

    public FormularioEdicaoProdutoPage cancelarAdicaoComponente() {
        linkCancelar.click();
        return new FormularioEdicaoProdutoPage(driver);
    }

    public FormularioEdicaoProdutoPage submeterComponente() throws InterruptedException {
        linkSalvarComponente.click();
        Thread.sleep(4000);
        return new FormularioEdicaoProdutoPage(driver);
    }
    public FormularioEdicaoProdutoPage submeterMultiplosComponentes(String nomeComponente,String qtdComponente) throws InterruptedException {
        informaNomeComponente(nomeComponente);
        informaQTDComponente(qtdComponente);
        submeterComponente();
        return new FormularioEdicaoProdutoPage(driver);
    }
}
