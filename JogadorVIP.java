package GerenciadorDeTime;

public class JogadorVIP extends Jogador{

    private double multiplicadorDePontos;

    public double getMultiplicadorDePontos() {
        return multiplicadorDePontos;
    }

    public void setMultiplicadorDePontos(double multiplicadorDePontos) {
        this.multiplicadorDePontos = multiplicadorDePontos;
    }

    public JogadorVIP(String nome, int idade, int pontuacao, double multiplicadorDePontos){
        super(nome, idade, pontuacao);
        this.multiplicadorDePontos = multiplicadorDePontos;

    }

    @Override
    public void aumentarPontuacao(int pontosGanhos){
        double pontosCalculados = (pontosGanhos*multiplicadorDePontos);
        setPontuacao(getPontuacao() + (int) pontosCalculados);

    }

}
