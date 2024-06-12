package objects;

import java.util.List;

public class Publicacao implements defaultInterface {
    private int ano;
    private String siglaVeiculo;
    private String titulo;
    private List<Long> autores;
    private int numero;
    private int volume;
    private String local;
    private int paginaInicial;
    private int paginaFinal;

    public Publicacao(int ano, String siglaVeiculo, String titulo, List<Long> autores, int numero, int volume, String local,int pagInicial, int pagFinal){
        this.ano = ano;
        this.siglaVeiculo = siglaVeiculo;
        this.titulo = titulo;
        this.autores = autores;
        this.numero = numero;
        this.volume = volume;
        this.local = local;
        this.paginaFinal = pagFinal;
        this.paginaInicial = pagInicial;
    }
    public int getAno(){
        return ano;
    }
    public String getSiglaVeiculo(){
        return siglaVeiculo;
    }
    public String getTitulo(){
        return titulo;
    }
    public List<Long> getAutoresList(){
        return autores;
    }
    public int getNumero(){
        return numero;
    }
    public int getVolume(){
        return volume;
    }
    public String getLocal(){
        return local;
    }
    public int getPagInicial(){
        return paginaInicial;
    }
    public int getPagFinal(){
        return paginaFinal;
    }
    public void showInfo(){

    }
}
