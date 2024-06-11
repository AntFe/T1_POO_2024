package objects;

import java.time.LocalDate;
import java.util.Map;

public class Regra {
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private Map<String, Double> qualisPontos;
    private int qtdeAnosPontos;
    private Map<String, Integer> qualisMinimos;
    private int qtdeAnosArtigos;
    private double pontuacaoMinima;

    public Regra(LocalDate dataInicio, LocalDate dataFim, Map<String, Double> qualisPontos, int qtdeAnosPontos, Map<String, Integer> qualisMinimos, int qtdeAnosArtigos, double pontuacaoMinima) {
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.qualisPontos = qualisPontos;
        this.qtdeAnosPontos = qtdeAnosPontos;
        this.qualisMinimos = qualisMinimos;
        this.qtdeAnosArtigos = qtdeAnosArtigos;
        this.pontuacaoMinima = pontuacaoMinima;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public Map<String, Double> getQualisPontos() {
        return qualisPontos;
    }

    public int getQtdeAnosPontos() {
        return qtdeAnosPontos;
    }

    public Map<String, Integer> getQualisMinimos() {
        return qualisMinimos;
    }

    public int getQtdeAnosArtigos() {
        return qtdeAnosArtigos;
    }

    public double getPontuacaoMinima() {
        return pontuacaoMinima;
    }
}

