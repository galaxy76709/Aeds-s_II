import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class Game {
    public int identificadorApp;
    public String nome;
    public String dataLancamento;
    public int proprietariosEstimados;
    public double preco;
    public String[] idiomasSuportados;
    public int pontuacaoMetacritic;
    public double pontuacaoUsuario;
    public int conquistas;
    public String[] editoras;
    public String[] desenvolvedores;
    public String[] categorias;
    public String[] generos;
    public String[] etiquetas;

    public String proprietariosEstimadosBruto;
    public String precoBruto;
    public String pontuacaoUsuarioBruta;
    public String conquistasBruto;

    public Game() {
        this.identificadorApp = 0;
        this.nome = "";
        this.dataLancamento = "dd/mm/aaaa";
        this.proprietariosEstimados = 0;
        this.preco = 0.0;
        this.idiomasSuportados = null;
        this.pontuacaoMetacritic = -1;
        this.pontuacaoUsuario = -1.0;
        this.conquistas = 0;
        this.editoras = null;
        this.desenvolvedores = null;
        this.categorias = null;
        this.generos = null;
        this.etiquetas = null;
        this.proprietariosEstimadosBruto = "";
        this.precoBruto = "";
        this.pontuacaoUsuarioBruta = "";
        this.conquistasBruto = "";
    }

    public Game(int id, String nome, String dataLancamento, String proprietariosBruto, String precoBruto,
                String[] idiomas, int metacritic, String userScoreBruto, String conquistasBruto, String[] editoras,
                String[] desenvolvedores, String[] categorias, String[] generos, String[] etiquetas) {
        this.identificadorApp = id;
        this.nome = nome;
        this.dataLancamento = dataLancamento;
        this.proprietariosEstimadosBruto = proprietariosBruto;
        this.precoBruto = precoBruto;
        this.idiomasSuportados = idiomas;
        this.pontuacaoMetacritic = metacritic;
        this.pontuacaoUsuarioBruta = userScoreBruto;
        this.conquistasBruto = conquistasBruto;
        this.editoras = editoras;
        this.desenvolvedores = desenvolvedores;
        this.categorias = categorias;
        this.generos = generos;
        this.etiquetas = etiquetas;

        this.proprietariosEstimados = 0;
        this.preco = 0.0;
        this.pontuacaoUsuario = -1.0;
        this.conquistas = 0;
    }

    public Game formatarDadosJogo(Game jogo) {
        try {
            String dataOriginal = jogo.dataLancamento.trim();
            Date dataConvertida = null;
            String[] padroesDeData = { "MMM dd, yyyy", "MMMM dd, yyyy", "yyyy-MM-dd", "dd/MM/yyyy", "MM/dd/yyyy" };
            for (String padrao : padroesDeData) {
                try {
                    dataConvertida = new SimpleDateFormat(padrao, Locale.ENGLISH).parse(dataOriginal);
                    break;
                } catch (ParseException e) {}
            }
            if (dataConvertida != null) {
                jogo.dataLancamento = new SimpleDateFormat("dd/MM/yyyy").format(dataConvertida);
            } else {
                jogo.dataLancamento = "01/01/1970";
            }
        } catch (Exception e) {
            jogo.dataLancamento = "01/01/1970";
        }

        StringBuilder construtorDigitos = new StringBuilder();
        String proprietariosRaw = jogo.proprietariosEstimadosBruto.trim();
        for (int i = 0; i < proprietariosRaw.length(); i++) {
            if (Character.isDigit(proprietariosRaw.charAt(i))) {
                construtorDigitos.append(proprietariosRaw.charAt(i));
            }
        }
        if (construtorDigitos.length() > 0) {
            jogo.proprietariosEstimados = Integer.parseInt(construtorDigitos.toString());
        } else {
            jogo.proprietariosEstimados = 0;
        }

        if (jogo.precoBruto.equalsIgnoreCase("Free to Play")) {
            jogo.preco = 0.0;
        } else {
            try {
                jogo.preco = Double.parseDouble(jogo.precoBruto);
            } catch (NumberFormatException e) {
                jogo.preco = 0.0;
            }
        }

        if (jogo.pontuacaoMetacritic == 0) {
            jogo.pontuacaoMetacritic = -1;
        }

        if (jogo.pontuacaoUsuarioBruta.equalsIgnoreCase("tbd")) {
            jogo.pontuacaoUsuario = -1.0;
        } else {
            try {
                jogo.pontuacaoUsuario = Double.parseDouble(jogo.pontuacaoUsuarioBruta);
            } catch (NumberFormatException e) {
                jogo.pontuacaoUsuario = -1.0;
            }
        }

        if (jogo.conquistasBruto.trim().isEmpty()) {
            jogo.conquistas = 0;
        } else {
            try {
                jogo.conquistas = Integer.parseInt(jogo.conquistasBruto);
            } catch (NumberFormatException e) {
                jogo.conquistas = 0;
            }
        }

        return jogo;
    }

    public static String formatarArrayParaString(String[] array) {
        if (array == null || array.length == 0) return "[]";
        return "[" + String.join(", ", array) + "]";
    }

    @Override
    public String toString() {
        return "=> " + this.identificadorApp + " ## " +
                this.nome + " ## " +
                this.dataLancamento + " ## " +
                this.proprietariosEstimados + " ## " +
                this.preco + " ## " +
                formatarArrayParaString(this.idiomasSuportados) + " ## " +
                this.pontuacaoMetacritic + " ## " +
                this.pontuacaoUsuario + " ## " +
                this.conquistas + " ## " +
                formatarArrayParaString(this.desenvolvedores) + " ## " +
                formatarArrayParaString(this.editoras) + " ## " +
                formatarArrayParaString(this.categorias) + " ## " +
                formatarArrayParaString(this.generos) + " ## " +
                formatarArrayParaString(this.etiquetas) + " ##";
    }

    public static class ordering {
        static int comparacoes = 0;

        public static void ordenarPorNome(ArrayList<Game> jogos) {
            int n = jogos.size();
            for (int i = n / 2 - 1; i >= 0; i--) {
                reconstroi(jogos, n, i);
            }
            for (int i = n - 1; i > 0; i--) {
                swap(jogos, 0, i);
                reconstroi(jogos, i, 0);
            }
        }

        private static void reconstroi(ArrayList<Game> jogos, int n, int i) {
            int maior = i;
            int esq = 2 * i + 1;
            int dir = 2 * i + 2;

            comparacoes++;
            if (esq < n && Game.normalize(jogos.get(esq).nome).compareTo(Game.normalize(jogos.get(maior).nome)) > 0) {
                maior = esq;
            }

            comparacoes++;
            if (dir < n && Game.normalize(jogos.get(dir).nome).compareTo(Game.normalize(jogos.get(maior).nome)) > 0) {
                maior = dir;
            }

            if (maior != i) {
                swap(jogos, i, maior);
                reconstroi(jogos, n, maior);
            }
        }

        private static void swap(ArrayList<Game> jogos, int i, int j) {
            Game temp = jogos.get(i);
            jogos.set(i, jogos.get(j));
            jogos.set(j, temp);
        }

        public static boolean pesquisa_binaria(ArrayList<Game> jogos, String gameName) {
            int left = 0;
            int right = jogos.size() - 1;

            while (left <= right) {
                int mid = left + (right - left) / 2;
                comparacoes++;
                int cmp = Game.normalize(jogos.get(mid).nome).compareTo(Game.normalize(gameName));
                if (cmp == 0) return true;
                else if (cmp < 0) left = mid + 1;
                else right = mid - 1;
            }
            return false;
        }
    }

    // normaliza strings para comparação: remove acentos, caracteres especiais e unifica espaços/minúsculas
    public static String normalize(String s) {
        if (s == null) return "";
        String n = s.trim().toLowerCase(Locale.ROOT);
        n = Normalizer.normalize(n, Normalizer.Form.NFD).replaceAll("\\p{M}", "");
        n = n.replaceAll("[^a-z0-9\\s]", "");
        n = n.replaceAll("\\s+", " ");
        return n;
    }

    public static Game findGameById(int id, ArrayList<Game> listaJogos) {
        for (Game jogo : listaJogos) {
            if (jogo.identificadorApp == id) return jogo;
        }
        return null;
    }

    public static void escreverLog(String matricula, long tempoExecucao, int comparacoes) {
        String nomeARQ = matricula + "_binaria.txt";
        try (PrintWriter escritor = new PrintWriter(new FileWriter(nomeARQ))) {
            escritor.println(matricula + "\t" + tempoExecucao + "\t" + comparacoes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String caminhoArquivoCSV = "/tmp/games.csv";
        ArrayList<Game> listaDeJogos = new ArrayList<>();

        try (BufferedReader leitorArquivo = new BufferedReader(new FileReader(caminhoArquivoCSV))) {
            leitorArquivo.readLine();
            String linhaAtual;
            while ((linhaAtual = leitorArquivo.readLine()) != null) {
                ArrayList<String> listaDeCampos = new ArrayList<>();
                StringBuilder construtorString = new StringBuilder();
                boolean estaDentroDeAspas = false;

                for (int i = 0; i < linhaAtual.length(); i++) {
                    char caractereAtual = linhaAtual.charAt(i);
                    if (caractereAtual == '"') estaDentroDeAspas = !estaDentroDeAspas;
                    else if (caractereAtual == ',' && !estaDentroDeAspas) {
                        listaDeCampos.add(construtorString.toString().trim());
                        construtorString.setLength(0);
                    } else construtorString.append(caractereAtual);
                }
                listaDeCampos.add(construtorString.toString().trim());
                String[] campos = listaDeCampos.toArray(new String[0]);

                Game novoJogo = new Game(
                        Integer.parseInt(campos[0]),
                        campos[1],
                        campos[2],
                        campos[3],
                        campos[4],
                        campos[5].replaceAll("[\\[\\]\"']", "").split(","),
                        Integer.parseInt(campos[6]),
                        campos[7],
                        campos[8],
                        campos[9].replaceAll("[\\[\\]\"']", "").split(","),
                        campos[10].replaceAll("[\\[\\]\"']", "").split(","),
                        campos[11].replaceAll("[\\[\\]\"']", "").split(","),
                        campos[12].replaceAll("[\\[\\]\"']", "").split(","),
                        campos[13].replaceAll("[\\[\\]\"']", "").split(",")
                );

                novoJogo.formatarDadosJogo(novoJogo);
                listaDeJogos.add(novoJogo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Scanner leitorEntrada = new Scanner(System.in);
        ArrayList<Game> jogosParaPesquisa = new ArrayList<>();

        while (true) {
            if (!leitorEntrada.hasNextLine()) break;
            String entrada = leitorEntrada.nextLine().trim();
            if (entrada.equalsIgnoreCase("FIM")) break;
            if (entrada.matches("\\d+")) {
                Game jogo = findGameById(Integer.parseInt(entrada), listaDeJogos);
                if (jogo != null) {
                    jogosParaPesquisa.add(jogo);
                }
            }
        }

        ordering.comparacoes = 0;
        long tempoInicio = System.nanoTime();
        ordering.ordenarPorNome(jogosParaPesquisa);

        while (true) {
            if (!leitorEntrada.hasNextLine()) break;
            String nome = leitorEntrada.nextLine().trim();
            if (nome.equalsIgnoreCase("FIM")) break;
            if (!nome.isEmpty()) {
                boolean encontrado = ordering.pesquisa_binaria(jogosParaPesquisa, nome);
                System.out.println(encontrado ? " SIM" : " NAO");
            }
        }

        long tempoFim = System.nanoTime();
        long tempoExecucao = tempoFim - tempoInicio;
        escreverLog("845963", tempoExecucao, ordering.comparacoes);

        leitorEntrada.close();
    }
}