# LojinhaWebAutomacao

## Descrição
Este projeto é um conjunto de testes funcionais automatizados em Java utilizando o framework JUnit e a ferramenta Selenium WebDriver. O projeto contém testes de partição de equivalência e testes de análise de valor limite, além de estar focado nos testes de fumaça.
<p align="center">
     <a alt="Java">
        <img src="https://img.shields.io/badge/Java-v17-blue.svg" />
    </a>
    <a alt="JUnit5">
        <img src="https://img.shields.io/badge/JUnit5-v5.10.0-darkred.svg" />
    </a> 
    <a alt="WebDriverManager">
        <img src="https://img.shields.io/badge/WebDriverManager-v5.6.2-green.svg" />
    </a>
    <a alt="Selenium">
        <img src="https://img.shields.io/badge/Selenium-v4.14.1-darkgreen.svg" />
    </a>
</p>

## Padrões de Testes
O projeto segue os seguintes padrões de testes:
- **Page Object**: O padrão Page Object é utilizado para representar as páginas da aplicação como objetos. Cada página é representada por uma classe que contém os elementos da página e os métodos que interagem com esses elementos.
- **Page Factory**: O padrão Page Factory é utilizado para inicializar os elementos da página. Através da anotação `@FindBy`, os elementos da página são inicializados automaticamente.
- **Fluent Page**: O padrão Fluent Page é utilizado para tornar o código mais legível e fácil de entender. Os métodos da classe Page são encadeados para criar uma sequência de ações que representam a interação do usuário com a página.
- **Singleton para o WebDriver**: O padrão Singleton é utilizado para garantir que apenas uma instância do WebDriver seja criada durante a execução dos testes.

## Configuração e Execução dos Testes
Para executar os testes, siga os seguintes passos:
1. Clone o repositório: git clone https://github.com/zrafael-silvaz/LojinhaWebAutomacao.git
2. Abra o projeto em sua IDE de preferência.
3. Execute a comando `mvn test`.

## Observações
- Certifique-se de ter o Java e o Maven instalados em sua máquina.
- Não é necessario ter o ChromeDriver instalado, pois é utilizado uma biblioteca de gerenciamento, onde é necessario apenas informar na classe GerenciadorWebDriver qual sera o navegador desejado.
