package br.com.rsilva.lojinhawebautomacao2.Tests.Produtos;

import br.com.rsilva.lojinhawebautomacao2.Pages.LoginPage;

import br.com.rsilva.lojinhawebautomacao2.Utils.GerenciadorWebDriver;
import br.com.rsilva.lojinhawebautomacao2.Utils.ScreenShot;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;

import java.io.IOException;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Tests Web do Modulo de Produtos")
public class ProdutosTest {
    private WebDriver driver;
    private String usuario = "rSilva";
    private String senha = "123456";

    @BeforeAll
    public static void beforeAll() throws IOException {
        ScreenShot.criarDiretorio();
    }
    @BeforeEach
    void setupTest() throws IOException {
        driver = GerenciadorWebDriver.newDrive();

        //System.setProperty("webdriver.chrome.driver", "caminho/do/chromedriver");
        //WebDriverManager.chromedriver().setup();
        //this.driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(2000));
        driver.manage().window().maximize();
        driver.get("http://165.227.93.41/lojinha-web/v2/");
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @Test
    @DisplayName("Nao e permitido registrar um produto sem um nome")
    public void testNaoEPermitidoRegistrarProdutoSemNome() throws IOException {
        String mensagemApresentada = new LoginPage(driver)
                .informarUsuario(usuario)
                .informarSenha(senha)
                .submeterFormularioLogin()
                .acessarFormAddProduto()
                .informarNomeProduto("")
                .informarValorProduto("500")
                .informarCoresProduto("Red, blue")
                .submeterFormularioAdicaoComErro()
                .capturaMensagemApresentada();
        ScreenShot.salvarScreenshot(driver,"testNaoEPermitidoRegistrarProdutoSemNome");
        assertEquals("O produto precisa ter um nome", mensagemApresentada);
    }

    @Test
    @DisplayName("Nao e permitido registrar um produto sem cor")
    public void testNaoEPermitidoRegistrarProdutoSemCor() throws IOException {
        String mensagemApresentada = new LoginPage(driver)
                .informarUsuario(usuario)
                .informarSenha(senha)
                .submeterFormularioLogin()
                .acessarFormAddProduto()
                .informarNomeProduto("Mouse")
                .informarValorProduto("7000")
                .informarCoresProduto("")
                .submeterFormularioAdicaoComErro()
                .capturaMensagemApresentada();
        ScreenShot.salvarScreenshot(driver, "testNaoEPermitidoRegistrarProdutoSemCor");
        assertEquals("O produto precisa ter uma cor", mensagemApresentada);
    }
    @Test
    @DisplayName("Nao e permitido registrar um produto com valor igual a zero")
    public void testNaoEPermitidoRegistrarProdutoValorZerado() throws IOException {
        String mensagemApresentada = new LoginPage(driver)
                .informarUsuario(usuario)
                .informarSenha(senha)
                .submeterFormularioLogin()
                .acessarFormAddProduto()
                .informarNomeProduto("NoteBook")
                .informarValorProduto("0000")
                .informarCoresProduto("Red, blue")
                .submeterFormularioAdicaoComErro()
                .capturaMensagemApresentada();
        ScreenShot.salvarScreenshot(driver, "testNaoEPermitidoRegistrarProdutoValorZerado");
        assertEquals("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00", mensagemApresentada);
    }
    @Test
    @DisplayName("É permitido registrar um produto com valor igual a 0,01 - limite")
    public void testEPermitidoRegistrarProdutoValorUmCentavo() throws IOException {
        String mensagemApresentada = new LoginPage(driver)
                .informarUsuario(usuario)
                .informarSenha(senha)
                .submeterFormularioLogin()
                .acessarFormAddProduto()
                .informarNomeProduto("NoteBook")
                .informarValorProduto("0,01")
                .informarCoresProduto("Red, blue")
                .submeterFormularioAdicaoComErro()
                .capturaMensagemApresentada();
        ScreenShot.salvarScreenshot(driver, "testEPermitidoRegistrarProdutoValorUmCentavo");
        assertEquals("Produto adicionado com sucesso", mensagemApresentada);
    }
    @Test
    @DisplayName("É permitido registrar um produto com valor igual a 7.000,00 - limite")
    public void testEPermitidoRegistrarProdutoValorSeteMil() throws IOException {
        String mensagemApresentada = new LoginPage(driver)
                .informarUsuario(usuario)
                .informarSenha(senha)
                .submeterFormularioLogin()
                .acessarFormAddProduto()
                .informarNomeProduto("NoteBook")
                .informarValorProduto("700000")
                .informarCoresProduto("Red, blue")
                .submeterFormularioAdicaoComErro()
                .capturaMensagemApresentada();
        ScreenShot.salvarScreenshot(driver, "testEPermitidoRegistrarProdutoValorSeteMil");
        assertEquals("Produto adicionado com sucesso", mensagemApresentada);
    }
    @Test
    @DisplayName("Nao permitido registrar produto acima de 7 mil - limite")
    public void testNaoPermiteProdutoValorAcimaSeteMil() throws IOException {
        String mensagemApresentada = new LoginPage(driver)
                .informarUsuario(usuario)
                .informarSenha(senha)
                .submeterFormularioLogin()
                .acessarFormAddProduto()
                .informarNomeProduto("NoteBook")
                .informarValorProduto("700100")
                .informarCoresProduto("Red, blue")
                .submeterFormularioAdicaoComErro()
                .capturaMensagemApresentada();
        ScreenShot.salvarScreenshot(driver, "testNaoPermiteProdutoValorAcimaSeteMil");
        assertEquals("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00", mensagemApresentada);
    }

    @Test
    @DisplayName("Registrando produto com valor valido - limite")
    public void testAdicionarProdutoValorValido() throws IOException {
        String mensagemApresentada = new LoginPage(driver)
                .informarUsuario(usuario)
                .informarSenha(senha)
                .submeterFormularioLogin()
                .acessarFormAddProduto()
                .informarNomeProduto("NoteBook")
                .informarValorProduto("500000")
                .informarCoresProduto("Red, blue")
                .submeterFormularioAdicao()
                .capturaMensagemApresentada();
        ScreenShot.salvarScreenshot(driver, "testAdicionarProdutoValorValido");
        assertEquals("Produto adicionado com sucesso", mensagemApresentada);
    }
    @Test
    @DisplayName("Verificando mascara do campo valor")
    public void testVerificaMascaraValor() throws IOException {
        String mensagemApresentada = new LoginPage(driver)
                .informarUsuario(usuario)
                .informarSenha(senha)
                .submeterFormularioLogin()
                .acessarFormAddProduto()
                .informarNomeProduto("NoteBook")
                .informarValorProduto("500000")
                .informarCoresProduto("Red, blue")
                .submeterFormularioAdicao()
                .verificaMascaraValor("5.000,00");
        ScreenShot.salvarScreenshot(driver, "testVerificaProdutoAdicionado");
        assertEquals("Produto conforme", mensagemApresentada);
    }

    @Test
    @DisplayName("Removendo produto")
    public void testRemoverProduto() throws IOException {
        String mensagemApresentada = new LoginPage(driver)
                .informarUsuario(usuario)
                .informarSenha(senha)
                .submeterFormularioLogin()
                .removerProduto()
                .capturaMensagemApresentada();
        ScreenShot.salvarScreenshot(driver, "testRemoverProduto");
        assertEquals("Produto removido com sucesso", mensagemApresentada);
    }
    @Test
    @DisplayName("Removendo multiplos produtos")
    public void testRemoverMultiplosProdutos() throws IOException {
        String mensagemApresentada = new LoginPage(driver)
                .informarUsuario(usuario)
                .informarSenha(senha)
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
        ScreenShot.salvarScreenshot(driver, "testRemoverMultiplosProdutos");
        assertEquals("Produto removido com sucesso", mensagemApresentada);
    }
    @Test
    @DisplayName("Registrando produto com componente")
    public void testAdicionarComponente() throws InterruptedException, IOException {
        String mensagemApresentada = new LoginPage(driver)
                .informarUsuario(usuario)
                .informarSenha(senha)
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
        ScreenShot.salvarScreenshot(driver, "testAdicionarComponente");
        assertEquals("Componente de produto adicionado com sucesso", mensagemApresentada);
    }
    @Test
    @DisplayName("Registrando produto com 8 componentes - limite")
    public void testAdicionarMultiplosComponentes() throws InterruptedException, IOException {
        String nomeComponente = "Cabo de Forca";
        String qtdComponente = "1";
        String mensagemApresentada = new LoginPage(driver)
                .informarUsuario(usuario)
                .informarSenha(senha)
                .submeterFormularioLogin()
                .acessarFormAddProduto()
                .informarNomeProduto("NoteBook")
                .informarValorProduto("500000")
                .informarCoresProduto("Red, blue")
                .submeterFormularioAdicao()
                .adicionarComponente()
                .submeterMultiplosComponentes(nomeComponente,qtdComponente)
                .adicionarComponente()
                .submeterMultiplosComponentes(nomeComponente,qtdComponente)
                .adicionarComponente()
                .submeterMultiplosComponentes(nomeComponente,qtdComponente)
                .adicionarComponente()
                .submeterMultiplosComponentes(nomeComponente,qtdComponente)
                .adicionarComponente()
                .submeterMultiplosComponentes(nomeComponente,qtdComponente)
                .adicionarComponente()
                .submeterMultiplosComponentes(nomeComponente,qtdComponente)
                .adicionarComponente()
                .submeterMultiplosComponentes(nomeComponente,qtdComponente)
                .adicionarComponente()
                .submeterMultiplosComponentes(nomeComponente,qtdComponente)
                .adicionarComponente()
                .submeterMultiplosComponentes(nomeComponente,qtdComponente)
                .adicionarComponente()
                .submeterMultiplosComponentes(nomeComponente,qtdComponente)
                .adicionarComponente()
                .submeterMultiplosComponentes(nomeComponente,qtdComponente)
                .adicionarComponente()
                .submeterMultiplosComponentes(nomeComponente,qtdComponente)
                .adicionarComponente()
                .submeterMultiplosComponentes(nomeComponente,qtdComponente)
                .adicionarComponente()
                .submeterMultiplosComponentes(nomeComponente,qtdComponente)
                .adicionarComponente()
                .submeterMultiplosComponentes(nomeComponente,qtdComponente)
                .adicionarComponente()
                .submeterMultiplosComponentes(nomeComponente,qtdComponente)
                .capturaMensagemApresentada();
        ScreenShot.salvarScreenshot(driver, "testAdicionarComponente");
        assertEquals("Componente de produto adicionado com sucesso", mensagemApresentada);
    }
    @Test
    @DisplayName("Registrando produto com componente sem um nome")
    public void testAdicionarComponenteSemNome() throws InterruptedException, IOException {
        String mensagemApresentada = new LoginPage(driver)
                .informarUsuario(usuario)
                .informarSenha(senha)
                .submeterFormularioLogin()
                .editandoProduto()
                .adicionarComponente()
                .informaNomeComponente("")
                .informaQTDComponente("1")
                .submeterComponente()
                .capturaMensagemApresentada();
        ScreenShot.salvarScreenshot(driver, "testAdicionarComponenteSemNome");
        assertEquals("Informe um nome para o componente", mensagemApresentada);
    }

    @Test
    @DisplayName("Registrando produto e um componente com quantidade zero")
    public void testAdicionarComponenteQTDZero() throws InterruptedException, IOException {
        String mensagemApresentada = new LoginPage(driver)
                .informarUsuario(usuario)
                .informarSenha(senha)
                .submeterFormularioLogin()
                .editandoProduto()
                .adicionarComponente()
                .informaNomeComponente("Arfryer")
                .informaQTDComponente("")
                .submeterComponente()
                .capturaMensagemApresentada();
        ScreenShot.salvarScreenshot(driver, "testAdicionarComponenteQTDZero");
        assertEquals("A quantidade mínima para o componente não deve ser inferior a 1", mensagemApresentada);
    }

    @Test
    @DisplayName("Cancelando adicao de componente")
    public void testCancelandoAdicaoComponente() throws InterruptedException, IOException {
        String mensagemApresentada = new LoginPage(driver)
                .informarUsuario(usuario)
                .informarSenha(senha)
                .submeterFormularioLogin()
                .editandoProduto()
                .adicionarComponente()
                .informaNomeComponente("Arfryer")
                .informaQTDComponente("1")
                .cancelarAdicaoComponente()
                .capturaMensagemApresentada();
        ScreenShot.salvarScreenshot(driver, "testCancelandoAdicaoComponente");
        assertEquals("Nao ha mensagem", mensagemApresentada);
    }

    @Test
    @DisplayName("Removendo Componente")
    public void testRemoverComponente() throws InterruptedException {
        String mensagemApresentada = new LoginPage(driver)
                .informarUsuario(usuario)
                .informarSenha(senha)
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
        ScreenShot.salvarScreenshot(driver, "testRemoverComponente");
        assertEquals("Componente de produto removido com sucesso", mensagemApresentada);
    }
    @Test
    @DisplayName("Removendo multiplos Componentes")
    public void testRemoverMultiplosComponentes() throws IOException, InterruptedException {
        String mensagemApresentada = new LoginPage(driver)
                .informarUsuario(usuario)
                .informarSenha(senha)
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
        ScreenShot.salvarScreenshot(driver, "testRemoverMultiplosComponentes");
        assertEquals("Componente de produto removido com sucesso", mensagemApresentada);
    }
}
