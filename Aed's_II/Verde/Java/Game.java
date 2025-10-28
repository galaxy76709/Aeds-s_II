#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include <time.h>

#define MAX_STR 200
#define MAX_LISTA 1000
#define MAX_VETOR 100

static char *meu_strdup(const char *s) {
    if (!s) return NULL;
    size_t n = strlen(s) + 1;
    char *p = (char*)malloc(n);
    if (p) memcpy(p, s, n);
    return p;
}

static int meu_strcasecmp(const char *a, const char *b) {
    if (!a || !b) return (a==b)?0:(a?1:-1);
    while (*a && *b) {
        unsigned char ca = (unsigned char) tolower((unsigned char)*a);
        unsigned char cb = (unsigned char) tolower((unsigned char)*b);
        if (ca != cb) return ca - cb;
        a++; b++;
    }
    return (unsigned char) tolower((unsigned char)*a) - (unsigned char) tolower((unsigned char)*b);
}

typedef struct Jogo {
    int identificadorApp;
    char nome[MAX_STR];
    char dataLancamento[MAX_STR];
    int proprietariosEstimados;
    double preco;
    char idiomasSuportados[MAX_VETOR][MAX_STR];
    int numIdiomas;
    int pontuacaoMetacritic;
    double pontuacaoUsuario;
    int conquistas;
    char editoras[MAX_VETOR][MAX_STR];
    int numEditoras;
    char desenvolvedores[MAX_VETOR][MAX_STR];
    int numDesenvolvedores;
    char categorias[MAX_VETOR][MAX_STR];
    int numCategorias;
    char generos[MAX_VETOR][MAX_STR];
    int numGeneros;
    char etiquetas[MAX_VETOR][MAX_STR];
    int numEtiquetas;

    char proprietariosEstimadosBruto[MAX_STR];
    char precoBruto[MAX_STR];
    char pontuacaoUsuarioBruta[MAX_STR];
    char conquistasBruto[MAX_STR];
} Jogo;

long comparacoes = 0;
long movimentacoes = 0;

int parseInteiroDigitos(const char *s) {
    char tmp[MAX_STR]; int j=0;
    if (!s) return 0;
    for (int i=0; s[i]; i++) {
        if (isdigit((unsigned char)s[i])) tmp[j++]=s[i];
    }
    tmp[j]='\0';
    return j>0?atoi(tmp):0;
}

double parseDoubleSeguro(const char *s) {
    if (!s) return 0.0;
    if (meu_strcasecmp(s, "Free to Play")==0) return 0.0;
    return atof(s);
}

int dividirParaVetor(char destino[MAX_VETOR][MAX_STR], const char *s) {
    if (!s || strlen(s)==0) return 0;
    char copia[MAX_STR*2]; strncpy(copia, s, sizeof(copia)-1); copia[sizeof(copia)-1]='\0';
    for (int i=0; copia[i]; i++) if (copia[i]=='['||copia[i]==']'||copia[i]=='\"' || copia[i]=='\'' ) copia[i]=' ';
    int contador=0;
    char *tok = strtok(copia, ",");
    while (tok && contador<MAX_VETOR) {
        while (*tok==' ') tok++;
        strncpy(destino[contador], tok, MAX_STR-1); destino[contador][MAX_STR-1]='\0';
        contador++;
        tok = strtok(NULL, ",");
    }
    return contador;
}

void formatarDados(Jogo *jogo) {
    if (strlen(jogo->dataLancamento)==0) strcpy(jogo->dataLancamento, "01/01/1970");
    jogo->proprietariosEstimados = parseInteiroDigitos(jogo->proprietariosEstimadosBruto);
    jogo->preco = parseDoubleSeguro(jogo->precoBruto);
    if (jogo->pontuacaoUsuarioBruta[0]==0 || meu_strcasecmp(jogo->pontuacaoUsuarioBruta, "tbd")==0) jogo->pontuacaoUsuario=-1.0;
    else jogo->pontuacaoUsuario = atof(jogo->pontuacaoUsuarioBruta);
    if (jogo->pontuacaoMetacritic==0) jogo->pontuacaoMetacritic=-1;
    jogo->conquistas = parseInteiroDigitos(jogo->conquistasBruto);
}

