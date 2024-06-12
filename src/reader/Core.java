package reader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import objects.Docente;

public class Core {
    File arquivo;
    private String caminho;
    private static final DateTimeFormatter dataTempoFormatter= DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final NumberFormat numeroFormatter = NumberFormat.getInstance(Locale.forLanguageTag("pt-BR"));

    public static File csvAbrirArquivo(String caminho){
        File arq = new File(caminho);
        return arq;
    }
    public static List<Docente> lerDocentes(String caminho) throws IOException {
        List<Docente> docentes = new ArrayList<>();
        List<String> linhas = Files.readAllLines(Paths.get(caminho));
        linhas.remove(0); // Remover cabeçalho
        for (String linha : linhas) {
            String[] campos = linha.split(";");
            long codigo = Long.parseLong(campos[0].trim());
            String nome = campos[1].trim();
            LocalDate dataNascimento = LocalDate.parse(campos[2].trim(), dataTempoFormatter);
            LocalDate dataIngresso = LocalDate.parse(campos[3].trim(), dataTempoFormatter);
            docentes.add(new Docente(codigo, nome, dataNascimento, dataIngresso));
        }
        return docentes;
    }
}
