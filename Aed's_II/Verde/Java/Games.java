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
    public int             estimatedOwners;
    public int                achievements;
    public int             metacriticScore;
    public double                    price;
    public double                userScore;
    public String                     name;
    public String              releaseDate;
    public String [ ]   supportedLanguages;
    public String [ ]           publishers;
    public String [ ]           developers;
    public String [ ]           categories;
    public String [ ]               genres;
    public String [ ]                 tags;

    public Games( 
    // construtor basico para criacao de valores setados
     int id, int estimatedOwners, int achievements,
     int metacriticScore, double price, double userScore, String name, String releaseDate, 
     String [ ] supportedLanguages, String [ ] publishers, String [ ] developers, 
     String [ ] categories, String [ ] genres, String [ ] tags ) 
    {
        this.id                     =               id;
        this.estimatedOwners        =  estimatedOwners;
        this.achievements           =     achievements;
        this.metacriticScore        =  metacriticScore;
        this.price                  =            price;
        this.userScore              =        userScore;
        this.name                   =             name;
        this.releaseDate            =      releaseDate;
        this.supportedLanguages     =  supportedLanguages;
        this.publishers             =        publishers;
        this.developers             =        developers;
        this.categories             =        categories;
        this.genres                 =           genres;
        this.tags                   =             tags;
    }

    //---------------------------||---------------------------\\

    
    public void ERRO ( Games game ) 
    {

    //---------------------------||---------------------------\\

        String [ ] relaseDate_TMP = game.releaseDate.split("/"); 
        StringBuilder sb  = new StringBuilder( );  

        for ( int i = 0; i < 3; i++ ) 
        {
            if ( i < relaseDate_TMP.length && !relaseDate_TMP[i].trim().isEmpty() )
            { sb.append ( relaseDate_TMP [ i ] ); } 
            
            else 
            { sb.append ( "01" ); }

            if ( i < 2 ) sb.append("/");
        }

        game.releaseDate = sb.toString( ); 

    //---------------------------||---------------------------\\

        String estimatedOwners_STR = String.valueOf(game.estimatedOwners);
        StringBuilder sb_2 = new StringBuilder(); 

        for ( int i = 0 ; i < estimatedOwners_STR.length(); i++  )
        {
            if ( Character.isDigit(estimatedOwners_STR.charAt(i)) )
            {
                sb_2.append(estimatedOwners_STR.charAt(i));
            }
        }

        if ( sb_2.length() > 0 )
            game.estimatedOwners = Integer.parseInt(sb_2.toString());
        else
            game.estimatedOwners = 0;

    //---------------------------||---------------------------\\

        // Se preço for "Free to Play" ou vazio
        String price_STR = String.valueOf( game.price );

        if ( price_STR.equalsIgnoreCase("Free to Play") || price_STR.trim().equals("0") )
        {  game.price = 0.0;  }

    //---------------------------||---------------------------\\

        // Se meta vazio
        if ( game.metacriticScore <= 0 )
        { game.metacriticScore = -1; }

    //---------------------------||---------------------------\\

        // Se userScore vazio ou "tbd"
        String userScore_STR = String.valueOf ( game.userScore ); 

        if ( userScore_STR.equalsIgnoreCase("tbd") || userScore_STR.trim().isEmpty() || game.userScore == 0.0 )
        { game.userScore = -1.0; }

    //---------------------------||---------------------------\\

        // Se achievements vazio
        if ( game.achievements < 0 )
        { game.achievements = 0; }

    //---------------------------||---------------------------\\

    }

    public static class CSVreader 
    {
        public static List<Games> readCSV(String filePath) 
        { 
            List <Games> gamesList = new ArrayList<>(); 

            try (BufferedReader br = new BufferedReader(new FileReader(filePath))) 
            {
// descartar cabeçalho
                String line = br.readLine();

                while ( (line = br.readLine()) != null )
                {
                    String [ ] fields = line.split(",");

// tratamento basico 
                    int id                      = parseIntSafe (fields [0]);
                    String name                 = fields [ 1 ];
                    String releaseDate          = fields [ 2 ];
                    int estimatedOwners         = parseIntSafe(fields[3]);
                    double price                = parseDoubleSafe(fields[4]);
                    String[] supportedLanguages = toArray(fields[5]);
                    int metacriticScore         = parseIntSafe(fields[6]);
                    double userScore            = parseDoubleSafe(fields[7]);
                    int achievements            = parseIntSafe(fields[8]);
                    String[] publishers         = toArray(fields[9]);
                    String[] developers         = toArray(fields[10]);
                    String[] categories         = toArray(fields[11]);
                    String[] genres             = toArray(fields[12]);
                    String[] tags               = toArray(fields[13]);

                    Games game = new Games (
                        id, estimatedOwners, achievements,
                        metacriticScore, price, userScore,
                        name, releaseDate,
                        supportedLanguages, publishers,
                        developers, categories,
                        genres, tags
                    );
// Chamar o tratamento de erros
                    game.ERRO(game);

                    gamesList.add(game);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            return gamesList;
        }

        //---------------------------||---------------------------\\

        private static int parseIntSafe (String s)
        {
            if ( s == null || s.trim().isEmpty() ) return 0;
            try { return Integer.parseInt(s.trim().replaceAll("[^0-9]", "")); }
            catch (Exception e) { return 0; }
        }

        //---------------------------||---------------------------\\

        private static double parseDoubleSafe (String s)
        {
            if ( s == null || s.trim().isEmpty() ) return 0.0;
            try { return Double.parseDouble(s.trim()); }
            catch (Exception e) { return 0.0; }
        }
        //---------------------------||---------------------------\\
        private static String [ ] toArray (String s)
        {
            if ( s == null || s.trim().isEmpty() ) return new String[0];

            s = s.replace("[", "").replace("]", "");
            String [ ] parts = s.split(",");

            for ( int i = 0; i < parts.length; i++ )
            { parts[i] = parts[i].trim(); }

            return parts;
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
        String filePath = "C:\\Users\\kakab\\OneDrive\\Área de Trabalho\\git\\Aeds-s_II\\Aed's_II\\Verde\\Java\\games.csv";

        // Ler os jogos
        List <Games> gamesList = Games.CSVreader.readCSV ( filePath );

        // Mostrar quantos foram carregados
        System.out.println ( "Total de jogos carregados: " + gamesList.size() );

        // Exibir todos
        System.out.println ( "\nLista completa de jogos:\n" );

        for ( Games g : gamesList )
        {
            System.out.println ( g );
        }
    }

}
