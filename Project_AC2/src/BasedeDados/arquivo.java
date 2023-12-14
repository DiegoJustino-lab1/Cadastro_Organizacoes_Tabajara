package BasedeDados;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class arquivo {
    private File file;

    public arquivo(String filePath) {
        file = new File(filePath);
        try {
            boolean isCreated = file.createNewFile();
            System.out.println("O arquivo foi criado? " + isCreated);
            System.out.println("O caminho do arquivo é: " + file.getAbsolutePath());
            System.out.println("O nome do arquivo é: " + file.getName());
            System.out.println("O arquivo pode ser lido? " + file.canRead());
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void escrever(String conteudo) {
        try (FileWriter fw = new FileWriter(file, true)) {
            fw.write(conteudo + "\n");
            fw.flush();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

}