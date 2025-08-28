package GerenciadorDeTime;


import java.io.*;
import java.util.ArrayList;
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

            int opcao = leitor.nextInt();

            switch (opcao) {

                case 0:
                    executando = false;
                    System.out.println("Saindo do programa...");
                    break;

                case 1:

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
                        Jogador novoJogador = new Jogador(nome, idade, pontuacao);
                        time.add(novoJogador);

                    } else if (tipoJogador == 2) {
                        System.out.println("Digite o multiplicador de pontos: ");
                        double multiplicador = leitor.nextDouble();

                        JogadorVIP novoVip = new JogadorVIP(nome, idade, pontuacao, multiplicador);
                        time.add(novoVip);
                    }
                    System.out.println("Jogador adicionado com sucesso!");

                    break;

                case 2:
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
                    break;

                case 3:
                    System.out.println("\n--- Escolha um Jogador para Pontuar ---");

                    if (time.isEmpty()){
                        System.out.println("Nenhum jogador encontrado!");
                    } else {
                        for (int i=0; i < time.size(); i++){
                            System.out.println("[ " + i + " ]" + time.get(i).getNome());
                        }
                        System.out.println("------------------");

                        System.out.println("Digite o índice do jogador: ");
                        int indice = leitor.nextInt();

                        if (indice >= 0 && indice < time.size()){
                            System.out.println("Digite a quantidade de pontos que deseja adicionar: ");
                            int pontos = leitor.nextInt();

                            Jogador jogadorEscolhido = time.get(indice);

                            jogadorEscolhido.aumentarPontuacao(pontos);

                            System.out.println("Pontuação de " + jogadorEscolhido.getNome() + " atualizada para " + jogadorEscolhido.getPontuacao() + "!");

                        } else {
                            System.out.println("Índice inválido!");
                        }

                    }
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

    private static void salvarTime(){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("time.csv"))){
            writer.write("TIPO,NOME,IDADE,PONTUACAO,MULTIPLICADO\n");
            for (Jogador jogador : time){
                String linha = "";
                if (jogador instanceof JogadorVIP){
                    linha = "VIP," + jogador.getNome() + "," + jogador.getIdade() + "," + jogador.getPontuacao() + "," + ((JogadorVIP) jogador).getMultiplicadorDePontos();

                } else {
                    linha = "COMUM," + jogador.getNome() + "," + jogador.getIdade() + "," + jogador.getPontuacao() + ",0.0";
                }

                writer.write(linha + "\n");

            }

            System.out.println("Time salvo com sucesso no arquivo time.csv!");

        } catch (IOException e){
            System.out.println("Erro ao salvar o arquivo: " + e.getMessage());
        }
    }

    private static void carregarTime(){
        try(BufferedReader reader = new BufferedReader(new FileReader("time.csv"))){
            time.clear();
            reader.readLine();
            String linha;
            while ((linha = reader.readLine()) != null){
                String[] dados = linha.split(",");
                String nome = dados[1];
                int idade = Integer.parseInt(dados[2]);
                int pontuacao = Integer.parseInt(dados[3]);

                if (dados[0].equals("VIP")){
                    double multiplicador = Double.parseDouble(dados[4]);
                    JogadorVIP novoJogador = new JogadorVIP(nome, idade, pontuacao, multiplicador);
                    time.add(novoJogador);
                } else {
                    Jogador novoJogador = new Jogador(nome, idade, pontuacao);
                    time.add(novoJogador);
                }

            }
        } catch (IOException e){
            System.out.println("Erro ao carregar o arquivo: " + e.getMessage());
        }

    }

}


