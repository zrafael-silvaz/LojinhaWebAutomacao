package Modulos.Produtos;

import Paginas.LoginPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Tests Web do Modulo de Produtos")
public class ProdutosTest {

    private WebDriver driver;
    private LoginPage loginPage;

    @BeforeEach
    void setupTest(){
        driver = WebDriverManager.chromedriver().create();
        //WebDriverManager.chromedriver().setup();
        //this.driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(2000));
        driver.manage().window().maximize();
        driver.get("http://165.227.93.41/lojinha-web/v2/");
        WebElement textLogo = driver.findElement(By.id("logo-container"));
        assertEquals("Lojinha",textLogo.getText());
    }

    @AfterEach
    void tearDown(){
        driver.quit();
    }

    @Test
    @DisplayName("Nao e permitido registrar um produto sem um nome")
    public void testNaoEPermitidoRegistrarProdutoSemNome(){
        String mensagemApresentada = new LoginPage(driver)
                .informarUsuario("rSilva")
                .informarSenha("123456")
                .submeterFormularioLogin()
                .acessarFormAddProduto()
                .informarNomeProduto("")
                .informarValorProduto("500")
                .informarCoresProduto("Red, blue")
                .submeterFormularioAdicaoComErro()
                .capturaMensagemApresentada();
        assertEquals("O produto precisa ter um nome", mensagemApresentada);
    }
    @Test
    @DisplayName("Nao e permitido registrar um produto sem cor")
    public void testNaoEPermitidoRegistrarProdutoSemCor(){
        String mensagemApresentada = new LoginPage(driver)
                .informarUsuario("rSilva")
                .informarSenha("123456")
                .submeterFormularioLogin()
                .acessarFormAddProduto()
                .informarNomeProduto("Mouse")
                .informarValorProduto("7000")
                .informarCoresProduto("")
                .submeterFormularioAdicaoComErro()
                .capturaMensagemApresentada();
        assertEquals("O produto precisa ter uma cor", mensagemApresentada);
    }

    @Test
    @DisplayName("Nao e permitido registrar um produto com valor igual a zero")
    public void testNaoEPermitidoRegistrarProdutoValorZerado(){
        String mensagemApresentada = new LoginPage(driver)
                .informarUsuario("rSilva")
                .informarSenha("123456")
                .submeterFormularioLogin()
                .acessarFormAddProduto()
                .informarNomeProduto("NoteBook")
                .informarValorProduto("0000")
                .informarCoresProduto("Red, blue")
                .submeterFormularioAdicaoComErro()
                .capturaMensagemApresentada();
        assertEquals("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00", mensagemApresentada);
    }
    @Test
    @DisplayName("Nao permitido registrar produto acima de 7 mil")
    public void testNaoPermiteProdutoValorAcimaSeteMil(){
        String mensagemApresentada = new LoginPage(driver)
                .informarUsuario("rSilva")
                .informarSenha("123456")
                .submeterFormularioLogin()
                .acessarFormAddProduto()
                .informarNomeProduto("NoteBook")
                .informarValorProduto("700100")
                .informarCoresProduto("Red, blue")
                .submeterFormularioAdicaoComErro()
                .capturaMensagemApresentada();

        assertEquals("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00",mensagemApresentada);
    }
    @Test
    @DisplayName("Registrando produto com dados validos")
    public void testAdicionarProdutoValorValido(){
        String mensagemApresentada = new LoginPage(driver)
                .informarUsuario("rSilva")
                .informarSenha("123456")
                .submeterFormularioLogin()
                .acessarFormAddProduto()
                .informarNomeProduto("NoteBook")
                .informarValorProduto("5000")
                .informarCoresProduto("Red, blue")
                .submeterFormularioAdicao()
                .capturaMensagemApresentada();

        assertEquals("Produto adicionado com sucesso",mensagemApresentada);
    }
    @Test
    @DisplayName("Removendo produto")
    public void testRemoverProduto(){
        String mensagemApresentada = new LoginPage(driver)
                .informarUsuario("rSilva")
                .informarSenha("123456")
                .submeterFormularioLogin()
                .removerProduto()
                .capturaMensagemApresentada();

        assertEquals("Produto removido com sucesso",mensagemApresentada);
    }
    @Test
    @DisplayName("Registrando produto com componente")
    public void testAdicionarComponente() throws InterruptedException {
        String mensagemApresentada = new LoginPage(driver)
                .informarUsuario("rSilva")
                .informarSenha("123456")
                .submeterFormularioLogin()
                .acessarFormAddProduto()
                .informarNomeProduto("NoteBook")
                .informarValorProduto("500000")
                .informarCoresProduto("Red, blue")
                .submeterFormularioAdicao()
                .adicionarComponente()
                .informaNomeComponente("Cabo de Forca")
                .informaQTDComponente("1")
                .submeterComponente()
                .capturaMensagemApresentada();

        assertEquals("Componente de produto adicionado com sucesso",mensagemApresentada);
    }
    @Test
    @DisplayName("Removendo Componente")
    public void testRemoverComponente() {
        String mensagemApresentada = new LoginPage(driver)
                .informarUsuario("rSilva")
                .informarSenha("123456")
                .submeterFormularioLogin()
                .editandoProduto()
                .removerComponente()
                .capturaMensagemApresentada();

        assertEquals("Componente de produto removido com sucesso",mensagemApresentada);
    }
}
