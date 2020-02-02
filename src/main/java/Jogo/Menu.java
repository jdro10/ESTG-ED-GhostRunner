package Jogo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import Enum.Dificuldade;
import Estruturas.InvalidIndexException;

public class Menu {

    private InputStreamReader inputStreamReader;

    public Menu() {
        this.inputStreamReader = new InputStreamReader(System.in);
    }

    public void menuPrincipal() throws InvalidIndexException {
        BufferedReader reader = new BufferedReader(this.inputStreamReader);
        String escolha = null;

        do {
            System.out.println("----- GHOST RUNNER -----");
            System.out.println("1 - Modo simulação");
            System.out.println("2 - Modo manual");
            System.out.println("0 - Sair");

            try {
                escolha = reader.readLine();
            } catch (IOException e) {
                System.out.println(e);
            }

            switch (escolha) {
                case "1":
                    this.modoSimulacao();
                    break;
                case "2":
                    this.modoManual();
                    break;
                case "3":
                    break;
                default:
                    if (!escolha.equals("0")) {
                        System.out.println("Escolha inválida, tente novamente.");
                    } else {
                        System.out.println("Jogo terminado.");
                    }
                    break;
            }

        } while (!escolha.equals("0"));
    }

    private void modoSimulacao() throws InvalidIndexException {
        BufferedReader readerDificuldade = new BufferedReader(this.inputStreamReader);
        Mapa mapa = new Mapa();
        String dificuldade = null;
        Dificuldade dificuldadeEscolhida = null;

        try {
            System.out.println("Introduza a dificuldade: ");
            dificuldade = readerDificuldade.readLine();
        } catch (IOException e) {
            System.out.println(e);
        }

        if (dificuldade.equalsIgnoreCase("BASICO")) {
            dificuldadeEscolhida = Dificuldade.BASICO;
        } else if (dificuldade.equalsIgnoreCase("NORMAL")) {
            dificuldadeEscolhida = Dificuldade.NORMAL;
        } else if (dificuldade.equalsIgnoreCase("DIFICIL")) {
            dificuldadeEscolhida = Dificuldade.DIFICIL;
        }

        mapa.lerJson();

        Jogo jogo = new Jogo(mapa, dificuldadeEscolhida);

        jogo.simulacaoJogo();
    }

    private void modoManual() throws InvalidIndexException {
        BufferedReader readerDificuldade = new BufferedReader(this.inputStreamReader);
        Mapa mapa = new Mapa();
        String dificuldade = null;
        String nomeJogador = null;
        Dificuldade dificuldadeEscolhida = null;

        try {
            System.out.println("Introduza o seu nome: ");
            nomeJogador = readerDificuldade.readLine();
            System.out.println("Introduza a dificuldade: ");
            dificuldade = readerDificuldade.readLine();

        } catch (IOException e) {
            System.out.println(e);
        }

        if (dificuldade.equalsIgnoreCase("BASICO")) {
            dificuldadeEscolhida = Dificuldade.BASICO;
        } else if (dificuldade.equalsIgnoreCase("NORMAL")) {
            dificuldadeEscolhida = Dificuldade.NORMAL;
        } else if (dificuldade.equalsIgnoreCase("DIFICIL")) {
            dificuldadeEscolhida = Dificuldade.DIFICIL;
        }

        mapa.lerJson();

        Jogo jogo = new Jogo(mapa, dificuldadeEscolhida);
        Jogador jogador = new Jogador(nomeJogador);

        jogo.setJogador(jogador);

        BufferedReader readerPosicao = new BufferedReader(this.inputStreamReader);
        String pos = null;

        //jogo.mostrarOpcoes(999999);


        while (true) {
            jogo.mostrarHipoteses();
            System.out.println("\nIntroduza o próximo movimento: ");

            try {
                pos = readerPosicao.readLine();
                System.out.println();
            } catch (IOException e) {
                System.out.println(e);
            }

            jogo.escolheOpcoes(Integer.parseInt(pos));
        }
    }
}
