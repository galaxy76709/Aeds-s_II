package Verde.Java;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Games
{
// valores (variaveis) do arquivo csv
    public int                          id; 
    public int              estimatedOwners;
    public int                 achievements;
    public int              metacriticScore;
    public double                     price;
    public double                 userScore;
    public String                      name;
    public String               releaseDate;
    public String [ ]    supportedLanguages;
    public String [ ]            publishers;
    public String [ ]            developers;
    public String [ ]            categories;
    public String [ ]                genres;
    public String [ ]                  tags;

    public Games( 
    // construtor basico para criacao de valores setados
     int id, int estimatedOwners, int achievements,
     int metacriticScore, double price, double userScore, String name, String releaseDate, 
     String [ ] supportedLanguages, String [ ] publishers, String [ ] developers, 
     String [ ] categories, String [ ] genres, String [ ] tags ) 
    {
        this.id                     =               id;
        this.estimatedOwners        =   estimatedOwners;
        this.achievements           =      achievements;
        this.metacriticScore        =   metacriticScore;
        this.price                  =            price;
        this.userScore              =         userScore;
        this.name                   =              name;
        this.releaseDate            =       releaseDate;
        this.supportedLanguages     =   supportedLanguages;
        this.publishers             =        publishers;
        this.developers             =        developers;
        this.categories             =        categories;
        this.genres                 =            genres;
        this.tags                   =              tags;

        ERRO( );    // normalizar campos
    }

    //---------------------------||---------------------------\\
    
    public void ERRO ( ) 
    {
    //---------------------------||---------------------------\\
        // Corrigir data
        if ( releaseDate != null && releaseDate.trim().length() > 0 )
        {
            String [ ] partes = releaseDate.split("/");
            String novaData = "";
            int i = 0;

            while ( i < 3 )
            {
                if ( i < partes.length && partes[i].trim().length() > 0 )
                { novaData = novaData + partes[i]; }
                else
                { novaData = novaData + "01"; }

                if ( i < 2 )
                { novaData = novaData + "/"; }

                i = i + 1;
            }

            releaseDate = novaData;
        }

    //---------------------------||---------------------------\\
        if ( estimatedOwners < 0 )
        { estimatedOwners = 0; }

    //---------------------------||---------------------------\\
        if ( price < 0 )
        { price = 0.0; }

    //---------------------------||---------------------------\\
        if ( metacriticScore <= 0 )
        { metacriticScore = -1; }

    //---------------------------||---------------------------\\
        if ( userScore <= 0.0 )
        { userScore = -1.0; }

    //---------------------------||---------------------------\\
        if ( achievements < 0 )
        { achievements = 0; }

    //---------------------------||---------------------------\\
    }

    public static class CSVreader 
    {
        public static List<Games> readCSV(String filePath) 
        { 
            List <Games> gamesList = new ArrayList<Games>(); 

            try
            {
                BufferedReader br = new BufferedReader(new FileReader(filePath));
                String line = br.readLine();  // descartar cabeçalho

                line = br.readLine();

                while ( line != null )
                {
                    if ( line.trim().length() > 0 )
                    {
                        String [ ] fields = line.split(",");

                        if ( fields.length >= 14 )
                        {
                            int id                       = parseIntSafe (fields [0]);
                            String name                  = fields [ 1 ];
                            String releaseDate           = fields [ 2 ];
                            int estimatedOwners          = parseIntSafe(fields[3]);
                            double price                 = parseDoubleSafe(fields[4]);
                            String[] supportedLanguages  = toArray(fields[5]);
                            int metacriticScore          = parseIntSafe(fields[6]);
                            double userScore             = parseDoubleSafe(fields[7]);
                            int achievements             = parseIntSafe(fields[8]);
                            String[] publishers          = toArray(fields[9]);
                            String[] developers          = toArray(fields[10]);
                            String[] categories          = toArray(fields[11]);
                            String[] genres              = toArray(fields[12]);
                            String[] tags                = toArray(fields[13]);

                            Games game = new Games (
                                id, estimatedOwners, achievements,
                                metacriticScore, price, userScore,
                                name, releaseDate,
                                supportedLanguages, publishers,
                                developers, categories,
                                genres, tags
                            );

                            gamesList.add(game);
                        }
                    }
                    line = br.readLine();
                }

                br.close();

            }
            catch (IOException e)
            {
                e.printStackTrace();
            }

            return gamesList;
        }

        //---------------------------||---------------------------\\
        private static int parseIntSafe (String s)
        {
            int value = 0;
            if ( s != null && s.trim().length() > 0 )
            {
                try
                { value = Integer.parseInt(s.trim().replaceAll("[^0-9]", "")); }
                catch (Exception e)
                { value = 0; }
            }
            return value;
        }

        //---------------------------||---------------------------\\
        private static double parseDoubleSafe (String s)
        {
            double value = 0.0;
            if ( s != null && s.trim().length() > 0 )
            {
                try
                { value = Double.parseDouble(s.trim().replaceAll("[^0-9.]", "")); }
                catch (Exception e)
                { value = 0.0; }
            }
            return value;
        }

        //---------------------------||---------------------------\\
        private static String [ ] toArray (String s)
        {
            String [ ] arr = new String [0];

            if ( s != null && s.trim().length() > 0 )
            {
                s = s.replace("[", "");
                s = s.replace("]", "");

                arr = s.split(",");

                int i = 0;
                while ( i < arr.length )
                {
                    arr[i] = arr[i].trim();
                    i = i + 1;
                }
            }
            return arr;
        }
        //---------------------------||---------------------------\\
    }

    //---------------------------||---------------------------\\
    @Override
    public String toString ( )
    {
        return "Game [id=" + id + ", name=" + name +
               ", releaseDate=" + releaseDate +
               ", owners=" + estimatedOwners +
               ", price=" + price +
               ", meta=" + metacriticScore +
               ", userScore=" + userScore + "]";
    }

    //---------------------------||---------------------------\\
    public static void main ( String [ ] args ) 
    {
        String filePath = "tmp/games.csv";

        // Ler os jogos
        List <Games> gamesList = Games.CSVreader.readCSV ( filePath );

        // Mostrar quantos foram carregados
       // System.out.println ( "Total de jogos carregados: " + gamesList.size() );

        // Exibir todos
        //System.out.println ( "\nLista completa de jogos:\n" );

        int i = 0;
        while ( i < gamesList.size() )
        {
            System.out.println ( gamesList.get(i) );
            i = i + 1;
        }
    }

}
