package Modulos.Produtos;

import Paginas.LoginPage;
import Utils.ScreenShot;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.IOException;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Tests Web do Modulo de Produtos")
public class ProdutosTest {

    private WebDriver driver;

    @BeforeEach
    void setupTest() {
        driver = WebDriverManager.chromedriver().create();

        //System.setProperty("webdriver.chrome.driver", "caminho/do/chromedriver");

        //WebDriverManager.chromedriver().setup();
        //this.driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(2000));
        driver.manage().window().maximize();
        driver.get("http://165.227.93.41/lojinha-web/v2/");
        WebElement textLogo = driver.findElement(By.id("logo-container"));
        assertEquals("Lojinha", textLogo.getText());
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @Test
    @DisplayName("Nao e permitido registrar um produto sem um nome")
    public void testNaoEPermitidoRegistrarProdutoSemNome() throws IOException {
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
        ScreenShot.sS(driver, "testNaoEPermitidoRegistrarProdutoSemNome");
        assertEquals("O produto precisa ter um nome", mensagemApresentada);
    }

    @Test
    @DisplayName("Nao e permitido registrar um produto sem cor")
    public void testNaoEPermitidoRegistrarProdutoSemCor() throws IOException {
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
        ScreenShot.sS(driver, "testNaoEPermitidoRegistrarProdutoSemCor");
        assertEquals("O produto precisa ter uma cor", mensagemApresentada);
    }

    @Test
    @DisplayName("Nao e permitido registrar um produto com valor igual a zero")
    public void testNaoEPermitidoRegistrarProdutoValorZerado() throws IOException {
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
        ScreenShot.sS(driver, "testNaoEPermitidoRegistrarProdutoValorZerado");
        assertEquals("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00", mensagemApresentada);
    }

    @Test
    @DisplayName("Nao permitido registrar produto acima de 7 mil")
    public void testNaoPermiteProdutoValorAcimaSeteMil() throws IOException {
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
        ScreenShot.sS(driver, "testNaoPermiteProdutoValorAcimaSeteMil");
        assertEquals("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00", mensagemApresentada);
    }

    @Test
    @DisplayName("Registrando produto com dados validos")
    public void testAdicionarProdutoValorValido() throws IOException {
        String mensagemApresentada = new LoginPage(driver)
                .informarUsuario("rSilva")
                .informarSenha("123456")
                .submeterFormularioLogin()
                .acessarFormAddProduto()
                .informarNomeProduto("NoteBook")
                .informarValorProduto("500000")
                .informarCoresProduto("Red, blue")
                .submeterFormularioAdicao()
                .capturaMensagemApresentada();
        ScreenShot.sS(driver, "testAdicionarProdutoValorValido");
        assertEquals("Produto adicionado com sucesso", mensagemApresentada);
    }
    @Test
    @DisplayName("Vericando produto adicionado")
    public void testVerificaProdutoAdicionado() throws IOException {
        String mensagemApresentada = new LoginPage(driver)
                .informarUsuario("rSilva")
                .informarSenha("123456")
                .submeterFormularioLogin()
                .acessarFormAddProduto()
                .informarNomeProduto("NoteBook")
                .informarValorProduto("500000")
                .informarCoresProduto("Red, blue")
                .submeterFormularioAdicao()
                .verificaProdutoAdicionado("NoteBook","500000","Red, blue");
        ScreenShot.sS(driver, "testAdicionarProdutoValorValido");
        assertEquals("Produto verificado", mensagemApresentada);
    }

    @Test
    @DisplayName("Removendo produto")
    public void testRemoverProduto() throws IOException {
        String mensagemApresentada = new LoginPage(driver)
                .informarUsuario("rSilva")
                .informarSenha("123456")
                .submeterFormularioLogin()
                .removerProduto()
                .capturaMensagemApresentada();
        ScreenShot.sS(driver, "testRemoverProduto");
        assertEquals("Produto removido com sucesso", mensagemApresentada);
    }
    @Test
    @DisplayName("Removendo multiplos produtos")
    public void testRemoverMultiplosProdutos() throws IOException {
        String mensagemApresentada = new LoginPage(driver)
                .informarUsuario("rSilva")
                .informarSenha("123456")
                .submeterFormularioLogin()
                .acessarFormAddProduto()
                .informarNomeProduto("Airfryer")
                .informarValorProduto("40000")
                .informarCoresProduto("Black, Red")
                .submeterFormularioAdicao()
                .navegarListaProduto()
                .removerProduto()
                .removerProduto()
                .capturaMensagemApresentada();
        ScreenShot.sS(driver, "testRemoverProduto");
        assertEquals("Produto removido com sucesso", mensagemApresentada);
    }

    @Test
    @DisplayName("Registrando produto com componente")
    public void testAdicionarComponente() throws InterruptedException, IOException {
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
        ScreenShot.sS(driver, "testAdicionarComponente");
        assertEquals("Componente de produto adicionado com sucesso", mensagemApresentada);
    }

    @Test
    @DisplayName("Registrando produto com componente sem um nome")
    public void testAdicionarComponenteSemNome() throws InterruptedException, IOException {
        String mensagemApresentada = new LoginPage(driver)
                .informarUsuario("rSilva")
                .informarSenha("123456")
                .submeterFormularioLogin()
                .editandoProduto()
                .adicionarComponente()
                .informaNomeComponente("")
                .informaQTDComponente("1")
                .submeterComponente()
                .capturaMensagemApresentada();
        ScreenShot.sS(driver, "testAdicionarComponenteSemNome");
        assertEquals("Informe um nome para o componente", mensagemApresentada);
    }

    @Test
    @DisplayName("Registrando produto com componente com quantidade zero")
    public void testAdicionarComponenteQTDZero() throws InterruptedException, IOException {
        String mensagemApresentada = new LoginPage(driver)
                .informarUsuario("rSilva")
                .informarSenha("123456")
                .submeterFormularioLogin()
                .editandoProduto()
                .adicionarComponente()
                .informaNomeComponente("Arfryer")
                .informaQTDComponente("")
                .submeterComponente()
                .capturaMensagemApresentada();
        ScreenShot.sS(driver, "testAdicionarComponenteQTDZero");
        assertEquals("A quantidade mínima para o componente não deve ser inferior a 1", mensagemApresentada);
    }

    @Test
    @DisplayName("Cancelando adicao de componente")
    public void testCancelandoAdicaoComponente() throws InterruptedException, IOException {
        String mensagemApresentada = new LoginPage(driver)
                .informarUsuario("rSilva")
                .informarSenha("123456")
                .submeterFormularioLogin()
                .editandoProduto()
                .adicionarComponente()
                .informaNomeComponente("Arfryer")
                .informaQTDComponente("1")
                .cancelarAdicaoComponente()
                .capturaMensagemApresentada();
        ScreenShot.sS(driver, "testCancelandoAdicaoComponente");
        assertEquals("Nao ha mensagem", mensagemApresentada);
    }

    @Test
    @DisplayName("Removendo Componente")
    public void testRemoverComponente() throws IOException, InterruptedException {
        String mensagemApresentada = new LoginPage(driver)
                .informarUsuario("rSilva")
                .informarSenha("123456")
                .submeterFormularioLogin()
                .editandoProduto()
                .adicionarComponente()
                .informaNomeComponente("Cabo de Forca")
                .informaQTDComponente("1")
                .submeterComponente()
                .adicionarComponente()
                .informaNomeComponente("Chave manutencao")
                .informaQTDComponente("2")
                .submeterComponente()
                .removerComponente()
                .capturaMensagemApresentada();
        ScreenShot.sS(driver, "testRemoverComponente");
        assertEquals("Componente de produto removido com sucesso", mensagemApresentada);
    }
    @Test
    @DisplayName("Removendo multiplos Componentes")
    public void testRemoverMultiplosComponentes() throws IOException, InterruptedException {
        String mensagemApresentada = new LoginPage(driver)
                .informarUsuario("rSilva")
                .informarSenha("123456")
                .submeterFormularioLogin()
                .editandoProduto()
                .adicionarComponente()
                .informaNomeComponente("Cabo de Forca")
                .informaQTDComponente("1")
                .submeterComponente()
                .adicionarComponente()
                .informaNomeComponente("Chave manutencao")
                .informaQTDComponente("2")
                .submeterComponente()
                .adicionarComponente()
                .informaNomeComponente("Suporte Universal")
                .informaQTDComponente("1")
                .submeterComponente()
                .removerComponente()
                .removerComponente()
                .capturaMensagemApresentada();
        ScreenShot.sS(driver, "testRemoverMultiplosComponentes");
        assertEquals("Componente de produto removido com sucesso", mensagemApresentada);
    }

    @Test
    @DisplayName("Fazendo Logout")
    public void logOut() {
        String mensagemApresentada = new LoginPage(driver)
                .informarUsuario("rSilva")
                .informarSenha("123456")
                .submeterFormularioLogin()
                .logOut()
                .verificaLogOut();
        assertEquals("Acessar a Lojinha", mensagemApresentada);
    }
}