void imprimirJogo(const Jogo *jogo) {
    printf("=> %d ## %s ## %s ## %d ## %.2f ## ", jogo->identificadorApp, jogo->nome, jogo->dataLancamento, jogo->proprietariosEstimados, jogo->preco);
    printf("[");
    for (int i=0;i<jogo->numIdiomas;i++) { printf("%s", jogo->idiomasSuportados[i]); if (i<jogo->numIdiomas-1) printf(", "); }
    printf("] ## %d ## %.1f ## %d ## ", jogo->pontuacaoMetacritic, jogo->pontuacaoUsuario, jogo->conquistas);
    printf("["); for (int i=0;i<jogo->numDesenvolvedores;i++){ printf("%s", jogo->desenvolvedores[i]); if (i<jogo->numDesenvolvedores-1) printf(", "); } printf("] ## ");
    printf("["); for (int i=0;i<jogo->numEditoras;i++){ printf("%s", jogo->editoras[i]); if (i<jogo->numEditoras-1) printf(", "); } printf("] ## ");
    printf("["); for (int i=0;i<jogo->numCategorias;i++){ printf("%s", jogo->categorias[i]); if (i<jogo->numCategorias-1) printf(", "); } printf("] ## ");
    printf("["); for (int i=0;i<jogo->numGeneros;i++){ printf("%s", jogo->generos[i]); if (i<jogo->numGeneros-1) printf(", "); } printf("] ## ");
    printf("["); for (int i=0;i<jogo->numEtiquetas;i++){ printf("%s", jogo->etiquetas[i]); if (i<jogo->numEtiquetas-1) printf(", "); } printf("] ##\n");
}

int compararJogo(const Jogo *a, const Jogo *b) {
    int diaA=1,mesA=1,anoA=1970; int diaB=1,mesB=1,anoB=1970;
    if (a->dataLancamento[0]) sscanf(a->dataLancamento, "%d/%d/%d", &diaA, &mesA, &anoA);
    if (b->dataLancamento[0]) sscanf(b->dataLancamento, "%d/%d/%d", &diaB, &mesB, &anoB);
    comparacoes++; if (anoA<anoB) return -1; comparacoes++; if (anoA>anoB) return 1;
    comparacoes++; if (mesA<mesB) return -1; comparacoes++; if (mesA>mesB) return 1;
    comparacoes++; if (diaA<diaB) return -1; comparacoes++; if (diaA>diaB) return 1;
    comparacoes++; if (a->identificadorApp < b->identificadorApp) return -1; comparacoes++; if (a->identificadorApp > b->identificadorApp) return 1;
    return 0;
}

int particionar(Jogo arr[], int inicio, int fim) {
    Jogo pivo = arr[fim];
    int i = inicio - 1;
    for (int j=inicio;j<fim;j++) {
        if (compararJogo(&arr[j], &pivo) < 0) {
            i++;
            Jogo temp = arr[i]; arr[i]=arr[j]; arr[j]=temp; movimentacoes++;
        }
    }
    Jogo temp = arr[i+1]; arr[i+1]=arr[fim]; arr[fim]=temp; movimentacoes++;
    return i+1;
}

void quicksort(Jogo arr[], int inicio, int fim) {
    if (inicio<fim) {
        int pi = particionar(arr, inicio, fim);
        quicksort(arr, inicio, pi-1);
        quicksort(arr, pi+1, fim);
    }
}

