package Modulos.Login;

import Paginas.LoginPage;
import Utils.ScreenShot;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginTest {
    private WebDriver driver;

    @BeforeEach
    @DisplayName("Construe o driver e testa se esta mesmo na tela de login")
    public void setupTest() {
        driver = WebDriverManager.chromedriver().create();

        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(4000));
        driver.manage().window().maximize();

        driver.get("http://165.227.93.41/lojinha-web/v2/");
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    @DisplayName("Verificar se a pagina login foi acessada")
    public void verificaLoginPage() throws IOException {
        String element = new LoginPage(driver)
                .verificaFormularioLogin();
        ScreenShot.salvarScreenshot(driver, "verificaLoginPage");
        assertEquals("Acessar a Lojinha", element);
    }

    @Test
    @DisplayName("Submeter formulario login com dados corretos")
    public void submeterFormularioLogin() throws IOException {
        String mensagemApresentada = new LoginPage(driver)
                .informarUsuario("rsilva")
                .informarSenha("123456")
                .submeterFormularioLogin()
                .verificarLogin();
        ScreenShot.salvarScreenshot(driver, "submeterFormularioLogin");
        assertEquals("Boas vindas, rafael!", mensagemApresentada);
    }

    @Test
    @DisplayName("Submeter formulario login sem nome de usuario")
    public void submeterFormularioLoginSemUser() throws IOException {
        String mensagemApresentada = new LoginPage(driver)
                .informarUsuario("")
                .informarSenha("123456")
                .submeterFormularioLogin()
                .capturaMensagemApresentada();
        ScreenShot.salvarScreenshot(driver, "submeterFormularioSemUser");
        assertEquals("Usuario ou senha invalidos", mensagemApresentada);
    }

    @Test
    @DisplayName("Submeter formulario login sem senha de usuario")
    public void submeterFormularioLoginSemSenha() throws IOException {
        String mensagemApresentada = new LoginPage(driver)
                .informarUsuario("srilva")
                .informarSenha("")
                .submeterFormularioLogin()
                .capturaMensagemApresentada();
        ScreenShot.salvarScreenshot(driver, "submeterFormularioLoginSemSenha");
        assertEquals("Usuario ou senha invalidos", mensagemApresentada);
    }

    @Test
    @DisplayName("Submeter formulario login com senha invalida")
    public void submeterFormularioLoginSenhaInvalida() throws IOException {
        String mensagemApresentada = new LoginPage(driver)
                .informarUsuario("rsilva")
                .informarSenha("testInvalido")
                .submeterFormularioLogin()
                .capturaMensagemApresentada();
        ScreenShot.salvarScreenshot(driver, "submeterFormularioLoginSenhaInvalida");
        assertEquals("Usuario ou senha invalidos", mensagemApresentada);
    }

    @Test
    @DisplayName("Submeter formulario login com dados invalidos")
    public void submeterFormularioLoginDadosInvalidos() throws IOException {
        String mensagemApresentada = new LoginPage(driver)
                .informarUsuario("testInvalido")
                .informarSenha("testInvalido")
                .submeterFormularioLogin()
                .capturaMensagemApresentada();
        ScreenShot.salvarScreenshot(driver, "submeterFormularioLoginDadosInvalidos");
        assertEquals("Usuario ou senha invalidos", mensagemApresentada);
    }

}
