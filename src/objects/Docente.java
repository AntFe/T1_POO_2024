package objects;
import java.time.LocalDate;

public class Docente {
    private long codigo;
    private String nome;
    private LocalDate dataNascimento;
    private LocalDate dataIngresso;
    private boolean coordenadorStatus;

    public Docente(long codigo, String nome, LocalDate dataNascimento, LocalDate dataIngresso) {
        this.codigo = codigo;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.dataIngresso = dataIngresso;
        this.coordenadorStatus = false;
    }

    public long getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public boolean ehCoordenador(){
        return coordenadorStatus;
    }

    public void setCoordenador(boolean Status){
        this.coordenadorStatus = Status;
    }
    
    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public LocalDate getDataIngresso() {
        return dataIngresso;
    }
}
