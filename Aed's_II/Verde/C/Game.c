#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include <time.h>

#define MAX_LINE 4096
#define MAX_GAMES 5000
#define MAX_STRING 1024
#define MATRICULA "845963"

#ifdef _WIN32
#include <strings.h>
#ifndef strcasecmp
#define strcasecmp _stricmp
#endif
#ifndef strncasecmp
#define strncasecmp _strnicmp
#endif
#endif

typedef struct {
    int id;
    char name[MAX_STRING];
    char releaseDate[12];
    int estimatedOwners;
    double price;
    char supportedLanguages[MAX_STRING];
    int metacriticScore;
    double userScore;
    int achievements;
    char publishers[MAX_STRING];
    char developers[MAX_STRING];
    char categories[MAX_STRING];
    char genres[MAX_STRING];
    char tags[MAX_STRING];
} Game;

// Remove espaços do início e fim da string
void trim(char *str) {
    if (!str || *str == '\0') return;
    char *start = str;
    char *end = str + strlen(str) - 1;
    while (isspace((unsigned char)*start)) start++;
    while (end > start && isspace((unsigned char)*end)) end--;
    size_t len = (end - start) + 1;
    memmove(str, start, len);
    str[len] = '\0';
}

// Remove caracteres indesejados
void removeChars(char *str, const char *charsToRemove) {
    char *src = str, *dst = str;
    while (*src) {
        if (strchr(charsToRemove, *src) == NULL) {
            *dst++ = *src;
        }
        src++;
    }
    *dst = '\0';
}

// Retorna número do mês
int getMonthNumber(char *month) {
    char *months[] = { "Jan","Feb","Mar","Apr","May","Jun",
                       "Jul","Aug","Sep","Oct","Nov","Dec" };
    char *fullMonths[] = { "January","February","March","April","May","June",
                           "July","August","September","October","November","December" };
    for (int i = 0; i < 12; i++) {
        if (strncasecmp(month, months[i], 3) == 0 || strcasecmp(month, fullMonths[i]) == 0)
            return i + 1;
    }
    return 0;
}

// Converte datas para dd/mm/aaaa
void parseDate(char *input, char *output) {
    char inputCopy[MAX_STRING];
    strcpy(inputCopy, input);
    trim(inputCopy);

    int day = 0, month = 0, year = 0;
    char monthStr[50];

    if (sscanf(inputCopy, "%s %d, %d", monthStr, &day, &year) == 3) {
        month = getMonthNumber(monthStr);
        if (month > 0) { sprintf(output, "%02d/%02d/%04d", day, month, year); return; }
    }
    if (sscanf(inputCopy, "%d-%d-%d", &year, &month, &day) == 3 && year > 1000) {
        sprintf(output, "%02d/%02d/%04d", day, month, year); return;
    }
    if (sscanf(inputCopy, "%d/%d/%d", &day, &month, &year) == 3 && day <= 31 && month <= 12) {
        sprintf(output, "%02d/%02d/%04d", day, month, year); return;
    }
    strcpy(output, "01/01/1970");
}

// Extrai número de donos estimados
int extractEstimatedOwners(char *raw) {
    char buffer[MAX_STRING]; int j = 0;
    for (int i = 0; raw[i] != '\0' && i < MAX_STRING - 1; i++)
        if (isdigit(raw[i])) buffer[j++] = raw[i];
    buffer[j] = '\0';
    return (j > 0) ? atoi(buffer) : 0;
}

// Extrai preço
double extractPrice(char *raw) {
    if (strcasecmp(raw, "Free to Play") == 0) return 0.0;
    return atof(raw);
}

// Extrai user score
double extractUserScore(char *raw) {
    if (strcasecmp(raw, "tbd") == 0) return -1.0;
    return atof(raw);
}

// Extrai achievements
int extractAchievements(char *raw) {
    trim(raw);
    return (strlen(raw) == 0) ? 0 : atoi(raw);
}

// Divide linha em campos
int parseLine(char *line, char fields[][MAX_STRING]) {
    int inQuotes = 0, fieldIndex = 0, bufferIndex = 0;
    for (int i = 0; line[i] != '\0' && fieldIndex < 20; i++) {
        char c = line[i];
        if (c == '"') inQuotes = !inQuotes;
        else if (c == ',' && !inQuotes) {
            fields[fieldIndex][bufferIndex] = '\0';
            trim(fields[fieldIndex]);
            fieldIndex++; bufferIndex = 0;
        } else {
            if (bufferIndex < MAX_STRING - 1) fields[fieldIndex][bufferIndex++] = c;
        }
    }
    fields[fieldIndex][bufferIndex] = '\0';
    trim(fields[fieldIndex]);
    return fieldIndex + 1;
}

