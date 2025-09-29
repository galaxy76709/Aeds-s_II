package Verde.Java;
public class Games
{
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

    
    public void ERRO ( Games game ) 
    {

        String [ ] relaseDate_TMP = game.releaseDate.split("/"); 
        StringBuilder sb  = new StringBuilder( );  

        for ( int i = 0; i < 3; i++ ) 
        {
            if ( relaseDate_TMP [i] .equals(" ") )
            { sb.append ( "01" ); } 
            
            else 
            { sb.append ( relaseDate_TMP [ i ] ) ; }

        }

        game.releaseDate = sb.toString( ); 

    String estimatedOwners_STR = String.valueOf(game.estimatedOwners);
    StringBuilder sb_2 = new StringBuilder(); 

for ( int i = 0 ; i < estimatedOwners_STR.length(); i++  )
{
    if ( Character.isDigit(estimatedOwners_STR.charAt(i)) )
    {
        sb_2.append(estimatedOwners_STR.charAt(i));
    }

}

    game.estimatedOwners = Integer.parseInt(sb_2.toString());

    }

}