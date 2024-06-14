package objects;

public class Qualificacao implements defaultInterface{
    private int ano;
    private String siglaVeiculo;
    private String qualis;
    private float pontuacao;

    private void internalBuilder(int ano, String siglaVeiculo, String qualis){
        this.ano = ano;
        this.siglaVeiculo = siglaVeiculo;
        this.qualis = qualis;
    }

    public Qualificacao(int ano, String siglaVeiculo, String qualis) {
        // this.ano = ano;
        // this.siglaVeiculo = siglaVeiculo;
        // this.qualis = qualis;
        internalBuilder(ano, siglaVeiculo, qualis);
    }
    public Qualificacao(int ano, String siglaVeiculo, String qualis, float pontuacao){
        internalBuilder(ano, siglaVeiculo, qualis);
        this.pontuacao = pontuacao;
    }

    public int getAno() {
        return ano;
    }

    public void relacionarVeiculo(float pontuacao){
        this.pontuacao = pontuacao;
    }

    public float getPontuacaoBase(){
        return pontuacao;
    }

    public void showInfo(){
        System.out.println("Qualificação:");
        System.out.println(ano + "|" + siglaVeiculo);
        System.out.println(qualis);
        /// implementar showInfo para veiculos
    }
    public String getSiglaVeiculo() {
        return siglaVeiculo;
    }
    public String getQualis() {
        return qualis;
    }
}
