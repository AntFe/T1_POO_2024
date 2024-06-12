package objects;

import java.time.LocalDate;

public class Docente implements defaultInterface{
    private long codigo;
    private String nome;
    private LocalDate dataNascimento;
    private LocalDate dataIngresso;
    private boolean coordenadorStatus;
    private boolean recredenciado = false;

    public Docente(long codigo,String nome, LocalDate dataNascimento, LocalDate dataIngresso){
        this.codigo = codigo;
        this.coordenadorStatus = false;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.dataIngresso = dataIngresso;
    }
    public void showInfo(){

    }
    public void recredenciar(){
        this.recredenciado = true;
    }
    public void desRecredenciar(){
        this.recredenciado = false;
    }
    public boolean statusRecredenciado(){
        return recredenciado;
    }
    public boolean ehCoordenador(){
        return coordenadorStatus;
    }
    public void setStatus(boolean status){
        this.coordenadorStatus = status;
    }
    public long getCodigo(){
        return codigo;
    }
    public String getNome(){
        return nome;
    }
    public LocalDate getDataNascimento(){
        return dataNascimento;
    }
    public LocalDate getDataIngresso(){
        return dataIngresso;
    }
}
