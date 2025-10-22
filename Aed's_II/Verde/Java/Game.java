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

    // (Construtores e formatarDadosJogo permanecem os mesmos)
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
        // contadores estáticos
        static int comparacoesSort = 0;
        static int comparacoesSearch = 0;
        static int movimentos = 0;

        /**
         * Método de swap (troca) de dois elementos no ArrayList.
         * Incrementa o contador de movimentos.
         */
        private static void swap(ArrayList<Game> jogos, int i, int j) {
            if (i == j) return;
            Game temp = jogos.get(i);
            jogos.set(i, jogos.get(j));
            jogos.set(j, temp);
            movimentos++; // conta como uma movimentação (troca)
        }
        
        // --- INÍCIO: NOVOS MÉTODOS HEAPSORT POR ESTIMADOS ---

        /**
         * Ordena o ArrayList de jogos usando Heapsort.
         * Chave primária: proprietariosEstimados (ascendente)
         * Chave secundária: identificadorApp (ascendente)
         */
        public static void ordenarPorEstimados(ArrayList<Game> jogos) {
            int n = jogos.size();
            
            // 1. Construir o Max Heap (o maior elemento fica na raiz)
            // Começa do último nó "pai" e vai até a raiz
            for (int i = n / 2 - 1; i >= 0; i--) {
                reconstroiPorEstimados(jogos, n, i);
            }
            
            // 2. Extrair elementos um por um do heap
            for (int i = n - 1; i > 0; i--) {
                // Move a raiz atual (maior elemento) para o fim do array
                swap(jogos, 0, i);
                
                // Chama o reconstroi na heap reduzida (tamanho 'i')
                reconstroiPorEstimados(jogos, i, 0);
            }
        }

        /**
         * Função auxiliar (heapify/sift-down) para manter a propriedade do Max Heap.
         * Compara usando 'proprietariosEstimados' e 'identificadorApp' como desempate.
         */
        private static void reconstroiPorEstimados(ArrayList<Game> jogos, int n, int i) {
            int maior = i;
            int esq = 2 * i + 1;
            int dir = 2 * i + 2;

            // Compara com o filho da esquerda
            if (esq < n) {
                // Comparação 1: 'esq' > 'maior' (por proprietariosEstimados)
                comparacoesSort++; 
                if (jogos.get(esq).proprietariosEstimados > jogos.get(maior).proprietariosEstimados) {
                    maior = esq;
                } 
                // Comparação 2: 'esq' == 'maior' (verifica empate)
                else {
                    comparacoesSort++; 
                    if (jogos.get(esq).proprietariosEstimados == jogos.get(maior).proprietariosEstimados) {
                        // Comparação 3: 'esq.id' > 'maior.id' (desempate)
                        comparacoesSort++; 
                        if (jogos.get(esq).identificadorApp > jogos.get(maior).identificadorApp) {
                            maior = esq;
                        }
                    }
                }
            }

            // Compara com o filho da direita (usando 'maior' atualizado)
            if (dir < n) {
                // Comparação 1: 'dir' > 'maior' (por proprietariosEstimados)
                comparacoesSort++; 
                if (jogos.get(dir).proprietariosEstimados > jogos.get(maior).proprietariosEstimados) {
                    maior = dir;
                } 
                // Comparação 2: 'dir' == 'maior' (verifica empate)
                else {
                    comparacoesSort++; 
                    if (jogos.get(dir).proprietariosEstimados == jogos.get(maior).proprietariosEstimados) {
                        // Comparação 3: 'dir.id' > 'maior.id' (desempate)
                        comparacoesSort++; 
                        if (jogos.get(dir).identificadorApp > jogos.get(maior).identificadorApp) {
                            maior = dir;
                        }
                    }
                }
            }

            // Se 'maior' mudou (não é mais o 'i' original), troca e continua
            if (maior != i) {
                swap(jogos, i, maior);
                // Recursivamente reconstroi a sub-árvore afetada
                reconstroiPorEstimados(jogos, n, maior);
            }
        }
        
        // --- FIM: NOVOS MÉTODOS HEAPSORT ---


        // (Métodos antigos de ordenação por nome e pesquisa binária podem ser mantidos ou removidos)
        // (Vou mantê-los para não quebrar referências, embora não sejam usados no 'main' modificado)
        
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
            
            comparacoesSort++; // (Contador do sort por nome)
            if (esq < n && Game.normalize(jogos.get(esq).nome).compareTo(Game.normalize(jogos.get(maior).nome)) > 0) {
                maior = esq;
            }
            
            comparacoesSort++; // (Contador do sort por nome)
            if (dir < n && Game.normalize(jogos.get(dir).nome).compareTo(Game.normalize(jogos.get(maior).nome)) > 0) {
                maior = dir;
            }

            if (maior != i) {
                swap(jogos, i, maior);
                reconstroi(jogos, n, maior);
            }
        }

        public static boolean pesquisa_binaria(ArrayList<Game> jogos, String gameName) {
            int left = 0;
            int right = jogos.size() - 1;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                comparacoesSearch++;
                int cmp = Game.normalize(jogos.get(mid).nome).compareTo(Game.normalize(gameName));
                if (cmp == 0) return true;
                else if (cmp < 0) left = mid + 1;
                else right = mid - 1;
            }
            return false;
        }
    }


    // (normalize e findGameById permanecem os mesmos)
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

    // --- Métodos de LOG ---

    // (Log antigo da pesquisa binária - mantido, mas não usado)
    public static void escreverLog(String matricula, long tempoExecucao, int comparacoes) {
        String nomeARQ = matricula + "_binaria.txt";
        try (PrintWriter escritor = new PrintWriter(new FileWriter(nomeARQ))) {
            escritor.println(matricula + "\t" + tempoExecucao + "\t" + comparacoes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * NOVO MÉTODO DE LOG: Escreve o log do Heapsort.
     * Formato: matricula \t comparacoes \t movimentos \t tempoExecucao
     */
    public static void escreverLogHeapsort(String matricula, long tempoExecucao, int comparacoes, int movimentos) {
        String nomeARQ = matricula + "_heapsort.txt";
        try (PrintWriter escritor = new PrintWriter(new FileWriter(nomeARQ))) {
            // Formato: Matrícula, Comparações, Movimentações, Tempo
            escritor.println(matricula + "\t" + comparacoes + "\t" + movimentos + "\t" + tempoExecucao);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
       // String caminhoArquivoCSV = "C:\\Users\\kakab\\OneDrive\\Área de Trabalho\\git\\Aeds-s_II\\Aed's_II\\Verde\\Java\\games.csv";
         String caminhoArquivoCSV = "/tmp/games.csv"; 
        
        ArrayList<Game> listaDeJogos = new ArrayList<>();

        // (Leitura do CSV permanece a mesma)
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
        ArrayList<Game> jogosParaOrdenacao = new ArrayList<>();

        while (true) {
            if (!leitorEntrada.hasNextLine()) break;
            String entrada = leitorEntrada.nextLine().trim();
            if (entrada.equalsIgnoreCase("FIM")) break; 
            if (entrada.matches("\\d+")) {
                Game jogo = findGameById(Integer.parseInt(entrada), listaDeJogos);
                if (jogo != null) {
                    jogosParaOrdenacao.add(jogo);
                }
            }
        }

        
        ordering.comparacoesSort = 0;
        ordering.movimentos = 0;
        
        long tempoInicio = System.nanoTime();
        
        ordering.ordenarPorEstimados(jogosParaOrdenacao);
        
        long tempoFim = System.nanoTime();
        long tempoExecucao = tempoFim - tempoInicio;
        


        for (Game g : jogosParaOrdenacao) {
            System.out.println(g);
        }


        String matricula = "845963"; 
        escreverLogHeapsort(matricula, tempoExecucao, ordering.comparacoesSort, ordering.movimentos);

        leitorEntrada.close();
    }
}