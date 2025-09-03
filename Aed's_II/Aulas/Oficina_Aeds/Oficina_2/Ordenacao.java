package Aulas.Oficina_Aeds.Oficina_2;

import java.util.*;

class Jogador {
    String nome;
    int gols;
    int assist;
    int partidas;
    double media;

    Jogador(String nome, int gols, int assist, int partidas) {
        this.nome = nome;
        this.gols = gols;
        this.assist = assist;
        this.partidas = partidas;
        this.media = (double)(gols + assist) / partidas;
    }
}

public class Ordenacao {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());
        List<Jogador> jogadores = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String linha = sc.nextLine();
            String[] dados = linha.split(" ");

            String nome = dados[0];
            int gols = 0;
            int assist = 0;
            int partidas = (dados.length - 1) / 2;

            for (int j = 1; j < dados.length; j += 2) {
                gols += Integer.parseInt(dados[j]);
                assist += Integer.parseInt(dados[j + 1]);
            }

            jogadores.add(new Jogador(nome, gols, assist, partidas));
        }

        // Ordenar com base nos critérios
        jogadores.sort((a, b) -> {
            if (Double.compare(b.media, a.media) != 0)
                return Double.compare(b.media, a.media); // maior média primeiro
            if (b.gols != a.gols)
                return b.gols - a.gols; // mais gols primeiro
            return a.nome.compareTo(b.nome); // ordem alfabética
        });

        // Impressão
        System.out.println("Ranking de jogadores:");
        for (int i = 0; i < jogadores.size(); i++) {
            Jogador j = jogadores.get(i);
            String mediaFormatada = String.format("%.2f", j.media);
            System.out.printf("%d. %s - Media: %s - Gols: %d\n", 
                              i + 1, j.nome, mediaFormatada, j.gols);

            sc.close();
        }
    }
}
