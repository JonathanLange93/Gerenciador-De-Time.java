package GerenciadorDeTime;

import java.util.Scanner;

public class Jogador {

    Scanner scan = new Scanner(System.in);

    private String nome;
    private int idade;
    private int pontuacao;

    public String getNome() {
        return nome;
    }

    public int getIdade(){
        return idade;
    }

    public int getPontuacao(){
        return pontuacao;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public void setIdade(int idade) {
        if (idade < 0){
            System.out.println("Idade inválida, digite uma idade maior que ZERO!");
        } else {
            this.idade = idade;
        }
    }

    public void setPontuacao(int pontuacao){
        this.pontuacao = pontuacao;
    }

    public Jogador(String nome, int idade, int pontuacao){
        this.nome = nome;
        this.idade = idade;
        this.pontuacao = pontuacao;
    }

    public void aumentarPontuacao(int pontosGanhos){
        pontuacao += pontosGanhos;
    }


}
