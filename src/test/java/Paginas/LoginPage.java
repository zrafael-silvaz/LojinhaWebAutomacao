package Paginas;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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
}
