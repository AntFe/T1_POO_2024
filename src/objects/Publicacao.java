package objects;

import java.util.List;

public class Publicacao implements defaultInterface{
    private int ano;
    private String siglaVeiculo;
    private String titulo;
    private List<Long> autores;
    private int numero;
    private int volume;
    private String local;
    private int paginaInicial;
    private int paginaFinal;

    public Publicacao(int ano, String siglaVeiculo, String titulo, List<Long> autores, int numero, int volume, String local, int paginaInicial, int paginaFinal) {
        this.ano = ano;
        this.siglaVeiculo = siglaVeiculo;
        this.titulo = titulo;
        this.autores = autores;
        this.numero = numero;
        this.volume = volume;
        this.local = local;
        this.paginaInicial = paginaInicial;
        this.paginaFinal = paginaFinal;
    }

    public int getAno() {
        return ano;
    }

    public void showInfo(){
        
    }
    public String getSiglaVeiculo() {
        return siglaVeiculo;
    }

    public String getTitulo() {
        return titulo;
    }

    public List<Long> getAutores() {
        return autores;
    }

    public int getNumero() {
        return numero;
    }

    public int getVolume() {
        return volume;
    }

    public String getLocal() {
        return local;
    }

    public int getPaginaInicial() {
        return paginaInicial;
    }

    public int getPaginaFinal() {
        return paginaFinal;
    }
}
