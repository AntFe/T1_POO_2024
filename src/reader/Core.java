package reader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import objects.Docente;
import objects.Ocorrencia;
import objects.Publicacao;
import objects.Veiculo;

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

public static List<Ocorrencia> lerOcorrencias(String caminho) throws IOException {
        List<Ocorrencia> ocorrencias = new ArrayList<>();
        List<String> linhas = Files.readAllLines(Paths.get(caminho));
        linhas.remove(0); // Remover cabeçalho


        for (String linha : linhas) {
            
            String[] campos = linha.split(";");
            long codigo = Long.parseLong(campos[0].trim());
            String evento = campos[1].trim();
            LocalDate dataInicio = LocalDate.parse(campos[2].trim(), dataTempoFormatter);
            LocalDate dataFim = LocalDate.parse(campos[3].trim(), dataTempoFormatter);

            ocorrencias.add(new Ocorrencia(codigo, evento, dataInicio, dataFim));
        }



        return ocorrencias;
    }

    public static List<Veiculo> lerVeiculos(String caminho) throws IOException {
        List<Veiculo> veiculos = new ArrayList<>();
        List<String> linhas = Files.readAllLines(Paths.get(caminho));
        linhas.remove(0); // Remover cabeçalho
        for (String linha : linhas) {
            String[] campos = linha.split(";");
            String sigla = campos[0].trim();
            String nome = campos[1].trim();
            char tipo = campos[2].trim().charAt(0);
            double fatorImpacto = 0;
            try {
                fatorImpacto = numeroFormatter.parse(campos[3].trim()).doubleValue();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String issn = campos.length > 4 ? campos[4].trim() : "";
            veiculos.add(new Veiculo(sigla, nome, tipo, fatorImpacto, issn));
        }
        return veiculos;
    }

    public static List<Publicacao> lerPublicacoes(String caminho) throws IOException {
        List<Publicacao> publicacoes = new ArrayList<>();
        List<String> linhas = Files.readAllLines(Paths.get(caminho));
        linhas.remove(0); // Remover cabeçalho
        for (String linha : linhas) {
            String[] campos = linha.split(";");
            int ano = Integer.parseInt(campos[0].trim());
            String siglaVeiculo = campos[1].trim();
            String titulo = campos[2].trim();
            List<Long> autores = new ArrayList<>();
            for (String autor : campos[3].split(",")) {
                autores.add(Long.parseLong(autor.trim()));
            }
            int numero = Integer.parseInt(campos[4].trim());
            int volume = campos[5].trim().isEmpty() ? 0 : Integer.parseInt(campos[5].trim());
            String local = campos.length > 6 ? campos[6].trim() : "";
            int paginaInicial = Integer.parseInt(campos[7].trim());
            int paginaFinal = Integer.parseInt(campos[8].trim());
            publicacoes.add(new Publicacao(ano, siglaVeiculo, titulo, autores, numero, volume, local, paginaInicial, paginaFinal));
        }
        return publicacoes;
    }

    // public static List<Qualificacao> lerQualificacoes(String caminho) throws IOException {
    //     List<Qualificacao> qualificacoes = new ArrayList<>();
    //     List<String> linhas = Files.readAllLines(Paths.get(caminho));
    //     linhas.remove(0); // Remover cabeçalho
    //     for (String linha : linhas) {
    //         String[] campos = linha.split(";");
    //         int ano = Integer.parseInt(campos[0].trim());
    //         String siglaVeiculo = campos[1].trim();
    //         String qualis = campos[2].trim();
    //         qualificacoes.add(new Qualificacao(ano, siglaVeiculo, qualis));
    //     }
    //     return qualificacoes;
    // }

    // public static List<Regra> lerRegras(String caminho) throws IOException {
    //     List<Regra> regras = new ArrayList<>();
    //     List<String> linhas = Files.readAllLines(Paths.get(caminho));
    //     linhas.remove(0); // Remover cabeçalho
    //     for (String linha : linhas) {
    //         String[] campos = linha.split(";");
    //         LocalDate dataInicio = LocalDate.parse(campos[0].trim(), dataTempoFormatter);
    //         LocalDate dataFim = LocalDate.parse(campos[1].trim(), dataTempoFormatter);

    //         Map<String, Double> qualisPontos = new HashMap<>();
    //         String[] qualis1 = campos[2].split("-");
    //         String[] pontos = campos[3].split("-");
    //         for (int i = 0; i < qualis1.length; i++) {
    //             try {
    //                 qualisPontos.put(qualis1[i].trim(), numeroFormatter.parse(pontos[i].trim()).doubleValue());
    //             } catch (ParseException e) {
    //                 e.printStackTrace();
    //             }
    //         }
    //         int qtdeAnosPontos = Integer.parseInt(campos[4].trim());

    //         Map<String, Integer> qualisMinimos = new HashMap<>();
    //         String[] qualis2 = campos[5].split("-");
    //         String[] qtdesMinimas = campos[6].split("-");
    //         for (int i = 0; i < qualis2.length; i++) {
    //             qualisMinimos.put(qualis2[i].trim(), Integer.parseInt(qtdesMinimas[i].trim()));
    //         }
    //         int qtdeAnosArtigos = Integer.parseInt(campos[7].trim());
    //         double pontuacaoMinima = 0;
    //         try {
    //             pontuacaoMinima = numeroFormatter.parse(campos[8].trim()).doubleValue();
    //         } catch (ParseException e) {
    //             e.printStackTrace();
    //         }

    //         regras.add(new Regra(dataInicio, dataFim, qualisPontos, qtdeAnosPontos, qualisMinimos, qtdeAnosArtigos, pontuacaoMinima));
    //     }
    //     return regras;
    // }

}
