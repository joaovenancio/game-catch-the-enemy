#include <stdio.h>
#include <stdlib.h>
#include <time.h>

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

int main () {
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

	imprimir(cabeca);

	return 0;
}
