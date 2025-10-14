#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

#define MAX_STR      200
#define MAX_LIST     1000
#define MAX_ARRAY    50

//---------------------------||---------------------------\\

typedef struct Games
{
    int        id;
    int        estimatedOwners;
    int        achievements;
    int        metacriticScore;
    double     price;
    double     userScore;
    char       name[MAX_STR];
    char       releaseDate[MAX_STR];
    char       supportedLanguages[MAX_ARRAY][MAX_STR];
    char       publishers[MAX_ARRAY][MAX_STR];
    char       developers[MAX_ARRAY][MAX_STR];
    char       categories[MAX_ARRAY][MAX_STR];
    char       genres[MAX_ARRAY][MAX_STR];
    char       tags[MAX_ARRAY][MAX_STR];

    int        nSupportedLanguages;
    int        nPublishers;
    int        nDevelopers;
    int        nCategories;
    int        nGenres;
    int        nTags;

} Games;

//---------------------------||---------------------------\\
// funções auxiliares

int parseIntSafe ( const char *s )
{
    int value = 0;
    int i = 0;
    char tmp[MAX_STR];
    int j = 0;

    if ( s != NULL && strlen(s) > 0 )
    {
        while ( s[i] != '\0' )
        {
            if ( isdigit( (unsigned char) s[i] ) )
            {
                tmp[j] = s[i];
                j = j + 1;
            }
            i = i + 1;
        }
        tmp[j] = '\0';

        if ( j > 0 )
        { value = atoi(tmp); }
    }

    return value;
}

//---------------------------||---------------------------\\

double parseDoubleSafe ( const char *s )
{
    double value = 0.0;
    if ( s != NULL && strlen(s) > 0 )
    {
        value = atof(s);
    }
    return value;
}

//---------------------------||---------------------------\\

int toArray ( char array[MAX_ARRAY][MAX_STR], const char *s )
{
    int count = 0;
    char copy[MAX_STR * 2];
    char *token;

    if ( s != NULL && strlen(s) > 0 )
    {
        strcpy(copy, s);
        // remover colchetes
        char *p = copy;
        int i = 0;
        while ( copy[i] != '\0' )
        {
            if ( copy[i] == '[' || copy[i] == ']' )
            { copy[i] = ' '; }
            i = i + 1;
        }

        token = strtok(copy, ",");
        while ( token != NULL && count < MAX_ARRAY )
        {
            while ( *token == ' ' ) token++;
            strncpy(array[count], token, MAX_STR - 1);
            array[count][MAX_STR - 1] = '\0';

            count = count + 1;
            token = strtok(NULL, ",");
        }
    }

    return count;
}

//---------------------------||---------------------------\\

void normalizar ( Games *g )
{
    // Corrigir data
    if ( strlen(g->releaseDate) > 0 )
    {
        char tmp[MAX_STR];
        strcpy(tmp, g->releaseDate);

        char *partes[3];
        int n = 0;
        char *token = strtok(tmp, "/");

        while ( token != NULL && n < 3 )
        {
            partes[n] = token;
            n = n + 1;
            token = strtok(NULL, "/");
        }

        char novaData[MAX_STR] = "";
        int i = 0;

        while ( i < 3 )
        {
            if ( i < n && strlen(partes[i]) > 0 )
            {
                strcat(novaData, partes[i]);
            }
            else
            {
                strcat(novaData, "01");
            }

            if ( i < 2 )
            { strcat(novaData, "/"); }

            i = i + 1;
        }

        strcpy(g->releaseDate, novaData);
    }

    if ( g->estimatedOwners < 0 )
    { g->estimatedOwners = 0; }

    if ( g->price < 0 )
    { g->price = 0.0; }

    if ( g->metacriticScore <= 0 )
    { g->metacriticScore = -1; }

    if ( g->userScore <= 0.0 )
    { g->userScore = -1.0; }

    if ( g->achievements < 0 )
    { g->achievements = 0; }
}

//---------------------------||---------------------------\\

int readCSV ( const char *filePath, Games gamesList[MAX_LIST] )
{
    FILE *fp = fopen(filePath, "r");
    char line[2048];
    int count = 0;

    if ( fp == NULL )
    {
        printf("Erro ao abrir arquivo!\n");
        return 0;
    }

    // descartar cabeçalho
    fgets(line, sizeof(line), fp);

    while ( fgets(line, sizeof(line), fp) != NULL && count < MAX_LIST )
    {
        // remover \n
        line[strcspn(line, "\n")] = '\0';

        if ( strlen(line) > 0 )
        {
            char *fields[14];
            int i = 0;

            char *token = strtok(line, ",");
            while ( token != NULL && i < 14 )
            {
                fields[i] = token;
                i = i + 1;
                token = strtok(NULL, ",");
            }

            if ( i >= 14 )
            {
                Games g;

                g.id                  = parseIntSafe(fields[0]);
                strncpy(g.name, fields[1], MAX_STR - 1);
                g.name[MAX_STR - 1] = '\0';

                strncpy(g.releaseDate, fields[2], MAX_STR - 1);
                g.releaseDate[MAX_STR - 1] = '\0';

                g.estimatedOwners     = parseIntSafe(fields[3]);
                g.price               = parseDoubleSafe(fields[4]);
                g.nSupportedLanguages = toArray(g.supportedLanguages, fields[5]);
                g.metacriticScore     = parseIntSafe(fields[6]);
                g.userScore           = parseDoubleSafe(fields[7]);
                g.achievements        = parseIntSafe(fields[8]);
                g.nPublishers         = toArray(g.publishers, fields[9]);
                g.nDevelopers         = toArray(g.developers, fields[10]);
                g.nCategories         = toArray(g.categories, fields[11]);
                g.nGenres             = toArray(g.genres, fields[12]);
                g.nTags               = toArray(g.tags, fields[13]);

                normalizar(&g);

                gamesList[count] = g;
                count = count + 1;
            }
        }
    }

    fclose(fp);
    return count;
}

//---------------------------||---------------------------\\

void printGame ( const Games *g )
{
    printf("Game [id=%d, name=%s, releaseDate=%s, owners=%d, price=%.2f, meta=%d, userScore=%.2f]\n",
           g->id, g->name, g->releaseDate, g->estimatedOwners, g->price, g->metacriticScore, g->userScore );
}

//---------------------------||---------------------------\\

int main ( )
{
    Games gamesList[MAX_LIST];
    int total = 0;
    int i = 0;

    total = readCSV("tmp/games.csv", gamesList);

    //printf("Total de jogos carregados: %d\n", total);
   // printf("\nLista completa de jogos:\n\n");

    i = 0;
    while ( i < total )
    {
        printGame(&gamesList[i]);
        i = i + 1;
    }

    return 0;
}
