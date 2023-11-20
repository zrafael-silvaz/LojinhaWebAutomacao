package Utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

public class GerenciadorDiretorios {
    private static String nomeDiretorio = (LocalDate.now()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    private static String caminhoDiretorioScreenshots = "screenshots\\";
    private static Path novoDiretorio = Paths.get(caminhoDiretorioScreenshots + nomeDiretorio);

    public static Path gerenciar() throws IOException {
        criarDiretorio();
        limparDiretorio();
        return novoDiretorio;
    }
    private static void criarDiretorio() throws IOException {
        if (!Files.exists(novoDiretorio)) {
            Files.createDirectory(novoDiretorio);
            System.out.println("Diretório criado: " + nomeDiretorio);
        } else {
            System.out.println("Diretório já existe: " + nomeDiretorio);
        }
    }
    private static void limparDiretorio() {
        try (Stream<Path> paths = Files.walk(novoDiretorio)) {
            long count = paths.count();
            if (count > 0) {
                Files.walk(novoDiretorio)
                        .filter(Files::isRegularFile)
                        .forEach(file -> {
                            try {
                                Files.delete(file);
                            } catch (IOException e) {
                                System.err.println("Erro ao excluir arquivo: " + e.getMessage());
                            }
                        });
                System.out.println("Diretório limpo: " + novoDiretorio.getFileName());
            } else {
                System.out.println("Diretório está vazio: " + novoDiretorio.getFileName());
            }
        } catch (IOException e) {
            System.err.println("Erro ao limpar diretório: " + e.getMessage());
        }
    }
}