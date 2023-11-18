package Paginas;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public LoginPage informarUsuario(String usuario){
        driver.findElement(By.id("usuario")).sendKeys(usuario);
        return this;
    }
    public LoginPage informarSenha(String senha){
        driver.findElement(By.id("senha")).sendKeys(senha);
        return this;
    }
    public ListaDeProdutosPage submeterFormularioLogin(){
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        return new ListaDeProdutosPage(driver);
    }
    public String verificaLogOut(){
        return driver.findElement(By.cssSelector(".input-field.s12>h4")).getText();
    }
}
