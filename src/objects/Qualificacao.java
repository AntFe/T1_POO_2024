package objects;

public class Qualificacao implements defaultInterface{
    private int ano;
    private String siglaVeiculo;
    private String qualis;
    private Veiculo veiculoRelacionado;

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
    public Qualificacao(int ano, String siglaVeiculo, String qualis, Veiculo veiculoRelacionado){
        internalBuilder(ano, siglaVeiculo, qualis);
        this.veiculoRelacionado = veiculoRelacionado;
    }

    public int getAno() {
        return ano;
    }

    public void relacionarVeiculo(Veiculo veiculo){
        this.veiculoRelacionado = veiculo;
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
