package GerenciadorDeTime;

import java.util.Scanner;

public class GerenciadorDeTime {

    private static Jogador time[] = new Jogador[10];

    private static Scanner leitor = new Scanner(System.in);


    public static void main(String[] args) {

        System.out.println("Bem-vindo ao Gerenciador de Time!");

        boolean executando = true;

        while (executando) {
            System.out.println("\n[1] Adicionar Jogador");
            System.out.println("[2] Listar Jogadores");
            System.out.println("[3] Aumentar Pontuação de um Jogador");
            System.out.println("[0] Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = leitor.nextInt();

            switch (opcao) {

                case 0:
                    executando = false;
                    System.out.println("Saindo do programa...");
                    break;

                case 1:
                    int primeiraVagaLivre = -1;


                    for (int i = 0; i < time.length; i++) {
                        if (time[i] == null) {
                            primeiraVagaLivre = i;
                            break;

                        }
                    }

                    if (primeiraVagaLivre != -1) {
                        System.out.println("\nQual tipo de jogador deseja adicionar?");
                        System.out.println("[1] Jogador Comum");
                        System.out.println("[2] Jogador VIP");
                        System.out.print("Opção: ");
                        int tipoJogador = leitor.nextInt();
                        leitor.nextLine();

                        System.out.println("Digite o nome do Jogador: ");
                        String nome = leitor.nextLine();
                        System.out.println("Digite a idade do Jogador: ");
                        int idade = leitor.nextInt();
                        System.out.println("Digite a pontuação: ");
                        int pontuacao = leitor.nextInt();

                        if (tipoJogador == 1) {
                            time[primeiraVagaLivre] = new Jogador(nome, idade, pontuacao);

                        } else if (tipoJogador == 2) {
                            System.out.println("Digite o multiplicador de pontos: ");
                            double multiplicador = leitor.nextDouble();

                            time[primeiraVagaLivre] = new JogadorVIP(nome, idade, pontuacao, multiplicador);

                        }
                        System.out.println("Jogador adicionado com sucesso!");

                    } else {
                        System.out.println("Time completo, não é possível adicionar mais jogadores!");
                    }
                    break;

                case 2:
                    if (time == null) {
                        System.out.println("Nenhum jogador cadastrado!");
                    }
                    for (int i = 0; i < time.length; i++) {
                        if (time[i] == null) {

                        } else {
                            System.out.println("Nome: " + time[i].getNome());
                            System.out.println("Idade: " + time[i].getIdade());
                            System.out.println("Pontuação: " + time[i].getPontuacao());

                            if (time[i] instanceof JogadorVIP) {
                                JogadorVIP vip = (JogadorVIP) time[i];
                                System.out.println("Tipo: VIP (Multiplicador: " + vip.getMultiplicadorDePontos() + ")");
                            } else {
                                System.out.println("Tipo: Comum");
                            }

                            System.out.println("--------------------");

                        }

                    }
                    break;

                case 3:
                    System.out.println("\n--- Escolha um Jogador para Pontuar ---");
                    boolean timeVazio = true;

                    // 1. LISTAR JOGADORES
                    for (int i = 0; i < time.length; i++) {
                        if (time[i] != null) {
                            System.out.println("[" + i + "] " + time[i].getNome());
                            timeVazio = false; // Achamos pelo menos um jogador
                        }
                    }
                    System.out.println("---------------------------------------");

                    // 2. PEDIR INPUT E ATUALIZAR
                    if (!timeVazio) {
                        System.out.print("Digite o índice do jogador: ");
                        int indice = leitor.nextInt();

                        // Verifica se o índice é válido e se existe um jogador lá
                        if (indice >= 0 && indice < time.length && time[indice] != null) {
                            System.out.print("Digite os pontos a adicionar: ");
                            int pontos = leitor.nextInt();

                            time[indice].aumentarPontuacao(pontos);
                            System.out.println("Pontuação de " + time[indice].getNome() + " atualizada!");
                        } else {
                            System.out.println("Índice inválido!");
                        }
                    } else {
                        System.out.println("Nenhum jogador cadastrado para pontuar.");
                    }

                    break;

            }

        }
    }
}
