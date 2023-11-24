package br.com.rsilva.lojinhawebautomacao2.Tests.Login;

import br.com.rsilva.lojinhawebautomacao2.Pages.LoginPage;
import br.com.rsilva.lojinhawebautomacao2.Utils.GerenciadorWebDriver;
import br.com.rsilva.lojinhawebautomacao2.Utils.ScreenShot;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestLogin {
    private static WebDriver driver;
    @BeforeAll
    public static void beforeAll() throws IOException {
        ScreenShot.criarDiretorio();
    }
    @BeforeEach
    @DisplayName("Construe o driver e testa se esta mesmo na tela de login")
    @Order(1)
    public void setupTest() {
        driver = GerenciadorWebDriver.newDrive();
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
    public void testVerificaLoginPage() throws IOException {
        String element = new LoginPage(driver)
                .verificaFormularioLogin();
        ScreenShot.salvarScreenshot(driver, "verificaLoginPage");
        assertEquals("Acessar a Lojinha", element);
    }

    @Test
    @DisplayName("Submeter formulario login com dados corretos")
    public void testSubmeterFormularioLogin() throws IOException {
        String mensagemApresentada = new LoginPage(driver)
                .informarUsuario("rsilva")
                .informarSenha("123456")
                .submeterFormularioLogin()
                .verificarLogin();
        ScreenShot.salvarScreenshot(driver, "submeterFormularioLogin");
        assertEquals("Boas vindas, rafael!", mensagemApresentada);
    }

    @ParameterizedTest
    @CsvSource({
            "'',123456",
            "srilva,''",
            "srilva,testInvalido",
            "testInvalido,testInvalido"
    })
    @DisplayName("Submeter formulario com dados incorretos")
    public void testSubmeterFormularioLoginSemUser(String usuario, String senha) throws IOException {
        String mensagemApresentada = new LoginPage(driver)
                .informarUsuario(usuario)
                .informarSenha(senha)
                .submeterFormularioLogin()
                .capturaMensagemApresentada();
        ScreenShot.salvarScreenshot(driver, "submeterFormularioSemUser");
        assertEquals("Usuario ou senha invalidos", mensagemApresentada);
    }

//    @Test
//    @DisplayName("Submeter formulario login sem senha de usuario")
//    public void testSubmeterFormularioLoginSemSenha() throws IOException {
//        String mensagemApresentada = new LoginPage(driver)
//                .informarUsuario("srilva")
//                .informarSenha("")
//                .submeterFormularioLogin()
//                .capturaMensagemApresentada();
//        ScreenShot.salvarScreenshot(driver, "submeterFormularioLoginSemSenha");
//        assertEquals("Usuario ou senha invalidos", mensagemApresentada);
//    }
//
//    @Test
//    @DisplayName("Submeter formulario login com senha invalida")
//    public void testSubmeterFormularioLoginSenhaInvalida() throws IOException {
//        String mensagemApresentada = new LoginPage(driver)
//                .informarUsuario("rsilva")
//                .informarSenha("testInvalido")
//                .submeterFormularioLogin()
//                .capturaMensagemApresentada();
//        ScreenShot.salvarScreenshot(driver, "submeterFormularioLoginSenhaInvalida");
//        assertEquals("Usuario ou senha invalidos", mensagemApresentada);
//    }
//
//    @Test
//    @DisplayName("Submeter formulario login com dados invalidos")
//    public void testSubmeterFormularioLoginDadosInvalidos() throws IOException {
//        String mensagemApresentada = new LoginPage(driver)
//                .informarUsuario("testInvalido")
//                .informarSenha("testInvalido")
//                .submeterFormularioLogin()
//                .capturaMensagemApresentada();
//        ScreenShot.salvarScreenshot(driver, "submeterFormularioLoginDadosInvalidos");
//        assertEquals("Usuario ou senha invalidos", mensagemApresentada);
//    }
    @Test
    @DisplayName("Fazendo Logout")
    public void testLogOut() {
        String mensagemApresentada = new LoginPage(driver)
                .informarUsuario("rSilva")
                .informarSenha("123456")
                .submeterFormularioLogin()
                .logOut()
                .verificaLogOut();
        ScreenShot.salvarScreenshot(driver, "logOut");
        assertEquals("Acessar a Lojinha", mensagemApresentada);
    }
}
