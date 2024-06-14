import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;
import java.io.FileWriter;
import reader.LeitorCSV;
import objects.*;

public class Main {

    public static void main(String[] args) throws IOException {

        ///Tratamento de erro de argumento
        if (args.length != 1) {
            System.err.println("Uso: java Main <DIRETORIO/ONDE/ESTÃO/OS/CSV>");
            return;
        }
        
        String diretorio = args[0];
        /// Gerando listas de cada objeto respectivo
        List<Docente> docentes = LeitorCSV.lerDocentes(Paths.get(diretorio, "docentes.csv").toString());
        List<Ocorrencia> ocorrencias = LeitorCSV.lerOcorrencias(Paths.get(diretorio, "ocorrencias.csv").toString());
        List<Veiculo> veiculos = LeitorCSV.lerVeiculos(Paths.get(diretorio, "veiculos.csv").toString());
        List<Publicacao> publicacoes = LeitorCSV.lerPublicacoes(Paths.get(diretorio, "publicacoes.csv").toString());
        List<Qualificacao> qualificacoes = LeitorCSV.lerQualificacoes(Paths.get(diretorio, "qualis.csv").toString());
        List<Regra> regras = LeitorCSV.lerRegras(Paths.get(diretorio, "regras.csv").toString());


        /// Leitura dos dados
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insira o ano para recredenciamento:");
        int ano = scanner.nextInt();

        processarDados(docentes, ocorrencias, veiculos, publicacoes, qualificacoes, regras, ano, diretorio);

        scanner.close();


        /// falta a saida
    }


    // Resolver PROBLEMA : 
    private static void processarDados(
            List<Docente> docentes,
            List<Ocorrencia> ocorrencias,
            List<Veiculo> veiculos,
            List<Publicacao> publicacoes,
            List<Qualificacao> qualificacoes,
            List<Regra> regras,
            int ano,
            String diretorio) {

        // Lógica para processamento dos dados e geração dos relatórios
        
        // Exemplo: Mapear qualificações recentes para os veículos
        Map<String, String> qualificacaoRecente = new HashMap<>();
        for (Qualificacao q : qualificacoes) {
            qualificacaoRecente.put(q.getSiglaVeiculo(), q.getQualis());
        }

        // Lógica de recredenciamento dos docentes
        Map<Long, Double> pontuacaoDocentes = new HashMap<>();

        for (Publicacao p : publicacoes) {
            String qualis = qualificacaoRecente.getOrDefault(p.getSiglaVeiculo(), "C");
            Regra regra = regras.stream()
                    .filter(r -> ano >= r.getDataInicio().getYear() && ano <= r.getDataFim().getYear())
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Regra não encontrada para o ano " + ano));

            double pontos = regra.getQualisPontos().getOrDefault(qualis, 0.0);
            for (long autor : p.getAutores()) {
                pontuacaoDocentes.put(autor, pontuacaoDocentes.getOrDefault(autor, 0.0) + pontos);
            }
        }

        // Gerar relatórios
        gerarRelatorioRecredenciamento(docentes, pontuacaoDocentes, regras, ano, diretorio);
        gerarListaPublicacoes(publicacoes, qualificacaoRecente, veiculos, docentes, diretorio);
        gerarEstatisticasPublicacoes(publicacoes, qualificacaoRecente, diretorio);
    }

    //Problema: ???
    private static void gerarRelatorioRecredenciamento(
            List<Docente> docentes,
            Map<Long, Double> pontuacaoDocentes,
            List<Regra> regras,
            int ano,
            String diretorio) {

        // Implementação da lógica para gerar o relatório de recredenciamento
        // Criação do arquivo CSV
        List<String> linhas = new ArrayList<>();
        linhas.add("nome do docente;pontuação alcançada;recredenciado?");

        for (Docente docente : docentes) {
            double pontuacao = pontuacaoDocentes.getOrDefault(docente.getCodigo(), 0.0);
            String recredenciado = "Não";
            Regra regra = regras.stream()
                    .filter(r -> ano >= r.getDataInicio().getYear() && ano <= r.getDataFim().getYear())
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Regra não encontrada para o ano " + ano));

            if (pontuacao >= regra.getPontuacaoMinima()) {
                recredenciado = "Sim";
            }

            linhas.add(String.format("%s;%.1f;%s", docente.getNome(), pontuacao, recredenciado));
        }
        try {
            Files.write(Paths.get(diretorio, "saida", "1-recredenciamento.csv"), linhas);
        } catch (IOException e) {
            // e.printStackTrace();
            
            //deixa o arquivo em branco
            try {
                FileWriter file = new FileWriter(diretorio + "/saida/1-recredenciamento.csv");
                file.close();
            } catch (IOException ioe){
                ioe.printStackTrace();
            } 
        }
    }

