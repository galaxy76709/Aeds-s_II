import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

/**
 * A classe Game representa os dados de um jogo extraídos de um arquivo CSV.
 * Ela contém métodos para ler, processar e exibir as informações do jogo.
 */
public class Game {
    // Atributos que descrevem um jogo
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

    // Atributos para armazenar os dados brutos antes do processamento
    public String proprietariosEstimadosBruto;
    public String precoBruto;
    public String pontuacaoUsuarioBruta;
    public String conquistasBruto;

    /**
     * Construtor padrão. Inicializa um objeto Game com valores nulos ou padrão.
     */
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

    /**
     * Construtor parametrizado.
     * Recebe os dados brutos lidos do arquivo CSV e os armazena nos atributos correspondentes.
     */
    public Game(int id, String nome, String dataLancamento, String proprietariosBruto, String precoBruto, String[] idiomas,
                int metacritic, String userScoreBruto, String conquistasBruto, String[] editoras, String[] desenvolvedores,
                String[] categorias, String[] generos, String[] etiquetas) {
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
        
        // Inicializa os campos processados com valores padrão
        this.proprietariosEstimados = 0;
        this.preco = 0.0;
        this.pontuacaoUsuario = -1.0;
        this.conquistas = 0;
    }

    /**
     * Processa e formata os dados brutos de um objeto Game.
     * Converte strings em tipos numéricos, formata datas e trata valores especiais.
     * @param jogo O objeto Game a ser processado.
     * @return O mesmo objeto Game com os dados formatados.
     */
    public Game formatarDadosJogo(Game jogo) {
        
        // --- 1. Formatação da Data de Lançamento ---
        try {
            String dataOriginal = jogo.dataLancamento.trim();
            Date   dataConvertida = null;

            // Tenta diferentes formatos de data até encontrar um compatível
            String[] padroesDeData = { "MMM dd, yyyy", "MMMM dd, yyyy", "yyyy-MM-dd", "dd/MM/yyyy", "MM/dd/yyyy" };

            for (String padrao : padroesDeData) {
                try {
                    dataConvertida = new SimpleDateFormat(padrao, Locale.ENGLISH).parse(dataOriginal);
                    break; // Se a conversão for bem-sucedida, para o loop
                } catch (ParseException e) {
                    // Ignora e tenta o próximo formato
                }
            }

            // Se uma data foi convertida, formata para o padrão "dd/MM/yyyy"
            if (dataConvertida != null) {
                jogo.dataLancamento = new SimpleDateFormat("dd/MM/yyyy").format(dataConvertida);
            } else {
                jogo.dataLancamento = "01/01/1970"; // Data padrão em caso de falha
            }
        } catch (Exception e) {
            jogo.dataLancamento = "01/01/1970"; // Data padrão em caso de erro inesperado
        }

        // --- 2. Processamento do Número de Proprietários ---
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

        // --- 3. Processamento do Preço ---
        if (jogo.precoBruto.equalsIgnoreCase("Free to Play")) {
            jogo.preco = 0.0;
        } else {
            try {
                jogo.preco = Double.parseDouble(jogo.precoBruto);
            } catch (NumberFormatException e) {
                jogo.preco = 0.0; // Valor padrão se a conversão falhar
            }
        }

        // --- 4. Ajuste da Pontuação Metacritic ---
        // Considera 0 como uma pontuação não informada, padronizando para -1
        if (jogo.pontuacaoMetacritic == 0) {
            jogo.pontuacaoMetacritic = -1;
        }

        // --- 5. Processamento da Pontuação do Usuário ---
        if (jogo.pontuacaoUsuarioBruta.equalsIgnoreCase("tbd")) {
            jogo.pontuacaoUsuario = -1.0; // "To Be Determined"
        } else {
            try {
                jogo.pontuacaoUsuario = Double.parseDouble(jogo.pontuacaoUsuarioBruta);
            } catch (NumberFormatException e) {
                jogo.pontuacaoUsuario = -1.0; // Valor padrão se a conversão falhar
            }
        }

        // --- 6. Processamento do Número de Conquistas ---
        if (jogo.conquistasBruto.trim().isEmpty()) {
            jogo.conquistas = 0;
        } else {
            try {
                jogo.conquistas = Integer.parseInt(jogo.conquistasBruto);
            } catch (NumberFormatException e) {
                jogo.conquistas = 0; // Valor padrão em caso de erro
            }
        }

        return jogo;
    }