int lerCSV(const char *caminho, Jogo lista[], int max) {
    FILE *fp = fopen(caminho, "r");
    if (!fp) return 0;
    char linha[4096];
    if (!fgets(linha, sizeof(linha), fp)) { fclose(fp); return 0; }
    int contador=0;
    while (fgets(linha, sizeof(linha), fp) && contador<max) {
        linha[strcspn(linha, "\n")]='\0';
        if (strlen(linha)==0) continue;
        
        char *campos[14];
        int idxCampos=0;
        char buf[4096]; int idxBuf=0; int entreAspas=0;
        for (int i=0; linha[i]; i++) {
            char c=linha[i];
            if (c=='"') { entreAspas = !entreAspas; }
            if (c==',' && !entreAspas) { buf[idxBuf]='\0'; campos[idxCampos++]=meu_strdup(buf); idxBuf=0; }
            else { buf[idxBuf++]=c; }
        }
        buf[idxBuf]='\0'; if (idxCampos<14) campos[idxCampos++]=meu_strdup(buf);
        
        if (idxCampos>=14) {
            Jogo jogo; memset(&jogo,0,sizeof(Jogo));
            jogo.identificadorApp = atoi(campos[0]);
            strncpy(jogo.nome, campos[1], MAX_STR-1);
            strncpy(jogo.dataLancamento, campos[2], MAX_STR-1);
            strncpy(jogo.proprietariosEstimadosBruto, campos[3], MAX_STR-1);
            strncpy(jogo.precoBruto, campos[4], MAX_STR-1);
            jogo.numIdiomas = dividirParaVetor(jogo.idiomasSuportados, campos[5]);
            jogo.pontuacaoMetacritic = atoi(campos[6]);
            strncpy(jogo.pontuacaoUsuarioBruta, campos[7], MAX_STR-1);
            strncpy(jogo.conquistasBruto, campos[8], MAX_STR-1);
            jogo.numEditoras = dividirParaVetor(jogo.editoras, campos[9]);
            jogo.numDesenvolvedores = dividirParaVetor(jogo.desenvolvedores, campos[10]);
            jogo.numCategorias = dividirParaVetor(jogo.categorias, campos[11]);
            jogo.numGeneros = dividirParaVetor(jogo.generos, campos[12]);
            jogo.numEtiquetas = dividirParaVetor(jogo.etiquetas, campos[13]);
            formatarDados(&jogo);
            lista[contador++]=jogo;
        }
        for (int k=0;k<idxCampos;k++) free(campos[k]);
    }
    fclose(fp);
    return contador;
}

int buscarPorId(Jogo lista[], int numItens, int id) {
    for (int i=0;i<numItens;i++) if (lista[i].identificadorApp==id) return i;
    return -1;
}

int main() {
    char *caminhos[] = {"/tmp/games.csv" };
    Jogo bancoDeDados[MAX_LISTA]; int total=0;
    for (int p=0;p<4 && total==0;p++) total = lerCSV(caminhos[p], bancoDeDados, MAX_LISTA);
    if (total==0) { fprintf(stderr, "games.csv not found\n"); return 1; }

    Jogo selecionados[MAX_LISTA]; int numSelecionados=0;
    char linha[128];
    while (fgets(linha, sizeof(linha), stdin)) {
        linha[strcspn(linha, "\n")]='\0'; if (strcmp(linha,"FIM")==0) break; if (strlen(linha)==0) continue;
        int id = atoi(linha);
        int indice = buscarPorId(bancoDeDados, total, id);
        if (indice>=0) selecionados[numSelecionados++]=bancoDeDados[indice];
    }

    comparacoes=0; movimentacoes=0;
    clock_t inicioTempo = clock();
    if (numSelecionados>0) quicksort(selecionados, 0, numSelecionados-1);
    clock_t fimTempo = clock();
    long tempoExecucao = (long)((fimTempo-inicioTempo)*1000000/ CLOCKS_PER_SEC);

    for (int i=0;i<numSelecionados;i++) imprimirJogo(&selecionados[i]);

    FILE *arquivoLog = fopen("845963_quicksort.txt","w");
    if (arquivoLog) { fprintf(arquivoLog, "845963\t%ld\t%ld\t%ld\n", comparacoes, movimentacoes, tempoExecucao); fclose(arquivoLog); }

    return 0;
}