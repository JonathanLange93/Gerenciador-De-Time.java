package GerenciadorDeTime;


import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class GerenciadorDeTime {

    private static ArrayList<Jogador> time = new ArrayList<>();

    private static Scanner leitor = new Scanner(System.in);


    public static void main(String[] args) {

        System.out.println("Bem-vindo ao Gerenciador de Time!");

        boolean executando = true;

        while (executando) {
            System.out.println("\n[1] Adicionar Jogador");
            System.out.println("[2] Listar Jogadores");
            System.out.println("[3] Aumentar Pontuação de um Jogador");
            System.out.println("[4] Salvar Time");
            System.out.println("[5] Carregar Time");
            System.out.println("[0] Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = -1;

            try {
                opcao = leitor.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Erro: Por favor, digite apenas um número!");
                leitor.next();
                continue;
            }

            switch (opcao) {

                case 0:
                    executando = false;
                    System.out.println("Saindo do programa...");
                    break;

                case 1:

                    adicionarJogador();

                    break;

                case 2:

                    listarJogadores();

                    break;

                case 3:

                    aumentarPontuacao();

                    break;

                case 4:
                    System.out.println("--- Time Salvo ---");
                    salvarTime();
                    break;

                case 5:
                    System.out.println("=-=- Carregando Time -=-=");
                    carregarTime();
                    break;

                default:
                    System.out.println("Opção inválida! Por favor, escolha uma das opções do menu.");
                    break;

            }

        }
    }

    private static void salvarTime() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("time.csv"))) {
            writer.write("TIPO,NOME,IDADE,PONTUACAO,MULTIPLICADO\n");
            for (Jogador jogador : time) {
                String linha = "";
                if (jogador instanceof JogadorVIP) {
                    linha = "VIP," + jogador.getNome() + "," + jogador.getIdade() + "," + jogador.getPontuacao() + "," + ((JogadorVIP) jogador).getMultiplicadorDePontos();

                } else {
                    linha = "COMUM," + jogador.getNome() + "," + jogador.getIdade() + "," + jogador.getPontuacao() + ",0.0";
                }

                writer.write(linha + "\n");

            }

            System.out.println("Time salvo com sucesso no arquivo time.csv!");

        } catch (IOException e) {
            System.out.println("Erro ao salvar o arquivo: " + e.getMessage());
        }
    }

    private static void carregarTime() {
        try (BufferedReader reader = new BufferedReader(new FileReader("time.csv"))) {
            time.clear();
            reader.readLine();
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(",");
                String nome = dados[1];
                int idade = Integer.parseInt(dados[2]);
                int pontuacao = Integer.parseInt(dados[3]);

                if (dados[0].equals("VIP")) {
                    double multiplicador = Double.parseDouble(dados[4]);
                    JogadorVIP novoJogador = new JogadorVIP(nome, idade, pontuacao, multiplicador);
                    time.add(novoJogador);
                } else {
                    Jogador novoJogador = new Jogador(nome, idade, pontuacao);
                    time.add(novoJogador);
                }

            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar o arquivo: " + e.getMessage());
        }

    }

    private static void adicionarJogador() {

        System.out.println("\nQual tipo de jogador deseja adicionar?");
        System.out.println("[1] Jogador Comum");
        System.out.println("[2] Jogador VIP");
        System.out.print("Opção: ");

        int tipoJogador = -1;
        while (true) {


            try {
                tipoJogador = leitor.nextInt();
                break;

            } catch (InputMismatchException e) {
                System.out.println("Erro! digite o tipo do jogador: ");
                leitor.next();

            }
        }

        leitor.nextLine();

        System.out.println("Digite o nome do Jogador: ");
        String nome = leitor.nextLine();

        System.out.println("Digite a idade do Jogador: ");
        int idade = -1;

        while (true) {


            try {
                idade = leitor.nextInt();
                break;

            } catch (InputMismatchException e) {
                System.out.println("Erro! digite uma idade válida: ");
                leitor.next();

            }
        }

        System.out.println("Digite a pontuação: ");

        int pontuacao = -1;

        while (true) {


            try {
                pontuacao = leitor.nextInt();
                break;

            } catch (InputMismatchException e) {
                System.out.println("Erro! Digite uma pontuação: ");
                leitor.next();

            }
        }

        if (tipoJogador == 1) {
            Jogador novoJogador = new Jogador(nome, idade, pontuacao);
            time.add(novoJogador);

        } else if (tipoJogador == 2) {
            System.out.println("Digite o multiplicador de pontos: ");

            double multiplicador = -1;

            while (true) {

                try {
                    multiplicador = leitor.nextDouble();
                    break;

                } catch (InputMismatchException e) {
                    System.out.println("Erro! Digite o tipo de jogador: ");
                    leitor.next();

                }
            }


            JogadorVIP novoVip = new JogadorVIP(nome, idade, pontuacao, multiplicador);
            time.add(novoVip);
        }
        System.out.println("Jogador adicionado com sucesso!");

    }

    private static void listarJogadores() {
        System.out.println("\n--- Jogadores no time ---");

        if (time.isEmpty()) {
            System.out.println("Nenhum jogador encontrado!");

        } else {
            for (Jogador jogadorAtual : time) {
                System.out.println("Nome: " + jogadorAtual.getNome());
                System.out.println("Idade: " + jogadorAtual.getIdade());
                System.out.println("Pontuação: " + jogadorAtual.getPontuacao());

                if (jogadorAtual instanceof JogadorVIP) {
                    JogadorVIP vip = (JogadorVIP) jogadorAtual;
                    System.out.println("Tipo: VIP (Multiplicador: " + vip.getMultiplicadorDePontos() + ")");

                } else {
                    System.out.println("Tipo: Comum");
                }
                System.out.println("---------------------");
            }
        }

    }

    private static void aumentarPontuacao() {
        System.out.println("\n--- Escolha um Jogador para Pontuar ---");

        if (time.isEmpty()) {
            System.out.println("Nenhum jogador encontrado para pontuar.");
            return; // Sai do método se o time estiver vazio
        }

        // PASSO 1: Listar os jogadores para o usuário
        for (int i = 0; i < time.size(); i++) {
            System.out.println("[" + i + "] " + time.get(i).getNome());
        }
        System.out.println("---------------------------------------");

        // PASSO 2: Pedir e validar o ÍNDICE
        int indice;
        while (true) {
            try {
                System.out.print("Digite o índice do jogador: ");
                indice = leitor.nextInt();
                break; // Sai do loop se o número for válido
            } catch (InputMismatchException e) {
                System.out.println("Erro: Índice inválido. Digite um número.");
                leitor.next(); // Limpa a entrada errada
            }
        }

        // PASSO 3: Verificar se o índice existe na lista
        if (indice >= 0 && indice < time.size()) {

            // PASSO 4: Pedir e validar os PONTOS
            int pontos;
            while (true) {
                try {
                    System.out.print("Digite os pontos a adicionar: ");
                    pontos = leitor.nextInt();
                    break; // Sai do loop se o número for válido
                } catch (InputMismatchException e) {
                    System.out.println("Erro: Pontuação inválida. Digite um número.");
                    leitor.next(); // Limpa a entrada errada
                }
            }

            // PASSO 5: Aumentar a pontuação
            Jogador jogadorEscolhido = time.get(indice);
            jogadorEscolhido.aumentarPontuacao(pontos);
            System.out.println("Pontuação de " + jogadorEscolhido.getNome() + " atualizada para " + jogadorEscolhido.getPontuacao() + "!");

        } else {
            // Se o índice digitado não corresponde a nenhum jogador
            System.out.println("Índice inválido! Nenhum jogador encontrado nessa posição.");
        }
    }
    }



