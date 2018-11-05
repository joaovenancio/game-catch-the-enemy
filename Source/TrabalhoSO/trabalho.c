#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <unistd.h>
#include <pthread.h>

#define MAX_CONSUMIDROES 3

//Variveis globais:
pthread_mutex_t mutex;
//----------------------------------------------------------------------------

//Definir a estrutura da minha multilista:
typedef struct noduloMultilista {
    int val;
    struct noduloMultilista * proximo;
    struct nodulo * lista;
} noduloMultilista_t;

//Definir a estrutura da minha lista que esta dentro da multilista:
typedef struct nodulo {
    int val;
    struct nodulo * proximo;
} nodulo_t;

//Ele adiciona ao fim da lista:
void adicionar(noduloMultilista_t * cabeca, int prioridade, int val) {
	//Achar a prioridade:
	while (cabeca->val != prioridade) {
		if (cabeca->proximo != NULL) {
			cabeca = cabeca->proximo;
		} else {
			break;
			printf("Não existe essa prioridade.\n");
		}
	}
	printf("Prioridade: %i\n",cabeca->val );
	//
    nodulo_t * atual = cabeca->lista;
    if (atual != NULL) {
    	while (atual->proximo != NULL) {
     	   	atual = atual->proximo;
    	}
    	//Encontrou o proximo nulo:
	    //Agora podemos adicionar uma nova variavel:
	    atual->proximo = malloc(sizeof(nodulo_t));
	    atual->proximo->val = val;
	    atual->proximo->proximo = NULL;
	    printf("Segundo\n");

    } else {
    	printf("Primeiro\n");
    	atual = malloc(sizeof(nodulo_t));
    	atual->val = val;
		atual->proximo = NULL;

		cabeca->lista = atual;
    }
}

//Retorna 0 caso nao tiver na prioridade 0, -1 e -2 tambem
int remover_primeiro(noduloMultilista_t * cabeca, int prioridade) {
	while (cabeca->val != prioridade) {
		if (cabeca->proximo != NULL) {
			cabeca = cabeca->proximo;
		} else {
			printf("Não existe essa prioridade.\n");
			break;
		}
	}
	//Pega a lista da prioridade escolhida:
	nodulo_t * atual = cabeca->lista;
    int retorno = cabeca->val;
    /* if there is only one item in the list, remove it */
    if (atual == NULL) {
        return retorno;
    }
    //Pega o primeiro da lista e e retorna o seu valor para quem chamou o metodo, depois o tira da memoria e aponta para o proximo da lista:
    retorno = atual->val;
    cabeca->lista = atual->proximo;
    //Limpar o dado:
    atual->val = 0;
    atual->proximo = NULL;
    free(atual);
    return retorno;

}

void imprimir(noduloMultilista_t * cabeca) {
	//Achar a prioridade:
	while (cabeca != NULL) {

		nodulo_t * atual = cabeca->lista;
	    if (atual != NULL) {
	    	printf("Prioridade %i e processo de valor %i\n", cabeca->val,atual->val);
	    	while (atual->proximo != NULL) {
	    		printf("Prioridade %i e processo de valor %i\n", cabeca->val,atual->proximo->val);
	     	   	atual = atual->proximo;
	    	}
	    }

		cabeca = cabeca->proximo;
	}
}

void configurarMultilista(noduloMultilista_t * cabeca) {
	//Cofigurar o de prioridade 0 (maior):
	noduloMultilista_t * multilista = cabeca;
	multilista->val = 0;
	multilista->proximo = malloc(sizeof(noduloMultilista_t));
	multilista->lista = NULL;

	//Cofigurar o de prioridade 1:
	multilista->proximo->val = 1;
	multilista->proximo->proximo = malloc(sizeof(noduloMultilista_t));
	multilista->proximo->lista = NULL;

	//Configurar o de prioridade 2:
	multilista->proximo->proximo->val = 2;
	multilista->proximo->proximo->proximo = NULL;
	multilista->proximo->proximo->lista = NULL;

	return;
}

void produtor(noduloMultilista_t * cabeca) {
	//Gerador de cidadoes:
	int semente = time(0);//Pega o tempo do sistema rodando
	srand(semente);//Insere uma semete no dispositivo de gerar numeros aleatorios
	int numeroaleatorioprioridade = rand() %4; //Um numero "aleatorio" entre 0 e 3
	int numeroaleatorio = rand() %201 //Um numero "aleatorio" entre 0 e 200

	char rodando = 1; //!!!!!!!!!!!!!!!!

	while (rodando) {
		pthread_mutex_lock(&mutex); //Trancar o uso da multilista

		adicionar(cabeca,numeroaleatorioprioridade,numeroaleatorio);

		pthread_mutex_unlock(&mutex); //Liberar o uso da multilista


		sleep(500000);
	}
	


}

void consumidor() {

}

int main () {
	//Configurar a pthread para gerar os alunos:
	pthread_t produtor;
	//Configurar a pthread para consumir os alunos:
	pthread_t consumidores[MAX_CONSUMIDROES];
	//Inicializar o mutex:
	pthread_mutex_init(&mutex, NULL);

	//https://www.learn-c.org/en/Linked_lists
	noduloMultilista_t * cabeca = malloc(sizeof(noduloMultilista_t));
	configurarMultilista(cabeca);

	adicionar(cabeca,0,1);
	adicionar(cabeca,0,2);
	adicionar(cabeca,0,32);
	adicionar(cabeca,0,3);
	adicionar(cabeca,2,32);
	adicionar(cabeca,2,40);
	adicionar(cabeca,2,50);

	remover_primeiro(cabeca, 0);
	remover_primeiro(cabeca, 0);

	printf("-------------\n");

	produtor(cabeca);

	sleep(50000000);

	imprimir(cabeca);




	return 0;
}