    /**
     * Busca recursivamente por um jogo em uma lista com base no ID e o exibe.
     * @param indice O índice atual da busca na lista.
     * @param idParaBuscar O ID do jogo a ser encontrado.
     * @param listaDeJogos A lista onde a busca será feita.
     */
    public static void exibirJogoPorId(int indice, int idParaBuscar, ArrayList<Game> listaDeJogos) {
        // Condição de parada: se o índice ultrapassar o tamanho da lista
        if (indice >= listaDeJogos.size()) {
            return;
        }

        // Se encontrar o jogo, exibe e encerra a recursão
        if (listaDeJogos.get(indice).identificadorApp == idParaBuscar) {
            System.out.println(listaDeJogos.get(indice).toString());
            return;
        }

        // Chamada recursiva para o próximo elemento da lista
        exibirJogoPorId(indice + 1, idParaBuscar, listaDeJogos);
    }

    /**
     * Converte um array de Strings para uma única String formatada.
     * Exemplo: ["A", "B"] se torna "[A, B]".
     * @param array O array a ser formatado.
     * @return A string formatada.
     */
    public static String formatarArrayParaString(String[] array) {
        if (array == null || array.length == 0) {
            return "[]";
        }
        return "[" + String.join(", ", array) + "]";
    }

    /**
     * Sobrescreve o método toString para fornecer uma representação textual do objeto Game.
     * O formato é customizado para a saída do programa.
     */
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

    /**
     * Método principal que executa o programa.
     */
    public static void main(String[] args) {
        // Define o caminho do arquivo CSV a ser lido
        String caminhoArquivoCSV = "/tmp/games.csv";
        ArrayList<Game> listaDeJogos = new ArrayList<>();

        // --- Leitura e Processamento do Arquivo CSV ---
        try (BufferedReader leitorArquivo = new BufferedReader(new FileReader(caminhoArquivoCSV))) {
            leitorArquivo.readLine(); // Pula a primeira linha (cabeçalho)
            String linhaAtual;

            while ((linhaAtual = leitorArquivo.readLine()) != null) {
                ArrayList<String> listaDeCampos = new ArrayList<>();
                StringBuilder construtorString = new StringBuilder();
                boolean estaDentroDeAspas = false;

                // Lógica para separar os campos, tratando vírgulas dentro de aspas
                for (int i = 0; i < linhaAtual.length(); i++) {
                    char caractereAtual = linhaAtual.charAt(i);

                    if (caractereAtual == '"') {
                        estaDentroDeAspas = !estaDentroDeAspas; // Inverte o estado
                    } else if (caractereAtual == ',' && !estaDentroDeAspas) {
                        listaDeCampos.add(construtorString.toString().trim());
                        construtorString.setLength(0); // Limpa o construtor para o próximo campo
                    } else {
                        construtorString.append(caractereAtual);
                    }
                }
                listaDeCampos.add(construtorString.toString().trim()); // Adiciona o último campo

                String[] campos = listaDeCampos.toArray(new String[0]);

                // Cria um objeto Game com os dados lidos da linha
                Game novoJogo = new Game(
                    Integer.parseInt(campos[0]),
                    campos[1],
                    campos[2],
                    campos[3],
                    campos[4],
                    campos[5].replaceAll("[\\[\\]\"\'']", "").split(","),
                    Integer.parseInt(campos[6]),
                    campos[7],
                    campos[8],
                    campos[9].replaceAll("[\\[\ude20\'']", "").split(","),
                    campos[10].replaceAll("[\\[\\]\"\'']", "").split(","),
                    campos[11].replaceAll("[\\[\\]\"\'']", "").split(","),
                    campos[12].replaceAll("[\\[\\]\"\'']", "").split(","),
                    campos[13].replaceAll("[\\[\\]\"\'']", "").split(",")
                );
                
                // Processa os dados brutos para os formatos corretos
                novoJogo.formatarDadosJogo(novoJogo);
                listaDeJogos.add(novoJogo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // --- Leitura da Entrada do Usuário ---
        Scanner leitorEntrada = new Scanner(System.in);

        while (leitorEntrada.hasNextLine()) {
            String entradaUsuario = leitorEntrada.nextLine().trim();

            if (entradaUsuario.isEmpty()) {
                continue; // Ignora linhas em branco
            }

            try {
                int idParaBuscar = Integer.parseInt(entradaUsuario);
                // Inicia a busca recursiva pelo ID fornecido
                exibirJogoPorId(0, idParaBuscar, listaDeJogos);
            } catch (NumberFormatException e) {
                System.out.println("ID inválido: " + entradaUsuario);
            }
        }

        leitorEntrada.close();
    }
}