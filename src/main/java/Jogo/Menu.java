package Jogo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.File;
import java.util.Iterator;

import Enum.Dificuldade;
import Estruturas.ArrayOrderedList;
import Estruturas.EmptyException;
import Estruturas.InvalidIndexException;
import Estruturas.NoComparableException;
import Exceptions.MapaException;

public class Menu {

    private InputStreamReader inputStreamReader;

    public Menu() {
        this.inputStreamReader = new InputStreamReader(System.in);
    }

    public void menuPrincipal() throws InvalidIndexException, MapaException, IOException, NoComparableException {
        BufferedReader reader = new BufferedReader(this.inputStreamReader);
        String escolha = null;

        do {
            System.out.println("----- GHOST RUNNER -----");
            System.out.println("1 - Modo simulação");
            System.out.println("2 - Modo manual");
            System.out.println("3 - Ver Mapa");
            System.out.println("4 - Classificações");
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
                    this.verMapa();
                    break;
                case "4":
                    this.classificacoes();
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

    private void verMapa() {
        BufferedReader reader = new BufferedReader(this.inputStreamReader);
        Mapa mapa = new Mapa();
        String mapaEscolhido = null;

        try {
            File file = new File("./mapExample/");
            File[] arquivos = file.listFiles();

            int j = 0;

            for (File fileTmp : arquivos) {
                System.out.println(fileTmp.getName().substring(0, fileTmp.getName().length() - 5) + " -> Opção: " + j++);
            }

            int opcao = Integer.parseInt(reader.readLine());

            try {
                if (opcao <= j) {
                    mapaEscolhido = arquivos[opcao].getName();
                } else {
                    mapaEscolhido = arquivos[0].getName();
                }
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                System.out.println("Opção inválida, tente novamente.");
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Erro, opção introduzida inválida! Tente novamente.");
            return;
        }

        mapa.lerJson(mapaEscolhido);

        System.out.println(mapa.toString());
    }

    private void modoSimulacao() throws InvalidIndexException {
        BufferedReader reader = new BufferedReader(this.inputStreamReader);
        Mapa mapa = new Mapa();
        String dificuldade = null;
        Dificuldade dificuldadeEscolhida = null;
        String mapaEscolhido = null;

        try {
            System.out.println("Introduza a dificuldade: ");
            System.out.println("Básico -> Opção: 0");
            System.out.println("Médio -> Opção: 1");
            System.out.println("Difícil -> Opção: 2");
            dificuldade = reader.readLine();
            File file = new File("./mapExample/");
            File[] arquivos = file.listFiles();

            int j = 0;

            for (File fileTmp : arquivos) {
                System.out.println(fileTmp.getName().substring(0, fileTmp.getName().length() - 5) + " -> Opção: " + j++);
            }

            int opcao = Integer.parseInt(reader.readLine());

            if (opcao <= j) {
                mapaEscolhido = arquivos[opcao].getName();
            } else {
                mapaEscolhido = arquivos[0].getName();
            }

        } catch (IOException | NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Erro, opção introduzida inválida! Tente novamente.");
            return;
        }

        if (dificuldade.equalsIgnoreCase("0")) {
            dificuldadeEscolhida = Dificuldade.BASICO;
        } else if (dificuldade.equalsIgnoreCase("1")) {
            dificuldadeEscolhida = Dificuldade.NORMAL;
        } else if (dificuldade.equalsIgnoreCase("2")) {
            dificuldadeEscolhida = Dificuldade.DIFICIL;
        } else {
            System.out.println("\nDificuldade introduzida inválida.");
            System.out.println("Dificuldade definida como 'BASICO'\n");
            dificuldadeEscolhida = Dificuldade.BASICO;
        }

        mapa.lerJson(mapaEscolhido);

        try {
            Jogo jogo = new Jogo(mapa, dificuldadeEscolhida);
            jogo.simulacaoJogo();
        } catch (MapaException | EmptyException e) {
            System.out.println("Mapa escolhido é inválido! Por favor introduza um mapa válido.");
        }
    }

    private void modoManual() throws InvalidIndexException, IOException {
        BufferedReader reader = new BufferedReader(this.inputStreamReader);
        Mapa mapa = new Mapa();
        String dificuldade = null;
        String nomeJogador = null;
        Dificuldade dificuldadeEscolhida = null;
        String mapaEscolhido = null;

        try {
            System.out.println("Introduza o seu nome: ");
            nomeJogador = reader.readLine();
            System.out.println("Introduza a dificuldade: ");
            System.out.println("Básico -> Opção: 0");
            System.out.println("Médio -> Opção: 1");
            System.out.println("Difícil -> Opção: 2");
            dificuldade = reader.readLine();

            if (dificuldade.equalsIgnoreCase("0")) {
                dificuldadeEscolhida = Dificuldade.BASICO;
            } else if (dificuldade.equalsIgnoreCase("1")) {
                dificuldadeEscolhida = Dificuldade.NORMAL;
            } else if (dificuldade.equalsIgnoreCase("2")) {
                dificuldadeEscolhida = Dificuldade.DIFICIL;
            } else {
                System.out.println("\nDificuldade introduzida inválida.");
                System.out.println("Dificuldade definida como 'BASICO'\n");
                dificuldadeEscolhida = Dificuldade.BASICO;
            }

            File file = new File("./mapExample/");
            File[] arquivos = file.listFiles();

            int j = 0;

            for (File fileTmp : arquivos) {
                System.out.println(fileTmp.getName().substring(0, fileTmp.getName().length() - 5) + " -> Opção: " + j++);
            }

            int opcao = Integer.parseInt(reader.readLine());

            if (opcao <= j) {
                mapaEscolhido = arquivos[opcao].getName();
            } else {
                mapaEscolhido = arquivos[0].getName();
            }
        } catch (IOException | NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Mapa escolhido é inválido! Por favor introduza um mapa válido.");
            return;
        }

        mapa.lerJson(mapaEscolhido);

        Classificacao classificacao = new Classificacao(mapa.getNome(), dificuldadeEscolhida);

        try {
            classificacao.carregarClassificacaoJSON();
        } catch (IOException e) {
        }

        Jogo jogo = null;

        try {
            jogo = new Jogo(mapa, dificuldadeEscolhida);
        } catch (EmptyException | MapaException e) {
            System.out.println("Mapa escolhido é inválido! Por favor introduza um mapa válido.");
            return;
        }

        Jogador jogador = new Jogador(nomeJogador);

        jogo.setJogador(jogador);

        BufferedReader readerPosicao = new BufferedReader(this.inputStreamReader);
        String pos = null;
        boolean perdeu = false;

        while (jogo.getPosicaoJogador() != jogo.tamanhoMapa() - 1) {

            if (jogo.getVidaJogador() <= 0) {
                perdeu = true;
                break;
            }

            jogo.mostrarHipoteses();
            System.out.println("\nIntroduza o próximo movimento: ");

            try {
                pos = readerPosicao.readLine();
                System.out.println();
            } catch (IOException e) {
                System.out.println(e);
            }

            int opcaoJogador;

            try {
                opcaoJogador = Integer.parseInt(pos);
                jogo.escolheOpcoes(opcaoJogador);
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException ex) {
                System.out.println("Introduza um número por favor");
            }
        }

        if (perdeu) {
            System.out.println("Perdeste, tenta outra vez!");
        } else {
            classificacao.adicionarJogadores(jogador);
            classificacao.guardarClassificaoJSON();
            System.out.println(jogador.getNome() + " -> Sucesso, chegou ao exterior!\n");
        }
    }

    private void classificacoes() throws IOException, NoComparableException {
        ArrayOrderedList<Jogador> orderedListJogadores = new ArrayOrderedList<Jogador>();
        BufferedReader reader = new BufferedReader(this.inputStreamReader);
        BufferedReader readerDificuldade = new BufferedReader(this.inputStreamReader);
        String mapaEscolhido = null;
        String dificuldade = null;
        Dificuldade dificuldadeEscolhida = null;

        System.out.println("Introduza o nome do mapa que pretende ver as classificações: ");

        File file = new File("./classificacoes/");

        File[] arquivos = file.listFiles();

        int j = 0;

        for (File fileTmp : arquivos) {
            System.out.println(fileTmp.getName().substring(0, fileTmp.getName().length() - 5) + " -> Opção: " + j++);
        }

        int opcao = 0;

        try {
            opcao = Integer.parseInt(reader.readLine());
        } catch (NumberFormatException e) {
            System.out.println("Erro, opção introduzida inválida! Tente novamente.");
            return;
        }

        try {
            if (opcao <= j) {
                mapaEscolhido = arquivos[opcao].getName();
            } else {
                mapaEscolhido = arquivos[0].getName();
            }
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Opção inválida, tente novamente.");
        }

        Classificacao classificacao = new Classificacao(mapaEscolhido.substring(0, mapaEscolhido.length() - 5));

        try {
            classificacao.lerClassificacaoJSON();
        } catch (IOException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Erro na leitura do ficheiro" + e);
            return;
        }

        for (int i = 0; i < classificacao.getJogadores().length; i++) {
            if (classificacao.getJogador(i) != null) {
                orderedListJogadores.add(classificacao.getJogador(i));
            }
        }

        Iterator<Jogador> it = orderedListJogadores.iterator();

        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }
}
