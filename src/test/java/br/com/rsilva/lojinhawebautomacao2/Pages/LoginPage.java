package br.com.rsilva.lojinhawebautomacao2.Pages;

import br.com.rsilva.lojinhawebautomacao2.BasePages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginPage extends BasePages {
    @FindBy(css = ".input-field>h4")
    private WebElement elementoLogin;
    @FindBy(id = "usuario")
    private WebElement usuario;
    @FindBy(id = "senha")
    private WebElement senha;
    @FindBy(css = "button[type='submit']")
    private WebElement botaoLogin;
    @FindBy(css = ".input-field.s12>h4")
    private WebElement elementoLogout;

    public LoginPage(WebDriver driver) {
        super(driver);
    }
    public String verificaFormularioLogin(){
        return elementoLogin.getText();
    }
    public LoginPage informarUsuario(String usuario){
        this.usuario.sendKeys(usuario);
        return this;
    }
    public LoginPage informarSenha(String senha){
        this.senha.sendKeys(senha);
        return this;
    }
    public ListaDeProdutosPage submeterFormularioLogin(){
        this.botaoLogin.click();
        return new ListaDeProdutosPage(driver);
    }
    public String verificaLogOut(){
        return elementoLogout.getText();
    }
}