    private static void gerarListaPublicacoes(
            List<Publicacao> publicacoes,
            Map<String, String> qualificacaoRecente,
            List<Veiculo> veiculos,
            List<Docente> docentes,
            String diretorio) {

        // Implementação da lógica para gerar a lista de publicações
        // Criação do arquivo CSV
        List<String> linhas = new ArrayList<>();
        linhas.add("ano;sigla veículo;nome veículo;Qualis;fator de impacto;título da publicação;nomes dos docentes");

        for (Publicacao publicacao : publicacoes) {
            String qualis = qualificacaoRecente.getOrDefault(publicacao.getSiglaVeiculo(), "C");

            /// isso vai dar problema na entrevista
            /// usa função lambda e "ponteiro para um método"
            String nomeVeiculo = veiculos.stream().filter(v -> v.getSigla().equals(publicacao.getSiglaVeiculo())).map(Veiculo::getNome).findFirst().orElse("Desconhecido");

            /// tem que virar um numero com precisão 3 digitos
            String impacto = veiculos.stream().filter(v -> v.getSigla().equals(publicacao.getSiglaVeiculo())).map(Veiculo::getFatorImpacto).findFirst().orElse(0.0).toString();

            List<String> nomesAutores = publicacao.getAutores().stream().map(codigo -> docentes.stream().filter(d -> d.getCodigo() == codigo).map(Docente::getNome).findFirst().orElse("Desconhecido")).collect(Collectors.toList());

            linhas.add(String.format("%d;%s;%s;%s;%s;%s;%s", 
                publicacao.getAno(), 
                publicacao.getSiglaVeiculo(), 
                nomeVeiculo, 
                qualis, 
                impacto, 
                publicacao.getTitulo(), 
                String.join(",", nomesAutores)));
        }

        /// tem que gerar o arquivo vazio se der erro
        try {
            Files.write(Paths.get(diretorio, "saida", "2-publicacoes.csv"), linhas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void gerarEstatisticasPublicacoes(
            List<Publicacao> publicacoes,
            Map<String, String> qualificacaoRecente,
            String diretorio) {

        // Map para armazenar as estatísticas por Qualis
        Map<String, Integer> numeroArtigos = new HashMap<>();
        Map<String, Double> numeroArtigosPorDocente = new HashMap<>();

        // Inicializa os mapas
        for (String qualis : Arrays.asList("A1", "A2", "A3", "A4", "B1", "B2", "B3", "B4", "B5", "C")) {
            numeroArtigos.put(qualis, 0);
            numeroArtigosPorDocente.put(qualis, 0.0);
        }

        // Calcula as estatísticas
        for (Publicacao publicacao : publicacoes) {
            String qualis = qualificacaoRecente.getOrDefault(publicacao.getSiglaVeiculo(), "C");
            numeroArtigos.put(qualis, numeroArtigos.get(qualis) + 1);
            double artigosPorDocente = 1.0 / publicacao.getAutores().size();
            numeroArtigosPorDocente.put(qualis, numeroArtigosPorDocente.get(qualis) + artigosPorDocente);
        }

        // Criação do arquivo CSV
        /// Falta ordenar
        List<String> linhas = new ArrayList<>();
        linhas.add("Qualis;número de artigos;número de artigos por docente");

        for (String qualis : Arrays.asList("A1", "A2", "A3", "A4", "B1", "B2", "B3", "B4", "B5", "C")) {
            linhas.add(String.format("%s;%d;%.2f", qualis, numeroArtigos.get(qualis), numeroArtigosPorDocente.get(qualis)));
        }

        try {
            Files.write(Paths.get(diretorio, "saida", "3-estatisticas.csv"), linhas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
