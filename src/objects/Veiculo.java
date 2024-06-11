package objects;

public class Veiculo implements defaultInterface {
    private String sigla;
    private String nome;
    private char tipo;
    private double fatorImpacto;
    private String issn;

    public Veiculo(String sigla, String nome, char tipo, double fatorImpacto, String issn) {
        this.sigla = sigla;
        this.nome = nome;
        this.tipo = tipo;
        this.fatorImpacto = fatorImpacto;
        this.issn = issn;
    }

    public String getSigla() {
        return sigla;
    }


    public void showInfo(){

        
    }


    public String getNome() {
        return nome;
    }

    public char getTipo() {
        return tipo;
    }

    public double getFatorImpacto() {
        return fatorImpacto;
    }

    public String getIssn() {
        return issn;
    }
}
