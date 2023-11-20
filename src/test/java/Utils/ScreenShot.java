package Utils;

import com.google.common.io.Files;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;

public class ScreenShot {

    public static void salvarScreenshot(WebDriver driver, String nomeArquivo) {
        try{
            File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            //FileUtils.copyFile(file,new File("C:\\Users\\rafae\\workspace\\EstudosTestsWeb\\LojinhaWebAutomacao2\\screenshots\\" + nomeArquivo + ".png"));
            Files.copy(file,new File( GerenciadorDiretorios.gerenciar()+"\\"+ nomeArquivo + ".png"));
            System.out.println("Screenshsot capturado com sucesso!");

        }catch (Exception e){
            System.out.println("Erro ao capturar o screenshot!");
        }
    }
}



