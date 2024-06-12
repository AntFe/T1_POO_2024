package objects;

import java.time.LocalDate;

public class Ocorrencia implements defaultInterface {
    private long codigo;
    private String evento;
    private LocalDate dataInicio;
    private LocalDate dataFim;

    public Ocorrencia(long codigo, String evento, LocalDate dataInicio, LocalDate dataFim){
        this.codigo = codigo;
        this.evento = evento;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    public long getCodigo(){
        return codigo;
    }
    public String getEvento(){
        return evento;
    }
    public LocalDate getInicio(){
        return dataInicio;
    }
    public LocalDate getFim(){
        return dataFim;
    }
    public void showInfo(){

    }
}