// Processa campos em struct Game
void processGame(Game *game, char fields[][MAX_STRING]) {
    game->id = atoi(fields[0]);
    strncpy(game->name, fields[1], MAX_STRING - 1); game->name[MAX_STRING - 1] = '\0';
    parseDate(fields[2], game->releaseDate);
    game->estimatedOwners = extractEstimatedOwners(fields[3]);
    game->price = extractPrice(fields[4]);

    strncpy(game->supportedLanguages, fields[5], MAX_STRING - 1); 
    game->supportedLanguages[MAX_STRING - 1] = '\0';
    removeChars(game->supportedLanguages, "[]\"''"); trim(game->supportedLanguages);

    game->metacriticScore = atoi(fields[6]);
    if (game->metacriticScore == 0) game->metacriticScore = -1;

    game->userScore = extractUserScore(fields[7]);
    game->achievements = extractAchievements(fields[8]);

    strncpy(game->publishers, fields[9], MAX_STRING - 1); game->publishers[MAX_STRING - 1] = '\0';
    removeChars(game->publishers, "[]\"''"); trim(game->publishers);

    strncpy(game->developers, fields[10], MAX_STRING - 1); game->developers[MAX_STRING - 1] = '\0';
    removeChars(game->developers, "[]\"''"); trim(game->developers);

    strncpy(game->categories, fields[11], MAX_STRING - 1); game->categories[MAX_STRING - 1] = '\0';
    removeChars(game->categories, "[]\"''"); trim(game->categories);

    strncpy(game->genres, fields[12], MAX_STRING - 1); game->genres[MAX_STRING - 1] = '\0';
    removeChars(game->genres, "[]\"''"); trim(game->genres);

    strncpy(game->tags, fields[13], MAX_STRING - 1); game->tags[MAX_STRING - 1] = '\0';
    removeChars(game->tags, "[]\"''"); trim(game->tags);
}

// Formata e imprime Game
void printGame(Game *game) {
    char supportedLanguages[MAX_STRING], publishers[MAX_STRING], developers[MAX_STRING];
    char categories[MAX_STRING], genres[MAX_STRING], tags[MAX_STRING];

    #define formatList(dest, src) \
        { int j=0; for(int i=0; src[i]!='\0'&&j<MAX_STRING-2; i++){ dest[j++] = src[i]; if(src[i]==',') dest[j++]=' '; } dest[j]='\0'; }

    formatList(supportedLanguages, game->supportedLanguages);
    formatList(publishers, game->publishers);
    formatList(developers, game->developers);
    formatList(categories, game->categories);
    formatList(genres, game->genres);
    formatList(tags, game->tags);

    printf("=> %d ## %s ## %s ## %d ## %.2f ## [%s] ## %d ## %.1f ## %d ## [%s] ## [%s] ## [%s] ## [%s] ## [%s] ##\n",
        game->id, game->name, game->releaseDate, game->estimatedOwners, game->price,
        supportedLanguages, game->metacriticScore, game->userScore, game->achievements,
        publishers, developers, categories, genres, tags
    );
}

// Busca Game por ID
int findGameById(Game *games, int count, int targetId) {
    for(int i=0;i<count;i++) if(games[i].id==targetId) return i;
    return -1;
}

// Selection Sort por nome (case-insensitive)
void selectionSortByName(Game *games, int n, long long *comparisons, long long *movements) {
    for(int i=0;i<n-1;i++){
        int minIdx = i;
        for(int j=i+1;j<n;j++){
            (*comparisons)++;
            if(strcasecmp(games[j].name, games[minIdx].name)<0) minIdx=j;
        }
        if(minIdx!=i){
            Game tmp = games[i]; games[i]=games[minIdx]; games[minIdx]=tmp;
            (*movements)+=3;
        }
    }
}

int main() {
    const char *path = "/tmp/games.csv";
    Game *games = (Game *) malloc(MAX_GAMES*sizeof(Game));
    if(!games){ fprintf(stderr,"Erro ao alocar memoria\n"); return 1; }

    int gameCount=0;
    FILE *file = fopen(path,"r");
    if(!file){ fprintf(stderr,"Erro ao abrir arquivo: %s\n",path); free(games); return 1; }

    char line[MAX_LINE];
    if(fgets(line,MAX_LINE,file)==NULL){ fclose(file); free(games); return 1; }

    while(fgets(line,MAX_LINE,file) && gameCount<MAX_GAMES){
        line[strcspn(line,"\n")]='\0';
        if(strlen(line)==0) continue;
        char fields[20][MAX_STRING];
        int fieldCount=parseLine(line,fields);
        if(fieldCount>=14){ processGame(&games[gameCount],fields); gameCount++; }
    }
    fclose(file);

    Game *selected = (Game *) malloc(MAX_GAMES*sizeof(Game));
    if(!selected){ fprintf(stderr,"Erro ao alocar memória para selecionados\n"); free(games); return 1; }

    int selCount=0;
    char input[MAX_STRING];
    while(fgets(input,MAX_STRING,stdin)){
        input[strcspn(input,"\n")]='\0'; trim(input);
        if(strcasecmp(input,"FIM")==0) break;
        int id=atoi(input); int idx=findGameById(games,gameCount,id);
        if(idx!=-1) selected[selCount++]=games[idx];
    }

    long long comparisons=0, movements=0;
    clock_t start=clock();
    selectionSortByName(selected,selCount,&comparisons,&movements);
    clock_t end=clock();
    double elapsed=((double)(end-start))/CLOCKS_PER_SEC;

    char logName[256]; snprintf(logName,sizeof(logName),"%s_selecao.txt",MATRICULA);
    FILE *logFile=fopen(logName,"w");
    if(logFile){ fprintf(logFile,"%s\t%lld\t%lld\t%.6f\n",MATRICULA,comparisons,movements,elapsed); fclose(logFile); }

    for(int i=0;i<selCount;i++) printGame(&selected[i]);

    free(selected); free(games);
    return 0;
}
